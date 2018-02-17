package org.training.java.chess.controller;

import org.training.java.chess.chess.view.internationalization.Languages;
import org.training.java.chess.game.Board;
import org.training.java.chess.model.coordinate.InvalidCoordinateException;
import org.training.java.chess.model.logging.Logger;
import org.training.java.chess.model.player.chess.view.View;
import org.training.java.chess.view.dialogs.WelcomeDialog;


/** 
 * Chess Class where the Application is started
 * Starts a chess game
 * @author Peter Heide, pheide@t-online.de
 * @version 1 
 */

public class Game {
	/**
	 * Prints welcome text on console
	 */
	public static void welcome() {
		Logger.log(" __      __       ___    ___    __"); 
		Logger.log(        	"/\\ \\  __/\\ \\  __ /\\_ \\  /\\_ \\  /\\ \\  _"); 
		Logger.log(        	"\\ \\ \\/\\ \\ \\ \\/\\_\\\\//\\ \\ \\//\\ \\ \\ \\ \\/'\\     ___     ___ ___     ___ ___       __    ___");     
		Logger.log(			 " \\ \\ \\ \\ \\ \\ \\/\\ \\ \\ \\ \\  \\ \\ \\ \\ \\ , <    / __`\\ /' __` __`\\ /' __` __`\\   /'__`\\/' _ `\\"); 
		Logger.log(			  "  \\ \\ \\_/ \\_\\ \\ \\ \\ \\_\\ \\_ \\_\\ \\_\\ \\ \\\\`\\ /\\ \\ \\ \\/\\ \\/\\ \\/\\ \\/\\ \\/\\ \\/\\ \\ /\\  __//\\ \\/\\ \\"); 
		Logger.log(			   "   \\ `\\___x___/\\ \\_\\/\\____\\/\\____\\\\ \\_\\ \\_\\ \\____/\\ \\_\\ \\_\\ \\_\\ \\_\\ \\_\\ \\_\\\\ \\____\\ \\_\\ \\_\\"); 
		Logger.log(			    "    \\/___/___/  \\/_/\\/____/\\/____/ \\/_/\\/_/\\/___/  \\/_/\\/_/\\/_/\\/_/\\/_/\\/_/ \\/____/\\/_/\\/_/" + "\n"); 		
	}
	
	/**
	 * main method of the application
	 * @param args used for the languages english, german, or french: Game en US, Game de DE, or Game fr FR
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be constructed
	 */
	public static void main(String[] args) throws InvalidCoordinateException {
		// Internationalization
		Languages.initialize(args);
		
		Controller controller = null;
		View view = null;
		Board board = null;
		
		do {
			welcome();
			// Create an object of class Game and start the Game
			controller = new Controller();
			view = new View();
			controller.setView(view);
			view.setGame(controller);
			board = new Board();
			board.initialize();		
			controller.setBoard(board);
			view.setBoard(board);
			WelcomeDialog welcomeDialog = new WelcomeDialog(view.getShell(), controller);
			welcomeDialog.display();			
			view.initialize();
		} while (controller.isNewGame()); // When New Game button pressed
	}	
}
