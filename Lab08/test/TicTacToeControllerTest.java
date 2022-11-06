import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import tictactoe.TicTacToe;
import tictactoe.TicTacToeConsoleController;
import tictactoe.TicTacToeController;
import tictactoe.TicTacToeModel;

import java.io.StringReader;

import org.junit.Test;

/**
 * Test cases for the tic tac toe controller, using mocks for readable and
 * appendable.
 */
public class TicTacToeControllerTest {

  // Providing this shell for you to write your
  // own test cases for the TicTacToe controller
  // You DO NOT NEED to implement tests for the provided model

  // TODO: Implement your own tests cases for the controller

  @Test(expected = IllegalStateException.class)
  public void testFailingAppendable() {
    // Testing when something goes wrong with the Appendable
    // Here we are passing it a mock of an Appendable that always fails
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("2 2 1 1 3 3 1 2 1 3 2 3 2 1 3 1 3 2");
    Appendable gameLog = new FailingAppendable();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
  }

  @Test
  public void testInvalidConstructor() {
    Readable input = new StringReader("");
    Appendable append = new StringBuilder();
    try {
      TicTacToeController c = new TicTacToeConsoleController(input, null);
      fail();
    } catch (Exception e) {
      assertEquals("Readable and Appendable can't be null", e.getMessage());
    }

    try {
      TicTacToeController c = new TicTacToeConsoleController(null, append);
      fail();
    } catch (Exception e) {
      assertEquals("Readable and Appendable can't be null", e.getMessage());
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidModel() {
    Readable input = new StringReader("");
    Appendable append = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, append);
    c.playGame(null);
  }

  @Test
  public void testInvalidRowInput() {
    StringReader input = new StringReader("one two three 1 2 q");
    Appendable append = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, append);
    c.playGame(new TicTacToeModel());
    assertEquals(
            "   |   |  \n"
                    + "-----------\n"
                    + "   |   |  \n"
                    + "-----------\n"
                    + "   |   |  \n"
                    + "Enter a move for X:\n"
                    + "Not a valid number: one\n"
                    + "Not a valid number: two\n"
                    + "Not a valid number: three\n"
                    + "   | X |  \n"
                    + "-----------\n"
                    + "   |   |  \n"
                    + "-----------\n"
                    + "   |   |  \n"
                    + "Enter a move for O:\n"
                    + "Game quit! Ending game state:\n"
                    + "   | X |  \n"
                    + "-----------\n"
                    + "   |   |  \n"
                    + "-----------\n"
                    + "   |   |  \n",
            append.toString());
  }

