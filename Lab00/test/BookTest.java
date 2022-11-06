import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * A JUnit test class for the Book class.
 */

public class BookTest {
  private Person author;
  private Book myBook;

  @Before
  public void setup() {
    author = new Person("John", "Doe", 1945);
    myBook = new Book("Java", author, 2500);
  }

  @Test
  public void testTitle() {
    assertEquals("Java", myBook.getTitle());
  }

  @Test
  public void testAuthor() {
    assertEquals(author, myBook.getAuthor());
  }

  @Test
  public void testPrice() {
    assertEquals(2500, myBook.getPrice(), 0.0f);
  }
}