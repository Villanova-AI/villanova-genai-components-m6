package ai.neodatagroup.themis.bot;

import com.vaadin.flow.component.html.Span;

public class InputForUpdateSourceCommand extends Command {
    public InputForUpdateSourceCommand() {
        super("Update source");
    }

    @Override
    public Operation execute(Bot bot, Object... values) {
        super.validate(values);
        bot.addComponent(new Span("Not yet implemented."));
        return new Peek();
    }
}
