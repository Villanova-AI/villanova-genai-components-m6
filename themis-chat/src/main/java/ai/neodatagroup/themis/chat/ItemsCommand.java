package ai.neodatagroup.themis.chat;

public class ItemsCommand extends Command {
    public ItemsCommand() {
        super("Items");
    }

    @Override
    public Operation execute(Bot bot, Object... values) {
        super.validate(values);
        return new Push(new ItemsMenu(bot));
    }
}
