package com.quadromotion.FinalStateMachine;

import com.quadromotion.model.Model;

class Off extends State {

	public Off(Model model) {
		super(model);
		// TODO Auto-generated constructor stub
	}

	public void on() {
		System.out.println("Off + on  = Init");
	}
}