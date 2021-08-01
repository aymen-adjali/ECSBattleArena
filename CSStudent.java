/**
 * CSStudent class is a subclass of Student.
 * Inside CSStudent the attributes have been declared.
 * And the class contains abilities that can only be used by CSStudents.
 */
public class CSStudent extends CompStudent {

  private final String studentName;
  private static final int baseHP = 7;
  private static final int baseAtk = 6;
  private static final int baseDef = 6;
  private static final int baseSpd = 6;
  public static final int maxKP = 4;

  /**
   * Instantiates a new CSStudent.
   * with the correct attributes.
   */
  public CSStudent(String name) {
    super(name, baseHP, baseAtk, baseDef, baseSpd, maxKP);
    this.studentName = name;
  }

  /**
   * Pair working ability.
   * If the student has enough KP (maxKP) then they are able to use this ability.
   * If this method is called without enough KP an exception will be thrown.
   * Allows the character and a friend to attack the same enemy.
   * The method, changes HP, KP and EP accordingly.
   */
  public void pairWorking(Character friend, Character enemy) throws Exception {
    if (this.currentKP == maxKP) {
      this.currentKP = 0;
      System.out.println(this.getName() + " is using Pair Working!");
      System.out.println(friend.getName() + " and " + this.getName() + " both attacked " + enemy.getName());
      System.out.println(enemy.getName() + " lost " + (((100 * this.getAttack()) / (100 + enemy.getDefence())) + ((100 * friend.getAttack()) / (100 + enemy.getDefence()))) + " hp");
      enemy.decreaseHP(((100 * this.getAttack()) / (100 + enemy.getDefence())) + ((100 * friend.getAttack()) / (100 + enemy.getDefence())));
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
   * Support ability.
   * If the student has enough KP (maxKP) then they are able to use this ability.
   * If this method is called without enough KP an exception will be thrown.
   * Allows the character to heal a friend.
   * The method, changes HP, KP and EP accordingly.
   */
  public void support(Character friend) throws Exception {
    if (this.currentKP == maxKP) {
      this.currentKP = 0;
      System.out.println(this.getName() + " is using Support!");
      System.out.println(friend.getName() + " just got " + this.getDefence() + " hit points");
      friend.increaseHP(this.getDefence() / 2);
      this.increaseEP(4);
    } else {
      throw new Exception("Current KP not reached");
    }
  }
}
