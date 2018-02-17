package org.training.java.chess.demos.anonymous;

/**
 * Non anomymous class implementing interface for demo of anonymous classes
 * @author Peter Heide, pheide@t-online.de
 *
 */
public class NonAnonymousClass implements MyInterface {
	/**
	 * Sample method implements interface method
	 */
	@Override
	public void doSomething() {
		System.out.println("I am the non anonymous class");
		
	}
}
