package ai.neodatagroup.themis.chat;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Menu {
    private final String header;
    private final List<String> paragraphs;
    private final List<Command> commands;

    private Menu(Builder builder) {
        this.header = builder.header;
        this.paragraphs = List.copyOf(builder.paragraphs);
        this.commands = List.copyOf(builder.commands);
    }

    public Component getComponent(Bot bot) {
        VerticalLayout layout = new VerticalLayout();

        layout.add(new H3(header));

        FlexLayout flex = new FlexLayout();
        flex.setFlexWrap(FlexLayout.FlexWrap.WRAP);
        flex.setFlexDirection(FlexLayout.FlexDirection.ROW);
        flex.setAlignItems(FlexComponent.Alignment.START);
        flex.setJustifyContentMode(FlexComponent.JustifyContentMode.START);
        flex.getStyle()
            .set("border", "2px solid #ccc")
            .set("border-radius", "10px")
            .set("padding", "20px")
            .set("margin", "10px auto 10px 0")
            .set("width", "fit-content")
            .set("gap", "0.5rem");
        for (Command command : commands) {
            Button button = new Button(command.getName(), event -> {
                bot.execute(command);
            });
            flex.add(button);
        }
        layout.add(flex);

        return layout;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String header;

        private final List<String> paragraphs = new ArrayList<>();
        private final List<Command> commands = new ArrayList<>();

        private Builder() {}

        /** Sets the header (must be non-blank). */
        public Builder header(String header) {
            this.header = requireNonBlank(header, "header");
            return this;
        }

        /** Adds a single paragraph (non-null). */
        public Builder paragraph(String paragraph) {
            paragraphs.add(Objects.requireNonNull(paragraph, "paragraph must not be null"));
            return this;
        }

        /** Adds multiple paragraphs (each non-null). */
        public Builder paragraphs(String... paragraphs) {
            Objects.requireNonNull(paragraphs, "paragraphs must not be null");
            for (String p : paragraphs) {
                paragraph(p);
            }
            return this;
        }

        /** Adds a single command (non-null). */
        public Builder command(Command command) {
            commands.add(Objects.requireNonNull(command, "command must not be null"));
            return this;
        }

        /** Adds multiple commands (each non-null). */
        public Builder commands(Command... commands) {
            Objects.requireNonNull(commands, "commands must not be null");
            for (Command c : commands) {
                command(c);
            }
            return this;
        }

        /** Removes all paragraphs (optional helper). */
        public Builder clearParagraphs() {
            paragraphs.clear();
            return this;
        }

        /** Removes all commands (optional helper). */
        public Builder clearCommands() {
            commands.clear();
            return this;
        }

        /** Builds an immutable Menu instance. */
        public Menu build() {
            if (header == null || header.isBlank()) {
                throw new IllegalStateException("header must be non-blank");
            }
            return new Menu(this);
        }

        private static String requireNonBlank(String s, String name) {
            Objects.requireNonNull(s, name + " must not be null");
            if (s.trim().isEmpty()) {
                throw new IllegalArgumentException(name + " must not be blank");
            }
            return s;
        }
    }
}
