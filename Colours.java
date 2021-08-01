/**
 * The interface Colours allows the colour of printed lines to be changed.
 */
public interface Colours {
  String reset = "\u001B[0m";
  String black = "\u001B[30m";  //white not black...
  String red = "\u001B[31m";
  String green = "\u001B[32m";
  String yellow = "\u001B[33m";
  String blue = "\u001B[34m";
  String purple = "\u001B[35m";
  String cyan = "\u001B[36m";
}
