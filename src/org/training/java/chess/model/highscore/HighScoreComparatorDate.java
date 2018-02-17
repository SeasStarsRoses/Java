/** 
 * Class HighScoreListComparatorScore sorts HighScoreList with Criteria Rounds
 * @author Peter Heide, pheide@t-online.de
 * @version 1
 * @since 17.02.2018 
 */
package org.training.java.chess.model.highscore;

/**
 * @author Peter Heide, pheide@t-online.de
 * @version 1 
 * @since 17.02.2018
 */

import java.util.Comparator;

public class HighScoreComparatorDate implements Comparator <HighScore> {
	/**
	 * Comparator for HighScore sorted by Date backwards (Newer Date comes fist)
	 * @param score1 is first HighScore
	 * @param score2 is second HighScore
	 * @return -1 when Date of score1<score2, 0 when score1=score2, 1 when score1>score2
	 */		
	public int compare(HighScore score1, HighScore score2) {	
		return (-(score1.getDate()).compareTo(score2.getDate()));
	}
}
