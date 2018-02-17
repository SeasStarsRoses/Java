/**
 * 
 */
package org.training.java.chess.model.player.chess.view;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.training.java.chess.model.figures.Figure;

/**
 * Creates images for chess figures
 * @author Peter Heide, pheide@t-online.de
 * @version 1
 * @since 17.02.2018
 */

public class ImageFactory {			
	/**
	 * Helper method for Image creation
	 * @param figure King, Queen, Rook, Bishop, Knight, or Pawn
	 * @param white white or black
	 * @param display Display
	 * @param backgroundColor
	 * @return figure image with background color
	 */
	public static Image createImage(Figure figure, Display display, Color backgroundColor, int size) {
		return createImage(figure.getClass(), figure.isWhite(), display,  backgroundColor, size);
	}

	/**
	 * Helper method for Image creation
	 * @param figure figure class
	 * @param white white or black
	 * @param display Display
	 * @param backgroundColor
	 * @return figure image with background color
	 */
	public static Image createImage(Class<? extends Figure> figure, boolean white, Display display, Color backgroundColor, int size) {
		/** Figure images have 200x200 pixel*/
		final int figureImagePixelSize = 200;
		String filename = null;
		Image image = null;
		if (figure == org.training.java.chess.model.figures.King.class) {
			if (white) {
				filename = "200px-Chess_klt45.svg.gif";
			} else {
				filename = "200px-Chess_kdt45.svg.gif";
			}
		} else if (figure == org.training.java.chess.model.figures.Queen.class) {
			if (white) {
				filename = "200px-Chess_qlt45.svg.gif";
			} else {
				filename = "200px-Chess_qdt45.svg.gif";
			}
		} else if (figure == org.training.java.chess.model.figures.Rook.class) {
			if (white) {
				filename = "200px-Chess_rlt45.svg.gif";
			} else {
				filename = "200px-Chess_rdt45.svg.gif";
			}
		} else if (figure == org.training.java.chess.model.figures.Bishop.class) {
			if (white) {
				filename = "200px-Chess_blt45.svg.gif";
			} else {
				filename = "200px-Chess_bdt45.svg.gif";
			}
		} else if (figure == org.training.java.chess.model.figures.Knight.class) {
			if (white) {
				filename = "200px-Chess_nlt45.svg.gif";
			} else {
				filename = "200px-Chess_ndt45.svg.gif";
			}
		} else if (figure == org.training.java.chess.model.figures.Pawn.class) {
			if (white) {
				filename = "200px-Chess_plt45.svg.gif";
			} else {
				filename = "200px-Chess_pdt45.svg.gif";
			}	
		}
		
		image = createImage(display, filename, backgroundColor);
		
		// Scale needed for image size
		double scale = ((double) size) / figureImagePixelSize;
		
		int widthNew = image.getBounds().width;
		// Resize image
		widthNew = (int) (image.getBounds().width * scale);
		int heightNew = (int) (image.getBounds().height * scale);
		Image imageScaled = new Image(display, image.getImageData().scaledTo(
				(int) (widthNew), (int) (heightNew)));
		
		// Give resources free again
		image.dispose(); 
		
		return imageScaled;
	}
	
	
	/**
	 * Helper method for Image creation
	 * @param display Display
	 * @param filename of image
	 * @param backgroundColor
	 * @return image with background color
	 */
	private static Image createImage(Display display, String filename, Color backgroundColor) {
		Image image = new Image(display, "./resources/" + filename);
		image.setBackground(backgroundColor);
		return image;		
	}
}
