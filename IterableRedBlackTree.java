import java.util.Iterator;
import java.util.Stack;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


public class IterableRedBlackTree<T extends Comparable<T>>
    extends RedBlackTree<T> implements IterableSortedCollection<T> {
    
    
      /**
       * Performs a naive insertion into a binary search tree: adding the new node in a leaf position
       * within the tree. After this insertion, no attempt is made to restructure or balance the tree.
       * 
       * @param node the new node to be inserted
       * @return true if the value was inserted, false if is was in the tree already
       * @throws NullPointerException when the provided node is null
       */
      protected boolean insertHelper(Node<T> newNode) throws NullPointerException {
        if (newNode == null)
          throw new NullPointerException("new node cannot be null");
    
        if (this.root == null) {
          // add first node to an empty tree
          root = newNode;
          size++;
          return true;
        } else {
          // insert into subtree
          Node<T> current = this.root;
          while (true) {
            int compare = newNode.data.compareTo(current.data);
//            if (compare == 0) {
//              return false;
//            }
            if (compare <= 0) {
              // insert in left subtree
              if (current.down[0] == null) {
                // empty space to insert into
                current.down[0] = newNode;
                newNode.up = current;
                this.size++;
                return true;
              } else {
                // no empty space, keep moving down the tree
                current = current.down[0];
              }
            } else {
              // insert in right subtree
              if (current.down[1] == null) {
                // empty space to insert into
                current.down[1] = newNode;
                newNode.up = current;
                this.size++;
                return true;
              } else {
                // no empty space, keep moving down the tree
                current = current.down[1];
              }
            }
          }
        }
      }
      
      private Comparable<T> startPoint = t -> -1;
      
      public void setIterationStartPoint(Comparable<T> startPoint) {
        if (startPoint == null) {
          this.startPoint = t -> -1;
        } else {
          
          this.startPoint = startPoint;
        }
        
        
      }
  
      public Iterator<T> iterator() {
        
        return new RBTIterator<T>(root, startPoint);
      }
  
      private static class RBTIterator<R> implements Iterator<R> {
        private Comparable<R> startPoint;
        private Stack<Node<R>> stack;
        
        public RBTIterator(Node<R> root, Comparable<R> startPoint) {
          this.startPoint = startPoint;
          this.stack = new Stack<>();
          buildStackHelper(root);
        }
      
        private void buildStackHelper(Node<R> node) {
          if(node == null) {
            return;
          }
          
          int compare = startPoint.compareTo(node.data);
          
          
          if(compare <=0) {
            
            stack.push(node);
            
            buildStackHelper(node.down[0]);
            
          }
          else {
            buildStackHelper(node.down[1]);
          }
          
          
        }
      
        public boolean hasNext() {
          return !stack.isEmpty();
        }
      
        public R next() {
          if(!hasNext()) {
            throw new NoSuchElementException("No more elements.");
            
          }
          Node<R> node = stack.pop();
          buildStackHelper(node.down[1]);
          
          return node.data;
        }
    }
    
    
    @Test
    public void firstTest() {
      IterableRedBlackTree<Integer> tree = new IterableRedBlackTree<>();
      
      tree.insert(5);
      tree.insert(3);
      tree.insert(7);
      tree.insert(2);
      tree.insert(4);
      tree.insert(6);
      tree.insert(8);
      
      Iterator<Integer> newIterator = tree.iterator();
      StringBuilder newBuilder = new StringBuilder();
      while (newIterator.hasNext()) {
        
        newBuilder.append(newIterator.next()).append(" ");
      }
      
      
      Assertions.assertEquals("2 3 4 5 6 7 8 ", newBuilder.toString());
    }
    
    @Test
    public void secondTest() {
      IterableRedBlackTree<String> tree = new IterableRedBlackTree<>();
      
      tree.insert("hello");
      tree.insert("thank");
      tree.insert("you");
      tree.insert("very");
      tree.insert("much");
      tree.insert("good");
      tree.insert("bye");

      tree.setIterationStartPoint("h");

      Iterator<String> newIterator = tree.iterator();
      StringBuilder newBuilder = new StringBuilder();
      while (newIterator.hasNext()) {

        newBuilder.append(newIterator.next()).append(" ");
      }      
      Assertions.assertEquals("hello much thank very you ", newBuilder.toString());
    }
    
    @Test
    public void thirdTest() {
      
      IterableRedBlackTree<Integer> tree = new IterableRedBlackTree<>();
      tree.insert(5);
      tree.insert(3);
      tree.insert(7);
      tree.insert(2);
      tree.insert(4);
      tree.insert(6);
      tree.insert(8);
      tree.insert(4);
      
      Iterator<Integer> newIterator = tree.iterator();
      StringBuilder newBuilder = new StringBuilder();
      while (newIterator.hasNext()) {

        newBuilder.append(newIterator.next()).append(" ");
      }

      Assertions.assertEquals("2 3 4 4 5 6 7 8 ", newBuilder.toString());
    }
    
    
    
    

}
