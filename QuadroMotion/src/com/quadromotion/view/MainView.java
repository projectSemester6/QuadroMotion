package com.quadromotion.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;

import com.leapmotion.leap.Config;
import com.quadromotion.config.Config_2_Right_Hand;
import com.quadromotion.model.Model;
import com.quadromotion.pilotingstates.PilotingStates;

import de.yadrone.base.ARDrone;
import de.yadrone.base.IARDrone;

public class MainView extends JFrame implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8860617118897090898L;

	JProgressBar batteryLevelValue;
	JLabel speedXValue;
	JLabel speedYValue;
	JLabel speedZValue;
	JLabel speedSpinValue;
	JLabel altitudeLabel;
	JLabel altitudeValue;
	JLabel timeUntilTakeOffLabel;
	JLabel timeUntilTakeOffValue;

//	ConfigPanel configPanel;

	public MainView(Model m) {
		this(m, new ARDrone());
	}

	/**
	 * Constructor II
	 * 
	 * @param model
	 * @param drone
	 */
	public MainView(Model model, IARDrone drone) {

		model.addObserver(this);

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
				drone.stop();
				System.exit(0);
			}

			public void windowClosed(WindowEvent e) {
			}
		});
	
		initGUI(model);
	}

	public void initGUI(Model m) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setTitle("QuadroMotion Data");

		JPanel panel = new JPanel(new GridBagLayout());
		this.getContentPane().add(panel);

		JPanel statePanel = new StatePanel(m);

		JPanel detailsPanel = createDataPanel();
		detailsPanel.setBorder(BorderFactory.createTitledBorder("Drone data:"));

		
		JPanel configPanel = new ConfigPanel(m);
		

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
		panel.add(detailsPanel, gbc);
		gbc.anchor = GridBagConstraints.WEST;

		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 2;
		gbc.anchor = GridBagConstraints.NORTHEAST;
		panel.add(configPanel, gbc);
		gbc.anchor = GridBagConstraints.WEST;

		this.pack();
		setSize(640, 360);
		this.setVisible(true);
	}

	public JPanel createDataPanel() {

		JPanel panel = new JPanel();

		speedXValue = new JLabel(0 + " %");
		speedYValue = new JLabel(0 + " %");
		speedZValue = new JLabel(0 + " %");
		speedSpinValue = new JLabel(0 + " %");
		batteryLevelValue = createProgressBar("Battery level:", 0);

		altitudeLabel = new JLabel("Höhe: ");
		altitudeValue = new JLabel("0 mm");

		timeUntilTakeOffLabel = new JLabel("Zeit bis Start:");
		timeUntilTakeOffValue = new JLabel("0 ms");

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

	@Override
	public void update(Observable o, Object arg) {
		Model m = (Model) o;

		speedXValue.setText(String.valueOf(m.getSpeedX()) + " %");
		speedYValue.setText(String.valueOf(m.getSpeedY()) + " %");
		speedZValue.setText(String.valueOf(m.getSpeedZ()) + " %");
		speedSpinValue.setText(String.valueOf(m.getSpeedSpin()) + " %");
		batteryLevelValue.setValue((int) m.getBatLevel());
		altitudeValue.setText(m.getAltitudeString() + " mm");
		timeUntilTakeOffValue.setText(String.valueOf(m.getTimeUntilTakeOff()) + " ms");
	}
}
