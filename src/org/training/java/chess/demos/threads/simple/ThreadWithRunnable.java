package org.training.java.chess.demos.threads.simple;

/**
 * Demo for creating and starting two threads using Runnable interface
 * @version 1
 * @author Peter Heide, pheide@t-online.de
 */
public class ThreadWithRunnable {
	/**
	 * Creates two threads using the runnable interface
	 * Each one counts from 1 to 10 and sleeps a second
	 * @param args not used
	 */
	public static void main(String[] args) {
		Runnable r = new Runnable() {
			@Override
			public void run() {
				for (int i=0; i<10; i++) {
					System.out.println(i);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		Thread t1 = new Thread(r);
		Thread t2 = new Thread(r);
		t1.start();
		t2.start();

	}
}
