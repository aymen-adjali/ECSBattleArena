public class Main {
  public static final boolean VERBOSE = false;

  public static void main(String[] args) throws Exception {

    Character aymen = new SEStudent("Aymen");
    Character oltea = new SEStudent("Oltea");
    Character svetlozar = new SEStudent("Svetlozar");
    Character joe = new SEStudent("Joe");
    Character ibrahim = new SEStudent("Ibrahim");

    Enemy ardyn = new Minion("Ardyn");
    Enemy bahamut = new Minion("Bahamut");
    Enemy gilgamesh = new Minion("Gilgamesh");
    Enemy necron = new Minion("Necron");
    Enemy ultimecia = new Boss("Ultimecia");

    Team studentteam = new StudentTeam("challengers");
    studentteam.addMember(aymen);
    studentteam.addMember(oltea);
    studentteam.addMember(svetlozar);
    studentteam.addMember(joe);
    studentteam.addMember(ibrahim);

    Team monsterteam = new MonsterTeam("bosses");
    monsterteam.addMember(ardyn);
    monsterteam.addMember(bahamut);
    monsterteam.addMember(gilgamesh);
    monsterteam.addMember(necron);
    monsterteam.addMember(ultimecia);

    Battle battle = new Battle(studentteam, monsterteam);

    Team winningTeam = battle.fight();
    System.out.println(winningTeam.getName() + " won this battle");
  }
}