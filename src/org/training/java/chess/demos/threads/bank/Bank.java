package org.training.java.chess.demos.threads.bank;

public class Bank {
	private double kontostand = 100;

	public double getKontostand() {
		return kontostand;
	}

	public void setKontostand(double kontostand) {
		this.kontostand = kontostand;
	}

	// Liefert true bei Erfolg, sonst false
	public synchronized boolean abheben(double betrag) {
		double neuerKontostand = kontostand - betrag; 
		if (neuerKontostand >= 0) {
			kontostand = neuerKontostand;
			return true;
		} else {
			return false;			
		}
	}

	@Override
	public String toString() {
		return "Bank [kontostand=" + kontostand + "]";
	}	
}
