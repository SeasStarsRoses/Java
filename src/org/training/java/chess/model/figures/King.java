package org.training.java.chess.model.figures;

import java.util.ArrayList;
import java.util.List;

import org.training.java.chess.game.Board;
import org.training.java.chess.game.Move;
import org.training.java.chess.model.coordinate.BoardCoordinate;
import org.training.java.chess.model.coordinate.InvalidCoordinateException;
import org.training.java.chess.model.coordinate.MoveCoordinate;
import org.training.java.chess.model.logging.Logger;

/**
 * Figure Class King
 * 
 * @author Peter Heide, pheide@t-online.de
 * @since 6.10.2012
 */
public class King extends Figure {
	/** Material value for King */
	public static final int material = 10_000_000;

	/**
	 * @param white color of the figure is white or not white (black)
	 * @see Figure#Figure(boolean, Board, BoardCoordinate) super constructor
	 */
	public King(boolean white) {
		super(white);
		try {
			moves.add(new MoveCoordinate(0, -1)); // down
			moves.add(new MoveCoordinate(0, 1)); // up
			moves.add(new MoveCoordinate(1, -1)); // right down
			moves.add(new MoveCoordinate(1, 1)); // right up
			moves.add(new MoveCoordinate(-1, 1)); // left up
			moves.add(new MoveCoordinate(-1, -1)); // left down
			moves.add(new MoveCoordinate(-1, 0, 2)); // left and ooo
			moves.add(new MoveCoordinate(1, 0, 2)); // right and oo
		} catch (InvalidCoordinateException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

	/*
	 * Getter for king moves Method overridden because of rochade
	 * 
	 * @param coordinate where figure stands
	 * @param checkLegalMove determine if it should be checked if a move is
	 * legal or not
	 * 
	 * @return the moves
	 */
	@Override
	public List<Move> getMoves(Board board, BoardCoordinate fromCoord, boolean checkLegalMove) throws InvalidCoordinateException {
		BoardCoordinate enPassant = board.getEnPassant();
		boolean ooWhite =  board.isOoWhite();
		boolean ooBlack = board.isOoBlack();
		boolean oooWhite =  board.isOooWhite();
		boolean oooBlack = board.isOooBlack();

		List<Move> result = new ArrayList<>();
		boolean oo = white ? board.isOoWhite() : board.isOoBlack();
		boolean ooo = white ? board.isOooWhite() : board.isOooBlack();

		for (MoveCoordinate moveCoordinate : moves) {
			for (int offset = 1; offset <= moveCoordinate.getOffset(); offset++) {
				int column = fromCoord.getColumn() + offset * moveCoordinate.getColumn();
				int row = fromCoord.getRow() + offset * moveCoordinate.getRow();
				if (0 <= column && column < Board.size && 0 <= row && row < Board.size) {
					BoardCoordinate toCoord = new BoardCoordinate(column, row);
					Figure beatenFigure = board.getFigure(toCoord);
					Class<? extends Figure> beaten = 
							(beatenFigure == null ? null : beatenFigure.getClass());
					// OO and OOO
					// Just possible when own king is not in chess
					if (offset == 2) {
						if (beatenFigure != null) { break; }
						// OO and OO not allowed when King is in chess
						if (ooFieldUnderAttack(board, fromCoord)) {
							break;
						}
						if (column > fromCoord.getColumn()) { // oo
							if (!oo) {
								break;
							}
							// King must move to an empty field
							BoardCoordinate empty = new BoardCoordinate(
									fromCoord.getColumn() + 2,
									fromCoord.getRow());
							if (board.getFigure(empty) != null) {
								break;
							}
							// Field that King jumps over must not be under
							// attack
							BoardCoordinate ooCoordinate = new BoardCoordinate(
									fromCoord.getColumn() + 1,
									fromCoord.getRow());
							if (ooFieldUnderAttack(board, ooCoordinate)) {
								break;
							}
						} else { // ooo
							if (!ooo) {
								break;
							}
							// King must move to an empty field
							BoardCoordinate empty = new BoardCoordinate(
									fromCoord.getColumn() - 2,
									fromCoord.getRow());
							if (board.getFigure(empty) != null) {
								break;
							}
							// Field that King jumps over must not be under
							// attack
							BoardCoordinate oooCoordinate = new BoardCoordinate(
									fromCoord.getColumn() - 1,
									fromCoord.getRow());
							if (ooFieldUnderAttack(board, oooCoordinate)) {
								break;
							}
						}
					}
					Move move = new Move(getClass(), beaten, null, fromCoord, toCoord,
							enPassant, ooWhite, oooWhite, ooBlack, oooBlack);
					if ((beatenFigure == null || beatenFigure.isWhite() != white)
							&& (!checkLegalMove || isMoveLegal(board, move))) {
						result.add(move);
					}
					if (beatenFigure != null) {
						break;
					}
				}
			}
		}
		return result;
	}

	/*
	 * King must implement Rochade
	 * 
	 * @param board where figure stands
	 * @param move the move where king goes
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be created
	 * 
	 */
	@Override
	public void move(Board board, Move move) throws InvalidCoordinateException {
		BoardCoordinate fromCoord = move.getFromCoord();		
		BoardCoordinate toCoord = move.getToCoord();
		// Move rock when rochade
		if (Math.abs(fromCoord.getColumn() - toCoord.getColumn()) == 2) {
			BoardCoordinate rookCoordinateOld = null;
			BoardCoordinate rockCoordinateNew = null;
			if (isWhite()) {
				if (toCoord.equals(BoardCoordinate.C1)) {
					rookCoordinateOld = BoardCoordinate.A1;
					rockCoordinateNew = BoardCoordinate.D1;
				} else {
					rookCoordinateOld = BoardCoordinate.H1;
					rockCoordinateNew = BoardCoordinate.F1;
				}
			} else {
				if (toCoord.equals(BoardCoordinate.C8)) {
					rookCoordinateOld = BoardCoordinate.A8;
					rockCoordinateNew = BoardCoordinate.D8;
				} else {
					rookCoordinateOld = BoardCoordinate.H8;
					rockCoordinateNew = BoardCoordinate.F8;
				}
			}
			Figure rook = board.getFigure(rookCoordinateOld);
			board.setFigure(rockCoordinateNew, rook);
			if (rook == null) {
				Logger.log(board);
			}
			board.setFigure(rookCoordinateOld, null);
		}
		// invalidate Rochade
		if (white) {
			board.setOoWhite(false);
			board.setOooWhite(false);
		} else {
			board.setOoBlack(false);
			board.setOooBlack(false);			
		}
		// move King
		super.move(board, move);
	}

	/**
	 * Detects if an OO field is under attack so that oo not possible
	 * 
	 * @param board where figure stands
	 * @param coordinate
	 *            of field to see if threatened by an enemy figure
	 * @return true when field under attack and false otherwise
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be created
	 */
	public boolean ooFieldUnderAttack(Board board, BoardCoordinate coordinate) throws InvalidCoordinateException {
		for (int fieldColumn = 0; fieldColumn < Board.size; fieldColumn++) {
			for (int fieldRow = 0; fieldRow < Board.size; fieldRow++) {
				BoardCoordinate fieldCoordinate = new BoardCoordinate(
						fieldColumn, fieldRow);
				Figure enemyFigure = board.getFigure(fieldCoordinate);
				if (enemyFigure != null && enemyFigure.isWhite() != white) {
					// King needs extra logic because of endless recursion loop
					// King checks castle move, enemy king checks castle move,
					// king checks castle move...
					if (enemyFigure instanceof King) {
						if (Math.abs(coordinate.getColumn()
								- fieldCoordinate.getColumn()) <= 1
								&& Math.abs(coordinate.getRow()
										- fieldCoordinate.getRow()) <= 1) {
							return true;
						}
					} else {
						List<Move> moves = enemyFigure.getMoves(board, fieldCoordinate, false);
						for (Move move : moves) {
							if (move.getToCoord().equals(coordinate)) {
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
	 * Take move back and implement enPassant
	 * 
	 * @param board where figure stands
	 * @param move determines that is taken back
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be created
	 */
	@Override
	public void moveBack(Board board, Move move) throws InvalidCoordinateException {
		super.moveBack(board, move);
		
		// Castling
		BoardCoordinate from = move.getFromCoord();
		BoardCoordinate to = move.getToCoord();
		
		if (from.equals(BoardCoordinate.E1)) {
			if (to.equals(BoardCoordinate.C1)) {
				FigureFactory.createFigure(org.training.java.chess.model.figures.Rook.class, white,
						board, BoardCoordinate.A1);
				board.setFigure(BoardCoordinate.D1, null);
			} else if (to.equals(BoardCoordinate.G1)) {
				FigureFactory.createFigure(org.training.java.chess.model.figures.Rook.class, white,
						board, BoardCoordinate.H1);
				board.setFigure(BoardCoordinate.F1, null);
			}
		} else if (from.equals(BoardCoordinate.E8)) {
			if (to.equals(BoardCoordinate.C8)) {
				FigureFactory.createFigure(org.training.java.chess.model.figures.Rook.class, white,
						board, BoardCoordinate.A8);
				board.setFigure(BoardCoordinate.D8, null);
			} else if (to.equals(BoardCoordinate.G8)) {
				FigureFactory.createFigure(org.training.java.chess.model.figures.Rook.class, white,
						board, BoardCoordinate.H8);
				board.setFigure(BoardCoordinate.F8, null);
			}
		}
	}
	
	/**
	 * Getter for material value of figure 
	 * dependent of black (negative) or white (positive)
	 * @return the value
	 */
	@Override
	public int getValue() {
		return white ? King.material : -King.material;
	};	
}
