package chess.gui;

import chess.ai.Move;
import chess.ai.Side;
import static chess.ai.Side.*;

public enum Piece {
	WHITE_PAWN('\u2659', 1, WHITE), BLACK_PAWN('\u265F', 1, BLACK), 
		WHITE_ROOK('\u2656', 5, WHITE), BLACK_ROOK('\u265C', 5, BLACK), 
		WHITE_BISHOP('\u2657', 3, WHITE), BLACK_BISHOP('\u265D', 3, BLACK), 
		WHITE_KNIGHT('\u2658', 3, WHITE), BLACK_KNIGHT('\u265E', 3, BLACK), 
		WHITE_QUEEN('\u2655', 9, WHITE), BLACK_QUEEN('\u265B', 9, BLACK), 
		WHITE_KING('\u2654', 1500, WHITE), 
		BLACK_KING('\u265A', 1500, BLACK), 
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
