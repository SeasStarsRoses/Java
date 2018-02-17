package org.training.java.chess.controller;

import org.training.java.chess.model.coordinate.InvalidCoordinateException;
import org.training.java.chess.model.logging.Logger;


/**
 * Thread starts two threads for simultaneous chess
 * @author Peter Heide, pheide@t-online.de
 * @since 27.11.2012
 */

public class SimultaneousGames {
	/**
	 * Creat two Threads and run two chess games with them
	 * @param args for internatinalization, e.g. fr FR, de DE, en US
	 */
	public static void main(final String[] args) {
		Runnable r = new Runnable() {
			@Override
			public void run() {
				try {
					Game.main(args);
				} catch (InvalidCoordinateException e) {
					e.printStackTrace();
					System.exit(-1);
				}
				Logger.log("Hello");
			}
		};
		Thread t1 = new Thread(r);
		Thread t2 = new Thread(r);
		t1.start();
		t2.start();
	}
}
