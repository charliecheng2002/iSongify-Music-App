import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.io.IOException;
import java.util.Scanner;

/**
 * The test class for the Backend
 */
public class BackendDeveloperTests {
  
  @Test
  public void firstTest() {
    IterableRedBlackTree<SongInterface> newTree = new IterableRedBlackTree<>();
    Backend backend1 = new Backend(newTree);
    try {
      backend1.readData("songs.csv");
      Assertions.assertTrue(backend1.getAllSongs().get(3).equals("Partition"));
      Assertions.assertEquals(backend1.getSongsSize(), 600);
    } catch (IOException e) {
      // TODO Auto-generated catch block

      System.out.println("error!");
    }
    
    
  }
  
  @Test
  public void secondTest() {
    IterableRedBlackTree<SongInterface> newTree = new IterableRedBlackTree<>();
    Backend backend1 = new Backend(newTree);
    try {
      backend1.readData("songs.csv");
      backend1.getRange(-4, -2);

      Assertions.assertEquals(backend1.getFilteredSongsSize(), 179);

      Assertions.assertTrue(backend1.getAllFilteredSongs().get(5).equals("What I Need (feat. Kehlani)"));
    } catch (IOException e) {
      // TODO Auto-generated catch block
      System.out.println("error!");
    }
    
  }


  @Test
  public void thirdTest() {
    IterableRedBlackTree<SongInterface> newTree = new IterableRedBlackTree<>();
    Backend backend1 = new Backend(newTree);
    try {
      backend1.readData("songs.csv");
      backend1.filterByGenre("dance pop");
      backend1.getRange(-3, -2);
      Assertions.assertEquals(backend1.getFilteredSongsSize(), 25);
      Assertions.assertTrue(backend1.getAllFilteredSongs().get(9).equals("Don't Be so Hard on Yourself"));


    } catch (IOException e) {
      // TODO Auto-generated catch block
      System.out.println("error!");
    }
    
    
  }
  
  @Test
  public void fourthTest() {
    IterableRedBlackTree<SongInterface> newTree = new IterableRedBlackTree<>();
    Backend backend1 = new Backend(newTree);
    try {
      backend1.readData("songs.csv");
      backend1.filterByGenre("atl hip hop");
      backend1.getRange(-5, -2);
      Assertions.assertEquals(backend1.getFilteredSongsSize(), 4);
      Assertions.assertTrue(backend1.getAllFilteredSongs().get(2).equals("Castle Walls (feat. Christina Aguilera)"));

    } catch (IOException e) {
      // TODO Auto-generated catch block
      System.out.println("error!");
    }
    
  }
  
 
  
  @Test
  public void  fifthTest() {
    IterableRedBlackTree<SongInterface> newTree = new IterableRedBlackTree<>();
    Backend backend1 = new Backend(newTree);
    try {
      backend1.readData("songs.csv");
      backend1.getRange(-5, -1);
      Assertions.assertEquals(backend1.getFilteredSongsSize(), 328);

      Assertions.assertTrue(backend1.fiveMostLive().get(2).equals("Cooler Than Me - Single Mix"));

    } catch (IOException e) {


      System.out.println("error!");
    }
    
  }
  
  
	@Test
	public void PartnerTestOne() {
			
		TextUITester tester = new TextUITester("R\nmySongData.csv\nQ\n");
	                
		IterableRedBlackTree<SongInterface> tree = new IterableRedBlackTree<>();
		Backend backend = new Backend(tree);
		Scanner in = new Scanner(System.in);
		Frontend frontend = new Frontend(in,backend);
			

	        frontend.runCommandLoop();
        	Assertions.assertTrue(tester.checkOutput().contains("Done reading file."));
		
		
	}
	
	
	
	@Test
	public void PartnerTestTwo() {
		IterableRedBlackTree<SongInterface> tree = new IterableRedBlackTree<>();
                Backend backend = new Backend(tree);
                TextUITester tester = new TextUITester("Q\n");
		Scanner in = new Scanner(System.in);
                Frontend frontend = new Frontend(in, backend);
		
		frontend.runCommandLoop();
		
		Assertions.assertTrue(tester.checkOutput().contains("Choose command: "));
		
		
	}
	
	
	@Test
	public void IntegrationTestOne() {
		IterableRedBlackTree<SongInterface> tree = new IterableRedBlackTree<>();
                Backend backend = new Backend(tree);
		
		
		TextUITester tester = new TextUITester("R\nsongs.csv\nG\n-5 - 3\nQ\n");
		Scanner in = new Scanner(System.in);
                Frontend frontend = new Frontend(in, backend);
		frontend.runCommandLoop();
		Assertions.assertTrue(tester.checkOutput().contains("Hello"));
		//Assertions.assertTrue(tester.checkOutput().contains("If I Had You"));
		
		
	}

	
	@Test
        public void IntegrationTestTwo() {
                IterableRedBlackTree<SongInterface> tree = new IterableRedBlackTree<>();
                Backend backend = new Backend(tree);


                TextUITester tester = new TextUITester("R\nsongs.csv\nG\n-5 - 3\nF\ndance pop\nQ\n");
                Scanner in = new Scanner(System.in);
                Frontend frontend = new Frontend(in, backend);
                frontend.runCommandLoop();
                Assertions.assertTrue(tester.checkOutput().contains("Blah Blah Blah (feat. 3OH!3)"));          
		


        }
  
  
  
}



