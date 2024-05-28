# from dotenv import dotenv_values
from dataclasses import dataclass
import os

# config = dotenv_values(".env")

@dataclass
class Config:
    discord_token = os.environ["DISCORD_TOKEN"]
    aws_token: str = os.environ["AWS_TOKEN"]
    discord_bot_url: str = os.environ["DISCORD_BOT_URL"]
    db_host: str = os.environ["DB_HOST"]
    db_username: str = os.environ["DB_USERNAME"]
    db_password: str = os.environ["DB_PASSWORD"]
    db_name: str = os.environ["DB_NAME"]

config = Config()
