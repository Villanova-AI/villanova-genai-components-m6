package ai.neodatagroup.themis.chat;

import org.apache.commons.lang3.NotImplementedException;

public class DeleteSourceCommand extends Command {
    public DeleteSourceCommand(Bot bot) {
        super(bot, "Delete source");
    }

    @Override
    public void execute() {
        throw new NotImplementedException();
    }
}
