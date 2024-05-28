# Discord API has a limited number of requests (bot requests) per day.
# If developers meet that quota, then Discord will put a temporal ban to your bot (24 hours)
# In order to avoid that, and only for testing, create unit test methods to test your functions
# without using the functionality provided by your bot. Once all your tests passed, then you can
# integrate these functions with your bot logic in main.py

from config import config

import pymysql.cursors
import models
from database import Database
from orm import Query

# Connect to the database
# connection = pymysql.connect(host=config["DB_HOST"],
#                              user=config["DB_USERNAME"],
#                              password=config["DB_PASSWORD"],
#                              database=config["DB_NAME"],
#                              charset='utf8mb4',
#                              cursorclass=pymysql.cursors.Cursor)

# with connection:
#     with connection.cursor() as cursor:
#         # Read a single record
#         sql = "SELECT * FROM User JOIN Account ON User.id=Account.user_id;"
#         cursor.execute(sql)
#         result = cursor.fetchone()
#         print(result)

# account = models.Account.select()
# q.select(models.Role.name, models.Role.description).from_table(models.Role).where([models.Role.id, "=", 2])
def query_1():
    q = Query(Database)
    q.select(models.Role)
    sql = q.get_sql_stmt()
    return sql
    # q.fetchAll()
    # return q

def query_2():
    q = Query(Database)
    q.select(models.Role.name, models.Role.description)
    sql = q.get_sql_stmt()
    return sql

def query_3():
    q = Query(Database)
    try:
        q.select()
        return False
    except ValueError:
        return True
    
def query_4():
    q = Query(Database)
    try:
        q.get_sql_stmt()
        return False
    except ValueError:
        return True
    
def query_5():
    q = Query(Database)
    q.select(models.Role.name, models.Role.description).where(models.Role.id, "=", 1)
    sql = q.get_sql_stmt()
    return sql            

def query_6():
    q = Query(Database)
    q.select(models.Movie, models.Language).join(models.Language, join_type="LEFT", on=(models.Movie.language_id, "=", models.Language.id))
    sql = q.get_sql_stmt()
    return sql  

def query_7():
    q = Query(Database)
    q.select(models.Movie, models.Language).join(models.Language, join_type="LEFT", on=(models.Movie.language_id, "=", models.Language.id)).where(models.Movie.id, "=", 3)
    sql = q.get_sql_stmt()
    return sql  

def query_8():
    q = Query(Database)
    q.select(models.Movie, models.Language, models.FilmRating) \
        .join(models.Language, join_type="LEFT", on=(models.Movie.language_id, "=", models.Language.id)) \
        .join(models.FilmRating, join_type="LEFT", on=(models.Movie.film_rating, "=", models.FilmRating.id)) \
        .where(models.Movie.id, "=", 3)
    sql = q.get_sql_stmt()
    return sql  

def query_9():
    q = Query(Database)
    q.insert(models.Role).values({"name": "Useful employee", "description": "I do useful stuff"})
    sql = q.get_sql_stmt()
    return sql
    
def query_10():
    q = Query(Database)
    q.update(models.Role).values({"name": "Even More Useful Employee", "description": "I do even more useful stuff"}).where(models.Role.id, "=", 3)
    sql = q.get_sql_stmt()
    return sql

def query_11():
    # Test if delete works
    q = Query(Database)
    q.delete(models.Role).where(models.Role.id, "=", 5)
    sql = q.get_sql_stmt()
    return sql

def query_12():
    # Test if it'll throw an error if you omit where() while delete_all is false
    q = Query(Database)
    try:
        q.delete(models.Role)
        sql = q.get_sql_stmt()
        print(sql)
        return False
    except ValueError:
        return True

def query_13():
    # Test if you can delete all records
    q = Query(Database)
    q.delete(models.Role, delete_all=True)
    sql = q.get_sql_stmt()
    return sql

def query_14():
    q = Query(db=Database())
    q.select(models.UserStatus.id).where(models.UserStatus.name, "=", "Active")
    user_status = q.fetch_one()
    print(user_status)

    q.reset_query()
    # new_user = models.User(account_id=None, status=user_status["id"])
    # q.insert(new_user)
    q.insert(models.User).values({"account_id": None, "status": user_status["id"]})
    new_user_id = q.commit_changes()
    print(new_user_id)

def test_sql(name, func, expected_string):
    result = func()
    if result != expected_string:
        print(f'{name} did not pass!\nExpected: {expected_string}\nResult  : {result}')
        # print_differences(str(result), str(expected_string))
    else:
        print(f'{name} passed!\nExpected: {expected_string}\nResult  : {result}')
    print("----------------------------")

def print_differences(str1, str2):
    pos1 = 0
    pos2 = 0
    out1 = "\""
    out2 = "\""
    while pos1 < len(str1) and pos2 < len(str2):
        if str1[pos1] == str2[pos2]:
            out1 += "*"
            out2 += "*"
        else:
            out1 += str1[pos1]
            out2 += str2[pos2]
        
        pos1 += 1
        pos2 += 1
    while pos1 < len(str1):
        out1 += str1[pos1]
    while pos2 < len(str2):
        out2 += str2[pos2]

    out1 += "\""
    out2 += "\""

    # print(out1, out2, sep="\n")
    print(f"Expected:{out1}\nResult :", out2)

