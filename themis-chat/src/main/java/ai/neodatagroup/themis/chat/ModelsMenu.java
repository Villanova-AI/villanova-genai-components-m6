package ai.neodatagroup.themis.chat;

public class ModelsMenu extends Menu {
    public ModelsMenu(Bot bot) {
        super(
            "Commands to manage models",
            bot,
            new BackCommand()
        );
    }
}
