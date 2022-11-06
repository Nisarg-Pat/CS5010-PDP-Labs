import org.junit.Before;
import org.junit.Test;

import polynomial.Polynomial;
import polynomial.PolynomialImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Test cases for PolynomialImpl class. Covers all the scenarios that
 * could happen when calling different functions related to PolynomialImpl.
 */
public class PolynomialImplTest {

  private Polynomial emptyPolynomial;
  private Polynomial constantPolynomial;
  private Polynomial polynomial1;

  @Before
  public void setup() {
    emptyPolynomial = new PolynomialImpl();

    constantPolynomial = new PolynomialImpl();
    constantPolynomial.addTerm(5, 0);

    polynomial1 = new PolynomialImpl();
    polynomial1.addTerm(4, 3);
    polynomial1.addTerm(-3, 2);
    polynomial1.addTerm(5, 1);
    polynomial1.addTerm(-4, 0);

  }

  @Test
  public void testConstructor() {
    Polynomial newPolynomial = new PolynomialImpl();
    assertEquals("0", newPolynomial.toString());

    newPolynomial = new PolynomialImpl("");
    assertEquals("0", newPolynomial.toString());

    newPolynomial = new PolynomialImpl("4x^3 +3x^1 -5");
    assertEquals("4x^3 +3x^1 -5", newPolynomial.toString());

    newPolynomial = new PolynomialImpl("+3x^4 -2x^5 -5 -2x^4 +11x^1");
    assertEquals("-2x^5 +1x^4 +11x^1 -5", newPolynomial.toString());

    newPolynomial = new PolynomialImpl("-3x^4 -2x^5 -5 +11x^1");
    assertEquals("-2x^5 -3x^4 +11x^1 -5", newPolynomial.toString());

    newPolynomial = new PolynomialImpl("102");
    assertEquals("102", newPolynomial.toString());

    try {
      new PolynomialImpl(null);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("The string input is not proper.", e.getMessage());
    }

    try {
      new PolynomialImpl("abc");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("The string input is not proper.", e.getMessage());
    }

    try {
      new PolynomialImpl("3Y^1");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("The string input is not proper.", e.getMessage());
    }

    try {
      newPolynomial = new PolynomialImpl("4x^3+3x^1-5");
      System.out.println(newPolynomial);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("The string input is not proper.", e.getMessage());
    }
  }

  @Test
  public void testAdd() {

    try {
      polynomial1.add(null);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("The Polynomial types do not match.", e.getMessage());
    }

    assertEquals("4x^3 -3x^2 +5x^1 -4", polynomial1.add(emptyPolynomial).toString());
    assertEquals("4x^3 -3x^2 +5x^1 -4", emptyPolynomial.add(polynomial1).toString());
    assertEquals("0", emptyPolynomial.add(new PolynomialImpl()).toString());

    Polynomial polynomial2 = new PolynomialImpl("3x^5 +2x^3 -5x^1");
    Polynomial polynomial3 = new PolynomialImpl("-2x^2 +7");

    assertEquals("3x^5 +2x^3 -2x^2 -5x^1 +7", polynomial2.add(polynomial3).toString());
    assertEquals("3x^5 +6x^3 -3x^2 -4", polynomial1.add(polynomial2).toString());
    assertEquals("4x^3 -5x^2 +5x^1 +3", polynomial1.add(polynomial3).toString());


  }

  @Test
  public void testAddTerm() {
    try {
      polynomial1.addTerm(3, -2);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("The power should be >= 0.", e.getMessage());
    }

    assertEquals("4x^3 -3x^2 +5x^1 -4", polynomial1.toString());
    polynomial1.addTerm(-5, 1);
    assertEquals("4x^3 -3x^2 -4", polynomial1.toString());
    polynomial1.addTerm(0, 1);
    assertEquals("4x^3 -3x^2 -4", polynomial1.toString());
    polynomial1.addTerm(7, 2);
    assertEquals("4x^3 +4x^2 -4", polynomial1.toString());
    polynomial1.addTerm(5, 4);
    assertEquals("5x^4 +4x^3 +4x^2 -4", polynomial1.toString());
    polynomial1.addTerm(-8, 1);
    assertEquals("5x^4 +4x^3 +4x^2 -8x^1 -4", polynomial1.toString());
  }

  @Test
  public void testIsSame() {
    Polynomial polynomial2 = new PolynomialImpl("4x^3 -3x^2 +5x^1 -4");

    assertTrue(polynomial2.isSame(polynomial1));
    assertTrue(polynomial1.isSame(polynomial2));
    polynomial2.addTerm(1, 1);
    assertFalse(polynomial1.isSame(polynomial2));
    assertFalse(polynomial2.isSame(polynomial1));

    Polynomial polynomial3 = new PolynomialImpl("4x^3 +5x^1");
    Polynomial polynomial4 = new PolynomialImpl("-8x^4 -3x^2 -4");
    assertFalse(polynomial3.isSame(polynomial4));
    assertFalse(polynomial4.isSame(polynomial3));

    assertFalse(polynomial2.isSame(polynomial3));
    assertFalse(polynomial2.isSame(polynomial4));

    assertTrue(emptyPolynomial.isSame(new PolynomialImpl("")));
    assertFalse(polynomial1.isSame(emptyPolynomial));
    assertFalse(emptyPolynomial.isSame(polynomial1));
  }

  @Test
  public void testEvaluate() {
    assertEquals(0, emptyPolynomial.evaluate(3), 0.0);
    assertEquals(5, constantPolynomial.evaluate(-2), 0.0);

    assertEquals(2, polynomial1.evaluate(1), 0.0);

    assertEquals(26, polynomial1.evaluate(2), 0.0);
    assertEquals(-604, polynomial1.evaluate(-5), 0.0);
  }

  @Test
  public void testGetCoefficient() {
    assertEquals(0, emptyPolynomial.getCoefficient(3));

    assertEquals(5, constantPolynomial.getCoefficient(0));
    assertEquals(0, constantPolynomial.getCoefficient(1));

    assertEquals(4, polynomial1.getCoefficient(3));
    assertEquals(-3, polynomial1.getCoefficient(2));
    assertEquals(5, polynomial1.getCoefficient(1));
    polynomial1.addTerm(-5, 1);
    assertEquals(0, polynomial1.getCoefficient(1));
    assertEquals(0, polynomial1.getCoefficient(7));
    assertEquals(-4, polynomial1.getCoefficient(0));
  }

  @Test
  public void testGetDegree() {
    assertEquals(0, emptyPolynomial.getDegree());
    assertEquals(0, constantPolynomial.getDegree());
    assertEquals(3, polynomial1.getDegree());

    Polynomial polynomial3 = new PolynomialImpl("4x^3 +5x^1");
    Polynomial polynomial4 = new PolynomialImpl("-8x^4 -3x^2 -4");
    assertEquals(3, polynomial3.getDegree());
    assertEquals(4, polynomial4.getDegree());
  }

  @Test
  public void testToString() {
    assertEquals("0", emptyPolynomial.toString());
    assertEquals("5", constantPolynomial.toString());

    assertEquals("4x^3 -3x^2 +5x^1 -4", polynomial1.toString());

    polynomial1.addTerm(-5, 1);
    assertEquals("4x^3 -3x^2 -4", polynomial1.toString());

    Polynomial polynomial2 = new PolynomialImpl();

    polynomial2.addTerm(1, 1);
    assertEquals("1x^1", polynomial2.toString());
  }
}