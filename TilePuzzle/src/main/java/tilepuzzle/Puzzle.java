package main.java.tilepuzzle;

import java.util.ArrayList;
import java.util.List;

import main.java.tilepuzzle.model.Board;
import main.java.tilepuzzle.model.Direction;
import main.java.tilepuzzle.searchagent.SearchAgent;
import main.java.tilepuzzle.view.ConsoleRenderer;

public class Puzzle {
	private Board board;
	private ConsoleRenderer renderer;
	private SearchAgent searchAgent;
	private List<Direction> path;
	private int depth = 0;
	
	public Puzzle(int size) {
		board = new Board(size, size);
		renderer = new ConsoleRenderer(board);
		searchAgent = new SearchAgent(board);
	}
	
	public void setSolution(Integer[] solution) {
		searchAgent.setSolution(solution);
	}
	
	public void update() {
		searchAgent.searchIDS(depth++);
		path = searchAgent.path();
		
		for (Direction d: path ) {
			board.move(d);
			renderer.draw();
			System.out.println("Progress: " +searchAgent.progress());
		}
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException ignored) { }
	}
	
	public void start() {
		renderer.draw();

		while (!searchAgent.solved()) {
			update();
		}

		System.out.println("Puzzle solved!");
	}

	public static void main(String[] args) {
		final Integer[] solution2x2 = {1, 2, 3, 0};
		final Integer[] solution3x3 = {1, 2, 3, 4, 5, 6, 7, 8, 0};
		final Integer[] solution4x4 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0};

		System.out.println("Starting TilePuzzle.");

		Puzzle puzzle = new Puzzle(3);
		puzzle.setSolution(solution3x3);
		
		puzzle.start();
	}
}
