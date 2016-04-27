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
//	private ObservableList<Model> modelData = FXCollections.observableArrayList();

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

	public void changeXRightImage(int percent) {
		Image image = null;

		if (percent >= 90)
			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-100.png"));

//		else if (percent >= 80)
//			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-90.png"));

		else if (percent >= 70)
			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-80.png"));

//		else if (percent >= 60)
//			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-70.png"));

		else if (percent >= 50)
			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-60.png"));
//
//		else if (percent >= 40)
//			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-50.png"));

		else if (percent >= 30)
			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-40.png"));
//
//		else if (percent >= 20)
//			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-30.png"));

		else if (percent >= 1)
			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-20.png"));
//
//		else if (percent >= 1)
//			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-10.png"));

		else
			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-0.png"));

		x_right.setImage(image);

	}

	public void changeXLeftImage(int percent) {
		Image image = null;
		if (percent <= -90)
			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-100.png"));

//		else if (percent <= -80)
//			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-90.png"));

		else if (percent <= -70)
			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-80.png"));

//		else if (percent <= -60)
//			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-70.png"));

		else if (percent <= -50)
			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-60.png"));

//		else if (percent <= -40)
//			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-50.png"));

		else if (percent <= -30)
			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-40.png"));

//		else if (percent <= -20)
//			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-30.png"));

		else if (percent <= -1)
			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-20.png"));

//		else if (percent <= -1)
//			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-10.png"));

		else
			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-0.png"));
		x_left.setImage(image);
	}

	public void changeYTopImage(int percent) {
		Image image = null;
		if (percent >= 90)
			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-100.png"));

//		else if (percent >= 80)
//			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-90.png"));

		else if (percent >= 70)
			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-80.png"));

//		else if (percent >= 60)
//			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-70.png"));

		else if (percent >= 50)
			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-60.png"));

//		else if (percent >= 40)
//			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-50.png"));

		else if (percent >= 30)
			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-40.png"));

//		else if (percent >= 20)
//			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-30.png"));

		else if (percent >= 1)
			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-20.png"));

//		else if (percent >= 1)
//			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-10.png"));

		else
			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-0.png"));
		y_top.setImage(image);
	}

	
	public void changeYBottomImage(int percent) {
		Image image = null;
		if (percent <= -90)
			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-oben-rechts.png"));

		else if (percent <= -80)
			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-oben-rechts.png"));
		
		else if (percent <= -70)
			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-oben-rechts.png"));
		
		else if (percent <= -60)
			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-oben-rechts.png"));
		
		else if (percent <= -50)
			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-oben-rechts.png"));
		
		else if (percent <= -40)
			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-oben-rechts.png"));
		
		else if (percent <= -30)
			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-oben-rechts.png"));
		
		else if (percent <= -20)
			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-oben-rechts.png"));
		
		else if (percent <= -10)
			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-oben-rechts.png"));
		
		else if (percent <= -1)
			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-oben-rechts.png"));
		
		else
			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-oben-rechts-bl.png"));
		y_bottom.setImage(image);
	}

	
	public void changeZTopImage(int percent) {
		Image image = null;
		if (percent >= 90)
			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-100.png"));

//		else if (percent >= 80)
//			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-90.png"));

		else if (percent >= 70)
			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-80.png"));

//		else if (percent >= 60)
//			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-70.png"));

		else if (percent >= 50)
			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-60.png"));

//		else if (percent >= 40)
//			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-50.png"));

		else if (percent >= 30)
			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-40.png"));

//		else if (percent >= 20)
//			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-30.png"));

		else if (percent >= 1)
			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-20.png"));

//		else if (percent >= 1)
//			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-10.png"));

		else
			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-0.png"));
		z_top.setImage(image);
	}

	public void changeZBottomImage(int percent) {
		Image image = null;
		if (percent <= -90)
			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-100.png"));

//		else if (percent <= -80)
//			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-90.png"));

		else if (percent <= -70)
			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-80.png"));

//		else if (percent <= -60)
//			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-70.png"));

		else if (percent <= -50)
			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-60.png"));

//		else if (percent <= -40)
//			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-50.png"));

		else if (percent <= -30)
			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-40.png"));

//		else if (percent <= -20)
//			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-30.png"));

		else if (percent <= -1)
			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-20.png"));

//		else if (percent <= -1)
//			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-10.png"));

		else
			image = new Image(MainApp.class.getResourceAsStream("icons/pfeil-0.png"));
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

	private void updateView(Model m) {

		changeXRightImage((int) m.getSpeedX());
		changeXLeftImage((int) m.getSpeedX());

		changeYTopImage((int) m.getSpeedY());
		changeYBottomImage((int) m.getSpeedY());

		changeZTopImage((int) m.getSpeedZ());
		changeZBottomImage((int) m.getSpeedZ());

		if (m.getSpeedSpin() > maxLimit || m.getSpeedSpin() < minLimit)
			changeSpinImage("green");
		else
			changeSpinImage("black");
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		Model m = (Model) o;
		updateView(m);
	}
}
