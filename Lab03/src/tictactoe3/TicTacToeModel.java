package tictactoe3;

import java.util.Arrays;
import java.util.stream.Collectors;

public class TicTacToeModel implements TicTacToe {
  private int turnCounter;
  private final Player[][] board;
  private Player winner;

  public TicTacToeModel() {
    this.turnCounter = 0;
    this.board = new Player[3][3];
    this.winner = null;
  }

  @Override
  public void move(int r, int c) {
    if (isGameOver()) {
      throw new IllegalStateException("The game is already over");
    }
    if (r < 0 || r > 2 || c < 0 || c > 2 || getMarkAt(r, c) != null) {
      throw new IllegalArgumentException("The inputs are not valid");
    }
    board[r][c] = getTurn();
    turnCounter++;
  }

  @Override
  public Player getTurn() {
    if (turnCounter % 2 == 0) {
      return Player.X;
    } else {
      return Player.O;
    }

  }

  @Override
  public boolean isGameOver() {
    for (int i = 0; i < 3; i++) {
      if (board[i][0] != null && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
        updateWinner();
        return true;
      }
    }
    for (int j = 0; j < 3; j++) {
      if (board[0][j] != null && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
        updateWinner();
        return true;
      }
    }
    if (board[0][0] != null && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
      updateWinner();
      return true;
    }
    if (board[0][2] != null && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
      updateWinner();
      return true;
    }
    return turnCounter == 9;
  }

  private void updateWinner() {
    if (turnCounter % 2 == 0) {
      winner = Player.O;
    } else {
      winner = Player.X;
    }
  }

  @Override
  public Player getWinner() {
    return winner;
  }

  @Override
  public Player[][] getBoard() {
    Player[][] returnBoard = new Player[3][3];
    for(int i=0;i<3;i++){
      for(int j=0;j<3;j++){
        returnBoard[i][j]=board[i][j];
      }
    }
    return returnBoard;
  }

  @Override
  public Player getMarkAt(int r, int c) {
    if (r < 0 || r > 2 || c < 0 || c > 2) {
      throw new IllegalArgumentException("The inputs are not valid");
    }
    return board[r][c];
  }

  @Override
  public String toString() {
    // Using Java stream API to save code:
    return Arrays.stream(getBoard()).map(
                    row -> " " + Arrays.stream(row).map(
                            p -> p == null ? " " : p.toString()).collect(Collectors.joining(" | ")))
            .collect(Collectors.joining("\n-----------\n"));
    // This is the equivalent code as above, but using iteration, and still using the helpful
    // built-in String.join method.
    // List<String> rows = new ArrayList<>();
    // for(Player[] row : getBoard()) {
    //   List<String> rowStrings = new ArrayList<>();
    //   for(Player p : row) {
    //     if(p == null) {
    //       rowStrings.add(" ");
    //     } else {
    //       rowStrings.add(p.toString());
    //     }
    //   }
    //   rows.add(" " + String.join(" | ", rowStrings));
    // }
    // return String.join("\n-----------\n", rows);
  }


}
