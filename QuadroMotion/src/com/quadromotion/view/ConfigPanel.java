package com.quadromotion.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.quadromotion.config.GestureConfig;
import com.quadromotion.controller.InputController;
import com.quadromotion.gestures.KeyBoardCommands;
import com.quadromotion.model.Model;

public class ConfigPanel extends JPanel {

	/**
	 * Constructor
	 * 
	 * @param configName
	 *            the array of all config names
	 * @param m
	 *            the model
	 */
	public ConfigPanel(Model m) {
		JRadioButton[] config = { null, null, null, null };
		String[] configNames = { "2-Hand-Steuerung", "Rechte-Hand-Steuerung", "Linke-Hand-Steuerung",
				"Tastatur-Steuerung" };

		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createTitledBorder("Config:"));

		// create the radio buttons
		for (int i = 0; i < configNames.length; i++) {
			if (i == GestureConfig.CONFIG_1_TWO_HANDS) {
				config[i] = new JRadioButton(configNames[i], true);
			} else {
				config[i] = new JRadioButton(configNames[i], false);
			}
		}

		GridBagConstraints gbc = new GridBagConstraints();
		
		KeyBoardCommands kbc = new KeyBoardCommands(config);
		
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
								// create a new window for the key board input
								kbc.setInputController(new InputController(m));
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
