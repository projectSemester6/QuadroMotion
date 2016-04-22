package com.quadromotion.gestures;

import java.util.Observable;

/**
 * This class models the gestures of every hand
 * @author Gabriel
 *
 */
public class Gestures extends Observable implements IGestures{
	
	private float pitchRightHand;
	private float rollRightHand;
	private float yawRightHand;
	private float thrustRightHand;
	
	private float pitchLeftHand;
	private float rollLeftHand;
	private float yawLeftHand;
	private float thrustLeftHand;
	
	public Gestures(){
		
	}
	
	public void setPitchRightHand(float angle){
		pitchRightHand = angle;
		if(countObservers()>0){
			setChanged();
			notifyObservers(angle);
		}
	}
	@Override
	public double getPitchRightHand() {
		// TODO Auto-generated method stub
		return pitchRightHand;
	}
	@Override
	public double getRollRightHand() {
		// TODO Auto-generated method stub
		return rollRightHand;
	}
	@Override
	public double getYawRightHand() {
		// TODO Auto-generated method stub
		return yawRightHand;
	}
	@Override
	public double getThrustRightHand() {
		// TODO Auto-generated method stub
		return thrustRightHand;
	}
	@Override
	public double getSphereRadiusRightHand() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public double getPitchLeftHand() {
		// TODO Auto-generated method stub
		return pitchLeftHand;
	}
	@Override
	public double getRollLeftHand() {
		// TODO Auto-generated method stub
		return rollLeftHand;
	}
	@Override
	public double getYawLeftHand() {
		// TODO Auto-generated method stub
		return yawLeftHand;
	}
	@Override
	public double getThrustLeftHand() {
		// TODO Auto-generated method stub
		return thrustLeftHand;
	}
	@Override
	public double getSpehreRadiusLeftHand() {
		// TODO Auto-generated method stub
		return 0;
	}

}
