
import org.junit.Before;
import org.junit.Test;

import sentence.EmptyNode;
import sentence.PunctuationNode;
import sentence.Sentence;
import sentence.WordNode;

import static org.junit.Assert.assertEquals;


/**
 * Tests cases for various methods related to {@link Sentence}.
 * Tests all the different cases that could occur in this representation of a sentence.
 */
public class SentenceTest {

  private Sentence emptySentence;
  private Sentence sentence1;
  private Sentence sentence2;
  private Sentence onlyWords;

  @Before
  public void setup() {
    emptySentence = new EmptyNode();

    sentence1 = new WordNode("Hello",
            new PunctuationNode("!",
                    new PunctuationNode("!",
                            new WordNode("How",
                                    new WordNode("are",
                                            new WordNode("you",
                                                    new PunctuationNode("?",
                                                            new EmptyNode())))))));

    sentence2 = new WordNode("I",
            new WordNode("am",
                    new WordNode("your",
                            new WordNode("half",
                                    new WordNode("boss",
                                            new PunctuationNode(".",
                                                    new EmptyNode()))))));

    onlyWords = new WordNode("Do",
            new WordNode("not",
                    new WordNode("start",
                            new WordNode("running",
                                    new WordNode("here",
                                            new EmptyNode())))));
  }

  @Test
  public void testGetNumberOfWords() {
    assertEquals(0, emptySentence.getNumberOfWords());
    assertEquals(4, sentence1.getNumberOfWords());
    assertEquals(4, sentence1.getNumberOfWords());
    assertEquals(5, onlyWords.getNumberOfWords());
  }

  @Test
  public void longestWord() {
    assertEquals("", emptySentence.longestWord());
    assertEquals("Hello", sentence1.longestWord());
    assertEquals("your", sentence2.longestWord());
    assertEquals("running", onlyWords.longestWord());
  }

  @Test
  public void merge() {
    assertEquals("Hello!! How are you?", emptySentence.merge(sentence1).toString());
    assertEquals("Hello!! How are you?", sentence1.merge(emptySentence).toString());
    assertEquals("Hello!! How are you? I am your half boss.",
            sentence1.merge(sentence2).toString());
    assertEquals("I am your half boss. Hello!! How are you?",
            sentence2.merge(sentence1).toString());
    assertEquals("Do not start running here I am your half boss.",
            onlyWords.merge(sentence2).toString());
  }

  @Test
  public void testClone() {
    assertEquals("", emptySentence.clone().toString());
    assertEquals("Hello!! How are you?", sentence1.clone().toString());
    assertEquals("I am your half boss.", sentence2.clone().toString());
    assertEquals("Do not start running here", onlyWords.clone().toString());
  }

  @Test
  public void testToString() {
    assertEquals("", emptySentence.toString());
    assertEquals("Hello!! How are you?", sentence1.toString());
    assertEquals("I am your half boss.", sentence2.toString());
    assertEquals("Do not start running here", onlyWords.toString());
  }
}