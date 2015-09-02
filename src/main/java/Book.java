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

  public static Book find(int id) {
    try(Connection con = DB.sql2o.open()){
      String sql ="SELECT * FROM books WHERE id=:id";
      Book book = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Book.class);
      return book;
    }
  }

  public void update(String title, String genre) {
  this.title = title;
  this.genre = genre;
  try(Connection con = DB.sql2o.open()){
    String sql = "UPDATE books SET title=:title, genre=:genre WHERE id=:id";
    con.createQuery(sql) //course_name is not this.course_name due to parameters?
      .addParameter("title", title)
      .addParameter("genre", genre)
      .addParameter("id", id)
      .executeUpdate();
  }
}

  public void addAuthor(Author author) {
      try(Connection con = DB.sql2o.open()) {
        String sql = "INSERT INTO books_authors (book_id, author_id) VALUES (:book_id, :author_id)";
        con.createQuery(sql)
          .addParameter("book_id", this.getId())
          .addParameter("author_id", author.getId())
          .executeUpdate();
      }
    }


public ArrayList<Author> getAuthors() {
  //grabs student ids from a course
  try(Connection con = DB.sql2o.open()) {
    String sql = "SELECT author_id FROM books_authors WHERE book_id=:book_id";
    List<Integer> authorIds = con.createQuery(sql)
    .addParameter("course_id", this.getId())
    .executeAndFetch(Integer.class);

    //declare empty array to push all students ids that match to the courseid
    ArrayList<Author> authors = new ArrayList<Author>();

    //looping through the student index in order to grab all students that match course_id
    for(Integer index : authorIds) { //for index in student Ids
      String authorQuery = "SELECT * FROM authors WHERE Id = :index";
      Author author = con.createQuery(authorQuery)
        .addParameter("index", index)
        .executeAndFetchFirst(Author.class);
        authors.add(author);
    }return authors;
  }
}

public void delete() {
  try(Connection con = DB.sql2o.open()) {
    String deleteQuery = "DELETE FROM books WHERE id=:id";
      con.createQuery(deleteQuery)
        .addParameter("id", id)
        .executeUpdate();

    String joinDeleteQuery = "DELETE FROM books_authors WHERE book_id =:book_id";
      con.createQuery(joinDeleteQuery)
        .addParameter("book_id", this.getId())
        .executeUpdate();
  }
}


  }//ends class Course
