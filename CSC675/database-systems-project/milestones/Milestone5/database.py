# This is the new database with a query builder
import os
import pymysql.cursors
from config import config
from typing import List, Any

class Database:

    # This method was already implemented for you
    def connect(self):
        """
        This method creates a connection with your database
        IMPORTANT: all the environment variables must be set correctly
                   before attempting to run this method. Otherwise, it
                   will throw an error message stating that the attempt
                   to connect to your database failed.
        """
        try:
            conn = pymysql.connect(host=config.db_host,
                             user=config.db_username,
                             password=config.db_password,
                             database=config.db_name,
                             charset='utf8mb4',
                             cursorclass=pymysql.cursors.DictCursor)
            print("Bot connected to database {}".format(config.db_name))
            return conn
        except ConnectionError as err:
            print(f"An error has occurred: {err.args[1]}")
            print("\n")

    #TODO: needs to implement the internal logic of all the main query operations
    def get_response(self, query: str, values: tuple[Any, ...] = None, fetch=False, many_entities=False, commit=False):
        """
        query: the SQL query with wildcards (if applicable) to avoid injection attacks
        values: the values passed in the query
        fetch: If set to True, then the method fetches data from the database (i.e with SELECT)
        many_entities: If set to True, the method can insert multiple entities at a time.
        """
        connection = self.connect()
        response = None

        # Pymysql provides a way of closing the connection automatically using python's "with"
        # I looked into the library and they have __del__ built-in method make a call to cursor.close()
        with connection:
            with connection.cursor() as cursor:
                cursor.execute(query, values)
                
                if fetch:
                    if many_entities:
                        response = cursor.fetchall()
                    else:
                        response = cursor.fetchone()
                elif commit:
                    response = cursor.lastrowid
                    connection.commit()
                
        return response

