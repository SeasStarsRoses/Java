package org.training.java.chess.demos.formatting;
/**
 * Formatting strings demo for the methods 
 *   System.out.printf(String, Object...), 
 *   System.out.format(String, Object...), and
 *   String.format(String, args...)
 * @author Peter Heide, pheide@t-online.de
 * @since 1.12.2012
 */
public class Formatting {
	
	/**
	 * Print String format samples on Screen
	 * @param args not used
	 */
	public static void main(String[] args) {
		System.out.printf("Integer %d \n", 123);
		System.out.printf("Floating numbers %f \n", 123.12);
		System.out.printf("Boolean %b \n\n", false);		

		System.out.printf("Natural order %1$d + %2$d \n", 123, 456);
		System.out.printf("Order switched %2$d + %1$d \n\n", 123, 456);		
		
		System.out.printf("Signed %+d %+d \n\n", 123, -123);
		
		System.out.format("System.out.format(String, Object...) is same as System.out.printf(String, Object...) %d \n", 123);
		System.out.printf("System.out.printf(String, Object...) is same as System.out.format(String, Object...) %d \n", 123);
		String s = String.format("%d \n\n", 123);
		System.out.println("String.format(\"%d \\n\\n\", 123) results in " + s);
		s = String.format("%d \n\n", 123);
		System.out.println("String.format(\"%d \\n\\n\", 123) results in " + s);
		
		System.out.println("0123456789");
		System.out.printf(">%7d< 7-digits right-aligned \n", 12345);
		System.out.printf(">%07d< 7-digits right-aligned filled with zeroes\n", 12345);
		System.out.printf(">%-7d< 7-digits left-aligned \n", 123435);
		System.out.printf(">%3.2f< Floating point 123.456 with 3 digits before and 2 digits after decimal point\n", 123.456);
	}
}