def now_showing():
    # Get all of the movies that are going to be showing. Separated by date
    # Make sure to handle the language dub correctly too.
    # Ex: Ghibli 2024 (Japanese Dub)
    #     Ghibli 2024 (English Dub)

    """
    Output should look like the following
    Date: May 22, 2024
        Spirited Away (Japanese Dub)
            [10:00 am (id: 31)] [12:00 pm (id: 32)] [3:00 pm (id: 33)]
        Dune: Part 2
            [3:00 pm (id: 34)] [...]
        ...
    ...
    Enter the id of the movie and time you want to watch by typing:
    !reserve_seat [id]
    """
    q = Query(db=Database())
    q.select(models.Showing, models.Movie.title, models.Language.language_name) \
        .join(models.Movie, join_type="LEFT", on=(models.Showing.movie_id, "=", models.Movie.id)) \
        .join(models.Language, join_type="LEFT", on=(models.Showing.dubbing_id, "=", models.Language.id)) \
        .where(models.Showing.status, "=", "NOW SHOWING") \
        .order_by(models.Showing.start_time, asc=True)
    all_showings = q.fetch_all()

    output_string = formatting.output_now_showing(all_showings)
    print(output_string)

def get_showing_info(showing_id):
    q = Query(db=Database())
    q.select(models.Ticket, models.Movie.title, models.Seat, models.SeatType.name) \
        .join(models.Movie, join_type="LEFT", on=(models.Ticket.movie_id, "=", models.Movie.id)) \
        .join(models.Seat, join_type="LEFT", on=(models.Ticket.seat_id, "=", models.Seat.id)) \
        .join(models.SeatType, join_type="LEFT", on=(models.Seat.seat_type_id, "=", models.SeatType.id)) \
        .where(models.Ticket.showing_time_id, "=", showing_id) \
        .order_by(models.Seat.row_letter, asc=True)
    available_tickets = q.fetch_all()

#     You selected Spirited Away (Japanese Dub) at 4:00 pm
# Dubbing: Japanese, Subtitles: English, Film-Rating: PG-13
    showing_details = formatting.get_showing_details(showing_id)
    output_string = f'You selected **{showing_details["movie_title"]}**'
    if showing_details["dubbing_language"] is not None:
        output_string += f' ({showing_details["dubbing_language"]} Dub)'
    output_string += f' at **{showing_details["start_time"].strftime("%-I:%M %p")}**\n' 
    output_string += f'Dubbing: {showing_details["dubbing_language"]}, Subtitles: {showing_details["subtitle_language"]}, Age Rating: {showing_details["age_rating"]}, Sound System: {showing_details["sound_system"]}\n'
    output_string += f'Location: **Movie Theatre {showing_details["theatre_room_name"]}**\n'

    output_string += formatting.output_seating(available_tickets)
    output_string += "\nSelect a seat by typing in the id of the movie as well as the seat's row letter and column number\n"
    output_string += "`!confirm_seat [showing_id] [A4] [A5] ...`\n"
    output_string += "Example:\n"
    output_string += "`!confirm_seat 31 A4 A5` Reserves seats A4 and A5 for the movie Spirited Away at 4:00 pm\n"

    print(output_string)

def reserve_seat(showing_id, seat_id):
    # This will update the ticket and the seat
    pass

def get_movie_info(showing_time_id):
    output = formatting.get_showing_details(showing_time_id)
    print(output)

if __name__ == "__main__":
    test_sql("Test 1", query_1, "SELECT Role.* FROM Role")
    test_sql("Test 2", query_2, "SELECT Role.name, Role.description FROM Role")
    test_sql("Test 3", query_3, True)
    test_sql("Test 4", query_4, True)
    test_sql("Test 5", query_5, "SELECT Role.name, Role.description FROM Role WHERE Role.id = %s VALUES (1)")
    test_sql("Test 6", query_6, "SELECT Movie.*, Language.* FROM Movie LEFT JOIN Language ON Movie.language_id = Language.id")
    test_sql("Test 7", query_7, "SELECT Movie.*, Language.* FROM Movie LEFT JOIN Language ON Movie.language_id = Language.id WHERE Movie.id = %s VALUES (3)")
    test_sql("Test 8", query_8, "SELECT Movie.*, Language.*, FilmRating.* FROM Movie LEFT JOIN Language ON Movie.language_id = Language.id LEFT JOIN FilmRating ON Movie.film_rating = FilmRating.id WHERE Movie.id = %s VALUES (3)")
    test_sql("Test 9", query_9, "INSERT INTO Role (Role.name, Role.description) VALUES (%s, %s) VALUES (Useful employee, I do useful stuff)")
    test_sql("Test_10", query_10, "UPDATE Role SET Role.name = %s, Role.description = %s WHERE Role.id = %s VALUES (Even More Useful Employee, I do even more useful stuff, 3)")
    test_sql("Test 11", query_11, "DELETE FROM Role WHERE Role.id = %s VALUES (5)")
    test_sql("Test 11", query_12, True)
    test_sql("Test 11", query_13, "DELETE FROM Role")
    test_sql("Test 14", query_14, "Inserting a new user")
    test("Get Now Showing", now_showing, True)
    test("Get Showing Info", lambda: get_showing_info(10), True)
    test("Get Showing Info", lambda: get_movie_info(10), True)