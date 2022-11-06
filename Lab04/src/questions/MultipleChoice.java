package questions;

/**
 * Multiple Choice representation of {@link Question} class.
 * It offers several options, only one of which is correct.
 * A question may have at least 3 options, but no more than 8.
 * An answer can be entered as one of the option numbers in a string starting from "1".
 */
public class MultipleChoice extends MultipleSelect {

  /**
   * Constructor for MultipleChoice class.
   *
   * @param questionText  the text of the question
   * @param correctAnswer the correct answer number for the question as a String starting from 1.
   * @param options       the options offered for the question.
   *                      Atleast 3 and atmost 8 options are required.
   * @throws IllegalArgumentException if the arguments are invalid.
   */
  public MultipleChoice(String questionText, String correctAnswer, String... options) {
    super(questionText, correctAnswer, options);
    if (correctAnswer.split("").length > 1) {
      throw new IllegalArgumentException("The correct answer mentioned is not proper");
    }
  }

  @Override
  public String answer(String answer) {
    if (correctAnswer.contains(answer)) {
      return CORRECT;
    } else {
      return INCORRECT;
    }
  }

  @Override
  protected int compareToMultipleSelect(MultipleSelect multipleSelect, int sequence) {
    return -1;
  }

  @Override
  protected int compareToMultipleChoice(MultipleChoice multipleChoice, int sequence) {
    return this.getText().compareTo(multipleChoice.getText());
  }

  /**
   * Comparison with another Question to return a specific order of Questions.
   *
   * @param o Question with which to compare
   * @return positive when compared with {@link TrueFalse}
   *         lexicographic comparison of question when compared with {@link MultipleChoice}
   *         negative when compared with {@link MultipleSelect}
   *         negative when compared with {@link Likert}
   */
  @Override
  public int compareTo(Question o) {
    if (o instanceof AbstractQuestion) {
      AbstractQuestion other = (AbstractQuestion) o;
      return -1 * other.compareToMultipleChoice(this, other.getSequence());
    }
    return -1;
  }
}
