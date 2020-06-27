package main.java.checkers.team1.test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.java.checkers.team1.CheckersBoard;
import main.java.checkers.team1.Move;
import main.java.model.utility.Point;

public class TestCheckersBoard {
	CheckersBoard checkersboard;
	int rows=8,cols=8;
	
	@Before
	public void setup(){
		checkersboard = new CheckersBoard(rows,cols);
	}
	
	@After
	public void tearDown(){
		checkersboard = null;
	}

	@Test
	public void testBoardDimensions() {
		assertEquals(rows,checkersboard.getDimension().getHeight());
		assertEquals(cols,checkersboard.getDimension().getWidth());
	}
	
	@Test
	public void testForCheckWin()
	{
		assertEquals(checkersboard.checkWin(1),-1);
		assertEquals(checkersboard.checkWin(0),1);
		assertEquals(checkersboard.checkWin(3),0);
	}
	
	@Test
	public void testUpdateBoardWithRequestedMove(){
		Move requestedMove = new Move(new Point(1,2),new Point(3,4)); //difference b/w x-cords=2
		checkersboard.updateBoardWith(requestedMove);
	}	

	@Test
	public void testUpdateBoardWithRequestedMove10(){
		Move requestedMove = new Move(new Point(2,4),new Point(6,5)); //difference b/w x-cords=4
		checkersboard.updateBoardWith(requestedMove);
		requestedMove = new Move(new Point(6,4),new Point(2,7));
		checkersboard.updateBoardWith(requestedMove);
		requestedMove = new Move(new Point(2,4),new Point(6,2));
		checkersboard.updateBoardWith(requestedMove);
		requestedMove = new Move(new Point(6,4),new Point(2,3));
		checkersboard.updateBoardWith(requestedMove);
	}
	
	@Test
	public void testUpdateBoardWithRequestedMove11(){
		Move requestedMove = new Move(new Point(1,2),new Point(7,7)); //difference b/w x-cords=6
		checkersboard.updateBoardWith(requestedMove);
		requestedMove = new Move(new Point(6,1),new Point(0,7)); 
		checkersboard.updateBoardWith(requestedMove);
		requestedMove = new Move(new Point(1,7),new Point(7,5)); 
		checkersboard.updateBoardWith(requestedMove);
		requestedMove = new Move(new Point(6,7),new Point(0,6)); 
		checkersboard.updateBoardWith(requestedMove);
	}
	
	
	@Test
	public void testUpdateBoardWithRequestedMove4(){
		Move requestedMove = new Move(new Point(5,2),new Point(1,6));
		checkersboard.updateBoardWith(requestedMove);
	}
	
	
	@Test
	public void testUpdateBoardWithRequestedMove5(){
		Move requestedMove = new Move(new Point(1,6),new Point(5,2));
		checkersboard.updateBoardWith(requestedMove);
	}
	
	
	@Test
	public void testUpdateBoardWithRequestedMove6(){
		Move requestedMove = new Move(new Point(5,6),new Point(1,2));
		checkersboard.updateBoardWith(requestedMove);
	}
	
	
	@Test
	public void testUpdateBoardWithRequestedMove7(){
		Move requestedMove = new Move(new Point(6,2),new Point(0,7));
		checkersboard.updateBoardWith(requestedMove);
	}
	
	
	@Test
	public void testUpdateBoardWithRequestedMove8(){
		Move requestedMove = new Move(new Point(0,7),new Point(6,2));
		checkersboard.updateBoardWith(requestedMove);
	}
	
	
	@Test
	public void testUpdateBoardWithRequestedMove9(){
		Move requestedMove = new Move(new Point(6,7),new Point(0,2));
		checkersboard.updateBoardWith(requestedMove);
	}
	
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testUpdateBoardWithInvalidMove(){
		Move requestedMove = new Move(new Point(1,2),new Point(10,10));
		checkersboard.updateBoardWith(requestedMove);
	}
	
	@Test
	public void testPlayerAt(){
		assertEquals(checkersboard.playerAt(3, 3),0);
	}
	
	@Test
	public void testIsValidMove(){
		assertEquals(checkersboard.isValidMove(1, 9, 9),false);
		assertEquals(checkersboard.isValidMove(1, 3, 4),true);
	}
	
	@Test
	public void testIsSkipPlayer(){
		assertEquals(checkersboard.isSkipPlayer(0),true);
	}
	
	@Test
	public void testLegalJumpInvalid(){
		Move requestedMove = new Move(new Point(1,2),new Point(3,4));
		assertFalse(checkersboard.isLegalJump(1,requestedMove,5,6));
	}
	
	@Test
	public void testLegalJumpInValid2(){
		Move requestedMove = new Move(new Point(5,7),new Point(4,6));
		assertFalse(checkersboard.isLegalJump(0,requestedMove,5,6));
	}

	@Test
	public void testLegalJumpInValid3(){
		Move requestedMove = new Move(new Point(2,2),new Point(4,4));
		assertFalse(checkersboard.isLegalJump(1,requestedMove,3,3));
	}
	@Test
	public void testPossibleMoves(){
		assertTrue(checkersboard.getPossibleMoves().length>0); // for initial position numner of possible moves is always more than 1
	}
	
	@Test
	public void testForLegalMoveInvalid(){
		Move requestedMove = new Move(new Point(5,7),new Point(8,8));
		assertFalse(checkersboard.isLegalMove(0,requestedMove));
	}
	
	@Test
	public void testForLegalMoveValid(){
		Move requestedMove = new Move(new Point(5,1),new Point(4,2));
		assertTrue(checkersboard.isLegalMove(0,requestedMove));
	}
}

