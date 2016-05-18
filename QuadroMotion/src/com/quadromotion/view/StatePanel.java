package com.quadromotion.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.quadromotion.model.Model;
import com.quadromotion.pilotingstates.PilotingStates;

public class StatePanel extends JPanel implements Observer {

	private JRadioButton[] states = { null, null, null, null, null, null, null };
	private String[] stateNames = { "Off", "Init", "Bereit", "Abheben", "Schweben", "Fliegen", "Landen" };

	public StatePanel(Model m) {
		m.addObserver(this);
		for (int i = 0; i < stateNames.length; i++) {
			if (i == PilotingStates.STATE_0_OFF)
				states[i] = new JRadioButton(stateNames[i], true);
			else
				states[i] = new JRadioButton(stateNames[i], false);
			states[i].setEnabled(false);
		}

		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createTitledBorder("Status:"));

		GridBagConstraints gbc = new GridBagConstraints();

		gbc.anchor = GridBagConstraints.WEST;

		for (int i = 0; i < states.length; i++) {
			gbc.gridx = 0;
			gbc.gridy = i;
			this.add(states[i], gbc);
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		Model m = (Model) o;
		
		states[0].setSelected(checkState(m.getPilotingState(), PilotingStates.STATE_0_OFF));
		states[1].setSelected(checkState(m.getPilotingState(), PilotingStates.STATE_1_INIT));
		states[2].setSelected(checkState(m.getPilotingState(), PilotingStates.STATE_2_READY));
		states[3].setSelected(checkState(m.getPilotingState(), PilotingStates.STATE_3_TAKINGOFF,
				PilotingStates.STATE_4_WAITINGTAKEOFF));
		states[4].setSelected(checkState(m.getPilotingState(), PilotingStates.STATE_5_HOVERING));
		states[5].setSelected(checkState(m.getPilotingState(), PilotingStates.STATE_6_FLYING));
		states[6].setSelected(checkState(m.getPilotingState(), PilotingStates.STATE_7_LANDING,
				PilotingStates.STATE_8_WAITINGLANDING));
	}

	private boolean checkState(int currentState, int state) {
		if (currentState == state)
			return true;
		return false;
	}

	private boolean checkState(int currentState, int state1, int state2) {
		if (currentState == state1 || currentState == state2)
			return true;
		return false;
	}
}
