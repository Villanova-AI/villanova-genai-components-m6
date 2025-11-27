package ai.neodatagroup.themis.chat;

public class ModelsMenu extends Menu {
    public ModelsMenu() {
        super(
            "Commands to manage models",
            new BackCommand()
        );
    }
}
