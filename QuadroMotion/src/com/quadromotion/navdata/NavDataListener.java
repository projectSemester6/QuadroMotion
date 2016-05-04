package com.quadromotion.navdata;

import com.quadromotion.model.Model;

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

	public NavDataListener(IARDrone drone, Model model) {
		
		
		/**
		 * Velocity Listener
		 */
		drone.getNavDataManager().addVelocityListener(new VelocityListener(){
			public void velocityChanged(float vx, float vy, float vz){
				
			}
		});

		/**
		 * Attitude Listener
		 */
		drone.getNavDataManager().addAttitudeListener(new AttitudeListener() {

			public void attitudeUpdated(float pitch, float roll, float yaw) {
//				System.out.println("Pitch: " + pitch + " Roll: " + roll + " Yaw: " + yaw);
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
//				System.out.println("Battery: " + percentage + " %");
//				model.setBatLevel(percentage);
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
				model.setAltitude(altitude);
			}

			public void receivedExtendedAltitude(Altitude d) {

			}
		});
		
		/**
		 * State Listener
		 */
		drone.getNavDataManager().addStateListener(new StateListener(){
			public void stateChanged(DroneState data)
			{
				System.out.println(data.toString());
			}

			@Override
			public void controlStateChanged(ControlState state) {
				
			}
		});
	}
}
