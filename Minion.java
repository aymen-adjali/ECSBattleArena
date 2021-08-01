import java.util.Random;

/**
 * Minion class is a subclass of Enemy.
 * Inside Minion the attributes have been declared.
 * And the class contains abilities that can only be used by Minions.
 */
public class Minion extends Enemy {
  private final String minionName;
  private static final int baseHP = 5;
  private static final int baseAtk = 5;
  private static final int baseDef = 5;
  private static final int baseSpd = 5;

  /**
   * Instantiates a new Minion,
   * with the correct attributes.
   */
  public Minion(String name) {
    super(name, baseHP, baseAtk, baseDef, baseSpd);
    this.minionName = name;
  }

  /**
   * Strike ability.
   * Strike is overridden here from the Enemy class.
   * The move method will call Strike and depending on probabilities,
   * a specific ability will be used.
   * This is implemented using a random number generator from 1-100.
   */
  public void strike(Character enemy) {
    Random rand = new Random();
    int randNum = rand.nextInt(100);
    if (randNum > 0 && randNum <= 75) {
      SyntaxError(enemy);
    } else if (randNum > 75 && randNum <= 90) {
      NullPointerException();
    } else if (randNum > 90) {
      ArrayIndexOutOfBoundException(enemy);
    }
  }
}