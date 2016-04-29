package com.quadromotion.testing;

import com.leapmotion.leap.*;
import com.quadromotion.gestures.LeapMotion;
import com.quadromotion.model.Model;
import com.quadromotion.model.Services;

public class TestServiceClass {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Model model = new Model();
		Controller leapController = new Controller();
		LeapMotion leap = new LeapMotion(new Services(model));
		leapController.addListener(leap);
		ModelTest mt = new ModelTest(model);
		
		try{
			System.out.println("press enter to exit");
			System.in.read();
		}catch(Exception ignore){
			
		}finally{
			System.exit(0);
		}

	}

}
