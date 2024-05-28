from orm import Query
from database import Database
import models
# This file is responsible for formatting the output of the text based on what they recieve

def output_now_showing(all_showings: tuple[dict[any]]) -> str:
    # Group showings by date, then group by movie title
    showing_days = {}
    for showing in all_showings:
        date_key = showing["start_time"].strftime("%B %d, %Y")
        if date_key in showing_days:
            showing_days[date_key].append(showing)
        else:
            showing_days[date_key] = [showing]

    output_string = "Here is a list of movies that are currently showing:\n"
    for showing_date, shows in showing_days.items():
        output_string += f'## **Movies showing on {showing_date}**\n'
        for show in shows:
            start_time = show["start_time"].strftime("%-I:%M %p")
            movie_title = show["title"]
            showing_id = show["id"]
            output_string += f'> [*{start_time}*] **{movie_title}** <- `id: {showing_id}`\n'
    
    output_string += "\nEnter the id of the movie and time you want to watch by typing:" \
                     " `!reserve_seat [id]`\nAn Example: `!reserve_seat 31`"
    
    return output_string

def output_seating(ticket_data: tuple[dict[any]], seats_taken: list[str] | None = None) -> str:
    # Display a text gui showing available seats for a theatre
    # print(ticket_data)
    output_string = "```\n"

    # Attain number of rows and columns
    row_letters = set()
    max_columns = 0
    for ticket in ticket_data:
        row_letters.add(ticket["row_letter"])
        if max_columns < ticket["column_number"]:
            max_columns = ticket["column_number"]

    # Label each seat with the proper symbol
    seats: list[list[str]] = [[0] * max_columns for rows in range(len(row_letters))]
    legend: dict[str, str] = {}

    for ticket in ticket_data:
        row_index = ord(ticket["row_letter"].lower()) - 97
        column_index = ticket["column_number"] - 1
        # print(row_index, column_index)
        if ticket["bought_by"] is not None:
            seats[row_index][column_index] = "❌"
            legend["❌"] = "Taken"
        elif ticket["name"] == "Disabled":
            seats[row_index][column_index] = "♿"
            legend["♿"] = "Disabled"
        elif ticket["name"] == "Regular":
            seats[row_index][column_index] = "R"
            legend["R"] = "Regular"
        elif ticket["name"] == "Couple":
            seats[row_index][column_index] = "C"
            legend["C"] = "Couples"
        elif ticket["name"] == "Reclining":
            seats[row_index][column_index] = "L"
            legend["R"] = "Luxury"

    # Output selected seats
    if seats_taken is not None:
        for seat in seats_taken:
            (row_letter, column_number) = parse_seat(seat)
            row_index = ord(row_letter.lower()) - 97
            column_index = column_number - 1
            seats[row_index][column_index] = "✅"
            legend["✅"] = "Selected"
    
    # print(seats)

    # Print rows of seats with labels
    max_width = 0
    seating_strings = []
    side_padding = " " * 5
    space_between_seats = " " * 2
    for row_number, row in enumerate(seats):
        line = f'{side_padding}'
        row_letter = chr(row_number + 97).upper()
        line += f'{row_letter}    '
        # line += space_between_seats.join(row)
        line += space_between_seats.join(row)
        line += f'{side_padding}'
        seating_strings.append(line)
        
        if len(line) > max_width:
            max_width = len(line)

    
    # Print the bottom containing column numbers
    line = f'\n{side_padding}     '
    for column_number in range(1, max_columns + 1):
        line += str(column_number) + space_between_seats
    seating_strings.append(line)

    # Print the movie title
    theatre_screen_title = "THEATRE SCREEN"
    theatre_screen_spacing = " " * int((max_width - len(theatre_screen_title)) / 2)
    theatre_screen_line = "-" * max_width

    # Print the movie screen
    output_string += f"{theatre_screen_spacing}{theatre_screen_title}{theatre_screen_spacing}\n"
    output_string += f"{theatre_screen_line}\n"
    for seating_string in seating_strings:
        output_string += f"{seating_string}\n"

    # Print the legend
    output_string += f"\nLegend: "
    legend_strings = []
    for symbol, meaning in legend.items():
        legend_strings.append(f'{symbol} = {meaning}')
    output_string += ", ".join(legend_strings)
    output_string += "\n```"
    
    return output_string

def get_showing_details(showing_time_id) -> dict[any]:
    # It'll get the sub, dub, resolution, sound type, rating and theatre name
    q = Query(db=Database())
    q.select(models.Showing.id, models.Showing.start_time, models.Showing.dubbing_id, models.Showing.subtitle_id, models.Movie.title, models.SoundSystem.name, models.FilmRating.name, models.TheatreRoom.name) \
        .join(models.Movie, join_type="LEFT", on=(models.Movie.id, "=", models.Showing.movie_id)) \
        .join(models.SoundSystem, join_type="LEFT", on=(models.SoundSystem.id, "=", models.Showing.sound_type_id)) \
        .join(models.FilmRating, join_type="LEFT", on=(models.FilmRating.id, "=", models.Movie.film_rating)) \
        .join(models.TheatreRoom, join_type="LEFT", on=(models.TheatreRoom.id, "=", models.Showing.theatre_id)) \
        .where(models.Showing.id, "=", showing_time_id)
    result = q.fetch_one()

    showing_details = {
        "showing_id": result["id"],
        "start_time": result["start_time"],
        "dubbing_language": None,
        "subtitle_language": None,
        "dubbing_id": result["dubbing_id"],
        "subtitle_id": result["subtitle_id"],
        "movie_title": result["title"],
        "sound_system": result["name"],
        "age_rating": result["FilmRating.name"],
        "theatre_room_name": result["TheatreRoom.name"]
    }

    if result["dubbing_id"] is not None:
        q.reset_query()
        q.select(models.Language).where(models.Language.id, "=", result["dubbing_id"])
        dub = q.fetch_one()
        showing_details["dubbing_language"] = dub["language_name"]
        

    if result["subtitle_id"] is not None:
        q.reset_query()
        q.select(models.Language).where(models.Language.id, "=", result["subtitle_id"])
        sub = q.fetch_one()
        showing_details["subtitle_language"] = sub["language_name"]

    return showing_details

def parse_seat(seat: str) -> tuple[str, str]:
    # This assumes that the seat's name goes from A-Z and the number goes from 0 to 9
    row_letter = seat[0]
    column_number = int(seat[1])
    return (row_letter, column_number)

def output_confirmed_seating():
    "Seating Confirmed: A4, A5 (2 Luxury seats)"
    "Theatre: 1B - Luxury Theatre"
    "Cost: $30.00 "
    "Discount: None"