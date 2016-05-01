package com.quadromotion.view;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import com.quadromotion.controller.MainViewController;
import com.quadromotion.model.Model;

public class MainView extends JFrame implements Observer {

	private int speed = 15;
	private final int ZERO = 0;
	private Model model;
	private MainViewController controller;
	private Object gestures;

	JProgressBar batteryLevel;
	JLabel speedX;
	JLabel speedY;
	JLabel speedZ;
	JLabel speedSpin;

	JRadioButton initState;
	JRadioButton readyState;
	JRadioButton takingOffState;
	JRadioButton hoveringState;
	JRadioButton flyingState;
	JRadioButton landingState;
	
	JLabel altitudeLabel;
	JLabel altitudeValue;

	/**
	 * Constructor I
	 * 
	 * @param model
	 */
	public MainView(Model model) {

		this.model = model;
		this.model.addObserver(this);
		
		initGUI();
	}

	public void initGUI() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		setTitle("QuadroMotion");

		JPanel panel = new JPanel(new GridBagLayout());
		this.getContentPane().add(panel);

		JPanel statePanel = createStatePanel();
		statePanel.setBorder(BorderFactory.createTitledBorder("Status:"));

		JPanel detailsPanel = createDataPanel();
		detailsPanel.setBorder(BorderFactory.createTitledBorder("Drone data:"));

		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 2;
		gbc.insets = new Insets(2, 2, 2, 2);
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

	public JPanel createStatePanel() {
		JPanel panel = new JPanel();

		initState = new JRadioButton("Init", true);
		readyState = new JRadioButton("Ready", false);
		takingOffState = new JRadioButton("Taking off", false);
		hoveringState = new JRadioButton("Hovering", false);
		flyingState = new JRadioButton("Flying", false);
		landingState = new JRadioButton("Landing", false);

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

	public JPanel createDataPanel() {

		JPanel panel = new JPanel();

		speedX = new JLabel(model.toString(model.getSpeedX())+ "%");
		speedY = new JLabel(model.toString(model.getSpeedY())+ "%");
		speedZ = new JLabel(model.toString(model.getSpeedZ())+ "%");
		speedSpin = new JLabel(model.toString(model.getSpeedSpin())+ "%");
		batteryLevel = createProgressBar("Battery level:", (int) model.getBatLevel());
		
		altitudeLabel = new JLabel("Höhe: ");
		altitudeValue = new JLabel("0 mm");

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

		i++;

		gbc.gridx = 0;
		gbc.gridy = i;
		panel.add(altitudeLabel, gbc);

		gbc.gridx = 1;
		gbc.gridy = i;
		panel.add(altitudeValue, gbc);
		
		return panel;
	}

	private JProgressBar createProgressBar(String title, int value) {
		JProgressBar progressBar = new JProgressBar();
		progressBar.setValue(value);
		progressBar.setStringPainted(true);
		// Border border = BorderFactory.createTitledBorder(title);
		// progressBar.setBorder(border);
		return progressBar;
	}

	/**
	 * Prints a string to the console
	 * 
	 * @param inputString
	 */
	public void printToConsole(String inputString) {
		System.out.println(inputString);
	}

	public void setController(MainViewController controller) {
		this.controller = controller;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Elemente der View aktualisieren
		Model m = (Model) o;
		altitudeValue.setText(m.toString(m.getAltitude())+" mm");
		batteryLevel.setValue((int) m.getBatLevel());
		speedX.setText(m.toString(m.getSpeedX())+ " %");
		speedY.setText(m.toString(m.getSpeedY())+ " %");
		speedZ.setText(m.toString(m.getSpeedZ())+ " %");
		speedSpin.setText(m.toString(m.getSpeedSpin())+ " %");
		
		initState.setSelected(checkState(m.getState(), "init"));
		readyState.setSelected(checkState(m.getState(), "ready"));
		takingOffState.setSelected(checkState(m.getState(), "takingOff"));
		hoveringState.setSelected(checkState(m.getState(), "hovering"));
		flyingState.setSelected(checkState(m.getState(), "flying"));
		landingState.setSelected(checkState(m.getState(), "landing"));

	}
	
	private boolean checkState(String currentState, String state){
		if(currentState.equals(state)) return true;
		return false; 
	}
}
