package ai.neodatagroup.themis.bot;

import ai.neodatagroup.themis.bot.model.Item;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

import java.net.URI;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class EditCurrentUserCommand extends Command {
    public EditCurrentUserCommand() {
        super("Edit current user");
    }

    @Override
    public Operation execute(Bot bot, Object... values) {
        super.validate(values);
        bot.addComponent(makeComponent(bot));
        return new Noop();
    }

    private Component makeComponent(Bot bot, Object... values) {
        H3 header = new H3("Current user");
        TextArea instructionsArea = new TextArea();
        instructionsArea.setLabel("Instructions")   ;
        instructionsArea.setValue("I like vintage typewriters.");
        H5 gridHeader = new H5("List of items the current user has interacted with");
        List<Item> items = new ArrayList<>();
        Item item = new Item(1L, 1, Instant.now(), Instant.now());
        item.setTitle("Typewriter");
        item.setUrl(URI.create("https://www.amazon.com/Vintage-Typewriter-Portable-Processor-Enthusiasts/dp/B0F83NBZ12/ref=sr_1_1_sspa?crid=3EDD3GJ4C8ZIR&dib=eyJ2IjoiMSJ9.nydqRNHnIgjesskhTncSWRn1Z08jO9hH5hcvSd9RFhCcic343ClcLCmm5eYlSmpSrY20gpNuGUiezkrKbaDUtu8weHZfhwdNaXm8WQxrI7y9NaL-PFQZPs57ZWRvSpjIDvJ__0wYYQ7pu-KwJvlmH1mx7k_Ho4cqm2d4C2B5wYEofhDL8l1x4TGNkjrvyZnXBJgvj6SIzPG2Ovy1KolxPYciaIy8U3pJ19G36GMyVGI.HwwC-HhlXag0NsXi9fvThwNtZT9Lz2qQn014GEaOW5A&dib_tag=se&keywords=typewriter&qid=1764334306&sprefix=typewriter%2Caps%2C473&sr=8-1-spons&sp_csd=d2lkZ2V0TmFtZT1zcF9hdGY&psc=1"));
        items.add(item);
        item = new Item(2L, 1, Instant.now(), Instant.now());
        item.setTitle("Smartphone");
        item.setUrl(URI.create("https://www.amazon.com/SAMSUNG-Unlocked-Smartphone-Charging-Expandable/dp/B0DLHNWHRF/ref=sr_1_2?crid=1HGWLQ8A659GB&dib=eyJ2IjoiMSJ9.aTLiJgI9vuILJle2HYysGops57eNWeaWDUJS42rqqfiy8_ejjFqJkCTGFJQj1eS1ieIV2v5hu34rSpFbIRq0Yg-PQrkvM28sShKLf0z2mmH9c9DPDjnH-mT4DQMWMbRAlWC7MLjeadAB15VsXoNj-xuEHFFmHPa4ZbmP0w3wVpMkAG4z3_EbbMzTdyl84bMNMIXmp-Xz0IzD1WLjSKgMGsP2Tsxs0c_PC3bJypt5lMk.OtVybwTRti6FyY8JKVpu9_3gr5pLIEhwYcU5cepYo8E&dib_tag=se&keywords=samsung%2Bgalaxy%2Ba16&qid=1764587597&sprefix=samsung%2Bgalaxy%2Ba16%2Caps%2C239&sr=8-2&th=1"));
        items.add(item);
        item = new Item(3L, 1, Instant.now(), Instant.now());
        item.setTitle("Keyboard");
        item.setUrl(URI.create("https://www.amazon.com/AmazonBasics-Matte-Keyboard-QWERTY-Layout/dp/B07WJ5D3H4/ref=sr_1_1_ffob_sspa?crid=W411CNRLO1RE&dib=eyJ2IjoiMSJ9.YCBWI6TO4uPtB5tSnkbXA1XNhjqzZn4OMDxatc1PGSi8LPG1jEtL4zShazHuxkYhls-HRYVP592zqjR_KiLhtNDzgL-3jZBYVpZJGWxvM7Ezt8cmKg4e0B7hEAAyn4pe3icovLUfy6GGUdaUDXrj-T0Xh2iKLpfR4Zv7T5TsMDrLl1mZXWn5obf--pjrWMJADYgq1h_Bt_aLNdo0tyiwG5L2DNMuFGQ7Qpp8l--22hM.h6W5PLRjdgWeDv9ntb-WQXQDXoBjFGbB7A2qwlgwkwA&dib_tag=se&keywords=keyboard&qid=1764334439&sprefix=keyboard%2Caps%2C291&sr=8-1-spons&sp_csd=d2lkZ2V0TmFtZT1zcF9hdGY&psc=1"));
        items.add(item);
        Grid<Item> grid = new Grid<>();
        grid.addColumn(Item::getId).setHeader("ID");
        grid.addColumn(Item::getTitle).setHeader("Title");
        grid.addComponentColumn(item_ -> {
            Anchor anchor = new Anchor(item_.getUrl().toString(), "Open Link");
            anchor.setTarget("_blank");
            return anchor;
        }).setHeader("Link");
        grid.addComponentColumn(item_ -> {
            Button deleteButton = new Button("Delete");
            deleteButton.addClickListener(event -> {
                items.remove(item_);
                grid.getDataProvider().refreshAll();
            });
            return deleteButton;
        }).setHeader("Actions");
        grid.setItems(items);
        TextField itemIdField = new TextField("Item ID");
        Button addButton = new Button("Add");
        addButton.addClickListener(event -> {
            try {
                long itemId = Long.parseLong(itemIdField.getValue());
                Item item_ = new Item(itemId, 1, Instant.now(), Instant.now());
                item_.setTitle("Added item from database");
                item_.setUrl(URI.create("amazon.com/Voova-Briefcase-Expandable-Multi-function-Waterproof/dp/B088T1S8DT/ref=sr_1_1_sspa?crid=2H4SY9GCOWQN1&dib=eyJ2IjoiMSJ9.v5cHNge3P4Pfgfinf1b1QOkfQVrviqwm4Zs0uAgz2i0b3Wn3NyybOITR-Y5_ZrEwJuo_w-cLHCuIHo4LlJIDHogNi1nA5NBbimEr8y6VixX2DtbjCeYfffpfQpl-Wm8pK8xluZlB3G919rDiqMvvoH6rEBOHFv3OHA-g6qGcY0BWdhKCmM8vOhJ-2oS43pLDN_XtNyNQsp7RYnY-HHOVStPPQW-_Wv5z0HHrSn3QAws.3zxy_qxjTibSYgwDe_UnRZ1YnXc44ZtfiXNZfZgMKFY&dib_tag=se&keywords=laptop+bag&qid=1764336083&sprefix=laptop%2Caps%2C653&sr=8-1-spons&sp_csd=d2lkZ2V0TmFtZT1zcF9hdGY&psc=1"));
                items.add(item_);
                grid.getDataProvider().refreshAll();
            }
            catch (NumberFormatException e) {
                Notification.show("Invalid item ID");
            }
        });
        HorizontalLayout addForm = new HorizontalLayout(itemIdField, addButton);
        addForm.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.END);
        Button backButton = new Button("Back");
        backButton.addClickListener(event -> {
            bot.execute(new PeekCommand());
        });

        return new VerticalLayout(header, instructionsArea, gridHeader, grid, addForm, backButton);
    }
}