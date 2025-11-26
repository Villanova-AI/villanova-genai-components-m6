package ai.neodatagroup.themis.chat;

import ai.neodatagroup.themis.client.ApiClient;
import ai.neodatagroup.themis.client.ApiException;
import ai.neodatagroup.themis.client.Configuration;
import ai.neodatagroup.themis.client.api.SourcesApi;
import ai.neodatagroup.themis.client.model.Source;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.messages.MessageInput;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.LinkedList;
import java.util.List;

@Route("")
public class MainView extends VerticalLayout {
    private final H1 header = new H1("Themis chat");
    private final VerticalLayout container = new VerticalLayout();
    private final MessageInput messageInput = new MessageInput();

    private final List<HorizontalLayout> items = new LinkedList<>();
    private final int MAX_ITEMS = 50;

    public MainView() {
        ApiClient client = Configuration.getDefaultApiClient();
        SourcesApi sourceApi = new SourcesApi(client);

        setSizeFull();
        setPadding(true);
        setSpacing(true);
        getStyle()
            .set("max-width", "800px")
            .set("margin", "0 auto");

        // Container
        container.setWidthFull();
        container.setPadding(false);
        container.setSpacing(false);
        container.getStyle().set("overflow-y", "auto");
        container.getStyle().set("background", "#fafafa");
        container.getStyle().set("border", "1px solid #e5e7eb");
        container.getStyle().set("border-radius", "8px");
        container.getStyle().set("box-shadow", "0 1px 2px rgba(0,0,0,0.04)");

        // Message messageInput
        messageInput.setWidthFull();
        messageInput.getStyle()
            .set("min-height", "120px")
            .set("max-height", "120px");
        messageInput.addSubmitListener(event -> {
            String userInput = event.getValue();
            if (userInput != null && !userInput.trim().isEmpty()) {
                addItem(true, new Span(userInput));

                // Simulate bot response
                Component botResponse = new Span("You typed: " + userInput);
                addItem(false, botResponse);

                StringBuilder builder = new StringBuilder();
                builder.append("foo");
                try {
                    List<Source> sources = sourceApi.listSources();
                    for (Source source : sources) {
                        builder.append(source.getName());
                    }
                }
                catch (ApiException e) {
                    builder.append("error");
                }
                botResponse = new Span(builder.toString());
                addItem(false, botResponse);
            }
        });

        // Add components
        add(header, container, messageInput);
        setFlexGrow(1, container);
    }

    private void addItem(boolean isUser, Component content) {
        // Bubble styling
        Div bubble = new Div(content);
        bubble.getStyle()
            .set("display", "inline-block")
            .set("max-width", "75%")
            .set("padding", "8px 12px")
            .set("border-radius", isUser ? "14px 14px 4px 14px" : "14px 14px 14px 4px")
            .set("background", isUser ? "#e0edff" : "#f1f5f9")
            .set("color", "#111827")
            .set("word-wrap", "break-word")
            .set("white-space", "pre-wrap"); // keep user newlines

        // Row container for alignment
        HorizontalLayout row = new HorizontalLayout();
        row.setWidthFull();
        row.setPadding(false);
        row.setSpacing(false);

        // Add sender label (optional small caption)
        Span sender = new Span(isUser ? "User" : "Bot");
        sender.getStyle()
            .set("font-size", "12px")
            .set("color", "#6b7280")
            .set("margin", isUser ? "0 8px 2px 0" : "0 0 2px 8px")
            .set("display", "block");

        // Column groups to stack label and bubble
        VerticalLayout group = new VerticalLayout(sender, bubble);
        group.setWidthFull();
        group.setPadding(false);
        group.setSpacing(false);
        group.getStyle().set("gap", "4px");
        group.setDefaultHorizontalComponentAlignment(
            isUser ? FlexComponent.Alignment.END : Alignment.START
        );

        row.add(group);
        container.add(row);

        // Track and evict old items
        items.add(row);
        if (items.size() > MAX_ITEMS) {
            HorizontalLayout removedItem = items.removeFirst();
            container.remove(removedItem);
        }

        // Auto-scroll to bottom after adding
        container.getElement().executeJs("this.scrollTop = this.scrollHeight;");
    }
}
