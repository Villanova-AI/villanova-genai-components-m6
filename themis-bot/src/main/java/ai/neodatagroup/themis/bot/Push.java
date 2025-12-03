package ai.neodatagroup.themis.bot;

public record Push(Menu menu) implements Operation {
    @Override
    public void execute(Bot bot) {
        bot.menus.push(menu);
        bot.showMenuOrExit();
    }
}
