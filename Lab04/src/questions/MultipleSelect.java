package questions;

import java.util.HashMap;

/**
 * Multiple Select representation of {@link Question} class.
 * It offers several options, but there are multiple correct answers.
 * A question may have at least 3 options, but no more than 8.
 * An answer can be entered as one of the option numbers in a string starting from "1".
 */
public class MultipleSelect extends AbstractQuestion {

  /**
   * Constructor for MultipleSelect class.
   *
   * @param questionText  the text of the question
   * @param correctAnswer the correct answers for the question as a String starting from 1.
   * @param options       the options offered for the question.
   *                      Atleast 3 and atmost 8 options are required.
   * @throws IllegalArgumentException if the arguments are invalid.
   */
  public MultipleSelect(String questionText, String correctAnswer, String... options) {
    super(questionText, SEQUENCE_MULTIPLE_SELECT);
    if (options.length < 3 || options.length > 8) {
      throw new IllegalArgumentException("The options should be between 3 and 8");
    }
    String[] answers = correctAnswer.split(" ");
    int correctAnswerNumber;
    for (String answer : answers) {
      try {
        correctAnswerNumber = Integer.parseInt(answer);
      } catch (NumberFormatException e) {
        throw new IllegalArgumentException("The correct answer mentioned is not proper");
      }
      if (correctAnswerNumber < 1 || correctAnswerNumber > options.length) {
        throw new IllegalArgumentException("The correct Answer does not have proper range");
      }
      this.correctAnswer.add(answer);
    }
  }

  @Override
  public String answer(String answer) {
    String[] answers = answer.split(" ");
    HashMap<String, Integer> givenAnswers = new HashMap<>();
    for (String string : correctAnswer) {
      givenAnswers.put(string, 0);
    }
    for (String ans : answers) {
      if (!givenAnswers.containsKey(ans)) {
        return INCORRECT;
      }
      givenAnswers.put(ans, givenAnswers.get(ans) + 1);
    }
    for (String key : givenAnswers.keySet()) {
      if (givenAnswers.get(key) == 0) {
        return INCORRECT;
      }
    }
    return CORRECT;
  }

  @Override
  protected int compareToMultipleSelect(MultipleSelect multipleSelect, int sequence) {
    return this.getText().compareTo(multipleSelect.getText());
  }

  /**
   * Comparison with another Question to return a specific order of Questions.
   *
   * @param o Question with which to compare
   * @return positive when compared with {@link TrueFalse}
   *         positive when compared with {@link MultipleChoice}
   *         lexicographic comparison of question when compared with {@link MultipleSelect}
   *         negative when compared with {@link Likert}
   */
  @Override
  public int compareTo(Question o) {
    if (o instanceof AbstractQuestion) {
      AbstractQuestion other = (AbstractQuestion) o;
      return -1 * other.compareToMultipleSelect(this, other.getSequence());
    }
    return -1;
  }
}
