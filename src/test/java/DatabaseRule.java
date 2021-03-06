import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

  protected void before() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/library_catalog_test", null, null);
   }

  protected void after() {
    try(Connection con = DB.sql2o.open()) {
      String deleteAuthorsQuery = "DELETE FROM authors *;";
      String deleteBooksQuery = "DELETE FROM books *;";
      String deleteBooksAuthorsQuery = "DELETE FROM books_authors *;";
      String deletePatronsQuery = "DELETE FROM patrons *;";
      String deleteCheckoutsQuery = "DELETE FROM checkouts *;";
      con.createQuery(deleteAuthorsQuery).executeUpdate();
      con.createQuery(deleteBooksQuery).executeUpdate();
      con.createQuery(deleteBooksAuthorsQuery).executeUpdate();
      con.createQuery(deletePatronsQuery).executeUpdate();
      con.createQuery(deleteCheckoutsQuery).executeUpdate();
    }
  }
}
