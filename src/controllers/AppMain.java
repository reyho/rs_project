package controllers;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import models.FacultyPersonnel;
import views.BuildingView;
import views.LogInView;

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
		// Create a log in scene and place it in the stage
		Scene scene = new Scene(new BuildingView());
		window.setTitle("LogIn"); // Set the stage title
		window.setScene(scene); // Place the scene in the stage
		window.show(); // Display the stage
		
		// Login action returns a user and his privilege number is implicit.
		
		// Place nodes in the pane
		//mainLayout.setLeft(new NavTest(mainLayout));
		
		//mainLayout.setCenter(LogInView.getLogInScreen());
		
		
		
		
		
	}
	
	/*
	public static void main(String[] args) {
		/*EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("RS_Project");
		EntityManager em = emfactory.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		

		Building b = new Building();
		b.setName("TT_build_06");
		//b.setId(2);

		tx.begin();
		em.persist(b);
		tx.commit();
		 
		 BuildingsController bc = new BuildingsController();
		 bc.createBuilding("TT_build_07");*/
}
