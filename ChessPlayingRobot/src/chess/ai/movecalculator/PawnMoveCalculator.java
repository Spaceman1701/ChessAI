package chess.ai.movecalculator;

import java.util.ArrayList;
import java.util.List;

import chess.ai.Move;
import chess.ai.Side;
import chess.gui.Board;
import chess.gui.Piece;

public class PawnMoveCalculator {
	private PawnMoveCalculator() {
	}

	public static List<Move> possibleMoves(Side side, int location,
			Board board) {
		if (side == Side.BLACK) {
			return calcBlack(location, board);
		} else {
			return calcWhite(location, board);
		}
	}

	private static List<Move> calcWhite(int location, Board board) {
		List<Integer> possibleMoves = new ArrayList<Integer>();
		if (Utils.inRank(2, location)) {
			possibleMoves.add(new Integer(+16));
		}
		possibleMoves.add(new Integer(+8));

		List<Move> moves = new ArrayList<Move>();

		if (board.pieceAt(location + 7).side == Side.BLACK) {
			if (board.ranksApart(location, location+7) == 1) {
				addMove(moves, new Move(getPiece(Side.WHITE), location, location + 7, board), board);
			}
		}
		if (board.pieceAt(location + 9).side == Side.BLACK) {
			if (board.ranksApart(location, location+9) == 1) {
				addMove(moves,new Move(getPiece(Side.WHITE), location, location + 9, board), board);
			}
		}
		for (Integer i : possibleMoves) {
			if (!board.isPieceAt(location + i)) {
				addMove(moves, new Move(getPiece(Side.WHITE), location, location + i, board), board);
			}
		}
		return moves;
	}

	private static List<Move> calcBlack(int location, Board board) {
		List<Integer> possibleMoves = new ArrayList<Integer>();
		if (Utils.inRank(7, location)) {
			possibleMoves.add(new Integer(-16));
		}
		possibleMoves.add(new Integer(-8));

		List<Move> moves = new ArrayList<Move>();

		if (board.pieceAt(location - 7).side == Side.WHITE) {
			if (board.ranksApart(location, location-7) == 1) {
				addMove(moves, new Move(getPiece(Side.BLACK), location, location - 7, board), board);
			}
		}
		if (board.pieceAt(location - 9).side == Side.WHITE) {
			if (board.ranksApart(location, location-9) == 1) {
				addMove(moves, new Move(getPiece(Side.BLACK), location, location - 9, board), board);
			//	System.out.println(board.ranksApart(location, location-9));
			}
		}
		for (Integer i : possibleMoves) {
			if (!board.isPieceAt(location + i)) {
				addMove(moves, new Move(getPiece(Side.BLACK), location, location + i, board), board);
			}
		}
		return moves;
	}
	
	private static Piece getPiece(Side side) {
		if (side == Side.WHITE) {
			return Piece.WHITE_PAWN;
		} else {
			return Piece.BLACK_PAWN;
		}
	}

	private static boolean addMove(List<Move> list, Move moveToAdd, Board board) {
		if (board.isMoveValid(moveToAdd)) {
			list.add(moveToAdd);
			return true;
		}
		return false;
	}
}
