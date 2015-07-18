package views;

import controllers.BuildingsController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Building;

public class BuildingView extends VBox{
	
	TableView<Building> table;
	TextField nameInput;
	BuildingsController bc = new BuildingsController();
	
	
	
	public BuildingView() {
		TableColumn<Building, String> nameColumn = new TableColumn<>("Name");
		nameColumn.setMinWidth(200);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		nameInput = new TextField();
		nameInput.setPromptText("Name");
		nameInput.setMinWidth(100);
		
				
		Button addButton = new Button("Add");
		addButton.setOnAction(e -> addButtonClicked());
		Button deleteButton = new Button("Delete");
		deleteButton.setOnAction(e -> deleteButtonClicked());
		
		HBox hBox = new HBox();
		hBox.setPadding(new Insets(10, 10, 10, 10));
		hBox.setSpacing(10);
		hBox.getChildren().addAll(nameInput, addButton, deleteButton);

		
		table = new TableView<>(getBuilding());
		table.getColumns().add(nameColumn);
		
		
		this.getChildren().addAll(table, hBox);
	}
	private void deleteButtonClicked() {
		ObservableList<Building> allBuildings, buildingSelected;
		allBuildings = table.getItems();
		buildingSelected = table.getSelectionModel().getSelectedItems();
		bc.deleteBuilding(buildingSelected.get(0).getId());
		buildingSelected.forEach(allBuildings::remove);
	}


	private void addButtonClicked() {
		Building building = bc.createBuilding(nameInput.getText());
		table.getItems().add(building);
		nameInput.clear();
	}


	public ObservableList<Building> getBuilding(){
		//ObservableList<Building> buildings = FXCollections.observableArrayList();
		// read entities from database
		ObservableList<Building> buildings = bc.findBuildingAll();
		return buildings;
		
	}

}
