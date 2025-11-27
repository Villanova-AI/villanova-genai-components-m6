package ai.neodatagroup.themis.chat;

public class JobsMenu extends Menu {
    public JobsMenu(Bot bot) {
        super(
            "Commands to manage jobs",
            bot,
            new BackCommand()
        );
    }
}
