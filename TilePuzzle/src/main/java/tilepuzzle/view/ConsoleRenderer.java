package tilepuzzle.view;

import tilepuzzle.model.Board;
import tilepuzzle.model.Position;

public class ConsoleRenderer {
	private Board board;
	
	public ConsoleRenderer(Board board) {
		this.board = board;
	}
	
	public void draw() {
		System.out.println("--------");
		for (int y = 0; y < board.getHeight(); y++) {
			for (int x = 0; x < board.getWidth(); x++ ) {
				int tile = board.getTile(new Position(x, y));
				System.out.print(tile + " ");
			}
			System.out.print('\n');
		}
		System.out.println("--------");
	}
}
