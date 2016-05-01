package com.quadromotion.testing;

import com.quadromotion.controller.ARDroneCommander;
import com.quadromotion.model.Model;
import com.quadromotion.util.Util;

public class ChangeModelForTestingCockpitView extends Thread {

	private String threadName;
	private Model model;
	private final float minLimit = -50f;
	private final float maxLimit = 50f;
	private Util util = null;
	private long sleep = 100;

	public ChangeModelForTestingCockpitView(String threadName, Model model) {
		this.threadName = threadName;
		this.model = model;
		this.util = new Util();
	}

	int i;
	long loop1 = 0;
	long loop2 = 0;

	@Override
	public void run() {
		model.setTakeOffCommand(true);
		while (true) {
			long startTimeLoop = System.currentTimeMillis();

//			for (i = -100; i <= 100; i += 10) {
//				long startTimeLoop = System.currentTimeMillis();
//				model.setSpeedX(i);
//				model.setSpeedY(i);
//				model.setSpeedZ(i);
//				model.setSpeedSpin(i);
//				// System.out.println("speed up:\t" + i);
//				// System.out.println("");
//				try {
//					Thread.sleep(sleep);
//				}
//
//				catch (InterruptedException ie) {
//					System.out.println("Thread " + threadName + " interrupted...");
//				}
//				loop1 = (System.currentTimeMillis() - startTimeLoop);
//			}

//			for (i = 100; i >= -100; i -= 10) {
//				long startTimeLoop = System.currentTimeMillis();
//				model.setSpeedX(i);
//				model.setSpeedY(i);
//				model.setSpeedZ(i);
//				model.setSpeedSpin(i);
//				// System.out.println("speed down:\t" + i);
//				// System.out.println("speed y:\t"+i);
//				// System.out.println("speed z:\t"+i);
//				// System.out.println("speed spin:\t"+i);
//				// System.out.println("");
//				try {
//					Thread.sleep(sleep);
//				}
//
//				catch (InterruptedException ie) {
//					System.out.println("Thread " + threadName + " interrupted...");
//				}
//				loop2 = (System.currentTimeMillis() - startTimeLoop);
//			}

			float nx = util.limit(util.randomWithRange(minLimit, maxLimit), minLimit, maxLimit);
			float ny = util.limit(util.randomWithRange(minLimit, maxLimit), minLimit, maxLimit);
			float nz = util.limit(util.randomWithRange(minLimit, maxLimit), minLimit, maxLimit);
			float nspin = util.limit(util.randomWithRange(minLimit, maxLimit), minLimit, maxLimit);
			model.setSpeedX(nx);
			model.setSpeedY(ny);
			model.setSpeedZ(nz);
			model.setSpeedSpin(nspin);
			model.setBatLevel(nx);
			model.setAltitude(ny);
			loop1 = (System.currentTimeMillis() - startTimeLoop);

			if (loop1 >= (sleep + (sleep*0.1)))
				System.out.println("Dauer: " + loop1);
//			if (loop2 >= sleep + 10)
//				System.out.println("loop2: " + loop2);
			// System.out.println("speed x:\t"+i);
			// System.out.println("speed y:\t"+ny);
			// System.out.println("speed z:\t"+nz);
			// System.out.println("speed spin:\t"+nspin);
			// System.out.println("");
			// yield();
			try {
				Thread.sleep(sleep);
			}

			catch (InterruptedException ie) {
				System.out.println("Thread " + threadName + " interrupted...");
			}
		}
	}

}
