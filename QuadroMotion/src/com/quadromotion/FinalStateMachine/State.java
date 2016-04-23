package com.quadromotion.FinalStateMachine;

//6. Create a state base class that makes the concrete states interchangeable
public abstract class State {
	public void on() {
		System.out.println("error on");
	} // 7. The State base

	public void land() {
		System.out.println("error land");
	}

	public void commandMove() {
		System.out.println("error commandMove");
	}

	public void noCommand() {
		System.out.println("error noCommand");
	}

	public void noHands() {
		System.out.println("error noHands");
	}

	public void handsOk() {
		System.out.println("error handsOk");
	}

	public void takeOff() {
		System.out.println("error takeOff");
	}

	public void off() {
		System.out.println("error off");
	}
}