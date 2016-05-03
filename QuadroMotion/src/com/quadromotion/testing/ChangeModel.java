package com.quadromotion.testing;

import com.quadromotion.model.Model;
import com.quadromotion.pilotingstates.PilotingStates;
import com.quadromotion.util.Util;

public class ChangeModel extends Thread {

	private String threadName;
	private Model model;
	private final float minLimit = -50f;
	private final float maxLimit = 50f;
	private Util util = null;
	private long sleep = 500;

	public ChangeModel(String threadName, Model model) {
		this.threadName = threadName;
		this.model = model;
		this.util = new Util();
	}

	int i = 0;
	long loop1 = 0;
	long loop2 = 0;

	int time = 10;

	@Override
	public void run() {
		while (true) {
			long startTimeLoop = System.currentTimeMillis();

			switch (i) {
			case 0:
				model.setPilotingState(PilotingStates.STATE_0_INIT);
				model.setBatLevel(util.limit(util.randomWithRange(0, 100), 0, 100));
				time--;
				if (time == 0) {
					time = 10;
					i = 1;
				}
				break;
			case 1:
				model.setPilotingState(PilotingStates.STATE_1_READY);

				for (int j = 2000; j >= 0; j--) {
					
					try {
						model.setTimeUntilTakeOff(j);
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				i = 2;

				break;
			case 2:
				model.setPilotingState(PilotingStates.STATE_2_TAKINGOFF);
				model.setPilotingState(PilotingStates.STATE_3_WAITINGTAKEOFF);
				for (int j = 0; j <= 100; j++) {
					model.setAltitude(j);
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				i = 3;
				break;
			case 3:
				model.setPilotingState(PilotingStates.STATE_4_HOVERING);
				i = 4;
				if (time == 0){
					i = 5;
					time = 10;
					break;
				}
				time--;
				
				break;
			case 4:
				model.setPilotingState(PilotingStates.STATE_5_FLYING);
				float nx = util.limit(util.randomWithRange(minLimit, maxLimit), minLimit, maxLimit);
				float ny = util.limit(util.randomWithRange(minLimit, maxLimit), minLimit, maxLimit);
				float nz = util.limit(util.randomWithRange(minLimit, maxLimit), minLimit, maxLimit);
				float nspin = util.limit(util.randomWithRange(minLimit, maxLimit), minLimit, maxLimit);

				model.setSpeedX(nx);
				model.setSpeedY(ny);
				model.setSpeedZ(nz);
				model.setSpeedSpin(nspin);

				if (time == 0)
					i = 5;
				time--;
				break;
			case 5:
				model.setPilotingState(PilotingStates.STATE_6_LANDING);
				model.setPilotingState(PilotingStates.STATE_7_WAITINGLANDING);
				for (int j = 100; j >= 0; j--) {
					model.setAltitude(j);
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				i = 0;
				time = 5;
				break;
			default:
				break;
			}

			loop1 = (System.currentTimeMillis() - startTimeLoop);

			if (loop1 >= (sleep + (sleep * 0.1)))
				System.out.println("Dauer: " + loop1);

			try {
				Thread.sleep(sleep);
			}

			catch (InterruptedException ie) {
				System.out.println("Thread " + threadName + " interrupted...");
			}
		}
	}

}
