package ai.neodatagroup.themis.chat;

import com.vaadin.flow.component.html.Span;

public class ProfilesCommand extends Command {
    public ProfilesCommand() {
        super("Profiles");
    }

    @Override
    public Operation execute(Bot bot, Object... values) {
        super.validate(values);
        bot.addComponent(new Span("Not yet implemented."));
        return new Peek();
    }
}
