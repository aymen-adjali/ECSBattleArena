import java.util.ArrayList;

public class MonsterTeam extends Team {

  /**
   * Instantiates a new Monster team.
   * And now implements the abstract move method (from Team class).
   */
  public MonsterTeam(String name) {
    super(name);
  }

  /**
   * Move method.
   * Takes in a character, and a Team.
   * The method will then check which type of monster it is,
   * Although the only move they can use is Strike, the probabilities and
   * attacks available are different.
   * Implementing other methods, the Strike method is only able to randomly chose
   * from available alive enemies.
   * There is no logic for the monsters move method, all attacks are based on probabilities and the
   * people they attack are randomly chosen.
   */
  public void move(Character member, Team enemyTeam) {
    ArrayList<Character> aliveMembers = whoIsAlive(enemyTeam);
    int randomAlivePersonInEnemyTeam = getRandomNumberUsingNextInt(0, aliveMembers.size());
    if (member instanceof Minion) {
      ((Minion) member).strike(aliveMembers.get(randomAlivePersonInEnemyTeam));
    } else if (member instanceof Boss) {
      ((Boss) member).strike(aliveMembers.get(randomAlivePersonInEnemyTeam));
    }
  }

  /**
   * whoIsAlive method.
   * Takes in a Team as a parameter and returns an
   * array list (of Character objects) with only the
   * alive members in it.
   * This is to stop the monsters attacking an enemy that is already dead.
   */
  public ArrayList<Character> whoIsAlive(Team enemyTeam) {
    ArrayList<Character> onlyAliveMembers = new ArrayList<Character>();

    for (Character character : enemyTeam.getMembers()) {
      if (character.getHP() > 0) {
        onlyAliveMembers.add(character);
      }
    }
    return onlyAliveMembers;
  }
}
