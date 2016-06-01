/* Copyright 2016 Gabriel Urech, Alexis Stephan, Simon Henzmann
 * 
 * This file is part of QuadroMotion.
 * 
 * QuadroMotion is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * QuadroMotion is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with DokChess.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.quadromotion.input;

import com.quadromotion.model.Model;

/**
 * The input controller which saves the input of the input device to the model.
 * 
 * @author Gabriel Urech
 *
 */
public class InputController implements IInputController {

	/**
	 * The model.
	 */
	private Model model = null;

	/**
	 * Allocates a new <code>InputController</code> object so that it has <code>model</code> as the model.
	 * 
	 * @param model the model.
	 */
	public InputController(Model model) {
		this.model = model;
	}

	/* (non-Javadoc)
	 * @see com.quadromotion.input.IInputController#setSpeed(int, int, int, int)
	 */
	@Override
	public void setSpeed(int speedX, int speedY, int speedZ, int speedSpin) {
		model.setSpeed(speedX, speedY, speedZ, speedSpin);
	}

	/* (non-Javadoc)
	 * @see com.quadromotion.input.IInputController#getPilotingState()
	 */
	@Override
	public int getPilotingState() {
		return model.getPilotingState();
	}

	/* (non-Javadoc)
	 * @see com.quadromotion.input.IInputController#setPilotingState(int)
	 */
	@Override
	public void setPilotingState(int pilotingState) {
		model.setPilotingState(pilotingState);
	}

	/* (non-Javadoc)
	 * @see com.quadromotion.input.IInputController#getSelectedConfig()
	 */
	@Override
	public int getSelectedConfig() {
		return model.getSelectedConfig();
	}

	/* (non-Javadoc)
	 * @see com.quadromotion.input.IInputController#isInputDeviceConnected()
	 */
	@Override
	public boolean isInputDeviceConnected() {
		return model.isInputDeviceConnected();
	}

	/* (non-Javadoc)
	 * @see com.quadromotion.input.IInputController#setInputDeviceState(boolean)
	 */
	@Override
	public void setInputDeviceState(boolean inputDeviceState) {
		model.setInputDeviceConnected(inputDeviceState);
	}

	/* (non-Javadoc)
	 * @see com.quadromotion.input.IInputController#getTAKE_OFF_DELAY()
	 */
	@Override
	public int getTAKE_OFF_DELAY() {
		return model.getTAKE_OFF_DELAY();
	}

	/* (non-Javadoc)
	 * @see com.quadromotion.input.IInputController#setTimeUntilTakeOff(int)
	 */
	@Override
	public void setTimeUntilTakeOff(int timeUntilTakeOff) {
		model.setTimeUntilTakeOff(timeUntilTakeOff);
	}

	/* (non-Javadoc)
	 * @see com.quadromotion.input.IInputController#getTimeUntilTakeOff()
	 */
	@Override
	public int getTimeUntilTakeOff() {
		return model.getTimeUntilTakeOff();
	}

	/* (non-Javadoc)
	 * @see com.quadromotion.input.IInputController#setSelectedConfig(int)
	 */
	@Override
	public void setSelectedConfig(int config) {
		model.setSelectedConfig(config);
	}

	/* (non-Javadoc)
	 * @see com.quadromotion.input.IInputController#getControlState()
	 */
	@Override
	public String getControlState() {
		return model.getControlState();
	}
}
