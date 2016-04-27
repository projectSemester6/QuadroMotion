package com.quadromotion.app;

//import com.quadromotion.boot.Boot;
import com.quadromotion.controller.ConsolViewController;
import com.quadromotion.controller.SendThread;
import com.quadromotion.model.Model;
import com.quadromotion.view.ConsolView;

import de.yadrone.base.ARDrone;
import de.yadrone.base.IARDrone;

/**
 * Diese Klasse enthaelt die boot() und die run() Methode und steuert den
 * Programmablauf
 * 
 * @author Alexis
 *
 */
public class App {

	private Model model = null;
	// static ConsolView view = null;
	private ConsolViewController controller = null;
	private SendThread sender = null;
	private IARDrone drone = null;

	// static Boot boot = null;

	public App(){
		this(new Model());
	}
	
	public App(Model model){
		this.model = model;
	}
	
	public void boot() {
		drone = new ARDrone();
//		model = new Model();
		sender = new SendThread("Sender", model);
		// view = new ConsolView(model);
		// controller = view.getController();
		// view.printToConsole("Boot done...");
	}

	public void run() {
		// controller.showView();
		// view.printToConsole("run...");
		sender.start();
		try {
			System.in.read();
		} catch (Exception ignore) {

		} finally {
			// view.printToConsole("exit...");
		}
	}

	public void cleanup() {
		// view.printToConsole("cleanup...");
		model = null;
		controller = null;
		// view = null;
	}
}
