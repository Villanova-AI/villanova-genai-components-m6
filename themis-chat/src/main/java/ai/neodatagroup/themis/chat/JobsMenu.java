package ai.neodatagroup.themis.chat;

public class JobsMenu extends Menu {
    public JobsMenu() {
        super(
            "Commands to manage jobs",
            new BackCommand()
        );
    }
}
