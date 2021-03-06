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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.quadromotion.model.Model;

import de.yadrone.base.ARDrone;
import de.yadrone.base.IARDrone;

/**
 * This class represent the main view.
 * 
 * @author Gabriel Urech
 *
 */
public class MainView extends JFrame {

	/**
	 * The main view controller.
	 */
	private MainViewController controller = null;

	/**
	 * Allocates a new <code>ConfigPanel</code> object so that it has
	 * <code>model</code> as the model, has <code>drone</code> as the drone and
	 * has <code>controller</code> as the main view controller.
	 * 
	 * @param model the model.
	 * @param drone the drone.
	 * @param controller the main view controller.
	 */
	public MainView(Model model, IARDrone drone, MainViewController controller) {

		this.controller = controller;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("QuadroMotion");

		JButton connectionButton = new JButton("Drohne verbinden");
		controller.setConnectionButton(connectionButton);
		JPanel statePanel = new StatePanel(model);
		JPanel dataPanel = new DataPanel(model);
		JPanel configPanel = new ConfigPanel(model);
		JPanel controlStatePanel = new ControlStatePanel(model);
		JPanel panel = new JPanel(new GridBagLayout());
		this.getContentPane().add(panel);

		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 2;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		panel.add(statePanel, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 2;
		gbc.anchor = GridBagConstraints.NORTHEAST;
		panel.add(dataPanel, gbc);
		gbc.anchor = GridBagConstraints.WEST;

		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 2;
		gbc.anchor = GridBagConstraints.NORTHEAST;
		panel.add(configPanel, gbc);
		gbc.anchor = GridBagConstraints.WEST;

		gbc.gridx = 2;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 2;
		gbc.anchor = GridBagConstraints.NORTHEAST;
		panel.add(connectionButton, gbc);
		gbc.anchor = GridBagConstraints.WEST;

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		gbc.gridheight = 2;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		panel.add(controlStatePanel, gbc);
		gbc.anchor = GridBagConstraints.WEST;

		connectionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(e.getActionCommand());
				controller.connectionButtonChanged(connectionButton);
			}
		});

		addWindowListener(new WindowListener() {

			public void windowOpened(WindowEvent e) {
			}

			public void windowIconified(WindowEvent e) {
			}

			public void windowDeiconified(WindowEvent e) {
			}

			public void windowActivated(WindowEvent e) {
			}

			public void windowDeactivated(WindowEvent e) {
			}

			public void windowClosing(WindowEvent e) {
				if (drone != null)
					drone.stop();
				System.exit(0);
			}

			public void windowClosed(WindowEvent e) {
			}
		});

		this.pack();
		setSize(640, 360);
		this.setVisible(true);
	}
}
