package tictactoe11;

/**
 * Run a TicTacToe game interactively.
 */
public class Main {
  /**
   * Run a TicTacToe game interactively.
   */
  public static void main(String[] args) {
    // Old News: console-based game:
    //new TicTacToeConsoleController(new InputStreamReader(System.in),
    //    System.out).playGame(new TicTacToeModel());

    // New Hotness: Graphical User Interface:
    // 1. Create an instance of the model.
    // 2. Create an instance of the view.
    // 3. Create an instance of the controller.
    // 4. Call playGame() on the controller.
    try {
      TicTacToe model = new TicTacToeModel();
      TicTacToeView view = new TicTacToeSpringView(model);
      TicTacToeController controller = new TicTacToeAsyncController(model, view);
      controller.playGame(model);
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }

  }
}
