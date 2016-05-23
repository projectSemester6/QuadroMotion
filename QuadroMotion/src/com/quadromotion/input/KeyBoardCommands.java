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
package com.quadromotion.input;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import com.quadromotion.pilotingstates.PilotingStates;

public class KeyBoardCommands extends JFrame implements KeyListener {

	private int speed = 15;
	private boolean exit = false;
	private boolean isFlying = false;

	private IInputController controller = null;

	public KeyBoardCommands(JRadioButton[] config) {
		this.setLayout(new BorderLayout());
		JLabel label = new JLabel("Tastatursteuerung");
		this.addKeyListener(this);
		this.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				if (controller.getPilotingState() == PilotingStates.STATE_0_OFF)
					controller.setPilotingState(PilotingStates.STATE_2_READY);
				controller.setInputDeviceState(true);
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (controller.getPilotingState() == PilotingStates.STATE_2_READY)
					controller.setPilotingState(PilotingStates.STATE_0_OFF);
				controller.setInputDeviceState(false);
			}
		});

		addWindowListener(new WindowListener() {

			public void windowOpened(WindowEvent e) {
				if (controller.getPilotingState() == PilotingStates.STATE_0_OFF)
					controller.setPilotingState(PilotingStates.STATE_2_READY);
				controller.setInputDeviceState(true);
			}

			public void windowIconified(WindowEvent e) {
			}

			public void windowDeiconified(WindowEvent e) {
			}

			public void windowActivated(WindowEvent e) {
				if (controller.getPilotingState() == PilotingStates.STATE_0_OFF)
					controller.setPilotingState(PilotingStates.STATE_2_READY);
				controller.setInputDeviceState(true);
			}

			public void windowDeactivated(WindowEvent e) {
			}

			public void windowClosing(WindowEvent e) {

			}

			public void windowClosed(WindowEvent e) {
				config[0].setSelected(true);
				config[3].setSelected(false);
				controller.setSelectedConfig(0);
				if (controller.getPilotingState() == PilotingStates.STATE_2_READY)
					controller.setPilotingState(PilotingStates.STATE_0_OFF);
				controller.setInputDeviceState(false);
			}
		});

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.add(label, BorderLayout.CENTER);
		this.pack();
		this.setLocation(700, 100);
		this.setSize(300, 150);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		switch (keyCode) {
		case KeyEvent.VK_ENTER:
			if (controller.getPilotingState() == PilotingStates.STATE_2_READY) {
				controller.setPilotingState(PilotingStates.STATE_3_TAKINGOFF);
				isFlying = true;
				System.out.println("start");
			}
			break;
		case KeyEvent.VK_SPACE:
			if (controller.getPilotingState() == PilotingStates.STATE_5_HOVERING) {
				controller.setPilotingState(PilotingStates.STATE_7_LANDING);
				System.out.println("land");
			}
			break;
		case KeyEvent.VK_LEFT:
			if (controller.getPilotingState() == PilotingStates.STATE_5_HOVERING) {
				controller.setPilotingState(PilotingStates.STATE_6_FLYING);
				controller.setSpeed(0, -speed, 0, 0);
				System.out.println("left " + -speed);
			}
			break;
		case KeyEvent.VK_RIGHT:
			if (controller.getPilotingState() == PilotingStates.STATE_5_HOVERING) {
				controller.setPilotingState(PilotingStates.STATE_6_FLYING);
				controller.setSpeed(0, speed, 0, 0);
				System.out.println("right " + speed);
			}
			break;
		case KeyEvent.VK_UP:
			if (controller.getPilotingState() == PilotingStates.STATE_5_HOVERING) {
				controller.setPilotingState(PilotingStates.STATE_6_FLYING);
				controller.setSpeed(speed, 0, 0, 0);
				System.out.println("foreward");
			}
			break;
		case KeyEvent.VK_DOWN:
			if (controller.getPilotingState() == PilotingStates.STATE_5_HOVERING) {
				controller.setPilotingState(PilotingStates.STATE_6_FLYING);
				controller.setSpeed(-speed, 0, 0, 0);
				System.out.println("backwards");
			}
			break;
		case KeyEvent.VK_A:
			if (controller.getPilotingState() == PilotingStates.STATE_5_HOVERING) {
				controller.setPilotingState(PilotingStates.STATE_6_FLYING);
				controller.setSpeed(0, 0, 0, -speed);
				System.out.println("turncounterclockwise");
			}
			break;
		case KeyEvent.VK_D:
			if (controller.getPilotingState() == PilotingStates.STATE_5_HOVERING) {
				controller.setPilotingState(PilotingStates.STATE_6_FLYING);
				controller.setSpeed(0, 0, 0, speed);
				System.out.println("turnclockwise");
			}
			break;
		case KeyEvent.VK_W:
			if (controller.getPilotingState() == PilotingStates.STATE_5_HOVERING) {
				controller.setPilotingState(PilotingStates.STATE_6_FLYING);
				controller.setSpeed(0, 0, -speed, 0);
				System.out.println("up");
			}
			break;
		case KeyEvent.VK_S:
			if (controller.getPilotingState() == PilotingStates.STATE_5_HOVERING) {
				controller.setPilotingState(PilotingStates.STATE_6_FLYING);
				controller.setSpeed(0, 0, speed, 0);
				System.out.println("down");
			}
			break;
		case KeyEvent.VK_ESCAPE:
			System.out.println("exit");
			setExit(true);

		default:
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		switch (keyCode) {
		case KeyEvent.VK_SPACE:
			if (controller.getPilotingState() != PilotingStates.STATE_2_READY) {
				controller.setPilotingState(PilotingStates.STATE_2_READY);
				isFlying = false;
			}
			break;
		// case KeyEvent.VK_ENTER:
		// controller.setPilotingState(PilotingStates.STATE_5_HOVERING);
		// System.out.println("hovering");
		// break;
		// case KeyEvent.VK_LEFT:
		// if (controller.getControlState().equals("FLYING")) {
		// controller.setPilotingState(PilotingStates.STATE_5_HOVERING);
		// controller.setSpeed(0, 0, 0, 0);
		// }
		// break;
		// case KeyEvent.VK_RIGHT:
		// if (controller.getControlState().equals("FLYING")) {
		// controller.setPilotingState(PilotingStates.STATE_5_HOVERING);
		// controller.setSpeed(0, 0, 0, 0);
		// }
		// break;
		// case KeyEvent.VK_UP:
		// if (controller.getControlState().equals("FLYING")) {
		// controller.setPilotingState(PilotingStates.STATE_5_HOVERING);
		// controller.setSpeed(0, 0, 0, 0);
		// System.out.println("foreward");
		// }
		// break;
		// case KeyEvent.VK_DOWN:
		//
		// controller.setPilotingState(PilotingStates.STATE_5_HOVERING);
		// controller.setSpeed(0, 0, 0, 0);
		// System.out.println("backwards");
		//
		// break;
		// case KeyEvent.VK_A:
		// if (controller.getControlState().equals("FLYING")) {
		// controller.setPilotingState(PilotingStates.STATE_5_HOVERING);
		// controller.setSpeed(0, 0, 0, 0);
		// System.out.println("turncounterclockwise");
		// }
		// break;
		// case KeyEvent.VK_D:
		// if (controller.getControlState().equals("FLYING")) {
		// controller.setPilotingState(PilotingStates.STATE_5_HOVERING);
		// controller.setSpeed(0, 0, 0, 0);
		// System.out.println("turnclockwise");
		// }
		// break;
		// case KeyEvent.VK_W:
		// if (controller.getControlState().equals("FLYING")) {
		// controller.setPilotingState(PilotingStates.STATE_5_HOVERING);
		// controller.setSpeed(0, 0, 0, 0);
		// System.out.println("up");
		// }
		// break;
		// case KeyEvent.VK_S:
		// if (controller.getControlState().equals("FLYING")) {
		// controller.setPilotingState(PilotingStates.STATE_5_HOVERING);
		// controller.setSpeed(0, 0, 0, 0);
		// System.out.println("down");
		// }
		// break;
		default:
			if (controller.getPilotingState() != PilotingStates.STATE_2_READY){
				controller.setSpeed(0, 0, 0, 0);
				controller.setPilotingState(PilotingStates.STATE_5_HOVERING);
			}
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	public void anzeigen(boolean v) {
		this.setVisible(v);
	}
	
	public void setInputController(IInputController controller) {
		this.controller = controller;
	}

	public boolean isExit() {
		return exit;
	}

	public void setExit(boolean exit) {
		this.exit = exit;
	}
}
