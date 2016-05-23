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
package com.quadromotion.service;

import java.util.ArrayList;

import com.quadromotion.config.*;
import com.quadromotion.input.*;
import com.quadromotion.pilotingstates.PilotingStates;

public class Services {

	private ArrayList<Converter> converterList = new ArrayList<Converter>();
	private ArrayList<ConfigBase> configList = new ArrayList<ConfigBase>();

	private IInputController controller = null;
	private boolean leapConnected = false;

	private long startTakeOffCommandTime = 0;
	private long startTakeOffTime = 0;
	private long startLandingTime = 0;
	private long startHoveringWithoutHandsTime = 0;
	private long hoveringDuration = 0;

	private final int TAKEOFF_LAND_DELAY = 5; // in Sekunden

	public Services() {

		converterList.add(new Converter(OffsetConfig.MAX_ANGLE_X, OffsetConfig.MAX_SPEED_X, OffsetConfig.SPEED_OFFSET_X,
				OffsetConfig.ANGLE_OFFSET_X, OffsetConfig.FUNCTION_EXP_X));
		converterList.add(new Converter(OffsetConfig.MAX_ANGLE_Y, OffsetConfig.MAX_SPEED_Y, OffsetConfig.SPEED_OFFSET_Y,
				OffsetConfig.ANGLE_OFFSET_Y, OffsetConfig.FUNCTION_EXP_Y));
		converterList.add(new Converter(OffsetConfig.MAX_ANGLE_Z, OffsetConfig.MAX_SPEED_Z, OffsetConfig.SPEED_OFFSET_Z,
				OffsetConfig.ANGLE_OFFSET_Z, OffsetConfig.FUNCTION_EXP_Z));
		converterList.add(new Converter(OffsetConfig.MAX_ANGLE_SPIN, OffsetConfig.MAX_SPEED_SPIN,
				OffsetConfig.SPEED_OFFSET_SPIN, OffsetConfig.ANGLE_OFFSET_SPIN, OffsetConfig.FUNCTION_EXP_SPIN));

		configList.add(new Config_1_Two_Hands(converterList));
		configList.add(new Config_5_Right_Hand(converterList));
		configList.add(new Config_2_Two_Hands(converterList));
	}

	public void computeGestures(LeapMotion leap) {
		fsm(configList.get(controller.getSelectedConfig()).convertLeapInput(leap));
	}

	private void fsm(int modelValues[]) {

		int speedX = modelValues[0];
		int speedY = modelValues[1];
		int speedZ = modelValues[2];
		int speedSpin = modelValues[3];
		int takeOffGesture = modelValues[4];
		int landingGesture = modelValues[5];
		int countHands = modelValues[6];

		switch (controller.getPilotingState()) {
		case PilotingStates.STATE_0_OFF:
			if (controller.isInputDeviceConnected())
				controller.setPilotingState(PilotingStates.STATE_1_INIT);
			break;

		case PilotingStates.STATE_1_INIT:
			startTakeOffCommandTime = 0;
			if (controller.getTimeUntilTakeOff() != controller.getTAKE_OFF_DELAY())
				controller.setTimeUntilTakeOff(controller.getTAKE_OFF_DELAY());

			if (countHands == configList.get(controller.getSelectedConfig()).getCountHands())
				controller.setPilotingState(PilotingStates.STATE_2_READY);
			break;

		case PilotingStates.STATE_2_READY:

			if (countHands != configList.get(controller.getSelectedConfig()).getCountHands()) {
				controller.setPilotingState(PilotingStates.STATE_1_INIT);
			}

			if (takeOffGesture == 1) {
				if (startTakeOffCommandTime == 0)
					startTakeOffCommandTime = System.currentTimeMillis();
				controller.setTimeUntilTakeOff((int) (controller.getTAKE_OFF_DELAY()
						- (System.currentTimeMillis() - startTakeOffCommandTime)));

				if (controller.getTimeUntilTakeOff() <= 0) {
					controller.setPilotingState(PilotingStates.STATE_3_TAKINGOFF);
					controller.setTimeUntilTakeOff(controller.getTAKE_OFF_DELAY());
				}
			} else if (takeOffGesture == 0 && startTakeOffCommandTime != 0) {
				controller.setTimeUntilTakeOff(controller.getTAKE_OFF_DELAY());
				startTakeOffCommandTime = 0;
			}
			break;

		case PilotingStates.STATE_3_TAKINGOFF:
			startTakeOffCommandTime = 0;
			controller.setPilotingState(PilotingStates.STATE_4_WAITINGTAKEOFF);
			startTakeOffTime = System.currentTimeMillis();
			break;

		case PilotingStates.STATE_4_WAITINGTAKEOFF:
			if ((System.currentTimeMillis() - startTakeOffTime) > TAKEOFF_LAND_DELAY
					* 1000)
				controller.setPilotingState(PilotingStates.STATE_5_HOVERING);
			break;

		case PilotingStates.STATE_5_HOVERING:
			if (landingGesture == 1) {
				startHoveringWithoutHandsTime = 0;
				controller.setPilotingState(PilotingStates.STATE_7_LANDING);
				break;
			}

			else if (countHands != configList.get(controller.getSelectedConfig()).getCountHands()) {
				if (startHoveringWithoutHandsTime == 0)
					startHoveringWithoutHandsTime = System.currentTimeMillis();
				long timeNow = System.currentTimeMillis();
				hoveringDuration = timeNow - startHoveringWithoutHandsTime;
				if (hoveringDuration > TAKEOFF_LAND_DELAY * 1000) {
					controller.setPilotingState(PilotingStates.STATE_7_LANDING);
					startHoveringWithoutHandsTime = 0;
					break;
				}
			} else {
				startHoveringWithoutHandsTime = 0;
				hoveringDuration = 0;
				if (speedX != 0 || speedY != 0 || speedZ != 0 || speedSpin != 0) {
					controller.setPilotingState(PilotingStates.STATE_6_FLYING);
					break;
				}
			}

			break;

		case PilotingStates.STATE_6_FLYING:
			if (landingGesture == 1) {
				controller.setPilotingState(PilotingStates.STATE_7_LANDING);
				break;
			}

			if (countHands != configList.get(controller.getSelectedConfig()).getCountHands()
					|| (speedX == 0 && speedY == 0 && speedZ == 0 && speedSpin == 0)) {
				setSpeedToZero();
				controller.setPilotingState(PilotingStates.STATE_5_HOVERING);
				break;
			}
			controller.setSpeed(-speedX, -speedY, -speedZ, -speedSpin);
			break;

		case PilotingStates.STATE_7_LANDING:
			setSpeedToZero();
			controller.setPilotingState(PilotingStates.STATE_8_WAITINGLANDING);
			startLandingTime = System.currentTimeMillis();
			break;

		case PilotingStates.STATE_8_WAITINGLANDING:
			if ((System.currentTimeMillis() - startLandingTime) > TAKEOFF_LAND_DELAY * 1000)
				controller.setPilotingState(PilotingStates.STATE_2_READY);
			break;

		default:
			break;
		}
	}

	private void setSpeedToZero() {
		controller.setSpeed(0, 0, 0, 0);
	}

	public boolean isLeapConnected() {
		return leapConnected;
	}

	public void setLeapConnected(boolean leapConnected) {
		this.leapConnected = leapConnected;
		controller.setInputDeviceState(this.leapConnected);
		if (!controller.isInputDeviceConnected()) {
			controller.setPilotingState(PilotingStates.STATE_0_OFF);
		}
	}

	public void setInputController(InputController ic) {
		this.controller = ic;
	}
}
