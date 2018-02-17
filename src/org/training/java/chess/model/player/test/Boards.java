package org.training.java.chess.model.player.test;


import org.junit.Assert;
import org.training.java.chess.game.Board;
import org.training.java.chess.model.ai.StrongAI;
import org.training.java.chess.model.coordinate.BoardCoordinate;
import org.training.java.chess.model.coordinate.InvalidCoordinateException;
import org.training.java.chess.model.figures.Figure;
import org.training.java.chess.model.figures.FigureFactory;
import org.training.java.chess.model.figures.King;
import org.training.java.chess.model.figures.Pawn;
import org.training.java.chess.model.player.ComputerPlayer;

/**
 * JUnit Test for AI
 * 
 * @author Peter Heide, pheide@t-online.de
 * @version 1
 * @since 17.02.2018
 */
public class Boards {

	static private Board board;
	static private ComputerPlayer computerWhite;
	static private ComputerPlayer computerBlack;

	/**
	 * Initializes board an player
	 */
	public static void initialize() {
		board = new Board();

		computerWhite = new ComputerPlayer(true);
		computerBlack = new ComputerPlayer(false);

		computerWhite.setAI(new StrongAI());
		computerWhite.setEnemy(computerBlack);
		computerWhite.setBoard(board);

		computerBlack.setAI(new StrongAI());
		computerBlack.setEnemy(computerWhite);
		computerBlack.setBoard(board);
	}

	/**
	 * Set start position
	 * 
	 * @return created Board
	 * 
	 * White moves, OO possible for black and white, no e.p.
	 * 
	 *     A   B   C   D   E   F   G   H
	 *   +---+---+---+---+---+---+---+---+
	 * 8 | r | n | b | q | k | b | n | r | 8
	 *   +---+---+---+---+---+---+---+---+
	 * 7 | p | p | p | p | p | p | p | p | 7
	 *   +---+---+---+---+---+---+---+---+
	 * 6 |   |   |   |   |   |   |   |   | 6
	 *   +---+---+---+---+---+---+---+---+
	 * 5 |   |   |   |   |   |   |   |   | 5
	 *   +---+---+---+---+---+---+---+---+
	 * 4 |   |   |   |   |   |   |   |   | 4
	 *   +---+---+---+---+---+---+---+---+
	 * 3 |   |   |   |   |   |   |   |   | 3
	 *   +---+---+---+---+---+---+---+---+
	 * 2 | P | P | P | P | P | P | P | P | 2
	 *   +---+---+---+---+---+---+---+---+
	 * 1 | R | N | B | Q | K | B | N | R | 1
	 *   +---+---+---+---+---+---+---+---+
	 *    A   B   C   D   E   F   G   H
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be constructed
	 * 
	 */
	public static Board setPositionStart() throws InvalidCoordinateException {
		initialize(); // empty board
		board.initialize(); // start position

		return board;
	}

	/**
	 * Set position mate in one move
	 * 
	 * @return created Board
	 * 
	 * Solution: RH1xH8 mate
	 * 
	 * White moves, OO impossible for black and white, no e.p.
	 * 
	 *     A   B   C   D   E   F   G   H
	 *   +---+---+---+---+---+---+---+---+
	 * 8 | k |   |   |   |   |   |   |   | 8
	 *   +---+---+---+---+---+---+---+---+
	 * 7 |   |   |   |   |   |   |   |   | 7
	 *   +---+---+---+---+---+---+---+---+
	 * 6 | K |   |   |   |   |   |   |   | 6
	 *   +---+---+---+---+---+---+---+---+
	 * 5 |   |   |   |   |   |   |   |   | 5
	 *   +---+---+---+---+---+---+---+---+
	 * 4 |   |   |   |   |   |   |   |   | 4
	 *   +---+---+---+---+---+---+---+---+
	 * 3 |   |   |   |   |   |   |   |   | 3
	 *   +---+---+---+---+---+---+---+---+
	 * 2 |   |   |   |   |   |   |   |   | 2
	 *   +---+---+---+---+---+---+---+---+
	 * 1 | n |   |   |   |   |   |   | R | 1
	 *   +---+---+---+---+---+---+---+---+
	 *     A   B   C   D   E   F   G   H
	 */
	public static Board setPositionMateInOneMoveBefore() {
		initialize();

		FigureFactory.createFigure(org.training.java.chess.model.figures.Knight.class, false, 
				board, BoardCoordinate.A1);
		FigureFactory.createFigure(org.training.java.chess.model.figures.King.class, true, 
				board, BoardCoordinate.A6);
		FigureFactory.createFigure(org.training.java.chess.model.figures.Rook.class, true, 
				board, BoardCoordinate.H1);
		FigureFactory.createFigure(org.training.java.chess.model.figures.King.class, false, 
				board, BoardCoordinate.A8);
		
		board.setOoWhite(false);
		board.setOooWhite(false);
		board.setOoBlack(false);
		board.setOooBlack(false);

		return board;
	}

	/**
	 * Set position mate in three moves before move
	 * 
	 * @return created Board
	 * 
	 * Solution: 1. RH6H6 chess KB6C7 2. RG5G7 chess KC7D8 3. RH6H8 mate
	 * 
	 * White moves, ep null, OO impossible for black and white, no e.p.
	 * 
	 *     A   B   C   D   E   F   G   H
	 *   +---+---+---+---+---+---+---+---+
	 * 8 |   |   |   |   |   |   |   |   | 8
	 *   +---+---+---+---+---+---+---+---+
	 * 7 |   |   |   |   |   |   |   |   | 7
	 *   +---+---+---+---+---+---+---+---+
	 * 6 |   | k |   |   |   |   |   |   | 6
	 *   +---+---+---+---+---+---+---+---+
	 * 5 |   |   |   |   |   |   | R |   | 5
	 *   +---+---+---+---+---+---+---+---+
	 * 4 |   |   |   |   |   |   |   | R | 4
	 *   +---+---+---+---+---+---+---+---+
	 * 3 |   |   |   |   |   |   |   |   | 3
	 *   +---+---+---+---+---+---+---+---+
	 * 2 |   |   |   |   |   |   |   |   | 2
	 *   +---+---+---+---+---+---+---+---+
	 * 1 |   |   |   |   | K |   |   |   | 1
	 *   +---+---+---+---+---+---+---+---+
	 *     A   B   C   D   E   F   G   H
	 */
	public static Board setPositionMateInThreeMovesBefore() {
		initialize();

		FigureFactory.createFigure(org.training.java.chess.model.figures.King.class, true, board, 
				BoardCoordinate.E1);
		FigureFactory.createFigure(org.training.java.chess.model.figures.Rook.class, true, board,
				BoardCoordinate.H4);
		FigureFactory.createFigure(org.training.java.chess.model.figures.Rook.class, true, board,
				BoardCoordinate.G5);
		FigureFactory.createFigure(org.training.java.chess.model.figures.King.class, false, board, 
				BoardCoordinate.B6);
		
		board.setOoWhite(false);
		board.setOooWhite(false);
		board.setOoBlack(false);
		board.setOooBlack(false);

		return board;
	}

	/**
	 * Set position mate in three moves after move
	 * 
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be constructed
	 * @return created Board
	 * 
	 * Solution: 1. RH6H6 chess KB6C7 2. RG5G7 chess KC7D8 3. RH6H8 mate
	 * 
	 * Black moves, ep null, OO impossible for black and white, no e.p.
	 * 
	 *     A   B   C   D   E   F   G   H
	 *   +---+---+---+---+---+---+---+---+
	 * 8 |   |   |   |   |   |   |   |   | 8
	 *   +---+---+---+---+---+---+---+---+
	 * 7 |   |   |   |   |   |   |   |   | 7
	 *   +---+---+---+---+---+---+---+---+
	 * 6 |   | k |   |   |   |   |   | R | 6
	 *   +---+---+---+---+---+---+---+---+
	 * 5 |   |   |   |   |   |   | R |   | 5
	 *   +---+---+---+---+---+---+---+---+
	 * 4 |   |   |   |   |   |   |   |   | 4
	 *   +---+---+---+---+---+---+---+---+
	 * 3 |   |   |   |   |   |   |   |   | 3
	 *   +---+---+---+---+---+---+---+---+
	 * 2 |   |   |   |   |   |   |   |   | 2
	 *   +---+---+---+---+---+---+---+---+
	 * 1 |   |   |   |   | K |   |   |   | 1
	 *   +---+---+---+---+---+---+---+---+
	 *     A   B   C   D   E   F   G   H
	 */
	public static Board setPositionMateInThreeMovesAfter() throws InvalidCoordinateException {
		setPositionMateInThreeMovesBefore();
		moveFigure(BoardCoordinate.H4, BoardCoordinate.H6);
		return board;
	}

