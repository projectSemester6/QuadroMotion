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

public class NavDataListener {

	private BatteryListener batListener = null;

	public NavDataListener(IARDrone drone, NavDataController controller) {

		batListener = new BatteryListener() {

			public void batteryLevelChanged(int percentage) {
				controller.setBatteryLevel(percentage);
			}

			public void voltageChanged(int vbat_raw) {
			}
		};
		
		/**
		 * Battery Listener
		 */
		drone.getNavDataManager().addBatteryListener(batListener);

		/**
		 * Altitude Listener
		 */
		drone.getNavDataManager().addAltitudeListener(new AltitudeListener() {
			public void receivedAltitude(int altitude) {
				controller.setAltitude(altitude);
			}

			public void receivedExtendedAltitude(Altitude d) {

			}
		});

		/**
		 * Velocity Listener
		 */
		// drone.getNavDataManager().addVelocityListener(new VelocityListener()
		// {
		// public void velocityChanged(float vx, float vy, float vz) {
		//
		// }
		// });

		/**
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

		/**
		 * State Listener
		 */
		// drone.getNavDataManager().addStateListener(new StateListener() {
		// public void stateChanged(DroneState data) {
		//
		// }

		// @Override
		// public void controlStateChanged(ControlState state) {
		//
		// }
		// });
	}
	
	public BatteryListener getBatteryListener(){
		return batListener;
	}
}
