package main.java.tilepuzzle.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Board {
	private int width;
	private int height;
	
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}

	private ArrayList<Integer> tiles;
	private Set<Direction> validMoves;
	private Position position;
	
	public Board(int width, int height) {
		System.out.println(String.format("Creating board of size %d x %d.", width, height));
		this.width = width;
		this.height = height;
		this.validMoves = new HashSet<Direction>();
		createTiles();
	}
	
	private void createTiles() {
		int numTiles = width * height;
		tiles = new ArrayList<Integer>(numTiles);
		
		for (int i = 0; i < numTiles; i++) {
			tiles.add(i);
		}
		
		this.position = findEmptyTile();
		updateMoves();
	}
	
	private Position findEmptyTile() {
		for (int y = 0;  y < height; y++) {
			for (int x = 0; x < width; x++) {
				Position position = new Position(x, y);
				if (getTile(position) == 0) {
					return position;
				}
			}
		}
		return null;
	}
	
	private void updateMoves() {
		validMoves.clear();
		
		if (position.y > 0) {
			validMoves.add(Direction.UP);
		}
		if (position.y + 1 < height) {
			validMoves.add(Direction.DOWN);
		}
		if (position.x > 0) {
			validMoves.add(Direction.LEFT);
		}
		if (position.x + 1 < width) {
			validMoves.add(Direction.RIGHT);
		}
	}
	
	public int numTiles() {
		return tiles.size();
	}
	
	public Position getPosition() {
		return new Position(this.position);
	}
	
	public int getTile(Position position) {
		int index = position.y * this.width + position.x;
		return tiles.get(index);
	}
	
	public int getTile(int index) {
		return tiles.get(index);
	}

	public void setTile(Position position, int value) {
		int index = position.y * this.width + position.x;
		tiles.set(index, value);
	}
	
	public Set<Direction> getValidMoves() {
		return new HashSet<Direction>(validMoves);
	}
	
	public void move(Direction direction) {
		if (!validMoves.contains(direction)) {
			System.out.println("Invalid move.");
			return;
		}
		
		Position newPos = new Position(position);
		switch (direction) {
			case UP: newPos.y -= 1; break;
			case DOWN: newPos.y += 1; break;
			case LEFT: newPos.x -= 1; break;
			case RIGHT: newPos.x += 1; break;
		}
		
		swapTiles(position, newPos);
		position = newPos;
		updateMoves();
	}
	
	private void swapTiles(Position a, Position b) {
		int tmp = getTile(a);
		setTile(a, getTile(b));
		setTile(b, tmp);
	}
	
	public int hash() {
		int numBuckets = 42;
		int bucket = tiles.size() % numBuckets;
		
		for (int tile: tiles) {
			bucket = (bucket + tile) % numBuckets;
		}
		
		return bucket;
	}
	
	public int hashCode() {
		int hashCode = Arrays.deepHashCode(tiles.toArray());
		return hashCode;
	}
	
	public String toString() {
		StringBuffer s = new StringBuffer();
		Iterator<Integer> iter = tiles.iterator();
		while (iter.hasNext()) {
			for (int i = 0; i < this.width; i++) {
				s.append(iter.next() + " ");
			}
			s.append('\n');
		}

		return s.toString();
	}
}
