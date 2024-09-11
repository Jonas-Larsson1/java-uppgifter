package DiceGame;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Game {
  private static Scanner scanner = new Scanner(System.in);

  private static ArrayList<Player> initialize() {
    ArrayList<Player> players = new ArrayList<>();

    System.out.println("***********************************");
    System.out.println("   WELCOME TO DICE GAME 1.0   ");
    System.out.println("***********************************");

    System.out.println("How many players?");
    int numberOfPlayers = scanner.nextInt();

    System.out.println("How many dice per player?");
    int numbersOfDicePerPlayer = scanner.nextInt();

    System.out.println("How many sides per dice?");
    int numberOfSidesPerDice = scanner.nextInt();

    scanner.nextLine();

    for (int i = 1; i <= numberOfPlayers; i++) {
      System.out.printf("Enter the name of player #%d", i);
      System.out.println();
      String playerName = scanner.nextLine();
      ArrayList<Die> playerDices = Die.createDiceArray(numbersOfDicePerPlayer, numberOfSidesPerDice);
      players.add(new Player(playerName, playerDices));
    }

    System.out.println("\nAll players are ready! Let the game begin!");
    System.out.println("***********************************");

    return players;
  }

  private static void takeTurn(ArrayList<Player> players, int round) {
    System.out.println("\n===============================");
    System.out.printf("          ROUND %d           \n", round);
    System.out.println("===============================");

    players.forEach(player -> {
      System.out.printf("\n%s, it's your turn. Please make a guess: ", player.getName());
      System.out.println();
      int playerGuess = scanner.nextInt();

      System.out.printf("%s is rolling the dice...\n", player.getName());
      System.out.println();

      player.rollDice();

      for (int i = 0; i < player.getDice().size(); i++) {
        int numberOfSides = player.getDice().get(i).getNumberOfSides();
        int finalValue = player.getDice().get(i).getValue();
        animateDieRoll(numberOfSides, finalValue);
        System.out.println();
      }

      int dieValue = player.getDieValue();
      System.out.printf("\nTotal dice value: %d\n", dieValue);

      if (dieValue == playerGuess) {
        player.increasePoints();
        System.out.println("Congratulations! You guessed correctly and earned a point!");
      } else {
        System.out.println("Sorry, no points this round. Better luck next time!");
      }
    });
  }

  private static void animateDieRoll(int numberOfSides, int finalValue) {
    Random random = new Random();

    for (int i = 0; i < 10; i++) {
      int randomValue = random.nextInt(numberOfSides) + 1;
      System.out.print("\r" + randomValue);
      System.out.flush();
      delay(0.1);
    }

    System.out.print("\r" + finalValue);
    System.out.flush();
    delay(0.1);
  }

  private static void getWinners(ArrayList<Player> players) {
    players.sort(Comparator.comparingInt(Player::getPoints).reversed());
    int highestScore = players.getFirst().getPoints();

    ArrayList<Player> winningPlayers = new ArrayList<>();
    for(Player player : players) {
      if (player.getPoints() == highestScore) {
        winningPlayers.add(player);
      } else {
        break;
      }
    }

    System.out.println("\n***********************************");
    System.out.println("           FINAL RESULTS           ");
    System.out.println("***********************************");

    if (winningPlayers.size() == 1) {
      Player winner = winningPlayers.getFirst();
      System.out.printf("Congratulations %s! You are the winner with %d points!\n", winner.getName(), winner.getPoints());
    } else {
      System.out.println("It's a tie! The winners are:");
      for (Player player : winningPlayers) {
        System.out.printf("%s with %d points!\n", player.getName(), player.getPoints());
      }
    }

    System.out.println("\nThank you for playing Dice Game 1.0!");
  }

  private static void delay(double seconds) {
    try {
      TimeUnit.MILLISECONDS.sleep((long) (seconds * 1000));
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  public static void main() {
    ArrayList<Player> currentPlayers = initialize();
    int numberOfRounds = 5;
    int currentRound = 0;
    while (currentRound < numberOfRounds) {
      takeTurn(currentPlayers, currentRound);
      currentRound++;
    }
    getWinners(currentPlayers);
  }
}
