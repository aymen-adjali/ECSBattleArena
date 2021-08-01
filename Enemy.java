/**
 * The Enemy class is a subclass of Character.
 * Inside Enemy there are the common methods which all enemies can use.
 * Enemy implements Monster because not all subclasses of Enemy use the same abilities.
 */
public class Enemy extends Character implements Monster, Colours {

  /**
   * Instantiates a new Enemy.
   */
  public Enemy(String name, int baseHP, int baseAtk, int baseDef, int baseSpd) {
    super(name, baseHP, baseAtk, baseDef, baseSpd);
  }

  /**
   * Set level method.
   * Added so it is possible to create an Enemy at a certain level (not always level 1)
   * This is only available to Monsters... (not students)
   */
  public void setLevel(int level) {
    this.level = level;
  }

  /**
   * Strike method.
   * This is the only ability which is called in the move method, in MonsterTeam
   * It is implemented differently so this method is overridden subclasses of Enemy.
   */
  public void strike(Character enemy) {
    System.out.println(enemy.getName() + " is getting ready to attack...!");
  }

  /**
   * Syntax Error ability.
   * Allows the monster to deal damage to one enemy.
   * The method, changes HP, and EP accordingly.
   */
  public void SyntaxError(Character enemy) {
    System.out.println(red + this.getName() + reset + " is using Syntax Error on " + blue + enemy.getName() + reset);
    System.out.println(enemy.getName() + " just lost " + (100 * this.getAttack() / (100 + enemy.getDefence())) + " hp");
    enemy.decreaseHP(100 * this.getAttack() / (100 + enemy.getDefence()));
    if (enemy.getHP() == 0) {
      System.out.println(enemy.getName() + " has fainted...");
      this.increaseEP(4);
    }
    checkStudent(enemy);
    enemy.increaseEP(3);
    if (enemy.getEP() == 0) {
      enemy.decreaseHP(enemy.getMaxHP());
      //System.out.println(enemy.getName() + " leveled up while dying... so they got the level up but HP is set to O");
    }
    this.increaseEP(3);
    System.out.println();
  }

  /**
   * Null pointed exception ability.
   * Allows the monster to heal themselves.
   * The method, changes HP, and EP accordingly.
   */
  public void NullPointerException() {
    System.out.println(red + this.getName() + reset + " is using Null Pointer Exception!");
    //System.out.println("hp before is "  + this.getHP());
    System.out.println(this.getName() + " just got " + this.getDefence() + " hit points");
    this.increaseHP(this.getDefence());
    this.increaseEP(3);
    System.out.println();
  }

  /**
   * Array index out of bound exception ability.
   * Allows the monster to deal double damage to one enemy.
   * The method, changes HP, and EP accordingly.
   */
  public void ArrayIndexOutOfBoundException(Character enemy) {
    System.out.println(red + this.getName() + reset + " is using Array Index Out Of Bound Exception!");
    System.out.println(enemy.getName() + " just took double damage");
    System.out.println(enemy.getName() + " just lost " + (200 * this.getAttack() / (100 + enemy.getDefence())) + " hp");
    enemy.decreaseHP(200 * this.getAttack() / (100 + enemy.getDefence()));
    if (enemy.getHP() == 0) {
      System.out.println(enemy.getName() + " has fainted...");
      this.increaseEP(4);
    }
    checkStudent(enemy);
    enemy.increaseEP(3);
    //check if they level up after dying
    if (enemy.getEP() == 0) {
      enemy.decreaseHP(enemy.getHP());
      System.out.println(enemy.getName() + " leveled up while dying... so they got the lvl up but HP is set to O");
    }
    this.increaseEP(3);
    System.out.println();
  }

  /**
   * These two methods are not used by all subclasses of Enemy,
   * so the method body can be left empty as these methods are implemented from
   * the Monster interface.
   * They will then be overridden in the correct subclass.
   */
  public void NoneTermination(Team team) {
  }

  public void ConcurrentModificationException(Team team) {
  }
}
