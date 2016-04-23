package com.quadromotion.FinalStateMachine;

class Flying extends State {

	public void land(){
		System.out.println("Flying + land  = Ready");
	}
	
	public void noHands(){
		System.out.println("Flying + noHands  = Hovering");
	}
	
	public void noCommand(){
		System.out.println("Flying + noCommand  = Hovering");
	}
	
	public void commandMove(){
		System.out.println("Flying + commandMove = Flying");
	}
}