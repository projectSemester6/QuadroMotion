package com.quadromotion.testing;

import com.leapmotion.leap.*;
import com.quadromotion.gestures.LeapMotion;
import com.quadromotion.model.Model;

public class TestServiceClass {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Model model = new Model();
		Controller leapController = new Controller();
		LeapMotion leap = new LeapMotion();
		leapController.addListener(leap);
		try{
			System.out.print("press any key to exit");
			System.in.read();
		}catch(Exception ignore){
			
		}finally{
			System.exit(0);
		}

	}

}
