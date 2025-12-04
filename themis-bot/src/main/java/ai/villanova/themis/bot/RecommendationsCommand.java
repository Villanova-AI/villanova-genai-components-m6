package ai.villanova.themis.bot;

import ai.villanova.themis.bot.model.Item;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.net.URI;
import java.time.Instant;

public class RecommendationsCommand extends Command {
    public RecommendationsCommand()  {
        super("Recommendations", new Argument("prompt", String.class));
    }

    @Override
    public Operation execute(Bot bot, Object... values) {
        super.validate(values);
        String prompt = (String) values[0];

        H3 header = new H3("Recommendations for current user");
        Span label = new Span("Prompt:");
        label.getStyle().set("font-weight", "bold");
        Paragraph promptParagraph = new Paragraph(label, new Span(" " + prompt));
        Paragraph explanation = new Paragraph("Because in the past you bought Samsumg smartphones, and you are now seeking a laptop, I recommend to you a new Galaxy Book laptop with additional RAM. Moreover, because you like vintage keyboards, I looked for a model you may like.");
        Grid<Item> grid = new Grid<>();
        grid.addColumn(Item::getTitle).setHeader("Title");
        grid.addColumn(Item::getDescription).setHeader("Description");
        grid.addComponentColumn(item_ -> {
            Anchor anchor = new Anchor(item_.getUrl().toString(), "Open Link");
            anchor.setTarget("_blank");
            return anchor;
        }).setHeader("Link");
        final int N = 2;
        Item[] items = new Item[N];
        String[] urls = {
            "https://www.amazon.com/Samsung-Computer-Touchscreen-Speakers-NP750QHA-KA1US/dp/B0DSLNMY6M/ref=sr_1_1_sspa?crid=1JVLTJCV5T3S4&dib=eyJ2IjoiMSJ9.8h14joHyf6QLrsy0A_ITBSpPZ4QQoGMSK6s7lsYlcrjPXYytJq_oXaHOK5_sNvU4QV-P3PZJEmQhCNceNUO1lyw4MsOuUDdaUHFjoGLTSTyXJaNGlD2TDWXVr9f2X-B1wZ3uOm7sHU8yEUOV3IcmYWMZJ8MIr8sMxr3x3euDkYv0gGwdhItLWWLZHqsBhOTZeBvlc2h6RdXVSF0MuavZL1OmqpnJuP9idcQfi-CfswQ.AzriAHFQCoHxx2Cy2529YSfDfvb8Ya_BHCWBGKaSKfQ&dib_tag=se&keywords=laptop&qid=1764334361&sprefix=laptop%2Caps%2C252&sr=8-1-spons&sp_csd=d2lkZ2V0TmFtZT1zcF9hdGY&th=1",
            "https://www.amazon.com/Vintage-Typewriter-Portable-Processor-Enthusiasts/dp/B0F83NBZ12/ref=sr_1_1_sspa?crid=3EDD3GJ4C8ZIR&dib=eyJ2IjoiMSJ9.nydqRNHnIgjesskhTncSWRn1Z08jO9hH5hcvSd9RFhCcic343ClcLCmm5eYlSmpSrY20gpNuGUiezkrKbaDUtu8weHZfhwdNaXm8WQxrI7y9NaL-PFQZPs57ZWRvSpjIDvJ__0wYYQ7pu-KwJvlmH1mx7k_Ho4cqm2d4C2B5wYEofhDL8l1x4TGNkjrvyZnXBJgvj6SIzPG2Ovy1KolxPYciaIy8U3pJ19G36GMyVGI.HwwC-HhlXag0NsXi9fvThwNtZT9Lz2qQn014GEaOW5A&dib_tag=se&keywords=typewriter&qid=1764334306&sprefix=typewriter%2Caps%2C473&sr=8-1-spons&sp_csd=d2lkZ2V0TmFtZT1zcF9hdGY&psc=1"
        };
        items[0] = new Item(1L, 1, Instant.now(), Instant.now());
        items[0].setTitle("Galaxy Book");
        items[0].setDescription("Intel i7 processor, 32Gb RAM");
        items[0].setUrl(URI.create(urls[0]));
        items[1] = new Item(2L, 1, Instant.now(), Instant.now());
        items[1].setTitle("Vintage keyboard");
        items[1].setDescription("American layout");
        items[1].setUrl(URI.create(urls[1]));
        grid.setItems(items);
        Button backButton = new Button("Back");
        backButton.addClickListener(event -> {
            bot.execute(new PeekCommand());
        });

        VerticalLayout layout = new VerticalLayout(header, promptParagraph, explanation, grid, backButton);
        bot.addComponent(layout);

        return new Noop();
    }
}
