package ai.neodatagroup.themis.chat;

import ai.neodatagroup.themis.client.model.Item;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.net.URI;
import java.time.Instant;

public class RecommendationsCommand extends Command {
    public RecommendationsCommand() {
        super("Recommendations");
    }

    @Override
    public Operation execute(Bot bot, Object... values) {
        super.validate(values);

        H3 header = new H3("Recommendations for current user");
        Grid<Item> grid = new Grid<>();
        grid.addColumn(Item::getTitle).setHeader("Title");
        grid.addColumn(Item::getDescription).setHeader("Description");
        grid.addComponentColumn(item_ -> {
            Anchor anchor = new Anchor(item_.getUrl().toString(), "Open Link");
            anchor.setTarget("_blank");
            return anchor;
        }).setHeader("Link");
        final int N = 3;
        Item[] items = new Item[N];
        String[] urls = {
            "https://www.amazon.com/Vintage-Typewriter-Portable-Processor-Enthusiasts/dp/B0F83NBZ12/ref=sr_1_1_sspa?crid=3EDD3GJ4C8ZIR&dib=eyJ2IjoiMSJ9.nydqRNHnIgjesskhTncSWRn1Z08jO9hH5hcvSd9RFhCcic343ClcLCmm5eYlSmpSrY20gpNuGUiezkrKbaDUtu8weHZfhwdNaXm8WQxrI7y9NaL-PFQZPs57ZWRvSpjIDvJ__0wYYQ7pu-KwJvlmH1mx7k_Ho4cqm2d4C2B5wYEofhDL8l1x4TGNkjrvyZnXBJgvj6SIzPG2Ovy1KolxPYciaIy8U3pJ19G36GMyVGI.HwwC-HhlXag0NsXi9fvThwNtZT9Lz2qQn014GEaOW5A&dib_tag=se&keywords=typewriter&qid=1764334306&sprefix=typewriter%2Caps%2C473&sr=8-1-spons&sp_csd=d2lkZ2V0TmFtZT1zcF9hdGY&psc=1",
            "https://www.amazon.com/Samsung-Computer-Touchscreen-Speakers-NP750QHA-KA1US/dp/B0DSLNMY6M/ref=sr_1_1_sspa?crid=1JVLTJCV5T3S4&dib=eyJ2IjoiMSJ9.8h14joHyf6QLrsy0A_ITBSpPZ4QQoGMSK6s7lsYlcrjPXYytJq_oXaHOK5_sNvU4QV-P3PZJEmQhCNceNUO1lyw4MsOuUDdaUHFjoGLTSTyXJaNGlD2TDWXVr9f2X-B1wZ3uOm7sHU8yEUOV3IcmYWMZJ8MIr8sMxr3x3euDkYv0gGwdhItLWWLZHqsBhOTZeBvlc2h6RdXVSF0MuavZL1OmqpnJuP9idcQfi-CfswQ.AzriAHFQCoHxx2Cy2529YSfDfvb8Ya_BHCWBGKaSKfQ&dib_tag=se&keywords=laptop&qid=1764334361&sprefix=laptop%2Caps%2C252&sr=8-1-spons&sp_csd=d2lkZ2V0TmFtZT1zcF9hdGY&th=1",
            "https://www.amazon.com/AmazonBasics-Matte-Keyboard-QWERTY-Layout/dp/B07WJ5D3H4/ref=sr_1_1_ffob_sspa?crid=W411CNRLO1RE&dib=eyJ2IjoiMSJ9.YCBWI6TO4uPtB5tSnkbXA1XNhjqzZn4OMDxatc1PGSi8LPG1jEtL4zShazHuxkYhls-HRYVP592zqjR_KiLhtNDzgL-3jZBYVpZJGWxvM7Ezt8cmKg4e0B7hEAAyn4pe3icovLUfy6GGUdaUDXrj-T0Xh2iKLpfR4Zv7T5TsMDrLl1mZXWn5obf--pjrWMJADYgq1h_Bt_aLNdo0tyiwG5L2DNMuFGQ7Qpp8l--22hM.h6W5PLRjdgWeDv9ntb-WQXQDXoBjFGbB7A2qwlgwkwA&dib_tag=se&keywords=keyboard&qid=1764334439&sprefix=keyboard%2Caps%2C291&sr=8-1-spons&sp_csd=d2lkZ2V0TmFtZT1zcF9hdGY&psc=1"
        };
        for (int i = 0; i < N; i++) {
            Item item = new Item((long) i, 1, Instant.now(), Instant.now());
            item.setTitle("Recommended item #" + i);
            item.setDescription("Description for item #" + i);
            item.setUrl(URI.create(urls[i]));
            items[i] = item;
        }
        grid.setItems(items);
        Button backButton = new Button("Back");
        backButton.addClickListener(event -> {
            bot.execute(new PeekCommand());
        });

        VerticalLayout layout = new VerticalLayout(header, grid, backButton);
        bot.addComponent(layout);

        return new Noop();
    }
}
