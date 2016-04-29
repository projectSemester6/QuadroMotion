package com.quadromotion.testing;

import com.quadromotion.controller.SendThread;
import com.quadromotion.model.Model;

import de.yadrone.base.ARDrone;
import de.yadrone.base.IARDrone;
import de.yadrone.base.exception.ARDroneException;
import de.yadrone.base.exception.IExceptionListener;

/**
 * !!!Diese Klasse ist nur zum Testen des SendThreads!!!
 * 
 * @author Gabriel
 *
 */
public class TestTheThread {

	static String st_name = "Tester";
	private final static boolean MODE = false;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IARDrone drone = null;
		Model model = new Model();
		try {
		if (!MODE) {
			System.out.println("MODE = false");
				System.out.println("starte den " + st_name + "-Thread:");
				SendThread st1 = new SendThread(st_name, model);

				st1.start();
				try {
					Thread.sleep(5000);
				} catch (Exception ignore) {

				}
				System.out.println("next state: ready");
				model.setState("ready");
				try {
					Thread.sleep(1000);
				} catch (Exception ignore) {

				}
				System.out.println("next state: takingOff");

				model.setState("takingOff");
				try {
					Thread.sleep(1000);
				} catch (Exception ignore) {

				}
				System.out.println("next state: hovering");

				model.setState("hovering");
//				model.setSpeedX(10);
//				model.setSpeedY(20);
//				model.setSpeedZ(-70);
//				model.setSpeedSpin(10);
				try {
					Thread.sleep(1000);
				} catch (Exception ignore) {

				}
				System.out.println("next state: flying");

				model.setState("flying");
				try {
					Thread.sleep(1000);
				} catch (Exception ignore) {

				}
				model.setSpeedX(7);
				try {
					Thread.sleep(1000);
				} catch (Exception ignore) {

				}
				model.setSpeedY(3);
				try {
					Thread.sleep(1000);
				} catch (Exception ignore) {

				}
				model.setSpeedZ(5);
				try {
					Thread.sleep(1000);
				} catch (Exception ignore) {

				}
				model.setSpeedSpin(-10);
				try {
					Thread.sleep(1000);
				} catch (Exception ignore) {

				}
				System.out.println("next state: landing");

				model.setState("landing");
				try {
					Thread.sleep(1000);
				} catch (Exception ignore) {

				}
				System.out.println("next state: ready");

				model.setState("ready");
				try {
					Thread.sleep(5000);
				} catch (Exception ignore) {

				}
		} else if (MODE) {
			System.out.println("MODE = true");
				drone = new ARDrone();
				// drone.addExceptionListener(new IExceptionListener() {
				// public void exeptionOccurred(ARDroneException exc)
				// {
				// exc.printStackTrace();
				// }
				// });

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
					Thread.sleep(100);
				} catch (Exception ignore) {

				}
				model.setTakeOffCommand(false);
				try {
					Thread.sleep(5000);
				} catch (Exception ignore) {

				}
				model.setSpeedX(10);
				model.setSpeedY(20);
				model.setSpeedZ(-70);
				model.setSpeedSpin(10);
				try {
					Thread.sleep(200);
				} catch (Exception ignore) {

				}
				model.setSpeedX(0);
				model.setSpeedY(0);
				model.setSpeedZ(0);
				model.setSpeedSpin(0);
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
				// try {
				// Thread.sleep(2000);
				// } catch (Exception ignore) {
				//
				// }
				// st1.interrupt();
				// st1.stop();

			
		}
		
		
		}//try
		catch (Exception exc) {
			exc.printStackTrace();
		}// catch
		finally {
			if (drone != null)
				drone.stop();

			System.exit(0);
		}//finallly
		
	}//main
}//class
