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
package com.quadromotion.view;

import javax.swing.JButton;

import com.quadromotion.app.App;
import com.quadromotion.model.Model;

import de.yadrone.apps.tutorial.TutorialVideoListener;
import de.yadrone.base.IARDrone;

public class MainViewController {

	private Model model = null;
	private IARDrone drone = null;
	private App app = null;
	private MainView view = null;
	private JButton connectionButton = null;

	/**
	 * Constructor I
	 * 
	 * @param model
	 *            the model
	 * @param droneAttitude
	 * @param mainView
	 *            the view
	 */
	public MainViewController(Model model, IARDrone drone) {
		this.model = model;
		this.drone = drone;

	}

	public void showView() {
		view = new MainView(model, drone, this);
	}

	public void connect() {
		app.run();
	}

	public void setDroneConnected(boolean connection) {
		model.setDroneConnected(connection);
	}

	public void disconnect() {
		model.setControlState("-");
		setDroneConnected(false);
		connectionButton.setText("Drohne verbinden");
		model.setBatLevel(0);
		app.cleanupDrone();
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public void setApp(App app) {
		this.app = app;
	}

	public void connectionButtonChanged(JButton connectionButton) {
		if (!getModel().isDroneConnected()) {
			connectionButton.setSelected(false);
			connectionButton.setText("Drohne trennen");
			setDroneConnected(true);
			connect();
			// connectionButton.removeActionListener(this);
		} else {
			disconnect();
		}

	}

	public JButton getConnectionButton(){
		return connectionButton;
	}
	public void setConnectionButton(JButton connectionButton) {
		this.connectionButton = connectionButton;
	}
}
