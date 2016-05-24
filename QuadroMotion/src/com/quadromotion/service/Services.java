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

/**
 * This class is responsible for the conversion of the leap motion input to the
 * drone commands using the chosen configuration and handles the commands
 * according to the current state.
 * 
 * @author Gabriel Urech
 *
 */
public class Services {

	/**
	 * The converter list. This list contains every converter needed for each
	 * speed.
	 */
	private ArrayList<Converter> converterList = new ArrayList<Converter>();

	/**
	 * The config list. This list contains every configuration which lets you
	 * control the drone by the leap motion.
	 */
	private ArrayList<ConfigBase> configList = new ArrayList<ConfigBase>();

	/**
	 * The input controller interface. The input controller has to be set from
	 * the outside using the setInputController() method
	 */
	private IInputController controller = null;

	/**
	 * The take off delay. This is the delay from the take off command until it
	 * allows to send new commands to the drone. All commands from the leap will
	 * be ignored for this time.
	 */
	private final int TAKEOFF_DELAY = 5; // in Sekunden

	/**
	 * The landing delay. This is the delay from the landing command until it
	 * sets the next state to ready. All commands from the leap will be ignored
	 * for this time.
	 */
	private final int LAND_DELAY = 5; // in Sekunden

	/**
	 * The hovering delay. This delay defines how long the drone will continue
	 * to fly before it lands when there aren't any hands in the field of the
	 * leap motion or if the leap motion doesn't detect any hands.
	 */
	private final int HOVERING_DELAY = 5; // in Sekunden

	/**
	 * The position of the speed in direction x in the drone command array
	 */
	private final int speedX = 0;

	/**
	 * The position of the speed in direction y in the drone command array
	 */
	private final int speedY = 1;

	/**
	 * The position of the speed in direction z in the drone command array
	 */
	private final int speedZ = 2;

	/**
	 * The position of the spin speed in the drone command array
	 */
	private final int speedSpin = 3;

	/**
	 * The position of the take off command in the drone command array
	 */
	private final int takeOffCommand = 4;

	/**
	 * The position of the landing command in the drone command array
	 */
	private final int landingCommand = 5;

	/**
	 * The position of the number of hands in the drone command array
	 */
	private final int countHands = 6;

	/*
	 * some helpful variables
	 */
	private long startTakeOffCommandTime = 0;
	private long startTakeOffTime = 0;
	private long startLandingTime = 0;
	private long startHoveringWithoutHandsTime = 0;
	private long hoveringDuration = 0;

	/**
	 * Constructor
	 */
	public Services() {
		converterList = getConverterList();
		configList = getConfigList();
	}

	/**
	 * Computes the gestures from the Leap Motion to the defined drone commands
	 * and sends them to the final state machine.
	 * 
	 * @param leap
	 *            the Leap Motion
	 */
	public void computeGestures(LeapMotion leap) {
		fsm(configList.get(controller.getSelectedConfig()).convertLeapInput(leap));
	}

