
package bst;

import java.util.List;

interface BSTNode<T extends Comparable<T>> {
  BSTNode<T> add(T data);

  int size();

  int height();

  boolean present(T data);

  T minimum();

  T minimumHelper(T data);

  T maximum();

  T maximumHelper(T data);

  List<T> preOrder();

  List<T> preOrderHelper(List<T> preOrder);

  List<T> inOrder();

  List<T> inOrderHelper(List<T> inOrder);

  List<T> postOrder();

  List<T> postOrderHelper(List<T> postOrder);
}