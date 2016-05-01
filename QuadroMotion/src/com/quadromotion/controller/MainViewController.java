package com.quadromotion.controller;

import java.util.Observable;
import java.util.Observer;

import com.quadromotion.gestures.KeyBoardCommands;
import com.quadromotion.model.Model;
import com.quadromotion.view.MainView;

public class MainViewController{

	private Model model = null;
	private MainView mainView = null;

	/**
	 * Constructor I
	 * 
	 * @param model
	 *            the model
	 * @param mainView
	 *            the view
	 */
	public MainViewController() {

		this.model = new Model();
		this.mainView = new MainView(model);
	}

	/**
	 * Constructor II
	 * 
	 * @param model
	 *            the model
	 * @param mainView
	 *            the view
	 */
	public MainViewController(Model model) {

		this.setModel(model);
		this.mainView = new MainView(model);
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
