package org.training.java.chess.demos.clone.board;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;

public class CloneTest {

	@Test
	public void testCloneBlack() {
		// Erzeuge Brett
		MyBoard myBoard = new MyBoard();
		// Setze Schwarz am Zug 
		myBoard.setWhite(false);
		MyBoard myBoardClone = null;
		// Erzeuge Clone
		try {
			myBoardClone = (MyBoard) myBoard.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			fail("Clone warf Exception statt Objekt zu erzeugen");
		}
		
		// Stelle sicher dass Clone nicht Original ist
		Assert.assertNotSame(myBoard, myBoardClone);
		// Stelle sicher dass Clone inhaltlich gleich Original ist
		Assert.assertEquals(myBoard, myBoardClone);
	}	
	
	@Test
	public void testCloneWhite() {
		// Erzeuge Brett
		MyBoard myBoard = new MyBoard();
		// Erzeuge und setze Figur
		MyFigure figure = new MyFigure();
		myBoard.setFigure(figure);
		
		MyBoard myBoardClone = null;
		// Erzeuge Clone
		try {
			myBoardClone = (MyBoard) myBoard.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			fail("Clone warf Exception statt Objekt zu erzeugen");
		}
		
		// Stelle sicher dass Clone nicht Original ist
		Assert.assertNotSame(myBoard, myBoardClone);
		// Stelle sicher dass Clone inhaltlich gleich Original ist
		Assert.assertEquals(myBoard, myBoardClone);
		// Hole Figuren und stelle sicher dass nicht identisch
		MyFigure myFigure1 = myBoard.getFigure();
		MyFigure myFigure2 = myBoardClone.getFigure();
		Assert.assertNotSame(myFigure1, myFigure2);
	}

}
