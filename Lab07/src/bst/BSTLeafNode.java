package bst;

import java.util.ArrayList;
import java.util.List;

class BSTLeafNode<T extends Comparable<T>> implements BSTNode<T> {

  @Override
  public BSTNode<T> add(T data) {
    return new BSTGroupNode<>(data, this, this);
  }

  @Override
  public int size() {
    return 0;
  }

  @Override
  public int height() {
    return 0;
  }

  @Override
  public boolean present(T data) {
    return false;
  }

  @Override
  public T minimum() {
    return null;
  }

  @Override
  public T minimumHelper(T data) {
    return data;
  }

  @Override
  public T maximum() {
    return null;
  }

  @Override
  public T maximumHelper(T data) {
    return data;
  }

  @Override
  public List<T> preOrder() {
    return new ArrayList<>();
  }

  @Override
  public List<T> preOrderHelper(List<T> preOrder) {
    return preOrder;
  }

  @Override
  public List<T> inOrder() {
    return new ArrayList<>();
  }

  @Override
  public List<T> inOrderHelper(List<T> inOrder) {
    return inOrder;
  }

  @Override
  public List<T> postOrder() {
    return new ArrayList<>();
  }

  @Override
  public List<T> postOrderHelper(List<T> postOrder) {
    return postOrder;
  }
}