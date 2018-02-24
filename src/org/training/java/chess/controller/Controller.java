/**
 * 
 */
package org.training.java.chess.controller;

import java.util.List;

import org.eclipse.swt.widgets.Shell;
import org.training.java.chess.game.Board;
import org.training.java.chess.game.Move;
import org.training.java.chess.model.ai.AI;
import org.training.java.chess.model.ai.IntermediateAI;
import org.training.java.chess.model.ai.SimpleAI;
import org.training.java.chess.model.ai.Strength;
import org.training.java.chess.model.ai.StrongAI;
import org.training.java.chess.model.coordinate.BoardCoordinate;
import org.training.java.chess.model.coordinate.InvalidCoordinateException;
import org.training.java.chess.model.figures.Figure;
import org.training.java.chess.model.figures.Pawn;
import org.training.java.chess.model.logging.Logger;
import org.training.java.chess.model.player.ComputerPlayer;
import org.training.java.chess.model.player.HumanPlayer;
import org.training.java.chess.model.player.Player;
import org.training.java.chess.view.View;
import org.training.java.chess.view.dialogs.PawnExchangeFigureDialog;
import org.training.java.chess.view.dialogs.WelcomeDialog;

/**
 * Controller in Model View Controller paradigm
 * 
 * @author Peter Heide, pheide@t-online.de
 * @since 6.10.2012
 */
public class Controller {

	/**
	 * View in Model View controller paradigm;
	 */
	private View view;

	/**
	 * Board is model in model view controller paradigm
	 */
	private Board board;

	/** First click on board */
	private BoardCoordinate firstSelection = null;

	/**
	 * When New Game button is pressed, indicates New Game shall be played
	 */
	private boolean newGame = false;

	/** White Player is HumanPlayer or ComputerPlayer */
	Player whitePlayer = null;

	/** Black Player is HumanPlayer or ComputerPlayer */
	Player blackPlayer = null;

	/**
	 * Controller when coordinate is klicked
	 * 
	 * @param clicked first or second click
	 * @throws InvalidCoordinateException when coordinate cannot be created
	 */
	public void coordinateClicked(BoardCoordinate clicked) throws InvalidCoordinateException {
		// First click
		if (firstSelection == null) {
			Figure figure = board.getFigure(clicked);
			if (figure != null) {
				if (figure.isWhite() == board.isWhiteMoves()) {
					List<Move> moves = figure.getMoves(board, clicked, true);
					if (moves.isEmpty()) {
						return;
					}
					firstSelection = clicked;
				}
			}
			// Second click
		} else {
			Figure fromFigure = board.getFigure(firstSelection);
			BoardCoordinate secondSelection = clicked;
			Figure beatFigure = board.getFigure(secondSelection);
			Class<? extends Figure> figureBeatenClass = (beatFigure == null ? null
					: beatFigure.getClass());
			boolean isStraight = (firstSelection.getColumn() == clicked.getColumn());
			List<Move> moves = fromFigure.getMoves(board, firstSelection, true);
			boolean found = false;
			if (moves != null && !moves.isEmpty()) {
				for (Move move : moves) {
					if (move != null) {
						if (secondSelection.equals(move.getToCoord())) {
							found = true;
						}
					}
				}
			}
			if (!found) { return; }
			int row = secondSelection.getRow();
			if (fromFigure instanceof Pawn && (row == 0 || row == 7)) {
				Shell shell = view.getShell();
				PawnExchangeFigureDialog pawnExchangeFigureDialog = new PawnExchangeFigureDialog(
						shell, view);
				pawnExchangeFigureDialog.display(fromFigure.isWhite(),
						firstSelection, secondSelection, figureBeatenClass,
						this);
			} else {
				// EnPassant
				if (fromFigure instanceof Pawn && !isStraight && beatFigure==null) {
					BoardCoordinate beatCoordinate = null;
					if (fromFigure.isWhite()) {
						beatCoordinate = new BoardCoordinate(secondSelection.getColumn(), row-1);
					} else {
						beatCoordinate = new BoardCoordinate(secondSelection.getColumn(), row+1);						
					}
					beatFigure = board.getFigure(beatCoordinate);
				}
				Class<? extends Figure> beat = beatFigure == null ? null : beatFigure.getClass(); 
				Move move = new Move(fromFigure.getClass(), beat, null,
						firstSelection, secondSelection,
						board.getEnPassant(), board.isOoWhite(),
						board.isOooWhite(), board.isOoBlack(),
						board.isOooBlack());
				fromFigure.move(board, move);
			}
			if (!board.isWhiteMoves()) {
				Logger.log(board.getMoveNumber() + ". ");
			}
			Logger.log("" + fromFigure.getMoveChar() + firstSelection
					+ clicked);
			firstSelection = null;
			Logger.log(board);
			computerMove();
		}
		view.draw();
	}

	/**
	 * After human move there might be a computer move
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be constructed
	 */
	public void computerMove() throws InvalidCoordinateException {
		if (!board.isGameOver()) {
			if (board.isWhiteMoves() && whitePlayer instanceof ComputerPlayer) {
				ComputerPlayer whiteComputerPlayer = (ComputerPlayer) whitePlayer;
				whiteComputerPlayer.doTurn();
				view.draw();
				Logger.log(board);
			} else if (!board.isWhiteMoves()
					&& blackPlayer instanceof ComputerPlayer) {
				ComputerPlayer blackComputerPlayer = (ComputerPlayer) blackPlayer;
				blackComputerPlayer.doTurn();
				view.draw();
				Logger.log(board);
			}
		}
	}

