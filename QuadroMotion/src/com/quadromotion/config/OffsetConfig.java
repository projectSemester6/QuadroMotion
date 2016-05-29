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

/**
 * This class contains all constants used to convert the leap motion input to
 * the speed. The values can be accessed in a static way.
 * 
 * @author Gabriel Urech<br>
 *         Alexis Stephan<br>
 *         Simon Henzmann
 *
 */

public class OffsetConfig {

	/**
	 * The maximal angle for the speed x.
	 */
	public static final float MAX_ANGLE_X = 40;
	
	/**
	 * The maximal speed x.
	 */
	public static final float MAX_SPEED_X = 80;
	
	/**
	 * The minimal speed x.
	 */
	public static final float SPEED_OFFSET_X = 0;
	
	/**
	 * The minimal angle for the speed x.
	 */
	public static final float ANGLE_OFFSET_X = 5;
	
	/**
	 * The exponent of the function for the speed x.
	 */
	public static final float FUNCTION_EXP_X = 2.1F;
	
	/**
	 * The maximal angle for the speed y.
	 */
	public static final float MAX_ANGLE_Y = 40;
	
	/**
	 * The maximal speed y.
	 */
	public static final float MAX_SPEED_Y = 80;
	
	/**
	 * The minimal speed y.
	 */
	public static final float SPEED_OFFSET_Y = 0;
	
	/**
	 * The minimal angle for the speed y.
	 */
	public static final float ANGLE_OFFSET_Y = 5;
	
	/**
	 * The exponent of the function for the speed y.
	 */
	public static final float FUNCTION_EXP_Y = 2.1F;
	
	/**
	 * The maximal angle for the speed z.
	 */
	public static final float MAX_ANGLE_Z = 35;
	
	/**
	 * The maximal speed z.
	 */
	public static final float MAX_SPEED_Z = 80;
	
	/**
	 * The minimal speed z.
	 */
	public static final float SPEED_OFFSET_Z = 0;
	
	/**
	 * The minimal angle for the speed z.
	 */
	public static final float ANGLE_OFFSET_Z = 5;
	
	/**
	 * The exponent of the function for the speed z.
	 */
	public static final float FUNCTION_EXP_Z = 2.1F;
	
	/**
	 * The maximal angle for the speed y.
	 */
	public static final float MAX_ANGLE_SPIN = 30;
	
	/**
	 * The maximal speed spin.
	 */
	public static final float MAX_SPEED_SPIN = 80;
	
	/**
	 * The minimal speed spin.
	 */
	public static final float SPEED_OFFSET_SPIN = 0;
	
	/**
	 * The minimal angle for the speed spin.
	 */
	public static final float ANGLE_OFFSET_SPIN = 5;
	
	/**
	 * The exponent of the function for the speed spin.
	 */
	public static final float FUNCTION_EXP_SPIN = 2.4F;
	
	/**
	 * The maximal angle for the speed x.
	 */
	public static final float MAX_HAND_YAW = 35;
	
	/**
	 * The maximal angle for the speed x.
	 */
	public static final float MAX_HAND_THRUST = 30;
	
	/**
	 * The maximal angle for the speed x.
	 */
	public static final float HAND_THRUST_OFFSET = 140;
}
