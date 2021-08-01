import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The TowerOfMonster class allows there to be large scale battles,
 * with one guild fighting multiple waves of enemy teams.
 */
public class TowerOfMonsters implements Colours {

  private BufferedReader reader;

  /**
   * Instantiates a new Tower of monsters.
   * And takes in a String, which is the file name
   */
  public TowerOfMonsters(String name) {
    try {
      this.reader = new BufferedReader(new FileReader(name));
    } catch (FileNotFoundException e) {
      System.err.println("File not found...");
      System.exit(1);
    }
  }

  /**
   * File is ready method.
   * returns true is file is ready,
   * Catches IOException.
   */
  public boolean fileIsReady() {
    try {
      return reader.ready();
    } catch (IOException e) {
      System.err.println("File is not ready...");
    }
    return false;
  }

  /**
   * Get line method.
   * returns the line if there is one to return,
   * if not, it means its checked the end of the file.
   * Catches IOException.
   */
  public String getLine() {
    try {
      return reader.readLine();
    } catch (IOException e) {
      System.err.println("Nothing left to read...");
    }
    return null;
  }

  /**
   * ParseMonster method.
   * Takes in a string (everythingAboutMonster) which is got from makingMonsterTeams(),
   * and then splits it into the correct separate parts.
   * After that, it checks which type of Enemy it is, and then creates the
   * corresponding one. With the correct name, and with the correct level using the
   * setLevel method.
   * Returns an Enemy object.
   */
  public Enemy parseMonster(String everythingAboutMonster) {
    String[] nameAndRest = everythingAboutMonster.split("\\(");
    String name = nameAndRest[0];
    String andRest = nameAndRest[1];

    String[] typeAndLevel = andRest.split(",");
    String type = typeAndLevel[0];
    int level = Integer.parseInt(typeAndLevel[1].split("\\)")[0]);

    Enemy enemy;
    if (type.equals("Minion")) {
      enemy = new Minion(name);
      enemy.setLevel(level);
    } else if (type.equals("Boss")) {
      enemy = new Boss(name);
      enemy.setLevel(level);
    } else {
      System.err.println("Please make a valid type of enemy");
      enemy = null;
    }
    return enemy;
  }

  /**
   * makingMonsterTeams method.
   * Uses the getLine method to read the file given to the TowerOfMonster object.
   * Splits each line into a string containing information of just one monster.
   * Then call the previous method parseMonster() to get it in the correct format.
   * Lastly the method adds the monsters to the array list and returns it.
   */
  public ArrayList<MonsterTeam> makingMonsterTeams() {
    ArrayList<MonsterTeam> monsterTeams = new ArrayList<MonsterTeam>();
    String line;
    int level = 0;
    while ((line = getLine()) != null) {
      level++;
      MonsterTeam teamForLevel = new MonsterTeam("Level " + level + " Monster Team");
      String[] parts = line.split(";");
      String everythingAboutMonster = parts[0];
      for (String typeNameAndLevel : parts) {
        Character monster = parseMonster(typeNameAndLevel);
        teamForLevel.addMember(monster);
      }
      monsterTeams.add(teamForLevel);
    }
    return monsterTeams;
  }

  /**
   * The Main method.
   * Here, all the characters (students) are created, and added to a guild.
   * Then a TowerOfMonster object is created with the txt file with the monster information passed to it.
   * run tower is then called on the object.
   */
  public static void main(String[] args) {
    if (args.length == 0) {
      System.err.println("There are no arguments...");
      return;
    }
    String fileName = args[0];

    //Creating one Student of each type.
    Character aymen = new CSStudent("Aymen");
    Character oltea = new SEStudent("Oltea");
    Character svetlozar = new CyberStudent("Svetlozar");
    Character joe = new AIStudent("Joe");
    Character miguel = new ElectricalEStudent("Miguel");
    Character albert = new ElectronicEStudent("Albert");
    Character viktoria = new AEStudent("Viktoria");
    Character fran = new BEStudent("Fran");

    Guild studentGuild = new Guild();

    //Guild containing one of each Student type.
    studentGuild.addMember(aymen);
    studentGuild.addMember(oltea);
    studentGuild.addMember(svetlozar);
    studentGuild.addMember(joe);
    studentGuild.addMember(miguel);
    studentGuild.addMember(albert);
    studentGuild.addMember(viktoria);
    studentGuild.addMember(fran);

    TowerOfMonsters tower = new TowerOfMonsters(fileName);
    tower.runTower(studentGuild);
  }

