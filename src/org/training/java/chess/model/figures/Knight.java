package org.training.java.chess.model.figures;

import org.training.java.chess.game.Board;
import org.training.java.chess.model.coordinate.BoardCoordinate;
import org.training.java.chess.model.coordinate.InvalidCoordinateException;
import org.training.java.chess.model.coordinate.MoveCoordinate;

/** 
 * Figure Class Knight
 * @author Peter Heide, pheide@t-online.de
 * @version 1 
 */
public class Knight extends Figure 
{
	/** Material value */
	public static final int material = 3000;

	/**
	 * Constructor for Knight
	 * @param white color of the figure is white or not white (black)
	 * @see Figure#Figure(boolean, Board, BoardCoordinate) super constructor
	 */
	public Knight(boolean white) 
	{
		super(white);
		try {
			moves.add(new MoveCoordinate(1, 2));
			moves.add(new MoveCoordinate(2, 1));
			moves.add(new MoveCoordinate(-1, 2));
			moves.add(new MoveCoordinate(2, -1));
			moves.add(new MoveCoordinate(1, -2));
			moves.add(new MoveCoordinate(-2, 1));
			moves.add(new MoveCoordinate(-1, -2));
			moves.add(new MoveCoordinate(-2, -1));
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
	public int getValue() { return white ? Knight.material : -Knight.material; }	

	/**
	 * Getter for String with the first letter of the name for displaying purposes
	 * @return String with the first letter of the name, e.g. 'N' for "Knight"
	 */
	@Override
	public char getMoveChar()
	{
		return 'N';
	}	
	
	/**
	 * Getter for String with the first letter of the name for displaying purposes
	 * in board display
	 * @return String with the first letter of the name, e.g. 'K' for "King"
	 */
	public char getBoardChar()
	{
		Character c = 'N';
		if (!white) {
			c = Character.toLowerCase(c);
		}
		return c;
	}
}
