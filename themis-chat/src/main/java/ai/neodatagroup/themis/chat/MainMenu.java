package ai.neodatagroup.themis.chat;

public class MainMenu extends Menu {
    public MainMenu(Bot bot) {
        super(
            new SourcesCommand(bot),
            // new ItemsCommand(),
            // new JobsCommand(),
            // new DatasetsCommand(),
            // new ModelsCommand(),
            // new RecommendationsCommand(),
            // new ProfilesCommand(),
            new ExitCommand(bot)
        );
    }
}
