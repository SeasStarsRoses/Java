package org.training.java.chess.model.figures;

import java.util.ArrayList;
import java.util.List;

import org.training.java.chess.game.Board;
import org.training.java.chess.game.Move;
import org.training.java.chess.model.coordinate.BoardCoordinate;
import org.training.java.chess.model.coordinate.InvalidCoordinateException;
import org.training.java.chess.model.coordinate.MoveCoordinate;

/** 
 * Figure Class Pawn
 * @author Peter Heide, pheide@t-online.de
 * @version 1 
 */
public class Pawn extends Figure 
{
	/** Material value */
	public static final int material = 1000;
	
	/**
	 * @param white color of the figure is white or not white (black)
	 * @see Figure#Figure(boolean) super constructor
	 */
	public Pawn(boolean white) 
	{
		super(white);
		try {
			if (white) {
				moves.add(new MoveCoordinate(1, 1, 1));
				moves.add(new MoveCoordinate(-1, 1, 1));
				moves.add(new MoveCoordinate(0, 1, 2));
			} else {
				moves.add(new MoveCoordinate(-1, -1, 1));
				moves.add(new MoveCoordinate(1, -1, 1));
				moves.add(new MoveCoordinate(0, -1, 2));
			}		
		} catch (InvalidCoordinateException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
		
	/**
	 * Getter for String with the first letter of the name for displaying purposes
	 * @return String with the first letter of the name, e.g. 'N' for "Knight"
	 */
	@Override
	public char getMoveChar() {	return ' ';	}
	
	/* 
     * Set en passant when moving two rows forward
     * @param board where figure stands
	 * @param coordinate where figure stands
     * @param move to make
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be created
	 */
	@Override
	public void move(Board board, Move move) throws InvalidCoordinateException {		
		Class<? extends Figure> exchange = move.getExchange();
		BoardCoordinate fromCoord = move.getFromCoord();
		BoardCoordinate toCoord = move.getToCoord();
		BoardCoordinate enPassant = move.getEnPassantCoord();
		int toColumn = toCoord.getColumn();
		int fromRow = fromCoord.getRow();
		int toRow = toCoord.getRow();
		
		super.move(board, move);
		
		if (enPassant!=null && enPassant.equals(toCoord)) { // en passant move kills pawn
			BoardCoordinate enemyPawn = new BoardCoordinate(toColumn, fromRow);
			board.setFigure(enemyPawn, null);
		}
		
		if (exchange != null) { // Pawn exchange
			FigureFactory.createFigure(exchange, white, board, toCoord);
		} else  if (fromRow - toRow == 2) { // Set en Passant black
			BoardCoordinate enPassantNew = new BoardCoordinate(fromCoord.getColumn(), 5);
			board.setEnPassant(enPassantNew);
		} else if (fromRow - toRow == -2) { // Set en Passant white
			BoardCoordinate enPassantNew = new BoardCoordinate(fromCoord.getColumn(), 2);
			board.setEnPassant(enPassantNew);
		}		
	}
	
	/* (non-Javadoc)
	 * @param board where figure stands
	 * @param fromCoord coordinate where figure stands before move
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be created
	 */
	@Override
	public List<Move> getMoves(Board board, BoardCoordinate fromCoord, boolean checkLegalMove) throws InvalidCoordinateException {
		boolean ooWhite = board.isOoWhite();
		boolean oooWhite = board.isOooWhite();
		boolean ooBlack = board.isOoBlack();
		boolean oooBlack = board.isOooBlack();
		BoardCoordinate enPassant = board.getEnPassant();
		List<Move> result = new ArrayList<>();

		for(MoveCoordinate move : moves) {
			for (int offset=1; offset<=move.getOffset(); offset++) {
				int column = fromCoord.getColumn() + offset * move.getColumn();
				int row = fromCoord.getRow() + offset * move.getRow();
				if (0<=column && column<Board.size && 0<=row && row<Board.size) {
					BoardCoordinate toCoordinate = new BoardCoordinate(column, row);
					boolean isStraight = (column == fromCoord.getColumn());
					// When figure == null and moving straight forward or 
					//      figure == null and moving sidewards and on an en passant field
					// then add figure
					Figure beatFigure = board.getFigure(toCoordinate);
					if (isStraight && beatFigure != null) { break; } // Cannot hit any figure when moving straight
					if (!isStraight && beatFigure==null && toCoordinate.equals(board.getEnPassant())) { // EnPassant
						BoardCoordinate beatCoordinate = null;
						if (white) {
							beatCoordinate = new BoardCoordinate(column, row-1);
						} else {
							beatCoordinate = new BoardCoordinate(column, row+1);						
						}
						beatFigure = board.getFigure(beatCoordinate);
					}
					Class<? extends Figure> beatClass = 
							beatFigure == null ? null : beatFigure.getClass();					
					Class<? extends Figure> figureToClass = null;
					if (toCoordinate.getRow() == 0 || toCoordinate.getRow() == 7) {
						figureToClass = org.training.java.chess.model.figures.Queen.class;
					}
					Move figureMove = new Move(getClass(), beatClass, figureToClass, 
							fromCoord, toCoordinate, enPassant, 
							ooWhite, oooWhite, ooBlack, oooBlack);
					// Do not beat own figure
					// Do beat other figure only when not moving straight forward and other figur color differs from own
					if ((isStraight && beatFigure==null) || (!isStraight && beatFigure!=null && beatFigure.white!=white)) {
						if (!checkLegalMove || isMoveLegal(board, figureMove)) {
							if (row == 0 || row == 7) {
								result.add(new Move(getClass(), beatClass, 
										org.training.java.chess.model.figures.Queen.class, 
										fromCoord, toCoordinate, enPassant, 
										ooWhite, oooWhite, ooBlack, oooBlack));
								result.add(new Move(getClass(), beatClass, 
										org.training.java.chess.model.figures.Rook.class, 
										fromCoord, toCoordinate, enPassant, 
										ooWhite, oooWhite, ooBlack, oooBlack));
								result.add(new Move(getClass(), beatClass, 
										org.training.java.chess.model.figures.Bishop.class, 
										fromCoord, toCoordinate, enPassant, 
										ooWhite, oooWhite, ooBlack, oooBlack));
								result.add(new Move(getClass(), beatClass, 
										org.training.java.chess.model.figures.Knight.class,
										fromCoord, toCoordinate, enPassant, 
										ooWhite, oooWhite, ooBlack, oooBlack));
							} else {				
								result.add(figureMove);
							}
						}
						if (beatFigure != null) { break; }
					}
					// Move two fields straight only when pawn on starting row
					if (isStraight && fromCoord.getRow() != 1 && fromCoord.getRow() != 6) {
						break;
					}
				}
			}
		}
		return result;
	}
}
