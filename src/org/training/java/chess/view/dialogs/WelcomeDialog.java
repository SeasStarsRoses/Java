package org.training.java.chess.view.dialogs;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.training.java.chess.chess.view.internationalization.Languages;
import org.training.java.chess.controller.Controller;
import org.training.java.chess.model.ai.Strength;
import org.training.java.chess.model.coordinate.InvalidCoordinateException;

/**
 * Welcome Dialog asks user if white and black are humans or computers
 * Same settings as last time dialog was called		
 * 
 * @author Peter Heide, pheide@t-online.de
 * @version 1
 * @since 17.02.2018
 */

public class WelcomeDialog {	
	/** White player is human or computer */
	private static boolean whiteHuman = true;
	/** Black player is human or computer */
	private static boolean blackHuman = true;
	/** White computer player plays Simple, Intermediate, or Expert */
	private static Strength whiteComputerStrength = Strength.STRONG;
	/** Black computer player plays Simple, Intermediate, or Expert */
	private static Strength blackComputerStrength = Strength.STRONG;
	
	/** Controller in Model View Controller */
	private Controller controller; 

	/** Dialog shell*/
	final Shell dialog;

	/**
	 * Constructor needs shell from view
	 * @param shell from view
	 * @param controller for callback information when dialog done
	 */
	public WelcomeDialog(Shell shell, Controller controller) {
		dialog = new Shell(shell, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		this.controller = controller;
	}

	/**
	 * Display the Welcome Dialog
	 */
	public void display() {
		// Open dialog
		// Size is relative to shell, location is relative to screen
		dialog.setSize(10, 10);
		dialog.setText(Languages.getValue(Languages.WELCOME));
		RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
		rowLayout.marginLeft = 10;
		rowLayout.marginTop = 10;
		dialog.setLayout(rowLayout);
		// Please choose label
		final Label choose = new Label(dialog, SWT.NONE);
		choose.setBounds(50, 50, 50, 50);		
		choose.setText(Languages.getValue(Languages.CHOOSE_PLAYER_TYPE));

		final Composite whiteComposite = new Composite(dialog, 0);
		final Label labelWhiteColor = new Label(whiteComposite, SWT.NONE);
		final Button buttonWhiteHuman = new Button(whiteComposite, SWT.RADIO);
		final Button buttonWhiteComputer = new Button(whiteComposite, SWT.RADIO);
		final Label labelWhiteAI = new Label(whiteComposite, SWT.VERTICAL);
		final Combo comboWhite = new Combo(whiteComposite, SWT.NONE);
		displayCompositeHelper(whiteComposite, labelWhiteColor, buttonWhiteHuman, buttonWhiteComputer, labelWhiteAI, comboWhite, true);
		
		// Black composite with human and computer radio buttons
		final Composite blackComposite = new Composite(dialog, 1);
		final Label labelBlackColor = new Label(blackComposite, SWT.NONE);
		final Button buttonBlackHuman = new Button(blackComposite, SWT.RADIO);
		final Button buttonBlackComputer = new Button(blackComposite, SWT.RADIO);
		final Label labelBlackAI = new Label(blackComposite, SWT.VERTICAL);
		final Combo comboBlack = new Combo(blackComposite, SWT.NONE);
		displayCompositeHelper(blackComposite, labelBlackColor, buttonBlackHuman, buttonBlackComputer, labelBlackAI, comboBlack, false);
		
		// Composite OK and Cancel Buttons
		final Composite buttons = new Composite(dialog, 2);
		FormLayout formLayout = new FormLayout ();
		formLayout.marginWidth = 10;
		formLayout.marginHeight = 10;
		formLayout.marginLeft = 100;
		formLayout.spacing = 10;
		buttons.setLayout(formLayout);		
		final Button cancel = new Button(buttons, SWT.PUSH);
		cancel.setText(Languages.getValue(Languages.CANCEL));
		cancel.setFocus();	
		FormData data = new FormData();
		data.width = 60;
		data.right = new FormAttachment(100, 0);
		data.bottom = new FormAttachment(100, 0);
		cancel.setLayoutData(data);		
		// Lambda Expression for Button Click method
		cancel.addListener(SWT.Selection, event -> { dialog.dispose(); } );
		
		final Button ok = new Button(buttons, SWT.PUSH);
		ok.setText(Languages.getValue(Languages.OK));
		data = new FormData();
		data.width = 60;
		data.right = new FormAttachment(cancel, 0, SWT.DEFAULT);
		data.bottom = new FormAttachment(100, 0);
		ok.setLayoutData(data);
		dialog.setDefaultButton(ok);
		// Lambda Expression for Button Click method
		ok.addListener(SWT.Selection, event -> { 
			whiteHuman = buttonWhiteHuman.getSelection();
			blackHuman = buttonBlackHuman.getSelection();
			String strWhiteComputerStrength = comboWhite.getText();
			String strBlackComputerStrength = comboBlack.getText();
			for (Strength strength : Strength.values()) {
				String strengthValue = Languages.getValue(strength.toString());
				if (strengthValue.equals(strWhiteComputerStrength)) {
					whiteComputerStrength = strength;
				}
				if (strengthValue.equals(strBlackComputerStrength)) {
					blackComputerStrength = strength;
				}
			}
			dialog.dispose();
			try {
				controller.welcomeDialogCallback(whiteHuman, blackHuman);
			} catch (InvalidCoordinateException e) {
				e.printStackTrace();
				System.exit(-1);
			}
		} );

		// Open dialog
		dialog.pack();
		dialog.open();
	}

	/**
	 * Display Composite for white or black to set Human/Computer and Strength
	 * @param composite the composite
	 * @param labelColor displays White or Black
	 * @param buttonHuman radio button for human
	 * @param buttonComputer radio button for computer
	 * @param labelAI displays Artificial Intelligence
	 * @param combo with strength Beginner, Intermediate, or expert
	 * @param white is White or Black
	 */
	private void displayCompositeHelper(final Composite composite, final Label labelColor, 
			final Button buttonHuman, final Button buttonComputer, final Label labelAI, final Combo combo, Boolean white) {
		final RowLayout layout = new RowLayout(SWT.HORIZONTAL);
		layout.marginTop = 10;
		layout.marginLeft = 10;
		layout.marginRight = 10;
		layout.pack = false;
		composite.setLayout(layout);
		buttonHuman.setText(Languages.getValue(Languages.HUMAN));
		// Make all widgets same size
		buttonComputer.setText(Languages.getValue(Languages.COMPUTER));
		// Same settings as last time dialog was called
		// White composite with human and computer radio buttons
		labelAI.setText(Languages.getValue(Languages.ARTIFICIAL_INTELLIGENCE));
		// SIMPLE, INTERMEDIATE, STRONG
		Strength[] strenghs = Strength.values();
		// Select same strength as last time
		int i = 0;
		for (Strength strength : strenghs) {
			String strengthString = strength.toString();
			String strengthValue = Languages.getValue(strengthString);			
			combo.add(strengthValue);
			System.out.println("i = " + i);
			System.out.println("white = " + white);
			System.out.println("strengthString = " + strengthString);
			System.out.println("whiteComputerStrength = " + whiteComputerStrength);
			if (i==0) {
				combo.select(0);				
			} else if ((white && strengthString.equals(whiteComputerStrength.toString())) ||
				  (!white && strengthString.equals(blackComputerStrength.toString()))) {
				System.out.println("Yes");
				combo.select(i);
			}
			i++;
		}
		if (white) {
			labelColor.setText(Languages.getValue(Languages.WHITE));			
			buttonHuman.setSelection(whiteHuman);
			buttonComputer.setSelection(!whiteHuman);
			labelAI.setVisible(!whiteHuman);
			combo.setVisible(!whiteHuman);
		} else {
			labelColor.setText(Languages.getValue(Languages.BLACK));						
			buttonHuman.setSelection(blackHuman);
			buttonComputer.setSelection(!blackHuman);			
			labelAI.setVisible(!blackHuman);
			combo.setVisible(!blackHuman);
		}
		
		buttonHuman.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				combo.setVisible(false);
				labelAI.setVisible(false);
			}
		});
		buttonComputer.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				combo.setVisible(true);
				labelAI.setVisible(true);
			}
		});		
	}
	
	/**
	 * Caller needs information if dialog is disposed (done)
	 * @return dialog disposed
	 */
	public boolean isDisposed() {
		return dialog.isDisposed();
	}

	/**
	 * Getter for Strength, there is not setter
	 * @return the whiteComputerStrength
	 */
	public static Strength getWhiteComputerStrength() {
		return whiteComputerStrength;
	}

	/**
	 * Getter for Strength, there is not setter
	 * @return the blackComputerStrength
	 */
	public static Strength getBlackComputerStrength() {
		return blackComputerStrength;
	}	
}
