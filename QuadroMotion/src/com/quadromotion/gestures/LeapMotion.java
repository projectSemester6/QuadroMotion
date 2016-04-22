package com.quadromotion.gestures;

import java.util.Observable;

import com.leapmotion.leap.*;
//import com.leapmotion.leap.Bone;
//import com.leapmotion.leap.CircleGesture;
import com.leapmotion.leap.Controller;
//import com.leapmotion.leap.Finger;
//import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Gesture;
import com.leapmotion.leap.GestureList;
import com.leapmotion.leap.Hand;
//import com.leapmotion.leap.KeyTapGesture;
import com.leapmotion.leap.Listener;
//import com.leapmotion.leap.ScreenTapGesture;
//import com.leapmotion.leap.SwipeGesture;
//import com.leapmotion.leap.Tool;
import com.leapmotion.leap.Vector;
//import com.leapmotion.leap.Gesture.State;


public class LeapMotion extends Observable implements IGestures {

	private int anzahlHaenden = 0;
	
	private double rechtPitch = 0;
	private double rechtYaw = 0;
	private double rechtRoll = 0;
	private double rechtSphereRadius = 0;
	private double rechtThrust = 0;
	
	private double linkPitch = 0;
	private double linkYaw = 0;
	private double linkRoll= 0;
	private double linkSphereRadius = 0;
	private double linkThrust = 0;

	
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

		anzahlHaenden = frame.hands().count();
		GestureList gestures = frame.gestures();

		if (anzahlHaenden > 1) {

			// Get hands
			for (Hand hand : frame.hands()) {
				String handType = hand.isLeft() ? "Left hand" : "Right hand";

				// Get the hand's normal vector and direction

				Vector normal = hand.palmNormal();
				Vector direction = hand.direction();

				if (handType == "Right hand") {

					rechtPitch = Math.toDegrees(direction.pitch());
					rechtYaw = Math.toDegrees(direction.yaw());
					rechtRoll = Math.toDegrees(normal.roll());

					rechtSphereRadius = hand.sphereRadius();

					
				}
				if (handType == "Left hand") {

					linkPitch = Math.toDegrees(direction.pitch());
					linkRoll = Math.toDegrees(normal.roll());
					linkYaw = Math.toDegrees(direction.yaw());

					linkSphereRadius = hand.sphereRadius();


				}

				if (!frame.hands().isEmpty() || !gestures.isEmpty()) {
					System.out.println();
				}
			}

		}


	}

	@Override
	public double getPitchRightHand() {
		// TODO Auto-generated method stub
		return rechtPitch;
	}

	@Override
	public double getRollRightHand() {
		// TODO Auto-generated method stub
		return rechtRoll;
	}

	@Override
	public double getYawRightHand() {
		// TODO Auto-generated method stub
		return rechtYaw;
	}

	@Override
	public double getThrustRightHand() {
		// TODO Auto-generated method stub
		return rechtThrust;
	}

	@Override
	public double getSphereRadiusRightHand() {
		// TODO Auto-generated method stub
		return rechtSphereRadius;
	}

	@Override
	public double getPitchLeftHand() {
		// TODO Auto-generated method stub
		return linkPitch;
	}

	@Override
	public double getRollLeftHand() {
		// TODO Auto-generated method stub
		return linkRoll;
	}

	@Override
	public double getYawLeftHand() {
		// TODO Auto-generated method stub
		return linkYaw;
	}

	@Override
	public double getThrustLeftHand() {
		// TODO Auto-generated method stub
		return linkThrust;
	}

	@Override
	public double getSpehreRadiusLeftHand() {
		// TODO Auto-generated method stub
		return linkSphereRadius;
	}



}

