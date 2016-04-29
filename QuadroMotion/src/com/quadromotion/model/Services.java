package com.quadromotion.model;

import java.util.Observable;
import java.util.Observer;

import com.quadromotion.config.OffsetConfig;
import com.quadromotion.gestures.LeapMotion;
import com.quadromotion.model.convertion.AngleToSpeedConverter;

public class Services {

	private AngleToSpeedConverter convertX = null;
	private AngleToSpeedConverter convertY = null;
	private AngleToSpeedConverter convertZ = null;
	private AngleToSpeedConverter convertSpin = null;

	private LeapMotion leap = null;
	private Model model = null;

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
//		this.leap.addObserver(this);

	}

	public void ServicesGesturesConfig_1(LeapMotion leap) {

		model.setSpeedY(convertY.expConverter(leap.getPitchRightHand()));
		model.setSpeedX(convertX.expConverter(leap.getRollRightHand()));
		model.setSpeedZ(convertZ.HeavySideConverter(leap.getYawLeftHand()));
		model.setSpeedSpin(convertSpin.linearConverter(leap.getRollLeftHand()));

		if ((model.getTakeOffCommand() == false) && (model.isFlying() == false) && (leap.getYawRightHand() < -35)) {
			model.setTakeOffCommand(true);
		}
		if ((model.getLandingCommand() == false) && (model.isFlying() == true) && (leap.getYawLeftHand() > 35)) {
			model.setLandingCommand(true);
		}
		
	}

	public void ServicesGesturesConfig_2() { // l'inverse de la config_1

		model.setSpeedX(convertX.expConverter(leap.getPitchLeftHand()));
		model.setSpeedY(convertY.expConverter(leap.getRollLeftHand()));
		model.setSpeedZ(convertZ.HeavySideConverter(leap.getYawRightHand()));
		model.setSpeedSpin(convertSpin.linearConverter(leap.getRollRightHand()));

		if ((model.getTakeOffCommand() == false) && (model.isFlying() == false) && (leap.getYawLeftHand() > 35)) {
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
		if ((model.getLandingCommand() == false) && (model.isFlying() == true) && (leap.getYawLeftHand() > 35)) {
			model.setLandingCommand(true);
		}
	}
	
	
}
