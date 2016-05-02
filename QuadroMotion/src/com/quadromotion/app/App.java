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

	private Model model = null;
	private static SendThread sender = null;
	private static IARDrone drone = null;
	private static Controller leapController = null;
	private static LeapMotion leap = null;
	private static Services service = null;

	private MainViewController viewController = null;
	private MainView view = null;

	public App() {
		this.model = new Model();
	}

	public void boot() {
		initLeap();
		initDrone();
		initView();
	}

	private void initLeap() {
		service = new Services(model);
		leap = new LeapMotion(service);
		setLeapController(new Controller(leap));
	}

	private void initView() {
		viewController = new MainViewController(model);
		setView(viewController.getView());
		view.setDrone(drone);
	}

	private void initDrone() {
		drone = new ARDrone();
		sender = new SendThread("Sender", model, drone);
	}

	public void run() {
		drone.start();
		sender.start();
//		sender.setPriority(Thread.MAX_PRIORITY);
		viewController.showView();
	}

	public void cleanup() {
		if (model != null)
			model = null;

		if (drone != null)
			drone.stop();
	}

	public void setDrone(IARDrone drone) {
		App.drone = drone;
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

	public MainView getView() {
		return view;
	}

	public void setView(MainView view) {
		this.view = view;
	}

	public static Controller getLeapController() {
		return leapController;
	}

	public static void setLeapController(Controller leapController) {
		App.leapController = leapController;
	}
}
