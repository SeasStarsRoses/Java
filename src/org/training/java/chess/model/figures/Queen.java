package org.training.java.chess.model.figures;

import org.training.java.chess.model.coordinate.BoardCoordinate;
import org.training.java.chess.model.coordinate.InvalidCoordinateException;
import org.training.java.chess.model.coordinate.MoveCoordinate;

/** 
 * Figure Class Queen
 * @author Peter Heide, pheide@t-online.de
 * @version 1 
 */
public class Queen extends Figure 
{
	/** Material value */
	public static final int material = 9000;

	/**
	 * @param white color of the figure is white or not white (black)
	 * @see Figure#Figure(boolean, BoardCoordinate) super constructor
	 */
	public Queen(boolean white) 
	{
		super(white);
		try {
			moves.add(new MoveCoordinate(0, 1, 7));
			moves.add(new MoveCoordinate(0, -1, 7));
			moves.add(new MoveCoordinate(1, -1, 7));
			moves.add(new MoveCoordinate(1, 0, 7));
			moves.add(new MoveCoordinate(1, 1, 7));
			moves.add(new MoveCoordinate(-1, -1, 7));
			moves.add(new MoveCoordinate(-1, 0, 7));
			moves.add(new MoveCoordinate(-1, 1, 7));
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
	public int getValue() { return white ? Queen.material : -Queen.material; }	
}
