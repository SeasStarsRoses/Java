package org.training.java.chess.model.figures;

import org.training.java.chess.game.Board;
import org.training.java.chess.game.Move;
import org.training.java.chess.model.coordinate.BoardCoordinate;
import org.training.java.chess.model.coordinate.InvalidCoordinateException;
import org.training.java.chess.model.coordinate.MoveCoordinate;

/** 
 * Figure Class Rock
 * @author Peter Heide, pheide@t-online.de
 * @since 17.02.2018
 * @version 1 
 */
public class Rook extends Figure 
{
	/** Material value */
	public static final int material = 4500;

	/**
	 * Constructor for Rock
	 * @param white color of the figure is white or not white (black)
	 * @param coordinate so that figure knows where it stands
	 * @see Figure#Figure(boolean, BoardCoordinate) super constructor
	 */
	public Rook(boolean white) 
	{
		super(white);
		try {
			moves.add(new MoveCoordinate(1, 0, 7));
			moves.add(new MoveCoordinate(0, 1, 7));
			moves.add(new MoveCoordinate(-1, 0, 7));
			moves.add(new MoveCoordinate(0, -1, 7));
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
	public int getValue() { return white ? Rook.material : -Rook.material; }	

	/* 
	 * When Rock moves, then Rochade might be imposible
	 * @param board where figure stands
	 * @param move the figure move
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be created
	 */
	@Override
	public void move(Board board, Move move) throws InvalidCoordinateException {
		super.move(board, move);
		BoardCoordinate fromCoord = move.getFromCoord();
		if (fromCoord.equals(BoardCoordinate.A1)) {
			board.setOooWhite(false);
		} else if (fromCoord.equals(BoardCoordinate.H1)) {
			board.setOoWhite(false);			
		} else if (fromCoord.equals(BoardCoordinate.A8)) {
			board.setOooBlack(false);
		} else if (fromCoord.equals(BoardCoordinate.H8)) {
			board.setOoBlack(false);
	    } 			
	}
}
