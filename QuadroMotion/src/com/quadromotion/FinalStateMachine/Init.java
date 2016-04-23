package com.quadromotion.FinalStateMachine;

class Init extends State {

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