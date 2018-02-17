package org.training.java.chess.model.player.test;

import org.junit.Test;
import org.training.java.chess.game.Board;
import org.training.java.chess.model.coordinate.InvalidCoordinateException;

/**
 * JUnit Test for AI
 * 
 * @author Peter Heide, pheide@t-online.de
 * @version 1
 * @since 17.02.2018
 */
public class MateInNMoves {

	/**
	 * White wins in one move (chess mate)
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be constructed
	 */
	@Test
	public void testWhiteWinsInOneMove() throws InvalidCoordinateException {
		Board beforeBoard = Boards.setPositionMateInOneMoveBefore();
		Board afterBoard = Boards.setPositionMateInOneMoveAfter();
		Helper.testMove(beforeBoard, afterBoard, 2);
	}

	/**
	 * White wins in two move (chess mate)
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be constructed
	 */
	@Test
	public void testWhiteWinsInTwoMoves() throws InvalidCoordinateException {
		Board beforeBoard = Boards.setPositionMateInTwoMovesBefore();
		Board afterBoard = Boards.setPositionMateInTwoMovesAfter();
		Helper.testMove(beforeBoard, afterBoard, 4);
	}	

	/**
	 * White wins in three moves (chess mate)
	 */
//	@Test
//	public void testWhiteWinsInThreeMoves() {
//		Board beforeBoard = Boards.setPositionMateInThreeMovesBefore();
//		Board afterBoard = Boards.setPositionMateInThreeMovesAfter();
//		Helper.testMove(beforeBoard, afterBoard, 6);
//	}	
}
