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

	private LeapMotion leap = null;
	private Model model = null;

	private String state = "ready";

	public Services(Model model, LeapMotion leap) {

		this.leap = leap;
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

		switch (state) {
		case "ready":
			if (model.getLandingCommand())
				model.setLandingCommand(false);
			if (leap.getYawRightHand() < -35) {
				print("next state is: takingOff\n");
				state = "takingOff";
			}
			break;
		case "takingOff":
			print("next state is: hovering\n");
			model.setTakeOffCommand(true);
			state = "hovering";
			break;
		case "hovering":
			if (model.getHoverCommand())
				model.setTakeOffCommand(false);
			if (leap.getYawLeftHand() > 35) {
				print("next state is: landing\n");
				state = "landing";
				break;
			}
			if (leap.getPitchRightHand() != 0 || leap.getRollRightHand() != 0 || leap.getYawLeftHand() != 0
					|| leap.getRollLeftHand() != 0) {
				print("next state is: flying\n");
				state = "flying";
				break;
			}

			model.setHoverCommand(true);
			break;
		case "flying":
			if (leap.getPitchRightHand() == 0 || leap.getRollRightHand() == 0 || leap.getYawLeftHand() == 0
					|| leap.getRollLeftHand() == 0) {
				print("next state is: hovering\n");
				state = "hovering";
				break;
			}
			// model.setSpeedX(convertX.expConverter(leap.getPitchRightHand()));
			// model.setSpeedY(convertY.expConverter(leap.getRollRightHand()));
			// model.setSpeedZ(convertZ.HeavySideConverter(leap.getYawLeftHand()));
			// model.setSpeedSpin(convertSpin.linearConverter(leap.getRollLeftHand()));
			if (leap.getYawLeftHand() > 35) {
				print("next state is: landing\n");
				state = "landing";
				break;
			}
			break;
		case "landing":
			print("drone is landing");
			print("next state is: ready\n");
			model.setLandingCommand(true);
			state = "ready";
			break;
		default:
			break;
		}
	}

	private void print(String input){
		System.out.println(input);
	}
	public void ServicesGesturesConfig_2() { // l'inverse de la config_1

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

	public void ServicesGesturesConfig_3() {

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
