package ai.neodatagroup.themis.bot;

public class DatasetsCommand extends Command {
    public DatasetsCommand() {
        super("Datasets");
    }

    @Override
    public Operation execute(Bot bot, Object... values) {
        super.validate(values);
        Menu menu = new Menu(
            "Commands to manage datasets",
            "When fully implemented, this section will allow you to manage the machine learning datasets for my recommendation models.",
            new PopCommand()
        );
        return new Push(menu);
    }
}
