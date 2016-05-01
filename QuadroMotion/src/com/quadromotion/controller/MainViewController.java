package com.quadromotion.controller;

import com.quadromotion.gestures.KeyBoardCommands;
import com.quadromotion.model.Model;
import com.quadromotion.view.MainView;

public class MainViewController {

	private Model model = null;
	private MainView consolView = null;

	/**
	 * Constructor I
	 * 
	 * @param model
	 *            the model
	 * @param consolView
	 *            the view
	 */
	public MainViewController() {

		this.model = new Model();
		this.consolView = new MainView(model);
	}

	/**
	 * Constructor II
	 * 
	 * @param model
	 *            the model
	 * @param consolView
	 *            the view
	 */
	public MainViewController(Model model) {

		this.setModel(model);
		this.consolView = new MainView(model);
	}

	/**
	 * Constructor III
	 * 
	 * @param model
	 *            the model
	 * @param consolView
	 *            the view
	 */
	public MainViewController(Model model, MainView consolView) {
		this.model = model;
		this.consolView = consolView;
	}

	public void setSpeedX(int speed) {
		model.setSpeedX(speed);
	}

	public void setSpeedY(int speed) {
		model.setSpeedY(speed);
	}

	public void setSpeedZ(int speed) {
		model.setSpeedZ(speed);
	}

	public void setSpeedSpin(int speed) {
		model.setSpeedSpin(speed);
	}
	
	public void setLandingCommand(boolean command){
		model.setLandingCommand(command);
	}
	
	public void setTakeOffCommand(boolean command){
		model.setTakeOffCommand(command);
	}
	
	public void setHoverCommand(boolean command){
		model.setHoverCommand(command);
	}

	public void showView() {
		consolView.setVisible(true);
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public MainView getView() {
		return consolView;
	}

	public void setView(MainView view) {
		this.consolView = view;
	}
}
