"""
In this file you must implement all your database models.
If you need to use the methods from your database.py call them
statically. For instance:
       # opens a new connection to your database
       connection = Database.connect()
       # closes the previous connection to avoid memory leaks
       connection.close()
"""

from orm import BaseModel, Column

class Account(BaseModel):
    table_name = "Account"

    id = Column(table="Account", name="id", is_primary_key=True)
    role = Column(table="Account", name="role")
    password = Column(table="Account", name="password")
    email = Column(table="Account", name="email")
    phone_number = Column(table="Account", name="phone_number")
    user_id = Column(table="Account", name="user_id")
    first_name = Column(table="Account", name="first_name")
    last_name = Column(table="Account", name="last_name")
    username = Column(table="Account", name="username")

class Admin(BaseModel):
    table_name = "Admin"

    id = Column(table="Admin", name="id", is_primary_key=True)
    user_id = Column(table="Admin", name="user_id")
    account_id = Column(table="Admin", name="account_id")
    employee_id = Column(table="Admin", name="employee_id")
    pay_rate = Column(table="Admin", name="pay_rate")
    permissions = Column(table="Admin", name="permissions")
    creation_date = Column(table="Admin", name="creation_date")


class Dubbing(BaseModel):
    table_name = "Dubbing"

    id = Column(table="Dubbing", name="id", is_primary_key=True)
    language_id = Column(table="Dubbing", name="language_id")
    description = Column(table="Dubbing", name="description")


class Employee(BaseModel):
    table_name = "Employee"

    id = Column(table="Employee", name="id", is_primary_key=True)
    user_id = Column(table="Employee", name="user_id")
    account_id = Column(table="Employee", name="account_id")
    pay_rate = Column(table="Employee", name="pay_rate")
    permissions = Column(table="Employee", name="permissions")
    creation_date = Column(table="Employee", name="creation_date")


class Feature(BaseModel):
    table_name = "Feature"

    id = Column(table="Feature", name="id", is_primary_key=True)
    name = Column(table="Feature", name="name")
    description = Column(table="Feature", name="description")


class Guest(BaseModel):
    table_name = "Guest"

    id = Column(table="Guest", name="id", is_primary_key=True)
    user_id = Column(table="Guest", name="user_id")
    transaction_date = Column(table="Guest", name="transaction_date")

class Member(BaseModel):
    table_name = "Member"

    id = Column(table="Member", name="id", is_primary_key=True)
    user_id = Column(table="Member", name="user_id")
    account_id = Column(table="Member", name="account_id")
    points = Column(table="Member", name="points")
    discount = Column(table="Member", name="discount")
    creation_date = Column(table="Member", name="creation_date")

class PaymentInformation(BaseModel):
    table_name = "PaymentInformation"

    id = Column(table="PaymentInformation", name="id", is_primary_key=True)
    user_id = Column(table="PaymentInformation", name="user_id")
    creation_date = Column(table="PaymentInformation", name="creation_date")
    address = Column(table="PaymentInformation", name="address")
    city = Column(table="PaymentInformation", name="city")
    state = Column(table="PaymentInformation", name="state")
    zipcode = Column(table="PaymentInformation", name="zip")
    cvv = Column(table="PaymentInformation", name="cvv")
    card_number = Column(table="PaymentInformation", name="card_number")
    expiry = Column(table="PaymentInformation", name="expiry")
    first_name = Column(table="PaymentInformation", name="first_name")
    last_name = Column(table="PaymentInformation", name="last_name")


class Resolution(BaseModel):
    table_name = "Resolution"

    id = Column(table="Resolution", name="id", is_primary_key=True)
    name = Column(table="Resolution", name="name")
    description = Column(table="Resolution", name="description")


class Sale(BaseModel):
    table_name = "Sale"

    id = Column(table="Sale", name="id", is_primary_key=True)
    discount = Column(table="Sale", name="discount")
    name = Column(table="Sale", name="name")
    description = Column(table="Sale", name="description")


class Seat(BaseModel):
    table_name = "Seat"

    id = Column(table="Seat", name="id", is_primary_key=True)
    theatre_id = Column(table="Seat", name="theatre_id")
    is_occupied = Column(table="Seat", name="is_occupied")
    seat_type_id = Column(table="Seat", name="seat_type_id")
    row_letter = Column(table="Seat", name="row_letter")
    column_number = Column(table="Seat", name="column_number")


class SeatType(BaseModel):
    table_name = "SeatType"

    id = Column(table="SeatType", name="id", is_primary_key=True)
    name = Column(table="SeatType", name="name")
    description = Column(table="SeatType", name="description")


