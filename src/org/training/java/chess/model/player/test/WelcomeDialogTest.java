/*******************************************************************************

 * Copyright (c) 2000, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.training.java.chess.model.player.test;

/*
 * implement radio behavior for setSelection()
 *
 * For a list of all SWT example snippets see
 * http://www.eclipse.org/swt/snippets/
 * 
 * @since 3.2
 */

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

/**
 * WelcomeDialog Asks user if white and black player are humans or computers
 * 
 * @author Peter Heide, pheide@t-online.de
 * @version 1
 * @since 17.02.2018
 */
public class WelcomeDialogTest {
	final static Display display = new Display();
	final static Shell shell = new Shell(display);

	/**
	 * Display the Welcome Dialog
	 */
	public void displayWelcomeDialog() {
		
		// Open dialog
		final Shell dialog = new Shell(shell, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		dialog.setLocation(600, 500);
		dialog.setText("Welcome");
		dialog.setLayout(new RowLayout(SWT.VERTICAL));
		// Please choose label
		final Label choose = new Label(dialog, SWT.NONE);
		choose.setText("Please choose:");
		// White composite with human and computer radio buttons
		final Composite humanOrComputer = new Composite(dialog, 0);
		GridLayout gridLayout = new GridLayout(); 
		gridLayout.numColumns = 3;
		gridLayout.marginTop = 10;
		gridLayout.marginBottom = 10;
		gridLayout.marginHeight = 20;
		gridLayout.marginLeft = 20;
		gridLayout.marginRight = 20;
		gridLayout.horizontalSpacing = 10;
		gridLayout.horizontalSpacing = 20;
		humanOrComputer.setLayout(gridLayout);
		// Create Labels and Buttons
		final Label white = new Label(humanOrComputer, SWT.NONE);
		final Button whiteHuman = new Button(humanOrComputer, SWT.RADIO);
		final Button whiteComputer = new Button(humanOrComputer, SWT.RADIO);
		final Label black = new Label(humanOrComputer, SWT.NONE);
		final Button blackHuman = new Button(humanOrComputer, SWT.RADIO);
		final Button blackComputer = new Button(humanOrComputer, SWT.RADIO);

		white.setText("White");
				
		whiteHuman.setText("Human");
		whiteHuman.setSelection(true);
		whiteHuman.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				System.out.println("whiteHuman pressed");
		//		whiteIsHuman = false;
//				if (blackIsHuman) {
//					blackHuman.setSelection(true);
//				} else {
//					blackComputer.setSelection(true);
//				}
			}
		});

		whiteComputer.setText("Computer");
		black.setText("Black");
		blackHuman.setText("Human");
		blackHuman.setSelection(true);
		blackComputer.setText("Computer");
		// OK and Cancel Buttons
		final Composite buttons = new Composite(dialog, 1);
		FormLayout formLayout = new FormLayout ();
		formLayout.marginWidth = 10;
		formLayout.marginHeight = 10;
		formLayout.marginLeft = 100;
		formLayout.spacing = 10;
		buttons.setLayout (formLayout);
		final Button ok = new Button(buttons, SWT.PUSH);
		ok.setText("OK");
		ok.setFocus();
		dialog.setDefaultButton (ok);
		ok.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				System.out.println("OK pressed");
				if (whiteHuman.getSelection()) {
					System.out.println("White player is human");
				} else {
					System.out.println("White player is computer");
				}
				if (blackHuman.getSelection()) {
					System.out.println("Black player is human");
				} else {
					System.out.println("Black player is computer");
				}
				dialog.dispose();
			}
		});
		FormData data = new FormData ();
		data.width = 200;
		data.left = new FormAttachment (ok, 0, SWT.DEFAULT);
		data.right = new FormAttachment (100, 0);
		data.top = new FormAttachment (ok, 0, SWT.CENTER);
		data = new FormData ();
		data.width = 60;
		data.right = new FormAttachment (100, 0);
		data.bottom = new FormAttachment (100, 0);
		ok.setLayoutData (data);

		final Button cancel = new Button(buttons, SWT.PUSH);
		cancel.setText("Cancel");
		cancel.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				System.out.println("Cancel pressed");
				dialog.dispose();
			}
		});		
		data = new FormData ();
		data.width = 60;
		data.right = new FormAttachment (ok, 0, SWT.DEFAULT);
		data.bottom = new FormAttachment (100, 0);
		cancel.setLayoutData (data);
		// Test
//		final Composite buttons = new Composite(dialog, 1);
//		FormLayout formLayout = new FormLayout ();
//		formLayout.marginWidth = 10;
//		formLayout.marginHeight = 10;
//		formLayout.spacing = 10;
//		buttons.setLayout (formLayout);
//
//		Label label = new Label (buttons, SWT.NONE);
//		label.setText ("Type a String:");
//		FormData data = new FormData ();
//		label.setLayoutData (data);
//
//		Button cancel1 = new Button (buttons, SWT.PUSH);
//		cancel1.setText ("Cancel");
//		data = new FormData ();
//		data.width = 60;
//		data.right = new FormAttachment (100, 0);
//		data.bottom = new FormAttachment (100, 0);
//		cancel1.setLayoutData (data);
//		cancel1.addSelectionListener (new SelectionAdapter () {
//			public void widgetSelected (SelectionEvent e) {
//				System.out.println("User cancelled dialog");
//				dialog.close ();
//			}
//		});
//
//		final Text text = new Text (buttons, SWT.BORDER);
//		data = new FormData ();
//		data.width = 200;
//		data.left = new FormAttachment (label, 0, SWT.DEFAULT);
//		data.right = new FormAttachment (100, 0);
//		data.top = new FormAttachment (label, 0, SWT.CENTER);
//		data.bottom = new FormAttachment (cancel, 0, SWT.DEFAULT);
//		text.setLayoutData (data);
//
//		Button ok1 = new Button (buttons, SWT.PUSH);
//		ok1.setText ("OK");
//		data = new FormData ();
//		data.width = 60;
//		data.right = new FormAttachment (cancel1, 0, SWT.DEFAULT);
//		data.bottom = new FormAttachment (100, 0);
//		ok1.setLayoutData (data);
//		ok1.addSelectionListener (new SelectionAdapter () {
//			public void widgetSelected (SelectionEvent e) {
//				System.out.println ("User typed: " + text.getText ());
//				dialog.close ();
//			}
//		});
//
//		dialog.setDefaultButton (ok1);

		// Open dialog
		dialog.pack();
		dialog.open();
	}

	/**
	 * Display the main window so that WelcomeDialog can be on top
	 */
	public void displayMainWindow() {
		shell.setSize(500, 500);
		shell.setText("Main");
		shell.setLayout(new RowLayout(SWT.VERTICAL));
		Button button = new Button(shell, SWT.PUSH);
		button.setText("Display welcome dialog");
		button.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				System.out.println("Button pressed");
				displayWelcomeDialog();
			}
		});
		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

	/**
	 * Display Welcome Dialog of top of main window
	 * @param args not used
	 */
	public static void main(final String[] args) {
		WelcomeDialogTest welcomeDialog = new WelcomeDialogTest();
		welcomeDialog.displayMainWindow();
	}
}