	/**
	 * Set position knight has only one legal move before move
	 * 
	 * @return created Board
	 * 
	 * Solution: KH1G3 is only legal move
	 * 
	 * White moves, OO impossible for black and white, no e.p.
	 * 
	 *     A   B   C   D   E   F   G   H
	 *   +---+---+---+---+---+---+---+---+
	 * 8 |   |   |   |   |   |   |   |   | 8
	 *   +---+---+---+---+---+---+---+---+
	 * 7 |   |   |   |   |   |   |   |   | 7
	 *   +---+---+---+---+---+---+---+---+
	 * 6 |   |   |   |   |   |   |   |   | 6
	 *   +---+---+---+---+---+---+---+---+
	 * 5 |   |   |   |   |   |   |   |   | 5
	 *   +---+---+---+---+---+---+---+---+
	 * 4 |   |   |   |   |   |   |   |   | 4
	 *   +---+---+---+---+---+---+---+---+
	 * 3 |   |   |   |   |   | k |   | q | 3
	 *   +---+---+---+---+---+---+---+---+
	 * 2 |   |   |   |   |   | P |   |   | 2
	 *   +---+---+---+---+---+---+---+---+
	 * 1 |   |   |   |   |   |   | K | N | 1
	 *   +---+---+---+---+---+---+---+---+
	 *     A   B   C   D   E   F   G   H
	 */
	public static Board setPositionKnightMoveBefore() {
		initialize();

		FigureFactory.createFigure(org.training.java.chess.model.figures.King.class, true, 
				board, BoardCoordinate.G1);
		FigureFactory.createFigure(org.training.java.chess.model.figures.Knight.class, true, board,
				BoardCoordinate.H1);
		FigureFactory.createFigure(org.training.java.chess.model.figures.Pawn.class, true, board,
				BoardCoordinate.F2);
		FigureFactory.createFigure(org.training.java.chess.model.figures.King.class, false, board,
				BoardCoordinate.F3);
		FigureFactory.createFigure(org.training.java.chess.model.figures.Queen.class, false, board,
				BoardCoordinate.H3);

		board.setOoWhite(false);
		board.setOooWhite(false);
		board.setOoBlack(false);
		board.setOooBlack(false);
		
		return board;
	}

	/**
	 * only one legal move before move
	 * 
	 * Solution: RA7G7 is only legal move
	 * 
	 * Black moves, OO and OOO impossible for black and white, no e.p.
	 * 
	 *     A   B   C   D   E   F   G   H
	 *   +---+---+---+---+---+---+---+---+
	 * 8 |   |   |   |   |   |   |   | k | 8
	 *   +---+---+---+---+---+---+---+---+
	 * 7 | Q |   |   |   |   |   | r |   | 7
	 *   +---+---+---+---+---+---+---+---+
	 * 6 |   |   |   |   |   | K |   |   | 6
	 *   +---+---+---+---+---+---+---+---+
	 * 5 |   |   |   |   |   |   |   |   | 5
	 *   +---+---+---+---+---+---+---+---+
	 * 4 |   |   |   |   |   |   |   |   | 4
	 *   +---+---+---+---+---+---+---+---+
	 * 3 |   |   |   |   |   |   |   |   | 3
	 *   +---+---+---+---+---+---+---+---+
	 * 2 |   |   |   |   |   |   |   |   | 2
	 *   +---+---+---+---+---+---+---+---+
	 * 1 |   |   |   |   |   |   |   |   | 1
	 *   +---+---+---+---+---+---+---+---+
	 *     A   B   C   D   E   F   G   H
	 */
	public static Board setPositionQueenMoveBefore() {
		initialize();

		FigureFactory.createFigure(org.training.java.chess.model.figures.King.class, true, board, 
				BoardCoordinate.F6);
		FigureFactory.createFigure(org.training.java.chess.model.figures.Queen.class, true, board,
				BoardCoordinate.A7);
		FigureFactory.createFigure(org.training.java.chess.model.figures.Rook.class, false, board,
				BoardCoordinate.G7);
		FigureFactory.createFigure(org.training.java.chess.model.figures.King.class, false, board, 
				BoardCoordinate.H8);

		board.setOoWhite(false);
		board.setOooWhite(false);
		board.setOoBlack(false);
		board.setOooBlack(false);
		
		return board;
	}

	/**
	 * Only one legal move before move
	 * 
	 * Solution: QA7G7 is best move
	 * 
	 * Black moves, OO and OOO impossible for black and white, no e.p.
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be constructed
	 * @return Board initialized with desired position
	 * 
	 *     A   B   C   D   E   F   G   H
	 *   +---+---+---+---+---+---+---+---+
	 * 8 |   |   |   |   |   |   |   | k | 8
	 *   +---+---+---+---+---+---+---+---+
	 * 7 |   |   |   |   |   |   | Q |   | 7
	 *   +---+---+---+---+---+---+---+---+
	 * 6 |   |   |   |   |   | K |   |   | 6
	 *   +---+---+---+---+---+---+---+---+
	 * 5 |   |   |   |   |   |   |   |   | 5
	 *   +---+---+---+---+---+---+---+---+
	 * 4 |   |   |   |   |   |   |   |   | 4
	 *   +---+---+---+---+---+---+---+---+
	 * 3 |   |   |   |   |   |   |   |   | 3
	 *   +---+---+---+---+---+---+---+---+
	 * 2 |   |   |   |   |   |   |   |   | 2
	 *   +---+---+---+---+---+---+---+---+
	 * 1 |   |   |   |   |   |   |   |   | 1
	 *   +---+---+---+---+---+---+---+---+
	 *     A   B   C   D   E   F   G   H
	 */
	public static Board setPositionQueenMoveAfter() throws InvalidCoordinateException {
		setPositionQueenMoveBefore();

		moveFigure(BoardCoordinate.A7, BoardCoordinate.G7);

		return board;
	}

	/**
	 * Set position black rook has only one legal move before move
	 * 
	 * Solution: RA7G7 is only legal move
	 * 
	 * Black moves, OO and OOO impossible for black and white, no e.p.
	 * 
	 *     A   B   C   D   E   F   G   H
	 *   +---+---+---+---+---+---+---+---+
	 * 8 |   |   |   |   |   |   |   | k | 8
	 *   +---+---+---+---+---+---+---+---+
	 * 7 | r |   |   |   |   |   | Q |   | 7
	 *   +---+---+---+---+---+---+---+---+
	 * 6 |   |   |   |   |   | K |   |   | 6
	 *   +---+---+---+---+---+---+---+---+
	 * 5 |   |   |   |   |   |   |   |   | 5
	 *   +---+---+---+---+---+---+---+---+
	 * 4 |   |   |   |   |   |   |   |   | 4
	 *   +---+---+---+---+---+---+---+---+
	 * 3 |   |   |   |   |   |   |   |   | 3
	 *   +---+---+---+---+---+---+---+---+
	 * 2 |   |   |   |   |   |   |   |   | 2
	 *   +---+---+---+---+---+---+---+---+
	 * 1 |   |   |   |   |   |   |   |   | 1
	 *   +---+---+---+---+---+---+---+---+
	 *     A   B   C   D   E   F   G   H
	 */
	public static Board setPositionRookMoveBefore() {
		initialize();

		FigureFactory.createFigure(org.training.java.chess.model.figures.King.class, true, board, 
				BoardCoordinate.F6);
		FigureFactory.createFigure(org.training.java.chess.model.figures.Rook.class, false, board,
				BoardCoordinate.A7);
		FigureFactory.createFigure(org.training.java.chess.model.figures.Queen.class, true, board,
				BoardCoordinate.G7);
		FigureFactory.createFigure(org.training.java.chess.model.figures.King.class, false, board, 
				BoardCoordinate.H8);

		board.switchWhiteMoves();

		board.setOoWhite(false);
		board.setOooWhite(false);
		board.setOoBlack(false);
		board.setOooBlack(false);

		return board;
	}

