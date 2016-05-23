package com.quadromotion.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.quadromotion.input.KeyBoardCommands;
import com.quadromotion.model.Model;

public class ControlStatePanel extends JPanel implements Observer {

	JLabel controlState;
	JLabel connected;
	
	public ControlStatePanel(Model m) {
		m.addObserver(this);
		controlState = new JLabel(m.getControlState());
		connected = new JLabel(String.valueOf(m.isDroneConnected()));
		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createTitledBorder("Control state:"));
		this.setSize(80, 80);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;

		gbc.gridx = 0;
		gbc.gridy = 0;
		this.add(new JLabel("Status der Drohne: "), gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		this.add(controlState, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		this.add(new JLabel("Drohne verbunden: "), gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		this.add(connected, gbc);
	}

	@Override
	public void update(Observable o, Object arg) {
		Model m = (Model) o;
			controlState.setText(m.getControlState());
			connected.setText(String.valueOf(m.isDroneConnected()));
	}
}
