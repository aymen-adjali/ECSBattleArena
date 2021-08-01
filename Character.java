/**
 * The character class has shared methods and characteristics for both students and enemies (and any other added types)
 */
public class Character implements Comparable<Character>, Colours {

  //declaring member variables
  private final String name;
  private final int baseHP;
  private final int baseAtk;
  private final int baseDef;
  private final int baseSpd;
  protected int level;
  private int currentHP;
  private int currentEP;

  public Team team;

  /**
   * Instantiates a new Character.
   * All new characters should start at level one, so level is set to 1 initially.
   * All new new characters should start with max hp for level one,
   * so currentHP is set to baseHP initially.
   */
  public Character(String name, int baseHP, int baseAtk, int baseDef, int baseSpd) {
    this.name = name;
    this.baseHP = baseHP;
    this.baseAtk = baseAtk;
    this.baseDef = baseDef;
    this.baseSpd = baseSpd;
    this.level = 1;
    this.currentHP = baseHP;
  }

  /**
   * A set of accessor methods.
   * Some using the formula given too calculate the attributes that change according to the level of the character.
   */
  public String getName() {
    return this.name;
  }

  public int getMaxHP() {
    return (int) Math.round(this.baseHP * Math.pow(this.level, 1.2));
  }

  public int getAttack() {
    return (int) Math.round(this.baseAtk * Math.pow(this.level, 1.2));
  }

  public int getDefence() {
    return (int) Math.round(this.baseDef * Math.pow(this.level, 1.2));
  }

  public int getSpeed() {
    return (int) Math.round(this.baseSpd * Math.pow(this.level, 1.2));
  }

  public int getTargetEP() {
    return (int) Math.round(10 * Math.pow(this.level, 1.5));
  }

  public int getHP() {
    return this.currentHP;
  }

  public int getEP() {
    return this.currentEP;
  }

  public int getLevel() {
    return this.level;
  }

  /**
   * Increase hp method.
   * Increases the characters HP by the amount specified in the "amount" parameter.
   * Checks if the character is alive, and if the currentHP is larger then the maxHP first.
   * If the currentHP + the amount given is larger then the maxHP, the HP is set to maxHP
   * to avoid the HP going above HP.
   * Otherwise, HP is added normally.
   */
  public void increaseHP(int amount) {
    if (this.currentHP > 0 && this.currentHP <= this.getMaxHP()) {
      if (this.currentHP + amount > this.getMaxHP()) {
        this.currentHP = getMaxHP();
      } else
        this.currentHP += amount;
    }
  }

  /**
   * Decrease hp method.
   * Decreases the characters HP by the amount specified in the "amount" parameter.
   * Checks if the character is alive, and if the currentHP is larger then the maxHP first.
   * If the currentHP - the amount given is smaller then 0, the HP is set to 0
   * to avoid the HP going below 0.
   * Otherwise, HP is subtracted normally.
   */
  public void decreaseHP(int amount) {
    if (this.currentHP > 0 && this.currentHP <= this.getMaxHP()) {
      if (this.currentHP - amount < 0) {
        this.currentHP = 0;
      } else
        this.currentHP -= amount;
    }
  }

  /**
   * Increase ep method.
   * Increases the characters EP by the amount specified in the "amount" parameter.
   * If the character gets the targetEP, they level up.
   * However I check after if the character HP is 0 because sometimes character
   * can level up whilst being attack.
   * (I do not want out output they have levled up if they are dead)
   */
  public void increaseEP(int amount) {
    this.currentEP += amount;
    if (this.currentEP >= getTargetEP()) {
      if (getHP() != 0) {
        System.out.println();
        System.out.println(this.getName() + " just leveled up to level " + (level + 1) + "!");
      } else {
        //System.out.println("leveled up but dead so not output");
      }
      this.level++;
      this.currentHP = getMaxHP();
      this.currentEP = 0;
    }
  }

  /**
   * Decrease hp method.
   * Decreases the characters HP by the amount specified in the "amount" parameter.
   * Checks if the character is alive, and if the currentHP is larger then the maxHP first.
   * If the currentHP - the amount given is smaller then 0, the HP is set to 0
   * to avoid the HP going below 0.
   * Otherwise, HP is subtracted normally.
   */
  public void decreaseEP(int amount) {
    if (this.currentEP - amount < 0) {
      this.currentEP = 0;
    } else
      this.currentEP -= amount;
  }

  /**
   * Set team method
   */
  public void setTeam(Team team) {
    this.team = team;
  }

  /**
   * Get team method
   */
  public Team getTeam() {
    return this.team;
  }

  /**
   * Check student method.
   * This is used is some abilities where depending on the class of the enemy, certain things happen
   */
  public void checkStudent(Character n) {
    if (n instanceof Student) {
      ((Student) n).increaseKP(3);
    }
  }

  /**
   * isDead method.
   * Takes in a character as a parameter and returns true if they are dead,
   * (if the current HP is 0).
   */
  public Boolean isDead(Character character) {
    return character.getHP() <= 0;
  }

  /**
   * isEveryoneAlive method.
   * Takes in a team as a parameter and returns false
   * when there is at least one teammate dead.
   */
  public Boolean isEveryoneAlive(Team team) {
    int i = 0;
    for (Character character : team.getMembers()) {
      if (!isDead(character)) {
        i++;
      }
    }
    return i == team.getMembers().length;
  }

  /**
   * Compare method.
   * I used the method to order my characters in ascending order of speed
   * which is used to determine the order of attack in the battle class.
   */
  @Override
  public int compareTo(Character o) {
    if (this.getSpeed() > o.getSpeed()) {
      return -1;
    } else if (this.getSpeed() < o.getSpeed()) {
      return 1;
    }
    return 0;
  }
}
