package ai.neodatagroup.themis.chat;

public class SourcesMenu extends Menu {
    public SourcesMenu(Bot bot) {
        super(
            new ListSourcesCommand(bot),
            new GetSourceCommand(bot),
            new CreateSourceCommand(bot),
            new UpdateSourceCommand(bot),
            new DeleteSourceCommand(bot),
            new BackCommand(bot)
        );
    }
}
