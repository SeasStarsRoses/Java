package org.training.java.chess.view.dialogs;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.training.java.chess.chess.view.internationalization.Languages;
import org.training.java.chess.controller.Controller;
import org.training.java.chess.model.coordinate.BoardCoordinate;
import org.training.java.chess.model.coordinate.InvalidCoordinateException;
import org.training.java.chess.model.figures.Figure;
import org.training.java.chess.model.player.chess.view.ImageFactory;
import org.training.java.chess.model.player.chess.view.View;

/**
 * Figure Dialog displays Pawn Exchange Figures so that human player can choose
 * 
 * @author Peter Heide, pheide@t-online.de
 * @version 1
 * @since 17.02.2018
 */
public class PawnExchangeFigureDialog {
	
	/** Shell from view */
	private Shell shell;

	/** View */
	private View view;

	/**
	 * Figure when a pawn is changed into another figure
	 */
//	public static String exchangeFigure;
	public static Class<? extends Figure> exchange;

	/**
	 * Constructor needs shell from view
	 * @param shell from view
	 * @param view with main window
	 */
	public PawnExchangeFigureDialog(Shell shell, View view) {
		this.shell = shell;
		this.view = view;
	}	
	
	
	/**
	 * Opens a dialog when pawn gets to other figure
	 * @param white figure color
	 * @param firstSelection first click
	 * @param secondSelection of figure
	 * @param figureToClass Class of figure on to field for move
	 * @param controller callback so that controller can replace old figure with new
	 */
	public void display(final boolean white, final BoardCoordinate firstSelection, 
		final BoardCoordinate secondSelection, final Class<? extends Figure> figureToClass,
		final Controller controller) {
		final int boardWidth = 50;
		final Shell dialog = new Shell(shell, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		Display display = shell.getDisplay();
		dialog.setText(Languages.getValue(Languages.CHOOSE_ONE));
		dialog.setSize(boardWidth * 4, boardWidth * 2 + 30);
		final Canvas figures[] = new Canvas[4];
		
		MouseAdapter mouseAdapter2 = new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				for (int x = 0; x < 4; x++) {
					if (figures[x] == e.getSource()) {
						switch (x) {
							case 0:
								exchange = org.training.java.chess.model.figures.Queen.class;
								break;
							case 1:
								exchange = org.training.java.chess.model.figures.Rook.class;
								break;
							case 2:
								exchange = org.training.java.chess.model.figures.Bishop.class;
								break;
							case 3:
								exchange = org.training.java.chess.model.figures.Knight.class;
						}
						dialog.dispose();
						try {
							controller.pawnExchangeCallback(exchange, firstSelection, secondSelection);
						} catch (InvalidCoordinateException e1) {
							e1.printStackTrace();
							System.exit(-1);
						}
					}
				}
			}
		};

		for (int i = 0; i < 4; i++) {
			figures[i] = new Canvas(dialog, SWT.BORDER);
			figures[i].setBounds(boardWidth * i, 0, boardWidth, boardWidth);
			figures[i].addMouseListener(mouseAdapter2);
		}

		Image image = ImageFactory.createImage(org.training.java.chess.model.figures.Queen.class, true, display, view.BROWN_LIGHT, boardWidth);
		figures[0].setBackgroundImage(image);
		image = ImageFactory.createImage(org.training.java.chess.model.figures.Rook.class, true, display, view.BROWN_LIGHT, boardWidth);
		figures[1].setBackgroundImage(image);
		image = ImageFactory.createImage(org.training.java.chess.model.figures.Bishop.class, true, display, view.BROWN_LIGHT, boardWidth);
		figures[2].setBackgroundImage(image);
		image = ImageFactory.createImage(org.training.java.chess.model.figures.Knight.class, true, display, view.BROWN_LIGHT, boardWidth);
		figures[3].setBackgroundImage(image);
		
		dialog.addMouseListener(mouseAdapter2);

		// Move the dialog to the center of the top level shell.
		Rectangle shellBounds = shell.getBounds();
		Point dialogSize = dialog.getSize();

		dialog.setLocation(shellBounds.x + (shellBounds.width - dialogSize.x) / 2, 
				shellBounds.y + (shellBounds.height - dialogSize.y) / 2);

		dialog.pack();
		dialog.open();
	}	
	

}
