package org.training.java.chess.model.player.chess.view;

/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

/*
 * GridLayout snippet: align widgets in a GridLayout
 * 
 * For a list of all SWT example snippets see
 * http://www.eclipse.org/swt/snippets/
 * 
 * @since 3.0
 */

// Jede SWT Widget Applikation benötigt folgende Packages:
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.training.java.chess.chess.view.internationalization.Languages;
import org.training.java.chess.controller.Controller;
import org.training.java.chess.game.Board;
import org.training.java.chess.game.Move;
import org.training.java.chess.model.coordinate.BoardCoordinate;
import org.training.java.chess.model.coordinate.InvalidCoordinateException;
import org.training.java.chess.model.figures.Figure;
import org.training.java.chess.model.highscore.HighScore;
import org.training.java.chess.model.highscore.HighScoreList;
import org.training.java.chess.model.logging.Logger;
import org.training.java.chess.view.dialogs.AboutDialog;

/**
 * Game plays a Chess Game
 * 
 * @author Peter Heide, pheide@t-online.de
 * @version 1
 * @since 17.02.2018
 */
public class View {		
	/** GUI Display */
	private Display display = new Display();
	
	/** SWT Shell */
	private Shell shell = new Shell(display);

	/** Canvas for each field */
	final Canvas[][] canvas = new Canvas[Board.size][Board.size];
	
	/** Chess board */
	private Board board;
	
	/** Black color */
	public Color BLACK = new Color(display, 0, 0, 0);
	/** Brown dark color */
	public Color BROWN_DARK = new Color(display, 153, 51, 0);
	/** Blue color */
	public Color BLUE = new Color(display, 0, 0, 255);
	/** Brown light color */
	public Color BROWN_LIGHT = new Color(display, 255, 192, 100);
	/** Green color */
	public Color GREEN = new Color(display, 0, 255, 0);
	/** Orange color */
	public Color ORANGE = new Color(display, 255, 137, 19);
	/** Red color */
	public Color RED = new Color(display, 255, 0, 0);
	/** White color */
	public Color WHITE = new Color(display, 255, 255, 255);
	/** Yellow color */
	public Color YELLOW = new Color(display, 255, 255, 0);
	
	/** Display letter around board */
	Label [][] letters = new Label[2][Board.size];
	/** Display letter around board */
	Label [][] numbers = new Label[2][Board.size];

	/** Show game over */
	Label labelGameOver = new Label(shell, SWT.CENTER);
		
	/**
	 * Move number starts with 1 and increases by one when white an black moved
	 */
	public int moveNumber = 1;

	/**
	 * Controller in model view controller paradigm
	 */
	private Controller controller;

	/**
	 * MouseAdapter to handle mouse clicks on canvas objects
	 */
	private MouseAdapter mouseAdapter;
	
	/** Take a move back */
	Button backButton;
	/** New game button */
	Button newGameButton = new Button(shell, SWT.PUSH);
	
