package org.training.java.chess.demos.threads.simple;

/**
 * Demo for creating and and starting two threads
 * This Thread is directly derived from the Thread class
 * @version 1
 * @author Peter Heide, pheide@t-online.de
 */
public class ThreadWithNoRunnable extends Thread {
	/**
	 * Creates two threads using the runnable interface
	 * Each one counts from 1 to 10 and sleeps a second
	 * @param args not used
	 */
	public static void main(String[] args) {
		
		Thread thread1 = new ThreadWithNoRunnable();
		Thread thread2 = new ThreadWithNoRunnable();
		thread1.start();
		thread2.start();
	}

	@Override
	public void run() {
		super.run();
		for (int i=0; i<10; i++) {
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
