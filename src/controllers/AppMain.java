package controllers;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import models.FacultyPersonnel;
import views.BuildingView;
import views.LogInWidget;
import views.NavigationWidget;
import views.TimeTableView;

public class AppMain extends Application {
	private Stage window;
	private BorderPane mainLayout;
	private FacultyPersonnel user;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		setMainLayout(new BorderPane());
		
		this.renderLogInScreen();
	}

	private void renderLogInScreen() {
		Scene s = new Scene(new LogInWidget(this));
		window.setTitle("Log In"); // Set the stage title
		window.setScene(s); // Place the scene in the stage
		window.show(); // Display the stage
	}
	
	
	public FacultyPersonnel getUser() {
		return user;
	}


	public void setUser(FacultyPersonnel user) {
		this.user = user;
	}

	
	
	public void renderMainLayout() {
		getMainLayout().setLeft(new NavigationWidget(this, user));
		getMainLayout().setCenter(new TimeTableView());
		window.setScene(new Scene(getMainLayout(), 1200, 600));
		window.setTitle("RS_app");
		window.show();
	}


	public void setContentPane(Node view) {
		getMainLayout().setCenter(view);
	}


	public BorderPane getMainLayout() {
		return mainLayout;
	}


	public void setMainLayout(BorderPane mainLayout) {
		this.mainLayout = mainLayout;
	}
}
