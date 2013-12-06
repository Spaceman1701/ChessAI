package chess.ai;

import java.util.ArrayList;
import java.util.List;

import chess.ai.movecalculator.Utils;
import chess.gui.Board;
import chess.gui.Piece;
import chess.gui.Tile;

public class MoveThread extends Thread{
	private Board board;
	private AIPlayer ai;
	
	private static final int DEPTH = 6;
	
	public MoveThread(Board board, AIPlayer ai) {
		this.ai = ai;
		this.board = board;
	}
	public void run() {
		ai.makeInProgress();
		ai.resetProgress();
		ai.setProgressMax((int) Math.pow(15, DEPTH));
		
		MoveHolder mh = maxi(Integer.MIN_VALUE, Integer.MAX_VALUE, DEPTH);
		System.out.println("Move score evaluated at: " + mh.eval);
		System.out.println(mh.move + "Piece captured: " + mh.move.getCapturedPiece());
		mh.move.make(board);
		ai.updateLastMove(mh.move + " Piece captured: " + mh.move.getCapturedPiece());
		ai.makeDone();
	}
	
	public void updateProgress(int i) {
		ai.updateProgressBar(i);
	//	System.out.println("progress bar updated by " + i);
	}
	
	public MoveHolder maxi(int alpha, int beta, int depth) {
		if (depth == 0) {
			return new MoveHolder(null, evaluate());
		}
		Move bestMove = null;
		List<Move> moves = getAllMoves(Side.BLACK, board);
		int i = 0;
		for (Move m : moves) { //The AI is always black
			
			m.make(board);
			MoveHolder mini = mini(alpha, beta, depth-1);
			m.unmake(board);
			if (mini.eval >= beta) {
				updateProgress(moves.size() - i);
				return new MoveHolder(m, beta);
			}
			if (mini.eval > alpha) {
				alpha = mini.eval;
				bestMove = m;
			}
			i++;
			updateProgress(i);
		}
		return new MoveHolder(bestMove, alpha);
	}

	private MoveHolder mini(int alpha, int beta, int depth) {
		if (depth == 0) {
			return new MoveHolder(null,evaluate());
		}
		//int min = Integer.MAX_VALUE;
		Move bestMove = null;
		List<Move> moves = getAllMoves(Side.WHITE, board);
		int  i = 0;
		for (Move m : moves) { //The AI is always black
			m.make(board);
			MoveHolder maxi = maxi(alpha, beta, depth-1);
			m.unmake(board);
			if (maxi.eval <= alpha) {
				updateProgress(moves.size() - i);
				return new MoveHolder(m, alpha);
			}
			if (maxi.eval < beta) {
				beta = maxi.eval;
				bestMove = m;
			}
			i++;
			updateProgress(i);
		}
		
		return new MoveHolder(bestMove, beta);
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
