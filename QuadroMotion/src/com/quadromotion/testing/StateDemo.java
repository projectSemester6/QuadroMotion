package com.quadromotion.testing;

import com.quadromotion.FinalStateMachine.FSM;
import com.quadromotion.model.Model;

public class StateDemo {
	public static void main(String[] args) {
	    Model model = new Model();
		FSM fsm = new FSM(model);

		String[] msgs = { "on", "noHands", "handsOk", "takeOff", "noCommand", "noHands", "commandMove", 
				"commandMove", "noHands", "commandMove","on", "noCommand", "commandMove", "land", "noHands",
				"off"};

		for (int i = 0; i < msgs.length; i++)

			switch (msgs[i]) {
			case "off":
				fsm.off();
				break;
			case "on":
				fsm.on();
				break;
			case "noHands":
				fsm.noHands();
				break;
			case "handsOk":
				fsm.handsOk();
				break;
			case "takeOff":
				fsm.takeOff();
				break;
			case "noCommand":
				fsm.noCommand();
				break;
			case "commandMove":
				fsm.commandMove();
				break;
			case "land":
				fsm.land();
				break;
			default:
				break;
			}
	}
}
