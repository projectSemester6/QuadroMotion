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

/**
 * This class defines the configuration 2.
 * <p>
 * The commands are the following:
 * <p>
 * Right hand:<br>
 * <strong> hold the hand turned counterclockwise (yaw) for 2s:</strong> take
 * off the drone<br>
 * <strong> pitch forward:</strong> move forward<br>
 * <strong> pitch backward: </strong> move backward<br>
 * <strong> roll left:</strong> move left<br>
 * <strong> roll right:</strong> move right<br>
 * <strong> turn the hand counterclockwise (yaw):</strong> land the drone<br>
 * <p>
 * Left hand:<br>
 * <strong> yaw left:</strong> turn counterclockwise<br>
 * <strong> yaw right:</strong> turn clockwise<br>
 * <strong> pitch backward:</strong> move up<br>
 * <strong> pitch forward:</strong> move down<br>
 * 
 * @author Alexis Stephan<br>
 *         Gabriel Urech<br>
 *         Simon Henzmann
 *
 */

public class Config_2_Two_Hands extends ConfigBase {

	/**
	 * The number of hands used in this configuration.
	 */
	private static final int COUNT_HANDS = 2;

	/**
	 * The ArrayList containing all converter for each speed.
	 */
	private ArrayList<Converter> converterList = new ArrayList<Converter>();

	/**
	 * The name of this configuration.
	 */
	private final String name = "Zwei-Hand-Steuerung 2 (Yaw)";

	/**
	 * Allocates a new <code>Config_2_Two_Hands</code> object so that it has
	 * <code>converterList</code> as the converter list.
	 * @param converterList the list containing a converter for each speed.
	 */
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
		return COUNT_HANDS;
	}
}
