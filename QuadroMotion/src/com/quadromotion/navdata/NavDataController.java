package com.quadromotion.navdata;

import de.yadrone.base.IARDrone;
import de.yadrone.base.navdata.Altitude;
import de.yadrone.base.navdata.AltitudeListener;
import de.yadrone.base.navdata.AttitudeListener;
import de.yadrone.base.navdata.BatteryListener;

public class NavDataController {

	public NavDataController(IARDrone drone) {

		/**
		 * Attitude Listener
		 */
		drone.getNavDataManager().addAttitudeListener(new AttitudeListener() {

			public void attitudeUpdated(float pitch, float roll, float yaw) {
				System.out.println("Pitch: " + pitch + " Roll: " + roll + " Yaw: " + yaw);
			}

			public void attitudeUpdated(float pitch, float roll) {
			}

			public void windCompensation(float pitch, float roll) {
			}
		});

		/**
		 * Battery Listener
		 */
		drone.getNavDataManager().addBatteryListener(new BatteryListener() {

			public void batteryLevelChanged(int percentage) {
				System.out.println("Battery: " + percentage + " %");
			}

			public void voltageChanged(int vbat_raw) {
				System.out.println("Battery voltage: " + vbat_raw + " V");
			}
		});

		/**
		 * Altitude Listener
		 */
		drone.getNavDataManager().addAltitudeListener(new AltitudeListener() {
			public void receivedAltitude(int altitude) {
				System.out.println("Altitude: " + altitude);
			}

			public void receivedExtendedAltitude(Altitude d) {

			}
		});
	}
}
