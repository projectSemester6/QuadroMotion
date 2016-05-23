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
package com.quadromotion.service;

// converts the input angle value to the output speed 
public class Converter {

	private float maxAngle;
	private float maxSpeed;
	private float speedOffset;
	private float angleOffset;
	private float functionExp;

	/**
	 * 
	 * @param maxAngle
	 * @param maxSpeed
	 * @param speedOffset
	 * @param angleOffset
	 * @param functionExp
	 */
	public Converter(float maxAngle, float maxSpeed, float speedOffset, float angleOffset, float functionExp) {
		super();
		this.maxAngle = maxAngle;
		this.maxSpeed = maxSpeed;
		this.speedOffset = speedOffset;
		this.angleOffset = angleOffset;
		this.functionExp = functionExp;
	}

	public float convert(float inputValue) {

		if (Math.abs(inputValue) < angleOffset)
			return 0f;
		if (inputValue > maxAngle)
			inputValue = maxAngle;

		boolean inputSign = false;

		float functionSpeed = 0;
		float functionMaxAngle = 0;

		if (inputValue < 0) {
			inputSign = true; // signe n�gatif
		}

		inputValue = Math.abs(inputValue);

		functionMaxAngle = maxAngle - angleOffset; // fMA = (b-d)
		functionSpeed = (float) Math.pow((inputValue - angleOffset) / functionMaxAngle, functionExp); // y(x)
		// =
		// ((x-d)/fMA)^p
		functionSpeed = functionSpeed * (maxSpeed - speedOffset);// y(x) =
																	// y(x)*a
																	//
		float outputValue = functionSpeed + speedOffset; // y(x) = y(x) + c

		/**
		 * changement de signe
		 */

		if (outputValue > maxSpeed)
			outputValue = maxSpeed;

		if (inputSign) {
			return -outputValue;
			// outputValue = -outputValue;// y(x) = -y(x)
		}

		return outputValue;
	}

//	public float linearConverter(float inputValue) {
//		// TODO lineare umrechnung
//		if (Math.abs(inputValue) < angleOffset)
//			return 0;
//		if (inputValue > maxAngle)
//			inputValue = maxAngle;
//		boolean inputSign = false;
//		if (inputValue < 0) {
//			inputSign = true; // signe n�gatif
//		}
//		float slope = 0;
//		float intercept = 0;
//
//		slope = (maxSpeed - speedOffset) / (maxAngle - angleOffset); // a
//		intercept = speedOffset - slope * angleOffset; // b
//
//		float outputValue = slope * Math.abs(inputValue) + intercept;// y = a*x
//																		// + b;
//		if (outputValue > maxSpeed)
//			outputValue = maxSpeed;
//		if (inputSign)
//			return -outputValue;
//		return outputValue;
//
//	}
//
//	public float heavySideConverter(float inputValue) {
//
//		float outputValue = 0;
//		boolean inputSign = false;
//
//		if (inputValue < 0) {
//			inputSign = true; // signe n�gatif
//		}
//		inputValue = Math.abs(inputValue);
//
//		if ((inputValue > angleOffset) && (inputSign == true)) {
//			outputValue = -maxSpeed;
//		}
//		if ((inputValue > angleOffset) && (inputSign == false)) {
//			outputValue = maxSpeed;
//		}
//		if (inputValue < angleOffset) {
//			outputValue = 0;
//		}
//		return outputValue;
//	}
//
//	public float logarithmConverter(float inputValue) {
//
//		boolean inputSign = false;
//
//		float functionSpeed = 0;
//		float functionMaxAngle = 0;
//
//		/**
//		 * Ce bordel est a v�rifi� Math�matiquement
//		 */
//		float _functionExp = 1 / functionExp;
//
//		if (inputValue < 0) {
//			inputSign = true; // signe n�gatif
//		}
//
//		inputValue = Math.abs(inputValue);
//
//		functionMaxAngle = maxAngle - angleOffset; // fMA = (b-d)
//		functionSpeed = (float) Math.pow((inputValue - angleOffset) / functionMaxAngle, _functionExp); // y(x)
//		// =
//		// ((x-d)/fMA)^p
//		functionSpeed = functionSpeed * (maxSpeed - speedOffset);// y(x) =
//																	// y(x)*a
//		float outputValue = functionSpeed + speedOffset; // y(x) = y(x) + c
//
//		/**
//		 * changement de signe
//		 */
//
//		if (inputSign) {
//			outputValue = -outputValue;// y(x) = -y(x)
//		}
//
//		if (inputValue < angleOffset)
//			outputValue = 0;
//
//		return outputValue;
//	}
}
