package com.quadromotion.config;

import com.quadromotion.input.LeapMotion;
import com.quadromotion.service.Converter;

public class Config_1_Two_Hands extends ConfigBase {

	private static final int COUNTHANDS = 2;
	private Converter convertList[] = new Converter[4];

	public Config_1_Two_Hands(Converter convertList[]) {
		super();
		this.convertList = convertList;
	}

	public int[] convertLeapInput(LeapMotion leap) {
		int leapValues[] = { 0, 0, 0, 0 };
		int outputValues[] = { 0, 0, 0, 0, 0, 0, 0 };
		if (leap.getLeftHand() && leap.getRightHand()) {
			for (int i = 0; i < 4; i++) {
				switch (i) {
				case 0:
					leapValues[i] = (int) leap.getPitchRightHand(); // speedX
					break;
				case 1:
					leapValues[i] = (int) leap.getRollRightHand(); // speedY
					break;
				case 2:
					leapValues[i] = (int) leap.getPitchLeftHand(); // speedZ
					break;
				case 3:
					leapValues[i] = (int) leap.getRollLeftHand(); // speedSpin
					break;
				default:
					break;
				}
			}

			for (int i = 0; i < 4; i++) {
				outputValues[i] = (int) convertList[i].convert(leapValues[i]); // speed
			}

			for (int i = 4; i < 7; i++) {
				switch (i) {
				case 4:
					if (leap.getYawRightHand() < -35)
						outputValues[i] = 1; // takeOffGesture
					break;
				case 5:
					if (leap.getYawLeftHand() > 35)
						outputValues[i] = 1; // landingGesture
					break;
				case 6:
					outputValues[i] = leap.getAnzahlHaenden(); // countHands
					break;
				default:
					break;
				}
			}
		}
		return outputValues;
	}

	public int getCountHands() {
		return COUNTHANDS;
	}
}
