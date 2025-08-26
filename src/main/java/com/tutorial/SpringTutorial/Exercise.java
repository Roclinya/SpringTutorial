package com.tutorial.SpringTutorial;

public interface Exercise {
	String s = "alive";
	public void exercise();

}
 interface Run {
	public void exercise(int x);

}
 interface RunFast extends Exercise,Run{

	@Override
	default void exercise(int x) {
		// TODO Auto-generated method stub
		
	}
	@Override
	default void exercise() {
		// TODO Auto-generated method stub
		
	}
	 
 }
