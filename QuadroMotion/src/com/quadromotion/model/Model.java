package com.quadromotion.model;

import java.util.Observable;

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

	private float batLevel;
	private int altitude;
	private int timeUntilTakeOff;
	private final int TAKE_OFF_DELAY = 2000;
	
	private int gestureConfig = 0;

	private String state = "init";
	private int pilotingState = 0;

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
	}

	public float getSpeedX() {
		return speedX;
	}

	public void setSpeedX(float speed) {
		this.speedX = speed;
		if (countObservers() > 0) {
			setChanged();
			notifyObservers(this.speedX);
		}
	}

	public float getSpeedY() {
		return speedY;
	}

	public void setSpeedY(float speed) {
		this.speedY = speed;
		if (countObservers() > 0) {
			setChanged();
			notifyObservers(this.speedY);
		}
	}

	public float getSpeedZ() {
		return speedZ;
	}

	public void setSpeedZ(float speed) {
		this.speedZ = speed;
		if (countObservers() > 0) {
			setChanged();
			notifyObservers(this.speedZ);
		}
	}

	public float getSpeedSpin() {
		return speedSpin;
	}

	public void setSpeedSpin(float speed) {
		this.speedSpin = speed;
		if (countObservers() > 0) {
			setChanged();
			notifyObservers(this.speedSpin);
		}
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
		if (countObservers() > 0) {
			setChanged();
			notifyObservers(state);
		}
	}

	public float getBatLevel() {
		return batLevel;
	}

	public void setBatLevel(float value) {
		this.batLevel = value;
		if (countObservers() > 0) {
			setChanged();
			notifyObservers(value);
		}
	}

	public float getAltitude() {
		return altitude;
	}

	public String getAltitudeString() {
		return String.valueOf(altitude);
	}

	public void setAltitude(float value) {
		this.altitude = (int) value;
		if (countObservers() > 0) {
			setChanged();
			notifyObservers(value);
		}
	}

	public int getTimeUntilTakeOff() {
		return timeUntilTakeOff;
	}

	public void setTimeUntilTakeOff(int timeUntilTakeOff) {
		this.timeUntilTakeOff = timeUntilTakeOff;
		if (countObservers() > 0) {
			setChanged();
			notifyObservers(timeUntilTakeOff);
		}
	}

	public int getTAKE_OFF_DELAY() {
		return TAKE_OFF_DELAY;
	}
	
	public int getGestureConfig() {
		return gestureConfig;
	}

	public void setGestureConfig(int gestureConfig) {
		this.gestureConfig = gestureConfig;
	}

	public int getPilotingState() {
		return pilotingState;
	}

	public void setPilotingState(int pilotingState) {
		this.pilotingState = pilotingState;
	}

	@Override
	public String toString() {
		return null;
	}
}
