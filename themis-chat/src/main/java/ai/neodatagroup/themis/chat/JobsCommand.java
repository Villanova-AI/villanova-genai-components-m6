package ai.neodatagroup.themis.chat;

public class JobsCommand extends Command {
    public JobsCommand() {
        super("Jobs");
    }

    @Override
    public Operation execute(Bot bot, Object... values) {
        super.validate(values);
        return new Push(new JobsMenu());
    }
}
