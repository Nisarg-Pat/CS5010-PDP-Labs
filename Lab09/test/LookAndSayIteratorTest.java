import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;
import java.util.NoSuchElementException;

import lookandsay.LookAndSayIterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Test cases for {@link LookAndSayIterator}.
 * Covers all of the scenarios possible for the methods of LookAndSayIterator.
 */
public class LookAndSayIteratorTest {

  LookAndSayIterator iteratorNext;
  LookAndSayIterator iteratorPrevious;

  @Before
  public void setup() {
    iteratorNext = new LookAndSayIterator(new BigInteger("123"), new BigInteger("9".repeat(10)));
    iteratorPrevious = new LookAndSayIterator(new BigInteger("31121113"));
  }

  @Test
  public void testConstructorInvalid() {
    try {
      new LookAndSayIterator(new BigInteger("-1"), new BigInteger("999"));
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid seed: -1", e.getMessage());
    }

    try {
      new LookAndSayIterator(new BigInteger("1234321"), new BigInteger("9999"));
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid seed: 1234321", e.getMessage());
    }

    try {
      new LookAndSayIterator(new BigInteger("1001"));
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid seed: 1001", e.getMessage());
    }
  }

  @Test
  public void testNext() {
    assertTrue(iteratorNext.hasNext());
    assertEquals(new BigInteger("123"), iteratorNext.next());
    assertTrue(iteratorNext.hasNext());
    assertEquals(new BigInteger("111213"), iteratorNext.next());
    assertTrue(iteratorNext.hasNext());
    assertEquals(new BigInteger("31121113"), iteratorNext.next());
    assertTrue(iteratorNext.hasNext());
    assertEquals(new BigInteger("1321123113"), iteratorNext.next());
    assertFalse(iteratorNext.hasNext());

    try {
      iteratorNext.next();
      fail();
    } catch (NoSuchElementException e) {
      assertEquals("No next number in the sequence.", e.getMessage());
    }
  }

  @Test
  public void testPrev() {
    assertTrue(iteratorPrevious.hasPrevious());
    assertEquals(new BigInteger("111213"), iteratorPrevious.prev());
    assertTrue(iteratorPrevious.hasPrevious());
    assertEquals(new BigInteger("123"), iteratorPrevious.prev());
    assertFalse(iteratorPrevious.hasPrevious());

    try {
      iteratorPrevious.prev();
      fail();
    } catch (NoSuchElementException e) {
      assertEquals("No previous number in the sequence.", e.getMessage());
    }
  }
}