	/**
	 * Set position black rook has only one legal move after move
	 * 
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be constructed
	 * @return Board initialized with desired position
	 * 
	 *  Solution: A7G7 is only legal move
	 * 
	 *  Board: Black moves, en passant = null 
	 *  King White OO = false, OOO = false 
	 *  King Black OO = false, OOO = false
	 * 
	 *     A   B   C   D   E   F   G   H
	 *   +---+---+---+---+---+---+---+---+
	 * 8 |   |   |   |   |   |   |   | k | 8
	 *   +---+---+---+---+---+---+---+---+
	 * 7 |   |   |   |   |   |   | r |   | 7
	 *   +---+---+---+---+---+---+---+---+
	 * 6 |   |   |   |   |   | K |   |   | 6
	 *   +---+---+---+---+---+---+---+---+
	 * 5 |   |   |   |   |   |   |   |   | 5
	 *   +---+---+---+---+---+---+---+---+
	 * 4 |   |   |   |   |   |   |   |   | 4
	 *   +---+---+---+---+---+---+---+---+
	 * 3 |   |   |   |   |   |   |   |   | 3
	 *   +---+---+---+---+---+---+---+---+
	 * 2 |   |   |   |   |   |   |   |   | 2
	 *   +---+---+---+---+---+---+---+---+
	 * 1 |   |   |   |   |   |   |   |   | 1
	 *   +---+---+---+---+---+---+---+---+
	 *     A   B   C   D   E   F   G   H
	 */
	public static Board setPositionRookMoveAfter() throws InvalidCoordinateException {
		setPositionRookMoveBefore();

		moveFigure(BoardCoordinate.A7, BoardCoordinate.G7);

		return board;
	}

	/**
	 * Set position bishop has only one legal move after move
	 * 
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be constructed
	 * @return Board initialized with desired position
	 * 
	 *         Solution: BH1G2 is only legal move
	 * 
	 *         Black moves, OO impossible for black and white, no e.p.
	 * 
	 *     A   B   C   D   E   F   G   H
	 *     
	 *   +---+---+---+---+---+---+---+---+
	 * 8 |   |   |   |   |   |   |   |   | 8
	 *   +---+---+---+---+---+---+---+---+
	 * 7 |   |   |   |   |   |   |   |   | 7
	 *   +---+---+---+---+---+---+---+---+
	 * 6 |   |   |   |   |   |   |   |   | 6
	 *   +---+---+---+---+---+---+---+---+
	 * 5 |   |   |   |   |   |   |   |   | 5
	 *   +---+---+---+---+---+---+---+---+
	 * 4 |   |   |   |   |   |   |   |   | 4
	 *   +---+---+---+---+---+---+---+---+
	 * 3 |   |   |   |   |   | k |   |   | 3
	 *   +---+---+---+---+---+---+---+---+
	 * 2 |   |   |   |   |   |   | B |   | 2
	 *   +---+---+---+---+---+---+---+---+
	 * 1 |   |   |   |   |   |   | K |   | 1
	 *   +---+---+---+---+---+---+---+---+
	 *     A   B   C   D   E   F   G   H
	 */
	public static Board setPositionBishopMoveAfter() throws InvalidCoordinateException {
		setPositionBishopMoveBefore();

		moveFigure(BoardCoordinate.H1, BoardCoordinate.G2);

		return board;
	}

	/**
	 * Set position bishop has only one legal move after move
	 * 
	 * @return created Board
	 * 
	 *         Solution: BH1G2 was only legal move
	 * 
	 *         White moves, OO impossible for black and white, no e.p.
	 * 
	 *     A   B   C   D   E   F   G   H
	 *   +---+---+---+---+---+---+---+---+
	 * 8 |   |   |   |   |   |   |   |   | 8
	 *   +---+---+---+---+---+---+---+---+
	 * 7 |   |   |   |   |   |   |   |   | 7
	 *   +---+---+---+---+---+---+---+---+
	 * 6 |   |   |   |   |   |   |   |   | 6
	 *   +---+---+---+---+---+---+---+---+
	 * 5 |   |   |   |   |   |   |   |   | 5
	 *   +---+---+---+---+---+---+---+---+
	 * 4 |   |   |   |   |   |   |   |   | 4
	 *   +---+---+---+---+---+---+---+---+
	 * 3 |   |   |   |   |   | k |   |   | 3
	 *   +---+---+---+---+---+---+---+---+
	 * 2 |   |   |   |   |   |   | q |   | 2
	 *   +---+---+---+---+---+---+---+---+
	 * 1 |   |   |   |   |   |   | K | B | 1
	 *   +---+---+---+---+---+---+---+---+
	 *     A   B   C   D   E   F   G   H
	 */
	public static Board setPositionBishopMoveBefore() {
		initialize();

		FigureFactory.createFigure(org.training.java.chess.model.figures.King.class, true, board, 
				BoardCoordinate.G1);
		FigureFactory.createFigure(org.training.java.chess.model.figures.Bishop.class, true, board,
				BoardCoordinate.H1);
		FigureFactory.createFigure(org.training.java.chess.model.figures.Queen.class, false, board,
				BoardCoordinate.G2);
		FigureFactory.createFigure(org.training.java.chess.model.figures.King.class, false, board,
				BoardCoordinate.F3);

		board.setOoWhite(false);
		board.setOooWhite(false);
		board.setOoBlack(false);
		board.setOooBlack(false);

		return board;
	}

	/**
	 * Set position knight has only one legal move after move
	 * 
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be constructed
	 * @return created Board
	 * 
	 * Solution: KH1G3 is only legal move
	 * 
	 * Board: White moves, e.p. null, OO and OO impossible for white and black
	 * 
	 *     A   B   C   D   E   F   G   H
	 *   +---+---+---+---+---+---+---+---+
	 * 8 |   |   |   |   |   |   |   |   | 8
	 *   +---+---+---+---+---+---+---+---+
	 * 7 |   |   |   |   |   |   |   |   | 7
	 *   +---+---+---+---+---+---+---+---+
	 * 6 |   |   |   |   |   |   |   |   | 6
	 *   +---+---+---+---+---+---+---+---+
	 * 5 |   |   |   |   |   |   |   |   | 5
	 *   +---+---+---+---+---+---+---+---+
	 * 4 |   |   |   |   |   |   |   |   | 4
	 *   +---+---+---+---+---+---+---+---+
	 * 3 |   |   |   |   |   | k | N | q | 3
	 *   +---+---+---+---+---+---+---+---+
	 * 2 |   |   |   |   |   | P |   |   | 2
	 *   +---+---+---+---+---+---+---+---+
	 * 1 |   |   |   |   |   |   | K |   | 1
	 *   +---+---+---+---+---+---+---+---+
	 *     A   B   C   D   E   F   G   H
	 */
	public static Board setPositionKnightMoveAfter() throws InvalidCoordinateException {
		setPositionKnightMoveBefore();

		moveFigure(BoardCoordinate.H1, BoardCoordinate.G3, null);

		return board;
	}

	/**
	 * Set position mate in one move after move
	 * 
	 * Solution: RH1xH8 mate
	 * 
	 * Board: Black should move but cannot because chess mate ep = null, 
	 * OO and OOO impossible for white and black
	 * 
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be constructed
	 * @return Board initialized with desired position
	 * 
	 *     A   B   C   D   E   F   G   H
	 *   +---+---+---+---+---+---+---+---+
	 * 8 | k |   |   |   |   |   |   | R | 8
	 *   +---+---+---+---+---+---+---+---+
	 * 7 |   |   |   |   |   |   |   |   | 7
	 *   +---+---+---+---+---+---+---+---+
	 * 6 | K |   |   |   |   |   |   |   | 6
	 *   +---+---+---+---+---+---+---+---+
	 * 5 |   |   |   |   |   |   |   |   | 5
	 *   +---+---+---+---+---+---+---+---+
	 * 4 |   |   |   |   |   |   |   |   | 4
	 *   +---+---+---+---+---+---+---+---+
	 * 3 |   |   |   |   |   |   |   |   | 3
	 *   +---+---+---+---+---+---+---+---+
	 * 2 |   |   |   |   |   |   |   |   | 2
	 *   +---+---+---+---+---+---+---+---+
	 * 1 | n |   |   |   |   |   |   |   | 1
	 *   +---+---+---+---+---+---+---+---+
	 *     A   B   C   D   E   F   G   H
	 */
	public static Board setPositionMateInOneMoveAfter() throws InvalidCoordinateException {
		setPositionMateInOneMoveBefore();

		moveFigure(BoardCoordinate.H1, BoardCoordinate.H8);

		return board;
	}

