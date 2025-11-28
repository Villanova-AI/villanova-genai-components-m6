package ai.neodatagroup.themis.chat;

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
            
            Supported sources may be Wordpress e-commerce websites or news RSS feeds.
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
