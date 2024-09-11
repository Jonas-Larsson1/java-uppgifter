package DiceGame;

import java.util.ArrayList;
import java.util.Random;

public class Die {
  private int currentValue, numberOfSides;
  private static Random randomGenerator = new Random();

  public static ArrayList<Die> createDiceArray(int count, int numberOfSides) {
    ArrayList<Die> dieArrayList = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      dieArrayList.add(new Die(numberOfSides));
    }
    return dieArrayList;
  }
  public Die(int numberOfSides) {
    this.numberOfSides = numberOfSides;
  }

  public int getNumberOfSides() {
    return numberOfSides;
  }

  public int getCurrentValue() {
    return currentValue;
  }

  public void roll() {
    currentValue = randomGenerator.nextInt(numberOfSides) + 1;
  }

  public int getValue() {
    return currentValue;
  }
}
