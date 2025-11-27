package ai.neodatagroup.themis.chat;

import org.apache.commons.lang3.NotImplementedException;

public class GetSourceCommand extends Command {
    public GetSourceCommand(Bot bot) {
        super(bot, "Get source");
    }

    @Override
    public void execute() {
        throw new NotImplementedException();
    }
}
