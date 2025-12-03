package ai.neodatagroup.themis.bot;

public class ItemsCommand extends Command {
    public ItemsCommand() {
        super("Items");
    }

    @Override
    public Operation execute(Bot bot, Object... values) {
        super.validate(values);
        Menu menu = new Menu(
            "Commands to manage items",
            "When fully implemented, this section will allow you to manage the set of items I may recommend to you.",
            new PopCommand()
        );
        return new Push(menu);
    }
}
