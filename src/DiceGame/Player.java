package DiceGame;

import java.util.ArrayList;

public class Player {
  private String name;
  private int points;
  private ArrayList<Die> dice;

  public Player(String name, ArrayList<Die> dice) {
    this.name = name;
    this.dice = dice;
  }

  public String getName() {
    return name;
  }

  public int getPoints() {
    return points;
  }

  public ArrayList<Die> getDice() {
    return dice;
  }

  public void rollDice() {
    dice.forEach(Die::roll);
  }

  public int getDieValue() {
    int sum = 0;

    for (Die die : dice) {
      sum += die.getCurrentValue();
    }

    return sum;
  }

  public void increasePoints() {
    points++;
  }

  public void addDie(int sides) {
    Die newDie = new Die(sides);
    dice.add(newDie);
  }
}
