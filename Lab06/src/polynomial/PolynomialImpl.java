package polynomial;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * One-variable Implementation of {@link Polynomial}.
 * Each term is an integer power of the variable.
 * Each term has a coefficient and a power.
 * The terms maintain a specific order.
 * The term with higher power precedes the term with lower power.
 * Each term in the polynomial has unique power.
 */
public class PolynomialImpl implements Polynomial {

  private ListOfTerm head;

  /**
   * Creates a polynomial with no terms: Polynomial 0.
   */
  public PolynomialImpl() {
    this.head = new EmptyNode();
  }

  /**
   * Creates a polynomial with the terms mentioned in the String.
   * If polynomials in the string has same power,
   * their coefficients are added to form a single term.
   *
   * @param polynomialString The string of terms that should be added in the polynomial.
   * @throws IllegalArgumentException when the string is not of the proper format.
   */
  public PolynomialImpl(String polynomialString) {
    this();
    if (polynomialString == null) {
      throw new IllegalArgumentException("The string input is not proper.");
    }
    Scanner sc = new Scanner(polynomialString);
    while (sc.hasNext()) {
      String term = sc.next();
      if (!Pattern.matches("[+-]?\\p{Digit}+(x\\^\\p{Digit}+)?", term)) {
        throw new IllegalArgumentException("The string input is not proper.");
      }
      String[] newTerm = term.split("x\\^");
      int coefficient = Integer.parseInt(newTerm[0]);
      int power = 0;
      if (newTerm.length == 2) {
        power = Integer.parseInt(newTerm[1]);
      }
      if (coefficient == 0 || power < 0) {
        throw new IllegalArgumentException("The string input is not proper.");
      }
      addTerm(coefficient, power);
    }
  }

  @Override
  public Polynomial add(Polynomial other) throws IllegalArgumentException {
    if (!(other instanceof PolynomialImpl)) {
      throw new IllegalArgumentException("The Polynomial types do not match.");
    }
    PolynomialImpl that = (PolynomialImpl) other;
    Polynomial polynomial = new PolynomialImpl();
    int power = Math.max(this.getDegree(), that.getDegree());
    while (power >= 0) {
      int coefficient = this.getCoefficient(power) + that.getCoefficient(power);
      if (coefficient != 0) {
        polynomial.addTerm(coefficient, power);
      }
      power--;
    }
    return polynomial;
  }

  /**
   * Add a term to this polynomial with the specified coefficient and power.
   * If the polynomial already has a term with same power, their coefficients are added.
   *
   * @param coefficient the coefficient of the term to be added
   * @param power       the power of the term to be added
   * @throws IllegalArgumentException if the power is negative
   */
  @Override
  public void addTerm(int coefficient, int power) throws IllegalArgumentException {
    if (power < 0) {
      throw new IllegalArgumentException("The power should be >= 0.");
    }
    if (coefficient != 0) {
      head = head.addTerm(new Term(coefficient, power));
    }
  }

  @Override
  public boolean isSame(Polynomial poly) {
    if (this == poly) {
      return true;
    }
    if (!(poly instanceof PolynomialImpl)) {
      return false;
    }
    PolynomialImpl other = (PolynomialImpl) poly;
    return this.head.equals(other.head);
  }

  @Override
  public double evaluate(double x) {
    return head.evaluate(x);
  }

  @Override
  public int getCoefficient(int power) {
    return head.getCoefficient(power);
  }

  @Override
  public int getDegree() {
    return head.getDegree();
  }

  @Override
  public String toString() {
    return head.toString();
  }
}
