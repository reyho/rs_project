package views;

import controllers.FacultyPersonnelController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import models.FacultyPersonnel;

public class LogInView extends GridPane {
	public LogInView(FacultyPersonnel user) {
		this.setPadding(new Insets(10,10,10,10));
		this.setVgap(8);
		this.setHgap(10);
		
		Label nameLabel = new Label("Username");
		GridPane.setConstraints(nameLabel, 0, 0);
		
		TextField nameInput = new TextField();
		nameInput.setPromptText("username");
		GridPane.setConstraints(nameInput, 1, 0);
		
		Label passLabel = new Label("Password");
		GridPane.setConstraints(passLabel, 0, 1);
		
		TextField passInput = new TextField();
		passInput.setPromptText("password");
		GridPane.setConstraints(passInput, 1, 1);
		
		Button loginButton = new Button("Log In");
		GridPane.setConstraints(loginButton, 1, 2);
		loginButton.setOnAction(e->{
			System.out.println(nameInput.getText());
			attemptLogIn(nameInput.getText(), passInput.getText());
		});
		
		this.getChildren().addAll(nameLabel, nameInput, passLabel, passInput, loginButton);
		
		this.setStyle("-fx-background-color:black");
	}
	
	private void attemptLogIn(String username, String password) {
		FacultyPersonnelController fc = new FacultyPersonnelController();
		FacultyPersonnel user = fc.findFacultyMemberByUsername(username);
		if(user != null) {
			System.out.println(user);
			if(password.equals(user.getPassword())) {
				System.out.println("LoginSuccess!");
			} else {
				System.out.println("WrongPassword!");
			}
		} else {
			System.out.println("Wrong username!");
		}
	}

}
