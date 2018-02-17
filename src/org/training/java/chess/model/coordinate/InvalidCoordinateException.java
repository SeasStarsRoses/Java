/**
 * 
 */
package org.training.java.chess.model.coordinate;

/**
 * Thown when BoardCoordinate cannot be created
 * @author Peter Heide, pheide@t-online.de
 * @since 10.08.2012
 * @version 1 
 */
public class InvalidCoordinateException extends Exception {
	
	/**
	 * For Serialization
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param message e.g. column and row not between 0 and 7
	 */
	public InvalidCoordinateException(String message) {
		super(message);
	}
}
