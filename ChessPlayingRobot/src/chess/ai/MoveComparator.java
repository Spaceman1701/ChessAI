package chess.ai;

import java.util.Comparator;

import chess.gui.Piece;

public class MoveComparator<T> implements Comparator<Move>{


	@Override
	public int compare(Move o1, Move o2) {
		return o1.getCapturedPiece().value - o2.getCapturedPiece().value;
	}

}
