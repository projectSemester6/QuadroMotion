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

/**
 * Diese Schnittstelle definiert alle notwendigen Methoden zum Steuern der Drohne.
 * @author Gabriel
 *
 */
public interface IARDroneCommander {
	
	/**
	 * This method is needed to move the drone in every direction
	 * @param speedX the speed in direction X, can be positive or negative
	 * @param speedY the speed in direction Y, can be positive or negative
	 * @param speedZ the speed in direction Z, can be positive or negative
	 * @param speedSpin the speed to spin, can be positive or negative
	 */
	public void moveDrone(float speedX, float speedY, float speedZ, float speedSpin);
	
	/**
	 * This method is needed to send the take off command
	 */
	public void takeOff();
	
	/**
	 * This method is needed to send the hover command
	 */
	public void hover();
	
	/**
	 * This method is needed to send the landing command
	 */
	public void landing();
	
	/**
	 * This method is for cleaning up
	 */
	public void cleanup();

}
