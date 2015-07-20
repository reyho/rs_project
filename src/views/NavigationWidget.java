package views;

import controllers.AppMain;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import models.FacultyPersonnel;

public class NavigationWidget extends VBox{
	ListView<String> listPublic = new ListView<String>();
	ListView<String> listFaculty = new ListView<String>();
	ListView<String> listAdmin  = new ListView<String>();
	AppMain appMain;
	
	public NavigationWidget(AppMain app, FacultyPersonnel user) {
		appMain = app;
		// Set style of nav pane
		this.setPadding(new Insets(15));
		
		//Available to all
		Label label1 = new Label("Public Section");
		// item that can be selected in the public list
		ObservableList<String> itemsPublic =FXCollections.observableArrayList (
		    "TimeTable", "View Groups");
		listPublic.setItems(itemsPublic);
		listPublic.setPrefHeight(50);
		listPublic.setOnMouseClicked(e->{
			handleNavPublic(listPublic.getSelectionModel().getSelectedIndex());
			// clear the selection in other lists
			listFaculty.getSelectionModel().clearSelection();
			listAdmin.getSelectionModel().clearSelection();
		});
		
		// Available only to Faculty members
		Label label2 = new Label("Faculty Section");
		// item that can be selected in the faculty list
		ObservableList<String> itemsFaculty =FXCollections.observableArrayList (
		    "Single", "Double", "Suite", "Family App");
		listFaculty.setItems(itemsFaculty);
		listFaculty.setPrefHeight(200);
		listFaculty.setOnMouseClicked(e->{
			handleNavFaculty(listFaculty.getSelectionModel().getSelectedIndex());
			// clear the selection in other lists
			listPublic.getSelectionModel().clearSelection();
			listAdmin.getSelectionModel().clearSelection();
		});
		
		
		// Available only to Dean
		Label label3 = new Label("Admin Section");
		// item that can be selected in the admin list
		ObservableList<String> itemsAdmin =FXCollections.observableArrayList (
		    "Buildings", "Rooms", "Courses", "FacultyPersonnel", "Students", "Groups", "TimeSlots", "Departments", 
		    "Reservations");
		listAdmin.setItems(itemsAdmin);
		listAdmin.setPrefHeight(200);
		listAdmin.setOnMouseClicked(e->{
			handleNavAdmin(listAdmin.getSelectionModel().getSelectedIndex());
			// clear the selection in other lists
			listPublic.getSelectionModel().clearSelection();
			listFaculty.getSelectionModel().clearSelection();
		});
		
		// All nav lists are created, but only the ones relevant in regards
		// to current user privileges will be loaded
		
		// all users can see the public section
		this.getChildren().addAll(label1, listPublic);
		// if the app user is defined, he is a faculty member and not a guest
		if(appMain.getUser() != null ) {
			this.getChildren().addAll(label2, listFaculty);
			// if the current user has high enough privilege, he is admin
			if(appMain.getUser().getRole().getPrivilege() > 25) {
				this.getChildren().addAll(label3, listAdmin);
			}
		}
		
		
	}

	private void handleNavPublic(int selectedIndex) {
		// Load views corresponding to list labels
		switch (selectedIndex) {
		case 0:
			appMain.setContentPane(new TimeTableView());
			break;
		case 1:
			
			break;

		default:
			break;
		}
	}

	private void handleNavFaculty(int selectedIndex) {
		// Load views corresponding to list labels
		switch (selectedIndex) {
		case 0:
			
			break;
		case 1:
			
			break;

		default:
			break;
		}
	}

	private void handleNavAdmin(int index) {
		// Load views corresponding to list labels
		switch (index) {
		case 0:
			appMain.setContentPane(new BuildingView());
			break;
		case 1:
			appMain.setContentPane(new RoomView());
			break;
			
		case 2:
			appMain.setContentPane(new CourseView());
			break;
		case 3:
			appMain.setContentPane(new FacultyPersonnelView());
			break;
		case 4:
			appMain.setContentPane(new StudentView());
			break;
		case 5:
			appMain.setContentPane(new GroupView());
			break;
		case 6:
			appMain.setContentPane(new TimeSlotView());
			break;
		case 7:
			appMain.setContentPane(new DepartmentView());
			break;
		case 8:
			appMain.setContentPane(new ReservationView());
			break;
		default:
			break;
		}
	}
}
