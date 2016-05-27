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
package com.quadromotion.model;

import java.util.Observable;

import com.quadromotion.config.GestureConfig;

/**
 * This class holds the data and extends the <code>Observable</code> class to
 * inform every class which implements the <code>Observer</code> class.
 * 
 * @author Gabriel Urech<br>
 *         Alexis Stephan
 *
 */
public class Model extends Observable {

	/**
	 * The speed in direction x (forward / backward)
	 */
	private float speedX;

	/**
	 * The speed in direction y (right / left)
	 */
	private float speedY;

	/**
	 * The speed in direction z (down / up)
	 */
	private float speedZ;

	/**
	 * The spin speed (clockwise / counterclockwise)
	 */
	private float speedSpin;

	/**
	 * The battery level of the drone
	 */
	private int batLevel = 0;

	/**
	 * The altitude of the drone
	 */
	private int altitude = 0;

	/**
	 * The countdown time starting from the until the drone will take off
	 */
	private int timeUntilTakeOff;

	/**
	 * This delay defines the duration for which the pilot has to hold the take
	 * off gesture.
	 */
	private final int TAKE_OFF_DELAY = 2000;

	/**
	 * The selected gesture configuration.
	 */
	private int selectedGestureConfig;

	/**
	 * The current piloting state.
	 */
	private int pilotingState = 0;

	/**
	 * The connection state of the input device.
	 */
	private boolean inputDeviceConnected = false;

	/**
	 * The connection state of the drone.
	 */
	private boolean droneConnected = false;

	/**
	 * The current state of the drone.
	 */
	private String controlState = "-";

	/**
	 * Allocates a new <code>Model</code> object.
	 */
	public Model() {
		super();

		this.speedX = 0;
		this.speedY = 0;
		this.speedZ = 0;
		this.speedSpin = 0;
		this.timeUntilTakeOff = this.TAKE_OFF_DELAY;
		this.selectedGestureConfig = GestureConfig.CONFIG_1_TWO_HANDS;
	}

	/**
	 * Sets all speeds.
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
	public void setSpeed(float speedX, float speedY, float speedZ, float speedSpin) {
		this.speedX = speedX;
		this.speedY = speedY;
		this.speedZ = speedZ;
		this.speedSpin = speedSpin;
		if (countObservers() > 0) {
			setChanged();
			notifyObservers("speed");
		}
	}

	/**
	 * 
	 * @return the speed x.
	 */
	public float getSpeedX() {
		return this.speedX;
	}

	// public void setSpeedX(float speed) {
	// this.speedX = speed;
	// if (countObservers() > 0) {
	// setChanged();
	// notifyObservers(this.speedX);
	// }
	// }

	/**
	 * 
	 * @return the speed y.
	 */
	public float getSpeedY() {
		return this.speedY;
	}

	// public void setSpeedY(float speed) {
	// this.speedY = speed;
	// if (countObservers() > 0) {
	// setChanged();
	// notifyObservers(this.speedY);
	// }
	// }

	/**
	 * 
	 * @return the speed z.
	 */
	public float getSpeedZ() {
		return this.speedZ;
	}

	// public void setSpeedZ(float speed) {
	// this.speedZ = speed;
	// if (countObservers() > 0) {
	// setChanged();
	// notifyObservers(this.speedZ);
	// }
	// }

	/**
	 * 
	 * @return the spin speed.
	 */
	public float getSpeedSpin() {
		return this.speedSpin;
	}
	//
	// void setSpeedSpin(float speed) {
	// this.speedSpin = speed;
	// if (countObservers() > 0) {
	// setChanged();
	// notifyObservers(this.speedSpin);
	// }
	// }

	/**
	 * 
	 * @return the battery level of the drone.
	 */
	public int getBatLevel() {
		return this.batLevel;
	}

	/**
	 * Sets the battery level.
	 * 
	 * @param batLevel
	 *            the battery level.
	 */
	public void setBatLevel(int batLevel) {
		this.batLevel = batLevel;
		if (countObservers() > 0) {
			setChanged();
			notifyObservers(this.batLevel);
		}
	}

	/**
	 * 
	 * @return the altitude of the drone.
	 */
	public int getAltitude() {
		return this.altitude;
	}

	/**
	 * 
	 * @return the altitude of the drone as string.
	 */
	public String getAltitudeString() {
		return String.valueOf(this.altitude);
	}

	/**
	 * Sets the altitude of the drone.
	 * 
	 * @param altitude
	 *            the altitude.
	 */
	public void setAltitude(int altitude) {
		this.altitude = altitude;
		if (countObservers() > 0) {
			setChanged();
			notifyObservers(this.altitude);
		}
	}

	/**
	 * 
	 * @return the time until the drone takes off.
	 */
	public int getTimeUntilTakeOff() {
		return this.timeUntilTakeOff;
	}

	/**
	 * Sets the time until the drone will take off.
	 * 
	 * @param timeUntilTakeOff
	 *            the time in ms.
	 */
	public void setTimeUntilTakeOff(int timeUntilTakeOff) {
		this.timeUntilTakeOff = timeUntilTakeOff;
		if (countObservers() > 0) {
			setChanged();
			notifyObservers(this.timeUntilTakeOff);
		}
	}

	/**
	 * 
	 * @return the take off delay.
	 */
	public int getTAKE_OFF_DELAY() {
		return this.TAKE_OFF_DELAY;
	}

	/**
	 * 
	 * @return the selected configuration.
	 */
	public int getSelectedConfig() {
		return this.selectedGestureConfig;
	}

	/**
	 * Sets the gesture configuration.
	 * 
	 * @param gestureConfig
	 *            the configuration.
	 */
	public void setSelectedConfig(int gestureConfig) {
		this.selectedGestureConfig = gestureConfig;
	}

	/**
	 * 
	 * @return the current piloting state.
	 */
	public int getPilotingState() {
		return this.pilotingState;
	}

	/**
	 * Sets the piloting state.
	 * 
	 * @param pilotingState
	 *            the piloting state.
	 */
	public void setPilotingState(int pilotingState) {
		this.pilotingState = pilotingState;
		if (countObservers() > 0) {
			setChanged();
			notifyObservers(this.pilotingState);
		}
	}

	/**
	 * 
	 * @return the connection state of the input device.
	 */
	public boolean isInputDeviceConnected() {
		return this.inputDeviceConnected;
	}

	/**
	 * Sets the connection state of the input device.
	 * 
	 * @param inputDeviceState
	 *            the state.
	 */
	public void setInputDeviceConnected(boolean inputDeviceState) {
		this.inputDeviceConnected = inputDeviceState;
	}

	/**
	 * 
	 * @return the connection state of the drone.
	 */
	public boolean isDroneConnected() {
		return droneConnected;
	}

	/**
	 * Sets the connection state of the drone.
	 * 
	 * @param droneConnected
	 *            the state.
	 */
	public void setDroneConnected(boolean droneConnected) {
		this.droneConnected = droneConnected;
		if (countObservers() > 0) {
			setChanged();
			notifyObservers();
		}
	}

	@Override
	public String toString() {
		return null;
	}

	/**
	 * Sets the control state.
	 * 
	 * @param controlState
	 *            the control state.
	 */
	public void setControlState(String controlState) {
		this.controlState = controlState;
		if (countObservers() > 0) {
			setChanged();
			notifyObservers(this.controlState);
		}
	}

	/**
	 * 
	 * @return the control state of the drone.
	 */
	public String getControlState() {
		return this.controlState;
	}
}
