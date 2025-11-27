package ai.neodatagroup.themis.chat;

public final class Peek implements Operation {
    @Override
    public void execute(Bot bot) {
        bot.showMenuOrExit();
    }
}
