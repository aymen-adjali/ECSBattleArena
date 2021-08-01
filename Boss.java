import java.util.Random;

/**
 * Boss class is a subclass of Enemy.
 * Inside Boss the attributes have been declared.
 * And the class contains abilities that can only be used by Boss's.
 */
public class Boss extends Enemy {
  private final String bossName;
  private static final int baseHP = 8;
  private static final int baseAtk = 7;
  private static final int baseDef = 8;
  private static final int baseSpd = 7;

  /**
   * Instantiates a new Boss,
   * with the correct attributes.
   */
  public Boss(String name) {
    super(name, baseHP, baseAtk, baseDef, baseSpd);
    this.bossName = name;
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
    if (randNum >= 0 && randNum < 50) {
      SyntaxError(enemy);
    } else if (randNum >= 50 && randNum < 65) {
      NullPointerException();
    } else if (randNum >= 65 && randNum < 80) {
      ArrayIndexOutOfBoundException(enemy);
    } else if (randNum >= 80 && randNum < 90) {
      NoneTermination(getTeam());
    } else if (randNum >= 90) {
      ConcurrentModificationException(enemy.getTeam());
    }
  }

  /**
   * None Termination ability.
   * Allows the monster to resurrect all dead teammates.
   * The method, changes HP, and EP accordingly.
   */
  public void NoneTermination(Team team) {
    System.out.println(red + this.getName() + reset + " is using None Termination!");
    if (!isEveryoneAlive(team)) {
      System.out.println(this.getName() + " just resurrected all fallen team members");
      for (Character character : team.getMembers()) {
        if (character.getHP() == 0) {
          System.out.println(character.getMaxHP());
          character.increaseHP(character.getMaxHP());
          System.out.println(character.getName() + " just got revived, they're HP is now " + character.getHP() + "!");
        }
      }
    } else {
      System.out.println("No one was revived");
    }
    this.increaseEP(3);
    System.out.println();
  }

  /**
   * None Termination ability.
   * Allows the monster to resurrect all dead teammates.
   * The method, changes HP, and EP accordingly.
   */
  public void ConcurrentModificationException(Team enemyTeam) {
    System.out.println(red + this.getName() + reset + " is using Concurrent Modification Exception!");
    System.out.println("All members of " + enemyTeam.getName() + " just took damage");   //fixed :)
    for (Character character : enemyTeam.getMembers()) {
      if (character.getHP() > 0) {
        System.out.println(character.getName() + " just lost " + (100 * this.getAttack() / (100 + character.getDefence())) + " hp");
        character.decreaseHP(100 * this.getAttack() / (100 + character.getDefence()));
        checkStudent(character);
        if (character.getHP() == 0) {
          System.out.println(character.getName() + " has fainted...");
          this.increaseEP(4);
        }
      }
      character.increaseEP(3);
      //check if they level up after dying
      if (character.getEP() == 0) {
        character.decreaseHP(character.getMaxHP());
      }
    }
    this.increaseEP(3);
    System.out.println();
  }
}
