package com.quadromotion.app;

import com.leapmotion.leap.Controller;
import com.quadromotion.controller.MainViewController;
import com.quadromotion.controller.SendThread;
import com.quadromotion.gestures.LeapMotion;
import com.quadromotion.model.Model;
import com.quadromotion.model.Services;
import com.quadromotion.testing.ChangeModelForTestingCockpitView;
import com.quadromotion.view.CockpitViewController;
import com.quadromotion.view.MainView;

import de.yadrone.base.ARDrone;
import de.yadrone.base.IARDrone;

/**
 * This class is the main entry of the QuadroMotion app
 * 
 * @author Gabriel
 *
 */
public class QuadroMotionMain {

	private App app = null;
	private static Model model = null;
	private static IARDrone drone = null;
	private static SendThread sender = null;

	private static Controller leapController = null;
	private static LeapMotion leap = null;
	private static Services service = null;

	private static MainViewController c = null;
	private static MainView view = null;

	private static ChangeModelForTestingCockpitView cmt;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		model = new Model();
		service = new Services(model);
		leap = new LeapMotion(service);
		leapController = new Controller(leap);
		c = new MainViewController(model);
		cmt = new ChangeModelForTestingCockpitView("bla", model);
		drone = new ARDrone();
		sender = new SendThread("sender", model, drone);
		view = c.getView();
		
		c.showView();
		cmt.start();
		sender.start();
		try{
			drone.start();
			System.in.read();
		}
		catch (Exception exc)
		
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
