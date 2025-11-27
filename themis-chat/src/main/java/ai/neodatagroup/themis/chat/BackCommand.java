package ai.neodatagroup.themis.chat;

public class BackCommand extends Command{
    public BackCommand(Bot bot) {
        super(bot, "Back");
    }

    @Override
    public void execute() {
        bot.menus.pop();
        bot.showMenuOrExit();
    }
}
