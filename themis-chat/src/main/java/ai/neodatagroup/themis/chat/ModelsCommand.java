package ai.neodatagroup.themis.chat;

public class ModelsCommand extends Command {
    public ModelsCommand() {
        super("Models");
    }

    @Override
    public Operation execute(Bot bot, Object... values) {
        super.validate(values);
        Menu menu = new Menu(
            "Commands to manage models",
            "When fully implemented, this section will allow you to manage my recommendation models.",
            new PopCommand()
        );
        return new Push(menu);
    }
}