class ShowingFeature(BaseModel):
    table_name = "ShowingFeature"

    showing_id = Column(table="ShowingFeature", name="showing_id")
    feature_id = Column(table="ShowingFeature", name="feature_id")


class SoundSystem(BaseModel):
    table_name = "SoundSystem"

    id = Column(table="SoundSystem", name="id", is_primary_key=True)
    name = Column(table="SoundSystem", name="name")
    description = Column(table="SoundSystem", name="description")


class Subtitle(BaseModel):
    table_name = "Subtitle"

    id = Column(table="Subtitle", name="id", is_primary_key=True)
    language_id = Column(table="Subtitle", name="language_id")
    description = Column(table="Subtitle", name="description")


class TheatreRoom(BaseModel):
    table_name = "TheatreRoom"

    id = Column(table="TheatreRoom", name="id", is_primary_key=True)
    name = Column(table="TheatreRoom", name="name")
    num_seats = Column(table="TheatreRoom", name="num_seats")
    description = Column(table="TheatreRoom", name="description")


class TicketStatus(BaseModel):
    table_name = "TicketStatus"

    id = Column(table="TicketStatus", name="id", is_primary_key=True)
    name = Column(table="TicketStatus", name="name")
    description = Column(table="TicketStatus", name="description")


class TicketType(BaseModel):
    table_name = "TicketType"

    id = Column(table="TicketType", name="id", is_primary_key=True)
    name = Column(table="TicketType", name="name")
    description = Column(table="TicketType", name="description")
    suggested_price = Column(table="TicketType", name="suggested_price")


class User(BaseModel):
    table_name = "User"

    id = Column(table="User", name="id", is_primary_key=True)
    account_id = Column(table="User", name="account_id")
    creation_date = Column(table="User", name="creation_date")
    status = Column(table="User", name="status")


class UserStatus(BaseModel):
    table_name = "UserStatus"

    id = Column(table="UserStatus", name="id", is_primary_key=True)
    name = Column(table="UserStatus", name="name")
    description = Column(table="UserStatus", name="description")



class Role(BaseModel):
    table_name = "Role"

    id = Column(table=table_name, name="id", data_type="number", is_primary_key=True)
    name = Column(table=table_name, name="name", data_type="string")
    description = Column(table=table_name, name="description", data_type="string")

class Ticket(BaseModel):
    table_name = "Ticket"

    id = Column(table=table_name, name="id", data_type="number", is_primary_key=True)
    movie_id = Column(table=table_name, name="movie_id", data_type="number")
    showing_time_id = Column(table=table_name, name="showing_time_id", data_type="number")
    price = Column(table=table_name, name="price", data_type="number")
    seat_id = Column(table=table_name, name="seat_id", data_type="number")
    theatre_id = Column(table=table_name, name="theatre_id", data_type="number")
    status = Column(table=table_name, name="status", data_type="number")
    ticket_type = Column(table=table_name, name="ticket_type", data_type="number")
    bought_by = Column(table=table_name, name="bought_by", data_type="number")

class Showing(BaseModel):
    table_name = "Showing"

    id = Column(table=table_name, name="id", data_type="number", is_primary_key=True)
    movie_id = Column(table=table_name, name="movie_id", data_type="number")
    resolution_id = Column(table=table_name, name="resolution_id", data_type="number")
    subtitle_id = Column(table=table_name, name="subtitle_id", data_type="number")
    dubbing_id = Column(table=table_name, name="dubbing_id", data_type="number")
    sound_type_id = Column(table=table_name, name="sound_type_id", data_type="number")
    status = Column(table=table_name, name="status", data_type="string")
    theatre_id = Column(table=table_name, name="theatre_id", data_type="number")
    start_time = Column(table=table_name, name="start_time", data_type="datetime")
    end_time = Column(table=table_name, name="end_time", data_type="datetime")

class Movie(BaseModel):
    table_name = "Movie"

    id = Column(table=table_name, name="id", data_type="number", is_primary_key=True)
    title = Column(table=table_name, name="title", data_type="string")
    language_id = Column(table=table_name, name="language_id", data_type="number")
    description = Column(table=table_name, name="description", data_type="string")
    film_rating = Column(table=table_name, name="film_rating", data_type="number")
    duration = Column(table=table_name, name="duration", data_type="datetime")

class Language(BaseModel):
    table_name = "Language"

    id = Column(table=table_name, name="id", data_type="number", is_primary_key=True)
    language_name = Column(table=table_name, name="language_name", data_type="string")
    description = Column(table=table_name, name="description", data_type="string")

class FilmRating(BaseModel):
    table_name = "FilmRating"

    id = Column(table=table_name, name="id", data_type="number", is_primary_key=True)
    name = Column(table=table_name, name="name", data_type="string")
    description = Column(table=table_name, name="description", data_type="string")


