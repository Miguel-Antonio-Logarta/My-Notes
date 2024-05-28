"""
The code below is just representative of the implementation of a Bot. 
However, this code was not meant to be compiled as it. It is the responsability 
of all the students to modifify this code such that it can fit the 
requirements for this assignments.

My notes:
To read more on setting up commands: https://discordpy.readthedocs.io/en/stable/ext/commands/commands.html
https://discordpy.readthedocs.io/en/latest/ext/commands/index.html

"""

import discord
# import os
from discord.ext import commands
import models
from config import config
from database import Database
from orm import Query
import formatting
from re import match
import usercontext

#TODO DONE:  add your Discord Token as a value to your secrets on replit using the DISCORD_TOKEN key
# TOKEN = os.environ["DISCORD_TOKEN"]

intents = discord.Intents.all()

bot = commands.Bot(command_prefix='!', intents=discord.Intents.all())
online_users = usercontext.UserContext()

@bot.command(name="now_showing", description="Shows all movies that are now in theaters.")
async def now_showing(ctx):
    q = Query(db=Database())
    q.select(models.Showing, models.Movie.title, models.Language.language_name) \
        .join(models.Movie, join_type="LEFT", on=(models.Showing.movie_id, "=", models.Movie.id)) \
        .join(models.Language, join_type="LEFT", on=(models.Showing.dubbing_id, "=", models.Language.id)) \
        .where(models.Showing.status, "=", "NOW SHOWING") \
        .order_by(models.Showing.start_time, asc=True)
    all_showings = q.fetch_all()

    output_string = formatting.output_now_showing(all_showings)
    await ctx.send(output_string)

@bot.command(name="reserve_seat", description="Shows available seats for a given movie that is showing in theatres")
async def reserve_seat(ctx, showing_time_id):
    q = Query(db=Database())
    q.select(models.Ticket, models.Movie.title, models.Seat, models.SeatType.name) \
        .join(models.Movie, join_type="LEFT", on=(models.Ticket.movie_id, "=", models.Movie.id)) \
        .join(models.Seat, join_type="LEFT", on=(models.Ticket.seat_id, "=", models.Seat.id)) \
        .join(models.SeatType, join_type="LEFT", on=(models.Seat.seat_type_id, "=", models.SeatType.id)) \
        .where(models.Ticket.showing_time_id, "=", showing_time_id) \
        .order_by(models.Seat.row_letter, asc=True)
    available_tickets = q.fetch_all()

    # Get showing details
    showing_details = formatting.get_showing_details(showing_time_id)
    output_string = f'You selected **{showing_details["movie_title"]}**'
    if showing_details["dubbing_language"] is not None:
        output_string += f' ({showing_details["dubbing_language"]} Dub)'
    output_string += f' at **{showing_details["start_time"].strftime("%-I:%M %p")}**\n' 
    output_string += f'Dubbing: {showing_details["dubbing_language"]}, Subtitles: {showing_details["subtitle_language"]}, Age Rating: {showing_details["age_rating"]}, Sound System: {showing_details["sound_system"]}\n'
    output_string += f'Location: **Movie Theatre {showing_details["theatre_room_name"]}**\n'

    # Output seating
    output_string += formatting.output_seating(available_tickets)
    output_string += "\nSelect a seat by typing in the id of the movie as well as the seat's row letter and column number\n"
    output_string += "`!confirm_seat [showing_id] [A4] [A5] ...`\n"
    output_string += "Example:\n"
    output_string += "`!confirm_seat 31 A4 A5` Reserves seats A4 and A5 for the movie Spirited Away at 4:00 pm\n"
    
    await ctx.send(output_string)

