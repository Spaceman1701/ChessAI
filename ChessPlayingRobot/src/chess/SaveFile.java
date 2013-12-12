package chess;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import chess.gui.Tile;
import chess.gui.Piece;

public class SaveFile {
	private SaveFile() {
	}

	public static Tile[] loadFile(Tile[] tiles, File file) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line;
		int i = 0;
		try {
			while ((line = reader.readLine()) != null) {
				tiles[i].setPiece(Piece.fromString(line));
				i++;
			}
		} finally {
			reader.close();
		}
		return tiles;
	}

	public static void saveGame(Tile[] tiles, File file) throws IOException {
		FileWriter writer = new FileWriter(file);
		try {
			for (int i = 0; i < tiles.length; i++) {
				Tile t = tiles[i];
				writer.write(t.getPiece().toString() + System.getProperty("line.separator"));
			}
		} finally {
			writer.close();
		}
	}
}
