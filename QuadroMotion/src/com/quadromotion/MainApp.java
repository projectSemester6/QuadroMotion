package com.quadromotion;

import java.io.IOException;

import com.leapmotion.leap.Controller;
import com.quadromotion.app.App;
import com.quadromotion.gestures.LeapMotion;
import com.quadromotion.model.Model;
import com.quadromotion.testing.ChangeModelForTestingCockpitView;
import com.quadromotion.view.CockpitViewController;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
//	private App app = null;
	private static Model model = null;
	private ChangeModelForTestingCockpitView cmt;
	private static CockpitViewController controller = null;
	private static Controller leapController = null;
	private static LeapMotion leap = null;

	/**
	 * The data as an observable list of Model.
	 */
//	private ObservableList<Model> modelData = FXCollections.observableArrayList();

	public MainApp() {
		MainApp.model = new Model();
//		modelData.add(model);
	}

	@Override
	public void start(Stage primaryStage) {
//		this.app = new App(model);
//		app.boot();
		this.cmt = new ChangeModelForTestingCockpitView("Change model test", model);
		cmt.start();
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("QuadroMotionApp");

		initRootLayout();

		showCockpipView();
	}

	/**
	 * Initializes the root layout.
	 */
	public void initRootLayout() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Shows the cockpit view inside the root layout.
	 */
	public void showCockpipView() {
		try {
			// Load cockpit view.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/CockpitView.fxml"));
			AnchorPane cockpitView = (AnchorPane) loader.load();

			// Set cockpit view into the center of root layout.
			rootLayout.setCenter(cockpitView);
			 // Give the controller access to the main app.
			controller = loader.getController();
	        controller.setMainApp(this);
	        controller.setModel(model);
//	        controller.loadGreenImage(); // sets the x_right image to green
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns the main stage.
	 * 
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
