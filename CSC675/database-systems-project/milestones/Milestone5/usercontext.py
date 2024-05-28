class UserContext():
    """
    This class stores users' state
    This allows the discord bot to store memory in between commands
    The state gets reset if you restart the bot, so save any permanent changes into a database
    """

    def __init__(self) -> None:
        self.users_state = {}

    def login_user(self, discord_user: str, user_state: dict):
        self.users_state[discord_user] = user_state
    
    def logout_user(self, discord_user) -> dict[any] | None:
        return self.users_state.pop(discord_user, None)
    
    def get_user_state(self, discord_user)-> dict[any] | None:
        if discord_user not in self.users_state:
            return None
        else:
            return self.users_state[discord_user]