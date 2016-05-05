package com.quadromotion.config;

import com.quadromotion.gestures.LeapMotion;
import com.quadromotion.model.convertion.Converter;

public class Config_2 extends ConfigBase {

	private int COUNT_HANDS = 1;
	private Converter convertList[] = new Converter[4];

	public Config_2(Converter convertList[]) {

		super();
		this.convertList = convertList;
	}

	public int[] convertLeapInput(LeapMotion leap) {
		
		int outputValues[] = { 0, 0, 0, 0, 0, 0, 0 };
		
		int leapValues[] = { 0, 0, 0, 0 };

		for (int i = 0; i < 4; i++) {
			switch (i) {
			case 0:
				leapValues[i] = (int) leap.getPitchRightHand(); // speedX
				break;
			case 1:
				leapValues[i] = (int) leap.getRollRightHand(); // speedY
				break;
			case 2:
				//TODO Thrust richtig einlesen
//				leapValues[i] = (int) leap.getThrustRightHand(); // speedZ
				break;
			case 3:
				leapValues[i] = (int) leap.getYawRightHand(); // speedSpin
				break;
			default:
				break;
			}
		}

		for (int i = 0; i < 4; i++) {
			outputValues[i] = (int) convertList[i].expConverter(leapValues[i]); // speed
		}

		for (int i = 4; i < 7; i++) {
			switch (i) {
			case 4:
				if (leap.getYawRightHand() < -35)
					outputValues[i] = 1; // takeOffGesture
				else
					outputValues[i] = 0;
				break;
			case 5:
				if (leap.getYawRightHand() > 35)
					outputValues[i] = 1; // landingGesture
				else
					outputValues[i] = 0;
				break;
			case 6:
				// countHands
				if(leap.getRightHand()) {
					outputValues[i] = 1;
				}
				else {
					outputValues[i] = 0;
				}
				break;
			default:
				break;
			}
		}
		return outputValues;
	}
	public int getCountHands() {
		return COUNT_HANDS;
	}
}
