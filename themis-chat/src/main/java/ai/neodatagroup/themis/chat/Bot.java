package ai.neodatagroup.themis.chat;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.apache.commons.lang3.NotImplementedException;

import java.util.*;

@Route("")
public class Bot extends VerticalLayout {
    private final H1 header = new H1("Themis chat");
    private final VerticalLayout container = new VerticalLayout();
    private final Deque<Component> components = new ArrayDeque<>();
    private final int MAX_COMPONENTS = 50;

    protected Deque<Menu> menus;

    public Bot() {
        // Menu stack
        menus = new ArrayDeque<>();
        menus.push(new MainMenu(this));

        // Style
        setSizeFull();
        setPadding(true);
        setSpacing(true);

        // Container
        container.setWidthFull();
        container.setPadding(false);
        container.setSpacing(false);
        container.getStyle().set("overflow-y", "auto");
        container.getStyle().set("background", "#fafafa");
        container.getStyle().set("border", "1px solid #e5e7eb");
        container.getStyle().set("border-radius", "8px");
        container.getStyle().set("box-shadow", "0 1px 2px rgba(0,0,0,0.04)");

        // Add components
        add(header, container);
        setFlexGrow(1, container);

        // Show main menu
        showMenuOrExit();
    }

    public void addComponent(Component component) {
        container.add(component);
        components.add(component);
        if (components.size() > MAX_COMPONENTS) {
            Component removed = components.removeFirst();
            container.remove(removed);
        }
    }

    protected void showMenuOrExit() {
        if (menus.isEmpty()) {
            // Somehow close the Vaadin server
            throw new NotImplementedException();
        }
        else {
            addComponent(menus.peek().getComponent());
        }
    }
}
