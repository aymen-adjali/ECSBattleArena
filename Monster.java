/**
 * The interface Monster contains all the abilities which can be used by monsters.
 * Although not all monsters can use the same abilities.
 */
interface Monster {
  void strike(Character enemy);

  void SyntaxError(Character enemy);

  void NullPointerException();

  void ArrayIndexOutOfBoundException(Character enemy);

  void NoneTermination(Team team);

  void ConcurrentModificationException(Team team);

}
