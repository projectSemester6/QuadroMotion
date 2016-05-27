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

import de.yadrone.base.IARDrone;
import de.yadrone.base.command.CommandManager;
import de.yadrone.base.command.FlightAnimation;
import de.yadrone.base.command.LEDAnimation;

/**
 * This class models the communication between the computer and the drone.
 * 
 * @author Gabriel Urech
 *
 */
public class ARDroneCommander {

	/**
	 * The drone.
	 */
	private IARDrone drone = null;

	/**
	 * The command manager.
	 */
	private CommandManager cmd = null;

	/**
	 * Allocates a new <code> ARDroneCommander</code> object so that it has <code>drone</code> as the drone. 
	 * 
	 * @param drone
	 *            the drone
	 */
	public ARDroneCommander(IARDrone drone) {
		this.drone = drone;
		initialize();
	}

	/**
	 * Moves the drone in all given direction.
	 * 
	 * @param speedX
	 *            the speed in direction X, can be positive (forward) or
	 *            negative (backward)
	 * @param speedY
	 *            the speed in direction Y, can be positive (right) or negative
	 *            (left)
	 * @param speedZ
	 *            the speed in direction Z, can be positive (down) or negative
	 *            (up)
	 * @param speedSpin
	 *            the speed to spin, can be positive (clockwise) or negative
	 *            (counterclockwise)
	 */
	public void moveDrone(float speedX, float speedY, float speedZ, float speedSpin) {
		cmd.move(perc2float(speedY), perc2float(-speedX), perc2float(-speedZ), perc2float(speedSpin));
	}

	/**
	 * Sends the take off command
	 */
	public void takeOff() {
		cmd.takeOff();
	}

	/**
	 * Sends the hover command
	 */
	public void hover() {
		cmd.hover();
	}

	/**
	 * Sends the landing command
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
	 * Flips ahead
	 */
	public void flipAhead() {
		cmd.animate(FlightAnimation.FLIP_AHEAD);
	}

	/**
	 * Flips behind
	 */
	public void flipBehind() {
		cmd.animate(FlightAnimation.FLIP_BEHIND);
	}

	/**
	 * Flips left
	 */
	public void flipLeft() {
		cmd.animate(FlightAnimation.FLIP_LEFT);
	}

	/**
	 * Flips right
	 */
	public void flipRight() {
		cmd.animate(FlightAnimation.FLIP_RIGHT);
	}

	/**
	 * Animates the LEDs.<br>
	 * Color: orange<br>
	 * Frequency: 2 Hz
	 * Duration: 5 s
	 */
	public void animateLEDs() {
		cmd.setLedsAnimation(LEDAnimation.BLINK_ORANGE, 2, 5);
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
		cmd.setMaxAltitude(10000);
		cmd.setMinAltitude(60);
	}
}
