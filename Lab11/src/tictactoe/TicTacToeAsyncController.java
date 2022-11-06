package tictactoe;

/**
 * Asynchronous Controller implementation for Tic Tac Toe.
 * It includes the Tic-Tac-Toe model and a view to play the game on it.
 */
public class TicTacToeAsyncController implements TicTacToeController {

  TicTacToe model;
  TicTacToeView view;

  /**
   * Constructor for TicTacToeAsyncController. Takes in a model and a view.
   *
   * @param model The model for the Tic-Tac-Tow game.
   * @param view  The view for Tic-Tac-Tow game.
   */
  public TicTacToeAsyncController(TicTacToe model, TicTacToeView view) {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null.");
    }
    if (view == null) {
      throw new IllegalArgumentException("View cannot be null.");
    }
    this.model = model;
    this.view = view;
  }

  @Override
  public void playGame(TicTacToe m) {
    try {
      view.addClickListener(this);
      view.makeVisible();
    } catch (IllegalArgumentException | IllegalStateException e) {
      handleError(e);
    }

  }

  @Override
  public void handleCellClick(int row, int col) {
    try {
      if (!model.isGameOver() && model.getMarkAt(row, col) == null) {
        model.move(row, col);
        view.refresh();
      }
    } catch (IllegalArgumentException | IllegalStateException e) {
      handleError(e);
    }

  }

  private void handleError(Exception e) {
    //Ignore the caught error
  }
}
