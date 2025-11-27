package ai.neodatagroup.themis.chat;

public class ItemsMenu extends Menu {
    public ItemsMenu(Bot bot) {
        super(
            "Commands to manage items",
            bot,
            new BackCommand()
        );
    }
}
