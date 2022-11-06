package bst;

import java.util.ArrayList;
import java.util.List;

class BSTGroupNode<T extends Comparable<T>> implements BSTNode<T> {
  private final T data;
  private BSTNode<T> left;
  private BSTNode<T> right;

  BSTGroupNode(T data, BSTNode<T> left, BSTNode<T> right) {
    this.data = data;
    this.left = left;
    this.right = right;
  }

  @Override
  public BSTNode<T> add(T data) {
    if (this.data.compareTo(data) < 0) {
      right = this.right.add(data);
    } else if (this.data.compareTo(data) > 0) {
      left = this.left.add(data);
    }
    return this;
  }

  @Override
  public int size() {
    return 1 + left.size() + right.size();
  }

  @Override
  public int height() {
    return 1 + Math.max(left.height(), right.height());
  }

  @Override
  public boolean present(T data) {
    if (this.data.compareTo(data) == 0) {
      return true;
    } else if (this.data.compareTo(data) < 0) {
      return right.present(data);
    } else {
      return left.present(data);
    }
  }

  @Override
  public T minimum() {
    return left.minimumHelper(this.data);
  }

  @Override
  public T minimumHelper(T data) {
    return left.minimumHelper(this.data);
  }

  @Override
  public T maximum() {
    return right.maximumHelper(this.data);
  }

  @Override
  public T maximumHelper(T data) {
    return right.maximumHelper(this.data);
  }

  @Override
  public List<T> preOrder() {
    return preOrderHelper(new ArrayList<>());
  }

  @Override
  public List<T> preOrderHelper(List<T> preOrder) {
    preOrder.add(this.data);
    this.left.preOrderHelper(preOrder);
    this.right.preOrderHelper(preOrder);
    return preOrder;
  }

  @Override
  public List<T> inOrder() {
    return inOrderHelper(new ArrayList<>());
  }

  @Override
  public List<T> inOrderHelper(List<T> inOrder) {
    this.left.inOrderHelper(inOrder);
    inOrder.add(this.data);
    this.right.inOrderHelper(inOrder);
    return inOrder;
  }

  @Override
  public List<T> postOrder() {
    return postOrderHelper(new ArrayList<>());
  }

  @Override
  public List<T> postOrderHelper(List<T> postOrder) {
    this.left.postOrderHelper(postOrder);
    this.right.postOrderHelper(postOrder);
    postOrder.add(data);
    return postOrder;
  }
}