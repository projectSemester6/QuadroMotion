package com.quadromotion.app;

/**
 * This class is the main entry of the QuadroMotion app
 * 
 * @author Gabriel
 *
 */
public class QuadroMotion {

	private static App app = null;

	public static void main(String[] args) {
		try {
			app = new App();
			app.boot();
			app.run();
			System.in.read();
		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			app.cleanup();
			System.exit(0);
		}
	}
}
