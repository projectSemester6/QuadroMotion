/* Copyright 2016 Gabriel Urech, Alexis Stephan, Simon Henzmann
 * 
 * This file is part of QuadroMotion.
 * 
 * QuadroMotion is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * QuadroMotion is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with DokChess.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.quadromotion;

import com.quadromotion.app.App;

/**
 * This class is the main entry of the QuadroMotion application.
 * 
 * @author Gabriel Urech
 *
 */
public class QuadroMotion {

	/**
	 * The app.
	 */
	private static App app = null;

	/**
	 * Constructor.
	 */
	public QuadroMotion() {
		initialize();
	}

	/**
	 * The initialize method. Creates a new object of the <code>App</code> class
	 * and calls its <code>boot()</code> method.
	 */
	private void initialize() {
		try {
			app = new App();
			app.boot();
		} catch (Exception exc) {
			exc.printStackTrace();
			app.cleanupDrone();
			System.exit(-1);
		}
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		new QuadroMotion();
	}
}
