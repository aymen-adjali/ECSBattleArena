/**
 * CyberStudent class is a subclass of Student.
 * Inside CyberStudent the attributes have been declared.
 * And the class contains abilities that can only be used by CyberStudents.
 */
public class CyberStudent extends CompStudent implements Colours {

  private final String studentName;
  private static final int baseHP = 7;
  private static final int baseAtk = 7;
  private static final int baseDef = 5;
  private static final int baseSpd = 6;
  public static final int maxKP = 6;
  public int lvlUpCoefficient = 0;

  /**
   * Instantiates a new CyberStudent.
   * with the correct attributes.
   */
  public CyberStudent(String name) {
    super(name, baseHP, baseAtk, baseDef, baseSpd, maxKP);
    this.studentName = name;
  }

  /**
   * Cyber Attack ability.
   * If the student has enough KP (maxKP) then they are able to use this ability.
   * If this method is called without enough KP an exception will be thrown.
   * Allows the character to deal damage to all alive members of the enemy team.
   * The method, changes HP, KP and EP accordingly.
   */
  public void cyberAttack(Team enemyTeam) throws Exception {
    if (this.currentKP == maxKP) {
      this.currentKP = 0;
      System.out.println(this.getName() + " is using Cyber Attack!");
      System.out.println("All enemies in " + enemyTeam.getName() + " just took damage");
      for (Character enemy : enemyTeam.getMembers()) {
        if (!isDead(enemy)) {
          System.out.println();
          System.out.println(enemy.getName() + " lost " + ((100 * this.getAttack()) / (100 + enemy.getDefence())) + " hp");
          enemy.decreaseHP((100 * this.getAttack()) / (100 + enemy.getDefence()));
          //check if they level up after dying
          if (enemy.getHP() == 0) {
            lvlUpCoefficient += 1;
            System.out.println(enemy.getName() + " has fainted...");
          }
        }
      }
    } else {
      throw new Exception("Current KP not reached");
    }
    //increase EP at the end so he doesn't lvl up in the middle of the attack
    this.increaseEP(lvlUpCoefficient * 4);
    this.increaseEP(4);
    System.out.println();
  }
}