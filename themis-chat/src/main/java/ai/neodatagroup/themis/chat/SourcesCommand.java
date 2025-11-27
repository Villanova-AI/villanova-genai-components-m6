package ai.neodatagroup.themis.chat;

public class SourcesCommand extends Command {
    public SourcesCommand(Bot bot) {
        super(bot, "Sources");
    }

    @Override
    public void execute() {
        bot.menus.push(new SourcesMenu(bot));
        bot.showMenuOrExit();
    }
}
