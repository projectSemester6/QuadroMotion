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

public class MainView extends JFrame {

	private MainViewController controller = null;

	public MainView(Model m) {

	}

	/**
	 * Constructor II
	 * 
	 * @param model
	 * @param drone
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
