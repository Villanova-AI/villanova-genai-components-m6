package ai.neodatagroup.themis.bot;

public class SourcesCommand extends Command {
    public SourcesCommand() {
        super("Sources");
    }

    @Override
    public Operation execute(Bot bot, Object... values) {
        super.validate(values);
        Menu menu = new Menu(
            "Commands to manage sources",
            """
            When fully implemented, this section will allow you to manage a set of sources for the items I may recommend to you.
            
            Supported sources are:
             
            - e-commerce websites;
            - eBay;
            - news RSS feeds;
            - business directories;
            - Wikipedia;
            - PGN chess databases.
            """,
            //new ListSourcesCommand(),
            //new InputForGetSourceCommand(),
            //new InputForCreateSourceCommand(),
            //new InputForUpdateSourceCommand(),
            //new InputForDeleteSourceCommand(),
            new PopCommand()
        );
        return new Push(menu);
    }
}
