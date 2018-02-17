package org.training.java.chess.demos.clone.board;

public class MyBoard implements Cloneable {
	// Weiss am Zug
	private boolean white = true;
	// Figure
	private MyFigure figure = new MyFigure();
	
	
	public MyFigure getFigure() {
		return figure;
	}

	public void setFigure(MyFigure figure) {
		this.figure = figure;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((figure == null) ? 0 : figure.hashCode());
		result = prime * result + (white ? 1231 : 1237);
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MyBoard other = (MyBoard) obj;
		if (figure == null) {
			if (other.figure != null)
				return false;
		} else if (!figure.equals(other.figure))
			return false;
		if (white != other.white)
			return false;
		return true;
	}

	public boolean isWhite() {
		return white;
	}

	public void setWhite(boolean white) {
		this.white = white;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		// Erzeugt Objekt der Klasse MyBoard
		MyBoard myBoardClone = (MyBoard) super.clone();
		myBoardClone.setWhite(white);
		MyFigure figureClone = (MyFigure) figure.clone();
		myBoardClone.setFigure(figureClone);
		return myBoardClone;
	}
	
}
