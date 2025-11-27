package ai.neodatagroup.themis.chat;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class Menu {
    private final String header;

    protected final Bot bot;

    private final Command[] commands;

    public Menu(String header, Bot bot, Command... commands) {
        this.header = header;
        this.bot = bot;
        this.commands = commands;
    }

    public Component getComponent() {
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
}
