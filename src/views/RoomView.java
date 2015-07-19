package views;

import controllers.RoomController;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Building;
import models.Room;

public class RoomView extends VBox{
	
	TableView<Room> table;
	TextField nameInput, capacityInput;
	ComboBox<Building> buildingIdInput;
	RoomController rc = new RoomController();
	
	
	
	@SuppressWarnings("unchecked")
	public RoomView() {
		TableColumn<Room, String> nameColumn = new TableColumn<>("Name");
		nameColumn.setMinWidth(200);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		TableColumn<Room, Integer> capacityColumn = new TableColumn<>("Capacity");
		capacityColumn.setMinWidth(200);
		capacityColumn.setCellValueFactory(new PropertyValueFactory<>("capacity"));
		
		TableColumn<Room, Building> buildingColumn = new TableColumn<>("Building");
		buildingColumn.setMinWidth(200);
		buildingColumn.setCellValueFactory(new PropertyValueFactory<>("building"));
		
		nameInput = new TextField();
		nameInput.setPromptText("Name");
		nameInput.setMinWidth(100);
		
		capacityInput = new TextField();
		capacityInput.setPromptText("Capacity");
		capacityInput.setMinWidth(100);
		
		buildingIdInput = new ComboBox<>();
		buildingIdInput.setMinWidth(100);
		
		for(Building building : rc.getAllBuildings()) {
			buildingIdInput.getItems().add(building);
		}
		
				
		Button addButton = new Button("Add");
		addButton.setOnAction(e -> addButtonClicked());
		Button deleteButton = new Button("Delete");
		deleteButton.setOnAction(e -> deleteButtonClicked());
		
		HBox hBox = new HBox();
		hBox.setPadding(new Insets(10, 10, 10, 10));
		hBox.setSpacing(10);
		hBox.getChildren().addAll(nameInput, capacityInput, buildingIdInput, addButton, deleteButton);

		
		table = new TableView<>(getRoom());
		table.getColumns().addAll(nameColumn, capacityColumn, buildingColumn);
		
		
		this.getChildren().addAll(table, hBox);
	}
	private void deleteButtonClicked() {
		ObservableList<Room> allRooms, roomSelected;
		allRooms = table.getItems();
		roomSelected = table.getSelectionModel().getSelectedItems();
		rc.deleteRoom(roomSelected.get(0).getId());
		roomSelected.forEach(allRooms::remove);
	}


	private void addButtonClicked() {
		Room room = rc.createRoom(nameInput.getText(), Integer.parseInt(capacityInput.getText()), rc.getAllBuildings().get(0));
		table.getItems().add(room);
		nameInput.clear();
	}


	public ObservableList<Room> getRoom(){
		//ObservableList<Room> rooms = FXCollections.observableArrayList();
		// read entities from database
		ObservableList<Room> rooms = rc.findRoomAll();
		return rooms;
		
	}

}
