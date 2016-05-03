package com.quadromotion.model;

import com.quadromotion.config.OffsetConfig;
import com.quadromotion.gestures.LeapMotion;
import com.quadromotion.model.convertion.AngleToSpeedConverter;

public class Services {

	private AngleToSpeedConverter convertX = null;
	private AngleToSpeedConverter convertY = null;
	private AngleToSpeedConverter convertZ = null;
	private AngleToSpeedConverter convertSpin = null;

	private Model model = null;

	private long startTime = 0;

	public Services(Model model) {

		this.model = model;

		convertX = new AngleToSpeedConverter(OffsetConfig.MAX_ANGLE_X, OffsetConfig.MAX_SPEED_X,
				OffsetConfig.SPEED_OFFSET_X, OffsetConfig.ANGLE_OFFSET_X, OffsetConfig.FUNCTION_EXP_X);
		convertY = new AngleToSpeedConverter(OffsetConfig.MAX_ANGLE_X, OffsetConfig.MAX_SPEED_Y,
				OffsetConfig.SPEED_OFFSET_Y, OffsetConfig.ANGLE_OFFSET_Y, OffsetConfig.FUNCTION_EXP_Y);
		convertZ = new AngleToSpeedConverter(OffsetConfig.MAX_ANGLE_Z, OffsetConfig.MAX_SPEED_Z,
				OffsetConfig.SPEED_OFFSET_Z, OffsetConfig.ANGLE_OFFSET_Z, OffsetConfig.FUNCTION_EXP_Z);
		convertSpin = new AngleToSpeedConverter(OffsetConfig.MAX_ANGLE_SPIN, OffsetConfig.MAX_SPEED_SPIN,
				OffsetConfig.SPEED_OFFSET_SPIN, OffsetConfig.ANGLE_OFFSET_SPIN, OffsetConfig.FUNCTION_EXP_SPIN);

	}

	public void ServicesGesturesConfig_1(LeapMotion leap) {

		float speedX = convertX.HeavySideConverter(leap.getPitchRightHand());
		float speedY = convertY.HeavySideConverter(leap.getRollRightHand());
		float speedZ = convertZ.HeavySideConverter(leap.getPitchLeftHand());
		float speedSpin = convertSpin.HeavySideConverter(leap.getRollLeftHand());
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

	public void ServicesGesturesConfig_2(LeapMotion leap) { // l'inverse de la
															// config_1
		float speedX = convertX.expConverter(leap.getRollLeftHand());
		float speedY = convertY.expConverter(leap.getPitchLeftHand());
		float speedZ = convertZ.expConverter(leap.getPitchRightHand());
		float speedSpin = convertSpin.expConverter(leap.getRollRightHand());
		float takeOffGesture = leap.getYawRightHand();
		float landingGesture = leap.getYawLeftHand();
		int countHands = leap.getAnzahlHaenden();

		fsm(speedX, speedY, speedZ, speedSpin, takeOffGesture, landingGesture, countHands);

	}

	public void ServicesGesturesConfig_3(LeapMotion leap) {
		// TODO à definir!!!
		float speedX = convertX.expConverter(leap.getRollLeftHand());
		float speedY = convertY.expConverter(leap.getPitchLeftHand());
		float speedZ = convertZ.expConverter(leap.getPitchRightHand());
		float speedSpin = convertSpin.expConverter(leap.getRollRightHand());
		float takeOffGesture = leap.getYawRightHand();
		float landingGesture = leap.getYawLeftHand();
		int countHands = leap.getAnzahlHaenden();
		fsm(speedX, speedY, speedZ, speedSpin, takeOffGesture, landingGesture, countHands);

	}
}
