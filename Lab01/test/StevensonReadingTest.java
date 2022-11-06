import org.junit.Before;
import org.junit.Test;

import weather.StevensonReading;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Test cases for {@link StevensonReading}.
 */
public class StevensonReadingTest {

  private StevensonReading s1;
  private StevensonReading s2;

  @Before
  public void setup() {
    s1 = new StevensonReading(23.46, 17.67, 3.34, 12);
    s2 = new StevensonReading(53, 44, 200, 220);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testConstructorFailDewPoint() {
    new StevensonReading(23.46, 44.67, 3.34, 12);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorFailWindSpeed() {
    new StevensonReading(23.46, 17.67, -3.34, 12);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorFailTotalRain() {
    new StevensonReading(23.46, 17.67, 3.34, -12);
  }

  @Test
  public void testGetTemperature() {
    assertEquals(23, s1.getTemperature());
    assertEquals(53, s2.getTemperature());
  }

  @Test
  public void testGetDewPoint() {
    assertEquals(18, s1.getDewPoint());
    assertEquals(44, s2.getDewPoint());
  }

  @Test
  public void testGetWindSpeed() {
    assertEquals(3, s1.getWindSpeed());
    assertEquals(200, s2.getWindSpeed());
  }

  @Test
  public void testGetTotalRain() {
    assertEquals(12, s1.getTotalRain());
    assertEquals(220, s2.getTotalRain());
  }

  @Test
  public void testRelativeHumidity() {
    assertEquals(71, s1.getRelativeHumidity());
    assertEquals(55, s2.getRelativeHumidity());
  }

  @Test
  public void testGetHeatIndex() {
    assertEquals(24, s1.getHeatIndex());
    assertEquals(123, s2.getHeatIndex());
  }

  @Test
  public void testGetWindChill() {
    assertEquals(77, s1.getWindChill());
    assertEquals(159, s2.getWindChill());
  }

  @Test
  public void testToSting() {
    assertEquals("Reading: T = 23, D = 18, v = 3, rain = 12", s1.toString());
    assertEquals("Reading: T = 53, D = 44, v = 200, rain = 220", s2.toString());
  }

  @Test
  public void testHashCode() {
    StevensonReading testReading1 = new StevensonReading(23.46, 17.67, 3.34, 12);
    StevensonReading testReading2 = new StevensonReading(53, 44, 200, 220);

    assertEquals(testReading1.hashCode(), s1.hashCode());
    assertEquals(testReading2.hashCode(), s2.hashCode());
    assertNotEquals(s1.hashCode(), s2.hashCode());
    assertNotEquals(testReading1.hashCode(), testReading2.hashCode());
  }

  @Test
  public void testEquals() {
    StevensonReading testReading1 = new StevensonReading(23.46, 17.67, 3.34, 12);
    StevensonReading testReading2 = new StevensonReading(53, 44, 200, 220);

    assertEquals(testReading1, s1);
    assertEquals(testReading2, s2);
    assertNotEquals(s1, s2);
    assertNotEquals(testReading1, testReading2);
  }
}