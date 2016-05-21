package com.quadromotion.FinalStateMachine;

import com.quadromotion.model.Model;

class Flying implements State {

private Model model;
	
	public Flying(Model model){
		
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