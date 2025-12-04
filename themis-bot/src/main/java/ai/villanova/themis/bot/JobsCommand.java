package ai.villanova.themis.bot;

public class JobsCommand extends Command {
    public JobsCommand() {
        super("Jobs");
    }

    @Override
    public Operation execute(Bot bot, Object... values) {
        super.validate(values);
        Menu menu = new Menu(
            "Commands to manage jobs",
            """
            When fully implemented, this section will allow you to manage jobs on my server.
            
            The following job types are available:
            
            - A **crawl** job populates the items from a given source.
            - An **encode** job computes semantic embeddings for items.
            - A **curate** job automatically generate a machine learning dataset to train a recommendation model.
            - A **train** job trains a recommendation model.
            - A **test** job tests a recommendation model.
            """,
            new PopCommand()
        );
        return new Push(menu);
    }
}
