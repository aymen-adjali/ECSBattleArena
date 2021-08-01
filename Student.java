/**
 * The student class is a subclass of Character.
 * Inside Student there are the common methods which all students can use.
 * KP is also introduced here as it only applies to students (not monsters).
 */
public class Student extends Character implements Colours {

  public int currentKP;
  private final int maxKP;

  /**
   * Instantiates a new Student.
   * Introduces KP to the students attributes.
   */
  public Student(String name, int baseHP, int baseAtk, int baseDef, int baseSpd, int maxKP) {
    super(name, baseHP, baseAtk, baseDef, baseSpd);

    this.maxKP = maxKP;
  }

  /**
   * A set of accessor methods.
   */
  public int getCurrentKP() {
    return this.currentKP;
  }

  public int getMaxKP() {
    return this.maxKP;
  }

  /**
   * Increase kp method.
   * Increases the characters KP by the amount specified in the "amount" parameter.
   * KP is not able to go over the max.
   */
  public void increaseKP(int amount) {
    if (this.currentKP + amount > maxKP) {
      this.currentKP = maxKP;
    } else {
      this.currentKP += amount;
    }
  }
}
