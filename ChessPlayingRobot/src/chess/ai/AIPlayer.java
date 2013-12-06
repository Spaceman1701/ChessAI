package chess.ai;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JProgressBar;

import chess.ai.movecalculator.PawnMoveCalculator;
import chess.ai.movecalculator.Utils;
import chess.gui.Board;
import chess.gui.Piece;
import chess.gui.Tile;

public class AIPlayer{
	private Board board;
	private static final int DEPTH = 6;
	private AITurnWindow progress;
	private int max;
	
	private static boolean inProgress = false;
	
	public static boolean isInProgress() {
		return inProgress;
	}

	public AIPlayer(Board board) {
		this.board = board;
		new AITurnWindow(this);
	}

	public void makeTurn(AITurnWindow window) {
		this.progress = window;
		new MoveThread(board, this).start();
	}

	public void makeDone() {
		inProgress = false;
	}

	public void makeInProgress() {
		inProgress = true;
	}

	public void resetProgress() {
		progress.resetProgress();
	}

	public void setProgressMax(int pow) {
		progress.setProgressMax(pow);
	}

	public void updateProgressBar(int i) {
		progress.updateProgressBar(i);
	}
}
