package com.quadromotion.controller;

import java.util.Observable;
import java.util.Observer;

import com.quadromotion.model.Model;
import com.quadromotion.navdata.*;
import com.quadromotion.pilotingstates.PilotingStates;

import de.yadrone.base.IARDrone;

/**
 * This class sends the latest commands on every change to the drone
 * 
 * @author Gabriel
 *
 */
public class SendThread extends Thread implements Observer {
	private Model model = null;
	private Model m = null;

	private IARDroneCommander droneCommander = null;

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

		this.model = model;
		model.addObserver(this);
	}

	@Override
	public void run() {

	}

	/**
	 * sends the commands to the droneCommander
	 */
	private synchronized void sendCommand(Model m) {

		switch (m.getPilotingState()) {
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
			droneCommander.moveDrone(m.getSpeedX(), m.getSpeedY(), m.getSpeedZ(), m.getSpeedSpin());
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
		m = (Model) o;
		if (arg instanceof Float) {
			if ((float) arg == m.getBatLevel() || (float) arg == m.getAltitude()
					|| (float) arg == m.getTimeUntilTakeOff())
				return;
		} 
		if (m.isInputDeviceConnected())
			sendCommand(m);
	}
}
