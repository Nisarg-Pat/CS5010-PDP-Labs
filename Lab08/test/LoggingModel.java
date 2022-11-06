import tictactoe8.Player;
import tictactoe8.TicTacToe;

/**
 * A mock for the Model to simulate and log the correctness
 * of the inputs from the Controller to the Model.
 */
class LoggingModel implements TicTacToe {
  private final StringBuilder sb;

  LoggingModel(StringBuilder sb) {
    this.sb = sb;
  }

  @Override
  public void move(int r, int c) {
    sb.append("Row: ").append(r).append(", Column: ").append(c).append("\n");
  }

  @Override
  public Player getTurn() {
    return null;
  }

  @Override
  public boolean isGameOver() {
    return false;
  }

  @Override
  public Player getWinner() {
    return null;
  }

  @Override
  public Player[][] getBoard() {
    return new Player[1][1];
  }

  @Override
  public Player getMarkAt(int r, int c) {
    return null;
  }
}