	/**
	 * Saves the drone commands to the model depending on the current state and
	 * handles the states depending on the drone commands.
	 * 
	 * @param droneCommandArray
	 *            the array containing drone commands
	 */
	private void fsm(int droneCommandArray[]) {

		switch (controller.getPilotingState()) {
		case PilotingStates.STATE_0_OFF:
			if (controller.isInputDeviceConnected())
				controller.setPilotingState(PilotingStates.STATE_1_INIT);
			break;

		case PilotingStates.STATE_1_INIT:
			startTakeOffCommandTime = 0;
			if (controller.getTimeUntilTakeOff() != controller.getTAKE_OFF_DELAY())
				controller.setTimeUntilTakeOff(controller.getTAKE_OFF_DELAY());

			if (droneCommandArray[countHands] == configList.get(controller.getSelectedConfig()).getCountHands())
				controller.setPilotingState(PilotingStates.STATE_2_READY);
			break;

		case PilotingStates.STATE_2_READY:

			if (droneCommandArray[countHands] != configList.get(controller.getSelectedConfig()).getCountHands()) {
				controller.setPilotingState(PilotingStates.STATE_1_INIT);
			}

			if (droneCommandArray[takeOffCommand] == 1) {
				if (startTakeOffCommandTime == 0)
					startTakeOffCommandTime = System.currentTimeMillis();
				controller.setTimeUntilTakeOff((int) (controller.getTAKE_OFF_DELAY()
						- (System.currentTimeMillis() - startTakeOffCommandTime)));

				if (controller.getTimeUntilTakeOff() <= 0) {
					controller.setPilotingState(PilotingStates.STATE_3_TAKINGOFF);
					controller.setTimeUntilTakeOff(controller.getTAKE_OFF_DELAY());
				}
			} else if (droneCommandArray[takeOffCommand] == 0 && startTakeOffCommandTime != 0) {
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
			if ((System.currentTimeMillis() - startTakeOffTime) > TAKEOFF_DELAY * 1000)
				controller.setPilotingState(PilotingStates.STATE_5_HOVERING);
			break;

		case PilotingStates.STATE_5_HOVERING:
			if (droneCommandArray[landingCommand] == 1) {
				startHoveringWithoutHandsTime = 0;
				controller.setPilotingState(PilotingStates.STATE_7_LANDING);
				break;
			}

			else if (droneCommandArray[countHands] != configList.get(controller.getSelectedConfig()).getCountHands()) {
				if (startHoveringWithoutHandsTime == 0)
					startHoveringWithoutHandsTime = System.currentTimeMillis();
				long timeNow = System.currentTimeMillis();
				hoveringDuration = timeNow - startHoveringWithoutHandsTime;
				if (hoveringDuration > HOVERING_DELAY * 1000) {
					controller.setPilotingState(PilotingStates.STATE_7_LANDING);
					startHoveringWithoutHandsTime = 0;
					break;
				}
			} else {
				startHoveringWithoutHandsTime = 0;
				hoveringDuration = 0;
				if (droneCommandArray[speedX] != 0 || droneCommandArray[speedY] != 0 || droneCommandArray[speedZ] != 0
						|| droneCommandArray[speedSpin] != 0) {
					controller.setPilotingState(PilotingStates.STATE_6_FLYING);
					break;
				}
			}
			break;

		case PilotingStates.STATE_6_FLYING:
			if (droneCommandArray[landingCommand] == 1) {
				controller.setPilotingState(PilotingStates.STATE_7_LANDING);
				break;
			}
			if (droneCommandArray[countHands] != configList.get(controller.getSelectedConfig()).getCountHands()
					|| (droneCommandArray[speedX] == 0 && droneCommandArray[speedY] == 0
							&& droneCommandArray[speedZ] == 0 && droneCommandArray[speedSpin] == 0)) {
				setSpeedToZero();
				controller.setPilotingState(PilotingStates.STATE_5_HOVERING);
				break;
			}
			controller.setSpeed(-droneCommandArray[speedX], -droneCommandArray[speedY], -droneCommandArray[speedZ],
					-droneCommandArray[speedSpin]);
			break;

		case PilotingStates.STATE_7_LANDING:
			setSpeedToZero();
			controller.setPilotingState(PilotingStates.STATE_8_WAITINGLANDING);
			startLandingTime = System.currentTimeMillis();
			break;

		case PilotingStates.STATE_8_WAITINGLANDING:
			if ((System.currentTimeMillis() - startLandingTime) > LAND_DELAY * 1000)
				controller.setPilotingState(PilotingStates.STATE_2_READY);
			break;

		default:
			break;
		}
	}

	/**
	 * Gets the converter list.
	 * 
	 * @return the converter list
	 */
	private ArrayList<Converter> getConverterList() {
		ArrayList<Converter> cl = new ArrayList<Converter>();

		// direction x
		cl.add(new Converter(OffsetConfig.MAX_ANGLE_X, OffsetConfig.MAX_SPEED_X, OffsetConfig.SPEED_OFFSET_X,
				OffsetConfig.ANGLE_OFFSET_X, OffsetConfig.FUNCTION_EXP_X));

		// direction y
		cl.add(new Converter(OffsetConfig.MAX_ANGLE_Y, OffsetConfig.MAX_SPEED_Y, OffsetConfig.SPEED_OFFSET_Y,
				OffsetConfig.ANGLE_OFFSET_Y, OffsetConfig.FUNCTION_EXP_Y));

		// direction z
		cl.add(new Converter(OffsetConfig.MAX_ANGLE_Z, OffsetConfig.MAX_SPEED_Z, OffsetConfig.SPEED_OFFSET_Z,
				OffsetConfig.ANGLE_OFFSET_Z, OffsetConfig.FUNCTION_EXP_Z));

		// spin
		cl.add(new Converter(OffsetConfig.MAX_ANGLE_SPIN, OffsetConfig.MAX_SPEED_SPIN, OffsetConfig.SPEED_OFFSET_SPIN,
				OffsetConfig.ANGLE_OFFSET_SPIN, OffsetConfig.FUNCTION_EXP_SPIN));
		return cl;
	}

	/**
	 * Gets the configuration list.
	 * 
	 * @return the configuration list
	 */
	private ArrayList<ConfigBase> getConfigList() {
		ArrayList<ConfigBase> cl = new ArrayList<ConfigBase>();
		cl.add(new Config_1_Two_Hands(converterList));
		cl.add(new Config_5_Right_Hand(converterList));
		cl.add(new Config_2_Two_Hands(converterList));
		return cl;
	}

	/**
	 * Sets every speed to zero.
	 */
	private void setSpeedToZero() {
		controller.setSpeed(0, 0, 0, 0);
	}

	/**
	 * Sets the state of the leap motion.
	 * 
	 * @param leapState
	 *            True if the leap motion is connected, else false.
	 */
	public void setLeapState(boolean leapState) {
		controller.setInputDeviceState(leapState);
		if (!controller.isInputDeviceConnected()) {
			controller.setPilotingState(PilotingStates.STATE_0_OFF);
		}
	}

	/**
	 * Sets the input controller.
	 * 
	 * @param ic
	 *            the input controller.
	 */
	public void setInputController(InputController ic) {
		this.controller = ic;
	}
}
