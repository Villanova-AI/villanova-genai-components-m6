package ai.neodatagroup.themis.chat;

public class ModelsCommand extends Command {
    public ModelsCommand() {
        super("Models");
    }

    @Override
    public Operation execute(Bot bot, Object... values) {
        super.validate(values);
        return new Push(new ModelsMenu(bot));
    }
}
