package ai.neodatagroup.themis.chat;

import ai.neodatagroup.themis.client.ApiClient;
import ai.neodatagroup.themis.client.ApiException;
import ai.neodatagroup.themis.client.Configuration;
import ai.neodatagroup.themis.client.api.SourcesApi;
import ai.neodatagroup.themis.client.model.Source;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.apache.commons.lang3.NotImplementedException;

import java.util.List;

public class ListSourcesCommand extends Command {
    public ListSourcesCommand(Bot bot) {
        super(bot, "List sources");
    }

    public void execute() {
        ApiClient client = Configuration.getDefaultApiClient();
        SourcesApi api = new SourcesApi(client);
        try {
            List<Source> sources = api.listSources();

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
    }
}
