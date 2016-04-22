
package com.quadromotion.gestures;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class KeyBoardCommands extends JFrame implements KeyListener {

	int speed = 15;
	private int speedX = 0;
	private boolean exit = false;
	private float pitchRightHand;
	
	private Gestures gestures = new Gestures();

	public KeyBoardCommands() {
		this.setLayout(new BorderLayout());
		JTextField field = new JTextField();
		JLabel label = new JLabel("QuadroMotion");
		label.addKeyListener(this);
		// field.addKeyListener(this);
		this.addKeyListener(this);
		this.add(label, BorderLayout.CENTER);
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
			System.out.println("start");
			break;
		case KeyEvent.VK_SPACE:
			System.out.println("land");
			break;
		case KeyEvent.VK_LEFT:
			getGestures().setPitchRightHand(-speed);
			System.out.println("left " + speedX);
			break;
		case KeyEvent.VK_RIGHT:
			speedX = speed;
			System.out.println("right " + speedX);
			break;
		case KeyEvent.VK_UP:
			pitchRightHand = speed;
			System.out.println("foreward");
			break;
		case KeyEvent.VK_DOWN:
			System.out.println("backwards");
			break;
		case KeyEvent.VK_A:
			System.out.println("turncounterclockwise");
			break;
		case KeyEvent.VK_D:
			System.out.println("turnclockwise");
			break;
		case KeyEvent.VK_W:
			System.out.println("up");
			break;
		case KeyEvent.VK_S:
			System.out.println("down");
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

	}

	public int getSpeedX() {
		return speedX;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public boolean isExit() {
		return exit;
	}

	public void setExit(boolean exit) {
		this.exit = exit;
	}

	public Gestures getGestures() {
		return gestures;
	}

	public void setGestures(Gestures gestures) {
		this.gestures = gestures;
	}

}
