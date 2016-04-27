package com.quadromotion.testing;

import com.quadromotion.controller.ARDroneCommander;
import com.quadromotion.model.Model;
import com.quadromotion.util.Util;

public class ChangeModelForTestingCockpitView extends Thread {

	private String threadName;
	private Model model;
	private final float minLimit = -50f;
	private final float maxLimit = 50f;
	private Util util=null;

	public ChangeModelForTestingCockpitView(String threadName, Model model) {
		this.threadName = threadName;
		this.model = model;
		this.util = new Util();
	}

	@Override
	public void run() {
		model.setTakeOffCommand(true);
		while (!this.isInterrupted()) {
			int i;
			for(i=-100;i<=100; i+=10){
				model.setSpeedX(i);
				model.setSpeedY(i);
				model.setSpeedZ(i);
				model.setSpeedSpin(i);
//				System.out.println("speed:\t"+i);
//				System.out.println("");
			}
			for(i=100;i>=-100; i-=10){
				model.setSpeedX(i);
				model.setSpeedY(i);
				model.setSpeedZ(i);
				model.setSpeedSpin(i);
//				System.out.println("speed x:\t"+i);
//				System.out.println("speed y:\t"+i);
//				System.out.println("speed z:\t"+i);
//				System.out.println("speed spin:\t"+i);
//				System.out.println("");
			}
			
//			float nx = util.limit(util.randomWithRange(minLimit, maxLimit), minLimit, maxLimit);
//			float ny = util.limit(util.randomWithRange(minLimit, maxLimit), minLimit, maxLimit);
//			float nz = util.limit(util.randomWithRange(minLimit, maxLimit), minLimit, maxLimit);
//			float nspin = util.limit(util.randomWithRange(minLimit, maxLimit), minLimit, maxLimit);
			
//			System.out.println("speed x:\t"+i);
//			System.out.println("speed y:\t"+ny);
//			System.out.println("speed z:\t"+nz);
//			System.out.println("speed spin:\t"+nspin);
//			System.out.println("");
			// yield();
			try {
				Thread.sleep(100);
			}

			catch (InterruptedException ie) {
				System.out.println("Thread " + threadName + " interrupted...");
			}
		}
	}

}
