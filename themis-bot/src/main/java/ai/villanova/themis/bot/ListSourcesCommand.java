package ai.villanova.themis.bot;

import ai.villanova.themis.bot.api.SourcesApi;
import ai.villanova.themis.bot.model.Source;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.List;

public class ListSourcesCommand extends Command {
    public ListSourcesCommand() {
        super("List sources");
    }

    @Override
    public Operation execute(Bot bot, Object... values) {
        super.validate(values);

        ApiClient client = Configuration.getDefaultApiClient();
        SourcesApi api = new SourcesApi(client);
        try {
            List<Source> sources = api.listSources(0, 20);

            // Create a Vaadin component listing the retrieved sources
            VerticalLayout layout = new VerticalLayout();
            layout.add(new H3("Available sources"));
            if (sources.isEmpty()) {
                layout.add(new Span("No sources found."));
            }
            else {
                for (Source source : sources) {
                    layout.add(new Span(source.getUrl().toString()));
                }
            }
            bot.addComponent(layout);
        }
        catch (ApiException e) {
            // Create a Vaadin component reporting the exception
            VerticalLayout errorLayout = new VerticalLayout();
            errorLayout.add(new H3("Error retrieving sources"));
            errorLayout.add(new Span("Status code: " + e.getCode()));
            errorLayout.add(new Span("Message: " + e.getMessage()));
            bot.addComponent(errorLayout);
        }
        return new Peek();
    }
}
