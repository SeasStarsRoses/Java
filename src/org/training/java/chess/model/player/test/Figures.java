package org.training.java.chess.model.player.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.training.java.chess.game.Board;
import org.training.java.chess.game.Move;
import org.training.java.chess.model.ai.StrongAI;
import org.training.java.chess.model.coordinate.BoardCoordinate;
import org.training.java.chess.model.coordinate.InvalidCoordinateException;
import org.training.java.chess.model.figures.Figure;
import org.training.java.chess.model.figures.King;
import org.training.java.chess.model.player.ComputerPlayer;

/**
 * JUnit Test for AI
 * 
 * @author Peter Heide, pheide@t-online.de
 * @version 1
 * @since 17.02.2018
 */
public class Figures {

	/**
	 * JUnit test en passant where white pawn moves two fields forward
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be constructed
	 */
	@Test
	public void testEnPassantPawnMovesTwoFieldsForward() throws InvalidCoordinateException {
		Board boardBefore = Boards.setPositionEnPassantMoveTwoForward();
		
		Figure fromFigure = boardBefore.getFigure(BoardCoordinate.E2);
		
		
		Move move = MoveFactory.makeMove(BoardCoordinate.E2, BoardCoordinate.E4, boardBefore);;
		fromFigure.move(boardBefore, move);

		Board boardAfter = Boards.setPositionEnPassantFieldSet();
		Assert.assertTrue("Wrong en passant board after white pawn moves from E2 to E4", boardBefore.equals(boardAfter));
		
		boardBefore = Boards.setPositionEnPassantMoveTwoForward();
		Helper.testMoveBack(move, boardBefore, boardAfter);
	}

	/**
	 * JUnit test for en passant where black pawn hits white pawn
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be constructed
	 */
	@Test
	public void testEnPassantHit() throws InvalidCoordinateException {
		Board boardBefore = Boards.setPositionEnPassantFieldSet();
		ComputerPlayer computerBlack = Boards.getComputerBlack();
		
		StrongAI ai = new StrongAI();
		ai.doTurn(computerBlack, boardBefore, 1);
		
		Move move = boardBefore.getLastMove();
		
		Board boardAfter = Boards.setPositionEnPassantAfterHit();
		Assert.assertEquals("Wrong board after black pawn hits en passant pawn E4", boardBefore, boardAfter);
		
		boardBefore = Boards.setPositionEnPassantFieldSet();
		Helper.testMoveBack(move, boardBefore, boardAfter);
	}	
	/**
	 * White wins in one move (chess mate)
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be constructed
	 */
	@Test
	public void testPawnHits() throws InvalidCoordinateException {
		Board board = Boards.setPositionPawnHitsBefore();
		ComputerPlayer computerWhite = Boards.getComputerWhite();
		
		StrongAI strongAI = new StrongAI();
		strongAI.doTurn(computerWhite, board, 1);
		
		Board boardAfter = Boards.setPositionPawnHitsAfter();
		Assert.assertEquals("Wrong end position after pawn hits pawn", board, boardAfter);
	}		
	
	/**
	 * White wins in one move (chess mate)
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be constructed
	 */
	@Test
	public void testPawnExchangeToQueen() throws InvalidCoordinateException {
		Board board = Boards.setPositionPawnExchangeBefore();
		ComputerPlayer computerWhite = Boards.getComputerWhite();
		
		StrongAI strongAI = new StrongAI();
		strongAI.doTurn(computerWhite, board, 1);
		
		Board boardAfter = Boards.setPositionPawnExchangeAfter();
		Assert.assertEquals("Wrong end position after pawn exchange", board, boardAfter);
	}			

	/**
	 * White wins in one move (chess mate)
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be constructed
	 */
	@Test
	public void testPawnExchangeToKnight() throws InvalidCoordinateException {
		Board beforeBoard = Boards.setPositionPawnExchangeToKnightBefore();
		Board afterBoard = Boards.setPositionPawnExchangeToKnightAfter();
		Helper.testMove(beforeBoard, afterBoard, 2);		
	}			
	
	/**
	 * Test for OO short
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be constructed
	 */
	@Test
	public void testKingOO() throws InvalidCoordinateException {
		testOOAndOOOHelper(BoardCoordinate.E1, BoardCoordinate.G1, Boards.setPositionOOAfter());
	}	

