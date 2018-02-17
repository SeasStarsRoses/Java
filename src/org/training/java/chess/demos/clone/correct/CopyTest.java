package org.training.java.chess.demos.clone.correct;

// http://www.jusfortechies.com/java/core-java/deepcopy_and_shallowcopy.php

class Subject {

	  private String name;

	  public String getName() {
	    return name;
	  }

	  public void setName(String s) {
	    name = s;
	  }

	  public Subject(String s) {
	    name = s;
	  }
	}

	class Student implements Cloneable {
	  //Contained object
	  private Subject subj;

	  private String name;

	  public Subject getSubj() {
		return subj;
	  }

	  public String getName() {
		return name;
	  }

	  public void setName(String s) {
		name = s;
	  }

	  public Student(String s, String sub) {
		name = s;
		subj = new Subject(sub);
	  }

	  public Object clone() {
		  Student student = new Student(name, subj.getName());
		  return student;
	  }
	}

	public class CopyTest {

	  public static void main(String[] args) {
		//Original Object
		Student stud = new Student("John", "Algebra");

		System.out.println("Original Object: " + stud.getName() + " - "
			+ stud.getSubj().getName());

		//Clone Object
		Student clonedStud = (Student) stud.clone();

		System.out.println("Cloned Object: " + clonedStud.getName() + " - "
			+ clonedStud.getSubj().getName());

		stud.setName("Dan");
		stud.getSubj().setName("Physics");

		System.out.println("Original Object after it is updated: " 
			+ stud.getName() + " - " + stud.getSubj().getName());

		System.out.println("Cloned Object after updating original object: "
			+ clonedStud.getName() + " - " + clonedStud.getSubj().getName());

	  }
	}
