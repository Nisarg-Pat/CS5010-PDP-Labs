package questions;

/**
 * True - False representation of {@link Question} class.
 * It can be answered in one of two ways: true or false.
 * The only valid answer to this type of question is a "True" or "False".
 */
public class TrueFalse extends AbstractQuestion {

  /**
   * Constructor for TrueFalse class.
   *
   * @param questionText  the text of the question
   * @param correctAnswer the correct answer for the question. Can be only "True" or "False".
   * @throws IllegalArgumentException if correct answer mentioned is neither "True" nor "False".
   */
  public TrueFalse(String questionText, String correctAnswer) {
    super(questionText, SEQUENCE_TRUE_FALSE);
    if (!correctAnswer.equals("True") && !correctAnswer.equals("False")) {
      throw new IllegalArgumentException("The arguments are not proper");
    }
    this.correctAnswer.add(correctAnswer);
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
  protected int compareToTrueFalse(TrueFalse trueFalse, int sequence) {
    return this.getText().compareTo(trueFalse.getText());
  }

  /**
   * Comparison with another Question to return a specific order of Questions.
   *
   * @param o Question with which to compare
   * @return lexicographic comparison of question text when compared with {@link TrueFalse}
   *         negative when compared with {@link MultipleChoice}
   *         negative when compared with {@link MultipleSelect}
   *         negative when compared with {@link Likert}
   */
  @Override
  public int compareTo(Question o) {
    if (o instanceof AbstractQuestion) {
      AbstractQuestion other = (AbstractQuestion) o;
      return -1 * other.compareToTrueFalse(this, other.getSequence());
    }
    return -1;
  }
  
}
