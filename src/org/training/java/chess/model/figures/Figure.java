package org.training.java.chess.model.figures;

import java.util.ArrayList;
import java.util.List;

import org.training.java.chess.game.Board;
import org.training.java.chess.game.Move;
import org.training.java.chess.model.coordinate.BoardCoordinate;
import org.training.java.chess.model.coordinate.InvalidCoordinateException;
import org.training.java.chess.model.coordinate.MoveCoordinate;

/** 
 * Figure Class
 * @author Peter Heide, pheide@t-online.de
 * @version 1 
 */
public abstract class Figure implements Cloneable
{
	
	/** Material value default */
	public static final int material = 0;
	
	/**
	 * color white or not white (black)
	 */
	protected final boolean white;
		
	/** Relative moves a figure can make, e.g. pawn can move 2 rows forward */
	List<MoveCoordinate> moves = new ArrayList<>();
	
	/**
	 * Constructor with figure color
	 * @param white color of the figure is white or not white (black)
	 */
	public Figure(final boolean white)
	{
		this.white = white;
	}

	/**
	 * Getter for color
	 * @return color white or not white (black)
	 */
	public boolean isWhite() {
		return white;
	}
	
	/**
	 * Getter for String with the first letter of the name for displaying purposes
	 * in move list
	 * @return String with the first letter of the name, e.g. 'K' for "Knight"
	 */
	public char getMoveChar()
	{
		return getClass().getSimpleName().charAt(0);
	}

	/**
	 * Getter for String with the first letter of the name for displaying purposes
	 * in board display
	 * @return String with the first letter of the name, e.g. 'K' for "King"
	 */
	public char getBoardChar()
	{
		Character c = getClass().getSimpleName().charAt(0);
		if (!white) {
			c = Character.toLowerCase(c);
		}
		return c;
	}

	/**
	 * Displays Type and Color
	 * @return String with Type and Color
	 */
	@Override
	public String toString()
	{
		String name = getClass().getSimpleName();
		return "Figure " + name + " with color " + white;
	}	
	
	/**
	 * Getter for moves
	 * @param board where figure belongs to
	 * @param coordinate where figure stands
	 * @param checkLegalMove determine if it should be checked if a move is legal or not
	 * @return the moves
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be created
	 */
	public List<Move> getMoves(Board board, BoardCoordinate coordinate, boolean checkLegalMove) throws InvalidCoordinateException {
		List<Move> result = new ArrayList<>();

		for(MoveCoordinate moveCoordinate : moves) {
			for (int offset=1; offset<=moveCoordinate.getOffset(); offset++) {
				int column = coordinate.getColumn() + offset * moveCoordinate.getColumn();
				int row = coordinate.getRow() + offset * moveCoordinate.getRow();
				if (0<=column && column<Board.size && 0<=row && row<Board.size) {
					BoardCoordinate toCoordinate = new BoardCoordinate(column, row);
					Figure beatenFigure = board.getFigure(toCoordinate);
					Class<? extends Figure> beaten = beatenFigure == null ? null : beatenFigure.getClass();
					Move move = new Move(this.getClass(), beaten, null, coordinate, toCoordinate, board.getEnPassant(), 
							board.isOoWhite(), board.isOooWhite(), board.isOoBlack(), board.isOooBlack());
					if (beatenFigure == null || beatenFigure.isWhite() != white) {
						if (!checkLegalMove || isMoveLegal(board, move)) {
							result.add(move);
						}
					}
					if (beatenFigure != null) { break; } // No making more steps
				}
			}
		}
		return result;
	}	
		
	/**
	 * Check if a move is legal
	 * Make the move and check if king is in chess afterwards
	 * @param board where figure belongs to
	 * @param move the move to be checked
	 * @return true if move is legal, false otherwise
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be created
	 */
	public boolean isMoveLegal(Board board, Move move) throws InvalidCoordinateException {
		move(board, move);
		boolean result = !board.isChess(false);
		moveBack(board, move);
		return result;
	}

