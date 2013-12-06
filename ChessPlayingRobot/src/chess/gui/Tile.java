package chess.gui;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import chess.ai.AITurnWindow;

public class Tile {

	private JLabel lbl;
	private static Font unicode = new Font("Arial Unicode MS", Font.BOLD, 50);
	private static LineBorder border = new LineBorder(java.awt.Color.BLACK, 2);
	private Piece piece;

	private Board board;
	private final int position;

	Tile(String lblname, Board parent, final int position, java.awt.Color tc) {
		System.out.println(position);
		this.position = position;
		board = parent;
		lbl = new JLabel();
		lbl.setFont(unicode);
		lbl.setHorizontalAlignment(JLabel.CENTER);
		lbl.setText(lblname);
		lbl.setBackground(tc);
		lbl.setBorder(border);
		lbl.setOpaque(true);
		setPiece(Piece.NO_PIECE);
		lbl.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				AITurnWindow.setMouseOver(position);
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getButton() == MouseEvent.BUTTON1) {
					if (PieceTransferer.getInstance().isEmpty()) {
						PieceTransferer.getInstance().givePiece(getPiece());
						setPiece(Piece.NO_PIECE);
					} else {
						setPiece(PieceTransferer.getInstance().takePiece());
					}
				} else {
					System.out.println("Piece value is: " + piece.value);
					setPiece(Piece.NO_PIECE);
				}
			}
		});
	}

	public JLabel getLable() {
		return lbl;
	}
	public void setPiece2(Piece piece, int  cappingTile, String msg) {
	//	if (position == 0) {
	//		System.out.println("0 position called to set piece to:" + piece + " taken from " + cappingTile + " MSG: " + msg);
	//	}
		setPiece(piece);
	}
	public void setPiece(Piece piece) {
		
		this.piece = piece;
		lbl.setText(String.valueOf(piece.code));
	}

	public Piece getPiece() {
		return piece;
	}

	public int getPosition() {
		return position;
	}
}
