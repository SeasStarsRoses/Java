package org.training.java.chess.model.coordinate;

import org.training.java.chess.model.logging.Logger;


/**
 * 
 * @author Peter Heide, pheide@t-online.de
 * @since 10.08.2012
 * Version 1
 */
public class BoardCoordinate extends Coordinate implements Cloneable {
	
	// Constants
	/** Coordinate A1 */
	public static BoardCoordinate A1;
	/** Coordinate B1 */
	public static BoardCoordinate B1;
	/** Coordinate C1 */
	public static BoardCoordinate C1;
	/** Coordinate D1 */
	public static BoardCoordinate D1;
	/** Coordinate E1 */
	public static BoardCoordinate E1;
	/** Coordinate F1 */
	public static BoardCoordinate F1;
	/** Coordinate G1 */
	public static BoardCoordinate G1;
	/** Coordinate H1 */
	public static BoardCoordinate H1;
	/** Coordinate A2 */
	public static BoardCoordinate A2;
	/** Coordinate B2 */
	public static BoardCoordinate B2;
	/** Coordinate C2 */
	public static BoardCoordinate C2;
	/** Coordinate D2 */
	public static BoardCoordinate D2;
	/** Coordinate E2 */
	public static BoardCoordinate E2;
	/** Coordinate F2 */
	public static BoardCoordinate F2;
	/** Coordinate G2 */
	public static BoardCoordinate G2;
	/** Coordinate H2 */
	public static BoardCoordinate H2;
	/** Coordinate A3 */
	public static BoardCoordinate A3;
	/** Coordinate B3 */
	public static BoardCoordinate B3;
	/** Coordinate C3 */
	public static BoardCoordinate C3;
	/** Coordinate D3 */
	public static BoardCoordinate D3;
	/** Coordinate E3 */
	public static BoardCoordinate E3;
	/** Coordinate F3 */
	public static BoardCoordinate F3;
	/** Coordinate G3 */
	public static BoardCoordinate G3;
	/** Coordinate H3 */
	public static BoardCoordinate H3;
	/** Coordinate A4 */
	public static BoardCoordinate A4;
	/** Coordinate B4 */
	public static BoardCoordinate B4;
	/** Coordinate C4 */
	public static BoardCoordinate C4;
	/** Coordinate D4 */
	public static BoardCoordinate D4;
	/** Coordinate E4 */
	public static BoardCoordinate E4;
	/** Coordinate F4 */
	public static BoardCoordinate F4;
	/** Coordinate G4 */
	public static BoardCoordinate G4;
	/** Coordinate H4 */
	public static BoardCoordinate H4;
	/** Coordinate A5 */
	public static BoardCoordinate A5;
	/** Coordinate B5 */
	public static BoardCoordinate B5;
	/** Coordinate C5 */
	public static BoardCoordinate C5;
	/** Coordinate D5 */
	public static BoardCoordinate D5;
	/** Coordinate E5 */
	public static BoardCoordinate E5;
	/** Coordinate F5 */
	public static BoardCoordinate F5;
	/** Coordinate G5 */
	public static BoardCoordinate G5;
	/** Coordinate H5 */
	public static BoardCoordinate H5;
	/** Coordinate A6 */
	public static BoardCoordinate A6;
	/** Coordinate B6 */
	public static BoardCoordinate B6;
	/** Coordinate C6 */
	public static BoardCoordinate C6;
	/** Coordinate D6 */
	public static BoardCoordinate D6;
	/** Coordinate E6 */
	public static BoardCoordinate E6;
	/** Coordinate F6 */
	public static BoardCoordinate F6;
	/** Coordinate G6 */
	public static BoardCoordinate G6;
	/** Coordinate H6 */
	public static BoardCoordinate H6;
	/** Coordinate A7 */
	public static BoardCoordinate A7;
	/** Coordinate B7 */
	public static BoardCoordinate B7;
	/** Coordinate C7 */
	public static BoardCoordinate C7;
	/** Coordinate D7 */
	public static BoardCoordinate D7;
	/** Coordinate E7 */
	public static BoardCoordinate E7;
	/** Coordinate F7 */
	public static BoardCoordinate F7;
	/** Coordinate G7 */
	public static BoardCoordinate G7;
	/** Coordinate H7 */
	public static BoardCoordinate H7;
	/** Coordinate A8 */
	public static BoardCoordinate A8;
	/** Coordinate B8 */
	public static BoardCoordinate B8;
	/** Coordinate C8 */
	public static BoardCoordinate C8;
	/** Coordinate D8 */
	public static BoardCoordinate D8;
	/** Coordinate E8 */
	public static BoardCoordinate E8;
	/** Coordinate F8 */
	public static BoardCoordinate F8;
	/** Coordinate G8 */
	public static BoardCoordinate G8;
	/** Coordinate H8 */
	public static BoardCoordinate H8;
	
