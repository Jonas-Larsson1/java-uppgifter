package LibraryProgram;

public class Book {
  String title, author;
  int year, edition;
  boolean available;

  public Book(String title, String author, int edition, int year) {
    this.title = title;
    this.author = author;
    this.edition = edition;
    this.year = year;
    this.available = true;
  }

  public boolean loan() {
    return available && !(available = false);
  }

  public boolean returnBook() {
    return !available && (available = true);
  }

  public String toString() {
    return String.format("'%s' by %s, edition #%d published %d", title, author, edition, year);
  }
}
