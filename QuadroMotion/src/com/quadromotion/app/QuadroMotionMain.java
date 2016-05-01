package com.quadromotion.app;

import com.leapmotion.leap.Controller;
import com.quadromotion.controller.MainViewController;
import com.quadromotion.controller.SendThread;
import com.quadromotion.gestures.LeapMotion;
import com.quadromotion.model.Model;
import com.quadromotion.model.Services;
import com.quadromotion.testing.ChangeModel;
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

	private static App app = null;
	private static Model model = null;
	private static IARDrone drone = null;
	private static SendThread sender = null;

	private static Controller leapController = null;
	private static LeapMotion leap = null;
	private static Services service = null;

	private static MainViewController c = null;
	private static MainView view = null;

	private static ChangeModel cmt;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		app = new App();
		model =app.getModel();
//		service = app.getService();
//		leap = app.getLeapMotion();
//		leapController = new Controller(leap);
		
		cmt = new ChangeModel("bla", model);
		drone = app.getDrone();
		sender = app.getSender();
		
		try{
			app.boot();
			app.run();
			System.in.read();
		}
		catch (Exception exc)
		
		{
			exc.printStackTrace();
		}
		finally
		{
			app.cleanup();
			drone.stop();
			System.exit(0);
		}
	}
}
