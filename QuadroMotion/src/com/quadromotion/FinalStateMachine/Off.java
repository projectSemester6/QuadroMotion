package com.quadromotion.FinalStateMachine;

import com.quadromotion.model.Model;

class Off implements State {

	public Off(Model model) {
		super();
		// TODO Auto-generated constructor stub
	}

	public void on() {
		System.out.println("Off + on  = Init");
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