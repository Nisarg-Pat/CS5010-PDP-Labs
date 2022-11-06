package lookandsay;

import java.math.BigInteger;
import java.util.NoSuchElementException;

/**
 * LookAndSay representation of Iteration with Previous Functionality.
 */
public class LookAndSayIterator implements RIterator<BigInteger> {

  private BigInteger seed;
  private BigInteger prevNumber;
  private final BigInteger end;

  private static final BigInteger DEFAULT_END = new BigInteger("9".repeat(100));
  private static final BigInteger DEFAULT_SEED = BigInteger.ONE;

  /**
   * Constructor that sets default values to both seed and end value.
   * Default value of seed is 1
   * Default value of end is a number with 100 9s (the largest 100 digit number).
   */
  public LookAndSayIterator() {
    this(DEFAULT_SEED, DEFAULT_END);
  }

  /**
   * Constructor that sets the mentioned starting seed and default value end value.
   *
   * @param seed the starting value of the iterator.
   */
  public LookAndSayIterator(BigInteger seed) {
    this(seed, DEFAULT_END);
  }

  /**
   * Constructor that sets the mentioned starting seed and end value.
   *
   * @param seed the starting value of the iterator.
   * @param end  the maximum value of the iterator.
   */
  public LookAndSayIterator(BigInteger seed, BigInteger end) {
    if (seed.compareTo(new BigInteger("0")) <= 0
            || seed.compareTo(end) >= 0
            || seed.toString().contains("0")) {
      throw new IllegalArgumentException(String.format("Invalid seed: %s", seed));
    }
    this.seed = seed;
    this.prevNumber = getPrevNumber(seed);
    this.end = end;
  }

  @Override
  public BigInteger prev() throws NoSuchElementException {
    if (!hasPrevious()) {
      throw new NoSuchElementException("No previous number in the sequence.");
    }
    BigInteger returnNumber = prevNumber;
    seed = getNextNumber(prevNumber);
    prevNumber = getPrevNumber(prevNumber);
    return returnNumber;
  }

  @Override
  public boolean hasPrevious() {
    return prevNumber.compareTo(BigInteger.ZERO) >= 0 && prevNumber.compareTo(end) <= 0;
  }

  @Override
  public BigInteger next() throws NoSuchElementException {
    if (!hasNext()) {
      throw new NoSuchElementException("No next number in the sequence.");
    }
    BigInteger returnNumber = seed;
    prevNumber = getPrevNumber(seed);
    seed = getNextNumber(seed);
    return returnNumber;
  }

  @Override
  public boolean hasNext() {
    return seed.compareTo(end) <= 0;
  }

  private BigInteger getNextNumber(BigInteger number) {
    String val = number.toString();
    int length = val.length();
    int current = val.charAt(0) - '0';
    int count = 1;
    StringBuilder sb = new StringBuilder();
    for (int i = 1; i < length; i++) {
      int newDigit = val.charAt(i) - '0';
      if (newDigit == current) {
        count++;
      } else {
        sb.append(count).append(current);
        current = newDigit;
        count = 1;
      }
    }
    sb.append(count).append(current);
    return new BigInteger(sb.toString());
  }

  private BigInteger getPrevNumber(BigInteger number) {
    String val = number.toString();
    int length = val.length();
    if (length % 2 != 0) {
      return new BigInteger("-1");
    }
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < length; i += 2) {
      int count = val.charAt(i) - '0';
      int num = val.charAt(i + 1) - '0';
      sb.append(String.valueOf(num).repeat(Math.max(0, count)));
    }
    return new BigInteger(sb.toString());
  }
}
