package ai.neodatagroup.themis.chat;

public class ExitCommand extends Command {
    public ExitCommand(Bot bot) {
        super(bot, "Exit");
    }

    @Override
    public void execute() {
        bot.menus.clear();
    }
}
