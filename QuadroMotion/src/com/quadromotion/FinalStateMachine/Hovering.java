package com.quadromotion.FinalStateMachine;

import com.quadromotion.model.Model;

class Hovering extends State {
	
private Model model;
	
	public Hovering(Model model){
		super(model);
		this.model = model;
	}
	
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