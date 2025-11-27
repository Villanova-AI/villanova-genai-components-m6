package ai.neodatagroup.themis.chat;

public class SourcesCommand extends Command {
    public SourcesCommand() {
        super("Sources");
    }

    @Override
    public Operation execute(Bot bot, Object... values) {
        super.validate(values);
        return new Push(new SourcesMenu());
    }
}
