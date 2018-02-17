package org.training.java.chess.model.player;

import org.training.java.chess.model.ai.AI;
import org.training.java.chess.model.ai.StrongAI;
import org.training.java.chess.model.coordinate.InvalidCoordinateException;

/**
 * Computer Player extends Player
 * @author Peter Heide, pheide@t-online.de
 * @since 05.09.2012
 */
public class ComputerPlayer extends Player {
	
	/** Artificial Intellicence for doTurn method that makes moves */
	private AI ai = new StrongAI();
	
	/**
	 * Constructor with color
	 * @param white color
	 */
	public ComputerPlayer(boolean white) {
		super(white);
	}

	/**
	 * doTurn delegates making moves to AI
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be created
	 */
	public void doTurn() throws InvalidCoordinateException {
		ai.doTurn(this, board);
	}

	/**
	 * Setter for AI
	 * @param ai the ai to set
	 */
	public void setAI(AI ai) {
		this.ai = ai;
	}	

	/**
	 * Give back Class name and color
	 */
	public String toString() {
		return super.toString() + " with " + ai.getClass().getSimpleName();
	}

}
