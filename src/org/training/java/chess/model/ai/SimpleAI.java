package org.training.java.chess.model.ai;

import java.util.List;

import org.training.java.chess.game.Board;
import org.training.java.chess.game.Move;
import org.training.java.chess.model.coordinate.BoardCoordinate;
import org.training.java.chess.model.coordinate.InvalidCoordinateException;
import org.training.java.chess.model.figures.Figure;
import org.training.java.chess.model.figures.FigureFactory;
import org.training.java.chess.model.figures.Pawn;
import org.training.java.chess.model.highscore.HighScoreList;
import org.training.java.chess.model.logging.Logger;
import org.training.java.chess.model.player.ComputerPlayer;
/**
* Simple Artificial Intelligence for Computer moves
 * @author Peter Heide, pheide@t-online.de
* @since 05.09.2012
*/
public class SimpleAI implements AI {

	/** 
	 * Make a move an beat the figure with the highest value if you can
	 * This AI calculates one move
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be created
	 * @see org.training.java.chess.model.ai.AI#doTurn(org.training.java.chess.model.player.ComputerPlayer, org.training.java.chess.game.Board)  
	 *    Method of interface we implement
	 */
	@Override
	public void doTurn(ComputerPlayer computerPlayer, Board board) throws InvalidCoordinateException {
		HighScoreList.switchOff();
		Move bestMove = null;
		int bestValue;
		boolean white = computerPlayer.isWhite();
		if (white) {
			bestValue = Integer.MIN_VALUE;
		} else {
			bestValue = Integer.MAX_VALUE;
		}
		List<Move> moves = board.getMoves();
		if (moves != null) {
			for (Move move : moves) {
				BoardCoordinate fromCoord = move.getFromCoord();
				Figure figure = board.getFigure(fromCoord);
				BoardCoordinate toCoord = move.getToCoord();
				int toRow = toCoord.getRow();
				figure.move(board, move);
				if (figure instanceof Pawn && (toRow == 0 || toRow == 7)) {
					FigureFactory.createFigure(org.training.java.chess.model.figures.Queen.class, figure.isWhite(), board, toCoord);
				}
				int value = board.value();
				figure.moveBack(board, move);
				if (bestMove == null) {
					bestMove = move;
					bestValue = value;
				} else {
					if (white) {
						if (value > bestValue) {
							bestMove = move;
							bestValue = value;
						}
					} else {
						if (value < bestValue) {
							bestMove = move;
							bestValue = value;
						}
					}
				}				
			}			
		}
		if (bestMove == null) {
			Logger.log("Error: No Move possible");
			System.exit(-1);
		}

		HighScoreList.switchOn();
		BoardCoordinate from = bestMove.getFromCoord(); 
		Figure fromFigure = board.getFigure(from);
		BoardCoordinate to = bestMove.getToCoord();
		fromFigure.move(board, bestMove);
		int toRow = to.getRow();
		// Pawn Exchange
		if (fromFigure instanceof Pawn && (toRow == 0 || toRow == 7)) {
			Figure queen = FigureFactory.createFigure(org.training.java.chess.model.figures.Queen.class, fromFigure.isWhite(), board, to);
			board.setFigure(to, queen);
		}
	}
}
