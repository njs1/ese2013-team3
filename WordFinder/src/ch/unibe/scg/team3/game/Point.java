package ch.unibe.scg.team3.game;

/**
 * 
 * @author adrian
 * 
 */
public class Point {

	private final int x;
	private final int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	@Override
	public String toString() {
		return "(" + this.x + ", " + this.y + ")";
	}
}
