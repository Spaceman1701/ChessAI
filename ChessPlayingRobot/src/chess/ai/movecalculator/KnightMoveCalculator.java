package chess.ai.movecalculator;

import java.util.ArrayList;
import java.util.List;

import chess.ai.Move;
import chess.ai.Side;
import chess.gui.Board;
import chess.gui.Piece;

public class KnightMoveCalculator {
	
	private static final int[] VALUES = {6,10,15,17, -6,-10,-15,-17};
	private static final int[] PRUNINGVALUE = {6,10,-6,-10};
	
	public static List<Move> possibleMoves(Side side, int location, Board board) {
		
		List<Move> possibleMoves = new ArrayList<Move>();
		Side otherSide = Side.getOppisite(side);
		for (int i : VALUES) {
			int endLocation = location + i;
			Move possibleMove = new Move(getPiece(side), location, endLocation, board);
			if (board.isMoveValid(possibleMove) && board.pieceAt(endLocation).side != side && knightValid(possibleMove, i, board)) {
				possibleMoves.add(possibleMove);
			}
		}
		return possibleMoves;
	}
	
	private static Piece getPiece(Side side) {
		if (Side.BLACK == side) {
			return Piece.BLACK_KNIGHT;
		} else {
			return Piece.WHITE_KNIGHT;
		}
	}

	public static boolean knightValid(Move m, int value, Board board) {
		int startLoc = m.getStartLocation();
		int endLoc = m.getToLocation();
		int deltaRank = Math.abs(board.getRank(endLoc)-board.getRank(startLoc));
		if (deltaRank > 1 || deltaRank == 0) {
			for (int i : PRUNINGVALUE) {
				if (value == i) {
					return false;
				}
			}
		}
		return true;
	}

}
