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
import com.quadromotion.pilotingstates.PilotingStates;

import de.yadrone.base.IARDrone;

public class NavDataController {

	private Model model = null;
	private NavDataListener navDataListener = null;

	public NavDataController(Model model, IARDrone drone) {
		this.model = model;
		this.navDataListener = new NavDataListener(drone, this);
	}

	public void setBatteryLevel(int percentage) {
		if (percentage != model.getBatLevel())
			model.setBatLevel(percentage);
	}

	public void setAltitude(int altitude) {
		if (altitude != model.getAltitude())
			model.setAltitude(altitude);
	}

	public void setControlState(String string) {
		if (!string.equals(model.getControlState())) {
			model.setControlState(string);
		}
	}

	public NavDataListener getNavDataListener() {
		return navDataListener;
	}

	public void setNavDataListener(NavDataListener navDataListener) {
		this.navDataListener = navDataListener;
	}
}
