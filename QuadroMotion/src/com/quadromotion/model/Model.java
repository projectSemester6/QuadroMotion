package com.quadromotion.model;

import java.util.Observable;

import com.quadromotion.model.convertion.AngleToSpeedConverter;
import com.quadromotion.config.OffsetConfig;

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
	private boolean oneHandControl;

	private AngleToSpeedConverter convertX = null;
	private AngleToSpeedConverter convertY = null;
	private AngleToSpeedConverter convertZ = null;
	private AngleToSpeedConverter convertSpin = null;

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
		this.oneHandControl = false;

		convertX = new AngleToSpeedConverter(OffsetConfig.MAX_ANGLE_X, OffsetConfig.MAX_SPEED_X,
				OffsetConfig.SPEED_OFFSET_X, OffsetConfig.ANGLE_OFFSET_X, OffsetConfig.FUNCTION_EXP_X);
		convertY = new AngleToSpeedConverter(OffsetConfig.MAX_ANGLE_Y, OffsetConfig.MAX_SPEED_Y,
				OffsetConfig.SPEED_OFFSET_Y, OffsetConfig.ANGLE_OFFSET_Y, OffsetConfig.FUNCTION_EXP_Y);
		convertZ = new AngleToSpeedConverter(OffsetConfig.MAX_ANGLE_Z, OffsetConfig.MAX_SPEED_Z,
				OffsetConfig.SPEED_OFFSET_Z, OffsetConfig.ANGLE_OFFSET_Z, OffsetConfig.FUNCTION_EXP_Z);
		convertSpin = new AngleToSpeedConverter(OffsetConfig.MAX_ANGLE_SPIN, OffsetConfig.MAX_SPEED_SPIN,
				OffsetConfig.SPEED_OFFSET_SPIN, OffsetConfig.ANGLE_OFFSET_SPIN, OffsetConfig.FUNCTION_EXP_SPIN);
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

	public boolean isFlying() {
		return isFlying;
	}

	public void setIsFlying(boolean state) {
		this.isFlying = state;
	}

	public boolean isConnected() {
		return isConnected;
	}
}
