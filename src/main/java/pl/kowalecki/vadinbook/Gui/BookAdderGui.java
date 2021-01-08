package pl.kowalecki.vadinbook.Gui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.kowalecki.vadinbook.Model.Book;
import pl.kowalecki.vadinbook.Service.BookService;

@Route("add-book")
public class BookAdderGui extends VerticalLayout {

    private BookService bookService;

    @Autowired
    public BookAdderGui(BookService bookService){
        this.bookService = bookService;
        IntegerField textFieldId = new IntegerField("Id");
        TextField textFieldTitle = new TextField("Tytuł książki");
        TextField textFieldAuthor = new TextField("Autor");
        TextField textFieldGenre = new TextField("Gatunek");

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

        Button buttonAdd = new Button("Dodaj książkę");

        buttonAdd.addClickListener(clickEvent -> {
        Book book = new Book(textFieldId.getMin(),textFieldTitle.getValue(), textFieldAuthor.getValue(),textFieldGenre.getValue(),(String)comboBoxState.getValue());
        bookService.addBook(book);
        bookService.getAllBooks().forEach(System.out::println);
        });

        add(textFieldTitle,textFieldAuthor,textFieldGenre,comboBoxState, buttonAdd);
    }
}
