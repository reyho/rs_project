package views;

import java.time.ZoneId;
import java.util.Date;

import controllers.ReservationController;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Course;
import models.FacultyPersonnel;
import models.Reservation;
import models.Room;
import models.Semester;

public class ReservationView extends VBox{
	
	TableView<Reservation> table;
	TextField typeInput, starttimeInput, endtimeInput;
	ComboBox<FacultyPersonnel> personnelInput;
	ComboBox<Room> roomInput;
	DatePicker begindateInput = new DatePicker();
	Date date;
	ReservationController resc = new ReservationController();
	
	public ReservationView(){
		
		TableColumn<Reservation, String> typeColumn = new TableColumn<>("Type");		
		typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
		
		TableColumn<Reservation, Float> starttimeColumn = new TableColumn<>("Start time");		
		starttimeColumn.setCellValueFactory(new PropertyValueFactory<>("starttime"));
		
		TableColumn<Reservation, Float> endtimeColumn = new TableColumn<>("End time");		
		endtimeColumn.setCellValueFactory(new PropertyValueFactory<>("endtime"));
		
		TableColumn<Reservation, Date> begindatetimeColumn = new TableColumn<>("Begin datetime");		
		begindatetimeColumn.setCellValueFactory(new PropertyValueFactory<>("begindatetime"));
		
		TableColumn<Reservation, FacultyPersonnel> reservedByColumn = new TableColumn<>("Reserved By");		
		reservedByColumn.setCellValueFactory(new PropertyValueFactory<>("reservedBy"));
		
		TableColumn<Reservation, Room> roomColumn = new TableColumn<>("Room");		
		roomColumn.setCellValueFactory(new PropertyValueFactory<>("room"));
		
		typeInput = new TextField();
		typeInput.setPromptText("Type");
		
		starttimeInput = new TextField();
		starttimeInput.setPromptText("Start time");
		
		endtimeInput = new TextField();
		endtimeInput.setPromptText("End time");
		
		personnelInput = new ComboBox<>();
		for(FacultyPersonnel per : resc.getAllPersonnel()) {
			personnelInput.getItems().add(per);
		}
		
		roomInput = new ComboBox<>();
		for(Room roo : resc.getAllRooms()) {
			roomInput.getItems().add(roo);
		}
		
		Button addButton = new Button("Add");
		addButton.setOnAction(e -> addButtonClicked());
		addButton.setMinWidth(50);
		Button deleteButton = new Button("Delete");
		deleteButton.setOnAction(e -> deleteButtonClicked());
		deleteButton.setMinWidth(50);
		
		HBox hBox = new HBox();
		hBox.setPadding(new Insets(10, 10, 10, 10));
		hBox.setSpacing(10);
		hBox.getChildren().addAll(typeInput, starttimeInput, endtimeInput, begindateInput, 
				personnelInput, roomInput, addButton, deleteButton);
		
		table = new TableView<>(getReservations());
		table.getColumns().addAll(typeColumn, starttimeColumn, endtimeColumn, 
				begindatetimeColumn, reservedByColumn, roomColumn);
		
		this.getChildren().addAll(table, hBox);
		
	}
	
	public ObservableList<Reservation> getReservations(){
		//ObservableList<Course> courses = FXCollections.observableArrayList();
		// read entities from database
		ObservableList<Reservation> reservation = resc.findAllReservations();
		return reservation;
		
	}
	
	private void addButtonClicked() {
		Reservation reservation = resc.createReservation(typeInput.getText(), Float.parseFloat(starttimeInput.getText()), 
				Float.parseFloat(endtimeInput.getText()), 
				date = Date.from(begindateInput.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()), 
				personnelInput.getValue(), roomInput.getValue());
		table.getItems().add(reservation);
		
	}
	
	private void deleteButtonClicked() {
		ObservableList<Reservation> allReservations, courseSelected;
		allReservations = table.getItems();
		courseSelected = table.getSelectionModel().getSelectedItems();
		resc.deleteReservation(courseSelected.get(0).getId());
		courseSelected.forEach(allReservations::remove);
	}

}
