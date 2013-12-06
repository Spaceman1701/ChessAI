package chess.ai;

public class MoveHolder {
	public Move move;
	public int eval;
	public MoveHolder(Move m, int eval) {
		move = m;
		this.eval = eval;
	}
}
