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
* Strong Artificial Intelligence for Computer moves
* 
* Calculates n moves deep
* Board value is material figure value (e.g. white pawn = 1000, black pawn = -1000) plus number of legal moves
* Calculates deeper when move changes material value, e.g. hit figure, hit en passant, or pawn exchange to queen
* 
* When calculating deeper and just change material moves possible, then they are calculated
* when in addition to this normal moves possible, then board value before this move is calculated for them
* 
* Algorithm:
* - When no legal move, then detect if white wins, black wins, or draw
* - Make move and calculate deeper using recursion when 
*      1. More than one legal move at depth 1
*      2. depth <= maxdepth
*      3. Material change (hit, en passant, or pawn exchange)
*   Otherwise calculate board value without making move. Do this only once for same position.
* 
* @author Peter Heide, pheide@t-online.de
* @since 05.09.2012
*/
public class StrongAI implements AI {
	
	/* Maximal depth of moved we calculate */
	private int maxDepth;

	/** Best move found so far */
	private Move allStarMove = null;

	/** Value of best move found so far */
	private int allStarValue = 0;

	/** Stop time when calculation computer player has e.g. 5 Seconds */
	private long stopTime;
	
	/** Killer move is move who leads to minimax cut off nodes. Will be sorted first. */
	private Move killerWhite;
	
	/** Killer move is move who leads to minimax cut off nodes. Will be sorted first. */
	private Move killerBlack;
	
	/** killer move switch */
	private boolean killer = true;

	/** calculate beat move deeper switch */
//	private boolean beat = true;
	
	/** calculate beat move deeper up to a certain number of moves */
	private int beatDepth = 0;	
	
	/** calculate beat move deeper switch */
	private boolean increaseBeatDepth = true;
	
	/** Number of calculated moves */
	private static long movesCount = 0;

	/** 
	 * This AI calculates two moves, one for each player
	 * 
	 * @param player The computer player who moves, can be computer or human
	 * @param board The board of the computer player where he makes the moves
     * @param depth determines how many moves we calculate
     * @param miniMax implements MiniMax strategy to cut off unnecessary nodes
     * @return value of the board
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be created
	 * @see org.training.java.chess.model.ai.AI#doTurn(org.training.java.chess.model.player.ComputerPlayer, org.training.java.chess.game.Board)  
	 *    Method of interface we implement
	 */
	private int doTurn(Player player, Board board, int depth, int miniMax, int sizeBefore) throws InvalidCoordinateException {
		// Stop immediately when calculation time over
		if (maxDepth > 1 && System.currentTimeMillis() > stopTime) { return 0; }
		
		if (depth == 0) { movesCount = 0; }
		//Logger.log("Depth = " + depth);
		boolean white = player.isWhite();
		Move bestMove = null;
		int bestValue = white ? Integer.MIN_VALUE : Integer.MAX_VALUE;
		List<Move> moves = board.getMoves();
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
			return bestValue;
		}
		
		Collections.sort(moves);
		// Sort killer moves in front of all moves
		// Up to depth
		if (killer) {
			Move killer = white ? killerWhite : killerBlack; 
			if (killer!=null && (depth <= maxDepth || killer.isMaterialChange())) {
				int index = moves.indexOf(killer);
				if (index != -1) { // in moves contains killer
					moves.remove(index);
					moves.add(0, killer);				
				}
			} 			
		}
		
		for (Move move : moves) {
		//	Logger.log("Move = " + move);
			if (maxDepth > 1 && System.currentTimeMillis() > stopTime) { return 0; }
			BoardCoordinate fromCoord = move.getFromCoord();
			Figure fromFigure = board.getFigure(fromCoord);
			int value;
			// todo: 5 is temporary, remove again{			
			boolean makeMove = !(depth == 1 && size == 1) && 
					(depth <= maxDepth || (depth <= maxDepth+beatDepth && move.isMaterialChange()));
			// When there is only one legal move at start position, then make it immediately
			// Otherwise calculate up to max depth
			// When max depth is reached, calculate deeper only beat and pawn exchange moves
			if (makeMove) { // Rekursion
				if (depth == maxDepth+beatDepth) {
					increaseBeatDepth = true;
				}
				//Logger.log("makeMove is true for move " + move + "depth = " + depth + "Max=" + maxDepth + move.isBeatMove() + move.isEnPassant() + move.isMaterialChange());
				fromFigure.move(board, move);
				movesCount++;
				value = doTurn(player.getEnemy(), board, depth + 1, bestValue, size);
				fromFigure.moveBack(board, move);
			} else {
				// Do not calculate more moves when depth == 1 and only one legal move
				value = board.value(); 
				// Optimization
				int sizes = size - sizeBefore;
				if (white) {
					value += sizes;
				} else {					
					value -= sizes;																	
				}				
			}
//			if (depth == 2) {
//				Logger.log("depth=2 , makeMove = " + makeMove + ", move=" + move + ", value =  " + value);
//			}
//			if (depth == 1) {
//				Logger.log("Depth 1: Move = " + move +  ", value = " + value + ", bestMove =  " + bestMove + ", bestValue = " + bestValue);
//			}
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
				if (killer) {
					if (white) {
						killerWhite = move;
					} else {
						killerBlack = move;
					}					
				}
				return bestValue;
			}
			if (!makeMove) { break; } // Do not calculate board value more than once for same board
		}							
		if (depth == 1) {
			allStarMove = bestMove;
			allStarValue = bestValue;
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
			beatDepth = 0;
			increaseBeatDepth = true;
			while (increaseBeatDepth && System.currentTimeMillis() < stopTime) {
				increaseBeatDepth = false;
				this.maxDepth = depth;
				long startMoveMillis = System.currentTimeMillis();
				if (computerPlayer.isWhite()) {
					doTurn(computerPlayer, board, 1, Integer.MIN_VALUE, 0);
				} else {
					doTurn(computerPlayer, board, 1, Integer.MAX_VALUE, 0);			
				}
				long endMoveMillis = System.currentTimeMillis();
				long moveMillis = endMoveMillis - startMoveMillis;
				if (allStarMove != null && System.currentTimeMillis() < stopTime) 
				{
					moveToMake = allStarMove;
					Logger.log("Move to make = " + moveToMake + ", Depth = " + depth + 
							", Beat Depth=" + beatDepth + ", Number of moves = " + movesCount +
							", Milliseconds = " + moveMillis);
				}
				stopTime = startTime + milliseconds; // Calculation time	
				beatDepth++;
			}
		}
		HighScoreList.switchOn();
		// 	Make the best found move
		if (allStarMove != null) {
			Logger.log("Made move " + moveToMake + " with value " + allStarValue);
			BoardCoordinate fromCoordinate = allStarMove.getFromCoord(); 
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
			doTurn(computerPlayer, board, 1, Integer.MIN_VALUE, 0);
		} else {
			doTurn(computerPlayer, board, 1, Integer.MAX_VALUE, 0);			
		}
		HighScoreList.switchOn();
		// 	Make the best found move
		if (allStarMove != null) {
			BoardCoordinate fromCoord = allStarMove.getFromCoord(); 
			Figure fromFigure = board.getFigure(fromCoord);
			fromFigure.move(board, allStarMove);
		}
	}

	/* Getter for killer move switch */
	public boolean isKiller() {
		return killer;
	}

	/* Setter for killer move switch */
	public void setKiller(boolean killer) {
		this.killer = killer;
	}
}
