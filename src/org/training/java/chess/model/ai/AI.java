/**
 * 
 */
package org.training.java.chess.model.ai;

import org.training.java.chess.game.Board;
import org.training.java.chess.model.coordinate.InvalidCoordinateException;
import org.training.java.chess.model.player.ComputerPlayer;

/**
 * Artificial Intelligence for Computer moves
 * @author Peter Heide, pheide@t-online.de
 * @since 05.09.2012
 */
public interface AI {
	/**
	 * doTurn makes a move
	 * @param computerPlayer The computer player who moves
	 * @param board The board of the computer player where he makes the moves
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be created
	 */
	void doTurn(ComputerPlayer computerPlayer, Board board) throws InvalidCoordinateException;
}