# Algorithm
#   Parse seat row and column numbers
#   Query database to see if they exist
#   Output movie information
#   Output seating graph
#   Output selection and totals
#   Output next command
@bot.command(name="confirm_seat", description="confirms the seating for a show")
async def confirm_seat(ctx, showing_time_id, *args):
    # Check if showing has a valid id
    q = Query(db=Database())
    q.select(models.Ticket, models.Movie.title, models.Seat, models.SeatType.name) \
        .join(models.Movie, join_type="LEFT", on=(models.Ticket.movie_id, "=", models.Movie.id)) \
        .join(models.Seat, join_type="LEFT", on=(models.Ticket.seat_id, "=", models.Seat.id)) \
        .join(models.SeatType, join_type="LEFT", on=(models.Seat.seat_type_id, "=", models.SeatType.id)) \
        .where(models.Ticket.showing_time_id, "=", showing_time_id) \
        .order_by(models.Seat.row_letter, asc=True)
    available_tickets = q.fetch_all()

    print("available tickets", available_tickets)
    if len(available_tickets) == 0:
        await ctx.send("Showing time id is not valid!")
        return
    
    summary_data = {
        "seat_names": [],
        "cost": 0,
        "theatre_name": None,
        "theatre_info": None
    }

    # Check if the seats exist
    q.reset_query()
    q.select(models.Showing.id, models.TheatreRoom.name, models.TheatreRoom.description).join(models.TheatreRoom, join_type="LEFT", on=(models.TheatreRoom.id, "=", models.Showing.theatre_id))
    showing_info = q.fetch_one()
    summary_data["theatre_name"] = showing_info["name"]
    summary_data["theatre_info"] = showing_info["description"]

    for seat in args:
        (row_letter, column_number) = formatting.parse_seat(seat)
        q.reset_query()
        q.select(models.Ticket, models.Seat) \
            .join(models.Seat, join_type="LEFT", on=(models.Ticket.seat_id, "=", models.Seat.id)) \
            .where(models.Ticket.showing_time_id, "=", showing_time_id, "AND",
                    models.Seat.row_letter, "=", row_letter, "AND",
                    models.Seat.column_number, "=", column_number)
        ticket_info = q.fetch_one()
        if ticket_info is None:
            await ctx.send("Seat is not valid!")
            return
        
        summary_data["seat_names"].append(seat)
        summary_data["cost"] += ticket_info["price"]

    

    # Get showing details
    showing_details = formatting.get_showing_details(showing_time_id)
    output_string = f'You selected **{showing_details["movie_title"]}**'
    if showing_details["dubbing_language"] is not None:
        output_string += f' ({showing_details["dubbing_language"]} Dub)'
    output_string += f' at **{showing_details["start_time"].strftime("%-I:%M %p")}**\n' 
    output_string += f'Dubbing: {showing_details["dubbing_language"]}, Subtitles: {showing_details["subtitle_language"]}, Age Rating: {showing_details["age_rating"]}, Sound System: {showing_details["sound_system"]}\n'
    output_string += f'Location: **Movie Theatre {showing_details["theatre_room_name"]}**\n'

    output_string += formatting.output_seating(available_tickets, args)

    output_string += f'Seating Confirmed: {", ".join(summary_data["seat_names"])} ({len(summary_data["seat_names"])} seats)\n'
    output_string += f'Theatre: {summary_data["theatre_name"]} - {summary_data["theatre_info"]}\n'
    output_string += f'Cost: {(summary_data["cost"]/100):.2f}\n'
    output_string += f'Discount: None\n'

    output_string += 'Enter your payment information below to buy the tickets (please do not enter your actual credentials)\n'
    output_string += '`!buy_ticket [credit_card_number] [first_name] [last_name] [expiry] [cvv] [showing_id] [seat] [seat] ...`\n'
    output_string += 'Example:\n'
    output_string += '`!buy_ticket 1234567812345678 John Doe 01/13 441 31 A4 A5`\n'

    await ctx.send(output_string)

