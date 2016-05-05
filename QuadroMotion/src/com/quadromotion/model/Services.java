package com.quadromotion.model;

import com.quadromotion.config.*;
import com.quadromotion.gestures.LeapMotion;
import com.quadromotion.model.convertion.Converter;
import com.quadromotion.pilotingstates.PilotingStates;

public class Services {

	private Converter convertList[] = new Converter[4];
	private ConfigBase configList[] = new ConfigBase[3];

	private Model model = null;

	private long startTakeOffCommandTime = 0;
	private long startTakeOffTime = 0;
	private long startLandingTime = 0;
	private long startHoveringWithoutHandsTime = 0;

	private final int TAKEOFF_LAND_DELAY = 5; // in Sekunden

	public Services(Model model) {

		this.model = model;

		for (int i = 0; i < 4; i++) {
			switch (i) {

			case 0:
				convertList[i] = new Converter(OffsetConfig.MAX_ANGLE_X, OffsetConfig.MAX_SPEED_X,
						OffsetConfig.SPEED_OFFSET_X, OffsetConfig.ANGLE_OFFSET_X, OffsetConfig.FUNCTION_EXP_X);
				break;
			case 1:
				convertList[i] = new Converter(OffsetConfig.MAX_ANGLE_Y, OffsetConfig.MAX_SPEED_Y,
						OffsetConfig.SPEED_OFFSET_Y, OffsetConfig.ANGLE_OFFSET_Y, OffsetConfig.FUNCTION_EXP_Y);
				break;
			case 2:
				convertList[i] = new Converter(OffsetConfig.MAX_ANGLE_Z, OffsetConfig.MAX_SPEED_Z,
						OffsetConfig.SPEED_OFFSET_Z, OffsetConfig.ANGLE_OFFSET_Z, OffsetConfig.FUNCTION_EXP_Z);
				break;
			case 3:
				convertList[i] = new Converter(OffsetConfig.MAX_ANGLE_SPIN, OffsetConfig.MAX_SPEED_SPIN,
						OffsetConfig.SPEED_OFFSET_SPIN, OffsetConfig.ANGLE_OFFSET_SPIN, OffsetConfig.FUNCTION_EXP_SPIN);
				break;
			}
		}

		for (int i = 0; i < 3; i++) {
			switch (i) {
			case 0:
				configList[i] = new Config_1(convertList);
				break;
			case 1:
				configList[i] = new Config_2(convertList);
			default:
				break;
			}
		}
	}

	public void computeGestures(LeapMotion leap) {
		int modelValues[] = configList[model.getGestureConfig()].convertLeapInput(leap);
		fsm(modelValues);
	}

	private void fsm(int modelValues[]) {

		int speedX = modelValues[0];
		int speedY = modelValues[1];
		int speedZ = modelValues[2];
		int speedSpin = modelValues[3];
		int takeOffGesture = modelValues[4];
		int landingGesture = modelValues[5];
		int countHands = modelValues[6];

		switch (model.getPilotingState()) {
		case PilotingStates.STATE_0_INIT:
			startTakeOffCommandTime = 0;
			model.setTimeUntilTakeOff(model.getTAKE_OFF_DELAY());
			if (countHands == configList[model.getGestureConfig()].getCountHands())
				model.setPilotingState(PilotingStates.STATE_1_READY);
			break;
		case PilotingStates.STATE_1_READY:
			if (countHands != configList[model.getGestureConfig()].getCountHands()) {
				model.setPilotingState(PilotingStates.STATE_0_INIT);
			}

			if (takeOffGesture == 1) {
				if (startTakeOffCommandTime == 0)
					startTakeOffCommandTime = System.currentTimeMillis();
				model.setTimeUntilTakeOff(
						(int) (model.getTAKE_OFF_DELAY() - (System.currentTimeMillis() - startTakeOffCommandTime)));

				if (model.getTimeUntilTakeOff() <= 0) {
					model.setPilotingState(PilotingStates.STATE_2_TAKINGOFF);
					model.setTimeUntilTakeOff(model.getTAKE_OFF_DELAY());
				}
			} else if (takeOffGesture == 0 && startTakeOffCommandTime != 0) {
				model.setTimeUntilTakeOff(model.getTAKE_OFF_DELAY());
				startTakeOffCommandTime = 0;
			}

			break;

		case PilotingStates.STATE_2_TAKINGOFF:
			startTakeOffCommandTime = 0;
			model.setPilotingState(PilotingStates.STATE_3_WAITINGTAKEOFF);
			startTakeOffTime = System.currentTimeMillis();
			break;
		case PilotingStates.STATE_3_WAITINGTAKEOFF:
			if (/* takeOffGesture > -10 && */ (System.currentTimeMillis() - startTakeOffTime) > TAKEOFF_LAND_DELAY
					* 1000/* && model.getAltitude() > 0 */)
				model.setPilotingState(PilotingStates.STATE_4_HOVERING);
			break;
		case PilotingStates.STATE_4_HOVERING:
			if (startHoveringWithoutHandsTime == 0)
				startHoveringWithoutHandsTime = System.currentTimeMillis();
			if (((countHands == configList[model.getGestureConfig()].getCountHands()) && (landingGesture == 1))
					|| ((System.currentTimeMillis() - startHoveringWithoutHandsTime) > TAKEOFF_LAND_DELAY * 1000)) {
				startHoveringWithoutHandsTime = 0;
				model.setPilotingState(PilotingStates.STATE_6_LANDING);
				break;
			}

			if (countHands == configList[model.getGestureConfig()].getCountHands()
					&& (speedX != 0 || speedY != 0 || speedZ != 0 || speedSpin != 0)) {
				startHoveringWithoutHandsTime = 0;
				model.setPilotingState(PilotingStates.STATE_5_FLYING);
				break;
			}

			break;
		case PilotingStates.STATE_5_FLYING:
			if (landingGesture == 1) {
				model.setPilotingState(PilotingStates.STATE_6_LANDING);
				break;
			}

			if ((countHands < configList[model.getGestureConfig()].getCountHands())
					|| (speedX == 0 && speedY == 0 && speedZ == 0 && speedSpin == 0)) {
				setSpeedToZero();
				startHoveringWithoutHandsTime = System.currentTimeMillis();
				model.setPilotingState(PilotingStates.STATE_4_HOVERING);
				break;
			}

			model.setSpeedX(-speedX);
			model.setSpeedY(-speedY);
			model.setSpeedZ(-speedZ);
			model.setSpeedSpin(-speedSpin);

			break;
		case PilotingStates.STATE_6_LANDING:
			setSpeedToZero();
			model.setPilotingState(PilotingStates.STATE_7_WAITINGLANDING);
			startLandingTime = System.currentTimeMillis();
			break;
		case PilotingStates.STATE_7_WAITINGLANDING:
			if ((System.currentTimeMillis() - startLandingTime) > TAKEOFF_LAND_DELAY * 1000)
				// if (model.getAltitude() <= 10)
				model.setPilotingState(PilotingStates.STATE_1_READY);
			break;
		default:
			break;
		}
	}

	private void setSpeedToZero() {
		model.setSpeedX(0);
		model.setSpeedY(0);
		model.setSpeedZ(0);
		model.setSpeedSpin(0);
	}
}
