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
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.quadromotion.model.Model;
import com.quadromotion.pilotingstates.PilotingStates;

public class StatePanel extends JPanel implements Observer {

	private JRadioButton[] states = { null, null, null, null, null, null, null };
	private String[] stateNames = { "Off", "Init", "Bereit", "Abheben", "Schweben", "Fliegen", "Landen" };
	private long timeNow;
	private long timeStamp = 0;
	private int rate = 0;

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
		timeNow = System.currentTimeMillis();
		Model m = (Model) o;
		
		if (timeNow - timeStamp >= rate || m.getSelectedConfig() == 3) {
//			System.out.println(timeNow - timeStamp);
			timeStamp = timeNow;
			
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
