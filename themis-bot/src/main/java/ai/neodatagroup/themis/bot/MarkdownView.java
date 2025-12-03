package ai.neodatagroup.themis.bot;

import com.vaadin.flow.component.html.Div;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.data.MutableDataSet;

public class MarkdownView extends Div {
    private final Parser parser;
    private final HtmlRenderer renderer;

    public MarkdownView() {
        MutableDataSet options = new MutableDataSet();
        parser = Parser.builder(options).build();
        renderer = HtmlRenderer.builder(options).build();
    }

    public void setMarkdown(String markdown) {
        Document document = parser.parse(markdown == null ? "" : markdown);
        String html = renderer.render(document);
        getElement().executeJs("this.innerHTML = $0", html);
    }
}
