package com.quadromotion.FinalStateMachine;

import com.quadromotion.model.Model;

class Init extends State {

	public Init(Model model) {
		super(model);
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
}