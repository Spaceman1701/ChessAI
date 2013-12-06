package chess.ai.movecalculator;

import java.util.ArrayList;
import java.util.List;

import chess.ai.Move;
import chess.ai.Side;
import chess.gui.Board;
import chess.gui.Piece;
import chess.gui.Tile;

public class Utils {
	private Utils() {}
	public static boolean inRank(int rank, int location) {
		int min = (rank - 1) * 8;
		int max = (rank * 8) - 1;
		return (location >= min && location <= max);
	}
	
	public static List<Move> unknownPieceMove(Side side, Piece piece, int location, Board board) {
		if (piece.side == Side.WHITE) {
			if (Piece.WHITE_PAWN == piece) {
				return PawnMoveCalculator.possibleMoves(side, location, board);
			}
			else if (Piece.WHITE_ROOK == piece) {
				return RookMoveCalculator.possibleMoves(side, location, board);
			}
			else if (Piece.WHITE_BISHOP == piece) {
				return BishopMoveCalculator.possibleMoves(side, location, board);
			}
			else if (Piece.WHITE_KNIGHT == piece) {
				return KnightMoveCalculator.possibleMoves(side, location, board);
			}
			else if (Piece.WHITE_QUEEN == piece) {
				return QueenMoveCalculator.possibleMoves(side, location, board);
			}
			else if (Piece.WHITE_KING == piece) {
				return KingMoveCalculator.possibleMoves(side, location, board);
			}
		} else {
			if (Piece.BLACK_PAWN == piece) {
				return PawnMoveCalculator.possibleMoves(side, location, board);
			}
			else if (Piece.BLACK_ROOK == piece) {
				return RookMoveCalculator.possibleMoves(side, location, board);
			}
			else if (Piece.BLACK_BISHOP == piece) {
				return BishopMoveCalculator.possibleMoves(side, location, board);
			}
			else if (Piece.BLACK_KNIGHT == piece) {
				return KnightMoveCalculator.possibleMoves(side, location, board);
			}
			else if (Piece.BLACK_QUEEN == piece) {
				return QueenMoveCalculator.possibleMoves(side, location, board);
			}
			else if (Piece.BLACK_KING == piece) {
				return KingMoveCalculator.possibleMoves(side, location, board);
			}
		} 
		return null;
	}
}
