package polynomial;

import java.util.Objects;

class TermNode implements ListOfTerm {
  private final Term term;
  private final ListOfTerm next;

  protected TermNode(Term term, ListOfTerm next) {
    this.term = term;
    this.next = next;
  }

  @Override
  public ListOfTerm addTerm(Term newTerm) {
    if (newTerm.compareTo(this.term) < 0) {
      return new TermNode(newTerm, this);
    } else if (term.compareTo(newTerm) == 0) {
      int newCoefficient = this.term.getCoefficient() + newTerm.getCoefficient();
      if (newCoefficient == 0) {
        return next;
      }
      return new TermNode(new Term(newCoefficient, this.term.getPower()), next);
    } else {
      return new TermNode(term, next.addTerm(newTerm));
    }
  }

  @Override
  public boolean equalsTermNode(TermNode termNode) {
    return this.term.equals(termNode.term) && this.next.equals(termNode.next);
  }

  @Override
  public double evaluate(double x) {
    return evaluateHelper(x, 0);
  }

  @Override
  public double evaluateHelper(double x, double acc) {
    return next.evaluateHelper(x, acc + term.evaluate(x));
  }

  @Override
  public int getCoefficient(int power) {
    if (term.getPower() < power) {
      return 0;
    } else if (term.getPower() == power) {
      return term.getCoefficient();
    } else {
      return next.getCoefficient(power);
    }
  }

  @Override
  public int getDegree() {
    return term.getPower();
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
    return other.equalsTermNode(this);
  }

  @Override
  public int hashCode() {
    return Objects.hash(term, next);
  }

  @Override
  public String toString() {
    return toStringHelper(new StringBuilder(), false);
  }

  @Override
  public String toStringHelper(StringBuilder sb, boolean addSign) {
    if (addSign) {
      if (term.getCoefficient() > 0) {
        sb.append(" +");
      } else {
        sb.append(" ");
      }
    }
    return next.toStringHelper(sb.append(term), true);
  }
}
