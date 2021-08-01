/**
 * BEStudent class is a subclass of ElecStudent.
 * Inside BEStudent the attributes have been declared.
 * And the class contains abilities that can only be used by BEStudents.
 */
public class BEStudent extends ElecStudent {

  private final String studentName;
  private static final int baseHP = 8;
  private static final int baseAtk = 5;
  private static final int baseDef = 8;
  private static final int baseSpd = 4;
  public static final int maxKP = 10;

  /**
   * Instantiates a new BEStudent.
   * with the correct attributes.
   */
  public BEStudent(String name) {
    super(name, baseHP, baseAtk, baseDef, baseSpd, maxKP);
    this.studentName = name;
  }

  /**
   * Neuroscience ability.
   * If the student has enough KP (maxKP) then they are able to use this ability.
   * If this method is called without enough KP an exception will be thrown.
   * Allows the all team mates of the character to attack one enemy.
   * The method, changes HP, KP and EP accordingly.
   */
  public void neuroscience(Character enemy) throws Exception {
    if (this.currentKP == maxKP) {
      this.currentKP = 0;
      System.out.println(this.getName() + " is using Neuroscience!");
      System.out.println(enemy.getName() + " got took damage from every alive team member in " + this.getTeam().getName());
      for (Character teamMate : this.team.getMembers()) {
        if (teamMate.getHP() > 0) {
          enemy.decreaseHP((100 * teamMate.getAttack()) / (100 + enemy.getDefence()));
          System.out.println(enemy.getName() + " lost " + (100 * teamMate.getAttack()) / (100 + enemy.getDefence()) + " hp");
          if (enemy.getHP() <= 0 && teamMate.equals(this)) {
            this.increaseEP(4);
            System.out.println(enemy.getName() + " has fainted...");
            break;
          }
        }
      }
      this.increaseEP(4);
    } else {
      throw new Exception("Current KP not reached");
    }
    System.out.println();
  }

  /**
   * Biomedical Control ability.
   * If the student has enough KP (maxKP) then they are able to use this ability.
   * If this method is called without enough KP an exception will be thrown.
   * Allows the character to heal and increase EP of all alive members in their team.
   * The method, changes HP, KP and EP accordingly.
   */
  public void biomedicalControl() throws Exception {
    if (this.currentKP == maxKP) {
      this.currentKP = 0;
      System.out.println(this.getName() + " is using Biomedical Control!");
      System.out.println("All team mates in " + this.getTeam().getName() + " just got " + (this.getDefence()) / 3 + " hit points and 2 experience points");
      for (Character teamMate : this.team.getMembers()) {
        if (teamMate.getHP() > 0) {
          teamMate.increaseHP(this.getDefence() / 3);
          teamMate.increaseEP(2);
        }

      }
      this.increaseEP(4);
    } else {
      throw new Exception("SES student KP not reached");
    }

    System.out.println();
  }
}