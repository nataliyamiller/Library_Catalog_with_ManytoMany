import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Rule;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class AuthorTest {

     @Rule
     public DatabaseRule database = new DatabaseRule();

     @Test
     public void all_emptyAtFirst() {
       assertEquals(Author.all().size(), 0);
     }

     @Test
     public void equals_returnTrueIfAuthorsAreTheSame() {
       Author newAuthor = new Author("Shakespeare");
       Author secondAuthor = new Author("Shakespeare");
       assertTrue(newAuthor.equals(secondAuthor));
     }

     @Test
     public void save_savesIntoDatabase_true() {
       Author newAuthor = new Author("Shakespeare");
       newAuthor.save();
       assertTrue(Author.all().get(0).equals(newAuthor));
     }


 }//end AuthorTest class
