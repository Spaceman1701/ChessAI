package chess.gui;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import chess.ai.Move;

public class Board {

	static final char[] files = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h' };
	/**
	 * @ShreyHaria args the command line arguments
	 */
	private Tile[] board;

	public void gamestart() {

		GUI gui = new GUI();
		board = new Tile[64];
		int count = 0;
		for (int rank = 1; rank <= 8; rank++, count++) {
			for (char file = 'a'; file <= 'h'; file++) {
				int loc = getBoardLocation(file, rank) - 1;
				System.out.println("Count = " + count);
				System.out.println("Loc = " + loc);

				if ((file + rank) % 2 == 0) {
					board[loc] = new Tile("", this, loc, java.awt.Color.GRAY);
				} else {
					board[loc] = new Tile("", this, loc, java.awt.Color.WHITE);
				}

				gui.mainframe.add(board[loc].getLable());
				if (rank == 2) {
					board[loc].setPiece(Piece.WHITE_PAWN);
				} else if (rank == 7) {
					board[loc].setPiece(Piece.BLACK_PAWN);
				} else if (rank == 1) {
					switch (file) {
					case 'a':
					case 'h':
						board[loc].setPiece(Piece.WHITE_ROOK);
						break;
					case 'b':
					case 'g':
						// board[count].lbl.setIcon(WK);/**/
						/* heree got it */// board[count].lbl.set
						board[loc].setPiece(Piece.WHITE_KNIGHT);
						break;
					case 'c':
					case 'f':
						board[loc].setPiece(Piece.WHITE_BISHOP);
						break;
					case 'd':
						board[loc].setPiece(Piece.WHITE_QUEEN);
						break;
					case 'e':
						board[loc].setPiece(Piece.WHITE_KING);
						break;

					}
				} else if (rank == 8) {
					switch (file) {
					case 'a':
					case 'h':
						board[loc].setPiece(Piece.BLACK_ROOK);
						break;
					case 'b':
					case 'g':
						board[loc].setPiece(Piece.BLACK_KNIGHT);
						break;
					case 'c':
					case 'f':
						board[loc].setPiece(Piece.BLACK_BISHOP);
						break;
					case 'd':
						board[loc].setPiece(Piece.BLACK_QUEEN);
						break;
					case 'e':
						board[loc].setPiece(Piece.BLACK_KING);
						break;
					}
				}
			}
		}
		for (Tile t : board) {
			// System.out.println(t.getPiece() + " " + t.getPosition());
		}
		gui.mainframe.setVisible(true);
	}

	public Tile[] getBoard() {
		if (board == null) {
			System.err.println("board is null");
		}
		return board;
	}

	public int convertFile(char file) {
		for (int i = 1; i < files.length + 1; i++) {
			if (file == files[i - 1]) {
				return i;
			}
		}
		return -1;
	}

	public int getBoardLocation(char fileChar, int rank) {
		int file = convertFile(fileChar);
		int boardSoFar = (rank - 1) * 8;
		return boardSoFar + file;
	}

	public Piece pieceAt(int boardlocation) {
		if (boardlocation < 64 && boardlocation >= 0) {
			return board[boardlocation].getPiece();
		} else {
			return Piece.NO_PIECE;
		}
	}

	public boolean isPieceAt(int boardLocation) {
		return pieceAt(boardLocation) != Piece.NO_PIECE;
	}

	public boolean isMoveValid(Move move) {
		int startLocation = move.getStartLocation();
		int endLocation = move.getToLocation();
		//System.out.println("start = " + startLocation + " end = " + endLocation);
		if (endLocation < 0 || endLocation > 63) { // Outside vertical bounds
			return false;
		}
		if ((Math.abs(endLocation - startLocation) == 1)
				&& (getRank(endLocation) != getRank(startLocation))) {
			return false;
		}
		if (Math.abs(endLocation-startLocation) == 7 && getRank(endLocation) == getRank(startLocation)) { //simple horizontal checking
			return false;
		}
		if (endLocation % 8 == 0|| startLocation % 8 == 0 && (getRank(endLocation) != getRank(startLocation))) { //better horizontal checking
			if (endLocation % 8 == 0 && (startLocation+1) % 8 == 0) {
				return false;
			} else if ((endLocation+1) % 8 == 0){
				return false;
			}
		}
		return true;
	}
	
	
	public int getRank(int location) {
		float temp = ((float) location+1) / 8f;
		return (int) Math.ceil(temp);
	}
	public int ranksApart(int loc1, int loc2) {
		return Math.abs(getRank(loc1) - getRank(loc2));
	}
}

class GUI {

	static JFrame mainframe = new JFrame();
	GridLayout grid = new GridLayout(8, 8);

	GUI() {
		mainframe.setSize(700, 700);
		mainframe.setLayout(grid);
		mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}