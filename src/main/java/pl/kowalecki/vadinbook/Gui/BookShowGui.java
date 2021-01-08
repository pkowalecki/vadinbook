package pl.kowalecki.vadinbook.Gui;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.editor.Editor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.kowalecki.vadinbook.Model.Book;
import pl.kowalecki.vadinbook.Service.BookService;

import java.util.Collection;
import java.util.Collections;
import java.util.WeakHashMap;

@Route("showBook")
public class BookShowGui extends VerticalLayout {

    private BookService bookService;

    @Autowired
    public BookShowGui(BookService bookService){
        this.bookService = bookService;
        Grid<Book> grid = new Grid<>(Book.class);
        Binder<Book> binder = new Binder<>(Book.class);
        Editor<Book> editor = grid.getEditor();
        editor.setBinder(binder);
        editor.setBuffered(true);


        TextField textFieldTitle = new TextField("Tytuł książki");
        binder.forField(textFieldTitle).bind("book_name");
        TextField textFieldAuthor = new TextField("Autor");
        binder.forField(textFieldAuthor).bind("book_author");
        TextField textFieldGenre = new TextField("Gatunek");
        binder.forField(textFieldGenre).bind("book_genre");

        ComboBox comboBoxState = new ComboBox("Stan", "Przeczytane", "Nieprzeczytane", "Do przeczytania");
        comboBoxState.setClearButtonVisible(true);
        Div value = new Div();
        value.setText("Wybierz wartość");
        comboBoxState.addValueChangeListener(event -> {
            if(event.getValue() == null){
                value.setText("Nic nie wybrałeś");
            }else{
                value.setText((String) event.getValue());
            }
        });

        grid.setItems(bookService.getAllBooks());
        grid.removeColumnByKey("id");
        grid.setColumns("book_name", "book_author", "book_genre", "book_act");
        grid.getColumnByKey("book_name").setHeader("Tytuł ksiażki").setEditorComponent(textFieldTitle);
        grid.getColumnByKey("book_author").setHeader("autor").setEditorComponent(textFieldAuthor);
        grid.getColumnByKey("book_genre").setHeader("gatunek").setEditorComponent(textFieldGenre);
        grid.getColumnByKey("book_act").setHeader("stan").setEditorComponent(comboBoxState);
        Collection<Button> editButtons = Collections.newSetFromMap(new WeakHashMap<>());
        Grid.Column<Book> editorColumn = grid.addComponentColumn(book -> {
            Button edit = new Button("Edytuj");
            edit.addClassName("edytuj");
            edit.addClickListener(e -> {
                editor.editItem(book);
            });
            edit.setEnabled(!editor.isOpen());
            editButtons.add(edit);
            return edit;
        });
        editor.addOpenListener(e -> editButtons.stream()
                .forEach(button -> button.setEnabled(!editor.isOpen())));
        editor.addCloseListener(e -> editButtons.stream()
                .forEach(button -> button.setEnabled(!editor.isOpen())));
        Button save = new Button("Zapisz", e -> editor.save());
        save.addClassName("save");
        Button cancel = new Button("Anuluj", e -> editor.cancel());
        cancel.addClassName("cancel");
        grid.getElement().addEventListener("keyup", event -> editor.cancel())
                .setFilter("event.key === 'Escape' || event.key === 'Esc'");
        Div buttons = new Div(save, cancel);
        editorColumn.setEditorComponent(buttons);

        //editor.addSaveListener(event -> .setText(event.getItem().getBook_name() + ", " + event.getItem().getBook_author() + ", " + event.getItem().getBook_genre() + ", " + event.getItem().getBook_act()));
        add(grid);
    }
}
