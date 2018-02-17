package org.training.java.chess.demos.threads.bank;


public class MeineThreads {

	public static void main(String[] args) {	
		Bank bank = new Bank();
		NeuerThread mann = new NeuerThread("Mann", bank);
		NeuerThread frau = new NeuerThread("Frau", bank);
		
		mann.setPriority(Thread.MIN_PRIORITY);
		frau.setPriority(Thread.MAX_PRIORITY);
		
		mann.start();
		frau.start();
		
		System.out.println(mann);
	}
}
