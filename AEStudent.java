/**
 * AEStudent class is a subclass of ElecStudent.
 * Inside AEStudent the attributes have been declared.
 * And the class contains abilities that can only be used by AEStudents.
 */
public class AEStudent extends ElecStudent {

  private final String studentName;
  private static final int baseHP = 7;
  private static final int baseAtk = 7;
  private static final int baseDef = 5;
  private static final int baseSpd = 6;
  public static final int maxKP = 6;

  /**
   * Instantiates a new AEStudent.
   * with the correct attributes.
   */
  public AEStudent(String name) {
    super(name, baseHP, baseAtk, baseDef, baseSpd, maxKP);
    this.studentName = name;
  }

  /**
   * Autonomous Flying Vehicles ability.
   * If the student has enough KP (maxKP) then they are able to use this ability.
   * If this method is called without enough KP an exception will be thrown.
   * Allows the character to reduce EP of all alive members in the enemy team.
   * The method, changes HP, KP and EP accordingly.
   */
  public void autonomousFlyingVehicles(Team enemyTeam) throws Exception {
    if (this.currentKP == maxKP) {
      this.currentKP = 0;
      System.out.println(this.getName() + " is using Autonomous Flying Vehicles!");
      System.out.println("All enemies in " + enemyTeam.getName() + " just lost EP");
      for (Character enemy : enemyTeam.getMembers()) {
        if (!isDead(enemy)) {
          System.out.println();
          System.out.println(enemy.getName() + " lost " + 2 + " EP");
          enemy.decreaseEP(2);
        }
      }
    } else {
      throw new Exception("Current KP not reached");
    }
    this.increaseEP(4);
    System.out.println();
  }
}