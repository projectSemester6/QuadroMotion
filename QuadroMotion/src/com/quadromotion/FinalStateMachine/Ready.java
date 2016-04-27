package com.quadromotion.FinalStateMachine;

import com.quadromotion.model.Model;


public class Ready extends State {
	
	private Model model;
	public Ready(Model model){
		super(model);
		this.model = model;
	}
	
	public void takeOff(){
		System.out.println("Ready + command take off = Hovering");
		model.setTakeOffCommand(true);
	}
	
	public void noHands(){
		System.out.println("Ready + noHands = Init");
	}
}