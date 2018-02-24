/**
 * 
 */
package org.training.java.chess.view;

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
	// There can only be one display in town
	Display display = null;
	public static final String RESOURCES_DIR = "./resources/";
	
	/**
	 * Constructor
	 * @param display needed to creat images
	 */
	public ImageFactory(Display display) {
		super();
		this.display = display; 
	}

	// Figure file name: k=king, q=queen, r=rook, b=bishop, n=knight, p=pawh, l=light, d=dark
	private Image kingWhite = createImage("200px-Chess_klt45.svg.gif");
	private Image queenWhite = createImage("200px-Chess_qlt45.svg.gif");
	private Image rookWhite = createImage("200px-Chess_rlt45.svg.gif");
	private Image bishopWhite = createImage("200px-Chess_blt45.svg.gif");
	private Image knightWhite = createImage("200px-Chess_nlt45.svg.gif");
	private Image pawnWhite = createImage("200px-Chess_plt45.svg.gif");
	private Image kingBlack = createImage("200px-Chess_kdt45.svg.gif");
	private Image queenBlack = createImage("200px-Chess_qdt45.svg.gif");
	private Image rookBlack = createImage("200px-Chess_rdt45.svg.gif");
	private Image bishopBlack = createImage("200px-Chess_bdt45.svg.gif");
	private Image knightBlack = createImage("200px-Chess_ndt45.svg.gif");
	private Image pawnBlack = createImage("200px-Chess_pdt45.svg.gif");

	/**
	 * Helper method for Image creation
	 * @param figure King, Queen, Rook, Bishop, Knight, or Pawn
	 * @param white white or black
	 * @param display Display
	 * @param backgroundColor
	 * @return figure image with background color
	 */
	public Image createImage(Figure figure, Display display, int size) {
		return createImage(figure.getClass(), figure.isWhite(), display,  size);
	}

	/**
	 * Helper method for Image creation
	 * @param figure figure class
	 * @param white white or black
	 * @param display Display
	 * @param backgroundColor
	 * @return figure image with background color
	 */
	public Image createImage(Class<? extends Figure> figure, boolean white, Display display, int size) {
		/** Figure images have 200x200 pixel*/
		final int figureImagePixelSize = 200;
		Image image = null;
		if (figure == org.training.java.chess.model.figures.King.class) {
			if (white) {
				image = kingWhite;
			} else {
				image = kingBlack;
			}
		} else if (figure == org.training.java.chess.model.figures.Queen.class) {
			if (white) {
				image = queenWhite;
			} else {
				image = queenBlack;
			}
		} else if (figure == org.training.java.chess.model.figures.Rook.class) {
			if (white) {
				image = rookWhite;
			} else {
				image = rookBlack;
			}
		} else if (figure == org.training.java.chess.model.figures.Bishop.class) {
			if (white) {
				image = bishopWhite;
			} else {
				image = bishopBlack;
			}
		} else if (figure == org.training.java.chess.model.figures.Knight.class) {
			if (white) {
				image = knightWhite;
			} else {
				image = knightBlack;
			}
		} else if (figure == org.training.java.chess.model.figures.Pawn.class) {
			if (white) {
				image = pawnWhite;
			} else {
				image = pawnBlack;
			}	
		}
		
		// Scale needed for image size
		double scale = ((double) size) / figureImagePixelSize;
		
		int widthNew = image.getBounds().width;
		// Resize image
		widthNew = (int) (image.getBounds().width * scale);
		int heightNew = (int) (image.getBounds().height * scale);
		Image imageScaled = new Image(display, image.getImageData().scaledTo(
				(int) (widthNew), (int) (heightNew)));
		return imageScaled;
	}

	/**
	 * Helper method for Image creation
	 * @param filename of image
	 * @return image with background color
	 */
	private Image createImage(String filename) {
		Image image = new Image(display, RESOURCES_DIR + filename);
		return image;		
	}
}
