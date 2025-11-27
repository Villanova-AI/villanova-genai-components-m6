package ai.neodatagroup.themis.chat;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Span;
import org.apache.commons.lang3.NotImplementedException;

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
