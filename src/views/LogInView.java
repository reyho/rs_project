package views;

import controllers.AppMain;
import controllers.FacultyPersonnelController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import models.FacultyPersonnel;

public class LogInView extends GridPane {
	AppMain appMain;
	
	public LogInView(AppMain app) {
		appMain = app;
		
		this.setPadding(new Insets(10,10,10,10));
		this.setVgap(8);
		this.setHgap(10);
		
		Label errorMsg = new Label("");
		GridPane.setConstraints(errorMsg, 0, 0);
		errorMsg.setStyle("-fx-color:red");
		
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
		
		Button loginButton = new Button("Log In");
		GridPane.setConstraints(loginButton, 1, 3);
		loginButton.setOnAction(e->{
			//System.out.println(nameInput.getText());
			errorMsg.setText( attemptLogIn(nameInput.getText(), passInput.getText()));
			
		});
		
		this.getChildren().addAll(errorMsg, nameLabel, nameInput, passLabel, passInput, loginButton);
		
		this.setStyle("-fx-background-color:black");
	}
	
	private String attemptLogIn(String username, String password) {
		FacultyPersonnelController fc = new FacultyPersonnelController();
		FacultyPersonnel user = fc.findFacultyMemberByUsername(username);
		if(user != null) {
			System.out.println(user);
			if(password.equals(user.getPassword())) {
				appMain.setUser(user);
				appMain.renderMainLayout();
				return "";
			} else {
				return "WrongPassword!";
			}
		} else {
			return "Wrong username!";
		}
	}

}
