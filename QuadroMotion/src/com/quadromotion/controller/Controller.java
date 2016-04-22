package com.quadromotion.controller;

import com.quadromotion.gestures.KeyBoardCommands;
import com.quadromotion.model.Model;
import com.quadromotion.view.ConsolView;

public class Controller {

	private Model model = null;
	private ConsolView consolView = null;

	/**
	 * Constructor I
	 * 
	 * @param model
	 *            the model
	 * @param consolView
	 *            the view
	 */
	public Controller() {

		this.model = new Model();
		this.consolView = new ConsolView(model);
	}

	/**
	 * Constructor II
	 * 
	 * @param model
	 *            the model
	 * @param consolView
	 *            the view
	 */
	public Controller(Model model) {

		this.setModel(model);
		this.consolView = new ConsolView(model);
	}

	/**
	 * Constructor III
	 * 
	 * @param model
	 *            the model
	 * @param consolView
	 *            the view
	 */
	public Controller(Model model, ConsolView consolView) {
		this.model = model;
		this.consolView = consolView;
	}

	public void setSpeedX(double speed) {
		model.setSpeedX(speed);
	}

	public void setSpeedY(double speed) {
		model.setSpeedY(speed);
	}

	public void setSpeedZ(double speed) {
		model.setSpeedZ(speed);
	}

	public void setSpeedSpin(double speed) {
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

	public ConsolView getView() {
		return consolView;
	}

	public void setView(ConsolView view) {
		this.consolView = view;
	}
}
