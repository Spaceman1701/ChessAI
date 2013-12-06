package chess.ai;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import chess.ai.movecalculator.PawnMoveCalculator;
import chess.ai.movecalculator.Utils;
import chess.gui.Board;
import chess.gui.Piece;
import chess.gui.Tile;

public class AIPlayer {
	private Board board;

	public AIPlayer(Board board) {
		this.board = board;
		new AITurnWindow(this);
	}

	public void makeTurn() {
		Random r = new Random();
		MoveHolder mh = maxi(4);
		System.out.println("Move score evaluated at: " + mh.eval);
		System.out.println(mh.move + "Piece captured: " + mh.move.getCapturedPiece());
		mh.move.make(board);
	}
	
	public MoveHolder maxi(int depth) {
		if (depth == 0) {
			return new MoveHolder(null, evaluate());
		}
		Move bestMove = null;
		int max = Integer.MIN_VALUE;
		for (Move m : getAllMoves(Side.BLACK, board)) { //The AI is always black
			
			m.make(board);
			MoveHolder mini = mini(depth-1);
			if (mini.eval > max) {
				max = mini.eval;
				bestMove = m;
			}
			m.unmake(board);
		}
		return new MoveHolder(bestMove, max);
	}

	private MoveHolder mini(int depth) {
		if (depth == 0) {
			return new MoveHolder(null,evaluate());
		}
		int min = Integer.MAX_VALUE;
		Move bestMove = null;
		for (Move m : getAllMoves(Side.WHITE, board)) { //The AI is always black
			m.make(board);
			MoveHolder maxi = maxi(depth-1);
			if (maxi.eval < min) {
				min = maxi.eval;
				bestMove = m;
			}
			m.unmake(board);
		}
		return new MoveHolder(bestMove, min);
	}

	public List<Move> getAllMoves(Side side, Board board) {
		List<Move> moves = new ArrayList<Move>();
		for (Tile t : board.getBoard()) {
			if (t.getPiece() != Piece.NO_PIECE && t.getPiece().side == side) {
				List<Move> pieceMoves = Utils.unknownPieceMove(side, t.getPiece(), t.getPosition(), board);
				if (pieceMoves != null) {
					moves.addAll(pieceMoves); //Collects all possible moves
				}
			}
		}
		return moves;
	}
	

	private Side getOppisiteSide(Side side) {
		if (side == Side.WHITE) {
			return Side.BLACK;
		} else {
			return Side.WHITE;
		}
	}

	private int evaluate() {
		int whiteValue = 0;
		int blackValue = 0;
		for (Tile t : board.getBoard()) {
			if (t.getPiece().side == Side.WHITE) {
				whiteValue += t.getPiece().value;
			} else if (t.getPiece().side == Side.BLACK) {
				blackValue += t.getPiece().value;
			}
		}
		return (blackValue - whiteValue); // AI is always black
	}
}
