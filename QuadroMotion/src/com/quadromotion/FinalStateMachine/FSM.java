package com.quadromotion.FinalStateMachine;

import com.quadromotion.model.Model;

//1. Create a "wrapper" class that models the state machine
public class FSM {
private Model model;
public FSM(Model model){
	this.model = model;
}
	private State[] states = { new Off(model), new Init(model), new Ready(model), new Hovering(model), new Flying(model) }; // 2.
	// states
	private String[][] transitionString = { { "Off", "Init", "Off", "Off", "Off", "Off", "Off", "Off" },

			{ "Off", "Init", "Init", "Ready", "Init", "Init", "Init", "Init" },

			{ "Ready", "Ready", "Init", "Ready", "Hovering", "Ready", "Ready", "Ready" },

			{ "Hovering", "Hovering", "Hovering", "Hovering", "Hovering", "Hovering", "Flying", "Ready" },

			{ "Flying", "Flying", "Hovering", "Flying", "Flying", "Hovering", "Flying", "Ready" } };

	private int[][] transition = { { 0, 1, 0, 0, 0, 0, 0, 0 },

			{ 0, 1, 1, 2, 1, 1, 1, 1 },

			{ 2, 2, 1, 2, 3, 2, 2, 2 },

			{ 3, 3, 3, 3, 3, 3, 4, 2 },

			{ 4, 4, 3, 4, 4, 3, 4, 2 } }; // 4.
	// transitions
	private int current = 0; // 3. current

	private void next(int msg) {
		current = transition[current][msg];
	}

	// 5. All client requests are simply delegated to the current state object
	public void off() {
		states[current].off();
		next(0);
	}

	public void on() {
		states[current].on();
		next(1);
	}

	public void noHands() {
		states[current].noHands();
		next(2);
	}

	public void handsOk() {
		states[current].handsOk();
		next(3);
	}

	public void takeOff() {
		states[current].takeOff();
		next(4);
	}

	public void noCommand() {
		states[current].noCommand();
		next(5);
	}

	public void commandMove() {
		states[current].commandMove(40,30,20,10);
		next(6);
	}

	public void land() {
		states[current].land();
		next(7);
	}
}