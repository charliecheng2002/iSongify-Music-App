runBDTests:
	javac -cp .:../junit5.jar Backend.java BackendDeveloperTests.java
	java -jar ../junit5.jar -cp . --scan-classpath -n BackendDeveloperTests

runApp:
	rm *.class
	javac SortedCollectionInterface.java
	javac BinarySearchTree.java
	javac -cp .:../junit5.jar RedBlackTree.java
	javac -cp .:../junit5.jar IterableRedBlackTree.java
	javac SongInterface.java
	javac BackendInterface.java
	javac FrontendInterface.java
	javac Song.java
	javac Backend.java
	javac Frontend.java
	javac App.java
	java App


clean:
	rm *.class
