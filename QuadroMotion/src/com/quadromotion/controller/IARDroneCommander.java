package com.quadromotion.controller;

/**
 * This Interface defines the methods needed for the communication with the drone.
 * @author Gabriel
 *
 */
public interface IARDroneCommander {
	
	/**
	 * This method is needed to move the drone in every direction
	 * @param speedX the speed in direction X, can be positive or negative
	 * @param speedY the speed in direction Y, can be positive or negative
	 * @param speedZ the speed in direction Z, can be positive or negative
	 * @param speedSpin the speed to spin, can be positive or negative
	 */
	public void moveDrone(float speedX, float speedY, float speedZ, float speedSpin);
	
	/**
	 * This method is needed to send the hover command
	 */
	public void hover();
	
	/**
	 * This method is needed to send the take off command
	 */
	public void takeOff();
	
	/**
	 * This method is needed to send the landing command
	 */
	public void landing();
	
	/**
	 * This method is for cleaning up
	 */
	public void cleanup();

}
