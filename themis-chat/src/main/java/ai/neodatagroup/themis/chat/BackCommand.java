package ai.neodatagroup.themis.chat;

public class BackCommand extends Command{
    public BackCommand() {
        super("Back");
    }

    @Override
    public Operation execute(Bot bot, Object... values) {
        super.validate(values);
        return new Pop();
    }
}
