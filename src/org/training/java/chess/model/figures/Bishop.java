
package org.training.java.chess.model.figures;

import org.training.java.chess.game.Board;
import org.training.java.chess.model.coordinate.BoardCoordinate;
import org.training.java.chess.model.coordinate.InvalidCoordinateException;
import org.training.java.chess.model.coordinate.MoveCoordinate;

/** 
 * Figure Class Bishop
 * @author Peter Heide, pheide@t-online.de
 * @version 1 
 */
public class Bishop extends Figure 
{
	/** Material value */
	public static final int material = 3000;

	/**
	 * @param white color of the figure is white or not white (black)
	 * @see Figure#Figure(boolean, Board, BoardCoordinate) super constructor
	 */
	public Bishop(boolean white) 
	{
		super(white);
		try {
			moves.add(new MoveCoordinate(1, -1, 7));
			moves.add(new MoveCoordinate(-1, 1, 7));
			moves.add(new MoveCoordinate(1, 1, 7));
			moves.add(new MoveCoordinate(-1, -1, 7));
		} catch (InvalidCoordinateException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}	
	
	/**
	 * Getter for material value of figure 
	 * dependent of black (negative) or white (positive)
	 * @return the value
	 */
	public int getValue() { return white ? Bishop.material : -Bishop.material; }	
}