  private int c = 0;

  /**
   * runTower method.
   * runTower will loop while there are still levels available (lines in the txt file),
   * and while there are still students in the guild.
   * The method starts a battle between the Teams for each floor.
   * And outputs the winner and if the students progress to the next floor.
   * If they dont win the battle, they can try again until they have no one left alive in the guild.
   * When the students beat the last level, they have beaten the TowerOfMonsters
   */
  public void runTower(Guild guild) {

    story();

    ArrayList<MonsterTeam> listOfTeams = this.makingMonsterTeams();
    while (c < listOfTeams.size() && !isEveryoneInGuildDead(guild)) {

      printCurrentLevel();

      MonsterTeam monsterTeam = listOfTeams.get(c);
      printOutInfoAboutTeam(monsterTeam);

      Team studentTeam;
      studentTeam = guild.getTeam(monsterTeam);
      printOutInfoAboutTeam(studentTeam);

      System.out.println("The battle for floor " + (c + 1) + " will commence... " + studentTeam.getName() + " VS " + monsterTeam.getName() + "!");
      Battle battle = new Battle(studentTeam, monsterTeam);
      if (battle.fight().equals(studentTeam)) {
        c++;
        if (c >= listOfTeams.size()) {
          System.out.println(studentTeam.getName() + " have defeated " + monsterTeam.getName());
          System.out.println(studentTeam.getName() + " have successfully defeated the Tower of Monsters!");
        } else {
          System.out.println(studentTeam.getName() + " have defeated " + monsterTeam.getName() + " and progress to floor number " + (c + 1) + "!");
        }
      } else {
        if (!isEveryoneInGuildDead(guild)) {
          System.out.println(studentTeam.getName() + " were defeated by " + monsterTeam.getName() + " and have to retry floor number " + (c + 1) + "!");
        } else {
          System.out.println(studentTeam.getName() + " were defeated by " + monsterTeam.getName() + " on floor " + (c + 1) + "!");
          System.out.println("The guild has perished... there are no more students able to fight...");
          System.out.println(studentTeam.getName() + " were unable to defeat the Tower of Monsters!");
        }
      }
    }
  }

  /**
   * Story method.
   * Decoration
   */
  public void story() {
    System.out.println();
    System.out.println("Your guild is exploring an abandoned tower, be careful...");
  }

  /**
   * printCurrentLevel method.
   * Decoration
   */
  public void printCurrentLevel() {
    System.out.println();
    System.out.println("*****************************************\n" +
        purple + "                Floor " + (c + 1) + reset + "\n" +
        "*****************************************\n");
  }

  /**
   * printOutInfoAboutTeam method.
   * Decoration
   */
  public void printOutInfoAboutTeam(Team team) {
    if (team instanceof StudentTeam) {
      System.out.println("Gathering a student challenger team!");
      System.out.println(team.getName() + " contains " + team.getMembers().length + " members:");
    } else if (team instanceof MonsterTeam) {
      System.out.println("There are " + team.getMembers().length + " enemies blocking your path!");
      System.out.println("Defeat them to venture further up into the tower...");
      System.out.println();
      System.out.println("Monsters:");
    }
    for (Character member : team.getMembers()) {
      System.out.println((member.getClass() + "").replace("class ", "") + ", " + member.getName());
    }
    System.out.println();
  }

  /**
   * isEveryoneInGuildDead method.
   * Takes in a guild as a parameter and returns false
   * when there is at least one member alive in the guild.
   */
  public Boolean isEveryoneInGuildDead(Guild guild) {
    int i = 0;
    for (Character character : guild.getMembers()) {
      if (isDead(character)) {
        i++;
      }
    }
    return i == guild.getMembers().size();
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
}

