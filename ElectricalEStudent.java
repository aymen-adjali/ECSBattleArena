/**
 * ElectricalEStudent class is a subclass of ElecStudent.
 * Inside ElectricalEStudent the attributes have been declared.
 * And the class contains abilities that can only be used by ElectricalEStudents.
 * KP set very high because the abilities are good
 */
public class ElectricalEStudent extends ElecStudent {

  private final String studentName;
  private static final int baseHP = 6;
  private static final int baseAtk = 7;
  private static final int baseDef = 7;
  private static final int baseSpd = 5;
  public static final int maxKP = 10;

  /**
   * Instantiates a new ElectricalEStudent.
   * with the correct attributes.
   */
  public ElectricalEStudent(String name) {
    super(name, baseHP, baseAtk, baseDef, baseSpd, maxKP);
    this.studentName = name;
  }

  /**
   * Capacitor Discharge ability.
   * If the student has enough KP (maxKP) then they are able to use this ability.
   * If this method is called without enough KP an exception will be thrown.
   * Allows the character to deal triple damage to one enemy.
   * The method, changes HP, KP and EP accordingly.
   */
  public void capacitorDischarge(Character enemy) throws Exception {
    if (this.currentKP == maxKP) {
      this.currentKP = 0;
      System.out.println(this.getName() + " is using Capacitor Discharge!");
      System.out.println(enemy.getName() + " just took triple damage");
      System.out.println(enemy.getName() + " just lost " + (3 * (100 * this.getAttack()) / (100 + (enemy.getDefence())) + " hp"));
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
   * High Voltage Engineering ability.
   * If the student has enough KP (maxKP) then they are able to use this ability.
   * If this method is called without enough KP an exception will be thrown.
   * Allows the character to heal themselves, and gain more EP then normal.
   * The method, changes HP, KP and EP accordingly.
   */
  public void highVoltageEngineering() throws Exception {
    if (this.currentKP == maxKP) {
      this.currentKP = 0;
      System.out.println(this.getName() + " is using High Voltage Engineering!");
      //System.out.println("current hp" + this.getHP());
      System.out.println(this.getName() + " got " + this.getDefence() + " hp");
      this.increaseEP(6);
      this.increaseHP(this.getDefence());
      //System.out.println(this.getName() + " now has " + this.getHP() + " hp");
    } else {
      throw new Exception("Max KP not reached");
    }
  }
}
