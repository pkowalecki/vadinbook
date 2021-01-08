package pl.kowalecki.vadinbook.Service;

import pl.kowalecki.vadinbook.Model.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    boolean addBook(Book book);
}