@bot.command(name="buy_ticket", description="Buys tickets for a showing")
async def buy_ticket(ctx, cc_number, first_name, last_name, expiry, cvv, showing_time_id, *seats):
    # Check if showing has a valid id
    q = Query(db=Database())
    q.select(models.Showing.id, models.Showing.start_time, models.Movie.title, models.TheatreRoom.name)\
        .join(models.Movie, join_type="LEFT", on=(models.Movie.id, "=", models.Showing.movie_id))\
        .join(models.TheatreRoom, join_type="LEFT", on=(models.TheatreRoom.id, "=", models.Showing.theatre_id))\
        .where(models.Showing.id, "=", showing_time_id)
    showing_time_info = q.fetch_one()

    print("showing_time_info", showing_time_info)
    if len(showing_time_info) == 0:
        await ctx.send("Showing time id is not valid!")
        return

    # Check if the seats exist
    tickets = []
    for seat in seats:
        (row_letter, column_number) = formatting.parse_seat(seat)
        q.reset_query()
        q.select(models.Ticket, models.Seat) \
            .join(models.Seat, join_type="LEFT", on=(models.Ticket.seat_id, "=", models.Seat.id)) \
            .where(models.Ticket.showing_time_id, "=", showing_time_id, "AND",
                    models.Seat.row_letter, "=", row_letter, "AND",
                    models.Seat.column_number, "=", column_number)
        print("before it executes", q.get_sql_stmt())
        ticket_info = q.fetch_one()
        if ticket_info is None:
            await ctx.send("Seat is not valid!")
            return
        # tickets.append(ticket_info["id"])
        tickets.append((row_letter, column_number, ticket_info["id"]))

        
    # Create a transaction
    q.reset_query()
    q.insert(models.User).values({"status": 1})
    guest_user_id = q.commit_changes()
    # ticket_ids = []

    for ticket in tickets:
        # (row_letter, column_number) = formatting.parse_seat(seat)
        
        q.update(models.Ticket).values({"bought_by": guest_user_id, "status": 2})\
            .where(models.Ticket.showing_time_id, "=", showing_time_id, "AND", models.Ticket.id, "=", ticket[2])
        ticket_id = q.commit_changes()
        # ticket_ids.append((row_letter, column_number, ticket_id))

    output_string = f'Ticket confirmed for {showing_time_info["title"]} at {showing_time_info["start_time"].strftime("%-I:%M %p")}\n'
    output_string += f"Here's your ticket(s), please scan the id before entering the movie theatre\n"

    for ticket in tickets:
        output_string += f'{showing_time_info["title"]} {showing_time_info["start_time"].strftime("%-I:%M %p")}\n'
        output_string += f'Theatre {showing_time_info["name"]}, Seat: {ticket[0]}{ticket[1]} <- ID: {ticket[2]}\n'

    await ctx.send(output_string)


@bot.command(name="sign_up", description="Creates an account in the database")
async def sign_up(ctx, username: str, email: str, password: str, first_name: str = None, last_name: str = None, phone_number: str = None):
    # Check if email is already taken
    # role, password, email, user_id, username are all required, everything else is optional

    # Create a new account 
    email_regex = r'\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Z|a-z]{2,7}\b'
    phone_regex = r'^(\+\d{1,2}\s?)?\(?\d{3}\)?[\s.-]?\d{3}[\s.-]?\d{4}$'
    min_username_length = 6
    min_password_length = 6
    errors_raised = False
    errors = {}

    q = Query(db=Database())
    q.select(models.Account).where(
        models.Account.username, "=", username, 
        "OR", models.Account.email, "=", email,
        "OR", models.Account.phone_number, "=", phone_number)
    user_exists = q.fetch_one()
    print(user_exists)

    if user_exists is not None:
        errors["username"] = "Username, email, or phone number is already taken"
        errors_raised = True
    
    if len(username) < min_username_length:
        errors["username"] = "Username needs to have a minimum length of 6 characters!"
        errors_raised = True
    
    if not match(email_regex, email):
        errors["email"] = f"{email} is not a valid email address"
        errors_raised = True

    if len(password) < min_password_length:
        errors["password"] = "Password needs to have a minimum length of 6 characters!"
        errors_raised = True
    
    if phone_number is not None and not match(phone_regex, phone_number):
        errors["phone_number"] = f"{phone_number} is not a valid phone number"
        errors_raised = True

    if errors_raised:
        output_string = "Unfortunately, you have errors signing up, please try again: \n"
        for error_message in errors.values():
            output_string += error_message + "\n"
        await ctx.send(output_string)
    else:
        # q = Query(db=Database())
        q.reset_query()
        q.select(models.UserStatus.id).where(models.UserStatus.name, "=", "Active")
        user_status = q.fetch_one()

        q.reset_query()
        q.select(models.Role.id).where(models.Role.name, "=", "Member")
        role = q.fetch_one()
        print(role)

        # Create a new user
        q.reset_query()
        q.insert(models.User).values({"account_id": None, "status": user_status["id"]})
        new_user_id = q.commit_changes()

        # Create a new account
        q.reset_query()
        q.insert(models.Account).values({
            "role": role["id"],
            "password": password,
            "email": email,
            "phone_number": phone_number,
            "user_id": new_user_id,
            "first_name": first_name,
            "last_name": last_name,
            "username": username
        })
        new_account_id = q.commit_changes()

        # Attach account to the user
        q.update(models.User).values({"account_id": new_account_id}).where(models.User.id, "=", new_user_id)
        q.commit_changes()

        output_string = "Sign up successful call `!log_in [username] [password]` to log in"
        await ctx.send(output_string)


