package chess.gui;

import chess.ai.Move;
import chess.ai.Side;
import static chess.ai.Side.*;

public enum Piece {
	WHITE_PAWN('\u2659', 100, WHITE), BLACK_PAWN('\u265F', 100, BLACK), 
		WHITE_ROOK('\u2656', 500, WHITE), BLACK_ROOK('\u265C', 500, BLACK), 
		WHITE_BISHOP('\u2657', 300, WHITE), BLACK_BISHOP('\u265D', 300, BLACK), 
		WHITE_KNIGHT('\u2658', 300, WHITE), BLACK_KNIGHT('\u265E', 300, BLACK), 
		WHITE_QUEEN('\u2655', 900, WHITE), BLACK_QUEEN('\u265B', 900, BLACK), 
		WHITE_KING('\u2654', 10_000, WHITE), 
		BLACK_KING('\u265A', 10_000, BLACK), 
		NO_PIECE('\n', 0, EMPTY);
		
	public char code;
	public int value;
	public Side side;
	
	Piece(char code, int value, Side side) {
		this.code = code;
		this.value = value;
		this.side = side;
	}
	
	public static Piece fromString(String str) {
		for (Piece p : values()) {
			if (p.toString().equalsIgnoreCase(str)) {
				return p;
			}
		}
		return NO_PIECE;
	}
	
}
