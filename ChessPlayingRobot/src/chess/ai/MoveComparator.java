package chess.ai;

import java.util.Comparator;

import chess.gui.Piece;

public class MoveComparator<T> implements Comparator<Move>{


	@Override
	public int compare(Move o1, Move o2) {
		int v = o2.getCapturedPiece().value - o1.getCapturedPiece().value;
		if (v == 0) {
			return 1;
		}
		return v;
	}

}
