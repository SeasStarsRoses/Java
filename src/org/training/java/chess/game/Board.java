package org.training.java.chess.game;

import java.util.ArrayList;
import java.util.List;

import org.training.java.chess.model.coordinate.BoardCoordinate;
import org.training.java.chess.model.coordinate.InvalidCoordinateException;
import org.training.java.chess.model.figures.Figure;
import org.training.java.chess.model.figures.FigureFactory;
import org.training.java.chess.model.figures.King;
import org.training.java.chess.model.player.Player;

/**
 * Chess Board Manages the two dimensional array of fields
 * 
 * @author Peter Heide, pheide@t-online.de
 * @version 1
 * @since 12.12.2012
 */
public class Board implements Cloneable {
	
	/** Move number starts with 1 and increases by one when white an black moved */
	//private int moveNumber = 1;	
	
	/** size is board size */
	public static final int size = 8;
	
	/** 8x8 chess field */	
	private Figure [][] figures = new Figure[size][size];
	
	/** Board knows its players */
	private Player player1;
	/** Board knows its players */
	private Player player2;
	
	/** true when white moves and false otherwise */
	private boolean whiteMoves = true;

	/** enPassent coordinate is coordinate that another pawn can hit directly 
	 *  after own pawn moves to forward 
	 */
	private BoardCoordinate enPassant = null;
	
	/** Move list so that it is possible to take one back */
	private List<Move> moves = new ArrayList<>();

	/**
	 * Short castling white
	 */
	private boolean ooWhite = true;

	/**
	 * Short castling black
	 */
	private boolean ooBlack	= true;
	 
	/**
	 * Long castling white
	 */
	private boolean oooWhite = true;	
	
	/**
	 * Long castling black
	 */
	private boolean oooBlack = true;	
	
	/**
	 * Constructor for files
	 */
	public Board () {
		// This constructs a new array with 64 references to fields and they are all null
		figures = new Figure[size][size];
		for (int column=0; column<size; column++) {
			for (int row=0; row<size; row++) {
				figures[column][row] = null;
			}
		}
	}
	
	/**
	 * @return the enPassant
	 */
	public BoardCoordinate getEnPassant() {
		return enPassant;
	}

	/**
	 * @param enPassant the enPassant to set
	 */
	public void setEnPassant(BoardCoordinate enPassant) {
		this.enPassant = enPassant;
	}

	/**
	 * Initializes Board when new Game
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be created
	 */
	public void initialize() throws InvalidCoordinateException {
		FigureFactory.createFigure(org.training.java.chess.model.figures.Rook.class, true, this, BoardCoordinate.A1);
		FigureFactory.createFigure(org.training.java.chess.model.figures.Knight.class, true, this, BoardCoordinate.B1);
		FigureFactory.createFigure(org.training.java.chess.model.figures.Bishop.class, true, this, BoardCoordinate.C1);
		FigureFactory.createFigure(org.training.java.chess.model.figures.Queen.class, true, this, BoardCoordinate.D1);
		FigureFactory.createFigure(org.training.java.chess.model.figures.King.class, true, this, BoardCoordinate.E1);
		FigureFactory.createFigure(org.training.java.chess.model.figures.Bishop.class, true, this, BoardCoordinate.F1);
		FigureFactory.createFigure(org.training.java.chess.model.figures.Knight.class, true, this, BoardCoordinate.G1);
		FigureFactory.createFigure(org.training.java.chess.model.figures.Rook.class, true, this, BoardCoordinate.H1);
		
		for (int column = 0; column < size; column++) {
			BoardCoordinate coordinate = new BoardCoordinate(column, 1);
			FigureFactory.createFigure(org.training.java.chess.model.figures.Pawn.class, true, this, coordinate);
		}	
		
		for (int column = 0; column < size; column++) {
			BoardCoordinate coordinate = new BoardCoordinate(column, 6);
			FigureFactory.createFigure(org.training.java.chess.model.figures.Pawn.class, false, this, coordinate);
		}		

		FigureFactory.createFigure(org.training.java.chess.model.figures.Rook.class, false, this, BoardCoordinate.A8);
		FigureFactory.createFigure(org.training.java.chess.model.figures.Knight.class, false, this, BoardCoordinate.B8);
		FigureFactory.createFigure(org.training.java.chess.model.figures.Bishop.class, false, this, BoardCoordinate.C8);
		FigureFactory.createFigure(org.training.java.chess.model.figures.Queen.class, false, this, BoardCoordinate.D8);
		FigureFactory.createFigure(org.training.java.chess.model.figures.King.class, false, this, BoardCoordinate.E8);
		FigureFactory.createFigure(org.training.java.chess.model.figures.Bishop.class, false, this, BoardCoordinate.F8);
		FigureFactory.createFigure(org.training.java.chess.model.figures.Knight.class, false, this, BoardCoordinate.G8);
		FigureFactory.createFigure(org.training.java.chess.model.figures.Rook.class, false, this, BoardCoordinate.H8);
	}
	