	/**
	 * Set position mate in two moves
	 * 
	 * @return created Board
	 * 
	 * Solution: 1. KE4F5 KD6D5 2. QD4D1 mate or 1. KE4F5 KD6D7 2. KF5E5 mate
	 * 
	 *  Board: White moves, e.p. null, 
	 *  OO and OOO impossible for white and black
	 * 
	 *     A   B   C   D   E   F   G   H
	 *   +---+---+---+---+---+---+---+---+
	 * 8 |   |   | R |   |   |   |   |   | 8
	 *   +---+---+---+---+---+---+---+---+
	 * 7 |   |   |   |   |   |   |   |   | 7
	 *   +---+---+---+---+---+---+---+---+
	 * 6 |   |   |   | k |   |   |   |   | 6
	 *   +---+---+---+---+---+---+---+---+
	 * 5 |   |   |   | N |   |   | p |   | 5
	 *   +---+---+---+---+---+---+---+---+
	 * 4 |   |   |   |   | K |   | Q |   | 4
	 *   +---+---+---+---+---+---+---+---+
	 * 3 |   |   |   |   |   |   |   |   | 3
	 *   +---+---+---+---+---+---+---+---+
	 * 2 |   |   |   |   |   |   |   |   | 2
	 *   +---+---+---+---+---+---+---+---+
	 * 1 |   |   |   |   |   |   |   |   | 1
	 *   +---+---+---+---+---+---+---+---+
	 *     A   B   C   D   E   F   G   H
	 * 
	 */
	public static Board setPositionMateInTwoMovesBefore() {
		initialize();

		FigureFactory.createFigure(org.training.java.chess.model.figures.King.class, true, board, 
				BoardCoordinate.E4);
		FigureFactory.createFigure(org.training.java.chess.model.figures.Queen.class, true, board,
				BoardCoordinate.G4);
		FigureFactory.createFigure(org.training.java.chess.model.figures.Knight.class, true, board,
				BoardCoordinate.D5);
		FigureFactory.createFigure(org.training.java.chess.model.figures.Pawn.class, false, board,
				BoardCoordinate.G5);
		FigureFactory.createFigure(org.training.java.chess.model.figures.King.class, false, board, 
				BoardCoordinate.D6);
		FigureFactory.createFigure(org.training.java.chess.model.figures.Rook.class, true, board,
				BoardCoordinate.C8);

		board.setOoWhite(false);
		board.setOooWhite(false);
		board.setOoBlack(false);
		board.setOooBlack(false);
		
		return board;
	}

	/**
	 * Set position mate in two moves after first move
	 * 
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be constructed
	 * @return created Board
	 * 
	 *         Solution: 1. KE4F5 KD6D5 2. QD4D1 mate or 1. KE4F5 KD6D7 2. KF5E5
	 *         mate
	 * 
	 *         Board: Black moves, e.p. null, OO and OOO impossible for white
	 *         and black
	 * 
	 *     A   B   C   D   E   F   G   H
	 *   +---+---+---+---+---+---+---+---+
	 * 8 |   |   | R |   |   |   |   |   | 8
	 *   +---+---+---+---+---+---+---+---+
	 * 7 |   |   |   |   |   |   |   |   | 7
	 *   +---+---+---+---+---+---+---+---+
	 * 6 |   |   |   | k |   |   |   |   | 6
	 *   +---+---+---+---+---+---+---+---+
	 * 5 |   |   |   | N |   | K | p |   | 5
	 *   +---+---+---+---+---+---+---+---+
	 * 4 |   |   |   |   |   |   | Q |   | 4
	 *   +---+---+---+---+---+---+---+---+
	 * 3 |   |   |   |   |   |   |   |   | 3
	 *   +---+---+---+---+---+---+---+---+
	 * 2 |   |   |   |   |   |   |   |   | 2
	 *   +---+---+---+---+---+---+---+---+
	 * 1 |   |   |   |   |   |   |   |   | 1
	 *   +---+---+---+---+---+---+---+---+
	 *     A   B   C   D   E   F   G   H
	 */
	public static Board setPositionMateInTwoMovesAfter() throws InvalidCoordinateException {
		setPositionMateInTwoMovesBefore();

		moveFigure(BoardCoordinate.E4, BoardCoordinate.F5, null);

		return board;
	}

	/**
	 * Set position EnPassant
	 * 
	 * @return created Board
	 * 
	 *         Board: White moves, e.p. null, OO and OOO impossible for white
	 *         and black
	 * 
	 *     A   B   C   D   E   F   G   H
	 *   +---+---+---+---+---+---+---+---+
	 * 8 |   |   |   |   | k |   |   |   | 8
	 *   +---+---+---+---+---+---+---+---+
	 * 7 |   |   |   |   |   |   |   |   | 7
	 *   +---+---+---+---+---+---+---+---+
	 * 6 |   |   |   |   |   |   |   |   | 6
	 *   +---+---+---+---+---+---+---+---+
	 * 5 |   |   |   |   |   |   |   |   | 5
	 *   +---+---+---+---+---+---+---+---+
	 * 4 |   |   |   | p |   |   |   |   | 4
	 *   +---+---+---+---+---+---+---+---+
	 * 3 |   |   |   |   |   |   |   |   | 3
	 *   +---+---+---+---+---+---+---+---+
	 * 2 |   |   |   |   | P |   |   |   | 2
	 *   +---+---+---+---+---+---+---+---+
	 * 1 |   |   |   |   | K |   |   |   | 1
	 *   +---+---+---+---+---+---+---+---+
	 *     A   B   C   D   E   F   G   H
	 */
	public static Board setPositionEnPassantMoveTwoForward() {
		initialize();

		FigureFactory.createFigure(org.training.java.chess.model.figures.King.class, true, board, 
				BoardCoordinate.E1);
		FigureFactory.createFigure(org.training.java.chess.model.figures.Pawn.class, true, board,
				BoardCoordinate.E2);
		FigureFactory.createFigure(org.training.java.chess.model.figures.Pawn.class, false, board,
				BoardCoordinate.D4);
		FigureFactory.createFigure(org.training.java.chess.model.figures.King.class, false, board, 
				BoardCoordinate.E8);

		board.setOoWhite(false);
		board.setOooWhite(false);
		board.setOoBlack(false);
		board.setOooBlack(false);

		return board;
	}

	/**
	 * Set position EnPassant2
	 * 
	 * Black can play pawn D4E3 and hit white pawn E4
	 * 
	 * Board: Black moves, e.p. null, OO and OOO impossible for white and black
	 * 
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be constructed
	 * @return Board initialized with desired position
	 * 
	 *     A   B   C   D   E   F   G   H
	 *   +---+---+---+---+---+---+---+---+
	 * 8 |   |   |   |   | k |   |   |   | 8
	 *   +---+---+---+---+---+---+---+---+
	 * 7 |   |   |   |   |   |   |   |   | 7
	 *   +---+---+---+---+---+---+---+---+
	 * 6 |   |   |   |   |   |   |   |   | 6
	 *   +---+---+---+---+---+---+---+---+
	 * 5 |   |   |   |   |   |   |   |   | 5
	 *   +---+---+---+---+---+---+---+---+
	 * 4 |   |   |   | p | P |   |   |   | 4
	 *   +---+---+---+---+---+---+---+---+
	 * 3 |   |   |   |   |ep |   |   |   | 3
	 *   +---+---+---+---+---+---+---+---+
	 * 2 |   |   |   |   |   |   |   |   | 2
	 *   +---+---+---+---+---+---+---+---+
	 * 1 |   |   |   |   | K |   |   |   | 1
	 *   +---+---+---+---+---+---+---+---+
	 *     A   B   C   D   E   F   G   H
	 */
	public static Board setPositionEnPassantFieldSet() throws InvalidCoordinateException {
		setPositionEnPassantMoveTwoForward();

		moveFigure(BoardCoordinate.E2, BoardCoordinate.E4);

		return board;
	}

