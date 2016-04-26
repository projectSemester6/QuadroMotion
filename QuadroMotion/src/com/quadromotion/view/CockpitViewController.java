package com.quadromotion.view;

import java.util.Observable;
import java.util.Observer;

import com.quadromotion.MainApp;
import com.quadromotion.model.Model;
import com.quadromotion.util.Util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.image.*;

public class CockpitViewController implements Observer {

	/**
	 * The data as an observable list of Model.
	 */
	private ObservableList<Model> modelData = FXCollections.observableArrayList();

	@FXML
	private ImageView x_right;
	@FXML
	private ImageView x_left;
	@FXML
	private ImageView y_top;
	@FXML
	private ImageView y_bottom;
	@FXML
	private ImageView z_top;
	@FXML
	private ImageView z_bottom;
	@FXML
	private ImageView spin;

	private float minLimit = -15f;
	private float maxLimit = 15f;
	
	private Util util;

	private Model model;
	
	// Reference to the main application.
	private MainApp mainApp;

	/**
	 * The constructor. The constructor is called before the initialize()
	 * method.
	 */
	public CockpitViewController() {

	}

	@FXML
	public void changeXRightImage(String color) {
		Image image = null;
		switch (color) {
		case "green":
			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-oben.png"));
			break;
		case "black":
			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-oben-bl.png"));
			break;
		}
		x_right.setImage(image);
	}

	@FXML
	public void changeXLeftImage(String color) {
		Image image = null;
		switch (color) {
		case "green":
			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-oben.png"));
			break;
		case "black":
			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-oben-bl.png"));
			break;
		}
		x_left.setImage(image);
	}

	@FXML
	public void changeYTopImage(String color) {
		Image image = null;
		switch (color) {
		case "green":
			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-oben-rechts.png"));
			break;
		case "black":
			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-oben-rechts-bl.png"));
			break;
		}
		y_top.setImage(image);
	}

	@FXML
	public void changeYBottomImage(String color) {
		Image image = null;
		switch (color) {
		case "green":
			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-oben-rechts.png"));
			break;
		case "black":
			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-oben-rechts-bl.png"));
			break;
		}
		y_bottom.setImage(image);
	}

	@FXML
	public void changeZTopImage(String color) {
		Image image = null;
		switch (color) {
		case "green":
			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-oben.png"));
			break;
		case "black":
			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-oben-bl.png"));
			break;
		}
		z_top.setImage(image);
	}

	@FXML
	public void changeZBottomImage(String color) {
		Image image = null;
		switch (color) {
		case "green":
			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-oben.png"));
			break;
		case "black":
			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-oben-bl.png"));
			break;
		}
		z_bottom.setImage(image);
	}

	@FXML
	public void changeSpinImage(String color) {
		Image image = null;
		switch (color) {
		case "green":
			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-gebogen.png"));
			break;
		case "black":
			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-gebogen-bl.png"));
			break;
		}
		spin.setImage(image);
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {

	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

		// Add observable list data to the table
		// personTable.setItems(mainApp.getPersonData());
	}

	public void setModel(Model model) {
		this.model = model;
		model.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		Model m = (Model) o;
		if (m.getSpeedX() > maxLimit)
			changeXRightImage("green");
		else
			changeXRightImage("black");
		
		if (m.getSpeedY() > maxLimit)
			changeYTopImage("green");
		else
			changeYTopImage("black");
		
		if (m.getSpeedZ() > maxLimit)
			changeZTopImage("green");
		else
			changeZTopImage("black");
		
		if (m.getSpeedSpin() > maxLimit||m.getSpeedSpin()<minLimit)
			changeSpinImage("green");
		else
			changeSpinImage("black");
		
		
		if (m.getSpeedX() < minLimit)
			changeXLeftImage("green");
		else
			changeXLeftImage("black");
		
		if (m.getSpeedY() < minLimit)
			changeYBottomImage("green");
		else
			changeYBottomImage("black");
		
		if (m.getSpeedZ() < minLimit)
			changeZBottomImage("green");
		else
			changeZBottomImage("black");
	}
}
