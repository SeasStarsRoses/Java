/*******************************************************************************
 * Copyright (c) 2000, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.training.java.chess.model.template;

/* 
 * example snippet: Hello World
 *
 * For a list of all SWT example snippets see
 * http://www.eclipse.org/swt/snippets/
 */

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

/**
 * Zeichnet das Schachbrett mittels SWT
 * @author Peter Heide, pheide@t-online.de
 * @version 2.0
 * 
 */
public class ViewBoard2Fields1Figure2Field {
	/** Verbindet Java mit OS */
	private Display display = new Display();
	/** Hauptfenster */
	private Shell shell = new Shell(display);
	/** Schachbrett */
	private final Canvas[] array = new Canvas[size];
	/** Farbe Rot mit Systemfarben*/
	private Color COLOR_RED = display.getSystemColor(SWT.COLOR_RED); 
	/** Farbe grün mit Display */
	private Color COLOR_GREEN = new Color(display, 0, 255, 0);
	/** Größe des Schachfeldes */
	public static final int size = 2;
	/** Koordinate wo Anwender hingeklickt hat */
	public int coordinateKlicked = 0;
	/** Initialisiert View einmalig */
	public void init() {
		/* Shellgröße setzen */ 
		shell.setSize(500, 700);

		for (int i = 0; i < size; i++) {
			array[i] = new Canvas(shell, SWT.BORDER);
			array[i].setBounds(i * 100, 0, 100, 100);
			array[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseDown(MouseEvent e) {
					Canvas canvas = (Canvas) e.getSource();
					for (int i = 0; i < size; i++) {
						if (array[i] == canvas) {
							System.out.println("Sie haben auf " + i
									+ " geklickt");
							coordinateKlicked = i;
						}
					}
					draw();
				}
			});
			if (i == 0) {
				array[i].setBackground(COLOR_GREEN);
			} else {
				array[i].setBackground(COLOR_RED);
			}
		}

		Label lblHelloWorld = new Label(shell, SWT.NONE);
		lblHelloWorld.setBounds(0, 200, 216, 20);
		lblHelloWorld.setText("Hello world");

		Button btnKlickMich = new Button(shell, SWT.NONE);
		btnKlickMich.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Danke fürs Klicken");
			}
		});
		btnKlickMich.setBounds(0, 150, 90, 30);
		btnKlickMich.setText("Klick mich");
		shell.open();

		draw();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

	/** Zeichnet die View immer wieder */ 
	public void draw() {
		for (int i = 0; i < size; i++) {
			Image imageOld = array[i].getBackgroundImage();
			if (imageOld != null) {
				imageOld.dispose();
			}
			array[i].setBackgroundImage(null);
			array[i].setBackground(null);
			if (coordinateKlicked == i) {
				String fileName = ".\\resources\\200px-Chess_bdt45.svg.gif";
				Image image = new Image(display, fileName);
				Image imageScaled = new Image(display, image.getImageData()
						.scaledTo(100, 100));
				if ((i % 2) == 0) {
					imageScaled.setBackground(COLOR_GREEN);
				} else {
					imageScaled.setBackground(COLOR_RED);
				}
				array[i].setBackgroundImage(imageScaled);
			} else {
				if ((i % 2) == 0) {
					array[i].setBackground(COLOR_GREEN);
				} else {
					array[i].setBackground(COLOR_RED);
				}				
			}
		}
	}

	/** Started das Programm */ 
	public static void main(String[] args) {
		ViewBoard2Fields1Figure2Field view = new ViewBoard2Fields1Figure2Field();
		view.init();
	}
}
