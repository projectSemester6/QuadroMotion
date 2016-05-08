package com.quadromotion.model;

import java.util.Observable;

import com.quadromotion.config.GestureConfig;

/**
 * This class holds the data
 * 
 * @author Gabriel
 *
 */
public class Model extends Observable {

	private float speedX;
	private float speedY;
	private float speedZ;
	private float speedSpin;

	private int batLevel;
	private int altitude;
	private int timeUntilTakeOff;
	private final int TAKE_OFF_DELAY = 2000;

	private int selectedGestureConfig;

	private int pilotingState = 0;
	
	private boolean leapConnected = false;
	private boolean droneConnected = false;

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
		if (countObservers() > 0) {
			setChanged();
			notifyObservers(this.altitude);
		}
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

	public int getSelectedGestureConfig() {
		return this.selectedGestureConfig;
	}

	public void setSelectedGestureConfig(int gestureConfig) {
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

	public boolean isLeapConnected() {
		return this.leapConnected;
	}

	public void setLeapConnected(boolean leapConnected) {
		this.leapConnected = leapConnected;
	}

	public boolean isDroneConnected() {
		return droneConnected;
	}

	public void setDroneConnected(boolean droneConnected) {
		this.droneConnected = droneConnected;
	}

	@Override
	public String toString() {
		return null;
	}
}
