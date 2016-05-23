package com.quadromotion.FinalStateMachine;

import com.quadromotion.model.Model;

class Hovering implements State {
	
private Model model;
	
	public Hovering(Model model){
		super();
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

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ready() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void takeOff() {
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
	public void landing() {
		// TODO Auto-generated method stub
		
	}
}