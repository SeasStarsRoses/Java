package org.training.java.chess.demos.clone.board;

public class MyFigure implements Cloneable {
	private boolean white = true;

	public boolean isWhite() {
		return white;
	}

	public void setWhite(boolean white) {
		this.white = white;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		MyFigure figure = (MyFigure) super.clone();
		//MyFigure figure = new MyFigure();
		figure.setWhite(white);
		return figure;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		MyFigure other = (MyFigure) obj;
		if (white != other.white)
			return false;
		return true;
	}
	
	
}
