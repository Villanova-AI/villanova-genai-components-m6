package ai.neodatagroup.themis.chat;

public record Push(Menu menu) implements Operation {
    @Override
    public void execute(Bot bot) {
        bot.menus.push(menu);
        bot.showMenuOrExit();
    }
}
