package ai.villanova.themis.bot;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;

import ai.villanova.themis.bot.model.User;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class ListUsersCommand extends Command {
    public ListUsersCommand() {
        super("List users");
    }

    @Override
    public Operation execute(Bot bot, Object... values) {
        super.validate(values);
        bot.addComponent(makeComponent(bot));
        return new Noop();
    }

    private Component makeComponent(Bot bot, Object... values) {
        H3 header = new H3("List of users");
        AtomicLong currentUserId = new AtomicLong(3L);
        User[] users = new User[4];
        users[0] = new User(1L, Instant.now(), Instant.now());
        users[0].setName("Mickey Mouse");
        users[1] = new User(2L, Instant.now(), Instant.now());
        users[1].setName("Goofy");
        users[2] = new User(3L, Instant.now(), Instant.now());
        users[2].setName("Donald Duck");
        users[3] = new User(4L, Instant.now(), Instant.now());
        users[3].setName("Daisy");
        Grid<User> grid = new Grid<>();
        grid.addColumn(new ComponentRenderer<>(user -> {
            if (user.getId() != null && user.getId().equals(currentUserId.get())) {
                Icon check = VaadinIcon.CHECK.create();
                check.setColor("var(--lumo-success-color)");
                check.getStyle().set("margin-inline", "0.25rem");
                return check;
            }
            // Empty placeholder to keep column alignment consistent
            return new Span();
        })).setHeader("").setAutoWidth(true).setFlexGrow(0);
        grid.addColumn(User::getId).setHeader("ID");
        grid.addColumn(User::getName).setHeader("Name");
        grid.addColumn(User::getCreatedAt).setHeader("Created at");
        grid.addColumn(User::getUpdatedAt).setHeader("Updated at");
        grid.addColumn(new ComponentRenderer<>(user -> {
            Button setCurrentButton = new Button("Set current");
            setCurrentButton.addClickListener(event -> {
                currentUserId.set(user.getId()); // Update the current user
                grid.getDataProvider().refreshAll(); // Refresh grid to update check icon
            });

            Button deleteButton = new Button("Delete");
            deleteButton.addClickListener(event -> {
                if (user.getId() != null && user.getId().equals(currentUserId.get())) {
                    Notification.show("Current user may not be deleted.");
                    return;
                }
                List<User> updatedUsers = Arrays.stream(users)
                    .filter(u -> !u.getId().equals(user.getId()))
                    .toList();
                grid.setItems(updatedUsers); // Update grid items
            });

            return new HorizontalLayout(setCurrentButton, deleteButton);
        })).setHeader("Actions").setAutoWidth(true);

        grid.setItems(users);
        Button backButton = new Button("Back");
        backButton.addClickListener(event -> {
           bot.execute(new PeekCommand());
        });
        return new VerticalLayout(header, grid, backButton);
    }
}
