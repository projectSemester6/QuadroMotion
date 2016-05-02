package com.quadromotion.app;

import com.leapmotion.leap.Controller;
import com.quadromotion.controller.SendThread;
import com.quadromotion.gestures.LeapMotion;
import com.quadromotion.model.Model;
import com.quadromotion.model.Services;
import com.quadromotion.view.MainView;
import com.quadromotion.view.VideoListener;

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
	private Controller leapController = null;
	private LeapMotion leap = null;
	private Services service = null;

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
		leapController = new Controller();
		leapController.addListener(leap);
	}

	private void initView() {
		new VideoListener(drone);
		new MainView(model, drone);
	}

	private void initDrone() {
		drone = new ARDrone();
		sender = new SendThread("Sender", model, drone);
	}

	public void run() {
		drone.start();
		sender.start();
	}

	public void cleanup() {
		if (model != null)
			model = null;

		if (drone != null)
			drone.stop();
	}
}
