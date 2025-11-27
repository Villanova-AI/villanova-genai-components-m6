package ai.neodatagroup.themis.chat;

public class ItemsMenu extends Menu {
    public ItemsMenu() {
        super(
            "Commands to manage items",
            new BackCommand()
        );
    }
}
