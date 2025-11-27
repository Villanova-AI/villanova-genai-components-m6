package ai.neodatagroup.themis.chat;

public class MainMenu extends Menu {
    public MainMenu(Bot bot) {
        super(
            "Main menu",
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
