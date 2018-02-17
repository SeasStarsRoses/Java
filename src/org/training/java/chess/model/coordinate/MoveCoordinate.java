package org.training.java.chess.model.coordinate;

import org.training.java.chess.model.logging.Logger;

/**
 * @author Peter Heide, pheide@t-online.de
 * @since 10.08.2012
 * Version 1
 */
public class MoveCoordinate extends Coordinate implements Cloneable {
	
	/**
	 * offset means how far a figure can move, e.g. King = 1 and Queen = 7
	 */
	private int offset = 1;
	
	/**
	 * MoveCoordinate with column and row between -2 and 2 for figures (Knight can move 2)  
	 * @throws InvalidCoordinateException
	 * @param column between 0 and 7
	 * @param row between 0 and 7
	 */
	public MoveCoordinate(int column, int row) throws InvalidCoordinateException {
		super(column, row);
		if (row<-2 || row>2 || column<-2 || column>2) {
			String error = "MoveCoordinate: Column " + column + " and row " + row + 
					" not between 0 and 7 or ";
			Logger.log(error);
			throw new InvalidCoordinateException(error);
		}
	}

	/**
	 * MoveCoordinate with column and row between -2 and 2 for figures (Knight can move 2)  
	 * @throws InvalidCoordinateException
	 * @param column between 0 and 7
	 * @param row between 0 and 7
	 * @param offset between 1 and 7 to determine how far a figure can move (e.g. Rock 7)
	 */
	public MoveCoordinate(int column, int row, int offset)  throws InvalidCoordinateException {
		super(column, row);
		if (row<-2 || row>2 || column<-2 || column>2 || !(offset==1 || offset==2 || offset==7)) {
			String error = "MoveCoordinate: Column " + column + " and row " + row + 
					" not between 0 and 7 or " + offset + " not  1, 2 or 7";
			Logger.log(error);
			throw new InvalidCoordinateException(error);
		}
		this.offset = offset;
	}
	
	/**
	 * @return the offset
	 */
	public int getOffset() {
		return offset;
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
		return getClass().getSimpleName() + "with column="  + c + 
				", row=" + (row + 1) + " and offset=" + offset;
	}

	/**
	 * Setter for row validates row between 0 and 2
	 * @see org.training.java.chess.model.coordinate.Coordinate#setRow(int)
	 */
	@Override
	public void setRow(int row) {
		if (row<-2 || row>2) {
			Logger.log("MoveCoordinate wrong with row " + row);
			System.exit(-1);
		}
		super.setRow(row);
	}

	/**
	 * Setter for column validates column between 0 and 7
	 * @see org.training.java.chess.model.coordinate.Coordinate#setRow(int)
	 */
	@Override
	public void setColumn(int column) {
		if (column<-2 || column>2) {
			Logger.log("MoveCoordinate wrong with column " + column);
			System.exit(-1);
		}
		super.setColumn(column);
	}
	
	/* Clones the MoveCoordinate
	 * @return the cloned Object that has the same row and column as the original
	 * @see java.lang.Object#clone()
	 */
	@Override
	public MoveCoordinate clone() {
		return (MoveCoordinate) super.clone();
	}
}
