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

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.quadromotion.config.GestureConfig;
import com.quadromotion.input.InputController;
import com.quadromotion.input.KeyBoardCommands;
import com.quadromotion.model.Model;

/**
 * This class defines each element in the config panel of the main view.
 * @author Gabriel Urech
 *
 */
public class ConfigPanel extends JPanel {

	/**
	 * Allocates a new <code>ConfigPanel</code> object so that it has
	 * <code>m</code> as the model.
	 *
	 * @param m            the model.
	 */
	public ConfigPanel(Model m) {
		JRadioButton[] config = { null, null, null, null };
		String[] configNames = { "2-Hand-Steuerung", "Rechte-Hand-Steuerung", "2-Hand-Steuerung-Yaw",
				"Tastatur-Steuerung" };

		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createTitledBorder("Konfigurationswahl:"));

		// create the radio buttons
		for (int i = 0; i < configNames.length; i++) {
			if (i == GestureConfig.CONFIG_1_TWO_HANDS) {
				config[i] = new JRadioButton(configNames[i], true);
			} else {
				config[i] = new JRadioButton(configNames[i], false);
			}
		}

		GridBagConstraints gbc = new GridBagConstraints();

		// create a new window for the key board input
		KeyBoardCommands kbc = new KeyBoardCommands(config);
		kbc.setInputController(new InputController(m));

		gbc.anchor = GridBagConstraints.WEST;
		for (int i = 0; i < config.length; i++) {
			gbc.gridx = 0;
			gbc.gridy = i;
			this.add(config[i], gbc);

			config[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					for (int i = 0; i < config.length; i++) {
						if (e.getActionCommand() == configNames[i]) {
							config[i].setSelected(true);
							m.setSelectedConfig(i);
							if (m.getSelectedConfig() == 3) {
								kbc.anzeigen(true);
							}
						}

						else {
							config[i].setSelected(false);
							kbc.anzeigen(false);
						}

					}
				}
			});
		}
	}
}
