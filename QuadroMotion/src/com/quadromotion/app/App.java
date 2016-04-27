package com.quadromotion.app;


import com.quadromotion.controller.SendThread;
import com.quadromotion.model.Model;

import de.yadrone.base.ARDrone;
import de.yadrone.base.IARDrone;

/**
 * Diese Klasse enthaelt die boot() und die run() Methode und steuert den
 * Programmablauf
 * 
 * @author Alexis
 *
 */
public class App {

	private Model model = null;
	private SendThread sender = null;
	private IARDrone drone = null;


	public App(){
		this(new Model());
	}
	
	public App(Model model){
		this.model = model;
	}
	
	public void boot() {
		sender = new SendThread("Sender", model, drone);
	}

	public void run() {
		sender.start();
		drone.start();
	}

	public void cleanup() {
		model = null;
	}

	public void setDrone(IARDrone drone) {
		// TODO Auto-generated method stub
		this.drone = drone;
	}
}