@bot.command(name="log_in", description="database business requirement #4 here")
async def log_in(ctx, username, password):
    # Check if username exists
    # Check if password matches
    # On success, tell user that they are now logged in
        # Start recording user state
    q = Query(db=Database())
    q.select(models.Account).where(models.Account.username, "=", username)
    user_exists = q.fetch_one()
    if user_exists is not None:
        if password != user_exists["password"]:
            await ctx.send("Wrong email or password!")
        else:
            online_users.login_user(ctx.author, {
                    "username": user_exists["username"],
                    "user_id": user_exists["user_id"],
                    "account_id": user_exists["id"]
                })
            await ctx.send(f"You are now logged in. Welcome {user_exists['username']}!")


@bot.command(name="log_out", description="database business requirement #5 here")
async def log_out(ctx):
    # Remove user from user state so that they are logged out
    result = online_users.logout_user(ctx.author)
    if result is None:
        await ctx.send("No account to log out of")
    else:
        await ctx.send("You have succesfully logged out")


@bot.command(name="refund_ticket", description="database business requirement #6 here")
async def refund_ticket(ctx, ticket_id):
    # Check if they are logged in
    # Check if the ticket that they entered belongs to them
    user = online_users.get_user_state(ctx.author)
    if user is None:
        await ctx.send("You are not logged in!")
    else:
        q = Query(db=Database())
        q.select(models.Ticket).where(models.Ticket.id, "=", ticket_id, "AND", models.Ticket.bought_by, "=", user["user_id"])
        ticket = q.fetch_one()
        if ticket is None:
            await ctx.send("Ticket does not exist. Are you sure the id is correct?")
        else:
            # 1 means unsold
            q.update(models.Ticket).values({"status": 1, "bought_by": None})
            q.commit_changes()
            await ctx.send("Ticket has been refunded")



@bot.command(name="view_profit", description="database business requirement #7 here")
async def view_profit(ctx):
    # Check if they are logged in and that they are an admin
    await ctx.send("This method is not implemented yet")


@bot.command(name="pay_stubs", description="database business requirement #8 here")
async def pay_stubs(ctx):
    # Check if they are logged in and if they are an employee
    await ctx.send("This method is not implemented yet")


@bot.command(name="view_rewards", description="database business requirement #9 here")
async def view_rewards(ctx, *args):
    # Check if they are logged in and output discounts and rewards
    await ctx.send("This method is not implemented yet")


@bot.command(name="apply_discout", description="database business requirement #10 here")
async def apply_discout(ctx, *args):
    # Check if they are logged in and apply all discounts available
    await ctx.send("This method is not implemented yet")


@bot.command(name="view_tickets", description="database business requirement #11 here")
async def view_tickets(ctx, *args):
    # Check if they are logged in and view all tickets
    user = online_users.get_user_state(ctx.author)
    if user is None:
        await ctx.send("You are not logged in!")
    else:
        # Output: 
        # Ticket for Dune Part 2 at 4:30pm. Seat A4 in Theatre B <- id: 32
        output_string = ""
        q = Query(db=Database())
        q.select(models.Ticket.id, 
                    models.Movie.title, 
                    models.Showing.start_time, 
                    models.Seat.row_letter, 
                    models.Seat.column_number, 
                    models.TheatreRoom.name)\
            .join(models.Movie, join_type="LEFT", on=(models.Movie.id, "=", models.Ticket.movie_id)) \
            .join(models.TheatreRoom, join_type="LEFT", on=(models.TheatreRoom.id, "=", models.Ticket.theatre_id)) \
            .join(models.Showing, join_type="LEFT", on=(models.Showing.id, "=", models.Ticket.showing_time_id)) \
            .join(models.Seat, join_type="LEFT", on=(models.Seat.id, "=", models.Ticket.seat_id)) \
            .where(models.Ticket.bought_by, "=", user["user_id"])
        tickets = q.fetch_all()
        for ticket in tickets:
            output_string += f'Ticket for {ticket["title"]} at {ticket["start_time"].strftime("%-I:%M %p")}. Seat {ticket["row_letter"]}{ticket["column_number"]} in Theatre {ticket["name"]} <- id: {ticket["id"]}\n'
        await ctx.send(output_string)


@bot.command(name="check_in_ticket", description="database business requirement #12 here")
async def check_in_ticket(ctx, *args):
    # Check if the user is an employee and change the state of the ticket to "used"
    await ctx.send("This method is not implemented yet")


@bot.command(name="cmd_15", description="database business requirement #15 here")
async def _command15(ctx, *args):
    await ctx.send("This method is not implemented yet")

# bot.run(config["DISCORD_TOKEN"])
bot.run(config.discord_token)
