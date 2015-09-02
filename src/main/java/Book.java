import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;


public class Book {
  private int id;
  private String title;
  public String genre;

  public int getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getGenre() {
    return genre;
  }

  public Book(String title, String genre) {
    this.title = title;
    this.genre = genre;
  }

  @Override
  public boolean equals (Object otherBook) {
    if(!(otherBook instanceof Book)) {
      return false;
    } else {
      Book newBook = (Book) otherBook;
      return this.getTitle().equals(newBook.getTitle()) &&
      this.getGenre().equals(newBook.getGenre());
    }
  }

  public static List<Book> all() {
    String sql = "SELECT id, title, genre FROM books";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Book.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO books (title, genre) VALUES (:title, :genre)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("title", title)
        .addParameter("genre", genre)
        .executeUpdate()
        .getKey();
    }
  }



  }//ends class Course
