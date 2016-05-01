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
	private boolean takeOffCommand;
	private boolean landingCommand;
	private boolean hoverCommand;
	private boolean isFlying;
	private boolean isHovering;
	private boolean isConnected;
	private boolean isTakingOff;
	private boolean isLanding;
	private float batLevel;
	private float altitude;

	private String state = "init";
	private String prevState;

	/**
	 * Constructor
	 */
	public Model() {
		super();

		this.speedX = 0;
		this.speedY = 0;
		this.speedZ = 0;
		this.speedSpin = 0;
		this.takeOffCommand = false;
		this.landingCommand = false;
		this.hoverCommand = false;
		this.isFlying = false;
		this.isHovering = false;
		this.isConnected = false;
//		this.batLevel = new SimpleFloatProperty();
//		this.altutude = new SimpleFloatProperty();
//		this.status = new SimpleFloatProperty();
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

	public boolean getTakeOffCommand() {
		return takeOffCommand;
	}

	public void setTakeOffCommand(boolean command) {
		this.takeOffCommand = command;
		if (countObservers() > 0) {
			setChanged();
			notifyObservers(this.takeOffCommand);
		}
	}

	public boolean getLandingCommand() {
		return landingCommand;
	}

	public void setLandingCommand(boolean command) {
		this.landingCommand = command;
		if (countObservers() > 0) {
			setChanged();
			notifyObservers(this.landingCommand);
		}
	}

	public boolean getHoverCommand() {
		return hoverCommand;
	}

	public void setHoverCommand(boolean command) {
		this.hoverCommand = command;
		if (countObservers() > 0) {
			setChanged();
			notifyObservers(this.hoverCommand);
		}
	}

	public boolean isHovering() {
		return isHovering;
	}

	public void setIsHovering(boolean state) {
		this.isHovering = state;
		if (countObservers() > 0) {
			setChanged();
			notifyObservers(state);
		}
	}

	public boolean isFlying() {
		return isFlying;
	}

	public void setIsFlying(boolean state) {
		this.isFlying = state;
		if (countObservers() > 0) {
			setChanged();
			notifyObservers(state);
		}
	}

	public boolean isConnected() {
		return isConnected;
	}

	public void setIsConnected(boolean state) {
		this.isConnected = state;
		if (countObservers() > 0) {
			setChanged();
			notifyObservers(state);
		}
	}

	public boolean isTakingOff() {
		return isTakingOff;
	}

	public void setTakingOff(boolean state) {
		this.isTakingOff = state;
		if (countObservers() > 0) {
			setChanged();
			notifyObservers(state);
		}
	}

	public boolean isLanding() {
		return isLanding;
	}

	public void setLanding(boolean state) {
		this.isLanding = state;
		if (countObservers() > 0) {
			setChanged();
			notifyObservers(state);
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

	public void setAltitude(float value) {
		this.altitude = value;
		if (countObservers() > 0) {
			setChanged();
			notifyObservers(value);
		}
	}

	public String getPrevState() {
		return prevState;
	}

	public void setPrevState(String prevState) {
		this.prevState = prevState;
	}
	
	public String toString(float value){
		return String.valueOf(value);
	}
}
