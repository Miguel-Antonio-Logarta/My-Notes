from database import Database

class BaseModel:
    table_name = None

    def __init__(self, **kwargs):
        if not isinstance(self.table_name, str):
            raise ValueError("table_name has not been set!")
        
        print(f"{self.table_name} is good to go")

class Column:
    def __init__(self, table: str, name: str, data_type: str = None, is_primary_key = False):
        self.parent_table = table
        self.column_name = name
        self.data_type = data_type
        self.is_primary_key = is_primary_key

    def __str__(self) -> str:
        return self.column_name

    def __repr__(self) -> str:
        return str({
            "parent_table": self.parent_table,
            "column_name": self.column_name,
            "data_type": self.data_type,
            "is_primary_key": self.is_primary_key
        })


class Query:
    # TODO: Refactor database injection. Currently we call it like "q = database.QueryBuilder(Database.database)"
    # We have to pass an instance of the database into the query

    operators = {"=", ">=", "<=", ">", "<", "AND", "OR", "NOT"}
    join_types = {"INNER", "OUTER", "LEFT", "RIGHT"}

    def __init__(self, db: Database) -> None:
        self.mode = None

        # This might need to be restructed to support C, U, D operations
        self.query_object = {
                "select": [],
                "from": None,
                "join": [],
                "where": {
                    "stmt": None,
                    "values": []
                },
                "limit": None,
                "order_by": {
                    "column": None,
                    "asc": False
                },
                "offset": None,
                "insert": {
                    "table": None,
                    "stmt": None,
                    "no_values_provided": True,
                    "values": {}
                },
                "update": {
                    "table": None,
                    "stmt": None,
                    "no_values_provided": True,
                    "values": {}
                },
                "delete": {
                    "table": None,
                    "delete_all": False,
                    "stmt": None,
                }
            } 
        self.query_stmt = None
        self.query_values = []
        self.db = db
        self.last_row_id = None

    def select(self, *args):
        # TODO: Add support for AS keyword
        self.mode = "SELECT"
        if len(args) == 0:
            raise ValueError("No table selected!")
        
        from_table = None # The first model passed will be the table to be selected from
        for arg in args:
            select_string = ""
            
            if isinstance(arg, Column):
                # Select column
                column = arg
                select_string = f"{column.parent_table}.{column.column_name}"
                if from_table is None:
                    from_table = column.parent_table
            elif issubclass(arg, BaseModel):
                # Select all columns from this table
                table = arg
                select_string = f"{table.table_name}.*"
                if from_table is None:
                    from_table = table.table_name
            else:
                raise ValueError(f"Not a valid model: {arg}")
        
            self.query_object["select"].append(select_string)
        
        self.query_object["from"] = from_table
        return self
  
    def where(self, *args):
        # TODO: Add a check to see if the column that is referenced is included in the "join" or "from" 
        # If it is not, that means that the user is referencing a column that is not in the table
        where_tokens = []
        values = []
        for arg in args:
            if isinstance(arg, Column):
                # Needs to belong to same table in UPDATE and DELETE
                if self.mode == "UPDATE" and arg.parent_table != self.query_object["update"]["table"]:
                    raise ValueError(f'Column {arg.parent_table}.{arg.column_name} does not belong to table {self.query_object["update"]["table"]}')
                
                if self.mode == "DELETE" and arg.parent_table != self.query_object["delete"]["table"]:
                    raise ValueError(f'Column {arg.parent_table}.{arg.column_name} does not belong to table {self.query_object["update"]["table"]}')
                
                where_tokens.append(f'{arg.parent_table}.{arg.column_name}')
            elif arg in self.operators:
                where_tokens.append(arg)
            else:
                if not (isinstance(arg, (str, int))):
                    raise ValueError(f'{arg} is not a valid string or integer')
                where_tokens.append("%s")
                values.append(arg)

        # print("Values: ", values)
        self.query_object["where"]["stmt"] = " ".join(where_tokens)
        self.query_object["where"]["values"] = values
        # print("where values Values: ", self.query_object["where"]["values"])

        return self
                
            # .join(models.Movie, type="LEFT", on=(Language.id, "=", Movie.language_id))
    def join(self, model: BaseModel, join_type: str, on: tuple):
        if not issubclass(model, BaseModel):
            raise ValueError("Not a valid table!")
        elif model.table_name == self.query_object["from"]:
            raise ValueError("Not a unique table!")
        elif join_type not in self.join_types:
            raise ValueError("Not a valid join type!")
        elif len(on) == 0:
            raise ValueError("No predicate provided to join tables")
        
        join_obj = {
            "table": None,
            "join_type": None,
            "on": None,
            "values": []
        }

        join_obj["table"] = model.table_name
        join_obj["join_type"] = join_type

        on_tokens = []
        on_values = []
        for arg in on:
            if isinstance(arg, Column):
                on_tokens.append(f'{arg.parent_table}.{arg.column_name}')
            elif arg in self.operators:
                on_tokens.append(arg)
            else:
                if not (isinstance(arg, (str, int))):
                    raise ValueError(f'{arg} is not a valid string or integer')
                on_tokens.append("%s")
                on_values.append(arg)

        join_obj["on"] = " ".join(on_tokens)
        join_obj["values"] = on_values

        self.query_object["join"].append(join_obj)
        return self

    def insert(self, model):
        # Parameters: Either 1 model, or multiple columns
        # INSERT INTO TABLE (Table columns) VALUES ()
        self.mode = "INSERT"
        if not issubclass(model, BaseModel):
            raise ValueError("Not a valid table!")
        
        # Hacky, but it works
        values = {}
        for attr, v in model.__dict__.items():
            if not (attr.startswith("__") or attr.startswith("table")):
                values[f'{str(v)}'] = None
    
        self.query_object["insert"]["table"] = model.table_name
        self.query_object["insert"]["values"] = values
        
        return self


    def update(self, model):
        self.mode = "UPDATE"
        if not issubclass(model, BaseModel):
            raise ValueError("Not a valid table!")
        
        # Hacky, but it works
        values = {}
        for attr, v in model.__dict__.items():
            if not (attr.startswith("__") or attr.startswith("table")):
                values[f'{str(v)}'] = None
    
        self.query_object["update"]["table"] = model.table_name
        self.query_object["update"]["values"] = values
        # print(values)
        return self

    def delete(self, model, delete_all=False):
        self.mode = "DELETE"
        if not issubclass(model, BaseModel):
            raise ValueError("Not a valid table!")
        
        self.query_object["delete"]["table"] = model.table_name
        self.query_object["delete"]["delete_all"] = delete_all

        return self

    def values(self, data: dict):
        # All keys need to be columns of a table,
        # Check Mode
        # Loop through keys
        # If keys are not in the dict created by INSERT
        # throw an error
        if self.mode == "INSERT":
            for column, value in data.items():
                if column in self.query_object["insert"]["values"]:
                    self.query_object["insert"]["values"][column] = value
                    self.query_object["insert"]["no_values_provided"] = False
                else:
                    raise ValueError(f'{column} column does not exist in {self.query_object["insert"]["table"]} table')
        elif self.mode == "UPDATE":
            for column, value in data.items():
                if column in self.query_object["update"]["values"]:
                    self.query_object["update"]["values"][column] = value
                    self.query_object["update"]["no_values_provided"] = False
                else:
                    raise ValueError(f'{column} column does not exist in {self.query_object["update"]["table"]} table')
        return self
    
    def order_by(self, column: Column, asc=False):
        if self.mode != "SELECT":
            raise ValueError("GROUP BY not allowed")

        if not isinstance(column, Column):
            raise ValueError("Not a valid column!")
        
        self.query_object["order_by"]["column"] = f'{column.parent_table}.{column.column_name}'
        self.query_object["order_by"]["asc"] = asc
            
        return self

    
    def build_query(self) -> None:
        """
        A sql query consists of parts:
        SELECT ....
        FROM ... (tablename)
            JOIN (tablename) ON ... <- n times
        WHERE ...
        """
        if self.mode == "SELECT":
            if self.query_object["from"] is None or len(self.query_object["select"]) == 0:
                raise ValueError("Nothing to query from!")
            
            # SELECT ...
            select_stmt = "SELECT "
            select_stmt += ", ".join(self.query_object["select"])

            # FROM ...
            select_stmt += f' FROM {self.query_object["from"]}'

            # JOIN... (optional)
            select_values = []
            for join_obj in self.query_object["join"]:
                # if join_obj["on"] == "INNER":
                # INNER JOIN Movie ON Language.id = Movie.language_id
                select_stmt += f' {join_obj["join_type"]} JOIN {join_obj["table"]} ON {join_obj["on"]}'
                select_values.extend(join_obj["values"])


            # WHERE ... (optional)
            if self.query_object["where"]["stmt"] is not None:
                select_stmt += " WHERE"
                select_stmt += f' {self.query_object["where"]["stmt"]}'
                select_values.extend(self.query_object["where"]["values"])

            # ORDER BY ... (optional)
            if self.query_object["order_by"]["column"] is not None:
                select_stmt += f' ORDER BY {self.query_object["order_by"]["column"]}'
                if self.query_object["order_by"]["asc"]:
                    select_stmt += " ASC"
                else:
                    select_stmt += " DESC"

            self.query_stmt = select_stmt
            self.query_values = select_values
            
        elif self.mode == "INSERT":
            if self.query_object["insert"]["no_values_provided"] is True:
                raise ValueError("No values provided to insert statement!")
            
            # INSERT INTO [TABLE] ([TABLE COLUMNS...]) VALUES (%s, %s, ...)
            insert_stmt = f'INSERT INTO {self.query_object["insert"]["table"]} ('
            insert_values = []
            
            columns = []
            for column_name, value in self.query_object["insert"]["values"].items():
                if value is not None:
                    columns.append(f'{self.query_object["insert"]["table"]}.{column_name}')
                    insert_values.append(value)

            insert_stmt += f'{", ".join(columns)})'
            insert_stmt += f' VALUES ({", ".join(["%s" for _ in insert_values])})'

            self.query_stmt = insert_stmt
            self.query_values = insert_values

            # print(self.query_object["insert"])
        elif self.mode == "UPDATE":
            if self.query_object["update"]["no_values_provided"] is True:
                raise ValueError("No values provided to update statement!")
            
            if self.query_object["where"]["stmt"] is None:
                raise ValueError("where() not called after update() command")
            
            # UPDATE [TABLE]  WHERE ...
            update_stmt = f'UPDATE {self.query_object["update"]["table"]} SET '
            
            # SET TABLE.COL1 = %s, TABLE.COL2 = %s, ...
            update_tokens = []
            update_values = []
            for column_name, value in self.query_object["update"]["values"].items():
                if value is not None:
                    update_tokens.append(f'{self.query_object["update"]["table"]}.{column_name} = %s')
                    update_values.append(value)
                    # columns.append(f'{self.query_object["update"]["table"]}.{column_name}')

            update_stmt += ", ".join(update_tokens)

            # WHERE %s, %s... (required for safety)
            # TODO: Make sure that the columns in the where statement belong to the table that is being updated
            update_stmt += " WHERE"
            update_stmt += f' {self.query_object["where"]["stmt"]}'
            update_values.extend(self.query_object["where"]["values"])

            self.query_stmt = update_stmt
            self.query_values = update_values

        elif self.mode == "DELETE":
            # DELETE FROM table_name WHERE condition;
            delete_stmt = f'DELETE FROM {self.query_object["delete"]["table"]}'
            delete_values = []

            # WHERE ...
            if self.query_object["where"]["stmt"] is not None:
                delete_stmt += " WHERE"
                delete_stmt += f' {self.query_object["where"]["stmt"]}'
                delete_values.extend(self.query_object["where"]["values"])
            elif self.query_object["delete"]["delete_all"] is True:
                print(f'ATTENTION: DELETING TABLE FROM {self.query_object["delete"]["table"]}')
            else:
                raise ValueError("WHERE clause not provided!")

            self.query_stmt = delete_stmt
            self.query_values = delete_values

        else:
            raise ValueError("Nothing to query!")

 
    def get_sql_stmt(self, **kwargs) -> str:
        self.build_query()
        if len(self.query_values) > 0:
            print(self.query_values)
            return f'{self.query_stmt} VALUES ({", ".join(str(x) for x in self.query_values)})'
        else:    
            return f'{self.query_stmt}'
    
    def fetch_one(self) -> dict[any] | None:
        if self.mode != "SELECT":
            raise ValueError("select() query was not called!")
        
        database = self.db
        database.connect()
        self.query_object["limit"] = 1
        self.build_query()
        r = database.get_response(self.query_stmt, tuple(self.query_values), fetch=True, many_entities=False)

        return r
    
    def fetch_many(self, max_rows) -> tuple[dict[any]] | None:
        if self.mode != "SELECT":
            raise ValueError("select() query was not called!")
        
        database = self.db
        database.connect()
        self.query_object["limit"] = max_rows
        self.build_query()
        r = self.db.get_response(self.query_stmt, tuple(self.query_values), fetch=True, many_entities=True)
        # database.close()
        
        return r

    def fetch_all(self) -> tuple[dict[any]] | None:
        if self.mode != "SELECT":
            raise ValueError("select() query was not called!")
        
        database = self.db
        database.connect()
        self.build_query()
        print(self.get_sql_stmt())
        # r = database.get_response(self.query_stmt, tuple(self.query_object["values"]), fetch=True, many_entities=True)
        r = database.get_response(self.query_stmt, tuple(self.query_values), fetch=True, many_entities=True)
        # database.close()

        return r
    
    def commit_changes(self) -> int | None:
        # Confirms changes to the database
        if not (self.mode == "INSERT" or self.mode == "UPDATE" or self.mode == "DELETE"):
            raise ValueError("No changes to commit to the database")
        
        database = self.db
        database.connect()
        self.build_query()
        print(self.get_sql_stmt())
        
        if self.mode == "INSERT":
            self.last_row_id = database.get_response(self.query_stmt, self.query_values, fetch=False, many_entities=False, commit=True)    

        return self.last_row_id

    def get_last_row_id(self) -> int:
        if self.last_row_id is None:
            raise ValueError("Last row id is none!")
        
        return self.last_row_id

    def reset_query(self):
        self.mode = None

        self.query_object = {
                "select": [],
                "from": None,
                "join": [],
                "where": {
                    "stmt": None,
                    "values": []
                },
                "limit": None,
                "order_by": {
                    "column": None,
                    "asc": False
                },
                "offset": None,
                "insert": {
                    "table": None,
                    "stmt": None,
                    "no_values_provided": True,
                    "values": {}
                },
                "update": {
                    "table": None,
                    "stmt": None,
                    "no_values_provided": True,
                    "values": {}
                },
                "delete": {
                    "table": None,
                    "delete_all": False,
                    "stmt": None,
                }
            } 
        self.query_stmt = None
        self.query_values = []
        self.last_row_id = None


        