  @Test
  public void testInvalidColumnInput() {
    StringReader input = new StringReader("1 one two three 2 q");
    Appendable append = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, append);
    c.playGame(new TicTacToeModel());
    assertEquals(
            "   |   |  \n"
                    + "-----------\n"
                    + "   |   |  \n"
                    + "-----------\n"
                    + "   |   |  \n"
                    + "Enter a move for X:\n"
                    + "Not a valid number: one\n"
                    + "Not a valid number: two\n"
                    + "Not a valid number: three\n"
                    + "   | X |  \n"
                    + "-----------\n"
                    + "   |   |  \n"
                    + "-----------\n"
                    + "   |   |  \n"
                    + "Enter a move for O:\n"
                    + "Game quit! Ending game state:\n"
                    + "   | X |  \n"
                    + "-----------\n"
                    + "   |   |  \n"
                    + "-----------\n"
                    + "   |   |  \n",
            append.toString());
  }

  @Test
  public void testInvalidRow() {
    StringReader input = new StringReader("-1 2 q");
    Appendable append = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, append);
    c.playGame(new TicTacToeModel());
    assertEquals(
            "   |   |  \n"
                    + "-----------\n"
                    + "   |   |  \n"
                    + "-----------\n"
                    + "   |   |  \n"
                    + "Enter a move for X:\n"
                    + "Not a valid move: -1, 2\n"
                    + "Game quit! Ending game state:\n"
                    + "   |   |  \n"
                    + "-----------\n"
                    + "   |   |  \n"
                    + "-----------\n"
                    + "   |   |  \n",
            append.toString());
  }

  @Test
  public void testInvalidColumn() {
    StringReader input = new StringReader("1 7 q");
    Appendable append = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, append);
    c.playGame(new TicTacToeModel());
    assertEquals(
            "   |   |  \n"
                    + "-----------\n"
                    + "   |   |  \n"
                    + "-----------\n"
                    + "   |   |  \n"
                    + "Enter a move for X:\n"
                    + "Not a valid move: 1, 7\n"
                    + "Game quit! Ending game state:\n"
                    + "   |   |  \n"
                    + "-----------\n"
                    + "   |   |  \n"
                    + "-----------\n"
                    + "   |   |  \n",
            append.toString());
  }

  @Test
  public void testQuitRow() {
    StringReader input = new StringReader("1 1 q");
    Appendable append = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, append);
    c.playGame(new TicTacToeModel());
    assertEquals(
            "   |   |  \n"
                    + "-----------\n"
                    + "   |   |  \n"
                    + "-----------\n"
                    + "   |   |  \n"
                    + "Enter a move for X:\n"
                    + " X |   |  \n"
                    + "-----------\n"
                    + "   |   |  \n"
                    + "-----------\n"
                    + "   |   |  \n"
                    + "Enter a move for O:\n"
                    + "Game quit! Ending game state:\n"
                    + " X |   |  \n"
                    + "-----------\n"
                    + "   |   |  \n"
                    + "-----------\n"
                    + "   |   |  \n",
            append.toString());
  }

  @Test
  public void testQuitColumn() {
    StringReader input = new StringReader("1 1 2 q");
    Appendable append = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, append);
    c.playGame(new TicTacToeModel());
    assertEquals(
            "   |   |  \n"
                    + "-----------\n"
                    + "   |   |  \n"
                    + "-----------\n"
                    + "   |   |  \n"
                    + "Enter a move for X:\n"
                    + " X |   |  \n"
                    + "-----------\n"
                    + "   |   |  \n"
                    + "-----------\n"
                    + "   |   |  \n"
                    + "Enter a move for O:\n"
                    + "Game quit! Ending game state:\n"
                    + " X |   |  \n"
                    + "-----------\n"
                    + "   |   |  \n"
                    + "-----------\n"
                    + "   |   |  \n",
            append.toString());
  }

  @Test
  public void testCorrectModelArguments() {
    StringReader input = new StringReader("1 1 1 2 1 3 2 1 2 2 2 3 3 1 3 2 3 3 q");
    Appendable append = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, append);
    StringBuilder log = new StringBuilder();
    c.playGame(new LoggingModel(log));
    assertEquals(
            "Row: 0, Column: 0\n"
                    + "Row: 0, Column: 1\n"
                    + "Row: 0, Column: 2\n"
                    + "Row: 1, Column: 0\n"
                    + "Row: 1, Column: 1\n"
                    + "Row: 1, Column: 2\n"
                    + "Row: 2, Column: 0\n"
                    + "Row: 2, Column: 1\n"
                    + "Row: 2, Column: 2\n",
            log.toString());
  }

  @Test
  public void testValidRowColumn() {
    StringReader input = new StringReader("2 2 q");
    Appendable append = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, append);
    c.playGame(new TicTacToeModel());
    assertEquals(
            "   |   |  \n"
                    + "-----------\n"
                    + "   |   |  \n"
                    + "-----------\n"
                    + "   |   |  \n"
                    + "Enter a move for X:\n"
                    + "   |   |  \n"
                    + "-----------\n"
                    + "   | X |  \n"
                    + "-----------\n"
                    + "   |   |  \n"
                    + "Enter a move for O:\n"
                    + "Game quit! Ending game state:\n"
                    + "   |   |  \n"
                    + "-----------\n"
                    + "   | X |  \n"
                    + "-----------\n"
                    + "   |   |  \n",
            append.toString());
  }

  @Test
  public void testValidFilledSpace() {
    StringReader input = new StringReader("2 2 2 2 q");
    Appendable append = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, append);
    c.playGame(new TicTacToeModel());
    assertEquals(
            "   |   |  \n"
                    + "-----------\n"
                    + "   |   |  \n"
                    + "-----------\n"
                    + "   |   |  \n"
                    + "Enter a move for X:\n"
                    + "   |   |  \n"
                    + "-----------\n"
                    + "   | X |  \n"
                    + "-----------\n"
                    + "   |   |  \n"
                    + "Enter a move for O:\n"
                    + "Not a valid move: 2, 2\n"
                    + "Game quit! Ending game state:\n"
                    + "   |   |  \n"
                    + "-----------\n"
                    + "   | X |  \n"
                    + "-----------\n"
                    + "   |   |  \n",
            append.toString());
  }

  @Test
  public void testInvalidMoveController() {
    StringReader input = new StringReader("2 2 0 -8 rabbit 3 3 q");
    Appendable append = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, append);
    c.playGame(new TicTacToeModel());
    assertEquals(
            "   |   |  \n"
                    + "-----------\n"
                    + "   |   |  \n"
                    + "-----------\n"
                    + "   |   |  \n"
                    + "Enter a move for X:\n"
                    + "   |   |  \n"
                    + "-----------\n"
                    + "   | X |  \n"
                    + "-----------\n"
                    + "   |   |  \n"
                    + "Enter a move for O:\n"
                    + "Not a valid move: 0, -8\n"
                    + "Not a valid number: rabbit\n"
                    + "   |   |  \n"
                    + "-----------\n"
                    + "   | X |  \n"
                    + "-----------\n"
                    + "   |   | O\n"
                    + "Enter a move for X:\n"
                    + "Game quit! Ending game state:\n"
                    + "   |   |  \n"
                    + "-----------\n"
                    + "   | X |  \n"
                    + "-----------\n"
                    + "   |   | O\n",
            append.toString());
  }

  @Test
  public void testXWins() {
    StringReader input = new StringReader("1 1 2 1 1 2 2 2 1 3");
    Appendable append = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, append);
    c.playGame(new TicTacToeModel());
    assertEquals(
            "   |   |  \n"
                    + "-----------\n"
                    + "   |   |  \n"
                    + "-----------\n"
                    + "   |   |  \n"
                    + "Enter a move for X:\n"
                    + " X |   |  \n"
                    + "-----------\n"
                    + "   |   |  \n"
                    + "-----------\n"
                    + "   |   |  \n"
                    + "Enter a move for O:\n"
                    + " X |   |  \n"
                    + "-----------\n"
                    + " O |   |  \n"
                    + "-----------\n"
                    + "   |   |  \n"
                    + "Enter a move for X:\n"
                    + " X | X |  \n"
                    + "-----------\n"
                    + " O |   |  \n"
                    + "-----------\n"
                    + "   |   |  \n"
                    + "Enter a move for O:\n"
                    + " X | X |  \n"
                    + "-----------\n"
                    + " O | O |  \n"
                    + "-----------\n"
                    + "   |   |  \n"
                    + "Enter a move for X:\n"
                    + " X | X | X\n"
                    + "-----------\n"
                    + " O | O |  \n"
                    + "-----------\n"
                    + "   |   |  \n"
                    + "Game is over! X wins.\n",
            append.toString());
  }

  @Test
  public void testOWins() {
    StringReader input = new StringReader("1 1 2 1 1 2 2 2 3 3 2 3");
    Appendable append = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, append);
    c.playGame(new TicTacToeModel());
    assertEquals(
            "   |   |  \n"
                    + "-----------\n"
                    + "   |   |  \n"
                    + "-----------\n"
                    + "   |   |  \n"
                    + "Enter a move for X:\n"
                    + " X |   |  \n"
                    + "-----------\n"
                    + "   |   |  \n"
                    + "-----------\n"
                    + "   |   |  \n"
                    + "Enter a move for O:\n"
                    + " X |   |  \n"
                    + "-----------\n"
                    + " O |   |  \n"
                    + "-----------\n"
                    + "   |   |  \n"
                    + "Enter a move for X:\n"
                    + " X | X |  \n"
                    + "-----------\n"
                    + " O |   |  \n"
                    + "-----------\n"
                    + "   |   |  \n"
                    + "Enter a move for O:\n"
                    + " X | X |  \n"
                    + "-----------\n"
                    + " O | O |  \n"
                    + "-----------\n"
                    + "   |   |  \n"
                    + "Enter a move for X:\n"
                    + " X | X |  \n"
                    + "-----------\n"
                    + " O | O |  \n"
                    + "-----------\n"
                    + "   |   | X\n"
                    + "Enter a move for O:\n"
                    + " X | X |  \n"
                    + "-----------\n"
                    + " O | O | O\n"
                    + "-----------\n"
                    + "   |   | X\n"
                    + "Game is over! O wins.\n",
            append.toString());
  }

  @Test
  public void testDraw() {
    StringReader input = new StringReader("1 1 1 2 1 3 2 2 2 1 3 1 2 3 3 3 3 2");
    Appendable append = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, append);
    c.playGame(new TicTacToeModel());
    assertEquals(
            "   |   |  \n"
                    + "-----------\n"
                    + "   |   |  \n"
                    + "-----------\n"
                    + "   |   |  \n"
                    + "Enter a move for X:\n"
                    + " X |   |  \n"
                    + "-----------\n"
                    + "   |   |  \n"
                    + "-----------\n"
                    + "   |   |  \n"
                    + "Enter a move for O:\n"
                    + " X | O |  \n"
                    + "-----------\n"
                    + "   |   |  \n"
                    + "-----------\n"
                    + "   |   |  \n"
                    + "Enter a move for X:\n"
                    + " X | O | X\n"
                    + "-----------\n"
                    + "   |   |  \n"
                    + "-----------\n"
                    + "   |   |  \n"
                    + "Enter a move for O:\n"
                    + " X | O | X\n"
                    + "-----------\n"
                    + "   | O |  \n"
                    + "-----------\n"
                    + "   |   |  \n"
                    + "Enter a move for X:\n"
                    + " X | O | X\n"
                    + "-----------\n"
                    + " X | O |  \n"
                    + "-----------\n"
                    + "   |   |  \n"
                    + "Enter a move for O:\n"
                    + " X | O | X\n"
                    + "-----------\n"
                    + " X | O |  \n"
                    + "-----------\n"
                    + " O |   |  \n"
                    + "Enter a move for X:\n"
                    + " X | O | X\n"
                    + "-----------\n"
                    + " X | O | X\n"
                    + "-----------\n"
                    + " O |   |  \n"
                    + "Enter a move for O:\n"
                    + " X | O | X\n"
                    + "-----------\n"
                    + " X | O | X\n"
                    + "-----------\n"
                    + " O |   | O\n"
                    + "Enter a move for X:\n"
                    + " X | O | X\n"
                    + "-----------\n"
                    + " X | O | X\n"
                    + "-----------\n"
                    + " O | X | O\n"
                    + "Game is over! Tie game.",
            append.toString());
  }


}
