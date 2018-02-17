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

// Jede SWT Widget Applikation benötigt folgende Packages:
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

/**
 * Template for displaying a resizeable chess board with a figure
 * When user clicks on a field, the background color changes and 
 * coordinates are printed on console
 * 
 * @since 24.01.2013
 */
public class ViewBoardResized {
	/** Each Gui needs a Diaplay */
	final Display display = new Display();
	/** Main window */
	final Shell shell  = new Shell(display);
	/** Brown color with RGB (Red, Green, Blue) */
	final Color COLOR_BROWN = new Color(display, 184, 134, 11); 
	/** Blue color with RGB (Red, Green, Blue) */
	final Color COLOR_BLUE = new Color(display, 65, 105, 225);
	/** Board size */
	public final int size = 8;
	/** Chess board fields will be displayed as 8x8 canvas array */
	final Canvas[][] canvasArray = new Canvas[size][size];
	/** Remember the first click column coordinate for displaying purposes */
	int firstClickColumn = -1;
	/** Remember the first row coordinate for displaying purposes */
	int firstClickRow = -1;
	/** Display letter around board */
	final Label letter = new Label(shell, SWT.NONE);

	/**
	 * Displaying a chess board with 8x8 canvas objects
	 * When you click on a canvas, the color changes and row and column are displayed
 	 */
	public void initialize() {
		// Chess board has 8 rows and columns
		// 8x8 canvas object display a chess board
		// Main window size in columns and rows
		shell.setSize(475, 550);

		// Listener for mouse clicks
		// Implemented as inner class
		MouseAdapter mouseAdapter = new MouseAdapter() {
			// MouseEvent knosw which canvas object is clicked
			public void mouseDown(MouseEvent e) {
				for (int column = 0; column < size; column++) {
					for (int row = 0; row < size; row++) {
						// When canvas identical with clicked object
						if (canvasArray[column][row]==e.getSource()) {
							// Change color
							// Print row and column
							firstClickColumn = column;
							firstClickRow = row;
							System.out.println("column="+column+", row="+row);
							draw();
						}
					}
				}
			}
		};

		// Initialize canvas array
		for (int column = 0; column < size; column++) {
			for (int row = 0; row < size; row++) {
				// Create new canvas for chess field with border
				canvasArray[column][row] = new Canvas(shell, SWT.BORDER);
				// Set color
				canvasArray[column][row].setBackground(COLOR_BLUE);
				// Add mouse event listener for each canvas
				canvasArray[column][row].addMouseListener(mouseAdapter);
			}
		}
		// Redraw whenever window size changes
		PaintListener paintListener = new PaintListener() {
			@Override
			public void paintControl(PaintEvent arg0) {
				draw();
			}
		};
		// Connect window size change listener with window
		shell.addPaintListener(paintListener);

		// Open shell so that it gets visible on screen
		shell.open();
		
		draw(); // Paint
		
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
		// Area has window sizes
		Rectangle area = shell.getClientArea();
		int height = area.height;
		int width = area.width;
		// Use min of height and with to calculate board size because square
		int min = Math.min(height, width);
		// Width of canvas has 8 chess fields plus border
		int canvasSize = min / (size + 4);
		// Scale needed for image size
		double pictureSizeInPixel = 200;
		double scale = ((double) canvasSize) / pictureSizeInPixel; // Pic has size 200
		// Paint canvas
		for (int column = 0; column < size; column++) {
			for (int row = 0; row < size; row++) {
				// Determine where canvas is displayed
				// Params: x, y, width, height
				canvasArray[column][row].setBounds(column * canvasSize + 2 * canvasSize,  
						(size - row) * canvasSize + canvasSize, canvasSize, canvasSize);
			}
		}
		
		// Display a label
		Font fontBorder = new Font(display, "Courier New", canvasSize / 2, SWT.BOLD);
		// Params x, y, width, height
		letter.setBounds((int) (2.25d * canvasSize), canvasSize, canvasSize, canvasSize); 
		letter.setFont(fontBorder);
		char myChar = 'A';
		letter.setText(""+ ++myChar);
		
		// Set background color
		for (int column = 0; column < size; column++) {
			for (int row = 0; row < size; row++) {
				// Show last click with different color
				if (firstClickColumn != -1 && firstClickRow != -1 && 
						firstClickColumn == column && firstClickRow == row) {
					canvasArray[column][row].setBackground(COLOR_BROWN);					
				} else {
					canvasArray[column][row].setBackground(COLOR_BLUE);					
				}
			}
		}
		// Image
		Image image = new Image(display, "./resources/200px-Chess_bdt45.svg.gif");
		image.setBackground(COLOR_BROWN);
		int widthNew = image.getBounds().width;
		// Resize image
		widthNew = (int) (image.getBounds().width * scale);
		int heightNew = (int) (image.getBounds().height * scale);
		Image imageScaled = new Image(display, image.getImageData().scaledTo(
				(int) (widthNew), (int) (heightNew)));
		// Paint image
		canvasArray[0][0].setBackgroundImage(imageScaled);
	}
	
	/**
	 * Create a SWT view and initialize it
	 * @param args not used
	 */
	public static void main(String[] args) {
		ViewBoardResized view = new ViewBoardResized();
		view.initialize();
	}
}