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
package com.quadromotion.drone;

import de.yadrone.base.ARDrone;
import de.yadrone.base.IARDrone;
import de.yadrone.base.command.CommandManager;
import de.yadrone.base.command.FlightAnimation;
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
public class ARDroneCommander {

	/**
	 * The drone
	 */
	private IARDrone drone = null;

	/**
	 * The command manager
	 */
	private CommandManager cmd = null;

	private boolean isConnected;

	// /**
	// * Constructor I
	// *
	// * @param drone
	// * the drone
	// */
	// public ARDroneCommander() {
	// this.drone = new ARDrone();
	// drone.start();
	// initialize();
	// }

	/**
	 * Constructor I
	 * 
	 * @param drone
	 *            the drone
	 */
	public ARDroneCommander(IARDrone drone) {
		this.drone = drone;
		initialize();
	}

	/**
	 * move the drone in every direction
	 * 
	 * @param speedX
	 *            the speed in direction X, can be positive (forward) or
	 *            negative (backward)
	 * @param speedY
	 *            the speed in direction Y, can be positive (right) or negative
	 *            (left)
	 * @param speedZ
	 *            the speed in direction Z, can be positive (up) or negative
	 *            (down)
	 * @param speedSpin
	 *            the speed to spin, can be positive (clockwise) or negative
	 *            (counterclockwise)
	 */
	public void moveDrone(float speedX, float speedY, float speedZ, float speedSpin) {

		// speedX = perc2float(speedX);
		// speedY = -perc2float(speedY);
		// speedZ = perc2float(speedZ);
		// speedSpin = perc2float(speedSpin);

		cmd.move(perc2float(speedY), perc2float(-speedX), perc2float(-speedZ), perc2float(speedSpin));

		// cmd.forward(20);
	}

	/**
	 * sends the take off command
	 */
	public void takeOff() {
		cmd.takeOff();
	}

	/**
	 * sends the hover command
	 */
	public void hover() {
		cmd.hover();
	}

	/**
	 * sends the landing command
	 */
	public void landing() {
		cmd.landing();
	}

	private float perc2float(float speed) {
		if (speed == 0)
			return 0;
		return (speed / 100.0f);
	}

	/**
	 * flips ahead
	 */
	public void flipAhead() {
		cmd.animate(FlightAnimation.FLIP_AHEAD);
	}

	/**
	 * flips behind
	 */
	public void flipBehind() {
		cmd.animate(FlightAnimation.FLIP_BEHIND);
	}

	/**
	 * flips left
	 */
	public void flipLeft() {
		cmd.animate(FlightAnimation.FLIP_LEFT);
	}

	/**
	 * flips right
	 */
	public void flipRight() {
		cmd.animate(FlightAnimation.FLIP_RIGHT);
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
		drone.stop();
	}

	/**
	 * Initializes the command manager
	 */
	private void initialize() {
		cmd = drone.getCommandManager();
		cmd.setMaxAltitude(3000);
		cmd.setMinAltitude(60);
		isConnected = cmd.isConnected();
	}
}
