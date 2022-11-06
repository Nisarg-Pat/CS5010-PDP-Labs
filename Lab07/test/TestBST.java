
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import bst.BinarySearchTree;
import bst.BinarySearchTreeImpl;

import org.junit.Before;
import org.junit.Test;

/**
 * Test cases for {@link BinarySearchTreeImpl}.
 * Covers all the cases that could occur in a binary search tree implementation.
 */
public class TestBST {
  private BinarySearchTree<Integer> intBST;
  private BinarySearchTree<Integer> emptyBST;

  @Before
  public void setup() {
    emptyBST = new BinarySearchTreeImpl<>();
    intBST = new BinarySearchTreeImpl<>();
    intBST.add(5);        //           (5)
    intBST.add(3);        //         /    \
    intBST.add(7);        //       (3)     (7)
    intBST.add(1);        //      /  \    /  \
    intBST.add(0);        //    (1)  (4) (6)  (9)
    intBST.add(4);        //   /  \          /
    intBST.add(9);        // (0)  (2)     (8)
    intBST.add(2);
    intBST.add(8);
    intBST.add(6);
  }

  @Test
  public void testConstructor() {
    emptyBST = new BinarySearchTreeImpl<>();
    assertEquals("[]", emptyBST.toString());
  }

  @Test
  public void testAdd() {
    BinarySearchTree<Integer> bst = new BinarySearchTreeImpl<>();
    bst.add(5);
    assertEquals("[5]", bst.preOrder());
    assertEquals("[5]", bst.inOrder());
    assertEquals("[5]", bst.postOrder());
    assertEquals("[5]", bst.toString());
    bst.add(3);
    bst.add(7);
    bst.add(1);
    bst.add(0);
    bst.add(4);
    bst.add(9);
    bst.add(2);
    bst.add(8);
    bst.add(6);
    assertEquals("[5 3 1 0 2 4 7 6 9 8]", bst.preOrder());
    assertEquals("[0 1 2 3 4 5 6 7 8 9]", bst.inOrder());
    assertEquals("[0 2 1 4 3 6 8 9 7 5]", bst.postOrder());
    assertEquals("[0 1 2 3 4 5 6 7 8 9]", bst.toString());
    bst.add(9);
    bst.add(5);
    assertEquals("[5 3 1 0 2 4 7 6 9 8]", bst.preOrder());
    assertEquals("[0 1 2 3 4 5 6 7 8 9]", bst.inOrder());
    assertEquals("[0 2 1 4 3 6 8 9 7 5]", bst.postOrder());
    assertEquals("[0 1 2 3 4 5 6 7 8 9]", bst.toString());
  }

  @Test
  public void testSize() {
    assertEquals(0, emptyBST.size());
    assertEquals(10, intBST.size());
  }

  @Test
  public void testPresent() {
    assertTrue(intBST.present(5));
    assertTrue(intBST.present(0));
    assertTrue(intBST.present(7));
    assertTrue(intBST.present(9));
    assertTrue(intBST.present(6));
    assertTrue(intBST.present(4));

    assertFalse(intBST.present(-5));
    assertFalse(intBST.present(10));

  }

  @Test
  public void testMinimum() {
    int minimum = intBST.minimum();
    assertEquals(0, minimum);

    assertNull(emptyBST.minimum());
  }

  @Test
  public void testMaximum() {
    int maximum = intBST.maximum();
    assertEquals(9, maximum);

    assertNull(emptyBST.maximum());
  }


  @Test
  public void testPreOrder() {
    assertEquals("[]", emptyBST.preOrder());
    assertEquals("[5 3 1 0 2 4 7 6 9 8]", intBST.preOrder());
  }

  @Test
  public void testInOrder() {
    assertEquals("[]", emptyBST.inOrder());
    assertEquals("[0 1 2 3 4 5 6 7 8 9]", intBST.inOrder());
  }

  @Test
  public void testPostOrder() {
    assertEquals("[]", emptyBST.postOrder());
    assertEquals("[0 2 1 4 3 6 8 9 7 5]", intBST.postOrder());
  }

  @Test
  public void testHeight() {
    assertEquals(0, emptyBST.height());
    assertEquals(4, intBST.height());

    BinarySearchTree<Integer> differentHeightBST = new BinarySearchTreeImpl<>();
    differentHeightBST.add(10);
    differentHeightBST.add(4);
    differentHeightBST.add(3);
    differentHeightBST.add(12);
    differentHeightBST.add(15);
    differentHeightBST.add(18);
    differentHeightBST.add(20);
    assertEquals(5, differentHeightBST.height());
  }

  @Test
  public void testToString() {
    assertEquals("[]", emptyBST.toString());
    assertEquals("[0 1 2 3 4 5 6 7 8 9]", intBST.toString());
  }


}