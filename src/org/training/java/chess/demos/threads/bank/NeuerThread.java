package org.training.java.chess.demos.threads.bank;


public class NeuerThread extends Thread {
	
	private String name;
	private Bank bank;
	
	public NeuerThread(String name, Bank bank) {
		this.name = name;
		this.bank = bank;
	}

	@Override
	public void run() {
		super.run();
		for (int i=0; i<10; i++) {
			boolean erfolg = bank.abheben(100);
			
			System.out.println("Erfolgreich abgehoben = " + erfolg);
			try {
				Thread.sleep(1000); // 1000 Millisekunden
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Ich bin der neue Thread mit Zaehler " + i 
					+ " und Name " + name);			
		}
	}

	@Override
	public String toString() {
		return "NeuerThread [name=" + name + ", bank=" + bank + "]";
	}	
}
