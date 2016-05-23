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

public class Config_2_Two_Hands extends ConfigBase {

	private static final int COUNTHANDS = 2;
	private ArrayList<Converter> converterList = new ArrayList<Converter>();
//	private final String NAME = "2-Hand-Steuerung-Yaw";

	public Config_2_Two_Hands(ArrayList<Converter> converterList) {
		this.converterList = converterList;
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
					leapValues[i] = (int) -leap.getYawLeftHand(); // speedSpin
					break;
				default:
					break;
				}
			}

			for (int i = 0; i < 4; i++) {
				outputValues[i] = (int) converterList.get(i).convert(leapValues[i]); // speed
			}

			for (int i = 4; i < 7; i++) {
				switch (i) {
				case 4:
					if (leap.getYawRightHand() < -35)
						outputValues[i] = 1; // takeOffGesture
					break;
				case 5:
					if (leap.getYawRightHand() < -35)
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
