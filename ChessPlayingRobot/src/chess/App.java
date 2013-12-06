package chess;
import chess.ai.AIPlayer;
import chess.gui.Board;

public class App {
	
	public static void main (String[] args) {
		Board board = new Board();
		board.gamestart();
		AIPlayer ai = new AIPlayer(board);
	}
}
