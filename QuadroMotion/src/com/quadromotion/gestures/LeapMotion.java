package com.quadromotion.gestures;

import com.leapmotion.leap.*;
import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Gesture;
import com.leapmotion.leap.GestureList;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.Listener;
import com.leapmotion.leap.Vector;
import com.quadromotion.model.Services;

public class LeapMotion extends Listener implements IGestures {

	private int anzahlHaenden = 0;

	private float rechtPitch = 0;
	private float rechtYaw = 0;
	private float rechtRoll = 0;
	private float rechtSphereRadius = 0;
	private float rechtThrust = 0;

	private float linkPitch = 0;
	private float linkYaw = 0;
	private float linkRoll = 0;
	private float linkSphereRadius = 0;
	private float linkThrust = 0;

	private Services services = null;
	
	public LeapMotion(Services s) {
		super();
		this.services = s;
	}

	public void onInit(Controller controller) {
		System.out.println("Leap initialized");
	}

	public void onConnect(Controller controller) {
		System.out.println("Connected");
		controller.enableGesture(Gesture.Type.TYPE_SWIPE);
		controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
		controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
		controller.enableGesture(Gesture.Type.TYPE_KEY_TAP);
	}

	public void onDisconnect(Controller controller) {
		// Note: not dispatched when running in a debugger.
		System.out.println("Disconnected");
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

		if (anzahlHaenden > 1) {

			// Get hands
			for (Hand hand : frame.hands()) {
				String handType = hand.isLeft() ? "Left hand" : "Right hand";

				// Get the hand's normal vector and direction

				Vector normal = hand.palmNormal();
				Vector direction = hand.direction();

				if (handType == "Right hand") {

					setRechtPitch((float) Math.toDegrees(direction.pitch()));
					setRechtYaw((float) Math.toDegrees(direction.yaw()));
					setRechtRoll((float) Math.toDegrees(normal.roll()));
					setRechtSphereRadius(hand.sphereRadius());
				}
				if (handType == "Left hand") {

					setLinkPitch((float) Math.toDegrees(direction.pitch()));
					setLinkRoll((float) Math.toDegrees(normal.roll()));
					setLinkYaw((float) Math.toDegrees(direction.yaw()));
					setLinkSphereRadius(hand.sphereRadius());
				}

				if (!frame.hands().isEmpty() || !gestures.isEmpty()) {
				}
			}
		}
		services.ServicesGesturesConfig_1(this);
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

}
