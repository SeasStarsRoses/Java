package org.training.java.chess.model.player.test;


import org.junit.Assert;
import org.training.java.chess.game.Board;
import org.training.java.chess.game.Move;
import org.training.java.chess.model.ai.StrongAI;
import org.training.java.chess.model.coordinate.BoardCoordinate;
import org.training.java.chess.model.coordinate.InvalidCoordinateException;
import org.training.java.chess.model.figures.Figure;
import org.training.java.chess.model.player.ComputerPlayer;

/**
 * JUnit Test for AI
 * 
 * @author Peter Heide, pheide@t-online.de
 * @version 1
 * @since 17.02.2018
 */
public class Helper {

	/**
	 * Help method to test if beforeBoard + move = afterBoard
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be constructed
	 */
	public static void testMove(Board beforeBoard, Board afterBoard, int maxDepth) throws InvalidCoordinateException {
		Board beforeBoardClone = null;
		try {
			beforeBoardClone = beforeBoard.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		// Player who has next move makes one with strong KI and depth 1
		ComputerPlayer computerPlayer = null;
		StrongAI ai = new StrongAI();
		if (beforeBoard.isWhiteMoves()) {
			computerPlayer = Boards.getComputerWhite();
		} else {
			computerPlayer = Boards.getComputerBlack();			
		}
		ai.doTurn(computerPlayer, beforeBoard, maxDepth);			

		// Test move
		Assert.assertEquals("Board after move" + beforeBoard + afterBoard, 
				beforeBoard, afterBoard);

		// Test move back
		Move move = beforeBoard.getLastMove();
		testMoveBack(move, beforeBoardClone, beforeBoard);		
	}
	
	/**
	 * Test if a move can be taken back
	 * @param move the move to be taken back
	 * @param boardBefore Board before move
	 * @param boardAfter Board after move
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be constructed
	 */
	public static void testMoveBack(Move move, Board boardBefore, Board boardAfter) throws InvalidCoordinateException {
		BoardCoordinate toCoord = move.getToCoord();
		Figure boardFigure = boardAfter.getFigure(toCoord);
		Assert.assertNotNull("Figure after move not on to Coordinate", boardFigure);
		boardFigure.moveBack(boardAfter, move);
		Assert.assertEquals("Wrong board after taking back the move" + move + "\n" + 
				"Before: " + boardBefore + "After: " + boardAfter, boardBefore, boardAfter);
	}	
}
