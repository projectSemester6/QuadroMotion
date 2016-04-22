package com.quadromotion.testing;

import com.quadromotion.controller.SendThread;
import com.quadromotion.model.Model;

import de.yadrone.base.ARDrone;
import de.yadrone.base.IARDrone;
import de.yadrone.base.exception.ARDroneException;
import de.yadrone.base.exception.IExceptionListener;

/**
 * !!!Diese Klasse ist nur zum Testen des SendThreads!!!
 * @author Gabriel
 *
 */
public class TestTheThread {

	static String st_name = "Tester";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IARDrone drone = null;
		Model model = new Model();

		try
		{
			drone = new ARDrone();
//			drone.addExceptionListener(new IExceptionListener() {
//				public void exeptionOccurred(ARDroneException exc)
//				{
//					exc.printStackTrace();
//				}
//			});
			
			drone.start();
			System.out.println("starte den " + st_name + "-Thread:");
			SendThread st1 = new SendThread(st_name, model, drone);

			st1.start();
			try {
				Thread.sleep(5000);
			} catch (Exception ignore) {

			}
			model.setTakeOffCommand(true);
			try {
				Thread.sleep(300);
			} catch (Exception ignore) {

			}
			model.setTakeOffCommand(false);
			try {
				Thread.sleep(5000);
			} catch (Exception ignore) {

			}
			model.setSpeedSpin(16);
			try {
				Thread.sleep(1000);
			} catch (Exception ignore) {

			}
			model.setSpeedSpin(1);
			try {
				Thread.sleep(1000);
			} catch (Exception ignore) {

			}
			model.setLandingCommand(true);
			try {
				Thread.sleep(300);
			} catch (Exception ignore) {

			}
			model.setLandingCommand(false);
			try {
				Thread.sleep(2000);
			} catch (Exception ignore) {

			}
			//st1.interrupt();
			//st1.stop();
			
		}catch (Exception exc)
		{
			exc.printStackTrace();
		}
		finally
		{
			if (drone != null)
				drone.stop();

			System.exit(0);
		}
	}

}
