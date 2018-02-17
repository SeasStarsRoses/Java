package org.training.java.chess.demos.anonymous;

/**
 * Demo for anonymous classes
 * @author Peter Heide, pheide@t-online.de
 * @since 1.12.2012
 */
public class AnonymousClassesTest {

	/**
	 * Use anonymous and non anonymous class
	 * @param args not used
	 */
	public static void main(String[] args) {
		NonAnonymousClass myNonAnonymousClass = new NonAnonymousClass();
		myNonAnonymousClass.doSomething();
		
		// Anonymous class
		MyInterface myInterface = new MyInterface() {
			@Override
			public void doSomething() {
				System.out.println("I am the anonymous class");
			}
		};
		myInterface.doSomething();
		
		abstract class AnonymousClass{
			public abstract void anonymousMethod(); 
		}; 
		AnonymousClass anonymousClass = new AnonymousClass() {

			@Override
			public void anonymousMethod() {
				System.out.println("anomymous Method in anonymous class");
			}
		};
		anonymousClass.anonymousMethod();
	
	}
}
