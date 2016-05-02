package com.quadromotion.controller;

import com.quadromotion.model.Model;
import com.quadromotion.view.MainView;

public class MainViewController {

	private Model model = null;
	private MainView mainView = null;

	/**
	 * Constructor I
	 * 
	 * @param model
	 *            the model
	 * @param droneAttitude
	 * @param mainView
	 *            the view
	 */
	public MainViewController(Model model) {
		
		this.setModel(model);
//		this.mainView = new MainView(model);
	}

	public void showView() {
		mainView.setVisible(true);
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public MainView getView() {
		return mainView;
	}

	public void setView(MainView view) {
		this.mainView = view;
	}
}
