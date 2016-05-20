package com.quadromotion.app;

import com.leapmotion.leap.Controller;
import com.quadromotion.drone.SendThread;
import com.quadromotion.input.*;
import com.quadromotion.model.Model;
import com.quadromotion.service.Services;
import com.quadromotion.navdata.*;
import com.quadromotion.view.*;

import de.yadrone.base.ARDrone;
import de.yadrone.base.IARDrone;

/**
 * Diese Klasse enthaelt die boot() und die run() Methode
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
//	private NavDataController navDataController = null; 

	public App() {
		this.model = new Model();
	}

	public void boot() {
		initLeap();
		initView();
	}
	
	public void run() {
		initDrone();
		drone.start();
		sender.start();
//		new VideoListener(drone);
	}

	private void initLeap() {
		service = new Services();
		service.setInputController(new InputController(model));
		leap = new LeapMotion(service);
		leapController = new Controller();
		leapController.addListener(leap);
	}

	private void initView() {
		viewController = new MainViewController(model, drone);
		viewController.setApp(this);
		viewController.showView();
//		kbc = new KeyBoardCommands();
//		kbc.setInputController(new InputController(model));
	}

	private void initDrone() {
		drone = new ARDrone();
		sender = new SendThread("Sender", model, drone);
		new NavDataController(model, drone);
	}

	public void cleanup() {
		if (drone != null){
			drone.stop();
		}
	}
}
