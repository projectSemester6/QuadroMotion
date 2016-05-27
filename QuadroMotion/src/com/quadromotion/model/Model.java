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
 * This class holds the data and extends the <code>Observable</code> class.
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
	private int batLevel;
	
	/**
	 * The altitude of the drone
	 */
	private int altitude;
	
	/**
	 * The countdown time starting from the until the drone will take off
	 */
	private int timeUntilTakeOff;
	private final int TAKE_OFF_DELAY = 2000;

	private int selectedGestureConfig;

	private int pilotingState = 0;

	private boolean inputDeviceConnected = false;
	private boolean droneConnected = false;
	private String controlState = "-";

	/**
	 * Constructor
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

	public float getSpeedX() {
		return this.speedX;
	}

	public void setSpeedX(float speed) {
		this.speedX = speed;
		if (countObservers() > 0) {
			setChanged();
			notifyObservers(this.speedX);
		}
	}

	public float getSpeedY() {
		return this.speedY;
	}

	public void setSpeedY(float speed) {
		this.speedY = speed;
		if (countObservers() > 0) {
			setChanged();
			notifyObservers(this.speedY);
		}
	}

	public float getSpeedZ() {
		return this.speedZ;
	}

	public void setSpeedZ(float speed) {
		this.speedZ = speed;
		if (countObservers() > 0) {
			setChanged();
			notifyObservers(this.speedZ);
		}
	}

	public float getSpeedSpin() {
		return this.speedSpin;
	}

	public void setSpeedSpin(float speed) {
		this.speedSpin = speed;
		if (countObservers() > 0) {
			setChanged();
			notifyObservers(this.speedSpin);
		}
	}

	public int getBatLevel() {
		return this.batLevel;
	}

	public void setBatLevel(int value) {
		this.batLevel = value;
		if (countObservers() > 0) {
			setChanged();
			notifyObservers(this.batLevel);
		}
	}

	public int getAltitude() {
		return this.altitude;
	}

	public String getAltitudeString() {
		return String.valueOf(this.altitude);
	}

	public void setAltitude(int value) {
		this.altitude = value;
		// if (countObservers() > 0) {
		// setChanged();
		// notifyObservers(this.altitude);
		// }
	}

	public int getTimeUntilTakeOff() {
		return this.timeUntilTakeOff;
	}

	public void setTimeUntilTakeOff(int timeUntilTakeOff) {
		this.timeUntilTakeOff = timeUntilTakeOff;
		if (countObservers() > 0) {
			setChanged();
			notifyObservers(this.timeUntilTakeOff);
		}
	}

	public int getTAKE_OFF_DELAY() {
		return this.TAKE_OFF_DELAY;
	}

	public int getSelectedConfig() {
		return this.selectedGestureConfig;
	}

	public void setSelectedConfig(int gestureConfig) {
		this.selectedGestureConfig = gestureConfig;
	}

	public int getPilotingState() {
		return this.pilotingState;
	}

	public void setPilotingState(int pilotingState) {
		this.pilotingState = pilotingState;
		if (countObservers() > 0) {
			setChanged();
			notifyObservers(this.pilotingState);
		}
	}

	public boolean isInputDeviceConnected() {
		return this.inputDeviceConnected;
	}

	public void setInputDeviceConnected(boolean inputDeviceState) {
		this.inputDeviceConnected = inputDeviceState;
	}

	public boolean isDroneConnected() {
		return droneConnected;
	}

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

	public void setControlState(String string) {
		this.controlState = string;
		if (countObservers() > 0) {
			setChanged();
			notifyObservers(this.controlState);
		}
	}

	public String getControlState() {
		return this.controlState;
	}
}
