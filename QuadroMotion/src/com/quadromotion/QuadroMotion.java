package com.quadromotion;

import com.quadromotion.app.App;

/**
 * This class is the main entry of the QuadroMotion app
 * 
 * @author Gabriel
 *
 */
public class QuadroMotion {

	private static App app = null;

	public QuadroMotion() {
		initialize();
	}

	private void initialize() {
		try {
			app = new App();
			app.boot();
			app.run();

		} catch (Exception exc) {
			exc.printStackTrace();

			app.cleanup();
			System.exit(-1);
		}

	}

	public static void main(String[] args) {
		new QuadroMotion();
	}
}
