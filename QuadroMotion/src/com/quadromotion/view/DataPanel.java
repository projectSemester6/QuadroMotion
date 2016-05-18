package com.quadromotion.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import com.quadromotion.model.Model;

public class DataPanel extends JPanel implements Observer {

	JProgressBar batteryLevelValue;
	JLabel speedXValue;
	JLabel speedYValue;
	JLabel speedZValue;
	JLabel speedSpinValue;
	JLabel altitudeLabel;
	JLabel altitudeValue;
	JLabel timeUntilTakeOffLabel;
	JLabel timeUntilTakeOffValue;

	public DataPanel(Model m) {

		m.addObserver(this);

		speedXValue = new JLabel(0 + " %");
		speedYValue = new JLabel(0 + " %");
		speedZValue = new JLabel(0 + " %");
		speedSpinValue = new JLabel(0 + " %");
		batteryLevelValue = createProgressBar("Battery level:", 0);

		altitudeLabel = new JLabel("Höhe: ");
		altitudeValue = new JLabel("0 mm");

		timeUntilTakeOffLabel = new JLabel("Zeit bis Start:");
		timeUntilTakeOffValue = new JLabel(m.getTimeUntilTakeOff() + " ms");

		JLabel emptySpaceLabel = new JLabel(" ");
		JLabel speedXLabel = new JLabel("Speed X:");
		JLabel speedYLabel = new JLabel("Speed Y:");
		JLabel speedZLabel = new JLabel("Speed Z:");
		JLabel speedSpinLabel = new JLabel("Speed spin:");
		JLabel batteryLevelLabel = new JLabel("Battery level:");

		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createTitledBorder("Drone data:"));

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;
		int i = 0;

		gbc.gridx = 0;
		gbc.gridy = i;
		this.add(speedXLabel, gbc);

		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = 1;
		gbc.gridy = i;
		this.add(speedXValue, gbc);

		i++;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = i;
		this.add(speedYLabel, gbc);

		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = 1;
		gbc.gridy = i;
		this.add(speedYValue, gbc);

		i++;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = i;
		this.add(speedZLabel, gbc);

		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = 1;
		gbc.gridy = i;
		this.add(speedZValue, gbc);

		i++;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = i;
		this.add(speedSpinLabel, gbc);

		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = 1;
		gbc.gridy = i;
		this.add(speedSpinValue, gbc);

		i++;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = i;
		this.add(emptySpaceLabel, gbc);

		i++;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = i;
		this.add(batteryLevelLabel, gbc);

		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = 1;
		gbc.gridy = i;
		this.add(batteryLevelValue, gbc);

		i++;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = i;
		this.add(altitudeLabel, gbc);

		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = 1;
		gbc.gridy = i;
		this.add(altitudeValue, gbc);

		i++;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = i;
		this.add(timeUntilTakeOffLabel, gbc);

		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = 1;
		gbc.gridy = i;
		this.add(timeUntilTakeOffValue, gbc);
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

		speedXValue.setText(String.valueOf((int) m.getSpeedX()) + " %");
		speedYValue.setText(String.valueOf((int) m.getSpeedY()) + " %");
		speedZValue.setText(String.valueOf((int) m.getSpeedZ()) + " %");
		speedSpinValue.setText(String.valueOf((int) m.getSpeedSpin()) + " %");
		batteryLevelValue.setValue((int) m.getBatLevel());
		altitudeValue.setText(m.getAltitudeString() + " mm");
		timeUntilTakeOffValue.setText(String.valueOf(m.getTimeUntilTakeOff()) + " ms");
	}

}
