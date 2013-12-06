package chess.ai;

import chess.gui.Board;
import chess.gui.Piece;
import chess.gui.Tile;

public class Move {
	private final int startLocation;
	private final int toLocation;
	private final int value;
	private final Piece capturedPiece;
	private final Piece movedPiece;
	private int eval = 0;
	
	public Move(Piece movedPiece, int startLocation, int toLocation, Board board) {
		this.startLocation = startLocation;
		this.toLocation = toLocation;
		capturedPiece = board.pieceAt(toLocation);
		value = capturedPiece.value;
		this.movedPiece = movedPiece;
	}
	
	public int getToLocation() {
		return toLocation;
	}
	
	public int getValue() {
		return value;
	}
	
	public Piece getCapturedPiece() {
		return capturedPiece;
	}
	
	public int getStartLocation() {
		return startLocation;
	}
	
	public void make(Board board) {
		Tile start = board.getBoard()[startLocation];
		Tile end = board.getBoard()[toLocation];
		start.setPiece2(Piece.NO_PIECE, toLocation, new String("set start from make: " + movedPiece));
		end.setPiece2(movedPiece, startLocation, new String("set end from make: " + movedPiece));
	}
	
	public void unmake(Board board) {
		Tile start = board.getBoard()[startLocation];
		Tile end = board.getBoard()[toLocation];
		end.setPiece2(capturedPiece, startLocation, new String("set end from unmake: " + movedPiece));
		start.setPiece2(movedPiece, toLocation, new String("set start from unmake: " + movedPiece));
	}
	
	public void setEval(int newValue) {
		eval = newValue;
	}
	public int getEval() {
		return eval;
	}
	public String toString() {
		return movedPiece +" Move from " + startLocation + " to " + toLocation;
	}
}
