package ai.neodatagroup.themis.chat;

import org.apache.commons.lang3.NotImplementedException;

public class UpdateSourceCommand extends Command {
    public UpdateSourceCommand(Bot bot) {
        super(bot, "Update source");
    }

    @Override
    public void execute() {
        throw new NotImplementedException();
    }
}
