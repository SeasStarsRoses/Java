package org.training.java.chess.model.player.chess.demos.recursion;

public class Fakultaet {
	
	
	public static int fakultaetLinear(int n) {
		int result = 1;
		for (int i=n; i>=1; i--) {
			result *= i;
		}
		return result;
	}
	
	public static int fakultaetRekursiv(int n) {
		if (n == 1) { return 1; }
		else {
			return n * fakultaetRekursiv(n-1);
		}
	}
	
	
	public static void main(String[] args) {
		System.out.println("Fakultaet(4) = "+fakultaetLinear(4));
		System.out.println("Fakultaet(4) = "+fakultaetRekursiv(4));
	}
}
