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
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import com.quadromotion.model.Model;

/**
 * This class defines each element in the data panel of the main view.
 * @author Gabriel Urech
 *
 */
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
	private long timeStamp = 0;
	private long timeNow = 0;
	private int rate = 10;

	/**
	 * Allocates a new <code>DataPanel</code> object so that it has
	 * <code>model</code> as the model.
	 * 
	 * @param m
	 *            the model.
	 */
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

		this.setBorder(BorderFactory.createTitledBorder("Daten:"));

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
		timeNow = System.currentTimeMillis();
		Model m = (Model) o;

		if (timeNow - timeStamp >= rate || m.getSelectedConfig() == 3) {
			// System.out.println(timeNow - timeStamp);
			timeStamp = timeNow;

			speedXValue.setText(String.valueOf((int) m.getSpeedX()) + " %");
			speedYValue.setText(String.valueOf((int) m.getSpeedY()) + " %");
			speedZValue.setText(String.valueOf((int) m.getSpeedZ()) + " %");
			speedSpinValue.setText(String.valueOf((int) m.getSpeedSpin()) + " %");
			batteryLevelValue.setValue((int) m.getBatLevel());
			altitudeValue.setText(m.getAltitudeString() + " mm");
			timeUntilTakeOffValue.setText(String.valueOf(m.getTimeUntilTakeOff()) + " ms");
		}
	}
}
