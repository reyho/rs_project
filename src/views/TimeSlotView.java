package views;

import controllers.TimeSlotController;
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
import models.Group;
import models.Room;
import models.TimeSlot;

public class TimeSlotView extends VBox{
	
	TableView<TimeSlot> table;
	TextField starttimeInput, lengthInput, dayInput;
	ComboBox<Room> roomInput;
	ComboBox<Group> groupInput;
	TimeSlotController tsc = new TimeSlotController();
	
	@SuppressWarnings("unchecked")
	public TimeSlotView(){
		this.setPadding(new Insets(15));
		
		TableColumn<TimeSlot, Float> starttimeColumn = new TableColumn<>("Starttime");		
		starttimeColumn.setCellValueFactory(new PropertyValueFactory<>("starttime"));
		
		TableColumn<TimeSlot, Float> lengthColumn = new TableColumn<>("Length");		
		lengthColumn.setCellValueFactory(new PropertyValueFactory<>("length"));
		
		TableColumn<TimeSlot, Byte> dayColumn = new TableColumn<>("Day");		
		dayColumn.setCellValueFactory(new PropertyValueFactory<>("day"));
		
		TableColumn<TimeSlot, Room> roomColumn = new TableColumn<>("Room");		
		roomColumn.setCellValueFactory(new PropertyValueFactory<>("room"));
		
		TableColumn<TimeSlot, Group> groupColumn = new TableColumn<>("group");		
		groupColumn.setCellValueFactory(new PropertyValueFactory<>("group"));
		
		starttimeInput = new TextField();
		starttimeInput.setPromptText("Start Time");
		
		lengthInput = new TextField();
		lengthInput.setPromptText("Length");
		
		dayInput = new TextField();
		dayInput.setPromptText("Day");
		
		roomInput = new ComboBox<>();
		for(Room roo : tsc.getAllRooms()) {
			roomInput.getItems().add(roo);
		}
		
		groupInput = new ComboBox<>();
		for(Group gro : tsc.getAllGroups()) {
			groupInput.getItems().add(gro);
		}
		
		Button addButton = new Button("Add");
		addButton.setOnAction(e -> addButtonClicked());
		addButton.setMinWidth(50);
		Button deleteButton = new Button("Delete");
		deleteButton.setOnAction(e -> deleteButtonClicked());
		deleteButton.setMinWidth(50);
		
		HBox hBox = new HBox(10);
		hBox.setPadding(new Insets(10, 10, 10, 10));
		hBox.setSpacing(10);
		hBox.getChildren().addAll(starttimeInput, lengthInput, dayInput, roomInput, groupInput, addButton, deleteButton);
		
		table = new TableView<>(getTimeSlots());
		table.getColumns().addAll(starttimeColumn, lengthColumn, dayColumn, 
				roomColumn, groupColumn);
		
		this.getChildren().addAll(table, hBox);
		
	}
	
	public ObservableList<TimeSlot> getTimeSlots(){
		//ObservableList<Course> courses = FXCollections.observableArrayList();
		// read entities from database
		ObservableList<TimeSlot> timeslot = tsc.findTimeSlotsAll();
		return timeslot;
		
	}
	
	private void addButtonClicked() {
		TimeSlot timeslot = tsc.createTimeSlot(Float.parseFloat(starttimeInput.getText()), Float.parseFloat(lengthInput.getText()), 
				Byte.parseByte(dayInput.getText()), roomInput.getValue(), groupInput.getValue());
		table.getItems().add(timeslot);
		
	}
	
	private void deleteButtonClicked() {
		ObservableList<TimeSlot> allTimeslots, timeslotSelected;
		allTimeslots = table.getItems();
		timeslotSelected = table.getSelectionModel().getSelectedItems();
		tsc.deleteTimeSlot(timeslotSelected.get(0).getId());
		timeslotSelected.forEach(allTimeslots::remove);
	}

}
