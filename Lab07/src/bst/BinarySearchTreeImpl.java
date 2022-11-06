package bst;

import java.util.stream.Collectors;

/**
 * Generic Implementation of {@link BinarySearchTree}.
 * It implements the basic functionality that a Binary Search Tree ADT should have.
 * The elements on the left have smaller value than elements on the right.
 * Inorder traversal will provide the elements in an increasing order.
 * No duplicate elements are present in this implementation.
 *
 * @param <T> The type of the data each element will be having in the tree.
 */
public class BinarySearchTreeImpl<T extends Comparable<T>> implements BinarySearchTree<T> {
  private BSTNode<T> root;

  /**
   * Constructor for BinarySearchTree class. Creates an empty tree.
   */
  public BinarySearchTreeImpl() {
    root = new BSTLeafNode<>();
  }

  @Override
  public void add(T data) {
    root = root.add(data);
  }

  @Override
  public int size() {
    return root.size();
  }

  @Override
  public int height() {
    return root.height();
  }

  @Override
  public boolean present(T data) {
    return root.present(data);
  }

  @Override
  public T minimum() {
    return root.minimum();
  }

  @Override
  public T maximum() {
    return root.maximum();
  }

  @Override
  public String preOrder() {
    return "[" + root.preOrder().stream().map(t -> t.toString())
            .collect(Collectors.joining(" ")) + "]";
  }

  @Override
  public String inOrder() {
    return "[" + root.inOrder().stream().map(t -> t.toString())
            .collect(Collectors.joining(" ")) + "]";
  }

  @Override
  public String postOrder() {
    return "[" + root.postOrder().stream().map(t -> t.toString())
            .collect(Collectors.joining(" ")) + "]";
  }

  @Override
  public String toString() {
    return inOrder();
  }
}
