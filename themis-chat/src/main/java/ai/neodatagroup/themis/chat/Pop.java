package ai.neodatagroup.themis.chat;

public final class Pop implements Operation {
    @Override
    public void execute(Bot bot) {
        bot.menus.pop();
        bot.showMenuOrExit();
    }
}
