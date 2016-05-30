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
package com.quadromotion.config;

import com.quadromotion.input.LeapMotion;

/**
 * This class is the abstract class for all configuration classes.
 * @author Gabriel Urech
 *
 */
public abstract class ConfigBase {

	/**
	 * Converts the input values from the leap motion to the drone commands.
	 * @param leap the leap motion containing all input values.
	 * @return The array containing all commands used to navigate the drone.
	 */
	public abstract int[] convertLeapInput(LeapMotion leap);
	
	/**
	 * 
	 * @return The number of the hands used in the current configuration.
	 */
	public abstract int getCountHands();
}
