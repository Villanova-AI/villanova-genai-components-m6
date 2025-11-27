package ai.neodatagroup.themis.chat;

public class MainMenu extends Menu {
    public MainMenu(Bot bot) {
        super(
            "Main menu",
            bot,
            new SourcesCommand(),
            new ItemsCommand(),
            new JobsCommand(),
            new DatasetsCommand(),
            new ModelsCommand(),
            new RecommendationsCommand(),
            new ProfilesCommand()
        );
    }
}
