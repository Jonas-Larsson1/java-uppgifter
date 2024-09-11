package LibraryProgram;

import java.util.ArrayList;
import java.util.List;

public class Library {
  List<Book> books = new ArrayList<>();

  public Book addBook(Book newBook) {
    books.add(newBook);
    return newBook;
  }

  public Book searchForBook(String title) {
    for (Book book : books) {
      if (book.title.toLowerCase().contains(title.toLowerCase())) return book;
    }
    return null;
  }

  public List<Book> listAvailableBooks() {
    List<Book> availableBooks = new ArrayList<>();

    for (Book book : books) {
      if (book.available) availableBooks.add(book);
    }

    return availableBooks;
  }

  public Book returnBook(String title) {
    Book bookToReturn = searchForBook(title);
    if (bookToReturn != null && bookToReturn.returnBook()) return bookToReturn;

    return null;
  }

}
