package com.quadromotion.controller;

import com.quadromotion.model.Model;

/**
 * The input controller which stores the input of the input device to the model.
 * @author Gabriel
 *
 */
public class InputController implements IInputController{

	private Model model = null;

	/**
	 * Constructor
	 * @param model
	 */
	public InputController(Model model) {
		this.model = model;
	}

	@Override
	public void setSpeed(int speedX, int speedY, int speedZ, int speedSpin) {
		model.setSpeedX(speedX);
		model.setSpeedY(speedY);
		model.setSpeedZ(speedZ);
		model.setSpeedSpin(speedSpin);
	}

	@Override
	public int getPilotingState() {
		return model.getPilotingState();
	}

	@Override
	public void setPilotingState(int pilotingState) {
		model.setPilotingState(pilotingState);
	}

	@Override
	public int getSelectedConfig() {
		return model.getSelectedConfig();
	}

	@Override
	public boolean isInputDeviceConnected() {
		return model.isInputDeviceConnected();
	}

	@Override
	public void setInputDeviceState(boolean inputDeviceState) {
		model.setInputDeviceConnected(inputDeviceState);
	}

	@Override
	public int getTAKE_OFF_DELAY() {
		return model.getTAKE_OFF_DELAY();
	}

	@Override
	public void setTimeUntilTakeOff(int timeUntilTakeOff) {
		model.setTimeUntilTakeOff(timeUntilTakeOff);
	}

	@Override
	public int getTimeUntilTakeOff() {
		return model.getTimeUntilTakeOff();
	}

	@Override
	public void setSelectedConfig(int config) {
		model.setSelectedConfig(config);
	}
}
