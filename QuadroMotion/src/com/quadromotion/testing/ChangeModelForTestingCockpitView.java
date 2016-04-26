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
		while (!this.isInterrupted()) {
			float nx = util.limit(util.randomWithRange(minLimit, maxLimit), minLimit, maxLimit);
			float ny = util.limit(util.randomWithRange(minLimit, maxLimit), minLimit, maxLimit);
			float nz = util.limit(util.randomWithRange(minLimit, maxLimit), minLimit, maxLimit);
			float nspin = util.limit(util.randomWithRange(minLimit, maxLimit), minLimit, maxLimit);
			model.setSpeedX(nx);
			model.setSpeedY(ny);
			model.setSpeedZ(nz);
			model.setSpeedSpin(nspin);
			
			System.out.println("speed x:\t"+nx);
			System.out.println("speed y:\t"+ny);
			System.out.println("speed z:\t"+nz);
			System.out.println("speed spin:\t"+nspin);
			System.out.println("");
			// yield();
			try {
				Thread.sleep(1000);
			}

			catch (InterruptedException ie) {
				System.out.println("Thread " + threadName + " interrupted...");
			}
		}
	}

}
