package tictactoe;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * Tic-Tac-Toe view based on Spring.
 * Player needs to click the corresponding area where they want to put X or O.
 */
public class TicTacToeSpringView extends JFrame implements TicTacToeView {

  private final BoardPanel boardPanel;

  /**
   * Constructor for TicTacToeSpringView. It takes a Read-only model.
   *
   * @param model The Read-only Tic-Tac-Toe model for the View.
   */
  public TicTacToeSpringView(ReadonlyTttModel model) {
    super("Tic-Tac-Toe");
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null.");
    }
    this.setSize(500, 500);
    this.setLocation(500, 200);
    this.setResizable(false);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    boardPanel = new BoardPanel(model);
    add(boardPanel);
  }

  @Override
  public void addClickListener(TicTacToeController listener) {
    MouseAdapter mouseAdapter = new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        Pair coordinates = getCoordinatesFromClick(e.getX(), e.getY());
        if (coordinates.getX() != -1 || coordinates.getY() != -1) {
          listener.handleCellClick(coordinates.getX(), coordinates.getY());
        }
      }
    };
    boardPanel.addMouseListener(mouseAdapter);
  }

  private Pair getCoordinatesFromClick(int x, int y) {
    int r = -1;
    int c = -1;
    if (x >= 100 && x < 200) {
      c = 0;
    } else if (x >= 200 && x < 300) {
      c = 1;
    } else if (x >= 300 && x < 400) {
      c = 2;
    }

    if (y >= 100 && y < 200) {
      r = 0;
    } else if (y >= 200 && y < 300) {
      r = 1;
    } else if (y >= 300 && y < 400) {
      r = 2;
    }
    if (r != -1 && c != -1) {
      return new Pair(r, c);
    } else {
      return new Pair(-1, -1);
    }
  }

  @Override
  public void refresh() {
    repaint();
  }

  @Override
  public void makeVisible() {
    setVisible(true);
  }
}
