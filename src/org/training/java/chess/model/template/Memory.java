/*******************************************************************************
 * Copyright (c) 2000, 2015 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.training.java.chess.model.template;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * Memory game
 * 
 * @author Peter Heide, pheide@t-online.de
 */
public class Memory {
	public static final int FIELDS = 2;
	public static final int SIZE = 100;
	public static boolean firstClick = true;
	public static int firstRowClicked = -1;
	public static int firstColumnClicked = -1;
	public static int secondRowClicked = -1;
	public static int secondColumnClicked = -1;
	public static String[][] text = new String[SIZE][SIZE];

	public static void main(String args[]) {
		Display display = new Display();
		Shell shell = new Shell(display);
		Color COLOR_BLACK = new Color(display, 0, 0, 0);
		Color COLOR_GREEN = new Color(display, 0, 255, 0);
		Color COLOR_RED = new Color(display, 255, 0, 0);
		Color COLOR_WHITE = new Color(display, 255, 255, 255);
		Color COLOR_YELLOW = new Color(display, 255, 255, 0);
		final int SHELL_SIZE = FIELDS * SIZE + SIZE / 2;
		boolean[][] clicked = new boolean[SIZE][SIZE];
		Color[][] color = new Color[SIZE][SIZE];

		shell.setSize(SHELL_SIZE, SHELL_SIZE);
		
		for (int column = 0; column < FIELDS; column++) {
			for (int row = 0; row < FIELDS; row++) {
				text[column][row] = "";
				if (row == column) {
					color[column][row] = COLOR_YELLOW;
				} else {
					color[column][row] = COLOR_WHITE;
				}
			}
		}
			
		shell.addMouseListener(new MouseListener() {
			@Override
			public void mouseUp(MouseEvent mouseEvent) {
				int columnClick = mouseEvent.x / SIZE;
				int rowClick = mouseEvent.y / SIZE;
				if (color[columnClick][rowClick] == COLOR_GREEN) {
					return;
				}
				clicked[columnClick][rowClick] = true;
				if (firstClick) {
					firstColumnClicked = columnClick;
					firstRowClicked = rowClick;
					setText(columnClick, rowClick);
				for (int column = 0; column < FIELDS; column++) {
						for (int row = 0; row < FIELDS; row++) {
							if (color[column][row] == COLOR_RED) {
								if (row == column) {
									color[column][row] = COLOR_YELLOW;
								} else {
									color[column][row] = COLOR_WHITE;
								}
							}
						}
					}

				} else {
					secondColumnClicked = columnClick;
					secondRowClicked = rowClick;
					setText(columnClick, rowClick);
					if (firstColumnClicked == 0 && firstRowClicked == 0 && secondColumnClicked == 1 && secondRowClicked == 1 ||
						firstColumnClicked == 1 && firstRowClicked == 1 && secondColumnClicked == 0 && secondRowClicked == 0) {
						color[0][0] = COLOR_GREEN;
						color[1][1] = COLOR_GREEN;
					} else if (firstColumnClicked == 0 && firstRowClicked == 1 && secondColumnClicked == 1 && secondRowClicked == 0 || 
							   firstColumnClicked == 1 && firstRowClicked == 0 && secondColumnClicked == 0 && secondRowClicked == 1) {
						color[0][1] = COLOR_GREEN;
						color[1][0] = COLOR_GREEN;
					} else {
						color[firstColumnClicked][firstRowClicked] = COLOR_RED;
						color[secondColumnClicked][secondRowClicked] = COLOR_RED;
					}
				}
				firstClick = !firstClick;
				shell.redraw();
			}

			@Override
			public void mouseDown(MouseEvent mouseEvent) {
			}

			@Override
			public void mouseDoubleClick(MouseEvent mouseEvent) {
			}
		});

		shell.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent event) {
				for (int column = 0; column < FIELDS; column++) {
					for (int row = 0; row < FIELDS; row++) {
						event.gc.setBackground(color[column][row]);
						event.gc.fillRectangle(SIZE * column, SIZE * row, SIZE, SIZE);
						event.gc.setForeground(COLOR_BLACK);
						event.gc.drawText(text[column][row], SIZE * column, SIZE * row, SWT.DRAW_TRANSPARENT);
					}
				}
			}
		});

		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
	
	public static void setText(int column, int row) {
		if (column == 0 && row == 0) {
			text[0][0] = "Mouse";
		}
		else if (column == 0 && row == 1) {
			text[0][1] = "House";
		}
		else if (column == 1 && row == 0) {
			text[1][0] = "Haus";
		}
		else {
			text[1][1] = "Maus";
		}						 		
	}
}
