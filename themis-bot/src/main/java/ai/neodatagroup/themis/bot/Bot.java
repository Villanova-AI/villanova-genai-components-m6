package ai.neodatagroup.themis.bot;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.*;

@Route("")
public class Bot extends VerticalLayout {
    private final VerticalLayout container = new VerticalLayout();
    private final Deque<Component> components = new ArrayDeque<>();
    private int counter = 0;

    protected Deque<Menu> menus;
    private final Menu mainMenu;

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
        H1 header = new H1("Themis Bot");
        add(header, container);
        setFlexGrow(1, container);

        // Show main menu
        menus = new ArrayDeque<>();
        mainMenu = new Menu(
            "Main menu",
            """
            I am Themis Bot, I can provide you with personalized recommendations. You may also configure the underlying infrastructure that I use to provide you with recommendations.
            
            These are your options:
            
            - **Sources** allows you to set up sources of items to recommend.
            - **Items** allows you to edit the set of items that I may recommend to you.
            - **Jobs** allows you to manage jobs on my server, enabling you to:
              - automatically populate the items from the sources;
              - automatically compute semantic embeddings modelling the items;
              - automatically generate machine learning datasets for my recommendation models;
              - automatically train my recommendation models;
              - automatically test my recommendation models.
            - **Datasets** allows you to manage my machine learning datasets.
            - **Models** allows you to manage my recommendation models.
            - **Users** allows you to manage users, and set the current user.
            - **Recommendations** allows you to receive personalized recommendations for the current user.
            """,
            new SourcesCommand(),
            new ItemsCommand(),
            new JobsCommand(),
            new DatasetsCommand(),
            new ModelsCommand(),
            new UsersCommand(),
            new InputForRecommendationsCommand()
        );
        new Push(mainMenu).execute(this);
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
            menus.clear();
            new Push(mainMenu).execute(this);
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

        int MAX_COMPONENTS = 50;
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
            addComponent(menus.peek().getComponent(this));
        }
    }
}
