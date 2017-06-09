package tilepuzzle.model;

public class Position {
	public int x;
	public int y;

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Position(Position other) {
		this.x = other.x;
		this.y = other.y;
	}
	
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Position)) {
			return false;
		}
		Position that = (Position) other;
		return this.y == that.y && this.x == that.x;
	}
	
	@Override
	public String toString() {
		return String.format("(%d, %d)", x, y);
	}
}
