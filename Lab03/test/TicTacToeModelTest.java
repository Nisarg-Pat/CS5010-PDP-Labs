import org.junit.Before;
import org.junit.Test;

import tictactoe.Player;
import tictactoe.TicTacToe;
import tictactoe.TicTacToeModel;

import static org.junit.Assert.*;

public class TicTacToeModelTest {

  private TicTacToe ticTacToeGame;


  @Before
  public void setup(){
    ticTacToeGame = new TicTacToeModel();
  }

  @Test
  public void testStartPlayer(){
    assertEquals(Player.X, ticTacToeGame.getTurn());
  }

  @Test
  public void move() {
    ticTacToeGame.move(0,1);
    ticTacToeGame.move(0,0);
    ticTacToeGame.move(1,1);
    ticTacToeGame.move(0,0);
    ticTacToeGame.move(1,1);
    ticTacToeGame.move(1,2);
    ticTacToeGame.move(2,0);
    ticTacToeGame.move(2,1);
    ticTacToeGame.move(2,2);
    assertEquals(Player.X, ticTacToeGame.getMarkAt(0,0));
    assertEquals(Player.O, ticTacToeGame.getMarkAt(0,1));
    assertEquals(Player.X, ticTacToeGame.getMarkAt(0,2));
    assertEquals(Player.O, ticTacToeGame.getMarkAt(1,0));
    assertEquals(Player.X, ticTacToeGame.getMarkAt(1,1));
    assertEquals(Player.O, ticTacToeGame.getMarkAt(1,2));
    assertEquals(Player.X, ticTacToeGame.getMarkAt(2,0));
    assertEquals(Player.O, ticTacToeGame.getMarkAt(2,1));
    assertEquals(Player.X, ticTacToeGame.getMarkAt(2,2));
  }

  @Test
  public void testPlayerChanges(){
    assertEquals(Player.X, ticTacToeGame.getTurn());
    ticTacToeGame.move(0,0);
    assertEquals(Player.O, ticTacToeGame.getTurn());
    ticTacToeGame.move(0,1);
    assertEquals(Player.X, ticTacToeGame.getTurn());
    ticTacToeGame.move(0,2);
    assertEquals(Player.O, ticTacToeGame.getTurn());
    ticTacToeGame.move(1,0);
    assertEquals(Player.X, ticTacToeGame.getTurn());
    ticTacToeGame.move(1,1);
    assertEquals(Player.O, ticTacToeGame.getTurn());
    ticTacToeGame.move(1,2);
    assertEquals(Player.X, ticTacToeGame.getTurn());
    ticTacToeGame.move(2,0);
    assertEquals(Player.O, ticTacToeGame.getTurn());
    ticTacToeGame.move(2,1);
    assertEquals(Player.X, ticTacToeGame.getTurn());
  }

  @Test
  public void moveFailInvalidRow(){

  }

  @Test
  public void getTurn() {
  }

  @Test
  public void isGameOver() {
  }

  @Test
  public void getWinner() {
  }

  @Test
  public void getBoard() {
  }

  @Test
  public void getMarkAt() {
  }

  @Test
  public void testToString() {
  }
}