	/**
	 * Move figure to a new field
	 * @param board where figure belongs to
	 * @param move determines where to go
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be created
	 */
	public void move(Board board, Move move) throws InvalidCoordinateException {
		// Can be normal beaten figure of enPassant pawn
		BoardCoordinate fromCoord = move.getFromCoord(); 
		BoardCoordinate toCoord = move.getToCoord(); 
		Figure fromFigure = board.getFigure(fromCoord);

		board.setFigure(fromCoord, null);
		board.setFigure(toCoord, fromFigure);

		// Destroy castling when figure moves to A1, H1, A8, or H8
		if (toCoord.equals(BoardCoordinate.A1)) { // Destroy OO white
			board.setOooWhite(false);
		} else if (toCoord.equals(BoardCoordinate.H1)) {  // Destroy OOO white
			board.setOoWhite(false);
		} else if (toCoord.equals(BoardCoordinate.A8)) {  // Destroy OO black
			board.setOooBlack(false);
		} else if (toCoord.equals(BoardCoordinate.H8)) { // Destroy OOO black
			board.setOoBlack(false);
		}		
		
		board.setEnPassant(null);
		board.addMove(move);
		board.switchWhiteMoves();
	}

	/**
	 * Take move back
	 * @param board where figure stands
	 * @param move determines that is taken back
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be created
	 */
	public void moveBack(Board board, Move move) throws InvalidCoordinateException {
		BoardCoordinate fromCoordinate = move.getFromCoord();
		BoardCoordinate toCoordinate = move.getToCoord();
		Class<? extends Figure> fromClass = move.getFrom();
		Class<? extends Figure> beatenClass = move.getBeaten();
		Figure beatFigure = null;
		BoardCoordinate enPassant = move.getEnPassant();
		boolean ooWhite = move.isOoWhite();
		boolean oooWhite = move.isOooWhite();
		boolean ooBlack = move.isOoBlack();
		boolean oooBlack = move.isOooBlack();

		// Set Figure before move
		Figure fromFigure = FigureFactory.createFigure(fromClass, white);
		//board.setFigure(fromCoordinate, fromFigure);

		// Set beaten figure back
		if (beatenClass != null) {
			beatFigure = FigureFactory.createFigure(beatenClass, !white);			
		}
		
		boolean enPassantMove = 
				fromClass == org.training.java.chess.model.figures.Pawn.class && enPassant != null && enPassant.equals(toCoordinate);
		
		if (enPassantMove) { 
			// Figure needs to be set not on to coordinate when en passant
			BoardCoordinate newToCoord = new BoardCoordinate(toCoordinate.getColumn(), fromCoordinate.getRow());
			board.setFigure(newToCoord, beatFigure);			
			board.setFigure(toCoordinate, null); // remove pawn that made en passant move			
		} else {
			board.setFigure(toCoordinate, beatFigure);			
		}
		
		board.setFigure(fromCoordinate, fromFigure);
		board.setEnPassant(enPassant);

		// Set back board status before move
		board.setOoWhite(ooWhite);
		board.setOooWhite(oooWhite);
		board.setOoBlack(ooBlack);
		board.setOooBlack(oooBlack);
		
		board.removeLastMove();
		board.switchWhiteMoves();
	}

	/** Clones the Figure
	 * Board will not be cloned, reference to board stays the same
	 * @return cloned Object that has the same row and column as original
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Figure clone(){
		Figure cloneFigure = null;
		try {
			cloneFigure = (Figure) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		List<MoveCoordinate> cloneMoves = new ArrayList<>();
		for (MoveCoordinate moveCoordinate : moves) {
			cloneMoves.add(moveCoordinate.clone());
		}
		cloneFigure.moves = cloneMoves;
		return cloneFigure;
	}
		
	/**
	 * Getter for material value of figure 
	 * dependent of black (negative) or white (positive)
	 * @return the value
	 */
	public int getValue() {
		return white ? material : -material;
	}

	/** 
     * Auto-generated method using colour but not moves
	 * @see java.lang.Object#hashCode()
	 */	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (white ? 1231 : 1237);
		return result;
	}

	/** 
     * Equals method overridden for testing purposes
     * Auto-generated method using color but not moves
	 * @see java.lang.Object#equals(java.lang.Object)
	 */	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Figure other = (Figure) obj;
		if (white != other.white)
			return false;
		return true;
	}	
}