	/* 
	 * Console output for board
	 * @see java.lang.Object#toString()
	 *     
	 *      A   B   C   D   E   F   G   H
	 *    +---+---+---+---+---+---+---+---+
	 *  8 | r | k | b | q | k | b | k | r | 8
	 *    +---+---+---+---+---+---+---+---+
	 *  7 | p | p | p | p | p | p | p | p | 7
	 *    +---+---+---+---+---+---+---+---+
	 *  6 |   |   |   |   |   |   |   |   | 6
	 *    +---+---+---+---+---+---+---+---+
	 *  5 |   |   |   |   |   |   |   |   | 5
	 *    +---+---+---+---+---+---+---+---+
	 *  4 |   |   |   |   |   |   |   |   | 4
	 *    +---+---+---+---+---+---+---+---+
	 *  3 |   |   |   |   |   |   |   |   | 3
	 *    +---+---+---+---+---+---+---+---+
	 *  2 | P | P | P | P | P | P | P | P | 2
	 *    +---+---+---+---+---+---+---+---+
	 *  1 | R | K | B | Q | K | B | K | R | 1
	 *    +---+---+---+---+---+---+---+---+
	 *      A   B   C   D   E   F   G   H
	 *     
	 */
	@Override
	public String toString() {
		final String letters = "    A   B   C   D   E   F   G   H\r\n";
		final String rows   = "  +---+---+---+---+---+---+---+---+\r\n";
		StringBuffer result = new StringBuffer();
		result.append("Board: ");
		if (whiteMoves) {
			result.append("White");
		} else {
			result.append("Black");
		}
		result.append(" moves, en passant = " + enPassant + "\n");
		result.append("King white OO = " + ooWhite + ", OOO = " + oooWhite + "\r\n");
		result.append("King black OO = " + ooBlack + ", OOO = " + oooBlack + "\r\n");
		result.append("Moves:\r\n" + moves + "\r\n");
		
		result.append(letters).append(rows);
		for (int row = Board.size - 1; row >= 0; row--) {
			result.append(row + 1 + " |");
			for (int column = 0; column < Board.size; column++) {
				BoardCoordinate coordinate = null;
				try {
					coordinate = new BoardCoordinate(column, row);
				} catch (InvalidCoordinateException e) {
					e.printStackTrace();
					System.exit(-1);
				}
				Figure figure = getFigure(coordinate);
				if (figure == null) {
					result.append("   ");
				} else {
					result.append(" ").append(figure.getBoardChar()).append(" ");
				}
				if (column < Board.size - 1) {
					result.append("|");
				}
			}
			result.append("| ").append(row + 1).append("\r\n").append(rows);
		}
		result.append(letters);
		return result.toString();
	}	
	
	/**
	 * Detects of chess or not
	 * @param checkKingWhoCanMove true to see if King who can move next is in chess (passive chess)
	 *                            false to see if King who cannot move next is in chess (passive chess)
	 *        if whiteMoves == true and checkKingWhoCanMove == true, then white king is checked
	 *        if whiteMoves == true and checkKingWhoCanMove == false, then black king is checked
	 *        if whiteMoves == false and checkKingWhoCanMove == true, then black king is checked
	 *        if whiteMoves == false and checkKingWhoCanMove == false, then white king is checked
	 * @return true when King is in chess, false otherwise
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be created
	 */
	public boolean isChess(boolean checkKingWhoCanMove) throws InvalidCoordinateException {
		for (int column=0; column<size; column++) {
			for (int row=0; row<size; row++) {
				BoardCoordinate fromCood = new BoardCoordinate(column, row);
				Figure enemyFigure = figures[column][row];
				if (enemyFigure == null) { continue; }
				// Is checked King white?
				boolean kingWhite = (whiteMoves == checkKingWhoCanMove);
				// EnemyFigure must have other color than King to check
				if(enemyFigure.isWhite() != kingWhite) {
					List<Move> moves = enemyFigure.getMoves(this, fromCood, false);
					if (!moves.isEmpty()) {
						for (Move move : moves) {
							BoardCoordinate toCoord = move.getToCoord();
							Figure figure = getFigure(toCoord);
							if (figure != null && figure.isWhite() == kingWhite
								&& figure instanceof King) {
								return true;
							}
						}						
					}
				}
			}
		}			
		return false;
	}	
	
