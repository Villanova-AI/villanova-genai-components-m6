package ai.neodatagroup.themis.bot;

import com.vaadin.flow.component.html.Span;

import java.net.URI;

public class CreateSourceCommand extends Command {
    public CreateSourceCommand() {
        super(
            "Create source",
            new Argument("uri", URI.class),
            new Argument("name", String.class),
            new Argument("description", String.class)
        );
    }

    public Operation execute(Bot bot, Object... values) {
        super.validate(values);
        bot.addComponent(new Span("Not yet implemented."));
        return new Peek();
    }
}
