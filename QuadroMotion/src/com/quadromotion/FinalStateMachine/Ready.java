package com.quadromotion.FinalStateMachine;

import com.quadromotion.model.Model;


public class Ready implements State {
	
	private Model model;
	public Ready(Model model){
		super();
		this.model = model;
	}
	
	public void takeOff(){
		System.out.println("Ready + command take off = Hovering");
	}
	
	public void noHands(){
		System.out.println("Ready + noHands = Init");
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ready() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void takingOff() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hovering() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void flying() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void land() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void landing() {
		// TODO Auto-generated method stub
		
	}
}