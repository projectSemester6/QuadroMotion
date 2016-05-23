package com.quadromotion.FinalStateMachine;

import com.quadromotion.model.Model;

//6. Create a state base class that makes the concrete states interchangeable
public interface State {

	public void init();

	public void ready();

	public void takeOff();

	public void takingOff();

	public void hovering();

	public void flying();

	public void land();

	public void landing();

}