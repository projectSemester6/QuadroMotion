package com.quadromotion.model;

import com.quadromotion.gestures.LeapMotion;
import com.quadromotion.model.convertion.Converter;

public class Config_1 extends ConfigBase {

	private Converter convertList[] = new Converter[4];

	public Config_1(Converter convertList[]) {

		super();
		this.convertList = convertList;
	}

	public int[] convertLeapInput(LeapMotion leap) {
		int leapValues[] = { 0, 0, 0, 0 };

		for (int i = 0; i < 4; i++) {
			switch (i) {
			case 0:
				leapValues[i] = (int) leap.getPitchRightHand(); // speedX
				break;
			case 1:
				leapValues[i] = (int) leap.getPitchRightHand(); // speedY
				break;
			case 2:
				leapValues[i] = (int) leap.getPitchRightHand(); // speedZ
				break;
			case 3:
				leapValues[i] = (int) leap.getPitchRightHand(); // speedSpin
				break;
			default:
				break;
			}
		}

		int outputValues[] = { 0, 0, 0, 0, 0, 0, 0 };

		for (int i = 0; i < 4; i++) {
			outputValues[i] = (int) convertList[i].heavySideConverter(leapValues[i]); // speed
		}

		for (int i = 4; i < 7; i++) {
			switch (i) {
			case 4:
				outputValues[i] = (int) leap.getYawRightHand(); // takeOffGesture
				break;
			case 5:
				outputValues[i] = (int) leap.getYawLeftHand(); // landingGesture
				break;
			case 6:
				outputValues[i] = (int) leap.getAnzahlHaenden(); // countHands
				break;
			default:
				break;
			}
		}
		return outputValues;
	}
}
