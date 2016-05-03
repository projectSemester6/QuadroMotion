package com.quadromotion.model;

import com.quadromotion.config.OffsetConfig;
import com.quadromotion.gestures.LeapMotion;
import com.quadromotion.model.convertion.Converter;
import com.quadromotion.pilotingstates.PilotingStates;

public class Services {

	// private AngleToSpeedConverter convertX = null;
	// private AngleToSpeedConverter convertY = null;
	// private AngleToSpeedConverter convertZ = null;
	// private AngleToSpeedConverter convertSpin = null;

	private Converter convertList[] = new Converter[4];
	private ConfigBase configList[] = new ConfigBase[3];

	private Model model = null;

	private long startTime = 0;

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
			default:
				break;
			}
		}

		// convertX = new AngleToSpeedConverter(OffsetConfig.MAX_ANGLE_X,
		// OffsetConfig.MAX_SPEED_X,
		// OffsetConfig.SPEED_OFFSET_X, OffsetConfig.ANGLE_OFFSET_X,
		// OffsetConfig.FUNCTION_EXP_X);
		// convertY = new AngleToSpeedConverter(OffsetConfig.MAX_ANGLE_X,
		// OffsetConfig.MAX_SPEED_Y,
		// OffsetConfig.SPEED_OFFSET_Y, OffsetConfig.ANGLE_OFFSET_Y,
		// OffsetConfig.FUNCTION_EXP_Y);
		// convertZ = new AngleToSpeedConverter(OffsetConfig.MAX_ANGLE_Z,
		// OffsetConfig.MAX_SPEED_Z,
		// OffsetConfig.SPEED_OFFSET_Z, OffsetConfig.ANGLE_OFFSET_Z,
		// OffsetConfig.FUNCTION_EXP_Z);
		// convertSpin = new AngleToSpeedConverter(OffsetConfig.MAX_ANGLE_SPIN,
		// OffsetConfig.MAX_SPEED_SPIN,
		// OffsetConfig.SPEED_OFFSET_SPIN, OffsetConfig.ANGLE_OFFSET_SPIN,
		// OffsetConfig.FUNCTION_EXP_SPIN);

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
			if (countHands > 1)
				model.setPilotingState(PilotingStates.STATE_1_READY);
			break;
		case PilotingStates.STATE_1_READY:
			if (countHands < 2)
				model.setPilotingState(PilotingStates.STATE_0_INIT);

			if (takeOffGesture < -35) {
				if (startTime == 0)
					startTime = System.currentTimeMillis();
				model.setTimeUntilTakeOff((int) (model.getTAKE_OFF_DELAY() - (System.currentTimeMillis() - startTime)));
				if (model.getTimeUntilTakeOff() <= 0) {
					model.setPilotingState(PilotingStates.STATE_2_TAKINGOFF);
				}
			} else if (takeOffGesture >= -35) {
				model.setTimeUntilTakeOff(model.getTAKE_OFF_DELAY());
				startTime = 0;
			}

			break;

		case PilotingStates.STATE_2_TAKINGOFF:
			startTime = 0;
			model.setPilotingState(PilotingStates.STATE_3_WAITINGTAKEOFF);
			break;
		case PilotingStates.STATE_3_WAITINGTAKEOFF:
			if (takeOffGesture > -10 && model.getAltitude() > 0)
				model.setPilotingState(PilotingStates.STATE_4_HOVERING);
			break;
		case PilotingStates.STATE_4_HOVERING:

			if (landingGesture > 35) {
				model.setPilotingState(PilotingStates.STATE_6_LANDING);
				break;
			}

			if (countHands == 2 && (speedX != 0 || speedY != 0 || speedZ != 0 || speedSpin != 0)) {
				model.setPilotingState(PilotingStates.STATE_5_FLYING);
				break;
			}

			break;
		case PilotingStates.STATE_5_FLYING:
			if (landingGesture > 35) {
				model.setPilotingState(PilotingStates.STATE_6_LANDING);
				break;
			}

			if (countHands < 2) {
				model.setPilotingState(PilotingStates.STATE_4_HOVERING);
				model.setSpeedX(0);
				model.setSpeedY(0);
				model.setSpeedZ(0);
				model.setSpeedSpin(0);
				break;
			}

			model.setSpeedX(-speedX);
			model.setSpeedY(-speedY);
			model.setSpeedZ(-speedZ);
			model.setSpeedSpin(-speedSpin);

			break;
		case PilotingStates.STATE_6_LANDING:
			model.setPilotingState(PilotingStates.STATE_7_WAITINGLANDING);
			break;
		case PilotingStates.STATE_7_WAITINGLANDING:
			if (model.getAltitude() <= 10)
				model.setPilotingState(PilotingStates.STATE_1_READY);
			break;
		default:
			break;
		}
	}

	private void config_1(LeapMotion leap) {

		float speedX = convertList[0].expConverter(leap.getPitchRightHand());
		float speedY = convertList[1].expConverter(leap.getRollRightHand());
		float speedZ = convertList[2].expConverter(leap.getPitchLeftHand());
		float speedSpin = convertList[3].expConverter(leap.getRollLeftHand());
		float takeOffGesture = leap.getYawRightHand();
		int countHands = leap.getAnzahlHaenden();
		float landingGesture = leap.getYawLeftHand();

		fsm(speedX, speedY, speedZ, speedSpin, takeOffGesture, landingGesture, countHands);
	}

	private void fsm(float speedX, float speedY, float speedZ, float speedSpin, float takeOffGesture,
			float landingGesture, int countHands) {

		switch (model.getState()) {
		case "init":
			if (countHands > 1)
				model.setState("ready");
			break;
		case "ready":
			if (countHands < 2)
				model.setState("init");

			if (takeOffGesture < -35) {
				if (startTime == 0)
					startTime = System.currentTimeMillis();
				model.setTimeUntilTakeOff((int) (model.getTAKE_OFF_DELAY() - (System.currentTimeMillis() - startTime)));
				if (model.getTimeUntilTakeOff() <= 0) {
					model.setState("takingOff");
				}
			} else if (takeOffGesture >= -35) {
				model.setTimeUntilTakeOff(model.getTAKE_OFF_DELAY());
				startTime = 0;
			}

			break;

		case "takingOff":
			startTime = 0;
			model.setState("waitingTakeOff");
			break;
		case "waitingTakeOff":
			if (takeOffGesture > -10 && model.getAltitude() > 0)
				model.setState("hovering");
			break;
		case "hovering":

			if (landingGesture > 35) {
				model.setState("landing");
				break;
			}

			if (countHands == 2 && (speedX != 0 || speedY != 0 || speedZ != 0 || speedSpin != 0)) {
				model.setState("flying");
				break;
			}

			break;
		case "flying":
			if (landingGesture > 35) {
				model.setState("landing");
				break;
			}

			if (countHands < 2) {
				model.setState("hovering");
				model.setSpeedX(0);
				model.setSpeedY(0);
				model.setSpeedZ(0);
				model.setSpeedSpin(0);
				break;
			}

			model.setSpeedX(-speedX);
			model.setSpeedY(-speedY);
			model.setSpeedZ(-speedZ);
			model.setSpeedSpin(-speedSpin);

			break;
		case "landing":
			model.setState("waitingLanding");
			break;
		case "waitingLanding":
			if (model.getAltitude() <= 10)
				model.setState("ready");
			break;
		default:
			break;
		}
	}

	// public void ServicesGesturesConfig_2(LeapMotion leap) { // l'inverse de
	// la
	// // config_1
	// float speedX = convertX.expConverter(leap.getRollLeftHand());
	// float speedY = convertY.expConverter(leap.getPitchLeftHand());
	// float speedZ = convertZ.expConverter(leap.getPitchRightHand());
	// float speedSpin = convertSpin.expConverter(leap.getRollRightHand());
	// float takeOffGesture = leap.getYawRightHand();
	// float landingGesture = leap.getYawLeftHand();
	// int countHands = leap.getAnzahlHaenden();
	//
	// fsm(speedX, speedY, speedZ, speedSpin, takeOffGesture, landingGesture,
	// countHands);
	//
	// }

	// public void ServicesGesturesConfig_3(LeapMotion leap) {
	// // TODO à definir!!!
	// float speedX = convertX.expConverter(leap.getRollLeftHand());
	// float speedY = convertY.expConverter(leap.getPitchLeftHand());
	// float speedZ = convertZ.expConverter(leap.getPitchRightHand());
	// float speedSpin = convertSpin.expConverter(leap.getRollRightHand());
	// float takeOffGesture = leap.getYawRightHand();
	// float landingGesture = leap.getYawLeftHand();
	// int countHands = leap.getAnzahlHaenden();
	// fsm(speedX, speedY, speedZ, speedSpin, takeOffGesture, landingGesture,
	// countHands);
	//
	// }
}
