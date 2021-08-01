import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

/**
 * A guild of Character objects are stored in an array list.
 * The guild will be used in the TowerOfMonsters as a Team (max 5)
 * will have to be created from the members of the guild for each battle.
 */
public class Guild {

  public ArrayList<Character> guildMembers;

  /**
   * Instantiates a new Guild,
   */
  public Guild() {
    guildMembers = new ArrayList<Character>();
  }

  /**
   * addMember method.
   * Adds the member specified in the parameter (someone) to the
   * guild array list.
   */
  public void addMember(Character someone) {
    guildMembers.add(someone);
  }

  /**
   * getMembers method.
   * Returns guildMembers as a Collection of Character.
   */
  public Collection<Character> getMembers() {
    return this.guildMembers;
  }

  /**
   * getTeam method.
   * The get team method has been designed to chose 5 (or less) in order of currentHP
   * students with the most HP will be picked first for each round.
   * However is there are less then 5 students in the guild, they will all be added,
   * regardless of HP.
   */
  public Team getTeam(Team enemyTeam) {

    Team teamForLevel = new StudentTeam("Challengers");

    ArrayList<Character> onlyAliveMembersInGuild = getAllAliveMembersInGuild(guildMembers);
    int randomNumber = getRandomNumberUsingNextInt(0, onlyAliveMembersInGuild.size());
    while (teamForLevel.addMember(onlyAliveMembersInGuild.remove(randomNumber)) != -2 && onlyAliveMembersInGuild.size() > 0) {
      randomNumber = getRandomNumberUsingNextInt(0, onlyAliveMembersInGuild.size());
    }
    return teamForLevel;
  }

  /**
   * isDead method.
   * Takes in a character as a parameter and returns true if they are dead,
   * (if the current HP is 0).
   */
  public Boolean isDead(Character n) {
    return n.getHP() <= 0;
  }

  /**
   * allAliveInGuild method.
   * Takes in a character as a parameter and returns true if they are dead,
   * (if the current HP is 0).
   */
  public ArrayList<Character> getAllAliveMembersInGuild(ArrayList<Character> array) {
    ArrayList<Character> allAliveMembers = new ArrayList<Character>();
    for (Character character : array) {
      if (!isDead(character)) {
        allAliveMembers.add(character);
      }
    }
    return allAliveMembers;
  }

  /**
   * method that gets a random number.
   */
  public int getRandomNumberUsingNextInt(int min, int max) {
    Random random = new Random();
    return random.nextInt(max - min) + min;
  }
}
//method to sort guild members by speed.
//    Collections.sort(guildMembers, (guildmember, guildmember2) -> {
//
//      if(guildmember.getHP() > guildmember2.getHP()){
//        return -1;
//      } else if(guildmember2.getHP() > guildmember.getHP()){
//        return 1;
//      } else {
//        return 0;
//      }
//    } );