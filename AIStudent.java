import jdk.swing.interop.SwingInterOpUtils;

/**
 * Artificial Intelligence class is a subclass of Student.
 * Inside AIStudent the attributes have been declared.
 * And the class contains abilities that can only be used by AIStudents.
 */
public class AIStudent extends CompStudent implements Colours {

  private final String studentName;
  private static final int baseHP = 6;
  private static final int baseAtk = 7;
  private static final int baseDef = 7;
  private static final int baseSpd = 5;
  public static final int maxKP = 3;

  /**
   * Instantiates a new AIStudent,
   * with the correct attributes.
   */
  public AIStudent(String name) {
    super(name, baseHP, baseAtk, baseDef, baseSpd, maxKP);
    this.studentName = name;
  }

  /**
   * Machine learning ability.
   * If the student has enough KP (maxKP) then they are able to use this ability.
   * If this method is called without enough KP an exception will be thrown.
   * Allows the character to deal double damage to one enemy.
   * The method, changes HP, KP and EP accordingly.
   */
  public void machineLearning(Character enemy) throws Exception {
    if (this.currentKP == maxKP) {
      this.currentKP = 0;
      System.out.println(this.getName() + " is using Machine Learning!");
      System.out.println(enemy.getName() + " just took double damage");
      System.out.println(enemy.getName() + " just lost " + (2 * (100 * this.getAttack()) / (100 + (enemy.getDefence())) + " hp"));
      this.increaseEP(4);
      enemy.decreaseHP(2 * (100 * this.getAttack()) / (100 + enemy.getDefence()));
      if (enemy.getHP() <= 0) {
        System.out.println(enemy.getName() + " has fainted...");
        this.increaseEP(4);
      }
    } else {
      throw new Exception("Max KP not reached");
    }
    System.out.println();
  }

  /**
   * Natural Language Processing ability.
   * If the student has enough KP (maxKP) then they are able to use this ability.
   * If this method is called without enough KP an exception will be thrown.
   * Allows the character to heal themselves.
   * The method, changes HP, KP and EP accordingly.
   */
  public void naturalLanguageProcessing() throws Exception {
    if (this.currentKP == maxKP) {
      this.currentKP = 0;
      System.out.println(this.getName() + " is using Natural Language Processing!");
      System.out.println(this.getName() + " got " + this.getDefence() + " hp");
      this.increaseEP(4);
      this.increaseHP(this.getDefence());
    } else {
      throw new Exception("Max KP not reached");
    }
  }
}
