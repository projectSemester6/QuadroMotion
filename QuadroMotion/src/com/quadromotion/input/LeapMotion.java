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
 * The method <code>onFrame()</code> is called from the listener every time a new frame
 * arrives from the leap motion.
 * 
 * @author Gabriel Urech <br>
 *         Alexis Stephan
 *
 */
public class LeapMotion extends Listener {

	/**
	 * The services class.
	 */
	private Services services = null;

	/**
	 * The number of hands in the current frame.
	 */
	private int anzahlHaenden = 0;

	private boolean rightHand;
	private boolean leftHand;

	/**
	 * The right pitch.
	 */
	private float rechtPitch = 0;
	
	/**
	 * The right yaw.
	 */
	private float rechtYaw = 0;
	
	/**
	 * The right roll.
	 */
	private float rechtRoll = 0;
	
	/**
	 * The right sphere radius.
	 */
	private float rechtSphereRadius = 0;
	
	/**
	 * The right thrust.
	 */
	private float rechtThrust = 0; // Y direcgion of leap motion
	
	/**
	 * The distance from the right hand to the center in direction x.
	 */
	private float rechtSide = 0; // X direcgion of leap motion
	
	/**
	 * The distance from the right hand to the center in direction z.
	 */
	private float rechtForBack = 0; // Z direcgion of leap motion

	/**
	 * The left pitch.
	 */
	private float linkPitch = 0;
	
	/**
	 * The left yaw.
	 */
	private float linkYaw = 0;
	
	/**
	 * The left roll.
	 */
	private float linkRoll = 0;
	
	/**
	 * The left sphere radius.
	 */
	private float linkSphereRadius = 0;
	
	/**
	 * The left thrust.
	 */
	private float linkThrust = 0; // Y direction of leap motion
	
	/**
	 * The  distance from the left hand to the center in direction x.
	 */
	private float linkSide = 0; // X direcgion of leap motion
	
	 /**
	  * The distance from the left hand to the center in direction z.
	  */
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
	 * Allocates a new <code>LeapMotion</code> object so that it has
	 * <code>services</code> as the intelligence of the program.
	 * 
	 * @param services
	 *            the services class.
	 */
	public LeapMotion(Services services) {
		super();
		this.services = services;
	}

	/**
	 * This method is called by the leap listener on every frame received from
	 * the leap motion.
	 * 
	 * @param controller
	 *            the controller, which is responsible for the connection<br>
	 *            and the communication to the leap motion device.
	 */
	public void onFrame(Controller controller) {
		timeStamp = System.currentTimeMillis();
		Frame frame = controller.frame();

		anzahlHaenden = frame.hands().count();

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

	/**
	 * 
	 * @return the pitch of the right hand.
	 */
	public float getPitchRightHand() {
		return rechtPitch;
	}

	/**
	 * 
	 * @return the roll of the right hand.
	 */
	public float getRollRightHand() {
		return rechtRoll;
	}

	/**
	 * 
	 * @return the yaw of the right hand.
	 */
	public float getYawRightHand() {
		return rechtYaw;
	}

	/**
	 * 
	 * @return the thrust of the right hand.
	 */
	public float getThrustRightHand() {
		return rechtThrust;
	}

	/**
	 * 
	 * @return the sphere radius of the right hand.
	 */
	public float getSphereRadiusRightHand() {
		return rechtSphereRadius;
	}

	/**
	 * 
	 * @return the pitch of the left hand.
	 */
	public float getPitchLeftHand() {
		return linkPitch;
	}

	/**
	 * 
	 * @return the roll of the left hand.
	 */
	public float getRollLeftHand() {
		return linkRoll;
	}

	/**
	 * 
	 * @return the yaw of the left hand.
	 */
	public float getYawLeftHand() {
		return linkYaw;
	}

	/**
	 * 
	 * @return the thrust of the left hand.
	 */
	public float getThrustLeftHand() {
		return linkThrust;
	}

	/**
	 * 
	 * @return the sphere radius of the left hand.
	 */
	public float getSpehreRadiusLeftHand() {
		return linkSphereRadius;
	}

	/**
	 * 
	 * @return the number of hands.
	 */
	public int getAnzahlHaenden() {
		return anzahlHaenden;
	}

	/**
	 * 
	 * @return true, if the right hand is in the frame<br>
	 *         false, if the right hand is missing.
	 */
	public boolean getRightHand() {
		return rightHand;
	}

	/**
	 * 
	 * @return true, if the left hand is in the frame<br>
	 *         false, if the left hand is missing.
	 */
	public boolean getLeftHand() {
		return leftHand;
	}

	/**
	 * 
	 * @return the distance from the left hand to the center of the leap motion in direction x.
	 */
	public float getLinkSide() {
		return linkSide;
	}

	/**
	 * 
	 * @return the distance from the left hand to the center of the leap motion in direction x.
	 */
	public float getRechtSide() {
		return rechtSide;
	}

	/**
	 * 
	 * @return the distance from the left hand to the center of the leap motion in direction z.
	 */
	public float getLinkForBack() {
		return linkForBack;
	}
	
	/**
	 * 
	 * @return the distance from the right hand to the center of the leap motion in direction z.
	 */
	public float getRechtForBack() {
		return rechtForBack;
	}
}
