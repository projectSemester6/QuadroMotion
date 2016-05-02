package com.quadromotion.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;

import com.quadromotion.model.Model;

import de.yadrone.base.IARDrone;

public class MainView extends JFrame implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8860617118897090898L;

	private Model model = null;
	private IARDrone drone = null;

	JProgressBar batteryLevelValue;
	JLabel speedXValue;
	JLabel speedYValue;
	JLabel speedZValue;
	JLabel speedSpinValue;
	JLabel altitudeLabel;
	JLabel altitudeValue;
	JLabel timeUntilTakeOffLabel;
	JLabel timeUntilTakeOffValue;

	JRadioButton initState;
	JRadioButton readyState;
	JRadioButton takingOffState;
	JRadioButton hoveringState;
	JRadioButton flyingState;
	JRadioButton landingState;

	/**
	 * Constructor I
	 * 
	 * @param model
	 * @param droneAttitude 
	 */
	public MainView(Model model) {

		this.model = model;
		this.model.addObserver(this);

addWindowListener(new WindowListener() {
			
			public void windowOpened(WindowEvent e) { }
			public void windowIconified(WindowEvent e) { }
			public void windowDeiconified(WindowEvent e) { }
			public void windowActivated(WindowEvent e) { }
			public void windowDeactivated(WindowEvent e) { }
			public void windowClosing(WindowEvent e) {
				drone.stop();
				System.exit(0);
			}
			public void windowClosed(WindowEvent e) { }
		});
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
//		gbc.insets = new Insets(2, 2, 2, 2);
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

		// this.setVisible(true);
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

		gbc.anchor = GridBagConstraints.WEST;
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

		speedXValue = new JLabel(model.toString(model.getSpeedX()) + " %");
		speedYValue = new JLabel(model.toString(model.getSpeedY()) + " %");
		speedZValue = new JLabel(model.toString(model.getSpeedZ()) + " %");
		speedSpinValue = new JLabel(model.toString(model.getSpeedSpin()) + " %");
		batteryLevelValue = createProgressBar("Battery level:", (int) model.getBatLevel());

		altitudeLabel = new JLabel("Höhe: ");
		altitudeValue = new JLabel("0 mm");

		timeUntilTakeOffLabel = new JLabel("Zeit bis Start:");
		timeUntilTakeOffValue = new JLabel(String.valueOf(model.getTimeUntilTakeOff()) + " ms");

		JLabel emptySpaceLabel = new JLabel(" ");
		JLabel speedXLabel = new JLabel("Speed X:");
		JLabel speedYLabel = new JLabel("Speed Y:");
		JLabel speedZLabel = new JLabel("Speed Z:");
		JLabel speedSpinLabel = new JLabel("Speed Spin:");
		JLabel batteryLevelLabel = new JLabel("Battery level: ");

		panel.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;
		int i = 0;

		gbc.gridx = 0;
		gbc.gridy = i;
		panel.add(speedXLabel, gbc);

		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = 1;
		gbc.gridy = i;
		panel.add(speedXValue, gbc);

		i++;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = i;
		panel.add(speedYLabel, gbc);

		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = 1;
		gbc.gridy = i;
		panel.add(speedYValue, gbc);

		i++;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = i;
		panel.add(speedZLabel, gbc);

		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = 1;
		gbc.gridy = i;
		panel.add(speedZValue, gbc);

		i++;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = i;
		panel.add(speedSpinLabel, gbc);

		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = 1;
		gbc.gridy = i;
		panel.add(speedSpinValue, gbc);

		i++;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = i;
		panel.add(emptySpaceLabel, gbc);

		i++;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = i;
		panel.add(batteryLevelLabel, gbc);

		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = 1;
		gbc.gridy = i;
		panel.add(batteryLevelValue, gbc);

		i++;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = i;
		panel.add(altitudeLabel, gbc);

		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = 1;
		gbc.gridy = i;
		panel.add(altitudeValue, gbc);

		i++;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = i;
		panel.add(timeUntilTakeOffLabel, gbc);

		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = 1;
		gbc.gridy = i;
		panel.add(timeUntilTakeOffValue, gbc);

		return panel;
	}

	private JProgressBar createProgressBar(String title, int value) {
		JProgressBar progressBar = new JProgressBar();
		progressBar.setValue(value);
		progressBar.setStringPainted(true);
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

	@Override
	public void update(Observable o, Object arg) {
		// TODO Elemente der View aktualisieren
		Model m = (Model) o;
		
		speedXValue.setText(m.toString((int) m.getSpeedX()) + " %");
		speedYValue.setText(m.toString((int) m.getSpeedY()) + " %");
		speedZValue.setText(m.toString((int) m.getSpeedZ()) + " %");
		speedSpinValue.setText(m.toString((int) m.getSpeedSpin()) + " %");
		batteryLevelValue.setValue((int) m.getBatLevel());
		altitudeValue.setText(m.getAltitudeString() + " mm");
		timeUntilTakeOffValue.setText(String.valueOf(m.getTimeUntilTakeOff())+ " ms");

		initState.setSelected(checkState(m.getState(), "init"));
		readyState.setSelected(checkState(m.getState(), "ready"));
		takingOffState.setSelected(checkState(m.getState(), "takingOff", "waitingTakeOff"));
		hoveringState.setSelected(checkState(m.getState(), "hovering"));
		flyingState.setSelected(checkState(m.getState(), "flying"));
		landingState.setSelected(checkState(m.getState(), "landing", "waitingLanding"));

	}

	private boolean checkState(String currentState, String state) {
		if (currentState.equals(state))
			return true;
		return false;
	}
	
	private boolean checkState(String currentState, String state1, String state2) {
		if (currentState.equals(state1)||currentState.equals(state2))
			return true;
		return false;
	}
	
	public void setDrone(IARDrone drone){
		this.drone = drone;
	}
}
