import org.junit.Test;

import transmission.AutomaticTransmission;
import transmission.Transmission;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for {@link AutomaticTransmission}.
 */
public class AutomaticTransmissionTest {
  private final Transmission t1 = new AutomaticTransmission(10, 20, 30, 40, 50);

  /**
   * Test case for out of order threshold1.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorThreshold1Fail() {
    new AutomaticTransmission(40, 20, 30, 40, 50);
  }

  /**
   * Test case for out of order threshold2.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorThreshold2Fail() {
    new AutomaticTransmission(10, 40, 30, 40, 50);
  }

  /**
   * Test case for out of order threshold3.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorThreshold3Fail() {
    new AutomaticTransmission(10, 20, 40, 40, 50);
  }

  /**
   * Test case for out of order threshold4.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorThreshold4Fail() {
    new AutomaticTransmission(10, 20, 30, 20, 50);
  }

  /**
   * Test case for out of order threshold5.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorThreshold5Fail() {
    new AutomaticTransmission(10, 20, 30, 40, 0);
  }

  /**
   * Test case for negative threshold1.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorThreshold1Negative() {
    new AutomaticTransmission(-10, 20, 30, 40, 50);
  }

  /**
   * Test case for negative threshold2.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorThreshold2Negative() {
    new AutomaticTransmission(10, -20, 30, 40, 50);
  }

  /**
   * Test case for negative threshold3.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorThreshold3Negative() {
    new AutomaticTransmission(10, 20, -30, 40, 50);
  }

  /**
   * Test case for negative threshold4.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorThreshold4Negative() {
    new AutomaticTransmission(10, 20, 30, -40, 50);
  }

  /**
   * Test case for negative threshold5.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorThreshold5Negative() {
    new AutomaticTransmission(10, 20, 30, 40, -50);
  }


  /**
   * Test case for {@link AutomaticTransmission#increaseSpeed()}.
   */
  @Test
  public void testIncreaseSpeed() {
    assertEquals("Transmission (speed = 0, gear = 0)", t1.toString());
    t1.increaseSpeed();
    assertEquals("Transmission (speed = 1, gear = 1)", t1.toString());
    for (int i = 0; i < 9; i++) {
      t1.increaseSpeed();
    }
    assertEquals("Transmission (speed = 10, gear = 2)", t1.toString());
    for (int i = 0; i < 39; i++) {
      t1.increaseSpeed();
    }
    assertEquals("Transmission (speed = 49, gear = 5)", t1.toString());
    t1.increaseSpeed();
    assertEquals("Transmission (speed = 50, gear = 6)", t1.toString());
  }

  /**
   * Test case for {@link AutomaticTransmission#decreaseSpeed()}.
   */
  @Test
  public void testDecreaseSpeed() {
    for (int i = 0; i < 50; i++) {
      t1.increaseSpeed();
    }
    assertEquals("Transmission (speed = 50, gear = 6)", t1.toString());
    t1.decreaseSpeed();
    assertEquals("Transmission (speed = 49, gear = 5)", t1.toString());
    for (int i = 0; i < 39; i++) {
      t1.decreaseSpeed();
    }
    assertEquals("Transmission (speed = 10, gear = 2)", t1.toString());
    t1.decreaseSpeed();
    assertEquals("Transmission (speed = 9, gear = 1)", t1.toString());
    for (int i = 0; i < 9; i++) {
      t1.decreaseSpeed();
    }
    assertEquals("Transmission (speed = 0, gear = 0)", t1.toString());
  }

  /**
   * Test case for {@link AutomaticTransmission#decreaseSpeed()} called when speed is 0.
   */
  @Test(expected = IllegalStateException.class)
  public void testDecreaseSpeedFail() {
    t1.decreaseSpeed();
  }

  /**
   * Test case for {@link AutomaticTransmission#toString()}.
   */
  @Test
  public void testToString() {
    assertEquals("Transmission (speed = 0, gear = 0)", t1.toString());
  }

}