package com.quadromotion.testing;

import com.quadromotion.controller.ARDroneCommander;
import com.quadromotion.model.Model;
import com.quadromotion.util.Util;

public class ChangeModel extends Thread {

	private String threadName;
	private Model model;
	private final float minLimit = -50f;
	private final float maxLimit = 50f;
	private Util util = null;
	private long sleep = 100;

	public ChangeModel(String threadName, Model model) {
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

			float nx = util.limit(util.randomWithRange(minLimit, maxLimit), minLimit, maxLimit);
			float ny = util.limit(util.randomWithRange(minLimit, maxLimit), minLimit, maxLimit);
			float nz = util.limit(util.randomWithRange(minLimit, maxLimit), minLimit, maxLimit);
			float nspin = util.limit(util.randomWithRange(minLimit, maxLimit), minLimit, maxLimit);
			model.setSpeedX(nx);
			model.setSpeedY(ny);
			model.setSpeedZ(nz);
			model.setSpeedSpin(nspin);
			model.setBatLevel(util.limit(util.randomWithRange(0, 100), 0, 100));
			model.setAltitude(util.limit(util.randomWithRange(0, 1500), 0, 1500));

			if (i >= 60)
				i = 0;
			else if (i >= 50)
				model.setState("landing");
			else if (i >= 40)
				model.setState("flying");
			else if (i >= 30)
				model.setState("hovering");
			else if (i >= 20)
				model.setState("takingOff");
			else if (i >= 10)
				model.setState("ready");
			else
				model.setState("init");
			i++;
			loop1 = (System.currentTimeMillis() - startTimeLoop);

			if (loop1 >= (sleep + (sleep * 0.1)))
				System.out.println("Dauer: " + loop1);
			// if (loop2 >= sleep + 10)
			// System.out.println("loop2: " + loop2);
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
