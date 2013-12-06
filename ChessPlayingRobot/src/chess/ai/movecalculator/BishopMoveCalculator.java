package chess.ai.movecalculator;

import java.util.ArrayList;
import java.util.List;

import chess.ai.Move;
import chess.ai.Side;
import chess.gui.Board;
import chess.gui.Piece;

public class BishopMoveCalculator {

	private static final int[] possibleMoves = {7,-7,9,-9};
	public static List<Move> possibleMoves(Side side, int location, Board board) {
		List<Move> moves = new ArrayList<Move>();
		for (int i : possibleMoves ) {
			Move lastMove = new Move(getPiece(side), location, location+i, board);
			if (!board.isMoveValid(lastMove)) {
				continue;
			}
			if (board.isPieceAt(lastMove.getToLocation())) {
				Piece p = board.pieceAt(lastMove.getToLocation());
				if (p.side != side) {
					moves.add(lastMove);
				}
				continue;
			}
			moves.add(lastMove);
			boolean keepLooping = true;
			while(keepLooping) {
				Move newMove = new Move(getPiece(side), lastMove.getToLocation(), lastMove.getToLocation()+i, board);
				if (!board.isMoveValid(newMove)) { //Move is impossible
					keepLooping = false;
					continue;
				}
				if (board.isPieceAt(newMove.getToLocation())) { //Move is either impossible or last possible move
					Piece p = board.pieceAt(newMove.getToLocation());
					if (p.side != side) {
						Move finalMove = new Move(getPiece(side), location, newMove.getToLocation(), board);
						moves.add(finalMove);
					}
					keepLooping = false;
					continue;
				}
				Move finalMove = new Move(getPiece(side), location, newMove.getToLocation(), board);
				moves.add(finalMove); //Keep getting new moves on this path
				lastMove = newMove;
			}
		}
		return moves;
	}
	private static final Piece getPiece(Side side) {
		if (side == Side.WHITE) {
			return Piece.WHITE_BISHOP;
		} else {
			return Piece.BLACK_BISHOP;
		}
	}
}
