package ai.villanova.themis.bot;

public final class Pop implements Operation {
    @Override
    public void execute(Bot bot) {
        bot.menus.pop();
        bot.showMenuOrExit();
    }
}
