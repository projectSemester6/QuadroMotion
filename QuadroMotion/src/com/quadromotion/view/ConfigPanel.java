package com.quadromotion.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.quadromotion.config.GestureConfig;
import com.quadromotion.model.Model;

public class ConfigPanel extends JPanel {

//	private JRadioButton[] config = { null, null, null };
//	private String[] configNames = { "2-Hand-Steuerung", "Rechte-Hand-Steuerung", "Linke-Hand-Steuerung" };

	/**
	 * Constructor
	 * 
	 * @param configName
	 *            the array of all config names
	 * @param m
	 *            the model
	 */
	public ConfigPanel(Model m) {
		JRadioButton[] config = { null, null, null };
		String[] configNames = { "2-Hand-Steuerung", "Rechte-Hand-Steuerung", "Linke-Hand-Steuerung" };
		
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
							m.setSelectedGestureConfig(i);
						}

						else
							config[i].setSelected(false);
					}
				}
			});
		}
	}

//	public JRadioButton[] getConfigArray() {
//		return config;
//	}
//
//	public void setConfigArray(JRadioButton[] config) {
//		this.config = config;
//	}
}
