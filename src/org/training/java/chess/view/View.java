package org.training.java.chess.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
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
import org.training.java.chess.model.figures.King;
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

	/** Image Factory */
	private ImageFactory imageFactory = new ImageFactory(display);

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
	Label[][] letters = new Label[2][Board.size];
	/** Display letter around board */
	Label[][] numbers = new Label[2][Board.size];

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

	/** Take a move back */
	Button backButton;
	/** New game button */
	Button newGameButton = new Button(shell, SWT.PUSH);

	/** Just temporarily for testing */
	public static int canvasCounter = 0;

	/**
	 * Plays a Game
	 * 
	 * @throws InvalidCoordinateException
	 *             when BoardCoordinate cannot be created
	 */
	public void initialize() throws InvalidCoordinateException {
		shell.setSize(780, 700);
		shell.setLocation(0, 0);
		// Back Button
		backButton = new Button(shell, SWT.PUSH);
		backButton.setText("Back");
		// Lambda Expression for Button Click method
		backButton.addListener(SWT.Selection, event -> {
			try {
				controller.takeBackMove();
				draw();
			} catch (InvalidCoordinateException e1) {
				e1.printStackTrace();
				System.exit(-1);
			}
		});
		
		// New Game Button
		newGameButton = new Button(shell, SWT.PUSH);
		newGameButton.setText(Languages.getValue(Languages.NEW_GAME));
		// Lambda Expression for Button Click method
		newGameButton.addListener(SWT.Selection, event -> {
			controller.setNewGame(true);
			shell.dispose();
		});

		// Menu
		Menu bar = new Menu(shell, SWT.BAR);
		shell.setMenuBar(bar);
		MenuItem fileItem = new MenuItem(bar, SWT.CASCADE);
		fileItem.setText("&Help");
		Menu submenu = new Menu(shell, SWT.DROP_DOWN);
		fileItem.setMenu(submenu);
		MenuItem item = new MenuItem(submenu, SWT.PUSH);
		item.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				AboutDialog aboutDialog = new AboutDialog(shell, View.this);
				aboutDialog.display();
			}
		});
		item.setText("&About\tCtrl+A");
		item.setAccelerator(SWT.MOD1 + 'A');

		for (int column = 0; column < Board.size; column++) {
			for (int i = 0; i < 2; i++) {
				letters[i][column] = new Label(shell, SWT.NONE);
				letters[i][column].setBackground(ORANGE);
				letters[i][column].setForeground(WHITE);
				numbers[i][column] = new Label(shell, SWT.NONE);
				numbers[i][column].setBackground(ORANGE);
				numbers[i][column].setForeground(WHITE);
			}
		}

		shell.addMouseListener(new MouseListener() {

			@Override
			public void mouseUp(MouseEvent mouseEvent) {
				// Area has window sizes
				Rectangle area = shell.getClientArea();
				int height = area.height;
				int width = area.width;
				// Use min of height and with to calculate board size because square
				int min = Math.min(height, width);
				// Width of canvas has 8 chess fields plus border
				int canvasSize = min / (Board.size + 4);

				int columnPixel = mouseEvent.x;
				int rowPixel = mouseEvent.y;
				int column = 0;
				int row = 0;

				if (canvasSize > 0) {
					column = (columnPixel - 2 * canvasSize) / canvasSize;
					row = Board.size - ((rowPixel - canvasSize) / canvasSize);
				}

				if (BoardCoordinate.valid(column, row)) {
					System.out.println("You clicked column=" + column + ", row=" + row);
					BoardCoordinate coordinate;
					try {
						coordinate = new BoardCoordinate(column, row);
						controller.coordinateClicked(coordinate);
					} catch (InvalidCoordinateException e) {
						e.printStackTrace();
					}
				}
			}

			@Override
			public void mouseDown(MouseEvent mouseEvent) {
			}

			@Override
			public void mouseDoubleClick(MouseEvent mouseEvent) {
			}
		});

		// Redraw whenever window size changes
		// For Paint LIsterners see
		// https://www.eclipse.org/articles/Article-SWT-graphics/SWT_graphics.html
		shell.addPaintListener(new PaintListener() {
			// @Override
			/**
			 * Paint the chess board Param e PaintEvent, used to draw an image or a
			 * background
			 */
			public void paintControl(PaintEvent e) {
				// en passant coordinate
				BoardCoordinate enPassant = board.getEnPassant();

				Figure figure = new King(true);
				Image image = imageFactory.createImage(figure, display, 100);

				// Area has window sizes
				Rectangle area = shell.getClientArea();
				int height = area.height;
				int width = area.width;
				// Use min of height and with to calculate board size because square
				int min = Math.min(height, width);
				// Width of canvas has 8 chess fields plus border
				int canvasSize = min / (Board.size + 4);

				System.out.println(canvasCounter++);
				boolean whiteField = false; // Is field white or black?
				// column, row, width, height
				backButton.setBounds((int) ((2.25d + Board.size + 1) * canvasSize),
						canvasSize + (Board.size - 2) * canvasSize, canvasSize * 2, canvasSize);
				newGameButton.setBounds((int) ((2.25d + Board.size + 1) * canvasSize),
						canvasSize + (Board.size - 5) * canvasSize, canvasSize * 2, canvasSize);

				// List of all coordinates where a figure can move
				List<BoardCoordinate> toCoordinates = new ArrayList<BoardCoordinate>();
				BoardCoordinate firstSelection = controller.getFirstSelection();
				try {
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

					for (int column = 0; column < Board.size; column++) {
						for (int row = 0; row < Board.size; row++) {
							BoardCoordinate coordinate = new BoardCoordinate(column, row);
							Color color = null;
							// Select background colors
							if (enPassant != null && board.getEnPassant().equals(coordinate)) {
								color = BLUE;
							} else if (firstSelection != null && firstSelection.equals(coordinate)) {
								color = YELLOW;
							} else if (firstSelection != null && toCoordinates != null && toCoordinates.contains(coordinate)) {
								 color = GREEN;
							} else	if (toCoordinates.contains(coordinate)) {
								 color = RED;
   						    } 														
							else if (whiteField) {
								// Paint background for white fields
								color = BROWN_LIGHT;
							} else {
								// Paint background for black field
								color = BROWN_DARK;
							}
							int columnPixel = column * canvasSize + 2 * canvasSize;
							int rowPixel = (Board.size - row) * canvasSize + canvasSize;
							e.gc.setBackground(color);
							e.gc.fillRectangle(columnPixel, rowPixel, canvasSize, canvasSize);
							coordinate = new BoardCoordinate(column, row);
							figure = board.getFigure(coordinate);
							if (figure == null) {								
							} else {
								image = imageFactory.createImage(figure, display, canvasSize);
								e.gc.drawImage(image, columnPixel, rowPixel);
							}
							whiteField = !whiteField;
						}
						whiteField = !whiteField;
					}

					if (board.isGameOver()) {
						labelGameOver.setVisible(true);
						Font fontGameOver = new Font(display, "Courier New", 20, SWT.BOLD);
						labelGameOver.setBounds(5, 20, 12 * canvasSize, canvasSize);
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
						}
						;
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
				} catch (InvalidCoordinateException e1) {
					e1.printStackTrace();
				}
				try {
					Thread.sleep(500); // Sleep half a second so that painting is not done too often
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});

		shell.setMaximized(true); // to see a big board
		draw();
		Logger.log(board);

		/*
		 * A shell must be opened to be visible on screen In an event loop it can be
		 * controlled of shell still active
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
	 * There is only one image factory in town, so make it available for dialogs who
	 * needs it
	 * 
	 * @return imageFactory
	 */
	public ImageFactory getImageFactory() {
		return imageFactory;
	}

	/**
	 * Displays the Board and the figures on the GUI
	 * 
	 * @throws InvalidCoordinateException
	 *             when BoardCoordinate cannot be created
	 */
	public void draw() throws InvalidCoordinateException {
		shell.redraw(); // force shell to redraw
	}

	/**
	 * @return the display, especially because Images needs them in constructor
	 */
	public Display getDisplay() {
		return display;
	}

	/**
	 * Getter for Board
	 * 
	 * @return the board
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * Setter for game
	 * 
	 * @param game
	 *            is controller in model view controller paradigm
	 */
	public void setGame(Controller game) {
		this.controller = game;
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
	 * @return the shell
	 */
	public Shell getShell() {
		return shell;
	}
}