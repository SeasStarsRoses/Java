package org.training.java.chess.model.ai;

import java.util.Collections;
import java.util.List;

import org.training.java.chess.game.Board;
import org.training.java.chess.game.Move;
import org.training.java.chess.model.coordinate.BoardCoordinate;
import org.training.java.chess.model.coordinate.InvalidCoordinateException;
import org.training.java.chess.model.figures.Figure;
import org.training.java.chess.model.figures.King;
import org.training.java.chess.model.highscore.HighScoreList;
import org.training.java.chess.model.logging.Logger;
import org.training.java.chess.model.player.ComputerPlayer;
import org.training.java.chess.model.player.Player;
/**
* Simple Artificial Intelligence for Computer moves
 * @author Peter Heide, pheide@t-online.de
* @since 05.09.2012
*/
public class IntermediateAI implements AI {
	
	/* Maximal depth of moved we calculate */
	private int maxDepth;

	/** Best move found so far */
	private Move bestDepthMove = null;

	/** Value of best move found so far */
	private int bestDepthValue;

	/** Stop time when calculation computer player has e.g. 5 Seconds */
	private long stopTime;
	
	/** 
	 * This AI calculates two moves, one for each player
	 * @param player The computer player who moves, can be computer or human
	 * @param board The board of the computer player where he makes the moves
     * @param depth determines how many moves we calculate
     * @param miniMax implements MiniMax strategy to cut off unnecessary nodes
     * @return value of the board
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be created
	 * @see org.training.java.chess.model.ai.AI#doTurn(org.training.java.chess.model.player.ComputerPlayer, org.training.java.chess.game.Board)  
	 *    Method of interface we implement
	 */
	private int doTurn(Player player, Board board, int depth, int miniMax) throws InvalidCoordinateException {
		// Stop immediately when calculation time over
		if (System.currentTimeMillis() > stopTime) { return 0; }
		boolean white = player.isWhite();
		Move bestMove = null;
		int bestValue = white ? Integer.MIN_VALUE : Integer.MAX_VALUE;
		List<Move> moves = board.getMoves();
		Collections.sort(moves);
		int size = moves.size();
		if (size == 0) {
			// no move possible
			if (board.isChess(true)) {
				// Let's give bestValue the worst value possible
				// this is the value of the enemy King
				// The later we get set mate the better
				bestValue = white ? - King.material - depth: King.material + depth;
			} else {
				bestValue = 0;
				// game is draw
				// Fight as long as you can
			}
			if (depth == 1) {
				bestDepthValue = bestValue;
			}
			return bestValue;
		} else {
			for (Move move : moves) {
				BoardCoordinate fromCoord = move.getFromCoord();
				Figure fromFigure = board.getFigure(fromCoord);
				fromFigure.move(board, move);
				int value;
				// if you have only one move at highest depth, then make it immediately
				if (depth == maxDepth || (depth == 1 && size == 1)) {
					// Do not calculate more moves when depth == 1
					value = board.value(); 
					// Optimization
					if (white) {
						value += size;											
					} else {
						value -= size;																	
					}
				} else {
					// Rekursion
					value = doTurn(player.getEnemy(), board, depth + 1, bestValue);
				}
				fromFigure.moveBack(board, move);

				// First found move
				// white likes move when value gets higher
				// Write > instead of >= because of MiniMax
				// black likes move when value gets lower
				// Write > instead of >= because of MiniMax
				if ((bestMove == null || (white && value > bestValue) || (!white && value < bestValue))) {
						bestMove = move;
						bestValue = value;
				}
				// Cannot cut of nodes on maximal depth
				// Example when white moves
				//    When white moves on depth = n, then miniMax is the best value black found so far on depth = n+1
				//    Black will go for the lowest value possible, White for the highest
				//    BestValue ist best value white found so far
				//    When the best value white found is better than the miniMax black can go for,
				//      then we can return immediately because black will go for his miniMax move that grants him lower value
				if (depth != 1 && ((white && bestValue > miniMax) || (!white && bestValue < miniMax))) {
					return bestValue;
				}

			}							
		}
//		Logger.log("BestValue: " + bestValue + " BestMove: " + bestMove);
		if (depth == 1) {
			bestDepthMove = bestMove;
			bestDepthValue = bestValue;
		}
		return bestValue;
	}
	
	/** 
	 * This AI calculates the best move it can find in 5 Seonds
	 * @param player The computer player who moves, can be computer or human
	 * @param board The board of the computer player where he makes the moves
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be created
	 * @see org.training.java.chess.model.ai.AI#doTurn(org.training.java.chess.model.player.ComputerPlayer, org.training.java.chess.game.Board)  
	 *    Method of interface we implement
	 */
	@Override
	public void doTurn(ComputerPlayer computerPlayer, Board board) throws InvalidCoordinateException {
		doTurn(computerPlayer, board, 5000L);
	}

	/** 
	 * This AI calculates two moves, one for each player
	 * @param player The computer player who moves, can be computer or human
	 * @param board The board of the computer player where he makes the moves
     * @param milliseconds determines how many seconds we calculate
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be created
	 * @see org.training.java.chess.model.ai.AI#doTurn(org.training.java.chess.model.player.ComputerPlayer, org.training.java.chess.game.Board)  
	 */
	public void doTurn(ComputerPlayer computerPlayer, Board board, long milliseconds) throws InvalidCoordinateException {
		HighScoreList.switchOff();
		Move moveToMake = null;
		long startTime = System.currentTimeMillis();
		stopTime = Long.MAX_VALUE; // Calculate at least one move
		for (int depth = 1; depth<Integer.MAX_VALUE && System.currentTimeMillis() < stopTime; depth++) {
			this.maxDepth = depth;
			if (computerPlayer.isWhite()) {
				doTurn(computerPlayer, board, 1, Integer.MIN_VALUE);
			} else {
				doTurn(computerPlayer, board, 1, Integer.MAX_VALUE);			
			}
			if (bestDepthMove != null && System.currentTimeMillis() < stopTime) 
			{
				Logger.log("depth = " + depth + ", best value = " + bestDepthValue + ", best move = " + 
						bestDepthMove + "\n" + board);
				moveToMake = bestDepthMove;
			}
			stopTime = startTime + milliseconds; // Calculation time	
		}
		HighScoreList.switchOn();
		// 	Make the best found move
		if (bestDepthMove != null) {
			BoardCoordinate fromCoordinate = bestDepthMove.getFromCoord(); 
			Figure figure = board.getFigure(fromCoordinate);
			figure.move(board, moveToMake);
		}
	}

	/** 
	 * This AI calculates with a depth of maxDepth
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be created
	 * @see org.training.java.chess.model.ai.AI#doTurn(org.training.java.chess.model.player.ComputerPlayer, org.training.java.chess.game.Board)  
	 */
	public void doTurn(ComputerPlayer computerPlayer, Board board, int maxDepth) throws InvalidCoordinateException {
		HighScoreList.switchOff();
		stopTime = Long.MAX_VALUE; // Calculation time should never end
		this.maxDepth = maxDepth;
		if (computerPlayer.isWhite()) {
			doTurn(computerPlayer, board, 1, Integer.MIN_VALUE);
		} else {
			doTurn(computerPlayer, board, 1, Integer.MAX_VALUE);			
		}
		HighScoreList.switchOn();
		// 	Make the best found move
		if (bestDepthMove != null) {
			BoardCoordinate from = bestDepthMove.getFromCoord(); 
			Figure figure = board.getFigure(from);
			figure.move(board, bestDepthMove);
		}
	}	
}
