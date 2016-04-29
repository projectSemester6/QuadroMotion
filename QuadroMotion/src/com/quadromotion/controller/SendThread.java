package com.quadromotion.controller;

import java.util.Observable;
import java.util.Observer;

import com.quadromotion.model.Model;

import de.yadrone.base.ARDrone;
import de.yadrone.base.IARDrone;
import de.yadrone.base.command.CommandManager;

/**
 * This class sends the latest commands permanently to the drone
 * 
 * @author Gabriel
 *
 */
public class SendThread extends Thread implements Observer {

	private static final int SLEEP = 200;
	private String threadName;

	private Model model = null;
	private Model m = null;
	private ARDroneCommander droneCommander = null;

	// private IARDrone drone = null;
	// private CommandManager cmd = null;

	/**
	 * Constructor I
	 * 
	 * @param threadName
	 *            the thread name
	 * @param model
	 *            the model
	 */
	public SendThread(String threadName, Model model) {
		this.threadName = threadName;
		this.model = model;
		this.m = new Model();
		this.droneCommander = new ARDroneCommander();
		model.addObserver(this);
	}

	/**
	 * Constructor II
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
		this.model = model;
		this.m = new Model();
		// this.drone = drone;
		this.droneCommander = new ARDroneCommander(drone);
		model.addObserver(this);
	}

	@Override
	public void run() {
		System.out.println("Ich bin der Thread " + threadName);
//		int c = 0;
//		long startTime = System.currentTimeMillis();
		droneCommander.animateLEDs();
		do {
//			c++;
//			System.out.println(c + "er Durchlauf");
//			System.out.println("Zeit seit start: " + (System.currentTimeMillis() - startTime));
			sendCommand();
//			yield();
			try {
				Thread.sleep(SLEEP);
			}

			catch (InterruptedException ie) {
				System.out.println("Thread " + threadName + " interrupted...");
			}
		} while (!this.isInterrupted());

	}

	/**
	 * sends the commands to the droneCommander
	 */
	private synchronized void sendCommand() {
		// TODO FinaleStateMachine

		System.out.println("\nSender: " + model.getState());
		
		switch (model.getState()) {
		case "init":
			break;
		case "ready":
//			droneCommander.animateLEDs();
			break;
		case "takingOff":
			droneCommander.takeOff();
			break;
		case "hovering":
			droneCommander.hover();
			break;
		case "flying":
			droneCommander.moveDrone(m.getSpeedX(),m.getSpeedY(), m.getSpeedZ(), m.getSpeedSpin());
			
			System.out.println(m.getSpeedX());
			System.out.println(m.getSpeedY());
			System.out.println(m.getSpeedZ());
			System.out.println(m.getSpeedSpin());
			break;
		case "landing":
			droneCommander.landing();
			model.setState("ready");
			break;
		default:
			break;
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		m = (Model) o;
		// sendCommand();
	}
}
