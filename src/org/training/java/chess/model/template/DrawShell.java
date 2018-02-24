package org.training.java.chess.model.template;


import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * Shows how one Image will be reused with white and black background 
 * @author Peter Heide, pheide@t-online.de 
 *
 */
public class DrawShell {
	public static int canvasCounter = 0;
	public static boolean displayImage = false;

	public static void main(String args[]) {
		Display display = new Display();		
	    Shell shell = new Shell(display);	
	    shell.setSize(450, 450);
	    
		// Init Bild
		ImageData imgData = new ImageData("./resources/200px-Chess_bdt45.svg.gif");
		// Image ist final
		final Image image = new Image(display, imgData.scaledTo(100, 100));	    
		
	    shell.addPaintListener(new PaintListener(){
	            public void paintControl(PaintEvent e){	    			           
	                e.gc.setBackground(display.getSystemColor(SWT.COLOR_GREEN));
	    			e.gc.fillRectangle(0, 0, 100, 100);
	                e.gc.setBackground(display.getSystemColor(SWT.COLOR_CYAN));
	    			e.gc.fillRectangle(100, 100, 100, 100);
	                e.gc.setBackground(display.getSystemColor(SWT.COLOR_RED));	                
	    			e.gc.fillRectangle(200, 200, 100, 100);
	                e.gc.setBackground(display.getSystemColor(SWT.COLOR_BLUE));	                
	    			e.gc.fillRectangle(300, 300, 100, 100);
	    			
	                e.gc.drawImage(image, 0, 0);
	                e.gc.drawImage(image, 100, 100);
	                
	                System.out.println(canvasCounter++);
		            
	            }
	        });
	    
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
}
