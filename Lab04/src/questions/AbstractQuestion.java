package questions;

import java.util.HashSet;

/**
 * Abstract representation of {@link Question}. This class
 * contains all the method definitions that are common to the concrete
 * implementations of the Question interface. A new implementation of
 * the interface has the option of extending this class, or directly
 * implementing all the methods.
 */
abstract class AbstractQuestion implements Question {

  protected final String questionText;
  protected final HashSet<String> correctAnswer;
  protected final int sequence;

  protected static final int SEQUENCE_TRUE_FALSE = 1;
  protected static final int SEQUENCE_MULTIPLE_CHOICE = 2;
  protected static final int SEQUENCE_MULTIPLE_SELECT = 3;
  protected static final int SEQUENCE_LIKERT = 4;

  protected AbstractQuestion(String questionText, int sequence) {
    this.questionText = questionText;
    this.correctAnswer = new HashSet<>();
    this.sequence = sequence;
  }

  @Override
  public String getText() {
    return questionText;
  }

  protected int compareToTrueFalse(TrueFalse trueFalse, int sequence) {
    return sequence - SEQUENCE_TRUE_FALSE;
  }

  protected int compareToMultipleChoice(MultipleChoice multipleChoice, int sequence) {
    return sequence - SEQUENCE_MULTIPLE_CHOICE;
  }

  protected int compareToMultipleSelect(MultipleSelect multipleSelect, int sequence) {
    return sequence - SEQUENCE_MULTIPLE_SELECT;
  }

  protected int compareToLikert(Likert likert, int sequence) {
    return sequence - SEQUENCE_LIKERT;
  }

  protected int getSequence() {
    return sequence;
  }
}
