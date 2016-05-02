package com.quadromotion.testing;

import com.quadromotion.model.Model;
import com.quadromotion.view.MainView;

public class MainViewTest {

	public MainViewTest() {

		try {
			Model m = new Model();
			new MainView(m);
			ChangeModel cm = new ChangeModel("cm", m);
			cm.start();

		} catch (Exception exc)

		{
			exc.printStackTrace();
			System.exit(-1);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MainViewTest();
	}

}
