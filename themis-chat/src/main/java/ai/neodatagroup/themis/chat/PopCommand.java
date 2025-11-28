package ai.neodatagroup.themis.chat;

public class PopCommand extends Command{
    public PopCommand() {
        super("Back");
    }

    @Override
    public Operation execute(Bot bot, Object... values) {
        super.validate(values);
        return new Pop();
    }
}
