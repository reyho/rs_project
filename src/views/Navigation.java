package views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import models.FacultyPersonnel;

public class Navigation extends VBox{
	ListView<String> listPublic = new ListView<String>();
	ListView<String> listFaculty = new ListView<String>();
	ListView<String> listAdmin  = new ListView<String>();
	
	public Navigation(FacultyPersonnel user) {
		// Avaliable to all
		Label label1 = new Label("Public Section");
		
		ObservableList<String> itemsPublic =FXCollections.observableArrayList (
		    "Raspored", "Grupe");
		listPublic.setItems(itemsPublic);
		
		listPublic.setOnMouseClicked(e->{
			System.out.println("Selected " + listPublic.getSelectionModel().getSelectedIndex());
			listFaculty.getSelectionModel().clearSelection();
			listAdmin.getSelectionModel().clearSelection();
		});
		listPublic.setPrefHeight(50);
		
		// Avaliable only to Faculty members
		Label label2 = new Label("Faculty Section");
		
		ObservableList<String> itemsFaculty =FXCollections.observableArrayList (
		    "Single", "Double", "Suite", "Family App");
		listFaculty.setItems(itemsFaculty);
		
		listFaculty.setOnMouseClicked(e->{
			System.out.println("Selected " + listFaculty.getSelectionModel().getSelectedIndex());
			listPublic.getSelectionModel().clearSelection();
			listAdmin.getSelectionModel().clearSelection();
		});
		listFaculty.setPrefHeight(200);
		
		// Avaliable only to Dean
		Label label3 = new Label("Admin Section");
		
		ObservableList<String> itemsAdmin =FXCollections.observableArrayList (
		    "Single", "Double", "Suite", "Family App");
		listAdmin.setItems(itemsAdmin);
		
		listAdmin.setOnMouseClicked(e->{
			System.out.println("Selected " + listAdmin.getSelectionModel().getSelectedIndex());
			listPublic.getSelectionModel().clearSelection();
			listFaculty.getSelectionModel().clearSelection();
		});
		listAdmin.setPrefHeight(200);
		
		this.getChildren().addAll(label1, listPublic, label2, listFaculty, label3, listAdmin);
	}
}
