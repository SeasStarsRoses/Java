package org.training.java.chess.demos.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * Class where object shall be constructed
 * @see http://tutorials.jenkov.com/java-reflection/constructors.html 
 * @author Peter Heide, pheide@t-online.de
 */
class ConstructMe {
	private String hello;
	/**
	 * Default constructor to be used to construct object using reflection
	 */
	public ConstructMe() { hello = "Hello"; }
	/**
	 * Default constructor to be used to construct object using reflection
	 */
	public ConstructMe(String hello) {
		this.hello = hello;
	}
	/** Return hello value */ 
	@Override
	public String toString() {
		return "ConstructMe [hello=" + hello + "]";
	}
}

/**
 * Reflection Construcor Call
 * 
 * @author Peter Heide, pheide@t-online.de
 * @version 1
 * @since 17.02.2018
 */
public class Reflection {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Class<ConstructMe> constructMeClass = org.training.java.chess.demos.reflection.ConstructMe.class;
		Constructor<?>[] constructors = constructMeClass.getConstructors();
		for (Constructor<?> constructor : constructors) {
			try {
				Class<?>[] parameterTypes = constructor.getParameterTypes();
				ConstructMe constructMe = null;					
				if (parameterTypes == null || parameterTypes.length == 0) {
					constructMe = (ConstructMe) constructor.newInstance();					
				} else {
					constructMe = (ConstructMe) constructor.newInstance("World");
				}
				System.out.println(constructMe);
				Field privateStringField;
				privateStringField = constructMeClass.getDeclaredField("hello");
				privateStringField.setAccessible(true);
				String fieldValue = (String) privateStringField.get(constructMe);
				System.out.println("Private fieldValue = " + fieldValue);
			} catch (InstantiationException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException | NoSuchFieldException | SecurityException e) {
				e.printStackTrace();
			}
		}
	}
}
