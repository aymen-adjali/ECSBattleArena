import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * The team class forms the base for the simulation, charcters need to be able to be added,
 * and viewed in the team. I have added some other methods which take "Team" as a parameter.
 */
public abstract class Team {

  private final String name;
  public ArrayList<Character> members;

  /**
   * Instantiates a new Team.
   * Creates an array list which holds Character objects
   */
  public Team(String name) {
    this.name = name;
    members = new ArrayList<Character>();
  }

  public String getName() {
    return this.name;
  }

  /**
   * Get members method
   * The array list of Characters is converted to an array and is returned.
   */
  public Character[] getMembers() {
    return this.members.toArray(new Character[0]);
  }

  /**
   * Add member method.
   * Takes in a character as a parameter, and tried to add them to the team,
   * depending the scenarios, the return value is changed accordingly.
   * (Teams should be no larger then 5, and there can be no duplicate members).
   */
  public int addMember(Character member) {
    int status = 0;
    if (this.members.contains(member)) {
      status = -1;
    } else if (this.members.size() >= 5) {
      status = -2;
    } else {
      member.setTeam(this);
      this.members.add(member);
      status = this.members.size();
    }
    return status;
  }

  /**
   * Returns the character with least hp in the team that is passed to it.
   * Used inside my move method.
   */
  public Character getCharacterWithLeastHp(Team team) {
    int lowestHP = Integer.MAX_VALUE;
    Character leastHp = null;
    for (Character character : team.getMembers()) {
      if (character.getHP() < lowestHP && character.getHP() > 0) {
        lowestHP = character.getHP();
        leastHp = character;
      }
    }
    return leastHp;
  }

  /**
   * Returns the character with most attack.
   * Used inside my move method.
   */
  public Character getCharacterWithMostATK(Character character) {
    int maxAtk = Integer.MIN_VALUE;
    Character mostAtk = null;
    for (Character member : getMembers()) {
      if (!member.equals(character)) {
        if (member.getAttack() > maxAtk && member.getHP() > 0) {
          maxAtk = member.getAttack();
          mostAtk = member;
        }
      }
    }
    return mostAtk;
  }

  /**
   * Returns the enemy with most hp in the team that is passed to it.
   * Used inside my move method.
   */
  public Character getEnemyWithMostHP(Team team) {
    int maxHP = Integer.MIN_VALUE;
    Character mostHp = null;
    for (Character character : team.getMembers()) {
      if (character.getHP() > maxHP && character.getHP() > 0) {
        maxHP = character.getHP();
        mostHp = character;
      }
    }
    return mostHp;
  }

  /**
   * Returns the enemy with most attack in the team that is passed to it.
   * Used inside my move method.
   */
  public Character getEnemyWithMostATK(Team team) {
    int maxATK = Integer.MIN_VALUE;
    Character mostAtk = null;
    for (Character character : team.getMembers()) {
      if (character.getAttack() > maxATK && character.getHP() > 0) {
        maxATK = character.getAttack();
        mostAtk = character;
      }
    }
    return mostAtk;
  }

  /**
   * Returns the enemy with least hp in the team that is passed to it.
   * Used inside my move method.
   */
  public Character getEnemyWithLeastHP(Team team) {
    int lowestHP = Integer.MAX_VALUE;
    Character leastHP = null;
    for (Character x : team.getMembers()) {
      if (x.getHP() < lowestHP && x.getHP() > 0) {
        lowestHP = x.getHP();
        leastHP = x;
      }
    }
    return leastHP;
  }

  /**
   * method that gets a random number.
   * Used in the move method (for monsters)
   */
  public int getRandomNumberUsingNextInt(int min, int max) {
    Random random = new Random();
    return random.nextInt(max - min) + min;
  }

  /**
   * isDead method.
   * Takes in a character as a parameter and returns true if they are dead,
   * (if the current HP is 0).
   * Used in multiple classes which are extended from Team.
   */
  public Boolean isDead(Character character) {
    return character.getHP() <= 0;
  }

  /**
   * Abstract move method.
   * Because move is implemented different for Students and Monsters
   */
  public abstract void move(Character member, Team enemyTeam) throws Exception;
}

//comment this
//  public List<Character> getAlive(){
//    return this.members.stream().filter(member -> member.getHP() > 0).collect(Collectors.toList());
//
//  }
