package polynomial;

class EmptyNode implements ListOfTerm {

  @Override
  public ListOfTerm addTerm(Term newTerm) {
    return new TermNode(newTerm, this);
  }

  @Override
  public String toString() {
    return "0";
  }

  @Override
  public boolean equalsEmptyNode(EmptyNode emptyNode) {
    return true;
  }

  @Override
  public double evaluate(double x) {
    return 0;
  }

  @Override
  public double evaluateHelper(double x, double acc) {
    return acc;
  }

  @Override
  public int getCoefficient(int power) {
    return 0;
  }

  @Override
  public int getDegree() {
    return 0;
  }

  @Override
  public String toStringHelper(StringBuilder sb, boolean addSign) {
    return sb.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ListOfTerm)) {
      return false;
    }
    ListOfTerm other = (ListOfTerm) o;
    return other.equalsEmptyNode(this);
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }
}