	/**
	 * Set en passend position after black pawn D4 hits white pawn E4 with e.p.
	 * 
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be constructed
	 * @return Board initialized with desired position
	 * 
	 * Board: White moves, e.p. null, OO and OOO impossible for white and black
	 * 
	 *     A   B   C   D   E   F   G   H
	 *   +---+---+---+---+---+---+---+---+
	 * 8 |   |   |   |   | k |   |   |   | 8
	 *   +---+---+---+---+---+---+---+---+
	 * 7 |   |   |   |   |   |   |   |   | 7
	 *   +---+---+---+---+---+---+---+---+
	 * 6 |   |   |   |   |   |   |   |   | 6
	 *   +---+---+---+---+---+---+---+---+
	 * 5 |   |   |   |   |   |   |   |   | 5
	 *   +---+---+---+---+---+---+---+---+
	 * 4 |   |   |   |   |   |   |   |   | 4
	 *   +---+---+---+---+---+---+---+---+
	 * 3 |   |   |   |   | p |   |   |   | 3
	 *   +---+---+---+---+---+---+---+---+
	 * 2 |   |   |   |   |   |   |   |   | 2
	 *   +---+---+---+---+---+---+---+---+
	 * 1 |   |   |   |   | K |   |   |   | 1
	 *   +---+---+---+---+---+---+---+---+
	 *     A   B   C   D   E   F   G   H
	 */
	public static Board setPositionEnPassantAfterHit() throws InvalidCoordinateException {
		setPositionEnPassantFieldSet();

		moveFigure(BoardCoordinate.D4, BoardCoordinate.E3);

		return board;
	}

	/**
	 * Set position Pawn hit position
	 * 
	 * @return created Board
	 * 
	 * Board: White moves, e.p. null, OO and OOO impossible for white and black
	 * 
     *     A   B   C   D   E   F   G   H
     *   +---+---+---+---+---+---+---+---+
     * 8 |   |   |   |   | k |   |   |   | 8
     *   +---+---+---+---+---+---+---+---+
     * 7 |   |   |   |   |   |   |   |   | 7
     *   +---+---+---+---+---+---+---+---+
     * 6 |   |   |   |   |   |   |   |   | 6
     *   +---+---+---+---+---+---+---+---+
     * 5 |   |   |   | p |   |   |   |   | 5
     *   +---+---+---+---+---+---+---+---+
     * 4 |   |   |   |   | P |   |   |   | 4
     *   +---+---+---+---+---+---+---+---+
     * 3 |   |   |   |   |   |   |   |   | 3
     *   +---+---+---+---+---+---+---+---+
     * 2 |   |   |   |   |   |   |   |   | 2
     *   +---+---+---+---+---+---+---+---+
     * 1 |   |   |   |   | K |   |   |   | 1
     *   +---+---+---+---+---+---+---+---+
     *     A   B   C   D   E   F   G   H
	 */
	public static Board setPositionPawnHitsBefore() {
		initialize();

		FigureFactory.createFigure(org.training.java.chess.model.figures.King.class, true, board, 
				BoardCoordinate.E1);
		FigureFactory.createFigure(org.training.java.chess.model.figures.Pawn.class, true, board,
				BoardCoordinate.E4);
		FigureFactory.createFigure(org.training.java.chess.model.figures.Pawn.class, false, board,
				BoardCoordinate.D5);
		FigureFactory.createFigure(org.training.java.chess.model.figures.King.class, false, board, 
				BoardCoordinate.E8);

		board.setOoWhite(false);
		board.setOooWhite(false);
		board.setOoBlack(false);
		board.setOooBlack(false);

		return board;
	}

	/**
	 * Set position Pawn hit position afterwards
	 * 
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be constructed
	 * @return Board initialized with desired position
	 * 
	 * Board: Black moves, e.p. null, OO and OO impossible for white and black
	 * 
     *     A   B   C   D   E   F   G   H
     *   +---+---+---+---+---+---+---+---+
     * 8 |   |   |   |   | k |   |   |   | 8
     *   +---+---+---+---+---+---+---+---+
     * 7 |   |   |   |   |   |   |   |   | 7
     *   +---+---+---+---+---+---+---+---+
     * 6 |   |   |   |   |   |   |   |   | 6
     *   +---+---+---+---+---+---+---+---+
     * 5 |   |   |   | P |   |   |   |   | 5
     *   +---+---+---+---+---+---+---+---+
     * 4 |   |   |   |   |   |   |   |   | 4
     *   +---+---+---+---+---+---+---+---+
     * 3 |   |   |   |   |   |   |   |   | 3
     *   +---+---+---+---+---+---+---+---+
     * 2 |   |   |   |   |   |   |   |   | 2
     *   +---+---+---+---+---+---+---+---+
     * 1 |   |   |   |   | K |   |   |   | 1
     *   +---+---+---+---+---+---+---+---+
     *     A   B   C   D   E   F   G   H
	 */
	public static Board setPositionPawnHitsAfter() throws InvalidCoordinateException {
		setPositionPawnHitsBefore();

		moveFigure(BoardCoordinate.E4, BoardCoordinate.D5);

		return board;
	}

	/**
	 * Set position where black cannot castle long OO and OOO because of chess
	 * 
	 * @return board with no oo and no ooo Position
	 * 
	 * Board: Black moves, e.p. null OO and OOO impossible for white and possible for black
	 * 
	 *      A   B   C   D   E   F   G   H
	 *    +---+---+---+---+---+---+---+---+
	 *  8 | r |   |   |   | k |   |   | r | 8
	 *    +---+---+---+---+---+---+---+---+
	 *  7 |   |   |   |   |   |   |   |   | 7
	 *    +---+---+---+---+---+---+---+---+
	 *  6 |   |   |   |   | R |   |   |   | 6
	 *    +---+---+---+---+---+---+---+---+
	 *  5 |   |   |   |   |   |   |   |   | 5
  	 *    +---+---+---+---+---+---+---+---+
	 *  4 |   |   |   |   |   |   |   |   | 4
  	 *    +---+---+---+---+---+---+---+---+
	 *  3 |   |   |   |   |   |   |   |   | 3
  	 *    +---+---+---+---+---+---+---+---+
	 *  2 |   |   |   |   |   |   |   |   | 2
  	 *    +---+---+---+---+---+---+---+---+
	 *  1 |   |   |   |   | K |   |   |   | 1
  	 *    +---+---+---+---+---+---+---+---+
	 *      A   B   C   D   E   F   G   H
	 */
	public static Board setPositionKingChessNoOONoOOO() {
		initialize();

		FigureFactory.createFigure(org.training.java.chess.model.figures.Rook.class, true, board, 
				BoardCoordinate.E1);
		FigureFactory.createFigure(org.training.java.chess.model.figures.Rook.class, true, board,
				BoardCoordinate.E6);
		FigureFactory.createFigure(org.training.java.chess.model.figures.Rook.class, false, board,
				BoardCoordinate.A8);
		FigureFactory.createFigure(org.training.java.chess.model.figures.Rook.class, false, board, 
				BoardCoordinate.E8);
		FigureFactory.createFigure(org.training.java.chess.model.figures.Rook.class, false, board,
				BoardCoordinate.H8);

		board.setOoWhite(false);
		board.setOooWhite(false);
		board.setOoBlack(true);
		board.setOooBlack(true);

		board.switchWhiteMoves();

		return board;
	}

