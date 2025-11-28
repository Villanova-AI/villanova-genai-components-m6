package ai.neodatagroup.themis.chat;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

import java.net.URI;
import java.net.URISyntaxException;

public class InputForCreateSourceCommand extends Command {
    public InputForCreateSourceCommand() {
        super("Create source");
    }

    @Override
    public Operation execute(Bot bot, Object... values) {
        super.validate(values);

        // Create a Vaadin form allowing the user to create a source
        VerticalLayout form = new VerticalLayout();

        // The user should be able to input a url, a name, and a description
        // The form should end with a submit button and a cancel button
        // When the submit button is clicked, a submit command should be executed
        // When the cancel button is clicked, a cancel command should be executed
        TextField uriField = new TextField("URI");
        uriField.setPlaceholder("Enter source URI...");

        TextField nameField = new TextField("Name");
        nameField.setPlaceholder("Enter source name...");

        TextArea descriptionArea = new TextArea("Description");
        descriptionArea.setPlaceholder("Enter source description...");

        Button submitButton = new Button("Create");
        submitButton.addClickListener(event -> {
            URI uri = validateURI(uriField.getValue());
            if (uri == null) {
                Notification.show("Invalid URI");
                return;
            }

            String name = nameField.getValue();
            String description = descriptionArea.getValue();
            bot.execute(new CreateSourceCommand(), uri, name, description);
        });

        Button cancelButton = new Button("Cancel");
        cancelButton.addClickListener(event -> {
            bot.execute(new PeekCommand());
        });

        form.add(uriField, nameField, descriptionArea, submitButton, cancelButton);
        bot.addComponent(form);
        return new Noop();
    }


    private URI validateURI(String value) {
        try {
            URI uri = new URI(value);
            if (uri.getScheme() != null && (uri.getScheme().equals("http") || uri.getScheme().equals("https"))) {
                return uri;
            }
            else return null;
        } catch (URISyntaxException e) {
            return null;
        }
    }
}
