package org.training.java.chess.model.player.test;

import org.junit.Assert;

import org.junit.Test;
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
public class Moves {		
	/**
	 * White wins in one move (chess mate)
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be constructed
	 */
	@Test
	public void testMoveBack() throws InvalidCoordinateException {
		Board board = Boards.setPositionMateInOneMoveBefore();
		BoardCoordinate fromCoord = BoardCoordinate.H1;
		Figure fromFigure = board.getFigure(fromCoord);
		BoardCoordinate toCoord = BoardCoordinate.H8;
		Figure beatenFigure = board.getFigure(BoardCoordinate.H8);
		Class<? extends Figure> beaten = beatenFigure == null ? null : beatenFigure.getClass();
		
		Move move = new Move(fromFigure.getClass(), beaten, null, fromCoord, toCoord, 
				null, false, false, false, false);
		fromFigure.move(board ,move);
		
		Figure toFigure = board.getFigure(toCoord);
		toFigure.moveBack(board, move);
	}	

	/**
	 * White wins in one move (chess mate)
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be constructed
	 */
	@Test
	public void testMove() throws InvalidCoordinateException {
		Board board = Boards.setPositionStart();
		
		BoardCoordinate fromCoord = BoardCoordinate.E2;
		Figure fromFigure = board.getFigure(fromCoord);
		Class<? extends Figure> from = fromFigure.getClass();
		BoardCoordinate toCoord = BoardCoordinate.E4;
		Figure beatenFigure = board.getFigure(toCoord);
		Class<? extends Figure> beaten = beatenFigure == null ? null : beatenFigure.getClass();
		Move move = new Move(from, beaten, null, fromCoord, toCoord, null, true, true, true, true);
		
		fromFigure.move(board, move);
		
		fromFigure = board.getFigure(fromCoord);
		Assert.assertNull(fromFigure);
		Figure toFigure = board.getFigure(toCoord);
		Assert.assertNotNull(toFigure);
	}		

	/**
	 * Test first move on a normal chess board
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be constructed
	 */
	@Test
	public void testFirstMove() throws InvalidCoordinateException {
		Board board = Boards.setPositionStart();	
		ComputerPlayer computerWhite = Boards.getComputerWhite();
		
		StrongAI ai = new StrongAI();
		computerWhite.setAI(ai);
		ai.doTurn(computerWhite, board, 5000L);
		Move move = board.getLastMove();
		Assert.assertNotNull("There must be a move at starting position", move);
	}		
}
