/**
 * 
 */
package org.training.java.chess.model.player;

import org.training.java.chess.game.Board;

/**
 * Player can be Human or Computer
 * @author Peter Heide, pheide@t-online.de
 */
public abstract class Player {
	/**
	 * Color of player
	 */
	boolean white;
	
	/**
	 * Two players know each other
	 */
	Player enemy;
	
	/**
	 * Player knows its board
	 */
	Board board;
	
	/**
	 * Constructor with color
	 * @param white color
	 */
	public Player(boolean white) {
		super();
		this.white = white;
	}
		
	/**
	 * Getter for board
	 * @return the board
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * Setter for board
	 * @param board the board to set
	 */
	public void setBoard(Board board) {
		this.board = board;
	}

	/**
	 * @return the white
	 */
	public boolean isWhite() {
		return white;
	}

	/**
	 * Getter for enemy player
	 * @return the enemy
	 */
	public Player getEnemy() {
		return enemy;
	}

	/**
	 * Setter for enemy player
	 * @param enemy the enemy to set
	 */
	public void setEnemy(Player enemy) {
		this.enemy = enemy;
	}
	
	/**
	 * Give back Class name and color
	 */
	public String toString() {
		StringBuffer buffer = new StringBuffer(getClass().getSimpleName());
		buffer.append(white ? "white" : "black");
		return buffer.toString();
	}
}
