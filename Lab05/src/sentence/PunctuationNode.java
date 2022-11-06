package sentence;

/**
 * A node that represents a punctuation in a {@link Sentence}.
 * Each node has only one punctuation.
 * Punctuation can appear one after the other (without spaces in between them).
 */
public class PunctuationNode implements Sentence {
  private final String punctuation;
  private final Sentence next;

  /**
   * Constructor for sentence.PunctuationNode class.
   *
   * @param punctuation the punctuation stored in this Node
   * @param next        reference to next Node in the list.
   */
  public PunctuationNode(String punctuation, Sentence next) {
    this.punctuation = punctuation;
    this.next = next;
  }

  @Override
  public int getNumberOfWords() {
    return getNumberOfWordsHelper(0);
  }

  @Override
  public int getNumberOfWordsHelper(int acc) {
    return next.getNumberOfWordsHelper(acc);
  }

  @Override
  public String longestWord() {
    return longestWordHelper("");
  }

  @Override
  public String longestWordHelper(String longestTillNow) {
    return next.longestWordHelper(longestTillNow);
  }

  @Override
  public Sentence merge(Sentence other) {
    return new PunctuationNode(this.punctuation, next.merge(other));
  }

  @Override
  public Sentence clone() {
    return new PunctuationNode(this.punctuation, next.clone());
  }

  @Override
  public String toString() {
    return toStringHelper(new StringBuilder(), false);
  }

  @Override
  public String toStringHelper(StringBuilder sb, boolean shouldHaveSpaceBefore) {
    return next.toStringHelper(sb.append(punctuation), true);
  }


}
