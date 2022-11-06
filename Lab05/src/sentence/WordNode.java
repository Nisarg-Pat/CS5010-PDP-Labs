package sentence;

/**
 * A node that represents a word in a {@link Sentence}.
 * Each node has only one word.
 */
public class WordNode implements Sentence {
  private final String word;
  private final Sentence next;

  /**
   * Constructor for sentence.WordNode class.
   *
   * @param word the word stored in this Node
   * @param next reference to next Node in the list.
   */
  public WordNode(String word, Sentence next) {
    this.word = word;
    this.next = next;
  }

  @Override
  public int getNumberOfWords() {
    return getNumberOfWordsHelper(0);
  }

  @Override
  public int getNumberOfWordsHelper(int acc) {
    return next.getNumberOfWordsHelper(1 + acc);
  }

  @Override
  public String longestWord() {
    return longestWordHelper("");
  }

  @Override
  public String longestWordHelper(String longestTillNow) {
    if (this.word.length() > longestTillNow.length()) {
      return next.longestWordHelper(this.word);
    } else {
      return next.longestWordHelper(longestTillNow);
    }
  }

  @Override
  public Sentence merge(Sentence other) {

    return new WordNode(this.word, next.merge(other));
  }

  @Override
  public Sentence clone() {
    return new WordNode(this.word, next.clone());
  }

  @Override
  public String toString() {
    return toStringHelper(new StringBuilder(), false);
  }

  @Override
  public String toStringHelper(StringBuilder sb, boolean shouldHaveSpaceBefore) {
    if (shouldHaveSpaceBefore) {
      sb.append(" ");
    }
    return next.toStringHelper(sb.append(word), true);
  }


}
