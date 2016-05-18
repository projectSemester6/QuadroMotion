package com.quadromotion.controller;

/**
 * The interface between the input device and the model class.
 * @author Gabriel
 *
 */
public interface IInputController {

	/**
	 * Speichert alle Geschwindigkeiten im model.
	 * @param speedX
	 * @param speedY
	 * @param speedZ
	 * @param speedSpin
	 */
	public void setSpeed(int speedX, int speedY, int speedZ, int speedSpin);

	/**
	 * gets the current piloting state
	 */
	public int getPilotingState();

	/**
	 * Sets the piloting state.
	 * @param pilotingState
	 */
	public void setPilotingState(int pilotingState);

	/**
	 * Gets the current flying configureation.
	 * @return the selected flying configuration
	 */
	public int getSelectedConfig();
	
	/**
	 * Sets the flying configuration.
	 * @param config the configuration
	 */
	public void setSelectedConfig(int config);

	/**
	 * Gets the current state of the input device.
	 * @return true, if the input device is connected; false, if the input device is disconnected
	 */
	public boolean isInputDeviceConnected();

	/**
	 * Sets the current state of the input device. The state can be true or false.
	 * @param inputDeviceState
	 */
	public void setInputDeviceState(boolean inputDeviceState);

	/**
	 * Gets the value of the TAKE_OFF_DELAY.
	 * @return the value of the TAKE_OFF_DELAY.
	 */
	public int getTAKE_OFF_DELAY();

	/**
	 * Sets the time until the drone will take off.
	 * @param timeUntilTakeOff
	 */
	public void setTimeUntilTakeOff(int timeUntilTakeOff);

	/**
	 * Gets the time in ms until the drone will take off.
	 * @return the time in ms until the drone will take off.
	 */
	public int getTimeUntilTakeOff();

	public String getControlState();
}
