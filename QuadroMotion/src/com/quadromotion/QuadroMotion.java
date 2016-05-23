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
