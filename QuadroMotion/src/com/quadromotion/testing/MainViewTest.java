package com.quadromotion.testing;

import com.quadromotion.controller.MainViewController;
import com.quadromotion.controller.SendThread;
import com.quadromotion.model.Model;
import com.quadromotion.view.MainView;

public class MainViewTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Model m = new Model();
		MainViewController c = new MainViewController(m);
		MainView view = c.getView();
		ChangeModel cm = new ChangeModel("cm",m);
		SendThread sender = new SendThread(m);
		sender.start();
		c.showView();
		cm.start();
		try{
			System.in.read();
			
		}catch (Exception exc)
		
		{
			exc.printStackTrace();
		}
		finally
		{
			
			System.exit(0);
		}
	}

}
