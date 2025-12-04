package ai.villanova.themis.bot;

import com.vaadin.flow.component.html.Span;

public class DeleteSourceCommand extends Command {
    public DeleteSourceCommand() {
        super("Delete source");
    }

    @Override
    public Operation execute(Bot bot, Object... values) {
        super.validate(values);
        bot.addComponent(new Span("Not yet implemented."));
        return new Peek();
    }
}
