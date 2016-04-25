package com.quadromotion.FinalStateMachine;

import de.yadrone.base.IARDrone;

public class Ready extends State {
	
	public void takeOff(){
		System.out.println("Ready + command take off = Hovering");
	}
	
	public void noHands(){
		System.out.println("Ready + noHands = Init");
	}
}