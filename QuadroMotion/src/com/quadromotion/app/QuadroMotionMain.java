package com.quadromotion.app;
/**
 * This class is the main entry of the QuadroMotion app
 * @author Gabriel
 *
 */
public class QuadroMotionMain {

	static App app = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		app = new App();
		app.boot();
		app.run();
	}
}
