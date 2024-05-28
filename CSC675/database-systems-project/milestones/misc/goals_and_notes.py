"""
for models we want something complicated using metaclasses


class Role(BaseModel):
    table_name = "Role"
    id = Column(table=table_name, name="id", data_type="number", is_primary_key=True)
    name = Column(table=table_name, name="name", data_type="string")
    description = Column(table=table_name, name="description", data_type="string")

We can do CRUD operations with the Role
q = Query()
q.select(Role.name, role.description).where(Role.id == 1).fetchOne()

We can also insert a Role
new_role = models.Role(name="clerk", description="I manage ticket purchases at the front desk")
q = Query()
q.insert(new_role)

Or delete a Role
q = Query()
to_delete = q.select(Role.name, role.description).where(Role.id == 1).fetchOne()
q.delete(to_delete)

However, this might seem complicated and we might need to use metaclasses. 
I propose a different solution


C: Insert a role
q = Query()
q.insert(models.Role).values({"name": "clerk", "description": "I manage ticket purchases at the front desk"})
q.commit()

R: Read a role
q = Query()
q.select(Role.name, role.description).from(Role).where([Role.id, "==", "1"]).fetchOne()

q = Query()
q.select(models.Language.language_name, models.Movie)
    .from(models.Language)
    .join(models.Movie)
    .on([Language.id, "==", Movie.language_id])
    .where([Language.id, "==", 5])

U: Update a role
q = Query()
q.update(models.Role).values({"name": "ticket seller"}).where([Role.name, "==", "clerk"])
q.commit()

D: Delete a role
q = Query()
q.delete(models.Role, all=False).where([models.Role.id, "==", "3"])
q.commit()

Notice how where is not used in an insert statement

When a query like this is made:
q = Query()
q.select(models.Language.language_name, models.Movie)
    .from(models.Language)
    .join(models.Movie)
    .on([Language.id, "==", Movie.language_id])
    .where([Language.id, "==", 5])

The Lexical Parser object should look like
self.query_object = {
    "select": ["Language.Language_name", "Movie.*"],
    "from": "Language",
    "join": [
        {
            "table": "Movie",
            "on": "Language.id == Movie.language_id",
            "values": []
        },
        # If it needed a value, it would look like this
        {
            "table": "Movie",
            "on": "Language.id == %s",
            "values": [5]
        }
    ],
    "where": {
        "stmt": "Language.id = %s"
        "values": [5]
    }
}

We can try eliminating the from, join, and on methods by anticipating what the user wants to select
q.select(models.Language.language_name, models.Movie)
The from will be Language, and it will create a join with Movie, the on just needs to be set
self.query_object = {
    "select": ["Language.Language_name", "Movie.*"],
    "from": "Language",
    "join": [
        {
            "table": "Movie",
            "on": None,
            "values": []
        }
    ],
    "where": None
}
We can call .on() to set the join conditions
q.select(models.Language.language_name, models.Movie).on(Language.id, "=", Movie.language_id)

STEP 1 select(models.Language.language_name, models.Movie, models.Example)
self.query_object = {
    "select": ["Language.Language_name", "Movie.*", "Example.*"],
    "from": "Language",
    "join": [
        {
            "table": "Movie",
            "on": None,
            "values": []
        },
        {
            "table": "Example",
            "on": None,
            "values": [5]
        }
    ],
    "where": {
        "stmt": "Language.id = %s"
        "values": [5]
    }
}

STEP 2 join_on(Language.id, "=", Movie.language_id)
if arg's parent table is the from, add it,
if arg is an operator add it
if arg's parent table is not the from, search through the joins
    # What if the arg is on the left? what about the right?
self.query_object = {
    "select": ["Language.Language_name", "Movie.*", "Example.*"],
    "from": "Language",
    "join": [
        {
            "table": "Movie",
            "on": "Language.id == Movie.language_id",
            "values": []
        },
        {
            "table": "Example",
            "on": "Example.id == Language.id",
            "values": [5]
        }
    ],
    "where": {
        "stmt": "Language.id = %s"
        "values": [5]
    }
}

Nvm, it turns out we might need join after all
response = q.select(models.Language.language_name, models.Movie, models.Example)
            .join(models.Movie, type="LEFT", on=(Language.id, "=", Movie.language_id))
            .join(models.Example, type="INNER", on=(Example.id, "=", Language.id))
            .where(Langage.id, "=", 5)

# https://levelup.gitconnected.com/how-i-built-a-simple-orm-from-scratch-in-python-18b50108cfa3





INSERT INTO [Table] or [List of table Columns] VALUES (value1, value2, ...)

UPDATE table_name
SET column1 = value1, column2 = value2, ...
WHERE condition; 

DELETE FROM TABLENAME WHERE CONDITION

INSERT USES values()
Update uses where()
Delete uses where()

orm methods should look like the following

q = Query(Database)
q.insert(models.Role).values({"name": "clerk", "description": "I handle purchasing of tickets"})
q.update(models.Role).values({"description": "I sit at the front in the ticket booth"}).where(models.Role, "=", 3)

q.delete(models.Role).where(models.Role.id, "=", 3) <- to delete records matching the condition
q.delete(models.Role, delete_all=True) <- to delete all records from the table

old valeu: Handles the purchases of tickets

When the user wants to look at all the showings:
    Date: May 22, 2024
        Spirited Away (Japanese Dub)
            [10:00 am (id: 31)] [12:00 pm (id: 32)] [3:00 pm (id: 33)]
        Dune: Part 2
            [3:00 pm (id: 34)] [...]
        ...
    ...
    Enter the id of the movie and time you want to watch by typing:
    !reserve_seat [id]
    Example:
    !reserve_seat 31

When the user wants to buy the seating for the movie:
You selected Spirited Away (Japanese Dub) at 4:00 pm
Dubbing: Japanese, Subtitles: English, Film-Rating: PG-13

                THEATRE SCREEN
    ------------------------------------------

        E    L  L  L  L  L  L  L  L  L  L  L
        D    L  L  L  L  X  L  L  L  X  L  L
        C    L  L  X  X  L  L  L  X  L  L  L
        B    L  L  L  L  L  L  L  L  X  L  L
        A    L  L  L  L  L  L  L  L  L  L  L

             1  2  3  4  5  6  7  8  9  10 11

    Legend: _ = taken, L = Luxury, etc...

    Select a seat by typing in the id of the movie as well as the seat row and column number
    !confirm_seat [showing_id] [A4] [A5] ...
    Example:
    !confirm_seat 31 A4 A5 Reserves seats A4 and A5 for the movie Spirited Away at 4:00 pm

After the user selects a seat:
                    THEATRE SCREEN
    ------------------------------------------

        E    L  L  L  L  L  L  L  L  L  L  L
        D    L  L  L  L  X  L  L  L  X  L  L
        C    L  L  X  X  L  L  L  X  L  L  L
        B    L  L  L  L  L  L  L  L  X  L  L
        A    L  L  L  O  O  L  L  L  L  L  L

             1  2  3  4  5  6  7  8  9  10 11
    
    Seating Confirmed: A4, A5 (2 Luxury seats)
    Theatre: 1B - Luxury Theatre
    Cost: $30.00 
    Discount: None

    Enter your payment information below to buy the tickets (please do not enter your actual credentials)
    !buy_ticket [showing_id] [A4] [A5] ... [credit_card_number] [first_name] [last_name] [expiry] [cvv]

    Example:
    !buy_ticket 31 A4 A5 1234567812345678 John Doe 01/13 441 

After user buys the ticket:
    Ticket confirmed for Spirited Away (Japanese Dub) at 4:00pm
    Here's your ticket, please scan the bar code before entering the movie theatre

    --------------------------
    |    Spirited Away       |
    |    (Japanese Dub)      |
    |       4:00pm           |
    |     Theatre 1B         |
    |       Seat A4          |
    |   Scan this for entry: |
    |      ||||||||||        |
    -------------------------

    --------------------------
    |    Spirited Away       |
    |    (Japanese Dub)      |
    |       4:00pm           |
    |     Theatre 1B         |
    |       Seat A5          |
    |   Scan this for entry: |
    |      ||||||||||        |
    --------------------------

    Data we need to verify: Showing id, seat numbers
    Data we need: Movie title, showing time, theatre name, seat numbers

!refund_ticket
!use_ticket
!apply_discount

!register
!login

Here is a list of movies that are currently showing:
**Movies showing on May 23, 2024: **
> [*12:30 PM*] **Amélie (French Dub)** <- ID: 18
> [*3:30 PM*] **Spirited Away - Ghibli 2024 (English Dub)** <- ID: 19
> [*6:30 PM*] **Spirited Away - Ghibli 2024 (English Dub)** <- ID: 20

Here is a list of movies that are currently showing:
## **Movies showing on May 20, 2024: **
> [*10:30 AM*] **Everything Everywhere All at Once** <- `id: 4`
> [*1:30 PM*] **Alien** <- `id: 3`
> [*2:30 PM*] **Dune: Part Two** <- `id: 2`
> [*7:00 PM*] **Encanto** <- `id: 5`
> [*7:00 PM*] **Amélie (French Dub)** <- `id: 1`
> [*10:30 PM*] **White Chicks** <- `id: 6`

## **Movies showing on May 21, 2024: **
> [*11:30 AM*] **Spirited Away - Ghibli 2024 (English Dub)** <- `id: 10`
> [*2:30 PM*] **White Chicks** <- `id: 11`
> [*4:30 PM*] **Encanto** <- `id: 8`
> [*6:30 PM*] **Everything Everywhere All at Once** <- `id: 12`
> [*7:30 PM*] **Dune: Part Two** <- `id: 9`
> [*8:00 PM*] **Alien** <- `id: 7`

Enter the id of the movie and time you want to watch by typing: `!reserve_seat [id]`
An Example: `!reserve_seat 31`

To get the user that sent the message, just do ctx.author

From the showing_id, you get the all the tickets, from all the tickets, you get the seats and theatre_id

[{'id': 1185, 'movie_id': 4, 'showing_time_id': 10, 'price': 1499, 'seat_id': 45, 'theatre_id': 2, 'status': 1, 'ticket_type': 1, 'bought_by': None, 'Seat.id': 45, 'Seat.theatre_id': 2, 'is_occupied': 0, 'seat_type_id': 2, 'row_letter': 'F', 'column_number': 4}, {'id': 1184, 'movie_id': 4, 'showing_time_id': 10, 'price': 1499, 'seat_id': 44, 'theatre_id': 2, 'status': 1, 'ticket_type': 1, 'bought_by': None, 'Seat.id': 44, 'Seat.theatre_id': 2, 'is_occupied': 0, 'seat_type_id': 2, 'row_letter': 'F', 'column_number': 3}, {'id': 1183, 'movie_id': 4, 'showing_time_id': 10, 'price': 1499, 'seat_id': 43, 'theatre_id': 2, 'status': 1, 'ticket_type': 1, 'bought_by': None, 'Seat.id': 43, 'Seat.theatre_id': 2, 'is_occupied': 0, 'seat_type_id': 2, 'row_letter': 'F', 'column_number': 2}, {'id': 1182, 'movie_id': 4, 'showing_time_id': 10, 'price': 1499, 'seat_id': 42, 'theatre_id': 2, 'status': 1, 'ticket_type': 1, 'bought_by': None, 'Seat.id': 42, 'Seat.theatre_id': 2, 'is_occupied': 0, 'seat_type_id': 2, 'row_letter': 'F', 'column_number': 1}, {'id': 1181, 'movie_id': 4, 'showing_time_id': 10, 'price': 1499, 'seat_id': 40, 'theatre_id': 2, 'status': 1, 'ticket_type': 1, 'bought_by': None, 'Seat.id': 40, 'Seat.theatre_id': 2, 'is_occupied': 0, 'seat_type_id': 2, 'row_letter': 'E', 'column_number': 4}, {'id': 1180, 'movie_id': 4, 'showing_time_id': 10, 'price': 1499, 'seat_id': 39, 'theatre_id': 2, 'status': 1, 'ticket_type': 1, 'bought_by': None, 'Seat.id': 39, 'Seat.theatre_id': 2, 'is_occupied': 0, 'seat_type_id': 2, 'row_letter': 'E', 'column_number': 3}, {'id': 1179, 'movie_id': 4, 'showing_time_id': 10, 'price': 1499, 'seat_id': 38, 'theatre_id': 2, 'status': 1, 'ticket_type': 1, 'bought_by': None, 'Seat.id': 38, 'Seat.theatre_id': 2, 'is_occupied': 0, 'seat_type_id': 2, 'row_letter': 'E', 'column_number': 2}, {'id': 1178, 'movie_id': 4, 'showing_time_id': 10, 'price': 1499, 'seat_id': 37, 'theatre_id': 2, 'status': 1, 'ticket_type': 1, 'bought_by': None, 'Seat.id': 37, 'Seat.theatre_id': 2, 'is_occupied': 0, 'seat_type_id': 2, 'row_letter': 'E', 'column_number': 1}, {'id': 1174, 'movie_id': 4, 'showing_time_id': 10, 'price': 1499, 'seat_id': 32, 'theatre_id': 2, 'status': 1, 'ticket_type': 1, 'bought_by': None, 'Seat.id': 32, 'Seat.theatre_id': 2, 'is_occupied': 0, 'seat_type_id': 2, 'row_letter': 'D', 'column_number': 1}, {'id': 1177, 'movie_id': 4, 'showing_time_id': 10, 'price': 1499, 'seat_id': 35, 'theatre_id': 2, 'status': 1, 'ticket_type': 1, 'bought_by': None, 'Seat.id': 35, 'Seat.theatre_id': 2, 'is_occupied': 0, 'seat_type_id': 2, 'row_letter': 'D', 'column_number': 4}, {'id': 1176, 'movie_id': 4, 'showing_time_id': 10, 'price': 1499, 'seat_id': 34, 'theatre_id': 2, 'status': 1, 'ticket_type': 1, 'bought_by': None, 'Seat.id': 34, 'Seat.theatre_id': 2, 'is_occupied': 0, 'seat_type_id': 1, 'row_letter': 'D', 'column_number': 3}, {'id': 1175, 'movie_id': 4, 'showing_time_id': 10, 'price': 1499, 'seat_id': 33, 'theatre_id': 2, 'status': 1, 'ticket_type': 1, 'bought_by': None, 'Seat.id': 33, 'Seat.theatre_id': 2, 'is_occupied': 0, 'seat_type_id': 1, 'row_letter': 'D', 'column_number': 2}, {'id': 1173, 'movie_id': 4, 'showing_time_id': 10, 'price': 1499, 'seat_id': 30, 'theatre_id': 2, 'status': 1, 'ticket_type': 1, 'bought_by': None, 'Seat.id': 30, 'Seat.theatre_id': 2, 'is_occupied': 0, 'seat_type_id': 2, 'row_letter': 'C', 'column_number': 4}, {'id': 1172, 'movie_id': 4, 'showing_time_id': 10, 'price': 1499, 'seat_id': 29, 'theatre_id': 2, 'status': 1, 'ticket_type': 1, 'bought_by': None, 'Seat.id': 29, 'Seat.theatre_id': 2, 'is_occupied': 0, 'seat_type_id': 2, 'row_letter': 'C', 'column_number': 3}, {'id': 1171, 'movie_id': 4, 'showing_time_id': 10, 'price': 1499, 'seat_id': 28, 'theatre_id': 2, 'status': 1, 'ticket_type': 1, 'bought_by': None, 'Seat.id': 28, 'Seat.theatre_id': 2, 'is_occupied': 0, 'seat_type_id': 2, 'row_letter': 'C', 'column_number': 2}, {'id': 1170, 'movie_id': 4, 'showing_time_id': 10, 'price': 1499, 'seat_id': 27, 'theatre_id': 2, 'status': 1, 'ticket_type': 1, 'bought_by': None, 'Seat.id': 27, 'Seat.theatre_id': 2, 'is_occupied': 0, 'seat_type_id': 2, 'row_letter': 'C', 'column_number': 1}, {'id': 1169, 'movie_id': 4, 'showing_time_id': 10, 'price': 1499, 'seat_id': 25, 'theatre_id': 2, 'status': 1, 'ticket_type': 1, 'bought_by': None, 'Seat.id': 25, 'Seat.theatre_id': 2, 'is_occupied': 0, 'seat_type_id': 2, 'row_letter': 'B', 'column_number': 4}, {'id': 1168, 'movie_id': 4, 'showing_time_id': 10, 'price': 1499, 'seat_id': 24, 'theatre_id': 2, 'status': 1, 'ticket_type': 1, 'bought_by': None, 'Seat.id': 24, 'Seat.theatre_id': 2, 'is_occupied': 0, 'seat_type_id': 2, 'row_letter': 'B', 'column_number': 3}, {'id': 1167, 'movie_id': 4, 'showing_time_id': 10, 'price': 1499, 'seat_id': 23, 'theatre_id': 2, 'status': 1, 'ticket_type': 1, 'bought_by': None, 'Seat.id': 23, 'Seat.theatre_id': 2, 'is_occupied': 0, 'seat_type_id': 2, 'row_letter': 'B', 'column_number': 2}, {'id': 1166, 'movie_id': 4, 'showing_time_id': 10, 'price': 1499, 'seat_id': 22, 'theatre_id': 2, 'status': 1, 'ticket_type': 1, 'bought_by': None, 'Seat.id': 22, 'Seat.theatre_id': 2, 'is_occupied': 0, 'seat_type_id': 2, 'row_letter': 'B', 'column_number': 1}, {'id': 1162, 'movie_id': 4, 'showing_time_id': 10, 'price': 1499, 'seat_id': 17, 'theatre_id': 2, 'status': 1, 'ticket_type': 1, 'bought_by': None, 'Seat.id': 17, 'Seat.theatre_id': 2, 'is_occupied': 0, 'seat_type_id': 2, 'row_letter': 'A', 'column_number': 1}, {'id': 1165, 'movie_id': 4, 'showing_time_id': 10, 'price': 1499, 'seat_id': 20, 'theatre_id': 2, 'status': 1, 'ticket_type': 1, 'bought_by': None, 'Seat.id': 20, 'Seat.theatre_id': 2, 'is_occupied': 0, 'seat_type_id': 2, 'row_letter': 'A', 'column_number': 4}, {'id': 1164, 'movie_id': 4, 'showing_time_id': 10, 'price': 1499, 'seat_id': 19, 'theatre_id': 2, 'status': 1, 'ticket_type': 1, 'bought_by': None, 'Seat.id': 19, 'Seat.theatre_id': 2, 'is_occupied': 0, 'seat_type_id': 2, 'row_letter': 'A', 'column_number': 3}, {'id': 1163, 'movie_id': 4, 'showing_time_id': 10, 'price': 1499, 'seat_id': 18, 'theatre_id': 2, 'status': 1, 'ticket_type': 1, 'bought_by': None, 'Seat.id': 18, 'Seat.theatre_id': 2, 'is_occupied': 0, 'seat_type_id': 2, 'row_letter': 'A', 'column_number': 2}]
"""

