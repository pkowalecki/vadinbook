package pl.kowalecki.vadinbook.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String book_name;
    private String book_author;
    private String book_genre;
    private String book_act;



    public Book(long id, String book_name, String book_author, String book_genre, String book_act) {
        this.id = id;
        this.book_name = book_name;
        this.book_author = book_author;
        this.book_genre = book_genre;
        this.book_act = book_act;
    }

    public Book() {
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getBook_author() {
        return book_author;
    }

    public void setBook_author(String book_author) {
        this.book_author = book_author;
    }

    public String getBook_genre() {
        return book_genre;
    }

    public void setBook_genre(String book_genre) {
        this.book_genre = book_genre;
    }

    public String getBook_act() {
        return book_act;
    }

    public void setBook_act(String book_act) {
        this.book_act = book_act;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", book_name='" + book_name + '\'' +
                ", book_author='" + book_author + '\'' +
                ", book_genre='" + book_genre + '\'' +
                ", book_act='" + book_act + '\'' +
                '}';
    }
}
