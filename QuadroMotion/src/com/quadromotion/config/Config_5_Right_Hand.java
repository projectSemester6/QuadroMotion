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
package com.quadromotion.config;

import java.util.ArrayList;

import com.quadromotion.input.LeapMotion;
import com.quadromotion.service.Converter;

public class Config_5_Right_Hand extends ConfigBase {

	private static final int COUNT_HANDS = 1;
//	private Converter convertList[] = new Converter[4];
	private ArrayList<Converter> converterList = new ArrayList<Converter>();

//	public Config_5_Right_Hand(Converter convertList[]) {
//		super();
//		this.convertList = convertList;
//	}

	public Config_5_Right_Hand(ArrayList<Converter> converterList) {
		this.converterList = converterList;
	}

	public int[] convertLeapInput(LeapMotion leap) {

		int speedValues[] = { 0, 0, 0, 0 };
		int outputValues[] = { 0, 0, 0, 0, 0, 0, 0 };
//		 System.out.println(leap.getLeftHand() + "; " + leap.getRightHand());
		if (leap.getRightHand() && leap.getLeftHand()) {
			outputValues[5] = 1; // landingGesture
		} else if (leap.getRightHand() && !leap.getLeftHand()) {
			for (int i = 0; i < 4; i++) {
				switch (i) {
				case 0:
					speedValues[i] = (int) leap.getPitchRightHand(); // speedX,
																		// forward/backward
					break;
				case 1:
					speedValues[i] = (int) leap.getRollRightHand(); // speedY,
																	// right/left
					break;
				case 2:
					speedValues[i] = (int) (leap.getThrustRightHand() - OffsetConfig.HAND_THRUST_OFFSET) / 2; // speedZ,
																												// down/up
					break;
				case 3:
					speedValues[i] = (int) -leap.getYawRightHand(); // speedSpin
					break;
				default:
					break;
				}
			}

			for (int i = 0; i < 4; i++) {
				outputValues[i] = (int) converterList.get(i).convert(speedValues[i]); // speed
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
					if (leap.getYawRightHand() < -90) {
//						outputValues[i] = 1; // landingGesture
					} else
						outputValues[i] = 0;
					break;
				case 6:
					// countHands
					outputValues[i] = COUNT_HANDS;
					break;
				default:
					break;
				}
			}
		}
		return outputValues;
	}

	public int getCountHands() {
		return COUNT_HANDS;
	}
}
