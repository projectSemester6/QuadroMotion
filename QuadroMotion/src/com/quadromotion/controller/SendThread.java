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

	private static final int SLEEP = 100;
	private String threadName;

	private Model model = null;
	private Model m = null;
	private ARDroneCommander droneCommander = null;
//	private IARDrone drone = null;
//	private CommandManager cmd = null;

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
//		this.drone = drone;
		this.droneCommander = new ARDroneCommander(drone);
		model.addObserver(this);
	}

	@Override
	public void run() {
		System.out.println("Ich bin der Thread " + threadName);
		int c = 0;
		long startTime = System.currentTimeMillis();
		do {
			c++;
			System.out.println(c+"er Durchlauf");
			System.out.println("Zeit seit start: " + (System.currentTimeMillis() - startTime));
			sendCommand();
			yield();
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

		if (!m.isFlying()) {
			if (m.getTakeOffCommand()) {
				droneCommander.takeOff();
				droneCommander.animateLEDs();
				System.out.println("drone is taking off!");
				model.setIsFlying(true);
			} else {
				System.out.println("waiting for taking off...");
			}
		} else {

			if (m.getLandingCommand()) {
				droneCommander.landing();
				System.out.println("drone is landing!");
				model.setIsFlying(false);
			} else if (m.getSpeedX() != 0 || m.getSpeedY() != 0 || m.getSpeedZ() != 0 || m.getSpeedSpin() != 0) {
				droneCommander.moveDrone(m.getSpeedX(), m.getSpeedY(), m.getSpeedZ(), m.getSpeedSpin());
				System.out.println("drone is moving");

			} else {
				droneCommander.hover();
				System.out.println("drone is hovering!");
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		m = (Model) o;
//		sendCommand();
	}

}
