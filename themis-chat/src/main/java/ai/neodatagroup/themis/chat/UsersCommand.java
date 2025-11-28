package ai.neodatagroup.themis.chat;

public class UsersCommand extends Command {
    public UsersCommand() {
        super("Users");
    }

    @Override
    public Operation execute(Bot bot, Object... values) {
        super.validate(values);
        Menu menu = new Menu(
            "Commands to manage users",
            """
            This menu allows you the following options:
            
            - **Create user** allows you to create a new user.
            - **List users** allows you to inspect the list of users, set the current user among the listed ones, and optionally delete users.
            - **Edit current user** allows you to inspect and update the items with which the current user has interacted with.
            - **Back** brings you back to the main menu.
            """,
            new CreateUserCommand(),
            new ListUsersCommand(),
            new EditCurrentUserCommand(),
            new PopCommand()
        );
        return new Push(menu);
    }
}
