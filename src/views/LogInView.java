package views;

import com.sun.prism.paint.Color;

import controllers.AppMain;
import controllers.FacultyPersonnelController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import models.FacultyPersonnel;

public class LogInView extends GridPane {
	AppMain appMain;
	
	public LogInView(AppMain app) {
		appMain = app;
		
		this.setPadding(new Insets(10,10,10,10));
		this.setVgap(8);
		this.setHgap(10);
		
		Label welcomeMsg = new Label("");
		GridPane.setConstraints(welcomeMsg, 0, 0);
		
		Label errorMsg = new Label("");
		GridPane.setConstraints(errorMsg, 1, 4);
		
		Label nameLabel = new Label("Username");
		GridPane.setConstraints(nameLabel, 0, 1);
		
		TextField nameInput = new TextField();
		nameInput.setPromptText("username");
		GridPane.setConstraints(nameInput, 1, 1);
		
		Label passLabel = new Label("Password");
		GridPane.setConstraints(passLabel, 0, 2);
		
		TextField passInput = new TextField();
		passInput.setPromptText("password");
		GridPane.setConstraints(passInput, 1, 2);
		
		// Login handling
		Button loginButton = new Button("Log In");
		GridPane.setConstraints(loginButton, 1, 3);
		loginButton.setOnAction(e->{
			//System.out.println(nameInput.getText());
			errorMsg.setText( attemptLogIn(nameInput.getText(), passInput.getText()));
			errorMsg.setStyle("-fx-text-fill:red");
			if(appMain.getUser() != null) {
				appMain.renderMainLayout();
			}
		});
		
		
		Label guestLabel = new Label("For students:");
		GridPane.setConstraints(guestLabel, 0 , 5);
		
		Button loginButtonGuest = new Button("Student Access");
		GridPane.setConstraints(loginButtonGuest, 1, 5);
		
		this.getChildren().addAll(errorMsg, nameLabel, nameInput, passLabel, passInput, loginButton, guestLabel, loginButtonGuest);
		
		this.setStyle("-fx-background-color:white");
	}
	
	private String attemptLogIn(String username, String password) {
		FacultyPersonnelController fc = new FacultyPersonnelController();
		FacultyPersonnel user = fc.findFacultyMemberByUsername(username);
		if(user != null) {
			System.out.println(user);
			if(password.equals(user.getPassword())) {
				appMain.setUser(user);
				//appMain.renderMainLayout();
				return "Sucess";
			} else {
				return "WrongPassword!";
			}
		} else {
			return "Wrong username!";
		}
	}

}
