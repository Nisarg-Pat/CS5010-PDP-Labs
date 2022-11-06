package questions;

import java.util.List;

/**
 * Likert's representation of {@link Question} class.
 * can be answered on a fixed, 5-point Likert scale
 * (Strongly Agree, Agree, Neither Agree nor Disagree, Disagree, Strongly Disagree).
 * An answer can be entered as one of the option numbers, numbered from 1 in the above order.
 * Any valid option number is a "correct" answer.
 */
public class Likert extends AbstractQuestion {

  /**
   * Constructor for Likert class.
   *
   * @param questionText the text of the question
   */
  public Likert(String questionText) {
    super(questionText, SEQUENCE_LIKERT);
    correctAnswer.addAll(List.of(new String[]{"1", "2", "3", "4", "5"}));
  }

  @Override
  public String answer(String answer) {
    if (correctAnswer.contains(answer)) {
      return CORRECT;
    }
    else {
      return INCORRECT;
    }
  }

  @Override
  protected int compareToLikert(Likert likert, int sequence) {
    return this.getText().compareTo(likert.getText());
  }

  /**
   * Comparison with another Question to return a specific order of Questions.
   *
   * @param o Question with which to compare
   * @return positive when compared with {@link TrueFalse}
   *         positive when compared with {@link MultipleChoice}
   *         positive when compared with {@link MultipleSelect}
   *         lexicographic comparison of question when compared with {@link Likert}
   */
  @Override
  public int compareTo(Question o) {
    if (o instanceof AbstractQuestion) {
      AbstractQuestion other = (AbstractQuestion) o;
      return -1 * other.compareToLikert(this, other.getSequence());
    }
    return -1;
  }
}