	/**
	 * Plays a Game
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be created
	 */
	public void initialize() throws InvalidCoordinateException {
		shell.setSize(780, 700);
		shell.setLocation(0, 0);
		shell.setBackground(ORANGE);

		mouseAdapter = new MouseAdapter() {
			public void mouseDown(MouseEvent e) {
				try {
					for (int column = 0; column < Board.size; column++) {
						for (int row = 0; row < Board.size; row++) {
							if (canvas[column][row] == e.getSource()) {
								BoardCoordinate coordinate = null;
								coordinate = new BoardCoordinate(column, row);
								controller.coordinateClicked(coordinate);
							}
						}
					}
					draw();
				} catch (InvalidCoordinateException e1) {
					e1.printStackTrace();
					System.exit(-1);
				}
			}
		};

		// Back Button
		backButton = new Button(shell, SWT.PUSH);
		backButton.setText("Back");
		// Lambda Expression for Button Click method
		backButton.addListener( 
			SWT.Selection, 
			event -> { 
				try {
					if (board.isGameOver()) {
						addMouseListener();
					}
					controller.takeBackMove();
					draw();
				} catch (InvalidCoordinateException e1) {
					e1.printStackTrace();
					System.exit(-1);
				}
			} 
		);
				
		// New Game Button
		newGameButton = new Button(shell, SWT.PUSH);
		newGameButton.setText(Languages.getValue(Languages.NEW_GAME));
		// Lambda Expression for Button Click method
		newGameButton.addListener( SWT.Selection, event -> { controller.setNewGame(true); shell.dispose(); } );

		// Menu
		Menu bar = new Menu (shell, SWT.BAR);
		shell.setMenuBar (bar);
		MenuItem fileItem = new MenuItem (bar, SWT.CASCADE);
		fileItem.setText ("&Help");
		Menu submenu = new Menu (shell, SWT.DROP_DOWN);
		fileItem.setMenu (submenu);
		MenuItem item = new MenuItem (submenu, SWT.PUSH);
		item.addListener (SWT.Selection, new Listener () {
			public void handleEvent (Event e) {
				AboutDialog aboutDialog = new AboutDialog(shell, View.this);
				aboutDialog.display();
			}
		});
		item.setText ("&About\tCtrl+A");
		item.setAccelerator (SWT.MOD1 + 'A');
		
		for (int column = 0; column < Board.size; column++) {
			for (int i = 0; i < 2; i++) {
				letters[i][column] = new Label(shell, SWT.NONE);
				letters[i][column].setBackground(ORANGE);
				letters[i][column].setForeground(WHITE);				
				numbers[i][column] = new Label(shell, SWT.NONE);
				numbers[i][column].setBackground(ORANGE);
				numbers[i][column].setForeground(WHITE);				
			}
			for (int row = 0; row < Board.size; row++) {
				canvas[column][row] = new Canvas(shell, SWT.BORDER);
			}
		}
		addMouseListener();
		
		// Redraw whenever window size changes
		PaintListener paintListener = new PaintListener() {
			@Override
			public void paintControl(PaintEvent arg0) {
				try {
					draw();
				} catch (InvalidCoordinateException e) {
					e.printStackTrace();
					System.exit(-1);
				}
			}
		};
		// Connect window size change listener with window
		shell.addPaintListener(paintListener);
				
		shell.setMaximized(true); // to see a big board
		draw();
		Logger.log(board);

		/*
		 * A shell must be opened to be visible on screen
		 * In an event loop it can be controlled of shell still active
		 */
		shell.open();
		while (shell != null && !shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		if (display != null) {
		display.dispose();
		display = null;
		}
	}

	/**
	 * Displays the Board and the figures on the GUI
	 * @throws InvalidCoordinateException when BoardCoordinate cannot be created
	 */
	public void draw() throws InvalidCoordinateException {
		// en passant coordinate
		BoardCoordinate enPassant = board.getEnPassant();
		// Area has window sizes
		Rectangle area = shell.getClientArea();
		int height = area.height;
		int width = area.width;
		// Use min of height and with to calculate board size because square
		int min = Math.min(height, width);
		// Width of canvas has 8 chess fields plus border
		int canvasSize = min / (Board.size + 4);

		// List of all coordinates where a figure can move
		List<BoardCoordinate> toCoordinates = new ArrayList<BoardCoordinate>();  		
		BoardCoordinate firstSelection = controller.getFirstSelection();
		if (firstSelection != null) {
			Figure selectedFigure = board.getFigure(firstSelection);
			List<Move> moves = selectedFigure.getMoves(board, firstSelection, true);
			if (moves != null && !moves.isEmpty()) {
				for (Move move : moves) {
					if (move != null) {
						toCoordinates.add(move.getToCoord());
					}
				}				
			}
		} else {
			toCoordinates = board.moveCoordinates();
		}

		// Display a label
		Font fontBorder = new Font(display, "Courier New", canvasSize / 2, SWT.BOLD);
		// Params x, y, width, height

		for (int i = 0; i < 2; i++) {
			char firstLetter = 'A';
			int firstNumber = 8;
			for (int j=0; j < Board.size; j++) {
				Label letter = letters[i][j];
				letter.setBounds((int) (2.25d * canvasSize) + j * canvasSize,
						canvasSize + i * (Board.size + 1) * canvasSize,
						canvasSize, canvasSize);
				letter.setFont(fontBorder);
				letter.setText("" + firstLetter++);
				Label number = numbers[i][j];
				number.setBounds((int) (2.25d * canvasSize)
						+ (Board.size - (9 * i)) * canvasSize, canvasSize
						+ (j + 1) * canvasSize, (int) (canvasSize * 0.5),
						canvasSize);
				number.setFont(fontBorder);
				number.setText("" + firstNumber--);
			}
		}		
		
		// column, row, width, height
		backButton.setBounds((int) ((2.25d + Board.size + 1) * canvasSize),
				canvasSize + (Board.size - 2) * canvasSize, canvasSize * 2, canvasSize);
		newGameButton.setBounds((int) ((2.25d + Board.size + 1) * canvasSize),
				canvasSize + (Board.size - 5) * canvasSize, canvasSize * 2, canvasSize);
		boolean whiteField = true; // Paint field white or black
		for (int row = Board.size - 1; row >= 0; row--) {
			whiteField = !whiteField;
			for (int column = 0; column < 8; column++) {
				canvas[column][row].setBounds(column * canvasSize + 2 * canvasSize,  
						(Board.size - row) * canvasSize + canvasSize, canvasSize, canvasSize);
				whiteField = !whiteField;
				BoardCoordinate coordinate = new BoardCoordinate(column, row);
				Figure figure = board.getFigure(coordinate);
				if (figure == null) {
					// Reset Background
					Image imageOld = canvas[column][row].getBackgroundImage();
					if (imageOld != null) {
						imageOld.dispose();
					}
					canvas[column][row].setBackgroundImage(null);
					if (toCoordinates != null && toCoordinates.contains(coordinate)) {
					// 	Paint background where Figure can move
						canvas[column][row].setBackground(GREEN);
					} else if (enPassant!= null && board.getEnPassant().equals(coordinate))  {
						canvas[column][row].setBackground(BLUE);
					} else if (whiteField) {
					// 	Paint background for white fields
						canvas[column][row].setBackground(BROWN_LIGHT);
					} else {
					// 	Paint background for black field
						canvas[column][row].setBackground(BROWN_DARK);
					}
				} else {
					// Set figure with background color
					Image image = null;
					Color color = null;
					if (toCoordinates.contains(coordinate)) {
						color = RED;
					} else if (firstSelection != null && firstSelection.equals(coordinate)) {
						color = YELLOW;							
					} else if (toCoordinates != null && toCoordinates.contains(coordinate)) {
						color =  GREEN;
					} else if (whiteField) {
						color = BROWN_LIGHT;
					} else {
						color = BROWN_DARK;
					}
					
					Image imageOld = canvas[column][row].getBackgroundImage();
					if (imageOld != null) {
						imageOld.dispose();
					}
					image = ImageFactory.createImage(figure, display, color, canvasSize);
					canvas[column][row].setBackgroundImage(image);
				}
			}
		}
		if (board.isGameOver()) {
			for (int column = 0; column < Board.size; column++) {
				for (int row = 0; row < Board.size; row++) {
					canvas[column][row].removeMouseListener(mouseAdapter);
				}
			}		
			labelGameOver.setVisible(true);
			Font fontGameOver = new Font(display, "Courier New", 20, SWT.BOLD);
			labelGameOver.setForeground(WHITE);
			labelGameOver.setBackground(ORANGE);
			labelGameOver.setBounds(5, 20, 700, canvasSize);
			labelGameOver.setFont(fontGameOver);
			StringBuffer textGameOver = new StringBuffer("GAME OVER! "); 
			if (board.isDraw()) {
				textGameOver.append("Draw!");
			} else {
				if (board.isWhiteWinner()) {
					textGameOver.append("White");				
				} else {
					textGameOver.append("Black");								
				}
				textGameOver.append(" won in " + board.getMoveNumber() + " rounds");								
			};
			labelGameOver.setText(textGameOver.toString());
			// Write HighScore
			HighScoreList highScoreList = HighScoreList.getInstance();
			String winner = null;
			String looser = null;
			if (board.isWhiteWinner()) {
				winner = "White";
				looser = "Black";
			} else {
				winner = "Black";
				looser = "White";
			}
			int rounds = board.getMoveNumber();
			HighScore highScore = new HighScore(new Date(), winner, looser, rounds);
			highScoreList.addHighScore(highScore);
			System.out.println(highScoreList);
			HighScoreList.switchOff();
		} else {
			labelGameOver.setVisible(false);
		}

		
		if (board.getLastMove() == null) {
			backButton.setEnabled(false);
		} else {
			backButton.setEnabled(true);			
		}
	}
	
	/**
	 * @return the display, especially because Images needs them in constructor
	 */
	public Display getDisplay() {
		return display;
	}

	/**
	 * Getter for Board
	 * @return the board
	 */
	public Board getBoard() {
		return board;
	}
	
	/**
	 * Setter for game
	 * @param game is controller in model view controller paradigm
	 */
	public void setGame(Controller game) {
		this.controller = game;		
	}	
	
	/**
	 * Setter for board
	 * @param board
	 */
	public void setBoard(Board board) {
		this.board = board;		
	}

	/**
	 * @return the shell
	 */
	public Shell getShell() {
		return shell;
	}
	
	/**
	 * Add mouse listener to all canvas objects
	 */
	public void addMouseListener() {
		for (int column = 0; column < Board.size; column++) {
			for (int row = 0; row < Board.size; row++) {
				canvas[column][row].addMouseListener(mouseAdapter);
			}
		}		
	}
}