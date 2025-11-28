package ai.neodatagroup.themis.chat;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class CreateUserCommand extends Command {
    public CreateUserCommand() {
        super("Create user");
    }

    @Override
    protected Operation execute(Bot bot, Object... values) {
        super.validate(values);

        VerticalLayout component = new VerticalLayout();
        H3 header = new H3("Create user");
        TextField nameField = new TextField("Name");
        Button submitButton = new Button("Submit");
        submitButton.addClickListener(event -> {
           String name = nameField.getValue().trim();
           if (name.isEmpty()) {
               Notification.show("Please insert a name for the user to be created.");
               return;
           }
           bot.addComponent(new Span("User " + name + " created"));
           bot.execute(new PeekCommand());
        });
        Button cancelButton = new Button("Cancel");
        cancelButton.addClickListener(event -> bot.execute(new PeekCommand()));

        component.add(header, nameField, submitButton, cancelButton);
        bot.addComponent(component);
        return new Noop();
    }
}
