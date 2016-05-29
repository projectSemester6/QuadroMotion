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
package com.quadromotion.navdata;

import com.quadromotion.model.Model;

import de.yadrone.base.IARDrone;

/**
 * This class stores the data received from the drone.
 * 
 * @author Gabriel Urech
 *
 */
public class NavDataController {

	/**
	 * The model.
	 */
	private Model model = null;

	/**
	 * Allocates a new <code>NavDataController</code> object so that it has
	 * <code>model</code> as the model and has <code>drone</code> as the drone.
	 * 
	 * @param model
	 *            the reference to the model object.
	 * @param drone
	 *            the reference to the drone object.
	 */
	public NavDataController(Model model, IARDrone drone) {
		this.model = model;
		new NavDataListener(drone, this);
	}

	/**
	 * Stores the battery level.
	 * 
	 * @param percentage
	 *            the level of the battery in percent.
	 */
	public void setBatteryLevel(int percentage) {
		if (percentage != model.getBatLevel())
			model.setBatLevel(percentage);
	}

	/**
	 * Stores the altitude.
	 * 
	 * @param altitude
	 *            the altitude of the drone.
	 */
	public void setAltitude(int altitude) {
		if (altitude != model.getAltitude())
			model.setAltitude(altitude);
	}

	/**
	 * Stores the control state of the drone.
	 * @param string
	 */
	public void setControlState(String string) {
		if (!string.equals(model.getControlState())) {
			model.setControlState(string);
		}
	}
}
