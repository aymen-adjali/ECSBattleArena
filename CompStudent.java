/**
 * CompStudent class is a subclass of Student.
 * This subclass is just to differentiate the types of ECS students,
 * and there are no new methods or declarations.
 */
public class CompStudent extends Student {
  /**
   * Instantiates a new CompStudent.
   */
  public CompStudent(String name, int baseHP, int baseAtk, int baseDef, int baseSpd, int maxKP) {
    super(name, baseHP, baseAtk, baseDef, baseSpd, maxKP);
  }

  /**
   * Java programming can be used by all CompStudents with no constriction of KP.
   * Deceases HP and increases EP and KP values accordingly.
   * Allows the character to deal damage to one enemy.
   * There is an IF statement to check if the enemy dies whilst leveling up
   * Is so, they keep the level up but their health is reset back to zero.
   * (There is a chance they get resurrected so level up must be given).
   */
  public void javaProgramming(Character enemy) {
    System.out.println(blue + this.getName() + reset + " is using Java Programming on " + red + enemy.getName() + reset + "!");
    System.out.println(enemy.getName() + " just lost " + ((100 * this.getAttack()) / (100 + enemy.getDefence())) + " hp");
    enemy.decreaseHP((100 * this.getAttack()) / (100 + enemy.getDefence()));
    enemy.increaseEP(2);
    //check if they level up after dying
    if (enemy.getEP() == 0) {
      enemy.decreaseHP(enemy.getMaxHP());
    }
    if (enemy.getClass().equals(Student.class)) {
      ((Student) enemy).increaseKP(3);
    } else if (enemy.getHP() == 0) {
      System.out.println(enemy.getName() + " has fainted...");
      this.increaseEP(4);
    }
    increaseKP(this.currentKP + 1);
    increaseEP(this.getEP() + 3);
    System.out.println();
  }

  /**
   * Self study can be used by all CompStudents with no constriction of KP.
   * Increases HP, EP and KP accordingly.
   */
  public void selfStudy() {
    System.out.println(blue + this.getName() + reset + " is using Self Study!");
    System.out.println(this.getName() + " just got +2 HP, +2 KP and +6 EP");
    this.increaseHP(2);
    this.increaseKP(2);
    this.increaseEP(6);
    System.out.println();
  }
}