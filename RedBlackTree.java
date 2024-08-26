// == CS400 Spring 2024 File Header Information ==
// Name: cheng274
// Email: cheng274@wisc.edu
// Lecturer: Florian Heimerl
// Notes to Grader:

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


/**
 * Class that executes the RedBlackTree
 */
public class RedBlackTree<T extends Comparable<T>> extends BinarySearchTree<T> {



  protected static class RBTNode<T> extends Node<T> {
    public boolean isBlack = false;

    public RBTNode(T data) {
      super(data);
    }

    public RBTNode<T> getUp() {
      return (RBTNode<T>) this.up;
    }

    public RBTNode<T> getDownLeft() {
      return (RBTNode<T>) this.down[0];
    }

    public RBTNode<T> getDownRight() {
      return (RBTNode<T>) this.down[1];
    }
  }

  
  
  protected void enforceRBTreePropertiesAfterInsert(RBTNode<T> newNode) {
    RBTNode<T> parent = (RedBlackTree.RBTNode<T>) newNode.up;
    RBTNode<T> grandparent;
    
    if (parent != null) {
      grandparent = (RedBlackTree.RBTNode<T>) parent.up;
    } 
    else {
      grandparent = null;
    }
    
    RBTNode<T> aunt;
    if (grandparent == null) {
      aunt = null;
    }
    
    else if (grandparent.getDownLeft() == parent) {
      aunt = (RedBlackTree.RBTNode<T>)grandparent.down[1];
    }
    
    else {
      aunt = (RedBlackTree.RBTNode<T>)grandparent.down[0];
    }
    
    if (newNode.up == null) {
      return;
    }
    
    
    if (!(parent == null || !parent.isBlack)) {
      return;
    }
    
    if (!(aunt == null || aunt.isBlack)) {
      
      parent.isBlack = true;
      aunt.isBlack = true;
      grandparent.isBlack = false;
      
      
      enforceRBTreePropertiesAfterInsert(grandparent);
      return;
    }
    if (parent != null && grandparent != null && newNode == parent.down[1] && parent == grandparent.down[0]) {
      
      this.rotate(newNode, parent);
      
      
      newNode = (RedBlackTree.RBTNode<T>)newNode.down[0];
    }
    else if (parent != null && grandparent != null && newNode == parent.down[0] && parent == grandparent.down[1]) {
      this.rotate(newNode, parent);
      newNode = (RedBlackTree.RBTNode<T>)newNode.down[1];
    }
    parent = (RedBlackTree.RBTNode<T>) newNode.up;
    
    grandparent = (RedBlackTree.RBTNode<T>)parent.up;
    
    this.rotate(parent, grandparent);
    
    parent.isBlack = true;
    grandparent.isBlack = false;
  }

  

  
  
  @Override
  public boolean insert(T data) throws NullPointerException {
    if (data == null)
      throw new NullPointerException("Cannot insert data value null into the tree.");
    //Node<T> node = new Node<>(data);
    RBTNode<T> rbtnode = new RBTNode<T> (data);
    boolean success = this.insertHelper(rbtnode);
    this.enforceRBTreePropertiesAfterInsert(rbtnode);
    RBTNode<T> rbtroot = (RBTNode<T>) root;
    rbtroot.isBlack = true;
    return success;
  }
  
  
  
  
  
  @Test
  public void firstTest() {
    RedBlackTree<Integer> newRBT = new RedBlackTree<>();
    newRBT.insert(3);
    
    newRBT.insert(7);
    newRBT.insert(5);
    newRBT.insert(1);
    newRBT.insert(4);
    
    
    RBTNode<Integer> rbtRoot = (RedBlackTree.RBTNode<Integer>) newRBT.root;
    Assertions.assertTrue(newRBT.toInOrderString().equals("[ 1, 3, 4, 5, 7 ]"));

    Assertions.assertTrue(rbtRoot.isBlack);
    Assertions.assertFalse(rbtRoot.getDownLeft().getDownLeft().isBlack);
    
    Assertions.assertEquals(newRBT.size(), 5);
    
  }
  
  @Test
  public void secondTest() {
    RedBlackTree<Integer> newRBT = new RedBlackTree<>();
    newRBT.insert(7);
    newRBT.insert(4);
    newRBT.insert(8);
    
    newRBT.insert(2);
    
    newRBT.insert(1);
    
    newRBT.insert(5);
    
    
    RBTNode<Integer> rbtRoot = (RedBlackTree.RBTNode<Integer>) newRBT.root;
    Assertions.assertTrue(newRBT.toInOrderString().equals("[ 1, 2, 4, 5, 7, 8 ]"));
    
    Assertions.assertTrue(rbtRoot.isBlack);
    
    Assertions.assertTrue(rbtRoot.getDownLeft().getDownLeft().isBlack);
    Assertions.assertFalse(rbtRoot.getDownLeft().getDownRight().getDownRight().isBlack);
    
    
    
    
  }
  
  @Test
  public void thirdTest() {
    RedBlackTree<Integer> newRBT = new RedBlackTree<>();
    newRBT.insert(9);
    
    newRBT.insert(3);
    
    newRBT.insert(7);
    
    newRBT.insert(5);
    
    newRBT.insert(6);
    
    
    RBTNode<Integer> rbtRoot = (RedBlackTree.RBTNode<Integer>) newRBT.root;
    Assertions.assertTrue(newRBT.toInOrderString().equals("[ 3, 5, 6, 7, 9 ]"));
    
    Assertions.assertTrue(rbtRoot.isBlack);
    
    Assertions.assertFalse(rbtRoot.getDownLeft().getDownLeft().isBlack);
    
    Assertions.assertTrue(rbtRoot.getDownRight().isBlack);
    
  }
  
  
  

}
