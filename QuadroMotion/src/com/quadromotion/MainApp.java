package com.quadromotion;

import java.io.IOException;

import com.leapmotion.leap.Controller;
import com.quadromotion.app.App;
import com.quadromotion.input.LeapMotion;
import com.quadromotion.model.Model;
import com.quadromotion.service.Services;
import com.quadromotion.testing.ChangeModel;
import com.quadromotion.util.Util;
import com.quadromotion.view.CockpitViewController;

import de.yadrone.base.ARDrone;
import de.yadrone.base.IARDrone;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
	private Stage batteryStage;
	private BorderPane rootLayoutBattery;
	private App app = null;
	private static Model model = null;
	private static CockpitViewController controller = null;
	private static IARDrone drone = null;

	private static Controller leapController = null;
	private static LeapMotion leap = null;

	private static ChangeModel cmt;

	/**
	 * The data as an observable list of Model.
	 */
//	private ObservableList<Model> modelData = FXCollections.observableArrayList();

	public MainApp() {
		MainApp.model = new Model();
		// modelData.add(model);
	}

	@Override
	public void start(Stage primaryStage) {
//		this.drone = new ARDrone();
//		this.leap = new LeapMotion(new Services(model));
//		this.leapController = new Controller();
//		leapController.addListener(leap);

		// this.app = new App(model);
		// app.setDrone(drone);
		// app.boot();
		// app.run();
		
		 this.cmt = new ChangeModel("Change model test",
		 model);
		 cmt.setPriority(Thread.MAX_PRIORITY);
		 cmt.start();
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("QuadroMotion");

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
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initRootLayoutBattery() {
		try {
			FXMLLoader batLoader = new FXMLLoader();
			batLoader.setLocation(MainApp.class.getResource("view/RootLayoutBatteryLevel.fxml"));
			rootLayoutBattery = (BorderPane) batLoader.load();

			Scene batteryScene = new Scene(rootLayoutBattery);
			batteryStage.setScene(batteryScene);
			batteryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Shows the cockpit view inside the root layout.
	 */
	public void showBatteryLevelView() {
		try {
			// Load cockpit view.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/BatteryLevelView.fxml"));
			AnchorPane batteryLevelView = (AnchorPane) loader.load();

			// Set cockpit view into the center of root layout.
			rootLayoutBattery.setCenter(batteryLevelView);
			// Give the controller access to the main app.
			// batController = loader.getController();
			// batController.setMainApp(this);
			// batController.setModel(model);
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

	/**
	 * Returns the battery stage.
	 * 
	 * @return
	 */
	public Stage getBatteryStage() {
		return batteryStage;
	}

//	public static void main(String[] args) {
//		try {
//			launch(args);
//		} catch (Exception exc) {
//			exc.printStackTrace();
//		} finally {
//			if (drone != null)
//				drone.stop();
//
//			System.exit(0);
//		}
//	}
}
