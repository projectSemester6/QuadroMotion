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
package com.quadromotion.pilotingstates;

/**
 * This class defines the different states which can be accessed in a static way.
 * @author Gabriel Urech
 *
 */
public class PilotingStates {
	
	/**
	 * The off state.
	 */
	public final static int STATE_0_OFF = 0;
	
	/**
	 * The init state.
	 */
	public final static int STATE_1_INIT = 1;
	
	/**
	 * The ready state.
	 */
	public final static int STATE_2_READY = 2;
	
	/**
	 * The taking off state.
	 */
	public final static int STATE_3_TAKINGOFF = 3;
	
	/**
	 * The waiting for taking off finished state.
	 */
	public final static int STATE_4_WAITINGTAKEOFF = 4;
	
	/**
	 * The hovering state.
	 */
	public final static int STATE_5_HOVERING = 5;
	
	/**
	 * The flying state.
	 */
	public final static int STATE_6_FLYING = 6;
	
	/**
	 * The landing state.
	 */
	public final static int STATE_7_LANDING = 7;
	
	/**
	 * The waiting for landing finished state.
	 */
	public final static int STATE_8_WAITINGLANDING = 8;
}