	/** Static constructor 
	 * initializes static BoardCoordinates and performs exception handling 
	 */
	static {
		try {
			A1 = new BoardCoordinate("A1");
			B1 = new BoardCoordinate("B1");
			C1 = new BoardCoordinate("C1");
			D1 = new BoardCoordinate("D1");
			E1 = new BoardCoordinate("E1");
			F1 = new BoardCoordinate("F1");
			G1 = new BoardCoordinate("G1");
			H1 = new BoardCoordinate("H1");
			A2 = new BoardCoordinate("A2");
			B2 = new BoardCoordinate("B2");
			C2 = new BoardCoordinate("C2");
			D2 = new BoardCoordinate("D2");
			E2 = new BoardCoordinate("E2");
			F2 = new BoardCoordinate("F2");
			G2 = new BoardCoordinate("G2");
			H2 = new BoardCoordinate("H2");
			A3 = new BoardCoordinate("A3");
			B3 = new BoardCoordinate("B3");
			C3 = new BoardCoordinate("C3");			
			D3 = new BoardCoordinate("D3");
			E3 = new BoardCoordinate("E3");
			F3 = new BoardCoordinate("F3");
			G3 = new BoardCoordinate("G3");
			H3 = new BoardCoordinate("H3");
			A4 = new BoardCoordinate("A4");
			B4 = new BoardCoordinate("B4");
			C4 = new BoardCoordinate("C4");
			D4 = new BoardCoordinate("D4");
			E4 = new BoardCoordinate("E4");
			F4 = new BoardCoordinate("F4");
			G4 = new BoardCoordinate("G4");
			H4 = new BoardCoordinate("H4");
			A5 = new BoardCoordinate("A5");
			B5 = new BoardCoordinate("B5");
			C5 = new BoardCoordinate("C5");
			D5 = new BoardCoordinate("D5");
			E5 = new BoardCoordinate("E5");
			F5 = new BoardCoordinate("F5");
			G5 = new BoardCoordinate("G5");
			H5 = new BoardCoordinate("H5");
			A6 = new BoardCoordinate("A6");
			B6 = new BoardCoordinate("B6");
			C6 = new BoardCoordinate("C6");
			D6 = new BoardCoordinate("D6");
			E6 = new BoardCoordinate("E6");
			F6 = new BoardCoordinate("F6");
			G6 = new BoardCoordinate("G6");
			H6 = new BoardCoordinate("H6");
			A7 = new BoardCoordinate("A7");
			B7 = new BoardCoordinate("B7");
			C7 = new BoardCoordinate("C7");
			D7 = new BoardCoordinate("D7");
			E7 = new BoardCoordinate("E7");
			F7 = new BoardCoordinate("F7");
			G7 = new BoardCoordinate("G7");
			H7 = new BoardCoordinate("H7");
			A8 = new BoardCoordinate("A8");
			B8 = new BoardCoordinate("B8");
			C8 = new BoardCoordinate("C8");
			D8 = new BoardCoordinate("D8");
			E8 = new BoardCoordinate("E8");
			F8 = new BoardCoordinate("F8");
			G8 = new BoardCoordinate("G8");
			H8 = new BoardCoordinate("H8");
		} catch (InvalidCoordinateException e) {			
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	/**
	 * BoardCoordinate with column and row between 0 and 7  
	 * @param column between 0 and 7
	 * @param row between 0 and 7
	 */
	public BoardCoordinate(int column, int row) throws InvalidCoordinateException {
		super(column, row);
		if (row<0 || row>7 || column<0 || column>7) {
			String msg = "Board Coordinate wrong with column = " + column + " and row = " + row; 
			Logger.log(msg);
			throw new InvalidCoordinateException(msg);
		}
	}

	/**
	 * BoardCoordinate with String  
	 * @param coordinate e.g. "A1" gets converted to column=0 and row=0
	 */
	public BoardCoordinate(String coordinate) throws InvalidCoordinateException {
		/*
		 The pattern says a valid coordinate starts with a letter and than a number
		 "[A-H]{1}[1-8]{1}" means:
		[A-H] means a letter between A and H
		{1} means exactly one time
		[1-8] means a number between 1 and 8
		{1} means exactly one time		
		 */
		String pattern = "[A-H]{1}[1-8]{1}";	
		if (!coordinate.matches(pattern))
		{
			String msg = "Invalid Coordinate string " + coordinate; 
			Logger.log(msg);
			throw new InvalidCoordinateException(msg);
		}
		
		column = coordinate.charAt(0) - 'A';
		row = Integer.parseInt(coordinate.substring(1)) - 1;	
		// alternative: coordinate.charAt(1) - '0';
	}
	
	/**
	 * Overrides toString, shows e.g. column=0 and row=0 as "A1"
	 * @see java.lang.Object#toString()
	 * @return String e.g. "A1" for column=0 and row=0
	 */
	@Override
	public String toString() {
		char c = 'A';
		c += column;
		return "" + c + (row + 1);
	}

	/**
	 * Setter for row validates row betwee 0 and 7
	 * @see org.training.java.chess.model.coordinate.Coordinate#setRow(int)
	 */
	@Override
	public void setRow(int row) {
		if (row<0 || row>7) {
			Logger.log("Board Coordinate wrong with row " + row);
			System.exit(-1);
		}
		super.setRow(row);
	}

	/**
	 * Setter for column validates row between 0 and 7
	 * @see org.training.java.chess.model.coordinate.Coordinate#setRow(int)
	 */
	@Override
	public void setColumn(int column) {
		if (column<0 || column>7) {
			Logger.log("Board Coordinate wrong with column " + column);
			System.exit(-1);
		}
		super.setColumn(column);
	}
	/* Clones the BoardCoordinate
	 * @return the cloned Object that has the same row and column as the original
	 * @see java.lang.Object#clone()
	 */
	@Override
	public BoardCoordinate clone() {
		return (BoardCoordinate) super.clone();
	}
	
	/**
	 * Checks if a row or column is on the board
	 * @param columnOrRow column or row
	 * @return true when between 0 and 7, false otherwise
	 */
	public static boolean valid(int columnOrRow) {
		if (0 <= columnOrRow && columnOrRow <= 7) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks if a row and column are on the board
	 * @param column on chess board 
	 * @param row on chess board
	 * @return true when both between 0 and 7, false otherwise
	 */
	public static boolean valid(int column, int row) {
		return valid(column) & valid (row);
	}	
}
