/** 
 * Class HighScoreListComparatorScore sorts HighScoreList with Criteria Rounds
 * @author Peter Heide, pheide@t-online.de
 * @version 1 
 * @since 17.02.2018
 */
package org.training.java.chess.model.highscore;

/**
 * @author Peter Heide
 *
 */

import java.util.Comparator;

public class HighScoreComparatorRounds implements Comparator <HighScore> {
	/**
	 * Comparator for HighScore
	 * @param score1 is first HighScore
	 * @param score2 is second HighScore
	 * @return -1 when Round of score1<score2, 0 when score1=score2, 1 when score1>score2
	 */		
	public int compare(HighScore score1, HighScore score2) {		
		return ((Integer)score1.getRounds()).compareTo((Integer)score2.getRounds());
	}
}
