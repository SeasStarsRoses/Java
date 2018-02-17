package org.training.java.chess.model.highscore;

import java.util.Date;

/**
 * High Score data
 * 
 * @author Peter Heide, pheide@t-online.de
 * @version 1
 * @since 12.108.2013
 */
public class HighScore {
	/** Game Date */
	private Date date;
	/** Winner name */
	private String winner;
	/** Looser name */
	private String looser;
	/** Rounds of game */
	private int rounds;	
	
	/**
	 * Constructor with all values 
	 * @param date Date where game played
	 * @param winner name
	 * @param looser name
	 * @param rounds played
	 */
	public HighScore(Date date, String winner, String looser, int rounds)
	{
		this.date = date;
		this.winner = winner;
		this.looser = looser;
		this.rounds = rounds;
	}
	
	/**
	 * Get the date of this historical match  
	 * @return the date object itself
	 */	
	public Date getDate()
	{
		return date;
	}
	
	/**
	 * Get the name of the looser 
	 * @return Looser, which is hopefully not me
	 */	
	public String getLoosersName()
	{
		return looser;		
	}
		
	/**
	 * Get the number of rounds of the game 
	 * @return Number of rounds
	 */	
	public int getRounds()
	{
		return rounds;
	}
	
	/**
	 * Get the name of the winner
	 * @return winner
	 */	
	public String getWinnersName()
	{
		return winner;
	}

	@Override
	/** Display HighScore to show in console or Dialog */
	public String toString() {
		return "HighScore [date=" + date + ", winner=" + winner + ", looser="
				+ looser + ", rounds=" + rounds + "]";
	}
}