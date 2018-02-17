package org.training.java.chess.model.figures;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.security.InvalidParameterException;

import org.training.java.chess.game.Board;
import org.training.java.chess.model.coordinate.BoardCoordinate;
import org.training.java.chess.model.logging.Logger;

/**
 * Factory to creat a Figure Object
 * Can create a King, Queen, Rock, Bishop, Knight or Pawn 
 * @author Peter Heide, pheide@t-online.de
 *
 */
public class FigureFactory 
{	
	/**
	 * One single Method that can create all figure types and sets figure on the board
	 * @param figureClass figure type
	 * @param white color
	 * @param board figure knows board
	 * @param coordinate where figure stands
	 * @return figure
	 */
	public static Figure createFigure(
			Class<? extends Figure> figureClass, boolean white, 
			Board board, BoardCoordinate coordinate)
	{
		Figure figure = createFigure(figureClass, white);
		board.setFigure(coordinate, figure);
				
		return figure;
	}

	/**
	 * One single Method that can create all figure types
	 * @param figureClass figure type
	 * @param white color
	 * @return figure
	 */
	public static Figure createFigure(
			Class<? extends Figure> figureClass, boolean white)
			{
				if (figureClass == null) { // Validation
					String message = "Error in FigureFactory.createFigure(): figureClass is null"; 
					InvalidParameterException exception = new InvalidParameterException(message);
					Logger.log("Error in FigureFactory.createFigure(): figureClass is null");
					throw exception;
				} 
				
				// Create object using reflection instead of new()
				Figure figure = null;
				// Wenn es nur einen Konstruktor gibt, koennen Sie ihn so erhalten
				Constructor<?>[] constructors = figureClass.getConstructors();
				Constructor<?> constructor = constructors[0];
				// Erzeugen Sie eine Figur
				try {
					figure = (Figure) constructor.newInstance(white);
				} catch (InstantiationException | IllegalAccessException
						| IllegalArgumentException
						| InvocationTargetException e) {
					e.printStackTrace();
					System.exit(-1);
				}
				
				return figure;
			}
}
