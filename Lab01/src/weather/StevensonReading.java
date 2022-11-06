package weather;

import java.util.Objects;

/**
 * Stevenson Reading implementation of {@link WeatherReading}.
 * Each instance represents a reading from <a href="https://en.wikipedia.org/wiki/Stevenson_screen">Stevenson Screen</a>.
 */
public class StevensonReading implements WeatherReading {
  private final double temperature;
  private final double dewPoint;
  private final double windSpeed;
  private final double totalRain;
  private final double relativeHumidity;
  private final double heatIndex;
  private final double windChill;

  private static final double DELTA = 0.0001; //To compare floating point values.

  /**
   * Constructs a new stevenson reading based on the data collected.
   *
   * @param temperature   The air temperature in Celsius
   * @param dewPoint      The dew point temperature in Celsius which cannot be greater than
   *                      the air temperature
   * @param windSpeed     The non-negative wind speed in miles per hour
   * @param totalRain     The non-negative total rain received in the last 24 hours in millimeters
   * @throws IllegalArgumentException for invalid value of any parameter.
   */
  public StevensonReading(double temperature, double dewPoint, double windSpeed, double totalRain) {
    if (dewPoint > temperature || windSpeed < 0 || totalRain < 0) {
      throw new IllegalArgumentException("Invalid Data, Please check again.");
    }
    this.temperature = temperature;
    this.dewPoint = dewPoint;
    this.windSpeed = windSpeed;
    this.totalRain = totalRain;

    this.relativeHumidity = (dewPoint - temperature + 20) * 5;

    double[] c = {-8.78469475556, 1.61139411, 2.33854883889,
                  -0.14611605, -0.012308094, -0.0164248277778,
                  0.002211732, 0.00072546, -0.000003582};

    this.heatIndex = c[0] + (c[1] * temperature) + (c[2] * relativeHumidity)
            + (c[3] * temperature * relativeHumidity)
            + (c[4] * temperature * temperature)
            + (c[5] * relativeHumidity * relativeHumidity)
            + (c[6] * temperature * temperature * relativeHumidity)
            + (c[7] * temperature * relativeHumidity * relativeHumidity)
            + (c[8] * temperature * temperature * relativeHumidity * relativeHumidity);

    double inFahrenheit = (temperature * 1.8) + 32;
    double[] w = {35.74, 0.6215, -35.75, 0.4275};

    this.windChill = w[0] + (w[1] * inFahrenheit)
            + (w[2] * Math.pow(windSpeed, 0.16))
            + (w[3] * inFahrenheit * Math.pow(windSpeed, 0.16));
  }

  @Override
  public int getTemperature() {
    return (int) Math.round(temperature);
  }

  @Override
  public int getDewPoint() {
    return (int) Math.round(dewPoint);
  }

  @Override
  public int getWindSpeed() {
    return (int) Math.round(windSpeed);
  }

  @Override
  public int getTotalRain() {
    return (int) Math.round(totalRain);
  }

  @Override
  public int getRelativeHumidity() {
    return (int) Math.round(relativeHumidity);
  }

  @Override
  public int getHeatIndex() {
    return (int) Math.round(heatIndex);
  }

  @Override
  public int getWindChill() {
    return (int) Math.round(windChill);
  }

  /**
   * The string representation of the reading.
   *
   * @return String representation of the reading.
   */
  @Override
  public String toString() {
    return "Reading: T = " + getTemperature() + ", D = " + getDewPoint() + ", v = " + getWindSpeed()
            + ", rain = " + getTotalRain();
  }

  /**
   * Creates a hashcode based on the readings.
   *
   * @return hashcode
   */
  @Override
  public int hashCode() {
    return Objects.hash(Math.round(temperature / DELTA) * DELTA,
            Math.round(dewPoint / DELTA) * DELTA,
            Math.round(windSpeed / DELTA) * DELTA,
            Math.round(totalRain / DELTA) * DELTA);
  }

  /**
   * Compare if the input reading has the same values.
   *
   * @param o Object with which readings are compared
   * @return true if both readings are same else false
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof StevensonReading)) {
      return false;
    }

    // The successful instanceof check means our cast will succeed:
    StevensonReading that = (StevensonReading) o;

    boolean result = Math.abs(this.temperature - that.temperature) < DELTA;
    result = result && Math.abs(this.dewPoint - that.dewPoint) < DELTA;
    result = result && Math.abs(this.windSpeed - that.windSpeed) < DELTA;
    result = result && Math.abs(this.totalRain - that.totalRain) < DELTA;

    return result;
  }
}
