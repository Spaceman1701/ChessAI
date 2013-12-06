package chess.ai;

public enum Side {
	WHITE(-1), BLACK(1), EMPTY(0);
	
	public int mod;
	Side(int mod) {
		this.mod = mod;
	}
	
	public static Side getOppisite(Side side) {
		if (side == WHITE) {
			return BLACK;
		} else {
			return WHITE;
		}
	}
}
