package ai.neodatagroup.themis.chat;

public class DatasetsCommand extends Command {
    public DatasetsCommand() {
        super("Datasets");
    }

    @Override
    public Operation execute(Bot bot, Object... values) {
        super.validate(values);
        return new Push(new DatasetsMenu(bot));
    }
}
