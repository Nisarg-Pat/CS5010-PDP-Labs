package polynomial;

import java.util.Objects;

class Term implements Comparable<Term> {
  private final int coefficient;
  private final int power;

  protected Term(int coefficient, int power) {
    if (power < 0) {
      throw new IllegalArgumentException("The power should be >= 0.");
    }
    this.coefficient = coefficient;
    this.power = power;
  }

  protected int getCoefficient() {
    return coefficient;
  }

  protected int getPower() {
    return power;
  }

  protected double evaluate(double x) {
    return coefficient * Math.pow(x, power);
  }

  @Override
  public int compareTo(Term o) {
    return o.power - this.power;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Term)) {
      return false;
    }
    Term term = (Term) o;
    return coefficient == term.coefficient && power == term.power;
  }

  @Override
  public int hashCode() {
    return Objects.hash(coefficient, power);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(coefficient);
    if (power != 0) {
      sb.append("x").append("^").append(power);
    }
    return sb.toString();
  }
}
