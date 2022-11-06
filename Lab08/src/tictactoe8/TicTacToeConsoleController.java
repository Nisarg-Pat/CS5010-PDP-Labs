package tictactoe8;

import java.io.IOException;
import java.util.Scanner;

/**
 * This starter files is for students to implement a console controller for the
 * TicTacToe MVC assignment.
 */
public class TicTacToeConsoleController implements TicTacToeController {

  private final Appendable out;
  private final Scanner scan;

  /**
   * Constructor for the controller.
   *
   * @param in  the source to read from
   * @param out the target to print to
   */
  public TicTacToeConsoleController(Readable in, Appendable out) {
    if (in == null || out == null) {
      throw new IllegalArgumentException("Readable and Appendable can't be null");
    }
    this.out = out;
    scan = new Scanner(in);
  }

  @Override
  public void playGame(TicTacToe m) {
    if (m == null) {
      throw new IllegalArgumentException("Invalid TicTacToe model");
    }
    try {
      out.append(m.toString()).append("\n");
      Player currentTurn = null;
      boolean isSecondInput = false;
      int row = -1;
      int column = -1;
      while (!m.isGameOver()) {
        if (currentTurn != m.getTurn()) {
          currentTurn = m.getTurn();
          out.append("Enter a move for ").append(currentTurn.toString()).append(":\n");
        }
        String input = scan.next();
        if (input.equalsIgnoreCase("q")) {
          out.append("Game quit! Ending game state:\n").append(m.toString()).append("\n");
          return;
        } else {
          try {
            if (!isSecondInput) {
              row = Integer.parseInt(input);
              isSecondInput = true;
            } else {
              column = Integer.parseInt(input);
              isSecondInput = false;
              m.move(row - 1, column - 1);
              out.append(m.toString()).append("\n");
            }
          } catch (NumberFormatException e) {
            out.append("Not a valid number: ").append(input).append("\n");
          } catch (IllegalArgumentException e) {
            out.append("Not a valid move: ").append(Integer.toString(row)).append(", ")
                    .append(Integer.toString(column)).append("\n");
          }
        }
      }
      Player winner = m.getWinner();
      if (winner != null) {
        out.append("Game is over! ").append(m.getWinner().toString()).append(" wins.\n");
      } else {
        out.append("Game is over! ").append("Tie game.");
      }
    } catch (IOException ioe) {
      throw new IllegalStateException("Append failed", ioe);
    }
  }
}
