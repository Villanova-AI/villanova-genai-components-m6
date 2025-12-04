package ai.villanova.themis.bot;

import ai.villanova.themis.bot.api.SourcesApi;
import ai.villanova.themis.bot.model.Source;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;

import java.util.ArrayList;
import java.util.List;

public class SourcesCommand extends Command {
    private static final Source NEW_PLACEHOLDER = new Source();
    static {
        NEW_PLACEHOLDER.setName("New source ...");
    }

    Source activeSource;

    List<Source> sources;

    private final SourcesApi api;

    public SourcesCommand() {
        super("Sources");
        this.activeSource = null;
        this.sources = new ArrayList<>();
        this.sources.add(NEW_PLACEHOLDER);
        this.api = new SourcesApi(Configuration.getDefaultApiClient());
        try {
            this.sources.addAll(api.listSources(0, 10));
        } catch (Exception ignored) {}
    }

    @Override
    public Operation execute(Bot bot, Object... values) {
        super.validate(values);
        bot.addComponent(new CRUD(bot));
        return new Noop();
    }

    private class CRUD extends VerticalLayout {
        public CRUD(Bot bot) {
            H3 title = new H3("Manage Sources");

            Button backButton = new Button("Back");
            backButton.addClickListener(event -> bot.execute(new PeekCommand()));

            TextField searchField = new TextField("Search");
            searchField.setWidthFull();

            TextField nameField = new TextField("Name");
            nameField.setWidthFull();
            nameField.setReadOnly(true);
            TextArea descriptionArea = new TextArea("Description");
            descriptionArea.setWidthFull();
            descriptionArea.setMinRows(5);
            descriptionArea.setMaxRows(5);
            descriptionArea.setReadOnly(true);
            ComboBox<Source.TypeEnum> typeComboBox = new ComboBox<>("Type");
            typeComboBox.setItems(Source.TypeEnum.values());
            typeComboBox.setReadOnly(true);
            TextField urlField = new TextField("URL");
            urlField.setWidthFull();
            urlField.setReadOnly(true);
            VerticalLayout inputArea = new VerticalLayout(nameField, descriptionArea, typeComboBox, urlField);
            inputArea.getStyle().set("border", "1px solid #ccc");
            Button saveButton = new Button("Save");
            saveButton.setEnabled(false);
            Button cancelButton = new Button("Cancel");
            cancelButton.setEnabled(false);
            HorizontalLayout actionButtons = new HorizontalLayout(saveButton, cancelButton);
            actionButtons.setWidthFull();
            actionButtons.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
            actionButtons.getStyle().set("flex", "1");
            VerticalLayout editor = new VerticalLayout(inputArea, actionButtons);
            editor.setSizeFull();
            editor.setMargin(false);
            editor.setPadding(false);
            editor.setAlignItems(FlexComponent.Alignment.START);

            Grid<Source> grid = new Grid<>(Source.class, false);
            grid.addColumn(Source::getId).setHeader("ID").setAutoWidth(true);
            grid.addColumn(new ComponentRenderer<Span, Source>(source -> {
                if (source == NEW_PLACEHOLDER) {
                    Span span = new Span(source.getName());
                    span.getStyle()
                        .set("color", "#aaa")
                        .set("fontStyle", "italic");
                    return span;
                } else {
                    return new Span(source.getName());
                }
            })).setHeader("Name").setAutoWidth(true);
            grid.addColumn(new ComponentRenderer<HorizontalLayout, Source>(source -> {
                if (source == NEW_PLACEHOLDER) {
                    Button createButton = new Button("Create");
                    createButton.addClickListener(event -> {
                        nameField.clear();
                        descriptionArea.clear();
                        typeComboBox.clear();
                        urlField.clear();
                        nameField.setReadOnly(false);
                        descriptionArea.setReadOnly(false);
                        typeComboBox.setReadOnly(false);
                        urlField.setReadOnly(false);
                        saveButton.setEnabled(true);
                        cancelButton.setEnabled(true);
                        activeSource = null;
                    });
                    return new HorizontalLayout(createButton);
                }

                Button viewButton = new Button("View");
                Button editButton = new Button("Edit");
                Button deleteButton = new Button("Delete");

                viewButton.addClickListener(event -> {
                    nameField.setValue(source.getName());
                    descriptionArea.setValue(source.getDescription());
                    typeComboBox.setValue(source.getType());
                    urlField.setValue(source.getUrl().toString());
                    nameField.setReadOnly(true);
                    descriptionArea.setReadOnly(true);
                    typeComboBox.setReadOnly(true);
                    urlField.setReadOnly(true);
                    saveButton.setEnabled(false);
                    cancelButton.setEnabled(false);
                    activeSource = source;
                });

                editButton.addClickListener(event -> {
                    nameField.setValue(source.getName());
                    descriptionArea.setValue(source.getDescription());
                    typeComboBox.setValue(source.getType());
                    urlField.setValue(source.getUrl().toString());
                    nameField.setReadOnly(false);
                    descriptionArea.setReadOnly(false);
                    typeComboBox.setReadOnly(false);
                    urlField.setReadOnly(false);
                    saveButton.setEnabled(true);
                    cancelButton.setEnabled(true);
                    activeSource = source;
                });

                return new HorizontalLayout(editButton, deleteButton);
            }));
            grid.setWidthFull();
            grid.setItems(sources);
            Button previousButton = new Button("Previous");
            Button nextButton = new Button("Next");
            HorizontalLayout navigationButtons = new HorizontalLayout(previousButton, nextButton);
            navigationButtons.setWidthFull();
            navigationButtons.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
            VerticalLayout navigator = new VerticalLayout(grid, navigationButtons);
            navigator.setSizeFull();
            navigator.setMargin(false);
            navigator.setPadding(false);

            cancelButton.addClickListener(event -> {
                Source selected = grid.asSingleSelect().getValue();
                if (selected == NEW_PLACEHOLDER) {
                    nameField.clear();
                    descriptionArea.clear();
                    typeComboBox.clear();
                    urlField.clear();
                    grid.asSingleSelect().clear();
                } else {
                    nameField.setValue(selected.getName());
                    descriptionArea.setValue(selected.getDescription());
                    typeComboBox.setValue(selected.getType());
                    urlField.setValue(selected.getUrl().toString());
                }
                nameField.setReadOnly(true);
                descriptionArea.setReadOnly(true);
                typeComboBox.setReadOnly(true);
                urlField.setReadOnly(true);
            });

            HorizontalLayout headerLayout = new HorizontalLayout(title, backButton);
            headerLayout.setWidthFull();
            headerLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
            HorizontalLayout mainLayout = new HorizontalLayout(navigator, editor);
            mainLayout.setSizeFull();
            mainLayout.setAlignItems(FlexComponent.Alignment.STRETCH);
            add(headerLayout, searchField, mainLayout);
        }
    }
}
