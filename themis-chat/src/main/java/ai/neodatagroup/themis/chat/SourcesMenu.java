package ai.neodatagroup.themis.chat;

public class SourcesMenu extends Menu {
    public SourcesMenu(Bot bot) {
        super(
            "Commands to manage sources",
            bot,
            new ListSourcesCommand(),
            new InputForGetSourceCommand(),
            new InputForCreateSourceCommand(),
            new InputForUpdateSourceCommand(),
            new InputForDeleteSourceCommand(),
            new BackCommand()
        );
    }
}
