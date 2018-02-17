/**
 * 
 */
package org.training.java.chess.model.player.test;


import org.junit.Assert;
import org.junit.Test;
import org.training.java.chess.game.Board;

/**
 * JUnit Test for Clone
 * @author Peter Heide, pheide@t-online.de
 * @version 1 
 */
public class CloneTest {
	/**
	 * Clones a board 
	 * Make sure that the two beards are equal regarding the equals method
	 * Make sure that the two beards are not identically rregarding the == operator
	 */
	@Test
	public void test() {
		Board board1 = new Board();
		Board board2 = null;
		try {
			board2 = board1.clone();
		} catch (CloneNotSupportedException e) {
			Assert.fail("cloning board error");
			e.printStackTrace();
		}
		Assert.assertNotNull("Cloned board must not be null", board2);
		Assert.assertEquals("board1 an board2 must be equal after cloning", board1, board2);
		Assert.assertNotSame("board1 an board2 must not be identically after cloning", board1, board2);
	}
}
