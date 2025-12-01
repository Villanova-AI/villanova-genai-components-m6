package ai.neodatagroup.themis.chat;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.messages.MessageInput;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;

public class InputForRecommendationsCommand extends Command {
    public InputForRecommendationsCommand() {
        super("Recommendations");
    }

    @Override
    public Operation execute(Bot bot, Object... values) {
        super.validate(values);
        bot.addComponent(makeComponent(bot));
        return new Noop();
    }

    private Component makeComponent(Bot bot, Object... values) {
        H3 header = new H3("Recommendations for current user");
        TextArea promptArea = new TextArea();
        promptArea.setWidthFull();
        promptArea.setLabel("Prompt");
        promptArea.setPlaceholder("Type your prompt");
        Button sendButton = new Button("Send");
        HorizontalLayout promptBar = new HorizontalLayout(promptArea, sendButton);
        promptBar.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.END);
        promptBar.setFlexGrow(1, promptArea);
        promptBar.setWidthFull();
        sendButton.addClickListener(event -> {
            String prompt = promptArea.getValue().trim();
            if (prompt.isEmpty()) {
                Notification.show("Please, provide a non-empty prompt.");
                return;
            }
            bot.execute(new RecommendationsCommand(), prompt);
        });
        return new VerticalLayout(header, promptBar);
    }
}
