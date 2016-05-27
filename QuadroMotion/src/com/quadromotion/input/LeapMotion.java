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
package com.quadromotion.input;

import com.leapmotion.leap.*;
import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.Listener;
import com.leapmotion.leap.Vector;
import com.quadromotion.service.Services;

/**
 * This class is responsible for receiving the data from the leap motion.<br>
 * The method onFrame() is called from the listener every time a new frame
 * arrives from the leap motion.
 * 
 * @author Gabriel Urech <br>
 *         Alexis Stephan
 *
 */
public class LeapMotion extends Listener {

	/**
	 * The services class
	 */
	private Services services = null;

	/**
	 * the number of hands in the current frame
	 */
	private int anzahlHaenden = 0;

	private boolean rightHand;
	private boolean leftHand;

	private float rechtPitch = 0;
	private float rechtYaw = 0;
	private float rechtRoll = 0;
	private float rechtSphereRadius = 0;
	private float rechtThrust = 0; // Y direcgion of leap motion
	private float rechtSide = 0; // X direcgion of leap motion
	private float rechtForBack = 0; // Z direcgion of leap motion

	private float linkPitch = 0;
	private float linkYaw = 0;
	private float linkRoll = 0;
	private float linkSphereRadius = 0;
	private float linkThrust = 0; // Y direction of leap motion
	private float linkSide = 0; // X direcgion of leap motion
	private float linkForBack = 0; // Z direcgion of leap motion

	/**
	 * The rate in milliseconds at which the frames should be analyzed. <br>
	 * As test has shown, a frame arrives every millisecond. <br>
	 * This is way to much, therefore we inserted this variable to let the
	 * Thread sleep for this time.
	 */
	private final int rate = 20;
	private long timeStamp = 0;
	private long timeNow;

	/**
	 * Constructor
	 * 
	 * @param s
	 *            the services class
	 */
	public LeapMotion(Services s) {
		super();
		this.services = s;
	}

	/**
	 * This method is called by the leap listener on every frame received from
	 * the leap motion.
	 * 
	 * @param controller
	 *            the controller, which is responsible for the connection<br>
	 *            and the communication to the leap motion device
	 */
	public void onFrame(Controller controller) {
		timeStamp = System.currentTimeMillis();
		Frame frame = controller.frame();

		setAnzahlHaenden(frame.hands().count());

		if (anzahlHaenden > 0 && anzahlHaenden <= 2) {
			rightHand = false;
			leftHand = false;
			// Get hands
			for (Hand hand : frame.hands()) {

				// Get the hand's normal vector and direction as well as the
				// position of the hand

				Vector normal = hand.palmNormal();
				Vector direction = hand.direction();
				Vector handCenter = hand.palmPosition();

				if (hand.isRight()) {
					rightHand = hand.isRight();
					rechtPitch = (float) Math.toDegrees(direction.pitch());
					rechtYaw = (float) Math.toDegrees(direction.yaw());
					rechtRoll = (float) Math.toDegrees(normal.roll());
					rechtSphereRadius = hand.sphereRadius();
					rechtThrust = handCenter.getY();
					rechtSide = handCenter.getX();
					rechtForBack = handCenter.getZ();
				}

				if (hand.isLeft()) {
					leftHand = hand.isLeft();
					linkPitch = (float) Math.toDegrees(direction.pitch());
					linkRoll = (float) Math.toDegrees(normal.roll());
					linkYaw = (float) Math.toDegrees(direction.yaw());
					linkSphereRadius = hand.sphereRadius();
					linkThrust = handCenter.getY();
					linkSide = handCenter.getX();
					linkForBack = handCenter.getZ();
				}
			}
		} else {
			rightHand = false;
			leftHand = false;
		}
		services.computeGestures(this);
		try {
			Thread.sleep(rate);
		} catch (Exception ignore) {

		}
		timeNow = System.currentTimeMillis();
		// System.out.println("onFrame: "+(timeNow-timeStamp));
		// System.out.println("LeapMotion: "+ System.currentTimeMillis());
	}

	public void onInit(Controller controller) {
		System.out.println("Leap initialized");
	}

	public void onConnect(Controller controller) {
		System.out.println("Connected");
		services.setLeapState(true);
		services.computeGestures(this);
	}

	public void onDisconnect(Controller controller) {
		// Note: not dispatched when running in a debugger.
		System.out.println("Disconnected");
		services.setLeapState(false);
		services.computeGestures(this);
	}

	public void onExit(Controller controller) {
		System.out.println("Exited");
	}

	public float getPitchRightHand() {
		return rechtPitch;
	}

	public float getRollRightHand() {
		return rechtRoll;
	}

	public float getYawRightHand() {
		return rechtYaw;
	}

	public float getThrustRightHand() {
		return rechtThrust;
	}

	public float getSphereRadiusRightHand() {
		return rechtSphereRadius;
	}

	public float getPitchLeftHand() {
		return linkPitch;
	}

	public float getRollLeftHand() {
		return linkRoll;
	}

	public float getYawLeftHand() {
		return linkYaw;
	}

	public float getThrustLeftHand() {
		return linkThrust;
	}

	public float getSpehreRadiusLeftHand() {
		return linkSphereRadius;
	}

	public void setAnzahlHaenden(int anzahlHaenden) {
		this.anzahlHaenden = anzahlHaenden;
	}

	public int getAnzahlHaenden() {
		return anzahlHaenden;
	}

	public boolean getRightHand() {
		return rightHand;
	}

	public boolean getLeftHand() {
		return leftHand;
	}

	public float getLinkSide() {
		return linkSide;
	}

	public void setLinkSide(float linkSide) {
		this.linkSide = linkSide;
	}

	public float getLinkForBack() {
		return linkForBack;
	}
}
