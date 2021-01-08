package pl.kowalecki.vadinbook;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kowalecki.vadinbook.Model.Book;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {
}
