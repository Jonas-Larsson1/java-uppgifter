package LibraryProgram;

import java.util.Scanner;
import java.util.List;


public class LibraryInterface {
  private Scanner scanner = new Scanner(System.in);
  private Library library = null;
  private boolean running = false;

  public LibraryInterface(Library library) {
    this.library = library;
  }

  private void displayMenu() {
    System.out.println("\nWelcome to the library!:");
    System.out.println("1. Add a book to the library");
    System.out.println("2. Search for a book by title");
    System.out.println("3. List all available books");
    System.out.println("4. Return a book");
    System.out.println("5. Quit");
    System.out.print("Choose an option: ");
  }

  private int getUserChoice() {
    return scanner.nextInt();
  }

  private void processUserChoice(int userChoice) {
    scanner.nextLine();
    boolean promptForReturn = true;

    switch (userChoice) {
      case 1:
        addBook();
        break;

      case 2:
        searchForBook();
        break;

      case 3:
        listAvailableBooks();
        break;

      case 4:
        returnBook();
        break;

      case 5:
        running = false;
        promptForReturn = false;
        System.out.println("Exiting the library interface.");
        break;

      default:
        promptForReturn = false;
        System.out.println("Invalid choice, please try again.");
    }

    if (promptForReturn) menuReturnPrompt();
  }

  private void addBook() {
    System.out.print("Enter book title: ");
    String title = scanner.nextLine();

    System.out.print("Enter author name: ");
    String author = scanner.nextLine();

    System.out.print("Enter edition number: ");
    int edition = scanner.nextInt();

    System.out.print("Enter publication year: ");
    int year = scanner.nextInt();

    scanner.nextLine();

    Book newBook = new Book(title, author, edition, year);
    library.addBook(newBook);

    System.out.println("Book added: " + newBook);
//    System.out.println(newBook.hashCode());
  }

  private void searchForBook() {
    System.out.print("Enter book title to search for: ");
    String titleInput = scanner.nextLine();

    Book foundBook = library.searchForBook(titleInput);
    if (foundBook == null) {
      System.out.println("No book found.");
      return;
    }

    System.out.println("Book found: " + foundBook.toString());

    if (!foundBook.available) {
      System.out.println("The book is not available to be borrowed at this moment");
      return;
    }

    System.out.print("Would you like to borrow this book? (y/n): ");
    String response = scanner.nextLine();

    if (response.equalsIgnoreCase("y") && foundBook.loan()) {
      System.out.println("Loan successful, enjoy!");
    }

  }

  private void listAvailableBooks() {
    List<Book> availableBooks = library.listAvailableBooks();
    if (availableBooks.isEmpty()) {
      System.out.println("No books are currently available");
      return;
    }

    System.out.println("Available books:");
    for (Book book : availableBooks) {
      System.out.println(book.toString());
    }
  }

  private void returnBook() {
    System.out.print("Enter book title to return: ");
    String titleInput = scanner.nextLine();

    Book bookToReturn = library.searchForBook(titleInput);
    if (bookToReturn == null) {
      System.out.println("No book found.");
      return;
    }

    System.out.println(bookToReturn.toString());

    if (bookToReturn.available) {
      System.out.println("This book is already in the library.");
      return;
    }

    System.out.print("Do you want to return this book? (y/n)");

    String response = scanner.nextLine();

    if (response.equalsIgnoreCase("y") && bookToReturn.returnBook()) {
      System.out.println("Book successfully returned, thank you!");
    }
  }

  private void menuReturnPrompt() {
    System.out.print("Press Enter to return to the library menu...");
    scanner.nextLine();
  }

  public void start() {
    if (library == null) {
      System.out.println("Error starting library interface: no library found.");
    } else {
      running = true;
      System.out.println("Library interface successfully started.");
    }

    while (running) {
      displayMenu();
      int userChoice = getUserChoice();
      processUserChoice(userChoice);
    }
  }
}
