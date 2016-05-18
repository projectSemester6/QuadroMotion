
package com.quadromotion.gestures;

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

import com.quadromotion.controller.*;
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
				if (!isFlying)
					controller.setPilotingState(PilotingStates.STATE_2_READY);
				controller.setInputDeviceState(true);
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (!isFlying)
					controller.setPilotingState(PilotingStates.STATE_0_OFF);
				controller.setInputDeviceState(false);
			}
		});

		addWindowListener(new WindowListener() {

			public void windowOpened(WindowEvent e) {
				if (!isFlying)
					controller.setPilotingState(PilotingStates.STATE_2_READY);
				controller.setInputDeviceState(true);
			}

			public void windowIconified(WindowEvent e) {
			}

			public void windowDeiconified(WindowEvent e) {
			}

			public void windowActivated(WindowEvent e) {
				if (!isFlying)
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
				if (!isFlying)
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

	public void anzeigen(boolean v) {
		this.setVisible(v);
	}

	@Override
	public void keyTyped(KeyEvent e) {

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
			if (isFlying) {
				controller.setPilotingState(PilotingStates.STATE_7_LANDING);
				System.out.println("land");
			}
			break;
		case KeyEvent.VK_LEFT:
			if (isFlying) {
				controller.setPilotingState(PilotingStates.STATE_6_FLYING);
				controller.setSpeed(0, -speed, 0, 0);
				System.out.println("left " + -speed);
			}
			break;
		case KeyEvent.VK_RIGHT:
			if (isFlying) {
				controller.setPilotingState(PilotingStates.STATE_6_FLYING);
				controller.setSpeed(0, speed, 0, 0);
				System.out.println("right " + speed);
			}
			break;
		case KeyEvent.VK_UP:
			if (isFlying) {
				controller.setPilotingState(PilotingStates.STATE_6_FLYING);
				controller.setSpeed(speed, 0, 0, 0);
				System.out.println("foreward");
			}
			break;
		case KeyEvent.VK_DOWN:
			if (isFlying) {
				controller.setPilotingState(PilotingStates.STATE_6_FLYING);
				controller.setSpeed(-speed, 0, 0, 0);
				System.out.println("backwards");
			}
			break;
		case KeyEvent.VK_A:
			if (isFlying) {
				controller.setPilotingState(PilotingStates.STATE_6_FLYING);
				controller.setSpeed(0, 0, 0, -speed);
				System.out.println("turncounterclockwise");
			}
			break;
		case KeyEvent.VK_D:
			if (isFlying) {
				controller.setPilotingState(PilotingStates.STATE_6_FLYING);
				controller.setSpeed(0, 0, 0, speed);
				System.out.println("turnclockwise");
			}
			break;
		case KeyEvent.VK_W:
			if (isFlying) {
				controller.setPilotingState(PilotingStates.STATE_6_FLYING);
				controller.setSpeed(0, 0, -speed, 0);
				System.out.println("up");
			}
			break;
		case KeyEvent.VK_S:
			if (isFlying) {
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
			if (isFlying) {
				controller.setPilotingState(PilotingStates.STATE_2_READY);
				isFlying = false;
			}
			break;
		case KeyEvent.VK_ENTER:
			controller.setPilotingState(PilotingStates.STATE_5_HOVERING);
			System.out.println("hovering");
			break;
		case KeyEvent.VK_LEFT:
			if (isFlying) {
				controller.setPilotingState(PilotingStates.STATE_5_HOVERING);
				controller.setSpeed(0, 0, 0, 0);
			}
			break;
		case KeyEvent.VK_RIGHT:
			if (isFlying) {
				controller.setPilotingState(PilotingStates.STATE_5_HOVERING);
				controller.setSpeed(0, 0, 0, 0);
			}
			break;
		case KeyEvent.VK_UP:
			if (isFlying) {
				controller.setPilotingState(PilotingStates.STATE_5_HOVERING);
				controller.setSpeed(0, 0, 0, 0);
				System.out.println("foreward");
			}
			break;
		case KeyEvent.VK_DOWN:
			if (isFlying) {
				controller.setPilotingState(PilotingStates.STATE_5_HOVERING);
				controller.setSpeed(0, 0, 0, 0);
				System.out.println("backwards");
			}
			break;
		case KeyEvent.VK_A:
			if (isFlying) {
				controller.setPilotingState(PilotingStates.STATE_5_HOVERING);
				controller.setSpeed(0, 0, 0, 0);
				System.out.println("turncounterclockwise");
			}
			break;
		case KeyEvent.VK_D:
			if (isFlying) {
				controller.setPilotingState(PilotingStates.STATE_5_HOVERING);
				controller.setSpeed(0, 0, 0, 0);
				System.out.println("turnclockwise");
			}
			break;
		case KeyEvent.VK_W:
			if (isFlying) {
				controller.setPilotingState(PilotingStates.STATE_5_HOVERING);
				controller.setSpeed(0, 0, 0, 0);
				System.out.println("up");
			}
			break;
		case KeyEvent.VK_S:
			if (isFlying) {
				controller.setPilotingState(PilotingStates.STATE_5_HOVERING);
				controller.setSpeed(0, 0, 0, 0);
				System.out.println("down");
			}
			break;
		}
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
