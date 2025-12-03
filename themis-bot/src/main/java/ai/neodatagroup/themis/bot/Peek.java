package ai.neodatagroup.themis.bot;

public final class Peek implements Operation {
    @Override
    public void execute(Bot bot) {
        bot.showMenuOrExit();
    }
}
