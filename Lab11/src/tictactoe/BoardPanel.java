package tictactoe;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Color;

import javax.swing.JPanel;

/**
 * BoardPanel class to draw the Tic-Tac-Toe board.
 * Visibility: package-private
 */
class BoardPanel extends JPanel {
  private final ReadonlyTttModel model;

  protected BoardPanel(ReadonlyTttModel model) {
    this.model = model;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    g.setFont(new Font("default", Font.BOLD, 28));
    if (!model.isGameOver()) {
      g2d.drawString(String.format("Current Turn: %s", model.getTurn()), 155, 50);
    } else if (model.getWinner() != null) {
      if (model.getWinner() == Player.X) {
        g2d.setColor(Color.RED);
      } else {
        g2d.setColor(Color.BLUE);
      }
      g2d.drawString(String.format("Game Over. %s wins", model.getWinner()), 125, 50);
    } else {
      g2d.drawString("Game Over. Its a draw", 102, 50);
    }

    g.setFont(new Font("default", Font.BOLD, 32));
    Player[][] board = model.getBoard();
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (board[i][j] != null) {
          if (board[i][j] == Player.X) {
            g2d.setColor(Color.RED);
          } else {
            g2d.setColor(Color.BLUE);
          }
          g2d.drawString(board[i][j].toString(), j * 100 + 138, i * 100 + 160);
        }
      }
    }

    g2d.setColor(Color.BLACK);
    g2d.drawLine(100, 200, 400, 200);
    g2d.drawLine(100, 300, 400, 300);
    g2d.drawLine(200, 100, 200, 400);
    g2d.drawLine(300, 100, 300, 400);
  }
}
