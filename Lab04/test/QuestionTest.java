import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import questions.Likert;
import questions.MultipleChoice;
import questions.MultipleSelect;
import questions.Question;
import questions.TrueFalse;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This class contains all the unit tests for various kinds of questions.
 */
public class QuestionTest {
  private Question likert1;
  private Question likert2;
  private Question multipleChoice1;
  private Question multipleChoice2;
  private Question multipleSelect1;
  private Question multipleSelect2;
  private Question trueFalse1;
  private Question trueFalse2;

  /**
   * Setting up objects for all of the tests.
   */
  @Before
  public void setup() {
    likert1 = new Likert("Knockout is the best event.");
    likert2 = new Likert("Gem time should be increased.");
    multipleChoice1 = new MultipleChoice("Who is this brawling arcade machine?",
            "3", "3 BIT", "7 BIT", "8 BIT", "10 BIT");
    multipleChoice2 = new MultipleChoice("What animal can Nita summon?",
            "2", "Dog", "Bear", "Stout", "Cat", "Mouse");
    multipleSelect1 = new MultipleSelect("Which of these are Super Rare?",
            "1 4", "Jacky", "Primo", "Surge", "Darryl");
    multipleSelect2 = new MultipleSelect("Which of these are 2v2 events?",
            "1 2 3 5", "Gem Grab", "Bounty", "Brawl ball",
            "Showdown", "Knockout");
    trueFalse1 = new TrueFalse("Spike is Chromatic.", "True");
    trueFalse2 = new TrueFalse("Nani is thrower.", "False");
  }

  /**
   * Test for improper answer argument for multiple choice.
   */
  @Test(expected = IllegalArgumentException.class)
  public void multipleChoiceFailConstructorIncorrectAnswer() {
    new MultipleChoice("Which of these is trophy road",
            "0", "Nita", "Bull", "Spike");
  }

  /**
   * Test for total options less than 3.
   */
  @Test(expected = IllegalArgumentException.class)
  public void multipleChoiceFailConstructorOptionLimitLow() {
    new MultipleChoice("Which of these is trophy road",
            "1", "Nita");
  }

  /**
   * Test for total options greater than 8.
   */
  @Test(expected = IllegalArgumentException.class)
  public void multipleChoiceFailConstructorOptionLimitHigh() {
    new MultipleChoice("Which of these is trophy road",
            "1", "A", "B", "C", "D", "E", "F", "G", "H", "I");
  }

  /**
   * Test for improper answer argument for multiple select.
   */
  @Test(expected = IllegalArgumentException.class)
  public void multipleSelectFailConstructorInCorrectAnswer() {
    new MultipleSelect("Which of these is trophy road",
            "2 3 6", "Nita", "Bull", "Spike");
  }

  /**
   * Test for total options less than 3.
   */
  @Test(expected = IllegalArgumentException.class)
  public void multipleSelectFailConstructorOptionLimitLow() {
    new MultipleSelect("Which of these is trophy road",
            "1", "Nita");
  }

  /**
   * Test for total options greater than 8.
   */
  @Test(expected = IllegalArgumentException.class)
  public void multipleSelectFailConstructorOptionLimitHigh() {
    new MultipleSelect("Which of these is trophy road",
            "1", "A", "B", "C", "D", "E", "F", "G", "H", "I");
  }

  /**
   * Test for {@link Likert#getText()} and {@link Likert#answer(String)}.
   */
  @Test
  public void testLikert() {
    assertEquals("Knockout is the best event.", likert1.getText());

    //Correct answer test
    assertEquals("Correct", likert1.answer("1"));
    assertEquals("Correct", likert1.answer("2"));
    assertEquals("Correct", likert1.answer("3"));
    assertEquals("Correct", likert1.answer("4"));
    assertEquals("Correct", likert1.answer("5"));

    //Incorrect answer test
    assertEquals("Incorrect", likert1.answer("0"));
    assertEquals("Incorrect", likert1.answer("6"));
    assertEquals("Incorrect", likert1.answer("-5"));
    assertEquals("Incorrect", likert1.answer("Abc"));
    assertEquals("Incorrect", likert1.answer("80"));
  }

  /**
   * Test for {@link MultipleChoice#getText()} and {@link  MultipleChoice#answer(String)}.
   */
  @Test
  public void testMultipleChoice() {
    assertEquals("Who is this brawling arcade machine?", multipleChoice1.getText());
    assertEquals("What animal can Nita summon?", multipleChoice2.getText());

    //Correct answer test
    assertEquals("Correct", multipleChoice1.answer("3"));
    assertEquals("Correct", multipleChoice2.answer("2"));

    //Incorrect answer test
    assertEquals("Incorrect", multipleChoice1.answer("1"));
    assertEquals("Incorrect", multipleChoice1.answer("2"));
    assertEquals("Incorrect", multipleChoice1.answer("4"));
    assertEquals("Incorrect", multipleChoice2.answer("1"));
    assertEquals("Incorrect", multipleChoice2.answer("3"));
    assertEquals("Incorrect", multipleChoice2.answer("4"));

    assertEquals("Incorrect", multipleChoice1.answer("0"));
    assertEquals("Incorrect", multipleChoice1.answer("6"));
    assertEquals("Incorrect", multipleChoice1.answer("-5"));
    assertEquals("Incorrect", multipleChoice1.answer("Abc"));
    assertEquals("Incorrect", multipleChoice1.answer("80"));
  }

