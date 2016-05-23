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
package com.quadromotion.drone;

import java.util.Observable;
import java.util.Observer;

import com.quadromotion.model.Model;
import com.quadromotion.pilotingstates.PilotingStates;

import de.yadrone.base.IARDrone;

/**
 * This class sends the latest commands on every change to the drone
 * 
 * @author Gabriel
 *
 */
public class SendThread implements Observer, Runnable {
	// private Model model = null;
//	private Model m = null;
	private float speedx = 0;
	private float speedy = 0;
	private float speedz = 0;
	private float speedspin = 0;
	private int state = 0;
	
	private boolean changed;

	private int rate = 2; // aktualisierungsrate in ms

	private ARDroneCommander droneCommander = null;

	/**
	 * Constructor
	 * 
	 * @param threadName
	 *            the thread name
	 * @param model
	 *            the model
	 * @param drone
	 *            the ardrone
	 */
	public SendThread(String threadName, Model model, IARDrone drone) {
		this.droneCommander = new ARDroneCommander(drone);
//		m = model;
		changed = false;
		// this.model = model;
		model.addObserver(this);
	}

	/**
	 * sends the commands to the droneCommander
	 */
	private void sendCommand() {

		switch (state) {
		case PilotingStates.STATE_1_INIT:
			break;
		case PilotingStates.STATE_2_READY:
			break;
		case PilotingStates.STATE_3_TAKINGOFF:
			droneCommander.takeOff();
			break;
		case PilotingStates.STATE_4_WAITINGTAKEOFF:
			break;
		case PilotingStates.STATE_5_HOVERING:
			droneCommander.hover();
			break;
		case PilotingStates.STATE_6_FLYING:
			droneCommander.moveDrone(speedx,speedy, speedz, speedspin);
			break;
		case PilotingStates.STATE_7_LANDING:
			droneCommander.landing();
			break;
		case PilotingStates.STATE_8_WAITINGLANDING:
			break;
		default:
			break;
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		Model m = (Model) o;
		if (m.getPilotingState() == PilotingStates.STATE_3_TAKINGOFF
				|| m.getPilotingState() == PilotingStates.STATE_5_HOVERING
				|| m.getPilotingState() == PilotingStates.STATE_6_FLYING
				|| m.getPilotingState() == PilotingStates.STATE_7_LANDING) {
			speedx = m.getSpeedX();
			speedy = m.getSpeedY();
			speedz = m.getSpeedZ();
			speedspin = m.getSpeedSpin();
			state = m.getPilotingState();
			changed = true;
		}
	}

	@Override
	public void run() {
		boolean stop = false;
		while (!stop) {
			try {
				if (changed) {
					System.out.println(changed);
					System.out.println("SendThread: "+ System.currentTimeMillis());
					sendCommand();
					changed = false;
				}
				Thread.sleep(rate);
			} catch (Exception ignore) {
				stop = true;
				droneCommander.cleanup();
			}
		}
	}
}
