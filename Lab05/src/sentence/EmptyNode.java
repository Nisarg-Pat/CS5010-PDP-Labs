package sentence;

/**
 * A node that represents the end of the sentence.
 * Does not contain any word or punctuation in it.
 */
public class EmptyNode implements Sentence {

  @Override
  public int getNumberOfWords() {
    return 0;
  }

  @Override
  public int getNumberOfWordsHelper(int acc) {
    return acc;
  }

  @Override
  public String longestWord() {
    return "";
  }

  @Override
  public String longestWordHelper(String longestTillNow) {
    return longestTillNow;
  }

  @Override
  public Sentence merge(Sentence other) {
    return other.clone();
  }

  @Override
  public Sentence clone() {
    return this;
  }

  @Override
  public String toString() {
    return "";
  }

  @Override
  public String toStringHelper(StringBuilder sb, boolean shouldHaveSpaceBefore) {
    return sb.toString();
  }
}
