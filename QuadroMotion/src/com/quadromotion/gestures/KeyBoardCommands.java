
package com.quadromotion.gestures;

import java.awt.BorderLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;

import com.quadromotion.controller.*;
import com.quadromotion.model.Model;
import com.quadromotion.pilotingstates.PilotingStates;

public class KeyBoardCommands extends JFrame implements KeyListener {

	int speed = 15;
	private boolean exit = false;
	private boolean isFlying = false;

	private ServiceController controller = null;

	public KeyBoardCommands(Model m) {
		controller = new ServiceController(m);
		this.setLayout(new BorderLayout());
		JTextField field = new JTextField();
		JLabel label = new JLabel("QuadroMotion");
		label.addKeyListener(this);
		// field.addKeyListener(this);
		this.addKeyListener(this);
		this.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				if (!isFlying)
					controller.setPilotingState(PilotingStates.STATE_2_READY);
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if (!isFlying)
					controller.setPilotingState(PilotingStates.STATE_0_OFF);
			}

		});
		this.add(label, BorderLayout.CENTER);
		this.pack();
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int keyCode = e.getKeyCode();
		switch (keyCode) {
		case KeyEvent.VK_ENTER:
			controller.setPilotingState(PilotingStates.STATE_3_TAKINGOFF);
			isFlying = true;
			System.out.println("start");
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

	public boolean isExit() {
		return exit;
	}

	public void setExit(boolean exit) {
		this.exit = exit;
	}

}
