package com.quadromotion.gestures;

import com.leapmotion.leap.*;
import com.leapmotion.leap.Controller;
import com.leapmotion.leap.GestureList;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.Listener;
import com.leapmotion.leap.Vector;
import com.quadromotion.model.Services;

public class LeapMotion extends Listener implements IGestures {

	private int anzahlHaenden = 0;
	private boolean rightHand;
	private boolean leftHand;

	private float rechtPitch = 0;
	private float rechtYaw = 0;
	private float rechtRoll = 0;
	private float rechtSphereRadius = 0;
	private float rechtThrust = 0; 		// Y direcgion of leap motion
	private float rechtSide = 0;		// X direcgion of leap motion
	private float rechtForBack = 0; 	// Z direcgion of leap motion

	private float linkPitch = 0;
	private float linkYaw = 0;
	private float linkRoll = 0;
	private float linkSphereRadius = 0;
	private float linkThrust = 0; 		// Y direction of leap motion
	private float linkSide = 0; 		// X direcgion of leap motion
	private float linkForBack = 0; 		// Z direcgion of leap motion


	private Services services = null;

	public LeapMotion(Services s) {
		super();
		this.services = s;
	}

	public void onInit(Controller controller) {
		System.out.println("Leap initialized");
		services.computeGestures(this);
	}

	public void onConnect(Controller controller) {
		System.out.println("Connected");
		// controller.enableGesture(Gesture.Type.TYPE_SWIPE);
		// controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
		// controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
		// controller.enableGesture(Gesture.Type.TYPE_KEY_TAP);
		services.setLeapConnected(true);
	}

	public void onDisconnect(Controller controller) {
		// Note: not dispatched when running in a debugger.
		System.out.println("Disconnected");
		services.setLeapConnected(false);
	}

	public void onExit(Controller controller) {
		System.out.println("Exited");
	}

	/**
	 * @param on
	 *            frame
	 */
	public void onFrame(Controller controller) {

		Frame frame = controller.frame();

		setAnzahlHaenden(frame.hands().count());
		GestureList gestures = frame.gestures();

		if (anzahlHaenden > 0) {

			// Get hands
			for (Hand hand : frame.hands()) {

				// Get the hand's normal vector and direction

				Vector normal = hand.palmNormal();
				Vector direction = hand.direction();
				Vector handCenter = hand.palmPosition();

				rightHand = hand.isRight();
				leftHand = hand.isLeft();

				if (rightHand) {

					setRechtPitch((float) Math.toDegrees(direction.pitch()));
					setRechtYaw((float) Math.toDegrees(direction.yaw()));
					setRechtRoll((float) Math.toDegrees(normal.roll()));
					setRechtSphereRadius(hand.sphereRadius());
					setRechtThrust(handCenter.getY());
					setRechtSide(handCenter.getX());
					setRechtForBack(handCenter.getZ());
				}
				if (leftHand) {

					setLinkPitch((float) Math.toDegrees(direction.pitch()));
					setLinkRoll((float) Math.toDegrees(normal.roll()));
					setLinkYaw((float) Math.toDegrees(direction.yaw()));
					setLinkSphereRadius(hand.sphereRadius());
					setLinkThrust(handCenter.getY());
					setLinkSide(handCenter.getX());
					setLinkForBack(handCenter.getZ());
				}

				if (!frame.hands().isEmpty() || !gestures.isEmpty()) {
				}
			}
		} else {
			rightHand = false;
			leftHand = false;
		}
		services.computeGestures(this);
	}

	@Override
	public float getPitchRightHand() {
		return rechtPitch;
	}

	@Override
	public float getRollRightHand() {
		return rechtRoll;
	}

	@Override
	public float getYawRightHand() {
		return rechtYaw;
	}

	@Override
	public float getThrustRightHand() {
		return rechtThrust;
	}

	@Override
	public float getSphereRadiusRightHand() {
		return rechtSphereRadius;
	}

	@Override
	public float getPitchLeftHand() {
		return linkPitch;
	}

	@Override
	public float getRollLeftHand() {
		return linkRoll;
	}

	@Override
	public float getYawLeftHand() {
		return linkYaw;
	}

	@Override
	public float getThrustLeftHand() {
		return linkThrust;
	}

	@Override
	public float getSpehreRadiusLeftHand() {
		return linkSphereRadius;
	}

	public void setAnzahlHaenden(int anzahlHaenden) {
		this.anzahlHaenden = anzahlHaenden;

	}

	public void setRechtPitch(float rechtPitch) {
		this.rechtPitch = rechtPitch;

	}

	public void setRechtYaw(float rechtYaw) {
		this.rechtYaw = rechtYaw;

	}

	public void setRechtRoll(float rechtRoll) {
		this.rechtRoll = rechtRoll;

	}

	public void setRechtSphereRadius(float rechtSphereRadius) {
		this.rechtSphereRadius = rechtSphereRadius;

	}

	public void setRechtThrust(float rechtThrust) {
		this.rechtThrust = rechtThrust;

	}

	public float getRechtSide() {
		return rechtSide;
	}

	public void setRechtSide(float rechtSide) {
		this.rechtSide = rechtSide;
	}

	public float getRechtForBack() {
		return rechtForBack;
	}

	public void setRechtForBack(float rechtForBack) {
		this.rechtForBack = rechtForBack;
	}

	public void setLinkPitch(float linkPitch) {
		this.linkPitch = linkPitch;

	}

	public void setLinkYaw(float linkYaw) {
		this.linkYaw = linkYaw;

	}

	public void setLinkRoll(float linkRoll) {
		this.linkRoll = linkRoll;

	}

	public void setLinkSphereRadius(float linkSphereRadius) {
		this.linkSphereRadius = linkSphereRadius;

	}

	public void setLinkThrust(float linkThrust) {
		this.linkThrust = linkThrust;

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

	public void setLinkForBack(float linkForBack) {
		this.linkForBack = linkForBack;
	}
}
