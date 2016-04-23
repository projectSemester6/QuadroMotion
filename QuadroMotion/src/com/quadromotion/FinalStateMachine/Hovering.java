package com.quadromotion.FinalStateMachine;

class Hovering extends State {
	
	public void land(){
		System.out.println("Hovering + land  = Ready");
	}
	
	public void noHands(){
		System.out.println("Hovering + noHands  = Hovering");
	}
	
	public void noCommand(){
		System.out.println("Hovering + noCommand  = Hovering");
	}
	
	public void commandMove(){
		System.out.println("Hovering + commandMove  = Flying");
	}
}