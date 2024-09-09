package pl.coderslab;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BookServiceTest {

    private MockBookService mockBookService;

    @BeforeEach
    void setUp(){
        mockBookService = new MockBookService();
    }
    @Test
    void shouldGetBooks() {
        List<Book> books = mockBookService.getBooks();
        assertThat(books).isNotEmpty();
    }

    @Test
    void shouldAddBook() {
        Book book = new Book();
        MockBookService mockBookService1 = new MockBookService();
        book.setId(4L);
        book.setAuthor("Marek Krajewski");
        book.setTitle("Liczby Charona");

        mockBookService1.add(book);

        assertThat(book).isInstanceOf(Book.class);
        assertThat(mockBookService1.get(4L)).isNotNull();
    }

    @Test
    void shouldGetBook() {
        Book book = new Book();
        book.setAuthor("Bruce	Eckel");
        Optional<Book> book1  = mockBookService.get(1L);
        assertThat(mockBookService.get(1L)).isNotNull();
        assertThat(book1.get().getAuthor()).isEqualTo(book.getAuthor());

    }

    @Test
    void shouldDeleteBook() {
        List <Book> books = mockBookService.getBooks();
        Book book = new Book();
        book.setAuthor("Stephen King");
        book.setId(4L);
        books.add(book);

        mockBookService.delete(4L);

        assertThat(books).doesNotContain(book);
    }

    @Test
    void shouldUpdateBook() {
        Book book = new Book();
        List <Book> books = mockBookService.getBooks();

        book.setId(5L);
        book.setTitle("It");
        books.add(book);
        book.setAuthor("Stephen");
        mockBookService.update(book);
        assertThat(book.getAuthor()).isEqualTo("Stephen");
        assertThat(book.getTitle()).isEqualTo("It");
        assertThat(book.getId()).isEqualTo(5L);

    }
}