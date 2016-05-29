/* Copyright 2016 Gabriel Urech, Alexis Stephan, Simon Henzmann
 * 
 * This file is part of QuadroMotion.
 * 
 * QuadroMotion is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * QuadroMotion is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with DokChess.  If not, see <http://www.gnu.org/licenses/>.
 */
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
import de.yadrone.base.exception.ARDroneException;
import de.yadrone.base.exception.IExceptionListener;

/**
 * This class holds all components and provides the two methods
 * <code>boot()</code> and <code>run()</code>.<br>
 * 
 * @author Alexis Stephan<br>
 *         Gabriel Urech
 *
 */
public class App {

	/**
	 * The model.
	 */
	private Model model = null;

	/**
	 * The send thread.
	 */
	private SendThread sender = null;

	/**
	 * The ardrone interface.
	 */
	private IARDrone drone = null;

	/**
	 * The leap motion controller.
	 */
	private Controller leapController = null;

	/**
	 * The leap motion.
	 */
	private LeapMotion leap = null;

	/**
	 * The services class.
	 */
	private Services service = null;

	/**
	 * The main view controller.
	 */
	private MainViewController viewController = null;

	/**
	 * The Thread in which the send thread runs.
	 */
	private Thread t = null;

	/**
	 * Allocates a new <code> App</code> object and creates a new
	 * <code>model</code> object.
	 */
	public App() {
		this.model = new Model();
	}

	/**
	 * Instantiate the main view and all components for needed for the leap
	 * motion.
	 */
	public void boot() {
		initView();
		initLeap();
	}

	/**
	 * Instantiates the drone and the send thread and starts them.
	 */
	public void run() {
		initDrone();
		drone.start();
		if (sender == null) {
			sender = new SendThread("Sender", model, drone);
			t = new Thread(null, sender, sender.getThreadName());
			t.start();
		}
	}

	/**
	 * Initializes all components needed for the leap motion.
	 */
	private void initLeap() {
		service = new Services();
		service.setInputController(new InputController(model));
		leap = new LeapMotion(service);
		leapController = new Controller();
		leapController.addListener(leap);
	}

	/**
	 * Initializes the main view.
	 */
	private void initView() {
		viewController = new MainViewController(model, drone);
		viewController.setApp(this);
		viewController.showView();
	}

	/**
	 * Initializes the ardrone.
	 */
	private void initDrone() {
		if (drone == null) {
			drone = new ARDrone();
			drone.addExceptionListener(new IExceptionListener() {

				public void exeptionOccurred(ARDroneException exc) {
					System.out.println("Message: " + exc.getClass().getSimpleName());
					if (exc.getClass().getSimpleName().contains("NavDataException")
							|| exc.getClass().getSimpleName().contains("CommandException")) {
						cleanupDrone();
						model.setControlState("-");
						model.setDroneConnected(false);
						viewController.getConnectionButton().setText("Drohne verbinden");
					}
				}
			});
		}
		new NavDataController(model, drone);
	}

	/**
	 * Stops and cleans up the the ardrone.
	 */
	public void cleanupDrone() {
		if (drone != null) {
			drone.stop();
			drone = null;
		}
	}
}
