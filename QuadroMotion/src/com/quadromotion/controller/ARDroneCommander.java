package com.quadromotion.controller;

import de.yadrone.base.ARDrone;
import de.yadrone.base.IARDrone;
import de.yadrone.base.command.CommandManager;
import de.yadrone.base.command.LEDAnimation;

/**
 * This class models the communication between the computer and the drone
 * 
 * @author Gabriel
 * @param IARDrone
 *            the drone
 * @param CommandManager
 *            the drone command manager
 */
public class ARDroneCommander implements IARDroneCommander {

	private boolean isConnected;
	/**
	 * The drone
	 */
	private IARDrone drone = null;

	/**
	 * The command manager
	 */
	private CommandManager cmd = null;

	/**
	 * Constructor I
	 * 
	 * @param drone
	 *            the drone
	 */
	public ARDroneCommander() {
		this.drone = new ARDrone();
		drone.start();
		initialize();
	}
	
	/**
	 * Constructor II
	 * 
	 * @param drone
	 *            the drone
	 */
	public ARDroneCommander(IARDrone drone) {
		this.drone = drone;
		initialize();
	}

	/**
	 * Initializes the command manager
	 */
	private void initialize() {
		cmd = drone.getCommandManager();
		cmd.setMaxAltitude(1500);
		cmd.setMinAltitude(80);
		isConnected = cmd.isConnected();
	}

	/**
	 * move the drone in every direction
	 * 
	 * @param speedX
	 *            the speed in direction X, can be positive (forward) or negative (backward)
	 * @param speedY
	 *            the speed in direction Y, can be positive (right) or negative (left)
	 * @param speedZ
	 *            the speed in direction Z, can be positive (up) or negative (down)
	 * @param speedSpin
	 *            the speed to spin, can be positive (clockwise) or negative (counterclockwise)
	 */
	public void moveDrone(float speedX, float speedY, float speedZ, float speedSpin) {
		if (speedX != 0)
			speedX = perc2float(speedX);
		if (speedY != 0)
			speedY = -perc2float(speedY);
		if (speedZ != 0)
			speedZ = perc2float(speedZ);
		if (speedSpin != 0)
			speedSpin = perc2float(speedSpin);
		cmd.move(speedY, speedX, speedZ, speedSpin);
		cmd.forward(20);
	}

	private float perc2float(float speed) {
		return (speed / 100.0f);
	}

	/**
	 * sends the hover command
	 */
	public void hover() {
		cmd.hover();
	}

	/**
	 * sends the take off command
	 */
	public void takeOff() {
		cmd.takeOff();
	}

	/**
	 * sends the landing command
	 */
	public void landing() {
		cmd.landing();
	}

	/**
	 * animates the LEDs
	 */
	public void animateLEDs() {
		cmd.setLedsAnimation(LEDAnimation.BLINK_ORANGE, 6, 2);
	}

	/**
	 * Cleans up the connection to the drone
	 */
	public void cleanup() {
		if (cmd.isConnected() && cmd != null)
			cmd.close();
	}
}
