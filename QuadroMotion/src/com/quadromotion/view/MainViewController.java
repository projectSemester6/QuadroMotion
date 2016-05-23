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
		app.cleanup();
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
			setDroneConnected(false);
			disconnect();
			connectionButton.setText("Drohne verbinden");
		}

	}

	public JButton getConnectionButton(){
		return connectionButton;
	}
	public void setConnectionButton(JButton connectionButton) {
		this.connectionButton = connectionButton;
	}
}
