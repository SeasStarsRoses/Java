package org.training.java.chess.model.player.test;

import org.junit.Test;
import org.training.java.chess.game.Board;
import org.training.java.chess.model.ai.SimpleAI;
import org.training.java.chess.model.ai.StrongAI;
import org.training.java.chess.model.coordinate.InvalidCoordinateException;
import org.training.java.chess.model.logging.Logger;
import org.training.java.chess.model.player.ComputerPlayer;

public class ComputerAgainstComputer {

	@Test
	/**
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be constructed
	 */
	public void test() throws InvalidCoordinateException {
		Board board = Boards.setPositionStart();
		StrongAI whiteAI = new StrongAI();
		SimpleAI blackAI = new SimpleAI();
		ComputerPlayer whitePlayer = Boards.getComputerWhite();
		ComputerPlayer blackPlayer = Boards.getComputerBlack();
		whitePlayer.setBoard(board);
		blackPlayer.setBoard(board);
		whitePlayer.setAI(whiteAI);
		blackPlayer.setAI(blackAI);
		int move = 1;
		while (move < 100) {
			Logger.log("Move " + move + "\n");
			if (board.isGameOver()) { break; }
			whiteAI.doTurn(whitePlayer, board, 5000L); 
			Logger.log(board);
			if (board.isGameOver()) { break; }
			//blackAI.doTurn(whitePlayer, board, 5000L); 
			blackAI.doTurn(whitePlayer, board); 
			Logger.log(board);
			move++;
		}
		StringBuffer buffer = new StringBuffer();
		if (board.isGameOver()) {
			buffer.append("Game over! ");
			if (board.isDraw()) { 
				Logger.log("Draw"); 
			}
			else if (board.isWhiteWinner()) {
				buffer.append("White won");
			} else { 
				buffer.append("Black won"); 
			}
		} else {
			buffer.append("Game could not be ended");
		}
		buffer.append(" in " + move + " moves\n");
		Logger.log(buffer);
	}
}

