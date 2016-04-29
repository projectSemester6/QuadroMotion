package com.quadromotion.model;

import java.util.Observable;
import java.util.Observer;

import com.quadromotion.app.App;
import com.quadromotion.app.QuadroMotionMain;
import com.quadromotion.config.OffsetConfig;
import com.quadromotion.gestures.Gestures;
import com.quadromotion.gestures.LeapMotion;
import com.quadromotion.model.convertion.AngleToSpeedConverter;
import com.quadromotion.model.*;

public class Services {

	private AngleToSpeedConverter convertX = null;
	private AngleToSpeedConverter convertY = null;
	private AngleToSpeedConverter convertZ = null;
	private AngleToSpeedConverter convertSpin = null;

	private Model model = null;

	private String state = "init";

	private long startTime = 0;
	private long startStateTime = 0;

	private final int TAKE_OFF_DELAY = 2000;
	private long timeUntilTakeOff = 0;

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

		float speedY = convertY.expConverter(leap.getPitchRightHand());
		float speedX = convertX.expConverter(leap.getRollRightHand());
		float speedZ = convertZ.expConverter(leap.getPitchLeftHand());
		float speedSpin = convertSpin.expConverter(leap.getRollLeftHand());
		float takeOffCommand = leap.getYawRightHand();
		int countHands = leap.getAnzahlHaenden();

		print("Service: " + model.getState());

		switch (model.getState()) {
		case "init":
			if (countHands > 1)
				model.setState("ready");
			break;
		case "ready":
			if (countHands < 2)
				model.setState("init");
			if (model.getLandingCommand())
				model.setLandingCommand(false);

			if (takeOffCommand < -35) {
				if (startTime == 0)
					startTime = System.currentTimeMillis();
				timeUntilTakeOff = TAKE_OFF_DELAY - (System.currentTimeMillis() - startTime);
				if (timeUntilTakeOff <= 0) {
					print("next state is: takingOff\n");
					model.setState("takingOff");
				}
			} else if (takeOffCommand >= -35) {
				timeUntilTakeOff = TAKE_OFF_DELAY;
				startTime = 0;
			}

			print("time until take off: " + String.valueOf(timeUntilTakeOff));
			break;

		case "takingOff":
			startTime = 0;
			if (!model.getTakeOffCommand())
				model.setTakeOffCommand(true);
			if (takeOffCommand > -10)
				model.setState("hovering");
			break;

		case "hovering":

			model.setHoverCommand(true);

			if (leap.getYawLeftHand() > 35) {
				model.setState("landing");
				break;
			}

			if (countHands == 2 && (speedX != 0 || speedY != 0 || speedZ != 0 || speedSpin != 0)) {
				model.setState("flying");
				break;
			}

			break;
		case "flying":
			if (countHands < 2) {
				model.setState("hovering");
				break;
			}

			if (leap.getYawLeftHand() > 35) {
				model.setState("landing");
				break;
			}
			
			model.setSpeedX(-speedX);
			model.setSpeedY(-speedY);
			model.setSpeedZ(-speedZ);
			model.setSpeedSpin(-speedSpin);
			
			break;
		case "landing":
			model.setLandingCommand(true);
//			model.setState("ready");
			break;
		default:
			break;
		}
	}

	public void print(String input) {
		System.out.println(input);
	}

	public void ServicesGesturesConfig_2(LeapMotion leap) { // l'inverse de la
															// config_1

		model.setSpeedX(convertX.expConverter(leap.getPitchLeftHand()));
		model.setSpeedY(convertY.expConverter(leap.getRollLeftHand()));
		model.setSpeedZ(convertZ.HeavySideConverter(leap.getYawRightHand()));
		model.setSpeedSpin(convertSpin.linearConverter(leap.getRollRightHand()));

		if ((model.getTakeOffCommand() == false) && (model.isFlying() == false) && (leap.getYawLeftHand() < 35)) {
			model.setTakeOffCommand(true);
		}
		if ((model.getLandingCommand() == false) && (model.isFlying() == true) && (leap.getYawRightHand() < -35)) {
			model.setLandingCommand(true);
		}

	}

	public void ServicesGesturesConfig_3(LeapMotion leap) {

		model.setSpeedX(convertX.expConverter(leap.getPitchRightHand()));
		model.setSpeedY(convertY.expConverter(leap.getRollLeftHand()));
		model.setSpeedZ(convertZ.HeavySideConverter(leap.getYawLeftHand()));
		model.setSpeedSpin(convertSpin.linearConverter(leap.getRollRightHand()));

		if ((model.getTakeOffCommand() == false) && (model.isFlying() == false) && (leap.getYawRightHand() < -35)) {
			model.setTakeOffCommand(true);
		}
		if ((model.getLandingCommand() == false) && (model.isFlying() == true) && (leap.getYawLeftHand() < 35)) {
			model.setLandingCommand(true);
		}
	}

	private void updateModel(LeapMotion l) {

	}
}
