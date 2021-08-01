import java.util.*;

/**
 * The Battle class allows Two Teams to fight, using the
 * move method.
 */
public class Battle implements Colours {
  private int roundNum;
  Team nice, naughty;

  /**
   * Instantiates a new Battle.
   * Which takes in Two teams.
   */
  public Battle(Team nice, Team naughty) {
    this.nice = nice;
    this.naughty = naughty;
  }

  /**
   * Fight method.
   * Inside this method, all members of both Teams are added to one array list,
   * and are then sorted by ascending order of speed. (using a previous method).
   * This allowed the characters with the highest speed to attack first.
   * The fight method loops through while the round is smaller then 31, or until
   * all members of one team die.
   * At this point the method returns the winning team, or null (if its a draw).
   */
  public Team fight() {
    ArrayList<Character> everyoneInArrayList = new ArrayList<Character>();
    everyoneInArrayList.addAll(Arrays.asList(nice.getMembers()));
    everyoneInArrayList.addAll(Arrays.asList(naughty.getMembers()));

    try {
      System.out.println();
      while (roundNum < 31) {
        printCurrentRound();
        Collections.sort(everyoneInArrayList);
        Character characterToMove;
        for (int i = 0; i < everyoneInArrayList.size(); i++) {
          characterToMove = everyoneInArrayList.get(i);
          if (characterToMove.getHP() > 0) {
            if (characterToMove instanceof Student) {
              try {
                nice.move(characterToMove, naughty);
              } catch (Exception e) {
                return naughty;
              }
            } else if (characterToMove instanceof Enemy) {
              try {
                naughty.move(characterToMove, nice);
              } catch (Exception e) {
                System.err.println("This should not happen... Monsters don't have KP");
                return nice;
              }
            }
          }
          if (isEveryoneDead(nice)) {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("Round report:");
            roundReport(nice);
            roundReport(naughty);
            System.out.println();
            return naughty;
          } else if (isEveryoneDead(naughty)) {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("Round report:");
            roundReport(nice);
            roundReport(naughty);
            System.out.println();
            return nice;
          }
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Round report:");
        roundReport(nice);
        roundReport(naughty);
        System.out.println();
        Thread.sleep(50);
        roundNum++;
      }
      return null;
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      throw new RuntimeException(e);
    }
  }

  /**
   * roundReport method.
   * Prints out the status of every character (dead or alive), current HP,
   * current level and current KP (just for students). (only if they are alive) after each round.
   * The characters are ordered by HP (descending order).
   */
  public void roundReport(Team team) {
    ArrayList<Character> characters = new ArrayList<Character>();
    for (Character someone : team.getMembers()) {
      characters.add(someone);
    }
    //ArrayList<Character> characters = new ArrayList<Character>(Arrays.asList(team.getMembers()));
    for (int i = 0; i < characters.size() - 1; i++) {
      for (int j = i; j < characters.size(); j++)
        if (characters.get(i).getHP() == 0)
          Collections.swap(characters, i, j);
    }
    for (int i = 0; i < characters.size() - 1; i++) {
      for (int j = i; j < characters.size(); j++)
        if (characters.get(i).getHP() < characters.get(j).getHP())
          Collections.swap(characters, i, j);
    }

    for (Character character : characters) {
      System.out.println("~~~~~~~~~~~~~~~~~~~~~~~");
      if (isDead(character)) {
        if (character instanceof Enemy) {
          System.out.println(red + character.getName() + reset + " is dead...");
        } else if (character instanceof Student) {
          System.out.println(blue + character.getName() + reset + " is dead...");
        }
      } else {
        if (character instanceof Enemy) {
          System.out.println(red + character.getName() + reset + " is alive...");
        } else if (character instanceof Student) {
          System.out.println(blue + character.getName() + reset + " is alive...");
          System.out.println("current KP: " + ((Student) character).currentKP + "/" + ((Student) character).getMaxKP());
        }
        System.out.println("current HP: " + character.getHP() + "/" + character.getMaxHP());
        System.out.println("current Level: " + character.getLevel());
      }
    }
    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~");
  }

  /**
   * printCurrentRound method.
   * Prints out the current round.
   */
  public void printCurrentRound() {
    System.out.println("--------------\n" +
        green + "   Round " + (roundNum + 1) + reset + "\n" +
        "--------------\n");
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
   * isEveryoneAlive method.
   * Takes in a team as a parameter and returns false
   * when there is at least one teammate alive.
   */
  public Boolean isEveryoneDead(Team team) {
    int i = 0;
    for (Character character : team.getMembers()) {
      if (isDead(character)) {
        i++;
      }
    }
    return i == team.getMembers().length;
  }
}
