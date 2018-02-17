package org.training.java.chess.model.player.test;

import java.security.InvalidParameterException;

import org.training.java.chess.game.Board;
import org.training.java.chess.game.Move;
import org.training.java.chess.model.coordinate.BoardCoordinate;
import org.training.java.chess.model.figures.Figure;

/**
 * Move with from Coordinate to Coordinate
 * Contains all information necessary to make a move and take it back: Figures, Fields, board, ep, ...
 * @author Peter Heide, pheide@t-online.de
 * @version 1
 * @since 17.02.2018
 */
public class MoveFactory {	
	/**
	 * Help Constructor for convenient use
	 * Client does not have to deliver all parameters 
     *
	 * @param fromCoord where figure stands before move
	 * @param toCoord where figure stands after move
	 * @param board where move belongs to
	 * @throws InvalidMoveException 
	 */
	public static Move makeMove(BoardCoordinate fromCoord, BoardCoordinate toCoord, Board board) {
		return makeMove(fromCoord, toCoord, null, board);
	}
	

	/**
	 * Help Constructor for convenient use
	 * Client does not have to deliver all parameters 
     *
	 * @param fromCoord where figure stands before move
	 * @param toCoord where figure stands after move
	 * @param exchange when pawn gets to figure
	 * @param board where move belongs to
	 * @throws InvalidMoveException 
	 */
	public static Move makeMove(BoardCoordinate fromCoord, BoardCoordinate toCoord, 
			Class<? extends Figure> exchange, Board board) {

		Figure fromFigure = board.getFigure(fromCoord);
		if (fromFigure == null) {
			throw new InvalidParameterException(org.training.java.chess.model.player.test.MoveFactory.class.getSimpleName() + 
					": from=" + fromCoord + ", toCoord=" + toCoord + ", exchange=" + exchange + 
					"board=" + board);					
		}
		Class<? extends Figure> from = fromFigure.getClass();

		Figure beatenFigure = board.getFigure(toCoord);
		Class<? extends Figure> beaten = null;
		if (beatenFigure != null) {
			beaten = beatenFigure.getClass();
		}
		
		return new Move(from, beaten, exchange, fromCoord, toCoord, board.getEnPassant(), 
				board.isOoWhite(), board.isOooWhite(), board.isOoBlack(), board.isOooBlack());
	}	
}
