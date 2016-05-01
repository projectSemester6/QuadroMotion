package com.quadromotion.app;

import com.leapmotion.leap.Controller;
import com.quadromotion.controller.MainViewController;
import com.quadromotion.controller.SendThread;
import com.quadromotion.gestures.LeapMotion;
import com.quadromotion.model.Model;
import com.quadromotion.model.Services;
import com.quadromotion.view.MainView;

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

	private static QuadroMotionMain qmm = null;
	private static Model model = null;
	private static SendThread sender = null;
	private static IARDrone drone = null;
	private static Controller leapController = null;
	private static LeapMotion leap = null;
	private static Services service = null;

	private MainViewController c = null;
	private MainView view = null;

	public App() {
		this(new Model());
	}

	public App(Model model) {
		this.model = model;
	}

	public void boot() {
		initLeap();
		initDrone();
		initView();
	}

	private void initLeap() {
		service = new Services(model);
		leap = new LeapMotion(service);
		leapController = new Controller(leap);
	}

	private void initView() {
		c = new MainViewController(model);
		view = c.getView();
	}

	private void initDrone() {
		drone = new ARDrone();
		sender = new SendThread("Sender", model, drone);
	
	}

	public void run() {
		sender.start();
		drone.start();
		sender.setPriority(Thread.MAX_PRIORITY);
		this.c.showView();
	}

	public void cleanup() {
		if (model != null)
			model = null;
		
		if (drone != null)
			drone.stop();
	}

	public void setDrone(IARDrone drone) {
		// TODO Auto-generated method stub
		this.drone = drone;
	}

	public Model getModel() {
		return model;
	}

	public IARDrone getDrone() {
		return drone;
	}

	public SendThread getSender() {
		return sender;
	}

	public Services getService() {
		return service;
	}

	public LeapMotion getLeapMotion() {
		return leap;
	}

	public void setQuadroMotionMain(QuadroMotionMain qmm) {
		this.qmm = qmm;
	}
}