	/**
	 * @param view
	 *            the view to set
	 */
	public void setView(View view) {
		this.view = view;
	}

	/**
	 * Setter for board
	 * 
	 * @param board
	 */
	public void setBoard(Board board) {
		this.board = board;
	}

	/**
	 * Getter for new Game
	 * 
	 * @return if new Game or program end
	 */
	public boolean isNewGame() {
		return newGame;
	}

	/**
	 * Setter for new Game event
	 * 
	 * @param newGame
	 *            the newGame to set
	 */
	public void setNewGame(boolean newGame) {
		this.newGame = newGame;
	}

	/**
	 * Getter for first clicked field
	 * 
	 * @return the firstSelection
	 */
	public BoardCoordinate getFirstSelection() {
		return firstSelection;
	}

	/**
	 * Method called by View as soon as it is clear which figure gets exchanged
	 * 
	 * @param exchange
	 *            is the new figure type: King, Queen, Bishop, Rock or Knight
	 * @param fromCoordinate
	 *            First Click (From Field)
	 * @param toCoordinate
	 *            is the coordinate where the figure stands
	 * @param figureToClass
	 *            Figure on to field
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be created
	 */
	public void pawnExchangeCallback(Class<? extends Figure> exchange,
			final BoardCoordinate fromCoordinate, BoardCoordinate toCoordinate) throws InvalidCoordinateException {
		Figure fromFigure = board.getFigure(fromCoordinate);
		Figure beatFigure = board.getFigure(toCoordinate);
		Class<?extends Figure> beatClass = (beatFigure == null ? null : beatFigure.getClass());
		Move move = new Move(fromFigure.getClass(), beatClass, 
				exchange, fromCoordinate, toCoordinate,
				board.getEnPassant(), board.isOoWhite(), board.isOooWhite(),
				board.isOoBlack(), board.isOooBlack());
		fromFigure.move(board, move);

		Logger.log("New figure: " + exchange.getSimpleName());
		view.draw();
		Logger.log(board);
		Logger.log("Pawn Exchange Figure Dialog over");
		computerMove();
	}

	/**
	 * Welcome dialog callback when dialog is ready
	 * 
	 * @param whiteHuman
	 *            if white is human or computer
	 * @param blackHuman
	 *            if white is human or computer
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be constructed
	 */
	public void welcomeDialogCallback(boolean whiteHuman, boolean blackHuman) throws InvalidCoordinateException {
		if (whiteHuman) {
			whitePlayer = new HumanPlayer(true);
		} else {
			whitePlayer = new ComputerPlayer(true);
			Strength strength = WelcomeDialog.getWhiteComputerStrength();
			AI ai = new SimpleAI();
			if (strength == Strength.INTERMEDIATE) {
				ai = new IntermediateAI();
			} else if (strength == Strength.STRONG) {
				ai = new StrongAI();
			}
			ComputerPlayer computer = (ComputerPlayer) whitePlayer;
			computer.setAI(ai);
		}
		if (blackHuman) {
			blackPlayer = new HumanPlayer(false);
		} else {
			blackPlayer = new ComputerPlayer(false);
			Strength strength = WelcomeDialog.getBlackComputerStrength();
			AI ai = new SimpleAI();
			if (strength == Strength.INTERMEDIATE) {
				ai = new IntermediateAI();
			} else if (strength == Strength.STRONG) {
				ai = new StrongAI();
			}
			ComputerPlayer computer = (ComputerPlayer) blackPlayer;
			computer.setAI(ai);
		}

		// Player know each other
		whitePlayer.setEnemy(blackPlayer);
		blackPlayer.setEnemy(whitePlayer);

		whitePlayer.setBoard(board);
		blackPlayer.setBoard(board);

		board.setPlayer1(whitePlayer);
		board.setPlayer2(blackPlayer);

		// First move
		while (!board.isGameOver()) {
			if (whitePlayer instanceof ComputerPlayer) {
				ComputerPlayer whiteComputerPlayer = (ComputerPlayer) whitePlayer;
				whiteComputerPlayer.doTurn();
				view.draw();
				view.getDisplay().update(); // Necessary when computer against
											// computer to redraw
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				break;
			}
			// Second move
			if (blackPlayer instanceof ComputerPlayer) {
				ComputerPlayer blackComputerPlayer = (ComputerPlayer) blackPlayer;
				blackComputerPlayer.doTurn();
				view.draw();
				view.getDisplay().update(); // Necessary when computer against
											// computer to redraw
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				break;
			}
		}
	}

	/**
	 * Take back the last move
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be created
	 */
	public void takeBackMove() throws InvalidCoordinateException {
		Move move = board.getLastMove();
		BoardCoordinate toCoord = move.getToCoord();
		Figure toFigure = board.getFigure(toCoord);
		toFigure.moveBack(board, move);
		Logger.log(board);
	}
}