	/**
	 * Set position where black cannot castle long OO and OOO because field
	 * where king jumps over is threatened
	 * 
	 * @return board with no oo and no ooo Position
	 * 
	 * Board: Black moves, e.p. null OO and OOO impossible for white OO and OOO possible for black
	 * 
	 *      A   B   C   D   E   F   G   H
	 *    +---+---+---+---+---+---+---+---+
	 *  8 | r |   |   |   | k |   |   | r | 8
	 *    +---+---+---+---+---+---+---+---+
	 *  7 |   |   |   |   | B |   |   |   | 7
	 *    +---+---+---+---+---+---+---+---+
	 *  6 |   |   |   |   |   |   |   |   | 6
	 *    +---+---+---+---+---+---+---+---+
	 *  5 |   |   |   |   |   |   |   |   | 5
  	 *    +---+---+---+---+---+---+---+---+
	 *  4 |   |   |   |   |   |   |   |   | 4
  	 *    +---+---+---+---+---+---+---+---+
	 *  3 |   |   |   |   |   |   |   |   | 3
  	 *    +---+---+---+---+---+---+---+---+
	 *  2 |   |   |   |   |   |   |   |   | 2
  	 *    +---+---+---+---+---+---+---+---+
	 *  1 |   |   |   |   | K |   |   |   | 1
  	 *    +---+---+---+---+---+---+---+---+
	 *      A   B   C   D   E   F   G   H
	 */
	public static Board setPositionKingNoChessNoOONoOOO() {
		initialize();

		FigureFactory.createFigure(org.training.java.chess.model.figures.Bishop.class, true, board,
				BoardCoordinate.E1);
		FigureFactory.createFigure(org.training.java.chess.model.figures.Bishop.class, true, board,
				BoardCoordinate.E7);
		FigureFactory.createFigure(org.training.java.chess.model.figures.Rook.class, false, board,
				BoardCoordinate.A8);
		FigureFactory.createFigure(org.training.java.chess.model.figures.Bishop.class, false, board,
				BoardCoordinate.E8);
		FigureFactory.createFigure(org.training.java.chess.model.figures.Rook.class, false, board,
				BoardCoordinate.H8);

		board.setOoWhite(false);
		board.setOooWhite(false);
		board.setOoBlack(true);
		board.setOooBlack(true);

		board.switchWhiteMoves();

		return board;
	}

	/**
	 * Set position where black cannot castle long OO and OOO because coordinate
	 * where king is after oo and ooo is threatened
	 * 
	 * @return board with no oo and no ooo Position
	 * 
	 * Board: Black moves, e.p. null OO and OO impossible for white OO and OO possible for black
	 * 
	 *      A   B   C   D   E   F   G   H
	 *    +---+---+---+---+---+---+---+---+
	 *  8 | r |   |   |   | k |   |   | r | 8
	 *    +---+---+---+---+---+---+---+---+
	 *  7 |   |   |   |   |   |   |   |   | 7
	 *    +---+---+---+---+---+---+---+---+
	 *  6 |   |   |   |   | B |   |   |   | 6
	 *    +---+---+---+---+---+---+---+---+
	 *  5 |   |   |   |   |   |   |   |   | 5
  	 *    +---+---+---+---+---+---+---+---+
	 *  4 |   |   |   |   |   |   |   |   | 4
  	 *    +---+---+---+---+---+---+---+---+
	 *  3 |   |   |   |   |   |   |   |   | 3
  	 *    +---+---+---+---+---+---+---+---+
	 *  2 |   |   |   |   |   |   |   |   | 2
  	 *    +---+---+---+---+---+---+---+---+
	 *  1 |   |   |   |   | K |   |   |   | 1
  	 *    +---+---+---+---+---+---+---+---+
	 *      A   B   C   D   E   F   G   H
	 */
	public static Board setPositionKingThreatenedAfterOOAndOOO() {
		initialize();

		FigureFactory.createFigure(org.training.java.chess.model.figures.King.class, true, board, 
				BoardCoordinate.E1);
		FigureFactory.createFigure(org.training.java.chess.model.figures.Bishop.class, true, board,
				BoardCoordinate.E6);
		FigureFactory.createFigure(org.training.java.chess.model.figures.Rook.class, false, board,
				BoardCoordinate.A8);
		FigureFactory.createFigure(org.training.java.chess.model.figures.King.class, false, board, 
				BoardCoordinate.E8);
		FigureFactory.createFigure(org.training.java.chess.model.figures.Rook.class, false, board,
				BoardCoordinate.H8);

		board.setOoWhite(false);
		board.setOooWhite(false);
		board.setOoBlack(true);
		board.setOooBlack(true);

		board.switchWhiteMoves();

		return board;
	}

	/**
	 * Set position OO and OOO
	 * 
	 * @return board with oo Position
	 * 
	 * Board: White moves, e.p. null, OO and OOO possible for white and black
	 * 
	 *      A   B   C   D   E   F   G   H
	 *    +---+---+---+---+---+---+---+---+
	 *  8 |   |   |   |   | k |   |   |   | 8
	 *    +---+---+---+---+---+---+---+---+
	 *  7 |   |   |   |   |   |   |   |   | 7
	 *    +---+---+---+---+---+---+---+---+
	 *  6 |   |   |   |   |   |   |   |   | 6
	 *    +---+---+---+---+---+---+---+---+
	 *  5 |   |   |   |   |   |   |   |   | 5
  	 *    +---+---+---+---+---+---+---+---+
	 *  4 |   |   |   |   |   |   |   |   | 4
  	 *    +---+---+---+---+---+---+---+---+
	 *  3 |   |   |   |   |   |   |   |   | 3
  	 *    +---+---+---+---+---+---+---+---+
	 *  2 | q |   |   |   |   |   |   | r | 2
  	 *    +---+---+---+---+---+---+---+---+
	 *  1 | R |   |   |   | K |   |   | R | 1
  	 *    +---+---+---+---+---+---+---+---+
	 *      A   B   C   D   E   F   G   H
	 */
	public static Board setPositionKingOOAndOOOBefore() {
		initialize();

		FigureFactory.createFigure(org.training.java.chess.model.figures.Rook.class, true, board,
				BoardCoordinate.A1);
		FigureFactory.createFigure(org.training.java.chess.model.figures.King.class, true, board, 
				BoardCoordinate.E1);
		FigureFactory.createFigure(org.training.java.chess.model.figures.Queen.class, false, board,
				BoardCoordinate.A2);
		FigureFactory.createFigure(org.training.java.chess.model.figures.Rook.class, false, board,
				BoardCoordinate.H2);
		FigureFactory.createFigure(org.training.java.chess.model.figures.King.class, false, board,
				BoardCoordinate.E8);
		FigureFactory.createFigure(org.training.java.chess.model.figures.Rook.class, true, board,
				BoardCoordinate.H1);

		return board;
	}

	/**
	 * Set position OO and OOO
	 * 
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be constructed
	 * @return Board initialized with desired position
	 * 
	 * Board: Black moves, e.p. null, OO and OOO impossible for white and possible for black
	 * 
	 *      A   B   C   D   E   F   G   H
	 *    +---+---+---+---+---+---+---+---+
	 *  8 |   |   |   |   | k |   |   |   | 8
	 *    +---+---+---+---+---+---+---+---+
	 *  7 |   |   |   |   |   |   |   |   | 7
	 *    +---+---+---+---+---+---+---+---+
	 *  6 |   |   |   |   |   |   |   |   | 6
	 *    +---+---+---+---+---+---+---+---+
	 *  5 |   |   |   |   |   |   |   |   | 5
  	 *    +---+---+---+---+---+---+---+---+
	 *  4 |   |   |   |   |   |   |   |   | 4
  	 *    +---+---+---+---+---+---+---+---+
	 *  3 |   |   |   |   |   |   |   |   | 3
  	 *    +---+---+---+---+---+---+---+---+
	 *  2 | q |   |   |   |   |   |   | r | 2
  	 *    +---+---+---+---+---+---+---+---+
	 *  1 | R |   |   |   |   | R | K |   | 1
  	 *    +---+---+---+---+---+---+---+---+
	 *      A   B   C   D   E   F   G   H
	 */
	public static Board setPositionOOAfter() throws InvalidCoordinateException {
		Board board = setPositionKingOOAndOOOBefore();

		moveFigure(BoardCoordinate.E1, BoardCoordinate.G1);

		return board;
	}

