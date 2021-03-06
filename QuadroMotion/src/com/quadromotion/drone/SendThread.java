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
 * This class sends the commands on every change to the drone. It implements the
 * classes <code>Observer</code> and <code>Runnable</code>, so it can be run in
 * a separate <code>thread</code>.
 * 
 * @author Gabriel Urech
 *
 */
public class SendThread implements Observer, Runnable {
	private float speedx = 0;
	private float speedy = 0;
	private float speedz = 0;
	private float speedspin = 0;
	private int state = 0;
	private String threadName;
	private boolean changed;

	private int rate = 2; // aktualisierungsrate in ms

	private ARDroneCommander droneCommander = null;

	/**
	 * Allocates a new <code>SendThread</code> object so that it has
	 * <code>threadName</code> as name of the thread, has <code>model</code> as
	 * the model to be observed and has <code>drone</code> as the ardrone.
	 * 
	 * @param threadName
	 *            the thread name
	 * @param model
	 *            the model
	 * @param drone
	 *            the ardrone
	 */
	public SendThread(String threadName, Model model, IARDrone drone) {
		this.threadName = threadName;
		this.droneCommander = new ARDroneCommander(drone);
		// m = model;
		changed = false;
		// this.model = model;
		model.addObserver(this);
	}

	/**
	 * Sends the commands to the droneCommander according to the current
	 * piloting state.
	 */
	private void sendCommand() {

		switch (state) {
		case PilotingStates.STATE_1_INIT:
			break;
		case PilotingStates.STATE_2_READY:
			break;
		case PilotingStates.STATE_3_TAKINGOFF:
			droneCommander.animateLEDs();
			droneCommander.takeOff();
			break;
		case PilotingStates.STATE_4_WAITINGTAKEOFF:
			break;
		case PilotingStates.STATE_5_HOVERING:
			droneCommander.hover();
			break;
		case PilotingStates.STATE_6_FLYING:
			droneCommander.moveDrone(speedx, speedy, speedz, speedspin);
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
		if (state != m.getPilotingState() || m.getPilotingState() == PilotingStates.STATE_3_TAKINGOFF
				|| m.getPilotingState() == PilotingStates.STATE_4_WAITINGTAKEOFF
				|| m.getPilotingState() == PilotingStates.STATE_5_HOVERING
				|| m.getPilotingState() == PilotingStates.STATE_6_FLYING
				|| m.getPilotingState() == PilotingStates.STATE_7_LANDING) {
			speedx = m.getSpeedX();
			speedy = m.getSpeedY();
			speedz = m.getSpeedZ();
			speedspin = m.getSpeedSpin();
			state = m.getPilotingState();
			// changed = true;
			sendCommand();
		}
	}

	@Override
	public void run() {
		// boolean stop = false;
		// while (!stop) {
		// System.out.println("SendThread: " + System.currentTimeMillis());
		// try {
		// if (changed) {
		// System.out.println(changed);
		//
		// sendCommand();
		// changed = false;
		// }
		// Thread.sleep(rate);
		// } catch (Exception ignore) {
		// stop = true;
		// droneCommander.cleanup();
		// }
		// }
	}

	/**
	 * 
	 * @return the name of the thread.
	 */
	public String getThreadName() {
		return threadName;
	}

}
