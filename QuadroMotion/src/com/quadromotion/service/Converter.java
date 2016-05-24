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

/**
 * This class is responsible to convert the input angle to the output speed.
 * 
 * @author Gabriel Urech, Alexis Stephan
 *
 */
public class Converter {

	/**
	 * The maximum angle of the hand. The angle used in the calculation will not
	 * exceed this value.
	 */
	private float maxAngle;

	/**
	 * The maximum speed of the drone. If the calculated speed exceeds this
	 * value, it will be replaced by the maxSpeed.
	 */
	private float maxSpeed;

	/**
	 * The minimum speed of the drone. If the calculated speed is below this
	 * value, it will be replaced by the speedOffset.
	 */
	private float speedOffset;

	/**
	 * The offset angle of the hand. Defines at which angle the conversion
	 * begins. For example if the angleOffset is set to 10, every angle below
	 * this value will be ignored and the return value is zero.
	 */
	private float angleOffset;

	/**
	 * The exponent of the function.
	 */
	private float functionExp;

	/**
	 * Constructor
	 * 
	 * @param maxAngle
	 *            the maximum angle
	 * @param maxSpeed
	 *            the maximum speed
	 * @param speedOffset
	 *            the minimum speed
	 * @param angleOffset
	 *            the minimum angle
	 * @param functionExp
	 *            the exponent for the function
	 */
	public Converter(float maxAngle, float maxSpeed, float speedOffset, float angleOffset, float functionExp) {
		super();
		this.maxAngle = maxAngle;
		this.maxSpeed = maxSpeed;
		this.speedOffset = speedOffset;
		this.angleOffset = angleOffset;
		this.functionExp = functionExp;
	}

	/**
	 * Calculates the speed using the input value.
	 * 
	 * @param inputValue
	 *            the angle to be converted.
	 * @return the speed.
	 */
	public float convert(float inputValue) {

		if (Math.abs(inputValue) < angleOffset)
			return 0f;
		if (inputValue > maxAngle)
			inputValue = maxAngle;

		boolean inputSign = false;
		float functionSpeed = 0;
		float functionMaxAngle = 0;

		if (inputValue < 0) {
			inputSign = true; // signe negatif
		}

		inputValue = Math.abs(inputValue);

		/*
		 * fMA = (b-d)
		 */
		functionMaxAngle = maxAngle - angleOffset;

		/*
		 * y(x) = ((x-d)/fMA)^p
		 */
		functionSpeed = (float) Math.pow((inputValue - angleOffset) / functionMaxAngle, functionExp);

		/*
		 * y(x) = y(x)*a
		 */
		functionSpeed = functionSpeed * (maxSpeed - speedOffset);

		/*
		 * y(x) = y(x) + c
		 */
		float outputValue = functionSpeed + speedOffset;

		if (outputValue > maxSpeed)
			outputValue = maxSpeed;

		/*
		 * changement de signe
		 */
		if (inputSign) {
			return -outputValue;
		}
		return outputValue;
	}
}
