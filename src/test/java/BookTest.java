import java.util.Arrays;
import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Rule;
import java.util.List;
import java.util.ArrayList;

public class BookTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Book.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfBooksAreTheSame() {
    Book firstBook = new Book ("Book", "Genre");
    Book  secondBook = new Book ("Book", "Genre");
    assertTrue(firstBook.equals(secondBook));
}

  @Test
  public void save_savesIntoDatabase_true () {
    Book newBook = new Book ("Book", "Genre");
    newBook.save();
    assertTrue(Book.all().get(0).equals(newBook));
  }





















}//end BookTest class
