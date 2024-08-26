import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Frontend implements FrontendInterface {

  // declare backend and scanner

  Scanner scan;
  BackendInterface back;

  // declare min, max, and genre

  private String min = "min";
  private String max = "max";
  private String genre = "none";

  public Frontend(Scanner in, BackendInterface backend) {
    // initialize backend and scanner
    scan = in;
    back = backend;
  }

  @Override
  public void runCommandLoop() {
    // start main menu
    displayMainMenu();
    // get and store input
    String input = scan.nextLine();
    // based on what the input is, we are going to run the corresponding method
    if (input.equals("R")) {
      readFile();
      runCommandLoop();
    } else if (input.equals("G")) {
      getValues();
      runCommandLoop();
    } else if (input.equals("F")) {
      setFilter();
      runCommandLoop();
    } else if (input.equals("D")) {
      topFive();
      runCommandLoop();
    } else if (input.equals("Q")) {
      return;
    }
    else {
      System.out.println("invalid input");
      runCommandLoop();
    }
  }

  @Override
  public void displayMainMenu() {
    // generate and store menu
    String menu = """

        ~~~ Command Menu ~~~
            [R]ead Data
            [G]et Songs by Loudness dB [min - max]
            [F]ilter By Genre (none)
            [D]isplay Five Most Live
            [Q]uit
        Choose command:""";
    // replace min, max, and genre and print the menu
    menu = menu.replace("min", min).replace("max", max).replace("none", genre);
    System.out.print(menu + " ");
  }

  @Override
  public void readFile() {
    // ask user to enter csv path
    System.out.print("Enter path to csv file to load: ");
    // store path
    String input = scan.nextLine();
    // use a try catch to run the readData function
    try {
      back.readData(input);
    } catch (IOException e) {
      e.printStackTrace();
    }
    // exit message
    System.out.println("Done reading file.");
  }

  @Override
  public void getValues() {
    // ask for range of values
    System.out.print("Enter range of values (MIN - MAX): ");
    // get and store input
    String input = scan.nextLine();
    // parse and store min and max in private variables
    min = input.substring(0, input.indexOf(" "));
    System.out.println(min);
    max = input.substring(input.indexOf(" ") + 3);
    System.out.println(max);
    // get ints of ranges
    int lowInt = Integer.parseInt(min);
    int highInt = Integer.parseInt(max);
    // get arraylist of strings
    List<String> list = back.getRange(lowInt, highInt);
    // print desired output
    String out = list.size() + " songs found between " + min + " - " + max + ":\n";
    for (int i = 0; i < list.size(); i++) {
      if (i == list.size() - 1) {
        out += list.get(i);
      } else {
        out += list.get(i) + "\n";
      }
    }
    System.out.println(out);
  }

  @Override
  public void setFilter() {
    // if min and max not set up then error message
    if (min == "min" || max == "max") {
      System.out.print("set max and min first");
      return;
    }
    // prompt
    System.out.print("Enter genre: ");
    // scan and store
    String input = scan.nextLine();
    // store genre
    genre = input;
    // get output same way as in getValues
    List<String> list = back.filterByGenre(genre);
    // print desired output
    String out =
        list.size() + " songs found between " + min + " - " + max + " in genre " + genre + ":\n";
    for (int i = 0; i < list.size(); i++) {
      if (i == list.size() - 1) {
        out += list.get(i);
      } else {
        out += list.get(i) + "\n";
      }
    }
    System.out.println(out);
  }

  @Override
  public void topFive() {
    // if min and max and genre not set up then error message
    if (min == "min" || max == "max" || genre == "none") {
      System.out.print("set max, min, and genre first");
      return;
    }
 // get output same way as in getValues
    List<String> list = back.fiveMostLive();
    // print desired output
    String out =
        "Top Five songs found between " + min + " - " + max + " in genre " + genre + ":\n";
    for (int i = 0; i < list.size(); i++) {
      if (i == list.size() - 1) {
        out += list.get(i);
      } else {
        out += list.get(i) + "\n";
      }
    }
    System.out.println(out);
  }

}
