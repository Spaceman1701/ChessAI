package chess.gui;

public class PieceTransferer {
	private static PieceTransferer instance;
	private Piece piece = Piece.NO_PIECE;
	
	private PieceTransferer() {}
	
	public static PieceTransferer getInstance() {
		if (instance == null) {
			instance = new PieceTransferer();
		}
		return instance;
	}
	
	public void givePiece(Piece piece) {
		this.piece = piece;
	}
	
	public Piece takePiece() {
		Piece temp = piece;
		piece = Piece.NO_PIECE;
		return temp;
	}
	
	public boolean isEmpty() {
		return piece == Piece.NO_PIECE;
	}
}
