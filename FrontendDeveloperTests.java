import org.junit.jupiter.api.Test;
import java.util.Scanner;
import org.junit.jupiter.api.Assertions;

public class FrontendDeveloperTests {

  /**
   * Tests for R(read data)
   */
  @Test
  public void test1() {
    IterableSortedCollection tree = new ISCPlaceholder();
    BackendInterface backend = new BackendPlaceholder(tree);
    TextUITester tester = new TextUITester("R\nmySongData.csv\nQ\n");
    Frontend frontend = new Frontend(new Scanner(System.in), backend);

    frontend.runCommandLoop();


    String output = tester.checkOutput();

    if (!output.contains("Done reading file.")) {

      Assertions.fail("Issue with read:");

    }
  }

  /**
   * Tests the G(get values)
   */
  @Test
  public void test2() {
    IterableSortedCollection tree = new ISCPlaceholder();
    BackendInterface backend = new BackendPlaceholder(tree);
    TextUITester tester = new TextUITester("R\nmySongData.csv\nG\n80 - 90\nQ\n");
    Frontend frontend = new Frontend(new Scanner(System.in), backend);
    
    frontend.runCommandLoop();


    String output = tester.checkOutput();

    if (!output.contains("5 songs found between 80 - 90:\n"
        + "Hey, Soul Sister\n"
        + "Love The Way You Lie\n"
        + "TiK ToK\n"
        + "Bad Romance\n"
        + "Just the Way You Are")) {

      Assertions.fail("Issue with get");

    }
  }

  /**
   * test's F(genre)
   */
  @Test
  public void test3() {
    IterableSortedCollection tree = new ISCPlaceholder();
    BackendInterface backend = new BackendPlaceholder(tree);
    TextUITester tester = new TextUITester("R\nmySongData.csv\nG\n80 - 90\nF\npop\nQ\n");
    Frontend frontend = new Frontend(new Scanner(System.in), backend);

    frontend.runCommandLoop();


    String output = tester.checkOutput();

    if (!output.contains("2 songs found between 80 - 90 in genre pop:\n"
        + "Hey, Soul Sister\n"
        + "Love The Way You Lie")) {

      Assertions.fail("Issue with genre");

    }
  }

  /**
   * tests D(get top 5)
   */
  @Test
  public void test4() {
    IterableSortedCollection tree = new ISCPlaceholder();
    BackendInterface backend = new BackendPlaceholder(tree);
    TextUITester tester = new TextUITester("R\nmySongData.csv\nG\n80 - 90\nF\npop\nD\nQ\n");
    Frontend frontend = new Frontend(new Scanner(System.in), backend);

    frontend.runCommandLoop();


    String output = tester.checkOutput();

    if (!output.contains("Top Five songs found between 80 - 90 in genre pop:\n"
        + "8: Hey, Soul Sister\n"
        + "52: Love The Way You Lie")) {

      Assertions.fail("Issue with top5");

    }
  }

  /**
   * Tests Q(quit)
   */
  @Test
  public void test5() {
    IterableSortedCollection tree = new ISCPlaceholder();
    BackendInterface backend = new BackendPlaceholder(tree);
    TextUITester tester = new TextUITester("Q\n");
    Frontend frontend = new Frontend(new Scanner(System.in), backend);

    frontend.runCommandLoop();


    String output = tester.checkOutput();

    if (!output.contains("Choose command: ")) {

      Assertions.fail("Issue with quit");

    }
  }

}

