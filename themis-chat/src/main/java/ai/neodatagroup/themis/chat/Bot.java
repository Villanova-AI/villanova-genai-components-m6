package ai.neodatagroup.themis.chat;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.apache.commons.lang3.NotImplementedException;

import java.util.*;

@Route("")
public class Bot extends VerticalLayout {
    private final H1 header = new H1("Themis Bot");
    private final VerticalLayout container = new VerticalLayout();
    private final Deque<Component> components = new ArrayDeque<>();
    private int counter = 0;
    private final int MAX_COMPONENTS = 50;

    protected Deque<Menu> menus;

    public Bot() {
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
        menus = new ArrayDeque<>();
        new Push(new MainMenu(this)).execute(this);
    }

    public void execute(Command command, Object... values) {
        try {
            for (Component component : components) {
                component.getElement().setEnabled(false);
            }
            Operation operation = command.execute(this, values);
            operation.execute(this);
        } catch (Exception e) {
            // Display exception
            addComponent(new Span("Encountered exception: " + e.getMessage()));

            // Recovery
            menus = new ArrayDeque<>();
            new Push(new MainMenu(this)).execute(this);
        }
    }

    protected void addComponent(Component component) {
        counter++;
        Span counterSpan = new Span(String.valueOf(counter));
        counterSpan.getStyle()
            .set("display", "inline-block")
            .set("width", "24px")
            .set("height", "24px")
            .set("border-radius", "50%")
            .set("background-color", "#007bff")
            .set("color", "white")
            .set("text-align", "center")
            .set("line-height", "24px")
            .set("font-weight", "bold");
        Hr separator = new Hr();
        VerticalLayout wrapper = new VerticalLayout(counterSpan, component, separator);
        container.add(wrapper);
        components.add(wrapper);
        if (components.size() > MAX_COMPONENTS) {
            Component removed = components.removeFirst();
            container.remove(removed);
        }
        container.getElement().executeJs("this.scrollTop = this.scrollHeight;");
    }

    protected void showMenuOrExit() {
        if (menus.isEmpty()) {
            throw new IllegalStateException();
        }
        else {
            addComponent(menus.peek().getComponent());
        }
    }
}
