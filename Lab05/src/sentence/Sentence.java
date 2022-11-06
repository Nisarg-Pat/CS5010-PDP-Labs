package sentence;

/**
 * A representation of sentence.Sentence as a list of words and punctuations.
 */
public interface Sentence {

  /**
   * Return the number of words in the sentence. Punctuation does not count as a
   * word.
   *
   * @return the number of words in the sentence.
   */
  int getNumberOfWords();

  /**
   * Helper method for {@link #getNumberOfWords()}.
   *
   * @param acc the total number of words till now
   * @return the number of words in the sentence.
   */
  int getNumberOfWordsHelper(int acc);

  /**
   * Return the (first) longest word in the sentence. The longest word should not
   * begin or end with punctuation. If the sentence is empty, it should an empty
   * string.
   *
   * @return the longest word in the sentence.
   */
  String longestWord();

  /**
   * Helper method for {@link #longestWord()}.
   *
   * @param longestTillNow the longest word till now
   * @return the longest word in the sentence.
   */
  String longestWordHelper(String longestTillNow);

  /**
   * Merge two sentences together. The merge sentence is a list that preserves all
   * of the punctuation. The merged list should be returned by this method, and
   * the original lists should be unchanged.
   *
   * @param other the sentence to add to the end of this sentence.
   * @return the merged sentence
   */
  Sentence merge(Sentence other);

  /**
   * Return a duplicate of this sentence. A duplicate is a list that has the same
   * words and punctuation in the same sequence, but is independent of the
   * original list.
   *
   * @return a duplicate of this sentence
   */
  Sentence clone();

  /**
   * Convert the sentences into a single string representation. There must be
   * exactly one space between every two words. There is no space between the last
   * word and the end of the sentence.
   *
   * @return a string representation of this sentence
   */
  String toString();

  /**
   * Helper method for {@link #toString()}.
   *
   * @param sb                    The StringBuilder object to pass the string
   * @param shouldHaveSpaceBefore Should there be any space before the word
   * @return a string representation of this sentence
   */
  String toStringHelper(StringBuilder sb, boolean shouldHaveSpaceBefore);
}
