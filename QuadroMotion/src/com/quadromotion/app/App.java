package com.quadromotion.app;

//import com.quadromotion.boot.Boot;
import com.quadromotion.controller.Controller;
import com.quadromotion.controller.SendThread;
import com.quadromotion.model.Model;
import com.quadromotion.view.ConsolView;

/**
 * Diese Klasse enthaelt die boot() und die run() Methode und steuert den Programmablauf
 * @author Alexis
 *
 */
public class App {

	private Model model = null;
	//static ConsolView view = null;
	private Controller controller = null;
	private SendThread sendThread = null;
	
	//static Boot boot = null;

	
	public void boot() {
		// TODO: Gesteneingabe der View mitgeben
		//boot = new Boot();
		model = new Model();
//		view = new ConsolView(model);
//		controller =  view.getController();
//		view.printToConsole("Boot done...");
	}

	public void run(){
		controller.showView();
//		view.printToConsole("run...");
		
		try{
			System.in.read();
		} catch(Exception ignore){
			
		}
		finally{
//			view.printToConsole("exit...");
		}
	}
	
	public void cleanup(){
//		view.printToConsole("cleanup...");
		model = null;
		controller = null;
//		view = null;
	}
}
