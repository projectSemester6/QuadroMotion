package com.quadromotion.testing;

import java.util.Observable;
import java.util.Observer;

import com.quadromotion.model.Model;

public class ModelTest implements Observer {

	public ModelTest(Model m){
		m.addObserver(this);
	}
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		Model m = (Model)o;
		System.out.println("speedX: "+ m.getSpeedX());
		System.out.println("speedY: "+ m.getSpeedY());
		System.out.println("speedZ: "+ m.getSpeedZ());
		System.out.println("speedSpin: "+ m.getSpeedSpin());
	}

}
