package com.quadromotion.controller;

import com.quadromotion.model.Model;

public class ServiceController {

	private Model model = null;

	public ServiceController(Model model) {
		this.model = model;
	}

	public void setSpeed(int speedX, int speedY, int speedZ, int speedSpin) {
		model.setSpeedX(speedX);
		model.setSpeedY(speedY);
		model.setSpeedZ(speedZ);
		model.setSpeedSpin(speedSpin);
	}

	public int getPilotingState() {
		return model.getPilotingState();
	}

	public void setPilotingState(int pilotingState) {
		model.setPilotingState(pilotingState);
	}

	public int getSelectedGestureConfig() {
		return model.getSelectedGestureConfig();
	}

	public boolean isLeapConnected() {
		return model.isLeapConnected();
	}

	public void setLeapConnected(boolean leapConnected) {
		model.setLeapConnected(leapConnected);
	}

	public int getTAKE_OFF_DELAY() {
		return model.getTAKE_OFF_DELAY();
	}

	public void setTimeUntilTakeOff(int timeUntilTakeOff) {
		model.setTimeUntilTakeOff(timeUntilTakeOff);
	}

	public int getTimeUntilTakeOff() {
		return model.getTimeUntilTakeOff();
	}
}
