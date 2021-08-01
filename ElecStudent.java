/**
 * ElecStudent class is a subclass of Student.
 * This subclass is just to differentiate the types of ECS students,
 * and there are no new methods or declarations.
 */
public class ElecStudent extends Student {
  /**
   * Instantiates a new ElecStudent.
   */
  public ElecStudent(String name, int baseHP, int baseAtk, int baseDef, int baseSpd, int maxKP) {
    super(name, baseHP, baseAtk, baseDef, baseSpd, maxKP);
  }

  /**
   * C programming can be used by all ElecStudents with no constriction of KP.
   * Deceases HP and increases EP and KP values accordingly.
   * Allows the character to deal damage to one enemy.
   * There is an IF statement to check if the enemy dies whilst leveling up
   * Is so, they keep the level up but their health is reset back to zero.
   * (There is a chance they get resurrected so level up must be given).
   */
  public void cProgramming(Character enemy) {
    System.out.println(blue + this.getName() + reset + " is using C Programming on " + red + enemy.getName() + reset + "!");
    System.out.println(enemy.getName() + " just lost " + ((100 * this.getAttack()) / (100 + enemy.getDefence())) + " hp");
    enemy.decreaseHP((100 * this.getAttack()) / (100 + enemy.getDefence()));
    enemy.increaseEP(2);
    if (enemy.getEP() == 0) {
      //check if they level up after dying
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
   * Individual Research can be used by all ElecStudents with no constriction of KP.
   * Increases HP, EP and KP accordingly.
   */
  public void individualResearch() {
    System.out.println(blue + this.getName() + reset + " is using Individual Research!");
    System.out.println(this.getName() + " just got +4 HP, +2 KP and +4 EP");
    this.increaseHP(4);
    this.increaseKP(2);
    this.increaseEP(4);
    System.out.println();
  }
}