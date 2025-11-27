package ai.neodatagroup.themis.chat;

public class SourcesMenu extends Menu {
    public SourcesMenu() {
        super(
            "Commands to manage sources",
            new ListSourcesCommand(),
            new InputForGetSourceCommand(),
            new InputForCreateSourceCommand(),
            new InputForUpdateSourceCommand(),
            new InputForDeleteSourceCommand(),
            new BackCommand()
        );
    }
}
