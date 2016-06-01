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

/**
 * The interface between the input device and the model class.
 * 
 * @author Gabriel Urech
 *
 */
public interface IInputController {

	/**
	 * Saves all speeds in the model.
	 * 
	 * @param speedX
	 *            the speed in direction x.
	 * @param speedY
	 *            the speed in direction y.
	 * @param speedZ
	 *            the speed in direction z.
	 * @param speedSpin
	 *            the spin speed.
	 */
	public void setSpeed(int speedX, int speedY, int speedZ, int speedSpin);

	/**
	 * Gets the current piloting state.
	 * 
	 * @return the current piloting state.
	 */
	public int getPilotingState();

	/**
	 * Sets the piloting state.
	 * 
	 * @param pilotingState
	 *            the piloting state.
	 */
	public void setPilotingState(int pilotingState);

	/**
	 * Gets the current flying configuration.
	 * 
	 * @return the selected flying configuration
	 */
	public int getSelectedConfig();

	/**
	 * Sets the flying configuration.
	 * 
	 * @param config
	 *            the configuration
	 */
	public void setSelectedConfig(int config);

	/**
	 * Gets the current state of the input device.
	 * 
	 * @return true, if the input device is connected<br>
	 *         false, if the input device is disconnected
	 */
	public boolean isInputDeviceConnected();

	/**
	 * Sets the current state of the input device.<br>
	 * The state can be true or false.
	 *
	 * @param inputDeviceState the new input device state
	 */
	public void setInputDeviceState(boolean inputDeviceState);

	/**
	 * Gets the value of the TAKE_OFF_DELAY.
	 * 
	 * @return TAKE_OFF_DELAY.
	 */
	public int getTAKE_OFF_DELAY();

	/**
	 * Sets the time until the drone will take off.
	 *
	 * @param timeUntilTakeOff the new time until take off
	 */
	public void setTimeUntilTakeOff(int timeUntilTakeOff);

	/**
	 * Gets the time in ms until the drone will take off.
	 * 
	 * @return the time in ms until the drone will take off.
	 */
	public int getTimeUntilTakeOff();

	/**
	 * Gets the control state.
	 *
	 * @return the current control state of the ardrone.
	 */
	public String getControlState();
}
