package test.java.tilepuzzletest;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import main.java.tilepuzzle.model.Board;
import main.java.tilepuzzle.model.Direction;
import main.java.tilepuzzle.model.Position;

public class BoardTest {
	static Board board;
	
	@BeforeClass
	public static void setup() {
		board = new Board(3, 3);
		System.out.println(board.toString());
	}

	@Test
	public void testCornerPositions() {
		assertEquals(0, board.getTile(new Position(0, 0)));
		assertEquals(2, board.getTile(new Position(2, 0)));
		assertEquals(6, board.getTile(new Position(0, 2)));
		assertEquals(8, board.getTile(new Position(2, 2)));
	}
	
	@Test
	public void testValidMoves() {
		Set<Direction> moves = board.getValidMoves();
		assertTrue(moves.size() >= 2);
		assertTrue(moves.size() <= 4);
	}
	
	@Test
	public void testMove() {
		Set<Direction> moves = board.getValidMoves();
		assertTrue(moves.size() > 0);

		Position lastPos = board.getPosition();
		board.move(moves.iterator().next());
		assertNotEquals(lastPos, board.getPosition());

		System.out.println(board.toString());

		lastPos = board.getPosition();
		board.move(moves.iterator().next());
		assertNotEquals(lastPos, board.getPosition());

		System.out.println(board.toString());
	}
	
	@AfterClass
	public static void teardown() {
	}

}
