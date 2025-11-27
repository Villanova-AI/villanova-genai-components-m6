package ai.neodatagroup.themis.chat;

import org.apache.commons.lang3.NotImplementedException;

public class CreateSourceCommand extends Command {
    public CreateSourceCommand(Bot bot) {
        super(bot, "Create source");
    }

    @Override
    public void execute() {
        throw new NotImplementedException();
    }
}
