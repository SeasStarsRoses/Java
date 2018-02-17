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
package org.training.java.chess.model.template;

/*
 * GridLayout snippet: align widgets in a GridLayout
 * 
 * For a list of all SWT example snippets see
 * http://www.eclipse.org/swt/snippets/
 * 
 * @since 3.0
 */
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class ViewGridLayout {


	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		GridLayout layout = new GridLayout(2, true);
		shell.setLayout(layout);
		
		Button b = null;
		for (int i=0; i<4; i++) {
			b = new Button(shell, SWT.BORDER);
			b.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
			Image image = new Image(display, "C:\\AZ_DATEN\\SAVI\\JavaOOBasisPraxis\\BattleChess\\resources\\red.gif");
			b.setImage(image);
		}

		shell.setSize(400, 400);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		
		display.dispose();
	}
}