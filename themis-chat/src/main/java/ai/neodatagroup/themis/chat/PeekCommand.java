package ai.neodatagroup.themis.chat;

public class PeekCommand extends Command {
    public PeekCommand() {
        super("Peek");
    }

    @Override
    public Operation execute(Bot bot, Object... values) {
        super.validate(values);
        return new Peek();
    }
}
