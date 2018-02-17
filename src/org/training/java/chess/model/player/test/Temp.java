package org.training.java.chess.model.player.test;

import org.training.java.chess.model.figures.Figure;
import org.training.java.chess.model.figures.King;
import org.training.java.chess.model.figures.Knight;
import org.training.java.chess.model.figures.Rook;

public class Temp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		King king1 = new King(true);
		System.out.println(king1.getValue());
		King king2 = new King(false);
		System.out.println(king2.getValue());
		Figure figure = new Rook(true);
		System.out.println(figure.getValue());
		figure = new Rook(false);
		System.out.println(figure.getValue());
		figure = new Knight(true);
		System.out.println(figure.getValue());
		figure = new Knight(false);
		System.out.println(figure.getValue());
}

}
