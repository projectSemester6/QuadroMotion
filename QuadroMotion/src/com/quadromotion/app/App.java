package com.quadromotion.app;

import com.leapmotion.leap.Controller;
import com.quadromotion.controller.SendThread;
import com.quadromotion.controller.ServiceController;
import com.quadromotion.gestures.LeapMotion;
import com.quadromotion.model.Model;
import com.quadromotion.model.Services;
import com.quadromotion.navdata.*;
import com.quadromotion.view.*;
import com.quadromotion.gestures.*;

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
	private MainViewController viewController = null;
	private NavDataController navDataController = null; 
	private KeyBoardCommands kbc = null;

	public App() {
		this.model = new Model();
	}

	public void boot() {
		initLeap();
		initView();
		kbc = new KeyBoardCommands(model);
	}
	
	public void run() {
		initDrone();
		drone.start();
		sender.start();
	}

	private void initLeap() {
		service = new Services();
		service.setServiceController(new ServiceController(model));
		leap = new LeapMotion(service);
		leapController = new Controller();
		leapController.addListener(leap);
	}

	private void initView() {
//		new VideoListener(drone);
		viewController = new MainViewController(model, drone);
		viewController.setApp(this);
		viewController.showView();
	}

	private void initDrone() {
		drone = new ARDrone();
		sender = new SendThread("Sender", model, drone);
		navDataController = new NavDataController(model, drone);
	}

	public void cleanup() {
		if (drone != null){
			drone.stop();
		}
	}
}
