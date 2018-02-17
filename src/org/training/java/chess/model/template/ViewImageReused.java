package org.training.java.chess.model.template;


import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.*;

/**
 * Shows how one Image will be reused with white and black background 
 * @author Rüdiger Straub 
 *
 */
public class ViewImageReused {
	public static int canvasCounter = 1;

	public ViewImageReused() {

	}

	public static void main(String args[]) {
		
		new ViewImageReused();
		int size = 100;

		Display display = new Display();
		Shell shell = new Shell(display);
		
		// Init der farben 
		final Color COLOR1 = new Color(display, 255, 0, 0);
		final Color COLOR2 = new Color(display, 0, 255, 0);
		final Color COLOR3 = new Color(display, 0, 0, 255);
		
		// Init Bild
		ImageData imgData = new ImageData("./resources/200px-Chess_bdt45.svg.gif");
		// Image ist final
		final Image backgroundImage = new Image(display, imgData.scaledTo(size, size));

		// 3 Canvasobjekte erzeugen
		Canvas canvas1 = new Canvas(shell, SWT.BORDER);
		canvas1.setBackground(COLOR1);
		canvas1.setBounds(0, 0, size, size);

		Canvas canvas2 = new Canvas(shell, SWT.BORDER);
		canvas2.setBackground(COLOR2);
		canvas2.setBounds(100, 0, size, size);

		Canvas canvas3 = new Canvas(shell, SWT.BORDER);
		canvas3.setBackground(COLOR3);
		canvas3.setBounds(200, 0, size, size);

		/* * * * * * * * * *
		 * * * LISTENER  * * 
		 * * * * * * * * * */

		// PaintListener für canvas 1
		canvas1.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				if (e.getSource() instanceof Canvas) {
					Canvas can = (Canvas) e.getSource();
					can.setBackground(COLOR1);
					e.gc.drawImage(backgroundImage, 0, 0);
				}
			}
		});
		// PaintListener für canvas 2
		canvas2.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				if (e.getSource() instanceof Canvas) {
					Canvas can = (Canvas) e.getSource();
					can.setBackground(COLOR2);
					e.gc.drawImage(backgroundImage, 0, 0);
				}

			}
		});
		// PaintListener für canvas 2
				canvas3.addPaintListener(new PaintListener() {
					public void paintControl(PaintEvent e) {
						if (e.getSource() instanceof Canvas) {
							Canvas can = (Canvas) e.getSource();
							can.setBackground(COLOR3);
							e.gc.drawImage(backgroundImage, 0, 0);
						}

					}
				});
		// Canvas 3 hat keinen Listener
		
		canvas1.setBounds(0, 0, size, size);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
}
