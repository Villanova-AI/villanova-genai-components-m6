package ai.neodatagroup.themis.chat;

public class DatasetsMenu extends Menu {
    public DatasetsMenu(Bot bot) {
        super(
            "Commands to manage datasets",
            bot,
            new BackCommand()
        );
    }
}
