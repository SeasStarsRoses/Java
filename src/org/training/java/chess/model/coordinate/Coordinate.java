package org.training.java.chess.model.coordinate;

/** 
 * Figure Class
 * @author Peter Heide, pheide@t-online.de
 * @since 10.08.2012
 * @version 1 
 */
public class Coordinate implements Cloneable {

	/**
	 * Zero-based column
	 */
	protected int column;

	/**
	 * Zero-based row
	 */
	protected int row;

	/**
	 * Standard Constructor sets column=0 nd row=0
	 */
	public Coordinate() {}	
	
	/**
	 * Constructor takes zero-based column and row
	 * @param column can be any positive of negative value
	 * @param row can be any positive of negative value 
	 */
	public Coordinate(int column, int row) {
		super();
		this.column = column;
		this.row = row;
	}
	
	/**
	 * Getter for row
	 * @return the row
	 */
	public int getRow() {
		return row;
	}
	
	/**
	 * Setter for row
	 * @param row the row to set
	 */
	public void setRow(int row) {
		this.row = row;
	}
	
	/**
	 * Getter for Column 
	 * @return the column
	 */
	public int getColumn() {
		return column;
	}
	
	/**
	 * Setter for column
	 * @param column the column to set
	 */
	public void setColumn(int column) {
		this.column = column;
	}

	/**
	 * equals overridden to compare coordinates
	 * @return true if colums and rows are identical
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || ! (obj instanceof Coordinate)) {
			return false;
		}
		Coordinate coordinate = (Coordinate) obj;
		return column == coordinate.column && row == coordinate.row;
	}	
	
	
	
	/* Clones the Coordinate
	 * @throws CloneNotSupportedException, but this should not happen
	 * @return the cloned Object that has the same row and column as the original
	 * @see java.lang.Object#clone()
	 */
	@Override
	protected Coordinate clone() {
		Coordinate coordinate = null;
		try {
			coordinate = (Coordinate) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		return coordinate;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + column;
		result = prime * result + row;
		return result;
	}
}