  /**
   * Test for {@link MultipleSelect#getText()} and {@link MultipleSelect#answer(String)}.
   */
  @Test
  public void testMultipleSelect() {
    assertEquals("Which of these are Super Rare?", multipleSelect1.getText());
    assertEquals("Which of these are 2v2 events?", multipleSelect2.getText());

    //Correct answer test
    assertEquals("Correct", multipleSelect1.answer("1 4"));
    assertEquals("Correct", multipleSelect1.answer("4 1"));
    assertEquals("Correct", multipleSelect2.answer("1 2 5 3"));
    assertEquals("Correct", multipleSelect2.answer("5 2 1 3"));

    //Incorrect answer test
    assertEquals("Incorrect", multipleSelect1.answer("1 3"));
    assertEquals("Incorrect", multipleSelect1.answer("2 4"));
    assertEquals("Incorrect", multipleSelect1.answer("2 3 5"));
    assertEquals("Incorrect", multipleSelect2.answer("1 2"));
    assertEquals("Incorrect", multipleSelect2.answer("1 2 5 4"));
    assertEquals("Incorrect", multipleSelect2.answer("3 2 1"));

    assertEquals("Incorrect", multipleSelect1.answer("0"));
    assertEquals("Incorrect", multipleSelect1.answer("1 2 3 4"));
    assertEquals("Incorrect", multipleSelect1.answer("-1 -4"));
    assertEquals("Incorrect", multipleSelect1.answer("Abc 1 4"));
    assertEquals("Incorrect", multipleSelect1.answer("1 1"));
  }

  /**
   * Test for {@link TrueFalse#getText()} and {@link TrueFalse#answer(String)}.
   */
  @Test
  public void testTrueFalse() {
    assertEquals("Spike is Chromatic.", trueFalse1.getText());
    assertEquals("Nani is thrower.", trueFalse2.getText());

    //Correct answer test
    assertEquals("Correct", trueFalse1.answer("True"));
    assertEquals("Correct", trueFalse2.answer("False"));

    //Incorrect answer test
    assertEquals("Incorrect", trueFalse1.answer("False"));
    assertEquals("Incorrect", trueFalse2.answer("True"));

    assertEquals("Incorrect", trueFalse1.answer("A"));
    assertEquals("Incorrect", trueFalse1.answer("80"));
  }

  /**
   * Test for {@link TrueFalse#compareTo(Question)}.
   */
  @Test
  public void testTrueFalseCompareTo() {
    assertTrue(trueFalse1.compareTo(multipleChoice1) < 0);
    assertTrue(trueFalse1.compareTo(multipleSelect1) < 0);
    assertTrue(trueFalse1.compareTo(likert1) < 0);


    assertTrue(trueFalse1.compareTo(trueFalse2) > 0);
    assertTrue(trueFalse2.compareTo(trueFalse1) < 0);
  }

  /**
   * Test for {@link MultipleChoice#compareTo(Question)}.
   */
  @Test
  public void testMultipleChoiceCompareTo() {
    assertTrue(multipleChoice1.compareTo(trueFalse1) > 0);
    assertTrue(multipleChoice1.compareTo(multipleSelect1) < 0);
    assertTrue(multipleChoice1.compareTo(likert1) < 0);


    assertTrue(multipleChoice1.compareTo(multipleChoice2) > 0);
    assertTrue(multipleChoice2.compareTo(multipleChoice1) < 0);
  }

  /**
   * Test for {@link MultipleSelect#compareTo(Question)}.
   */
  @Test
  public void testMultipleSelectCompareTo() {
    assertTrue(multipleSelect1.compareTo(trueFalse1) > 0);
    assertTrue(multipleSelect1.compareTo(multipleChoice1) > 0);
    assertTrue(multipleSelect1.compareTo(likert1) < 0);


    assertTrue(multipleSelect1.compareTo(multipleSelect2) > 0);
    assertTrue(multipleSelect2.compareTo(multipleSelect1) < 0);
  }

  /**
   * Test for {@link Likert#compareTo(Question)}.
   */
  @Test
  public void testLikertCompareTo() {
    assertTrue(likert1.compareTo(trueFalse1) > 0);
    assertTrue(likert1.compareTo(multipleChoice1) > 0);
    assertTrue(likert1.compareTo(multipleSelect1) > 0);

    assertTrue(likert1.compareTo(likert2) > 0);
    assertTrue(likert2.compareTo(likert1) < 0);
  }

  /**
   * Test to check sorting of {@link Question}.
   */
  @Test
  public void sortQuestions() {
    Question[] questions = new Question[]{likert1, multipleSelect1, multipleSelect2, trueFalse2,
        multipleChoice2, likert2, multipleChoice1, trueFalse1};
    Question[] sortedQuestions = new Question[]{trueFalse2, trueFalse1, multipleChoice2,
        multipleChoice1, multipleSelect2, multipleSelect1, likert2, likert1};
    Arrays.sort(questions);
    for (int i = 0; i < questions.length; i++) {
      assertEquals(sortedQuestions[i], questions[i]);
    }
  }
}