package chess.ai.movecalculator;

import java.util.ArrayList;
import java.util.List;

import chess.ai.Move;
import chess.ai.Side;
import chess.gui.Board;
import chess.gui.Piece;

public class KingMoveCalculator {
	private static final int[] possibleMoves = {8,-8, 1,-1, 7,-7,9,-9};
	public static List<Move> possibleMoves(Side side, int location, Board board) {
		List<Move> moves = new ArrayList<Move>();
		for (int i : possibleMoves) {
			if (board.pieceAt(location+i).side != side) {
				Move m = new Move(getPiece(side), location, location+i, board);
				if (board.isMoveValid(m)) {
					moves.add(m);
				}
			}
 		}
		return moves;
	}
	private static Piece getPiece(Side side) {
		if (side == Side.WHITE) {
			return Piece.WHITE_KING;
		} else {
			return Piece.BLACK_KING;
		}
	}

}
