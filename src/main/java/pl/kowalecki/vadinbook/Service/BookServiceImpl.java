package pl.kowalecki.vadinbook.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kowalecki.vadinbook.BookRepo;
import pl.kowalecki.vadinbook.Model.Book;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    private BookRepo bookRepo;

    @Autowired
    public BookServiceImpl(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    @Override
    public boolean addBook(Book book) {
        bookRepo.save(book);
        return true;
    }
}
