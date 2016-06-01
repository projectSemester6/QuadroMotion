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
package com.quadromotion.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.quadromotion.model.Model;

/**
 * This class defines each element in the control panel of the main view.
 * @author Gabriel Urech
 *
 */
public class ControlStatePanel extends JPanel implements Observer {

	JLabel controlState;
	JLabel connected;

	/**
	 * Instantiates a new control state panel.
	 *
	 * @param m the m
	 */
	public ControlStatePanel(Model m) {
		m.addObserver(this);
		controlState = new JLabel(m.getControlState());
		connected = new JLabel("Nein");
		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createTitledBorder("Kontrollstatus:"));
		this.setSize(80, 80);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;

		gbc.gridx = 0;
		gbc.gridy = 1;
		this.add(new JLabel("Status der Drohne: "), gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		this.add(controlState, gbc);
		gbc.gridx = 0;
		gbc.gridy = 0;
		this.add(new JLabel("Drohne verbunden: "), gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		this.add(connected, gbc);
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		Model m = (Model) o;
		controlState.setText(m.getControlState());
		if (m.isDroneConnected())
			connected.setText("Ja");
		else connected.setText("Nein");
		if (!m.isDroneConnected())
			controlState.setText("-");
	}
}
