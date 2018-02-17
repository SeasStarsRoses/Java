/**
 * 
 */
package org.training.java.chess.view.dialogs;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.training.java.chess.chess.view.internationalization.Languages;
import org.training.java.chess.model.player.chess.view.ImageFactory;
import org.training.java.chess.model.player.chess.view.View;

/**
 * About Dialog displays copyright
 * 
 * @author Peter Heide, pheide@t-online.de
 * @version 1
 * @since 17.02.2018
 */
public class AboutDialog {
	/** Shell from view */
	private Shell shell;

	/** View */
	private View view;
	
	/**
	 * Constructor needs shell from view
	 * @param shell from view
	 * @param view with main window
	 */
	public AboutDialog(Shell shell, View view) {
		this.shell = shell;
		this.view = view;
	}	

	/**
	 * Opens a dialog when pawn gets to other figure
	 */
	public void display() {
		Display display = shell.getDisplay();
		final Shell dialog = new Shell(shell, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);

		dialog.setText(Languages.getValue(Languages.ABOUT));
		dialog.setSize(1250, 700);
		dialog.setLocation(0, 0);

		final Font font = new Font(display, "Arial", 14, SWT.BOLD);

		// Style WRAP is for wrapping the text
		final Label label = new Label(dialog, SWT.WRAP);
		label.setForeground(view.BLACK);
		label.setBounds(10, 250, 1230, 450);
		label.setFont(font);
		label.setText(
		   Languages.getValue(Languages.AUTHOR) + "\n\n" +
		   Languages.getValue(Languages.CHESS_PIECES) + "\n" +
		   Languages.getValue(Languages.LINK_CHESS_PIECES) + "\n\n"
	    );		
		
        dialog.pack();
	    dialog.open();
	}	
}
