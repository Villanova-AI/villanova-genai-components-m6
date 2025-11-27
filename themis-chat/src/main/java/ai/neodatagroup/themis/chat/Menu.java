package ai.neodatagroup.themis.chat;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class Menu {
    private final Command[] commands;

    public Menu(Command... commands) {
        this.commands = commands;
    }

    public Component getComponent() {
        VerticalLayout layout = new VerticalLayout();
        for (Command command : commands) {
            Button button = new Button(command.getName(), event -> command.execute());
            layout.add(button);
        }
        return layout;
    }
}
