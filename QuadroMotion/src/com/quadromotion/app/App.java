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

import java.awt.image.BufferedImage;

import javax.swing.SwingUtilities;

import com.leapmotion.leap.Controller;
import com.quadromotion.drone.SendThread;
import com.quadromotion.input.*;
import com.quadromotion.model.Model;
import com.quadromotion.service.Services;
import com.quadromotion.navdata.*;
import com.quadromotion.view.*;

import de.yadrone.apps.controlcenter.CCFrame;
import de.yadrone.apps.controlcenter.plugins.video.VideoCanvas;
import de.yadrone.apps.controlcenter.plugins.video.VideoPanel;
import de.yadrone.apps.tutorial.TutorialVideoListener;
import de.yadrone.base.ARDrone;
import de.yadrone.base.IARDrone;
import de.yadrone.base.exception.ARDroneException;
import de.yadrone.base.exception.IExceptionListener;
import de.yadrone.base.video.ImageListener;
import de.yadrone.base.video.VideoManager;

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
	// private NavDataController navDataController = null;

	public App() {
		this.model = new Model();
	}

	public void boot() {
		initView();
		initLeap();
	}

	public void run() {
		initDrone();
		drone.start();
		// sender.start();
		// new VideoListener(drone);
		// new TutorialVideoListener(drone);
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
	}

	private void initDrone() {

		if (drone == null) {
			drone = new ARDrone();
			drone.addExceptionListener(new IExceptionListener() {

				public void exeptionOccurred(ARDroneException exc) {
					// viewController.disconnect();
					// exc.printStackTrace();
					// System.out.println("Suppressed: "+exc.getSuppressed());
					 System.out.println("Message: " + exc.getClass().getSimpleName());
					if (exc.getClass().getSimpleName().contains("NavDataException")||exc.getClass().getSimpleName().contains("CommandException")) {
						cleanup();
						model.setDroneConnected(false);
						viewController.getConnectionButton().setText("Drohne verbinden");
					}
				}
			});
		}
		new Thread(new SendThread("Sender", model, drone)).start();
		// sender = new SendThread("Sender", model, drone);
		new NavDataController(model, drone);
	}

	public void cleanup() {
		if (drone != null) {
			drone.stop();
			drone = null;
		}
	}
}
