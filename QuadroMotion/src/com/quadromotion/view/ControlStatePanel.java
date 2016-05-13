package com.quadromotion.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.quadromotion.gestures.KeyBoardCommands;
import com.quadromotion.model.Model;

public class ControlStatePanel extends JPanel implements Observer {

	JLabel controlState;
	
	public ControlStatePanel(Model m) {
		m.addObserver(this);
		controlState = new JLabel("nicht verbunden");
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
	}

	@Override
	public void update(Observable o, Object arg) {
		Model m = (Model) o;
			controlState.setText(m.getControlState());
	}
}
