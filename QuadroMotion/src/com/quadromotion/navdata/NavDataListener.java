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

import de.yadrone.base.IARDrone;
import de.yadrone.base.navdata.Altitude;
import de.yadrone.base.navdata.AltitudeListener;
import de.yadrone.base.navdata.AttitudeListener;
import de.yadrone.base.navdata.BatteryListener;
import de.yadrone.base.navdata.ControlState;
import de.yadrone.base.navdata.DroneState;
import de.yadrone.base.navdata.StateListener;
import de.yadrone.base.navdata.VelocityListener;

/**
 * The navigation data listener.
 * 
 * @author Gabriel Urech
 *
 */
public class NavDataListener {

	/**
	 * Adds the different listener to the drone object.
	 * @param drone
	 *            the ardrone.
	 * @param controller
	 *            the navigation data controller <code>NavDataController</code>
	 */
	public NavDataListener(IARDrone drone, final NavDataController controller) {

		/*
		 * Battery Listener
		 */
		drone.getNavDataManager().addBatteryListener(new BatteryListener() {

			public void batteryLevelChanged(int percentage) {
				controller.setBatteryLevel(percentage);
			}

			public void voltageChanged(int vbat_raw) {
			}
		});

		/*
		 * Altitude Listener
		 */
		drone.getNavDataManager().addAltitudeListener(new AltitudeListener() {
			public void receivedAltitude(int altitude) {
				controller.setAltitude(altitude);
			}

			public void receivedExtendedAltitude(Altitude d) {

			}
		});

		/*
		 * Velocity Listener
		 */
		// drone.getNavDataManager().addVelocityListener(new VelocityListener()
		// {
		// public void velocityChanged(float vx, float vy, float vz) {
		//
		// }
		// });

		/*
		 * Attitude Listener
		 */
		// drone.getNavDataManager().addAttitudeListener(new AttitudeListener()
		// {
		//
		// public void attitudeUpdated(float pitch, float roll, float yaw) {
		// // System.out.println("Pitch: " + pitch + " Roll: " + roll + "
		// // Yaw: " + yaw);
		// }
		//
		// public void attitudeUpdated(float pitch, float roll) {
		// }
		//
		// public void windCompensation(float pitch, float roll) {
		// }
		// });

		/*
		 * State Listener
		 */
		drone.getNavDataManager().addStateListener(new StateListener() {
			public void stateChanged(DroneState data) {

			}

			@Override
			public void controlStateChanged(ControlState state) {
				controller.setControlState(state.toString());
			}
		});
	}
}
