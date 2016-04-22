package com.quadromotion.controller;

import java.util.Observable;
import java.util.Observer;

import com.quadromotion.model.Model;

import de.yadrone.base.ARDrone;
import de.yadrone.base.command.CommandManager;

/**
 * This class models the communication between the drone and the computer
 * 
 * @author Gabriel
 * @param ARDrone
 *            the drone
 * @param CommandManager
 *            the drone command manager
 */
public class ARDroneController implements IARDroneController, Observer {

	/**
	 * The speed in direction X
	 */
	float speedX;

	/**
	 * The speed in direction Y
	 */
	float speedY;

	/**
	 * The speed in direction Z
	 */
	float speedZ;

	/**
	 * The speed in direction spin
	 */
	float speedSpin;

	/**
	 * The drone
	 */
	private ARDrone drone = null;

	/**
	 * The command manager
	 */
	private CommandManager cmd = null;

	/**
	 * The model
	 */
	private Model model = null;

	/**
	 * Constructor I
	 * @param model the model
	 */
	public ARDroneController(Model model) {
		
		initialize(model);
	}

	/**
	 * Initializes the drone, the command manager and the model
	 * @param model the model
	 */
	private void initialize(Model model) {
		drone = new ARDrone();
		cmd = drone.getCommandManager();
		this.model = model;
		model.addObserver(this);
	}

	/**
	 * move the drone in every direction
	 */
	@Override
	public void moveDrone(float speedX, float speedY, float speedZ, float speedSpin) {
		cmd.move(speedX, speedY, speedZ, speedSpin);
	}

	/**
	 * sends the hover command
	 */
	@Override
	public void hover() {
		cmd.hover();
	}

	/**
	 * sends the take off command
	 */
	@Override
	public void takeOff() {
		cmd.takeOff();
	}

	/**
	 * sends the landing command
	 */
	@Override
	public void landing() {
		cmd.landing();
	}

	/**
	 * 
	 */
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

	/**
	 * Cleans up the connection to the drone
	 */
	@Override
	public void cleanup() {
		if(cmd.isConnected() && cmd !=null) cmd.close();
		
	}

}