	/**
	 * Set position after OOO
	 * 
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be constructed
	 * @return Board initialized with desired position
	 * 
	 * Board: Black moves, e.p. null, OO and OO impossible for white and possible for black
	 * 
	 *      A   B   C   D   E   F   G   H
	 *    +---+---+---+---+---+---+---+---+
	 *  8 |   |   |   |   | k |   |   |   | 8
	 *    +---+---+---+---+---+---+---+---+
	 *  7 |   |   |   |   |   |   |   |   | 7
	 *    +---+---+---+---+---+---+---+---+
	 *  6 |   |   |   |   |   |   |   |   | 6
	 *    +---+---+---+---+---+---+---+---+
	 *  5 |   |   |   |   |   |   |   |   | 5
  	 *    +---+---+---+---+---+---+---+---+
	 *  4 |   |   |   |   |   |   |   |   | 4
  	 *    +---+---+---+---+---+---+---+---+
	 *  3 |   |   |   |   |   |   |   |   | 3
  	 *    +---+---+---+---+---+---+---+---+
	 *  2 | q |   |   |   |   |   |   | r | 2
  	 *    +---+---+---+---+---+---+---+---+
	 *  1 |   |   | K | R |   |   |   | R | 1
  	 *    +---+---+---+---+---+---+---+---+
	 *      A   B   C   D   E   F   G   H
	 */
	public static Board setPositionOOOAfter() throws InvalidCoordinateException {
		Board board = setPositionKingOOAndOOOBefore();

		moveFigure(BoardCoordinate.E1, BoardCoordinate.C1);

		return board;
	}

	/**
	 * Set position pawn exchange to knight in order to win in one move before
	 * move
	 * 
	 * @return created Board
	 * 
	 * Move: Pawn B2B1 exchanges to Knight
	 * 
	 * Board: Black moves, e.p. null, OO and OOO impossible for white and black
	 * 
	 * 
	 *     A   B   C   D   E   F   G   H
	 *   +---+---+---+---+---+---+---+---+
	 * 8 |   |   |   |   |   |   |   |   | 8
	 *   +---+---+---+---+---+---+---+---+
	 * 7 |   |   |   |   |   |   |   |   | 7
	 *   +---+---+---+---+---+---+---+---+
	 * 6 |   |   |   |   |   |   |   |   | 6
	 *   +---+---+---+---+---+---+---+---+
	 * 5 |   | q |   |   |   |   |   |   | 5
	 *   +---+---+---+---+---+---+---+---+
	 * 4 |   |   |   |   |   |   |   |   | 4
	 *   +---+---+---+---+---+---+---+---+
	 * 3 | K |   |   |   |   |   |   |   | 3
	 *   +---+---+---+---+---+---+---+---+
	 * 2 |   | p |   |   |   |   |   |   | 2
	 *   +---+---+---+---+---+---+---+---+
	 * 1 | k |   |   |   |   |   |   |   | 1
	 *   +---+---+---+---+---+---+---+---+
	 *     A   B   C   D   E   F   G   H
	 * 
	 */
	public static Board setPositionPawnExchangeToKnightBefore() {
		initialize();

		FigureFactory.createFigure(org.training.java.chess.model.figures.King.class, false, board,
				BoardCoordinate.A1);
		FigureFactory.createFigure(org.training.java.chess.model.figures.Pawn.class, false, board,
				BoardCoordinate.B2);
		FigureFactory.createFigure(org.training.java.chess.model.figures.King.class, true, board, 
				BoardCoordinate.A3);
		FigureFactory.createFigure(org.training.java.chess.model.figures.Queen.class, false, board,
				BoardCoordinate.B5);

		board.switchWhiteMoves();

		board.setOoWhite(false);
		board.setOooWhite(false);
		board.setOoBlack(false);
		board.setOooBlack(false);

		return board;
	}

	/**
	 * Set position pawn exchange to knight in order to win in one move after
	 * move
	 * 
	 * @return created Board
	 * 
	 *  Board: Whites moves, e.p. null, OO and OO impossible for white and black, chess mate
	 * 
	 *     A   B   C   D   E   F   G   H
	 *   +---+---+---+---+---+---+---+---+
	 * 8 |   |   |   |   |   |   |   |   | 8
	 *   +---+---+---+---+---+---+---+---+
	 * 7 |   |   |   |   |   |   |   |   | 7
	 *   +---+---+---+---+---+---+---+---+
	 * 6 |   |   |   |   |   |   |   |   | 6
	 *   +---+---+---+---+---+---+---+---+
	 * 5 |   | q |   |   |   |   |   |   | 5
	 *   +---+---+---+---+---+---+---+---+
	 * 4 |   |   |   |   |   |   |   |   | 4
	 *   +---+---+---+---+---+---+---+---+
	 * 3 | K |   |   |   |   |   |   |   | 3
	 *   +---+---+---+---+---+---+---+---+
	 * 2 |   |   |   |   |   |   |   |   | 2
	 *   +---+---+---+---+---+---+---+---+
	 * 1 | k | n |   |   |   |   |   |   | 1
	 *   +---+---+---+---+---+---+---+---+
	 *     A   B   C   D   E   F   G   H
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be constructed
	 */
	public static Board setPositionPawnExchangeToKnightAfter() throws InvalidCoordinateException {
		setPositionPawnExchangeToKnightBefore();

		moveFigure(BoardCoordinate.B2, BoardCoordinate.B1, org.training.java.chess.model.figures.Knight.class);

		return board;
	}

	/**
	 * Set position Pawn Exchange before move
	 * 
	 * @return created Board
	 * 
	 * Board: White moves, e.p. null, OO and OO impossible for white and black
	 * 
	 *     A   B   C   D   E   F   G   H
	 *   +---+---+---+---+---+---+---+---+
	 * 8 | r |   |   |   |   |   |   |   | 8
	 *   +---+---+---+---+---+---+---+---+
	 * 7 |   | P |   |   | k |   |   |   | 7
	 *   +---+---+---+---+---+---+---+---+
	 * 6 |   |   |   |   |   |   |   |   | 6
	 *   +---+---+---+---+---+---+---+---+
	 * 5 |   |   |   |   |   |   |   |   | 5
	 *   +---+---+---+---+---+---+---+---+
	 * 4 |   |   |   |   |   |   |   |   | 4
	 *   +---+---+---+---+---+---+---+---+
	 * 3 |   |   |   |   |   |   |   |   | 3
	 *   +---+---+---+---+---+---+---+---+
	 * 2 |   |   |   |   |   |   |   |   | 2
	 *   +---+---+---+---+---+---+---+---+
	 * 1 |   |   |   |   | K |   |   |   | 1
	 *   +---+---+---+---+---+---+---+---+
	 *     A   B   C   D   E   F   G   H
	 * 
	 */
	public static Board setPositionPawnExchangeBefore() {
		initialize();

		FigureFactory.createFigure(org.training.java.chess.model.figures.King.class, true, board, 
				BoardCoordinate.E1);
		FigureFactory.createFigure(org.training.java.chess.model.figures.Pawn.class, true, board,
				BoardCoordinate.B7);
		FigureFactory.createFigure(org.training.java.chess.model.figures.King.class, false, board, 
				BoardCoordinate.E7);
		FigureFactory.createFigure(org.training.java.chess.model.figures.Rook.class, false, board,
				BoardCoordinate.A8);

		board.setOoWhite(false);
		board.setOooWhite(false);
		board.setOoBlack(false);
		board.setOooBlack(false);

		return board;
	}

	/**
	 * Set position Pawn Exchange before move
	 * 
	 * Board: Black moves, e.p. null, OO and OOO impossible for white and black
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be constructed
	 * @return Initialized Board
	 * 
	 *     A   B   C   D   E   F   G   H
	 *   +---+---+---+---+---+---+---+---+
	 * 8 | Q |   |   |   |   |   |   |   | 8
	 *   +---+---+---+---+---+---+---+---+
	 * 7 |   |   |   |   | k |   |   |   | 7
	 *   +---+---+---+---+---+---+---+---+
	 * 6 |   |   |   |   |   |   |   |   | 6
	 *   +---+---+---+---+---+---+---+---+
	 * 5 |   |   |   |   |   |   |   |   | 5
	 *   +---+---+---+---+---+---+---+---+
	 * 4 |   |   |   |   |   |   |   |   | 4
	 *   +---+---+---+---+---+---+---+---+
	 * 3 |   |   |   |   |   |   |   |   | 3
	 *   +---+---+---+---+---+---+---+---+
	 * 2 |   |   |   |   |   |   |   |   | 2
	 *   +---+---+---+---+---+---+---+---+
	 * 1 |   |   |   |   | K |   |   |   | 1
	 *   +---+---+---+---+---+---+---+---+
	 */
	public static Board setPositionPawnExchangeAfter() throws InvalidCoordinateException {
		setPositionPawnExchangeBefore();

		moveFigure(BoardCoordinate.B7, BoardCoordinate.A8, org.training.java.chess.model.figures.Queen.class);

		return board;
	}

