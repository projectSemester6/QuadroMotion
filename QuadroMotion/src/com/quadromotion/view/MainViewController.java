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

/**
 * The main view controller.
 * 
 * @author Gabriel Urech
 *
 */
public class MainViewController {

	/** The model. */
	private Model model = null;
	
	/** The drone. */
	private IARDrone drone = null;
	
	/** The app. */
	private App app = null;
	
	/** The view. */
	private MainView view = null;
	
	/** The connection button. */
	private JButton connectionButton = null;

	/**
	 * * Allocates a new <code>MainViewController</code> object so that it has
	 * <code>model</code> as the model and has <code>drone</code> as the drone.
	 * 
	 * @param model
	 *            the model.
	 * @param drone
	 *            the drone.
	 */
	public MainViewController(Model model, IARDrone drone) {
		this.model = model;
		this.drone = drone;

	}

	/**
	 * Creates a new main view.
	 */
	public void showView() {
		view = new MainView(model, drone, this);
	}

	/**
	 * Connects to the drone.
	 */
	public void connect() {
		app.run();
	}

	/**
	 * Sets the drone connected.
	 *
	 * @param connection the new drone connected
	 */
	public void setDroneConnected(boolean connection) {
		model.setDroneConnected(connection);
	}

	/**
	 * Disconnects from the drone.
	 */
	public void disconnect() {
		model.setControlState("-");
		setDroneConnected(false);
		connectionButton.setText("Drohne verbinden");
		model.setBatLevel(0);
		app.cleanupDrone();
	}

	/**
	 * Gets the model.
	 *
	 * @return the model
	 */
	public Model getModel() {
		return model;
	}

	/**
	 * Sets the model.
	 *
	 * @param model the new model
	 */
	public void setModel(Model model) {
		this.model = model;
	}

	/**
	 * Is called by the class <code>App</code> to give a reference back to
	 * itself.
	 * 
	 * @param app the App.
	 */
	public void setApp(App app) {
		this.app = app;
	}

	/**
	 * Connection button changed.
	 *
	 * @param connectionButton the connection button
	 */
	public void connectionButtonChanged(JButton connectionButton) {
		if (!getModel().isDroneConnected()) {
			connectionButton.setSelected(false);
			connectionButton.setText("Drohne trennen");
			setDroneConnected(true);
			connect();
		} else {
			disconnect();
		}

	}

	/**
	 * Gets the connection button.
	 *
	 * @return the connection button
	 */
	public JButton getConnectionButton() {
		return connectionButton;
	}

	/**
	 * Sets the connection button.
	 *
	 * @param connectionButton the new connection button
	 */
	public void setConnectionButton(JButton connectionButton) {
		this.connectionButton = connectionButton;
	}
}
