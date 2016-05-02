package com.quadromotion.controller;

import java.util.Observable;
import java.util.Observer;

import com.quadromotion.model.Model;
import com.quadromotion.navdata.*;
import de.yadrone.base.IARDrone;

/**
 * This class sends the latest commands permanently to the drone
 * 
 * @author Gabriel
 *
 */
public class SendThread extends Thread implements Observer {
	private String threadName;
	private Model model = null;
	private Model m = new Model();

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
		new NavDataListener(drone, model);
		this.model = model;
		this.model.addObserver(this);
	}

	@Override
	public void run() {
		
	}

	/**
	 * sends the commands to the droneCommander
	 */
	private synchronized void sendCommand(Model m) {

		switch (m.getState()) {
		case "init":
			break;
		case "ready":
			break;
		case "takingOff":
			droneCommander.takeOff();
			break;
		case "waitingTakeOff":
			break;
		case "hovering":
			droneCommander.hover();
			break;
		case "flying":
			droneCommander.moveDrone(m.getSpeedX(), m.getSpeedY(), m.getSpeedZ(), m.getSpeedSpin());
			break;
		case "landing":
			droneCommander.landing();
			break;
		case "waitingLanding":
			break;
		
		default:
			break;
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		sendCommand((Model) o);
	}
}
