package ai.neodatagroup.themis.bot;

import ai.neodatagroup.themis.bot.api.SourcesApi;
import ai.neodatagroup.themis.bot.model.Source;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

import java.net.URI;
import java.net.URISyntaxException;

public class CreateSourceCommand extends Command {
    private SourcesApi api;

    public CreateSourceCommand() {
        super("Create source");
        this.api = new SourcesApi(Configuration.getDefaultApiClient());
    }

    @Override
    public Operation execute(Bot bot, Object... values) {
        super.validate(values);
        bot.addComponent(makeComponent(bot));
        return new Noop();
    }

    private Component makeComponent(Bot bot) {
        VerticalLayout component = new VerticalLayout();

        H3 header = new H3("Create a new source");

        TextField nameField = new TextField("Name");
        nameField.setWidthFull();
        nameField.setPlaceholder("Enter source name...");

        TextArea descriptionArea = new TextArea("Description");
        descriptionArea.setWidthFull();
        descriptionArea.setMinRows(5);
        descriptionArea.setMaxRows(5);
        descriptionArea.setPlaceholder("Enter source description...");

        ComboBox<Source.TypeEnum> typeComboBox = new ComboBox<>("Type");
        typeComboBox.setItems(Source.TypeEnum.values());
        typeComboBox.setPlaceholder("Select source type...");

        TextField urlField = new TextField("URL");
        urlField.setWidthFull();
        urlField.setPlaceholder("Enter source URL...");

        Button submitButton = new Button("Create");
        submitButton.addClickListener(event -> {
            String name = nameField.getValue().trim();
            if (name.isEmpty()) {
                Notification.show("Name is required");
                return;
            }
            String description = descriptionArea.getValue().trim();
            Source.TypeEnum type = typeComboBox.getValue();
            if (type == null) {
                Notification.show("Type is required");
                return;
            }
            URI url = validateURL(urlField.getValue());
            if (url == null) {
                Notification.show("A valid http or https URL is required");
                return;
            }

            Source source = new Source();
            source.setName(name);
            source.setDescription(description);
            source.setType(type);
            source.setUrl(url);
            try {
                source = api.createSource(source);
                Notification.show("Source created with ID: " + source.getId());
                bot.execute(new PeekCommand());
            } catch (ApiException e) {
                Notification.show("Error creating source: " + e.getMessage());
            }
        });

        Button cancelButton = new Button("Cancel");
        cancelButton.addClickListener(event -> {
            bot.execute(new PeekCommand());
        });

        HorizontalLayout buttons = new HorizontalLayout(submitButton, cancelButton);
        buttons.setWidthFull();
        buttons.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        component.add(
            header,
            nameField,
            descriptionArea,
            typeComboBox,
            urlField,
            buttons
        );
        return component;
    }

    private URI validateURL(String value) {
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
