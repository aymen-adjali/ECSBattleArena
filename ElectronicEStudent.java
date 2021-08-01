/**
 * ElectronicEStudent class is a subclass of ElecStudent.
 * Inside ElectronicEStudent the attributes have been declared.
 * And the class contains abilities that can only be used by ElectronicEStudents.
 */
public class ElectronicEStudent extends ElecStudent {

  private final String studentName;
  private static final int baseHP = 7;
  private static final int baseAtk = 6;
  private static final int baseDef = 6;
  private static final int baseSpd = 6;
  public static final int maxKP = 4;

  /**
   * Instantiates a new ElectronicEStudent.
   * with the correct attributes.
   */
  public ElectronicEStudent(String name) {
    super(name, baseHP, baseAtk, baseDef, baseSpd, maxKP);
    this.studentName = name;
  }

  /**
   * Pair Soldering ability.
   * If the student has enough KP (maxKP) then they are able to use this ability.
   * If this method is called without enough KP an exception will be thrown.
   * Allows the character and a friend to attack two different enemies.
   * The method, changes HP, KP and EP accordingly.
   */
  public void pairSoldering(Character friend, Character enemy, Character enemy2) throws Exception {
    if (this.currentKP == maxKP) {
      this.currentKP = 0;
      System.out.println(this.getName() + " is using Pair Soldering!");
      System.out.println(this.getName() + " attacked " + enemy.getName());
      System.out.println(friend.getName() + " attacked " + enemy2.getName());
      System.out.println(enemy.getName() + " lost " + (100 * this.getAttack()) / (100 + enemy.getDefence()) + " hp");
      System.out.println(enemy2.getName() + " lost " + (100 * friend.getAttack()) / (100 + enemy2.getDefence()) + " hp");
      enemy.decreaseHP((100 * this.getAttack()) / (100 + enemy.getDefence()));
      enemy.decreaseHP((100 * friend.getAttack()) / (100 + enemy2.getDefence()));
      if (enemy.getHP() <= 0) {
        System.out.println(enemy.getName() + " has fainted...");
        this.increaseEP(4);
      }
      this.increaseEP(4);
    } else {
      throw new Exception("Current KP not reached");
    }
    System.out.println();
  }

  /**
   * Assist ability.
   * If the student has enough KP (maxKP) then they are able to use this ability.
   * If this method is called without enough KP an exception will be thrown.
   * Allows the character to heal a friend, and increase EP.
   * The method, changes HP, KP and EP accordingly.
   */
  public void assist(Character friend) throws Exception {
    if (this.currentKP == maxKP) {
      this.currentKP = 0;
      System.out.println(this.getName() + " is using Assist!");
      System.out.println(friend.getName() + " just got " + this.getDefence() + " hit points");
      System.out.println(friend.getName() + " just got " + 2 + " experience points");
      friend.increaseHP(this.getDefence() / 3);
      friend.increaseEP(2);
      this.increaseEP(4);
    } else {
      throw new Exception("Current KP not reached");
    }
  }
}