	/**
	 * Set position Pawn Exchange before move
	 * 
	 * Best move: White King G7 hits black Rook H8
	 * 
	 * Board: White moves, e.p. null OO and OOO impossible for white
	 * OO possible and OOO impossible for black
	 * 
	 *     A   B   C   D   E   F   G   H
	 *   +---+---+---+---+---+---+---+---+
	 * 8 |   |   |   |   | k |   |   | r | 8
	 *   +---+---+---+---+---+---+---+---+
	 * 7 |   |   |   |   |   |   | K |   | 7
	 *   +---+---+---+---+---+---+---+---+
	 * 6 |   |   |   |   |   |   |   |   | 6
	 *   +---+---+---+---+---+---+---+---+
	 * 5 |   |   |   |   |   |   |   |   | 5
	 *   +---+---+---+---+---+---+---+---+
	 * 4 |   |   |   |   |   |   |   |   | 4
	 *   +---+---+---+---+---+---+---+---+
	 * 3 |   |   |   |   |   |   |   |   | 3
	 *   +---+---+---+---+---+---+---+---+
	 * 2 |   |   |   |   |   |   |   |   | 2
	 *   +---+---+---+---+---+---+---+---+
	 * 1 |   |   |   |   |   |   |   |   | 1
	 *   +---+---+---+---+---+---+---+---+
	 */
	public static Board setPositionKingDestroyBefore() {

		initialize();

		FigureFactory.createFigure(org.training.java.chess.model.figures.King.class, true, board, 
				BoardCoordinate.G7);
		FigureFactory.createFigure(org.training.java.chess.model.figures.King.class, false, board, 
				BoardCoordinate.E8);
		FigureFactory.createFigure(org.training.java.chess.model.figures.Rook.class, false, board, 
				BoardCoordinate.H8);

		board.setOoWhite(false);
		board.setOooWhite(false);
		board.setOoBlack(true);
		board.setOooBlack(false);

		return board;
	}

	/**
	 * Set position Pawn Exchange after move
	 * 
	 * Board: Black moves, e.p. null OO and OOO impossible for white and black
	 * 
	 *     A   B   C   D   E   F   G   H
	 *   +---+---+---+---+---+---+---+---+
	 * 8 |   |   |   |   | k |   |   | K | 8
	 *   +---+---+---+---+---+---+---+---+
	 * 7 |   |   |   |   |   |   |   |   | 7
	 *   +---+---+---+---+---+---+---+---+
	 * 6 |   |   |   |   |   |   |   |   | 6
	 *   +---+---+---+---+---+---+---+---+
	 * 5 |   |   |   |   |   |   |   |   | 5
	 *   +---+---+---+---+---+---+---+---+
	 * 4 |   |   |   |   |   |   |   |   | 4
	 *   +---+---+---+---+---+---+---+---+
	 * 3 |   |   |   |   |   |   |   |   | 3
	 *   +---+---+---+---+---+---+---+---+
	 * 2 |   |   |   |   |   |   |   |   | 2
	 *   +---+---+---+---+---+---+---+---+
	 * 1 |   |   |   |   |   |   |   |   | 1
	 *   +---+---+---+---+---+---+---+---+
	 * @throws InvalidCoordinateException 
	 */
	public static Board setPositionKingDestroyAfter() throws InvalidCoordinateException {

		setPositionKingDestroyBefore();
		moveFigure(BoardCoordinate.G7, BoardCoordinate.H8);
		return board;
	}

	/**
	 * Make a move and switch if white or black moves Help funktion to make move
	 * excluding en passant, oo, and pawn exchange
	 * 
	 * @param fromCoord
	 *            Coordinate where figure stands before move
	 * @param toCoord
	 *            Coordinate wher figure stands after move
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be constructed
	 */
	private static void moveFigure(BoardCoordinate fromCoord,
			BoardCoordinate toCoord) throws InvalidCoordinateException {
		moveFigure(fromCoord, toCoord, null);
	}

	/**
	 * Make a move and switch if white or black moves Help funktion to make move
	 * including en passant, oo, and pawn exchange
	 * 
	 * @param fromCoord
	 *            Coordinate where figure stands before move
	 * @param toCoord
	 *            Coordinate where figure stands after move
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be constructed 
	 */
	private static void moveFigure(BoardCoordinate fromCoord,
			BoardCoordinate toCoord,
			Class<? extends Figure> pawnExchangeFigureClass) throws InvalidCoordinateException {
		int fromColumn = fromCoord.getColumn();
		int toColumn = toCoord.getColumn();
		Figure fromFigure = board.getFigure(fromCoord);
		boolean fromFigureWhite = fromFigure.isWhite();

		Figure toFigure = board.getFigure(toCoord);

		board.setFigure(toCoord, fromFigure);
		board.setFigure(fromCoord, null);

		// en passant
		if (fromFigure instanceof Pawn) {
			int fromRow = fromCoord.getRow();
			int toRow = toCoord.getRow();

			// Set en passant field
			if (fromRow - toRow == 2) {
				BoardCoordinate enPassantNew = new BoardCoordinate(
						fromCoord.getColumn(), 5);
				board.setEnPassant(enPassantNew);
			} else if (fromRow - toRow == -2) {
				BoardCoordinate enPassantNew = new BoardCoordinate(
						fromCoord.getColumn(), 2);
				board.setEnPassant(enPassantNew);
			} else {
				board.setEnPassant(null);
			}

			// hit figure
			if (fromColumn != toColumn && toFigure == null) {
				BoardCoordinate hitCoordinate = new BoardCoordinate(toColumn,
						fromRow);
				board.setFigure(hitCoordinate, null);
			}

			// pawn exchange
			if (toRow == 0 || toRow == 7) {
				Assert.assertNotNull(pawnExchangeFigureClass);
				FigureFactory.createFigure(pawnExchangeFigureClass,
						fromFigureWhite, board, toCoord);
			}
		}

		// Set OO
		// Move rock when rochade
		if (fromFigure instanceof King) {
			if (fromFigureWhite) {
				board.setOoWhite(false);
				board.setOooWhite(false);
			} else {
				board.setOoBlack(false);
				board.setOooBlack(false);
			}
			if (Math.abs(fromColumn - toColumn) == 2) {
				BoardCoordinate rookCoordinateOld = null;
				BoardCoordinate rockCoordinateNew = null;
				if (fromFigureWhite) {
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
				board.setFigure(rookCoordinateOld, null);
			}
		}
		// Destroy castling when figure moves to A1, H1, A8, or H8
		if (toCoord.equals(BoardCoordinate.A1)) { // Destroy OO white
			board.setOooWhite(false);
		} else if (toCoord.equals(BoardCoordinate.H1)) {  // Destroy OOO white
			board.setOoWhite(false);
		}
		else if (toCoord.equals(BoardCoordinate.A8)) {  // Destroy OO black
			board.setOooBlack(false);
		}
		else if (toCoord.equals(BoardCoordinate.H8)) { // Destroy OOO black
			board.setOoBlack(false);
		}

		board.switchWhiteMoves();
	}

	/**
	 * Getter for board
	 * 
	 * @return the board
	 */
	public static Board getBoard() {
		return board;
	}

	/**
	 * Getter for computerWhite
	 * 
	 * @return the computerWhite
	 */
	public static ComputerPlayer getComputerWhite() {
		return computerWhite;
	}

	/**
	 * Getter for computerBlack
	 * 
	 * @return the computerBlack
	 */
	public static ComputerPlayer getComputerBlack() {
		return computerBlack;
	}
}