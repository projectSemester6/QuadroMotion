package com.quadromotion.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class GridBagDemo1 extends JFrame {

	public GridBagDemo1() {
		initGUI();
	}

	public void initGUI() {

		setTitle("QuadroMotion");

		JPanel panel = new JPanel(new GridBagLayout());
		this.getContentPane().add(panel);

		JPanel statePanel = createStatePanel();
		statePanel.setBorder(BorderFactory.createTitledBorder("Status"));

		JPanel detailsPanel = createDataPanel();
		detailsPanel.setBorder(BorderFactory.createTitledBorder("Drone data"));

		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 2;
		gbc.insets = new Insets(2,2,2,2);
		gbc.anchor = GridBagConstraints.NORTHWEST;
		panel.add(statePanel, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 2;
		gbc.anchor = GridBagConstraints.NORTHEAST;
		panel.add(detailsPanel, gbc);
		gbc.anchor = GridBagConstraints.WEST;
		this.pack();

		this.setVisible(true);
	}

	private JProgressBar createProgressBar(String title, int value) {
		JProgressBar progressBar = new JProgressBar();
		progressBar.setValue(value);
		progressBar.setStringPainted(true);
		// Border border = BorderFactory.createTitledBorder(title);
		// progressBar.setBorder(border);
		return progressBar;
	}

	private JPanel createStatePanel() {
		JPanel panel = new JPanel();

		JRadioButton initState = new JRadioButton("Init", true);
		JRadioButton readyState = new JRadioButton("Ready", false);
		JRadioButton takingOffState = new JRadioButton("Taking off", false);
		JRadioButton hoveringState = new JRadioButton("Hovering", false);
		JRadioButton flyingState = new JRadioButton("Flying", false);
		JRadioButton landingState = new JRadioButton("Landing", false);

		initState.setEnabled(false);
		readyState.setEnabled(false);
		takingOffState.setEnabled(false);
		hoveringState.setEnabled(false);
		flyingState.setEnabled(false);
		landingState.setEnabled(false);
		
		panel.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();

		int i = 0;

		gbc.gridx = 0;
		gbc.gridy = i;
		panel.add(initState, gbc);

		i++;
		gbc.gridx = 0;
		gbc.gridy = i;
		panel.add(readyState, gbc);

		i++;
		gbc.gridx = 0;
		gbc.gridy = i;
		panel.add(takingOffState, gbc);

		i++;
		gbc.gridx = 0;
		gbc.gridy = i;
		panel.add(hoveringState, gbc);

		i++;
		gbc.gridx = 0;
		gbc.gridy = i;
		panel.add(flyingState, gbc);

		i++;
		gbc.gridx = 0;
		gbc.gridy = i;
		panel.add(landingState, gbc);

		return panel;
	}

	private JPanel createDataPanel() {

		JPanel panel = new JPanel();

		JProgressBar speedX = createProgressBar("Speed X:", 23);
		JProgressBar speedY = createProgressBar("Speed Y:", 2);
		JProgressBar speedZ = createProgressBar("Speed Z:", 43);
		JProgressBar speedSpin = createProgressBar("Speed spin:", 73);
		JProgressBar batteryLevel = createProgressBar("Battery level:", 99);

		JLabel DroneDataLabel = new JLabel("Drone Data");
		JLabel emptySpaceLabel = new JLabel(" ");
		JLabel speedXLabel = new JLabel("Speed X:");
		JLabel speedYLabel = new JLabel("Speed Y:");
		JLabel speedZLabel = new JLabel("Speed Z:");
		JLabel speedSpinLabel = new JLabel("Speed Spin:");
		JLabel batteryLevelLabel = new JLabel("Battery level: ");

		panel.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();

		int i = 0;

		gbc.gridx = 0;
		gbc.gridy = i;
		panel.add(speedXLabel, gbc);

		gbc.gridx = 1;
		gbc.gridy = i;
		panel.add(speedX, gbc);

		i++;

		gbc.gridx = 0;
		gbc.gridy = i;
		panel.add(speedYLabel, gbc);

		gbc.gridx = 1;
		gbc.gridy = i;
		panel.add(speedY, gbc);

		i++;

		gbc.gridx = 0;
		gbc.gridy = i;
		panel.add(speedZLabel, gbc);

		gbc.gridx = 1;
		gbc.gridy = i;
		panel.add(speedZ, gbc);

		i++;

		gbc.gridx = 0;
		gbc.gridy = i;
		panel.add(speedSpinLabel, gbc);

		gbc.gridx = 1;
		gbc.gridy = i;
		panel.add(speedSpin, gbc);

		i++;
		gbc.gridx = 0;
		gbc.gridy = i;
		panel.add(emptySpaceLabel, gbc);

		i++;

		gbc.gridx = 0;
		gbc.gridy = i;
		panel.add(batteryLevelLabel, gbc);

		gbc.gridx = 1;
		gbc.gridy = i;
		panel.add(batteryLevel, gbc);

		return panel;
	}

	public static void main(String[] args) {
		GridBagDemo1 frame = new GridBagDemo1();

		frame.pack();
		frame.setVisible(true);
	}
}