	/**
	 * Test for OOO short
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be constructed
	 */
	@Test
	public void testKingOOO() throws InvalidCoordinateException {
		testOOAndOOOHelper(BoardCoordinate.E1, BoardCoordinate.C1, Boards.setPositionOOOAfter());
	}	

	/**
	 * Test for OO short
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be constructed
	 */
	@Test
	public void testKingChessNoOONoOOO() throws InvalidCoordinateException {
		testKingNoOOAndOOOHelper(Boards.setPositionKingChessNoOONoOOO());
	}	

	/**
	 * Test for OO short
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be constructed
	 */
	@Test
	public void testKingNoChessNoOONoOOO() throws InvalidCoordinateException {
		testKingNoOOAndOOOHelper(Boards.setPositionKingNoChessNoOONoOOO());
		testKingNoOOAndOOOHelper(Boards.setPositionKingThreatenedAfterOOAndOOO());
	}	
	
	/**
	 * Make sure oo and ooo not possible
	 * @param setPositionChessNoOONoOOO
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be constructed
	 */
	private void testKingNoOOAndOOOHelper(Board board) throws InvalidCoordinateException {
		List<Move> moves = board.getMoves();
		for (Move move : moves) {
			BoardCoordinate fromCoord = move.getFromCoord();
			Figure fromFigure = board.getFigure(fromCoord);
			if (fromFigure instanceof King) {
				BoardCoordinate toCoord = move.getToCoord();
				Assert.assertFalse("Long Castling not allowed for black", BoardCoordinate.C8.equals(toCoord));		
				Assert.assertFalse("Short Castling not allowed for black", BoardCoordinate.G8.equals(toCoord));		
			}
		}
	}

	/**
	 * Test for OO and OOO, both short and long
	 * @throws InvalidCoordinateException 
	 */
	public void testOOAndOOOHelper(BoardCoordinate fromCoord, BoardCoordinate toCoord, Board afterBoard) throws InvalidCoordinateException {
		Board board = Boards.setPositionKingOOAndOOOBefore();
		List<Move> moves = board.getMoves();
		Figure from = board.getFigure(fromCoord);
		Move ooMove = MoveFactory.makeMove(fromCoord, toCoord, board);
		boolean found = false;
		for (Move move : moves) {
			if (ooMove.equals(move)) {
				found = true;
				break;
			}
		}
		Assert.assertTrue("oo or ooo not found in move list", found);
		
		from.move(board, ooMove);			
		Assert.assertEquals("Board after oo not correct", board, afterBoard);

		// Take move back
		Board beforeBoard = Boards.setPositionKingOOAndOOOBefore();	
		Helper.testMoveBack(ooMove, beforeBoard, afterBoard);
	}				

	/**
	 * Test only legal Knight move
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be constructed
	 */
	@Test
	public void testKnightMove() throws InvalidCoordinateException {
		Helper.testMove(Boards.setPositionKnightMoveBefore(), Boards.setPositionKnightMoveAfter(), 1);
	}				

	/**
	 * Test only legal Bishop move
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be constructed
	 */
	@Test
	public void testBishopMove() throws InvalidCoordinateException {
		Helper.testMove(Boards.setPositionBishopMoveBefore(), Boards.setPositionBishopMoveAfter(), 1);
	}					

	/**
	 * Test only legal rook move
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be constructed
	 */
	@Test
	public void testRookMove() throws InvalidCoordinateException {
		Helper.testMove(Boards.setPositionRookMoveBefore(), Boards.setPositionRookMoveAfter(), 1);
	}					

	/**
	 * Test only legal rook move
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be constructed
	 */
	@Test
	public void testQueenMove() throws InvalidCoordinateException {
		Helper.testMove(Boards.setPositionQueenMoveBefore(), Boards.setPositionQueenMoveAfter(), 1);
	}						
	
	/**
	 * Test white king destroys black oo by hitting black rook
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be constructed
	 */
	@Test
	public void testKingDestroysOO() throws InvalidCoordinateException {
		System.out.println(Boards.setPositionKingDestroyBefore());
		Helper.testMove(Boards.setPositionKingDestroyBefore(), Boards.setPositionKingDestroyAfter(), 1);
	}			
}
