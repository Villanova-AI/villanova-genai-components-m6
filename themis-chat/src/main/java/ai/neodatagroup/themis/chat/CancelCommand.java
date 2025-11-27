package ai.neodatagroup.themis.chat;

public class CancelCommand extends Command {
    public CancelCommand() {
        super("Cancel");
    }

    @Override
    public Operation execute(Bot bot, Object... values) {
        super.validate(values);
        return new Peek();
    }
}
