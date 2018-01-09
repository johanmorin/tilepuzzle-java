package main.java.tilepuzzle.searchagent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import main.java.tilepuzzle.model.*;

public class SearchAgent {
	private Board board;
	private Integer[] solution;
	private ArrayList<Direction> path;
	private ArrayList<Direction> bestPath;
	private HashSet<Integer> visited;
	private int bestProgress;
	
	public SearchAgent(Board board) {
		this.board = board;
		this.solution = solution;
		this.path = new ArrayList<Direction>();
		this.bestPath = path;
		this.bestProgress = 0;
		this.visited = new HashSet<Integer>();
	}
	
	public void setSolution(Integer[] solution) {
		this.solution = solution;
	}
	
	private void searchDLS(int depth) {
		if (depth == 0 || solved()) {
			if (progress() > bestProgress) {
				System.out.println("Found new path: " +path +" : " +progress());
				bestPath = ((ArrayList<Direction>)path.clone());
				bestProgress = progress();
			}
			return;
		}
		
		Set<Direction> moves = board.getValidMoves();
		for (Direction d: moves) {
			if (!path.isEmpty()) {
				Direction lastMove = path.get(path.size() - 1);
				if (d == lastMove.getOppositeDirection()) {
					continue;
				}
			}

			board.move(d);
			path.add(d);
			
			int hashCode = board.hashCode();
			if (!visited.contains(hashCode)) {
				visited.add(hashCode);
				searchDLS(depth - 1);
			}

			board.move(d.getOppositeDirection());
			path.remove(path.size() - 1);
		}
	}

	public void search() {
		int depth = 50;
		path.clear();
		bestPath.clear();
		bestProgress = progress();

		searchDLS(depth);
	}
	
	public void searchIDS(int max) {
		System.out.println("searchIDS, max: " +max);
		for (int depth = 0; depth < max; depth++) {
			path.clear();
			bestPath.clear();
			visited.clear();
			bestProgress = progress();
			searchDLS(depth);
		}
		System.out.println("Visited nodes: " +visited.size());
	}
	
	public List<Direction> path() {
		return bestPath;
	}
	
	public boolean solved() {
		return bestProgress == board.numTiles();
	}
	
	public int progress() {
		int numCorrect = 0;

		if (board.numTiles() != solution.length) {
			System.out.println("Invalid solution!");
			return 0;
		}
		
		for (int i = 0; i < solution.length; i++) {
			if (board.getTile(i) == solution[i].intValue()) {
				numCorrect++;
			}
		}

		return numCorrect;
	}

}