	/**
	 * Getter if white or black moves
	 * @return whiteMoves
	 */
	public boolean isWhiteMoves() {
		return whiteMoves;
	}

	/**
	 * Switches moves from white to black or other way
	 */
	public void switchWhiteMoves() {
		whiteMoves = !whiteMoves;
	}

	/**
	 * Getter for Player 1
	 * @return the player1
	 */
	public Player getPlayer1() {
		return player1;
	}

	/**
	 * Setter for Player 1
	 * @param player1 the player1 to set
	 */
	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	/**
	 * Setter for Player 2
	 * @return the player2
	 */
	public Player getPlayer2() {
		return player2;
	}

	/**
	 * Setter for Player 2
	 * @param player2 the player2 to set
	 */
	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}
		
	/**
	 * Gets Figure at Coordinate
	 * @param coordinate
	 * @return Figure of the board coordinate
	 */
	public Figure getFigure(BoardCoordinate coordinate){
		assert (coordinate != null);
		int column = coordinate.getColumn();
		int row = coordinate.getRow();
		return figures[column][row];
	}	

	/**
	 * Helper method for view: 
	 *    Coordinates where figure stand that can move should be red
	 *    When player selects a figure it should be yellow
	 *    All fields where the figure can move should be green
	 * @return List of all coordinates where a figure stands that can move
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be created
	 */
	public List<BoardCoordinate> moveCoordinates() throws InvalidCoordinateException {
		List<BoardCoordinate> result = new ArrayList<BoardCoordinate>(); 
		for (int column=0; column<size; column++) {
			for (int row=0; row<size; row++) {
				BoardCoordinate fromCoord = new BoardCoordinate(column, row);
				Figure figure = getFigure(fromCoord);
				if (figure != null && figure.isWhite() == whiteMoves) {
					List<Move> moves = figure.getMoves(this, fromCoord, true);
					if (!moves.isEmpty()) {
						result.add(fromCoord);
					}
				}				
			}
		}
		return result;
	}	
	
	/**
	 * A Game is draw when when actual player has no legal move and is not in chess
	 * @return if game is draw
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be created
	 */
	public boolean isDraw() throws InvalidCoordinateException {
		return isGameOver() && !isChess(true);
	}

	/**
	 * Check if white player won the game
	 * @return true if white won or false otherwisen
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be created
	 */
	public boolean isWhiteWinner() throws InvalidCoordinateException {
		return isGameOver() && !isDraw() && !whiteMoves;
	}

	/**
	 * Check if black player won the game
	 * @return true if white won or false otherwisen
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be created
	 */
	public boolean isBlackWinner() throws InvalidCoordinateException {
		return isGameOver() && !isDraw() && whiteMoves;
	}

	/** Clone the board
	 * @return cloned Board
	 */
	@SuppressWarnings("unchecked")
	public Board clone() throws CloneNotSupportedException {
		Board board = (Board) super.clone();
		board.figures = new Figure[size][size];
		// Cast necessary from List<Move> to ArrayList<Move> cause clone
		ArrayList<Move> movesArrayList = (ArrayList<Move>) moves;
		board.moves = (ArrayList<Move>) movesArrayList.clone();
		if (enPassant != null) {
			board.enPassant = enPassant.clone();
		}
		for (int column=0; column<size; column++) {
			for (int row=0; row<size; row++) {
				board.figures[column][row] = figures[column][row];
			}
		}
		return board;
	}

	/**
	 * Getter for move number
	 * @return the moveNumber
	 */
	public int getMoveNumber() {
		return moves.size() / 2 + 1;
	}

	/** 
     * Equals method overridden for testing purposes
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null || !(object instanceof Board)) {
			return false;
		}
		
		Board board = (Board) object;
		if (enPassant==null) {
			if (board.enPassant!=null) {
				return false;
			} 
		} else {
			if (!enPassant.equals(board.enPassant)) {
				return false;
			}
		}

		if (whiteMoves != board.whiteMoves ||
			ooWhite != board.ooWhite || oooWhite !=board.oooWhite ||
			ooBlack != board.ooBlack || oooBlack !=board.oooBlack) {
			return false;
		}
		
		for (int column=0; column<size; column++) {
			for (int row=0; row<size; row++) {
				Figure figure = figures[column][row];
				Figure boardFigure = board.figures[column][row];
				if (figure == null) {
					if (boardFigure != null) {
						return false;
					}
				} else {
					if (!figure.equals(boardFigure)) {
						return false;
					}
				}
			}
		}

		return true;
	}

	/**
	 * @return the game is over when no legal moves exist
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be created
	 */
	public boolean isGameOver() throws InvalidCoordinateException {
		List<Move> moves = getMoves();
		return moves==null || moves.isEmpty();
	}
	
	/**
	 * Material value of board, e.g. King is 1000000, Pawn is 10
	 * @return material value
	 * @throws InvalidCoordinateException 
	 */
	public int value() throws InvalidCoordinateException {
		int value = 0;
		for (int column=0; column<size; column++) {
			for (int row=0; row<size; row++) {
				BoardCoordinate coordinate = new BoardCoordinate(column, row);
				Figure figure = getFigure(coordinate);
				if (figure != null) {
					value += figure.getValue();
				}
			}
		}
		return value;
	}
	
	/**
	 * Returns all legal moves 
	 * @return List of all legal moves
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be created
	 */
	public List<Move> getMoves() throws InvalidCoordinateException {
		List<Move> boardMoves = new ArrayList<Move>(); 
		for (int column=0; column<size; column++) {
			for (int row=0; row<size; row++) {
				BoardCoordinate fromCoord = new BoardCoordinate(column, row);
				Figure fromFigure = getFigure(fromCoord);
				if (fromFigure != null && fromFigure.isWhite() == whiteMoves) {
					List<Move> figureMoves = fromFigure.getMoves(this, fromCoord, true);
					if (figureMoves!=null && !figureMoves.isEmpty()) {
						for (Move move : figureMoves) {
							boardMoves.add(move);							
						}
					}
				}				
			}
		}
		return boardMoves;
	}	
	
	/**
	 * Adds a move to the move list
	 * @param move the move to be added
	 */
	public void addMove(Move move) {
		moves.add(move);
	}
	
	/**
	 * Gets the last move
	 * @return the last move
	 */
	public Move getLastMove() {
		if (moves.isEmpty()) {
			return null;
		} else {
			int index = moves.size() - 1;
			return moves.get(index);			
		}
	}	

	/**
	 * Removes the last move
	 * @return the last move
	 */
	public Move removeLastMove() {
		if (moves.isEmpty()) {
			return null;
		} else {
			int index = moves.size() - 1;
			Move move = moves.get(index);
			moves.remove(index);
			return move;			
		}
	}
	
	/**
	 * Getter for white short castling (Help method)
	 * @return if white can castle short
	 */
	public boolean isOoWhite() {
		return ooWhite;
	}
	
	/**
	 * Getter for white long castling (Help method)
	 * @return if white can castle long
	 */
	public boolean isOooWhite() {
		return oooWhite;
	}

	/**
	 * Getter for black short castling (Help method)
	 * @return if black can castle short
	 */
	public boolean isOoBlack() {
		return ooBlack;
	}
	
	/**
	 * Getter for black long castling
	 * @return if black can castle long
	 */
	public boolean isOooBlack() {
		return oooBlack;
	}
	
	public void setWhiteMoves(boolean whiteMoves) {
		this.whiteMoves = whiteMoves;
	}

	/**
	 * Set the figure into the board at coordinate
	 * @param to coordinate where the figure stands
	 * @param figure the figure to set on the board, might be null
	 */
	public void setFigure(BoardCoordinate to, Figure figure) {
		int column = to.getColumn();
		int row = to.getRow();
		figures[column][row] = figure;
		
	}

	public void setOoWhite(boolean ooWhite) {
		this.ooWhite = ooWhite;
	}

	public void setOoBlack(boolean ooBlack) {
		this.ooBlack = ooBlack;
	}

	public void setOooWhite(boolean oooWhite) {
		this.oooWhite = oooWhite;
	}

	public void setOooBlack(boolean oooBlack) {
		this.oooBlack = oooBlack;
	}
}
