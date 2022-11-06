package transmission;

/**
 * This is the implementation of {@link Transmission} for
 * <a href = https://en.wikipedia.org/wiki/Automatic_transmission>Automatic Transmission</a> of gear.
 * The gear of the vehicle in Automatic Transmission is dependent upon the current speed.<br>
 * This simulation assumes that the vehicle is having 6 gears
 * and the choice of gear is a monotonically increasing function of speed.
 * This simulation also assumes that cars don't have negative speed, don't have reverse gear,
 * and can go as much fast as needed.<br>
 * The car at 0 speed represents idle state and is out of gear.
 * The gear for this state is represented as 0.
 */
public class AutomaticTransmission implements Transmission {
  private int speed;
  private int gear;
  private final int[] threshold;

  /**
   * Constructor that provides the threshold speeds for the gears.
   *
   * @param     threshold1 Threshold speed for changing gear 1 to 2 or back
   * @param     threshold2 Threshold speed for changing gear 2 to 3 or back
   * @param     threshold3 Threshold speed for changing gear 3 to 4 or back
   * @param     threshold4 Threshold speed for changing gear 4 to 5 or back
   * @param     threshold5 Threshold speed for changing gear 5 to 6 or back
   * @throws    IllegalArgumentException if the threshold values are negative
   *            or not in strictly increasing order.
   *
   */
  public AutomaticTransmission(int threshold1, int threshold2, int threshold3, int threshold4,
                               int threshold5) {
    if (!checkValidThreshold(threshold1, threshold2, threshold3, threshold4, threshold5)) {
      throw new IllegalArgumentException("Invalid Data, Please check again.");
    }

    //Giving threshold of 0 as a default value for gear 0.
    threshold = new int[]{0, threshold1, threshold2, threshold3, threshold4, threshold5};

    speed = 0;
    gear = 0;
  }

  private boolean checkValidThreshold(int first, int second, int third, int fourth, int fifth) {
    return first > 0 && (first < second) && (second < third)
            && (third < fourth) && (fourth < fifth);
  }

  @Override
  public void increaseSpeed() {
    speed++;
    changeGear();
  }

  @Override
  public void decreaseSpeed() throws IllegalStateException {
    if (speed == 0) {
      throw new IllegalStateException("Cannot decrease the speed from 0.");
    }
    speed--;
    changeGear();
  }

  private void changeGear() {
    if (gear < 6 && speed >= threshold[gear]) {
      gear++;
    } else if (gear > 0 && speed < threshold[gear - 1]) {
      gear--;
    } else if (speed == 0) {
      gear = 0;
    }
  }

  @Override
  public int getSpeed() {
    return speed;
  }

  @Override
  public int getGear() {
    return gear;
  }

  /**
   * The string representation of the Transmission at a particular time.
   * @return A string with the format - Transmission (speed = s, gear = g)
   */
  @Override
  public String toString() {
    return String.format("Transmission (speed = %d, gear = %d)", speed, gear);
  }
}
