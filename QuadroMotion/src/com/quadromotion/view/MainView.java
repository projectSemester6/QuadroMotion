package com.quadromotion.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DateFormat.Field;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.quadromotion.controller.MainViewController;
import com.quadromotion.gestures.KeyBoardCommands;
import com.quadromotion.model.Model;

public class MainView extends JFrame implements Observer {

	private int speed = 15;
	private final int ZERO = 0;
	private Model model;
	private MainViewController controller;
	private Object gestures;
	JLabel labelTitle;
	JLabel labelLeft;
	JLabel labelRight;
	JLabel labelForward;
	JLabel labelBackward;
	int c = 0;

	/**
	 * Constructor I
	 * @param model
	 */
	public MainView(Model model) {
		createView();
		
		this.model = model;
		setController(makeController());
		this.model.addObserver(this);
	}
	
	/**
	 * Constructor I
	 * @param model
	 */
	public MainView(Model model, String gestureType) {
		createView();
		this.model = model;
		setController(makeController());
		this.model.addObserver(this);
	}

	private void createView(){
		this.setLayout(new BorderLayout());
		
		//JTextField field = new JTextField();
		//field.addKeyListener(this);
		//this.add(field, BorderLayout.SOUTH);
		labelTitle = new JLabel("QuadroMotion");
//		labelTitle.addKeyListener(this);
//		this.addKeyListener(this);
		this.add(labelTitle, BorderLayout.CENTER);
		
		labelLeft = new JLabel("Left: ");
		this.add(labelLeft, BorderLayout.PAGE_END);

		ImageIcon icon = new ImageIcon("view/parrot-ardrone-2-0.png");
		Image image = icon.getImage();
//		this.add(image, BorderLayout.AFTER_LAST_LINE);
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(new FlowLayout());
		this.setBounds(200,100,300,100);
		//this.setVisible(true);
	}
	
	/**
	 * Prints a string to the console
	 * @param inputString
	 */
	public void printToConsole(String inputString) {
		System.out.println(inputString);
	}

	public MainViewController makeController() {
		return new MainViewController(model, this);
	}

	public MainViewController getController() {
		return controller;
	}

	public void setController(MainViewController controller) {
		this.controller = controller;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Elemente der View aktualisieren
		Model m = (Model) o;
		labelLeft.setText("Battery Level: "+ m.getBatLevel()+ " %");
 	}

//	@Override
//	public void keyTyped(KeyEvent e) {
//		// TODO Auto-generated method stub
//	}
//
//	@Override
//	public void keyPressed(KeyEvent e) {
//		// TODO Auto-generated method stub
//		int keyCode = e.getKeyCode();
//		switch (keyCode) {
//		case KeyEvent.VK_ENTER:
//			if(!model.getTakeOffCommand()) {
//				controller.setTakeOffCommand(true);
//				printToConsole("start = true");
//			}
//			break;
//		case KeyEvent.VK_SPACE:
//			controller.setLandingCommand(true);
//			printToConsole("land = true");
//			break;
//		case KeyEvent.VK_LEFT:
//			controller.setSpeedX(-speed);
//			printToConsole("left = " + -speed);
//			break;
//		case KeyEvent.VK_RIGHT:
//			controller.setSpeedX(speed);
//			printToConsole("right = " + speed);
//			break;
//		case KeyEvent.VK_UP:
//			controller.setSpeedY(speed);
//			printToConsole("foreward = " + speed);
//			break;
//		case KeyEvent.VK_DOWN:
//			controller.setSpeedY(-speed);
//			printToConsole("backwards = " + -speed);
//			break;
//		case KeyEvent.VK_A:
//			controller.setSpeedSpin(-speed);
//			printToConsole("turn counterclockwise = " + model.getSpeedSpin());
//			break;
//		case KeyEvent.VK_D:
//			controller.setSpeedSpin(speed);
//			printToConsole("turn clockwise = " + model.getSpeedSpin());
//			break;
//		case KeyEvent.VK_W:
//			controller.setSpeedZ(speed);
//			printToConsole("move up = " + speed);
//			break;
//		case KeyEvent.VK_S:
//			controller.setSpeedZ(-speed);
//			printToConsole("move down = " + -speed);
//			break;
//		case KeyEvent.VK_ESCAPE:
//			printToConsole("exit");
//		default:
//			break;
//		}
//	}
//
//	@Override
//	public void keyReleased(KeyEvent e) {
//		// TODO Auto-generated method stub
//		int keyCode = e.getKeyCode();
//		
//		switch (keyCode) {
//		case KeyEvent.VK_ENTER:
//			if(model.getTakeOffCommand()) {
//				controller.setTakeOffCommand(false);
//				printToConsole("start = false");
//			}
//			break;
//		case KeyEvent.VK_SPACE:
//			controller.setLandingCommand(false);
//			printToConsole("land = false");
//			break;
//		case KeyEvent.VK_LEFT:
//			controller.setSpeedX(ZERO);
//			printToConsole("left = " + ZERO);
//			break;
//		case KeyEvent.VK_RIGHT:
//			controller.setSpeedX(ZERO);
//			printToConsole("right = " + ZERO);
//			break;
//		case KeyEvent.VK_UP:
//			controller.setSpeedY(ZERO);
//			printToConsole("foreward = " + ZERO);
//			break;
//		case KeyEvent.VK_DOWN:
//			controller.setSpeedY(ZERO);
//			printToConsole("backwards = " + ZERO);
//			break;
//		case KeyEvent.VK_A:
//			controller.setSpeedSpin(ZERO);
//			printToConsole("turncounterclockwise = " + ZERO);
//			break;
//		case KeyEvent.VK_D:
//			controller.setSpeedSpin(ZERO);
//			printToConsole("turnclockwise = " + ZERO);
//			break;
//		case KeyEvent.VK_W:
//			controller.setSpeedZ(ZERO);
//			printToConsole("up = " + ZERO);
//			break;
//		case KeyEvent.VK_S:
//			controller.setSpeedZ(ZERO);
//			printToConsole("down = " + ZERO);
//			break;
//		case KeyEvent.VK_ESCAPE:
//			printToConsole("exit");
//		default:
//			break;
//		}
//	}
}
