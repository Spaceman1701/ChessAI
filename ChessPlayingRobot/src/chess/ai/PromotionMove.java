package chess.ai;

import chess.gui.Board;
import chess.gui.Piece;
import chess.gui.Tile;

public class PromotionMove extends Move{
	private Piece toPromote;

	public PromotionMove(Piece toPromote, Piece promotedTo, int startLocation, int toLocation,
			Board board) {
		super(promotedTo, startLocation, toLocation, board);
		this.toPromote = toPromote;
	}
	
	@Override
	public void unmake(Board board) {
		Tile start = board.getBoard()[startLocation];
		Tile end = board.getBoard()[toLocation];
		end.setPiece2(capturedPiece, startLocation, new String("set end from unmake: " + movedPiece));
		start.setPiece2(toPromote, toLocation, new String("set start from unmake: " + movedPiece));
	}
	
}
