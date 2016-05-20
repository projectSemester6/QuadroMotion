package com.quadromotion.view;

import com.quadromotion.app.App;
import com.quadromotion.model.Model;

import de.yadrone.base.IARDrone;

public class MainViewController {

	private Model model = null;
	private IARDrone drone = null;
	private App app = null;
	
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
	
	public void showView(){
		new MainView(model, drone, this);
	}
	
	public void connect(){
		app.run();
	}
	
	public void setDroneConnected(boolean connection){
		model.setDroneConnected(connection);
	}
	
	public void disconnect(){
		app.cleanup();
	}
	
	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}
	
	public void setApp(App app){
		this.app = app;
	}
}
