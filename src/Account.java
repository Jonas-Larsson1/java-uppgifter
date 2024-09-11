public class Account {
  int balance;
  String name;

  public Account() {
    balance = 0;
  }

  public Account(int balance) {
    this.balance = balance;
  }

  public Account(int balance, String name) {
    this.balance = balance;
    this.name = name;
  }

  public void withdraw(int amount) {
    balance -= amount;
  }

  public void deposit(int amount) {
    balance += amount;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String toString() {
    return String.format("Account belonging to %s and has a balance of %d", name, balance);
  }
}
