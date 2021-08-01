/**
 * The StudentTeam class is a subclass of Team
 * Inside StudentTeam the program is told what
 * to do/which ability to use in the case of each type
 * of student.
 */
public class StudentTeam extends Team implements Colours {

  /**
   * Instantiates a new Student team.
   * And now implements the abstract move method (from Team class).
   */
  public StudentTeam(String name) {
    super(name);
  }

  /**
   * Move method.
   * Takes in a character, and a Team.
   * The method will then check which type of Student it is,
   * and chose either an ability, or a basic attack (depending on KP).
   * Other methods are implemented here in order to make the decisions
   * "smart", and to not waste abilities.
   * For example it is pointless to heal a friend if they already have max HP.
   */
  public void move(Character member, Team enemyTeam) throws Exception {
    if (member instanceof CompStudent) {
      if (member instanceof CyberStudent) {
        if (((CyberStudent) member).currentKP == CyberStudent.maxKP) {
          System.out.println(blue + member.getName() + reset + " is choosing a special Cyber ability...!");
          ((CyberStudent) member).cyberAttack(enemyTeam);
        } else {
          int a = getRandomNumberUsingNextInt(1, 3);
          if (a == 2) {
            ((CyberStudent) member).javaProgramming(enemyTeam.getEnemyWithLeastHP(enemyTeam));
          } else {
            ((CyberStudent) member).selfStudy();
          }
        }
      } else if (member instanceof AIStudent) {
        if (((AIStudent) member).currentKP == ((AIStudent.maxKP))) {
          System.out.println(blue + member.getName() + reset + " is choosing a special AI ability...!");
          if (member.getHP() > 0 && (member.getHP() < (member.getMaxHP() * 0.6))) {
            ((AIStudent) member).naturalLanguageProcessing();
            System.out.println();
          } else {
            ((AIStudent) member).machineLearning(enemyTeam.getEnemyWithLeastHP(enemyTeam));
          }
        } else {
          int a = getRandomNumberUsingNextInt(1, 3);
          if (a == 2) {
            ((AIStudent) member).javaProgramming(enemyTeam.getEnemyWithLeastHP(enemyTeam));
          } else {
            ((AIStudent) member).selfStudy();
          }
        }
      } else if (member instanceof CSStudent) {
        if (((CSStudent) member).currentKP == ((CSStudent.maxKP)) && !lastOneAlive(member.getTeam())) {
          System.out.println(blue + member.getName() + reset + " is choosing a special CS ability...!");
          if (member.getTeam().getCharacterWithLeastHp(member.getTeam()).getHP() < ((member.getTeam().getCharacterWithLeastHp(member.getTeam()).getMaxHP()) * 0.6) && !member.getTeam().getCharacterWithLeastHp(member.getTeam()).equals(member)) {
            ((CSStudent) member).support(member.getTeam().getCharacterWithLeastHp(member.getTeam()));
            System.out.println();
          } else {
            ((CSStudent) member).pairWorking(member.getTeam().getCharacterWithMostATK(member), enemyTeam.getEnemyWithMostATK(enemyTeam));
          }
        } else {
          int a = getRandomNumberUsingNextInt(1, 3);
          if (a == 2) {
            ((CSStudent) member).javaProgramming(enemyTeam.getEnemyWithLeastHP(enemyTeam));
          } else {
            ((CSStudent) member).selfStudy();
          }
        }
      } else if (member instanceof SEStudent) {
        if (((SEStudent) member).currentKP == ((SEStudent.maxKP))) {
          System.out.println(blue + member.getName() + reset + " is choosing a special SE ability...!");
          if (!lastOneAlive(member.getTeam())) {
            int c = 0;
            for (Character character : member.getTeam().getMembers()) {
              if ((character.getHP() < (character.getMaxHP() * 0.7)) && !character.equals(member) && c < 1) {
                c++;
                ((SEStudent) member).groupDiscussion();
              }
            }
            if (c == 0) {
              ((SEStudent) member).groupWork(enemyTeam.getEnemyWithMostHP(enemyTeam));
            }
          } else {
            ((SEStudent) member).groupWork(enemyTeam.getEnemyWithMostHP(enemyTeam));
          }
        } else {
          int a = getRandomNumberUsingNextInt(1, 3);
          if (a == 2) {
            ((SEStudent) member).javaProgramming(enemyTeam.getEnemyWithLeastHP(enemyTeam));
          } else {
            ((SEStudent) member).selfStudy();
          }
        }
      }
    } else if (member instanceof ElecStudent) {
      if (member instanceof AEStudent) {
        if (((AEStudent) member).currentKP == AEStudent.maxKP) {
          System.out.println(blue + member.getName() + reset + " is choosing a special AE ability...!");//ill keep this for jokes
          ((AEStudent) member).autonomousFlyingVehicles(enemyTeam);
        } else {
          int a = getRandomNumberUsingNextInt(1, 3);
          if (a == 2) {
            ((AEStudent) member).cProgramming(enemyTeam.getEnemyWithLeastHP(enemyTeam));
          } else {
            ((AEStudent) member).individualResearch();
          }
        }
      } else if (member instanceof ElectricalEStudent) {
        if (((ElectricalEStudent) member).currentKP == ((ElectricalEStudent.maxKP))) {
          System.out.println(blue + member.getName() + reset + " is choosing a special Electrical ability...!");
          if (member.getHP() > 0 && (member.getHP() < (member.getMaxHP() * 0.5))) {
            ((ElectricalEStudent) member).highVoltageEngineering();
            System.out.println();
          } else {
            ((ElectricalEStudent) member).capacitorDischarge(enemyTeam.getEnemyWithLeastHP(enemyTeam));
          }
        } else {
          int a = getRandomNumberUsingNextInt(1, 3);
          if (a == 2) {
            ((ElectricalEStudent) member).cProgramming(enemyTeam.getEnemyWithLeastHP(enemyTeam));
          } else {
            ((ElectricalEStudent) member).individualResearch();
          }
        }
      } else if (member instanceof ElectronicEStudent) {
        if (((ElectronicEStudent) member).currentKP == ((ElectronicEStudent.maxKP)) && !lastOneAlive(member.getTeam())) {
          System.out.println(blue + member.getName() + reset + " is choosing a special Electronic ability...!");
          if ((member.getTeam().getCharacterWithLeastHp(member.getTeam()).getHP() < (member.getTeam().getCharacterWithLeastHp(member.getTeam()).getMaxHP() * 0.55)) && !member.getTeam().getCharacterWithLeastHp(member.getTeam()).equals(member)) {
            ((ElectronicEStudent) member).assist(member.getTeam().getCharacterWithLeastHp(member.getTeam()));
            System.out.println();
          } else {
            ((ElectronicEStudent) member).pairSoldering(member.getTeam().getCharacterWithMostATK(member), enemyTeam.getEnemyWithMostATK(enemyTeam), enemyTeam.getEnemyWithLeastHP(enemyTeam));
          }
        } else {
          int a = getRandomNumberUsingNextInt(1, 3);
          if (a == 2) {
            ((ElectronicEStudent) member).cProgramming(enemyTeam.getEnemyWithLeastHP(enemyTeam));
          } else {
            ((ElectronicEStudent) member).individualResearch();
          }
        }
      } else if (member instanceof BEStudent) {
        if (((BEStudent) member).currentKP == ((BEStudent.maxKP))) {
          System.out.println(blue + member.getName() + reset + " is choosing a special BE ability...!");
          if (!lastOneAlive(member.getTeam())) {
            int c = 0;
            for (Character character : member.getTeam().getMembers()) {
              if ((character.getHP() < (character.getMaxHP() * 0.7)) && !character.equals(member) && c < 1) {
                c++;
                ((BEStudent) member).biomedicalControl();
              }
            }
            if (c == 0) {
              ((BEStudent) member).neuroscience(enemyTeam.getEnemyWithMostHP(enemyTeam));
            }
          } else {
            ((BEStudent) member).neuroscience(enemyTeam.getEnemyWithMostHP(enemyTeam));
          }
        } else {
          int a = getRandomNumberUsingNextInt(1, 3);
          if (a == 2) {
            ((BEStudent) member).cProgramming(enemyTeam.getEnemyWithLeastHP(enemyTeam));
          } else {
            ((BEStudent) member).individualResearch();
          }
        }
      }
    }
  }

  /**
   * onlyOneAlive method.
   * Takes in a team as a parameter and returns true if the character
   * calling the method is the only one left alive in the team.
   * This method is used to make sure that abilities that require
   * friends cant be used if there is no alive friend.
   */
  public Boolean lastOneAlive(Team team) {
    int i = 0;
    for (Character n : team.getMembers()) {
      if (isDead(n)) {
        i++;
      }
    }
    //true if only one alive
    return i == team.getMembers().length - 1;
  }
}
