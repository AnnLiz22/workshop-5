package pl.coderslab;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

class BookServiceTest {

  private MockBookService mockBookService;

  @BeforeEach
  void setUp() {
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
    book.setId(4L);
    book.setAuthor("Marek Krajewski");
    book.setTitle("Liczby Charona");

    mockBookService.add(book);

    assertThat(book).isInstanceOf(Book.class);
    assertThat(mockBookService.get(4L)).isNotNull();
  }

  @Test
  void shouldGetBook() {
    Book book = new Book();
    book.setAuthor("Bruce	Eckel");
    Optional<Book> book1 = mockBookService.get(1L);
    assertThat(mockBookService.get(1L)).isNotNull();
    assertThat(book1.get().getAuthor()).isEqualTo(book.getAuthor());

  }

  @Test
  void shouldGetWithCorrectArgument() {
    mockBookService = mock(MockBookService.class);
    ArgumentCaptor<Long> idCaptor = ArgumentCaptor.forClass(Long.class);
    mockBookService.get(2L);
    Mockito.verify(mockBookService).get(idCaptor.capture());
    assertEquals(2L, idCaptor.getValue());
    assertNotEquals(1L, (long) idCaptor.getValue());
  }

  @Test
  void shouldDeleteBook() {
    List<Book> books = mockBookService.getBooks();
    Book book = new Book();
    book.setAuthor("Stephen King");
    book.setId(4L);
    books.add(book);

    mockBookService.delete(4L);

    assertThat(books).doesNotContain(book);
  }

  @Test
  void shouldDeleteWithCorrectArgument() {
    mockBookService = mock(MockBookService.class);
    List<Book> books = mockBookService.getBooks();
    Book book = new Book();
    book.setAuthor("Graham Masterton");
    book.setId(5L);
    books.add(book);

    ArgumentCaptor<Long> idCaptor = ArgumentCaptor.forClass(Long.class);
    mockBookService.delete(5L);
    Mockito.verify(mockBookService).delete(idCaptor.capture());
    assertEquals(5L, idCaptor.getValue());
  }

  @Test
  void shouldUpdateBook() {
    Book book = new Book();
    List<Book> books = mockBookService.getBooks();

    book.setId(5L);
    book.setTitle("It");
    books.add(book);
    book.setAuthor("Stephen");
    mockBookService.update(book);
    assertThat(book.getAuthor()).isEqualTo("Stephen");
    assertThat(book.getTitle()).isEqualTo("It");
    assertThat(book.getId()).isEqualTo(5L);

  }

  @Test
  void shouldUpdateWithCorrectArgument() {
    mockBookService = mock(MockBookService.class);
    Book book = new Book();
    List<Book> books = mockBookService.getBooks();
    book.setAuthor("Cormac McCarthy");
    books.add(book);
    book.setPublisher("Best publisher");

    mockBookService.update(book);

    ArgumentCaptor<Book> bookArgumentCaptor = ArgumentCaptor.forClass(Book.class);
    verify(mockBookService).update(bookArgumentCaptor.capture());
    assertEquals(book, bookArgumentCaptor.getValue());
  }
}