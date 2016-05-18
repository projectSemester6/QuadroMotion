package com.quadromotion.FinalStateMachine;

import com.quadromotion.model.Model;

class Flying extends State {

private Model model;
	
	public Flying(Model model){
		super(model);
		this.model = model;
	}
	
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