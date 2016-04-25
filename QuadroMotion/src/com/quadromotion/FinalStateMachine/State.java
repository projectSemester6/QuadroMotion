package com.quadromotion.FinalStateMachine;

//6. Create a state base class that makes the concrete states interchangeable
public abstract class State {
	public void on() {
		System.out.println("abstract on");
	} // 7. The State base

	public void land() {
		System.out.println("abstract land");
	}

	public void commandMove() {
		System.out.println("abstract commandMove");
	}

	public void noCommand() {
		System.out.println("abstract noCommand");
	}

	public void noHands() {
		System.out.println("abstract noHands");
	}

	public void handsOk() {
		System.out.println("abstract handsOk");
	}

	public void takeOff() {
		System.out.println("abstract takeOff");
	}

	public void off() {
		System.out.println("abstract off");
	}
}