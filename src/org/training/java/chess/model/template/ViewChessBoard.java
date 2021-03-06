package org.training.java.chess.model.template;


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

// Jede SWT Widget Applikation ben�tigt folgende Packages:
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

/**
 * Template for displaying a canvas with a color
 * @author Peter Heide, pheide@t-online.de
 * @version 1
 * @since 17.02.2018
 */
public class ViewChessBoard {
	// Each Gui needs a Diaplay
	final Display display = new Display();
	// Widgets use Shells und Displays as Container:
	final Shell shell = new Shell(display);
	// Colors with RGB (Red, Green, Blue)
	final Color COLOR_DARK = new Color(display, 184, 134, 11); 
	final Color COLOR_BLUE = new Color(display, 65, 105, 225);
	/** Board size */
	public final int size = 8;
	final Canvas[][] canvasArray = new Canvas[size][size];
	// Remember the first click koordinate for displaying purposes
	int firstClickColumn = -1;
	int firstClickRow = -1;
	/** Zur Anzeige von Zeichen wie A bis H */
	Label letter = new Label(shell, SWT.NONE);

	/**
	 * Displaying a chess board with 8x8 canvas objects
	 * When you click on a canvas, the color changes and row and column are displayed
 	 */
	public void initialize() {
		// Chess board has 8 rows and columns
		// width of canvas
		int width = 50;
		// 8x8 canvas object display a chess board
		// Main window size in columns and rows
		shell.setSize(475, 550);

		// Listener for Mouse Klicks
		// Implemented as inner class
		MouseAdapter mouseAdapter = new MouseAdapter() {
			// MouseEvent knosw which canvas object is clicked
			public void mouseDown(MouseEvent e) {
				for (int column = 0; column < size; column++) {
					for (int row = 0; row < size; row++) {
						// When canvas identical with clicked object
						if (canvasArray[column][row]==e.getSource()) {
							// Change color
							// Print rows and column
							firstClickColumn = column;
							firstClickRow = row;
						}
					}
				}
				draw();
			}
		};

		for (int column = 0; column < size; column++) {
			for (int row = 0; row < size; row++) {
				// Create new canvas for chess field with border
				canvasArray[column][row] = new Canvas(shell, SWT.BORDER);
				// Determine where canvas is displayed
				canvasArray[column][row].setBounds(column * width + 25,  
						(size - row) * width + 25, width, width);
				// Set color
				// canvasArray[column][row].setBackground(COLOR_BLUE);
				// Add mouse event listener for each canvas
				canvasArray[column][row].addMouseListener(mouseAdapter);
			}
		}
		draw(); // Paint

		 // Open shell so that it gets visible on screen
		shell.open();
		// Loop as long as shell is alive 
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				// Let's sleep a while...
				display.sleep();
		}
		// Close display
		display.dispose();
	}
	
	/**
	 * Paint the chess board on the screen
	 */
	void draw() {
		// Display a label
		Font fontBorder = new Font(display, "Courier New", 20, SWT.BOLD);
		letter.setBounds(40, 30, 45, 45); 
		letter.setFont(fontBorder);
		letter.setText("A");
		
		// Set background color
		for (int column = 0; column < size; column++) {
			for (int row = 0; row < size; row++) {
				// Show last click with different color
				if (firstClickColumn != -1 && firstClickRow != -1 && 
						firstClickColumn == column && firstClickRow == row) {
					canvasArray[column][row].setBackground(COLOR_DARK);					
				} else {
					canvasArray[column][row].setBackground(COLOR_BLUE);					
				}
			}
		}
	}
	
	/**
	 * Create a SWT view and initialize it
	 * @param args not used
	 */
	public static void main(String[] args) {
		ViewChessBoard view = new ViewChessBoard();
		view.initialize();
	}
	
}