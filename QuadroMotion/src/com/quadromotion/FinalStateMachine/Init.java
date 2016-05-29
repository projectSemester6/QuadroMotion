package com.quadromotion.FinalStateMachine;

import com.quadromotion.model.Model;

class Init implements State {

	public Init(Model model) {
		super();
		// TODO Auto-generated constructor stub
	}

	public void handsOk() {
		System.out.println("Init + handsOk = Ready");
	}

	public void noHands() {
		System.out.println("Init + noHands = Init");
	}

	public void off() {
		System.out.println("Init + off = Off");
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
	public void land() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void landing() {
		// TODO Auto-generated method stub
		
	}
}