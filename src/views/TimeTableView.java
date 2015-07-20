package views;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import controllers.TimeTableController;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import models.FacultyPersonnel;
import models.Room;
import models.Semester;
import models.TimeSlot;

public class TimeTableView extends VBox {
	HBox dayLabels = new HBox();
	HBox timeSlotsClasses = new HBox();
	HBox filters = new HBox();

	VBox timeSlotLabels = new VBox();
	TimeTable timeTable = new TimeTable();
	TimeTableController ttc = new TimeTableController();

	ComboBox<Room> roomsInput;
	ComboBox<Semester> semesterInput;
	ComboBox<FacultyPersonnel> personnelInput;
	Button button = new Button("Filter");
	

	public TimeTableView() {
		this.setStyle("-fx-background-color: lightgray;");
		this.setPrefSize(1000, 800);
		this.setMinWidth(600);
		this.setMinHeight(400);

		// vertical partitoning
		dayLabels.prefHeightProperty().bind(this.heightProperty().divide(14));
		timeSlotsClasses.prefHeightProperty().bind(this.heightProperty().divide(14).multiply(12));
		filters.prefHeightProperty().bind(this.heightProperty().divide(14));
		// horizontal partitioning
		timeTable.prefWidthProperty().bind(timeSlotsClasses.widthProperty().divide(6).multiply(5));
		timeSlotLabels.prefWidthProperty().bind(timeSlotsClasses.widthProperty().divide(6));
		
		// Static layout elements
		dayLabels.getChildren().addAll(getDayLabels());
		timeSlotLabels.getChildren().addAll(getTimeSlotLabels());
		
		
		// !! TimeTable Pane !!
		//timeTable.getChildren().addAll(getTimeSlots());
		
		
		// layout the scene.
		timeSlotsClasses.getChildren().addAll(timeSlotLabels, timeTable);
		HBox.setHgrow(timeTable, Priority.ALWAYS);

		// Set up filter controls
		roomsInput = new ComboBox<>();
		roomsInput.setItems(ttc.getAllRooms());

		semesterInput = new ComboBox<>();
		semesterInput.setItems(ttc.getAllSemesters());

		personnelInput = new ComboBox<>();
		personnelInput.setItems(ttc.getAllInstructors());
		
		button.setOnAction(e->{
			Room r = roomsInput.getValue();
			Semester s = semesterInput.getValue();
			FacultyPersonnel p = personnelInput.getValue(); 
			timeTable.getChildren().clear();
			timeTable.getChildren().addAll(getFilteredTimeSlots(r, s, p));
		});

		filters.getChildren().addAll(roomsInput, semesterInput, personnelInput, button);

		this.getChildren().addAll(filters, dayLabels, timeSlotsClasses);
		/*
		 * stage.setScene(scene); stage.show();
		 */
	}

	private List<Label> getFilteredTimeSlots(Room r, Semester s, FacultyPersonnel p) {
		List<TimeSlot> timeSlots = ttc.filterTimeSlots(r, s, p);
		List<Label> list =  new ArrayList<Label>();
		
		for(TimeSlot ts : timeSlots) {
			list.add(timeTable.getTimeSlotLabel(ts));
		}
		
		return  list;
	}

	private List<Label> getTimeSlots() {

		// controller gets timeslots
		TimeTableController tc = new TimeTableController();
		List<TimeSlot> tSlots = tc.getAllDepartments();

		List<Label> list = new ArrayList<Label>();
		for (TimeSlot ts : tSlots) {
			list.add(timeTable.getTimeSlotLabel(ts));
		}

		return list;
	}

	private List<Label> getTimeSlotLabels() {
		List<Label> list = new ArrayList<Label>(6);
		list.add(timeTable.post("08h - 09h", Color.LIGHTGREY));
		list.add(timeTable.post("09h - 10h", Color.LIGHTGREY));
		list.add(timeTable.post("10h - 11h", Color.LIGHTGREY));
		list.add(timeTable.post("11h - 12h", Color.LIGHTGREY));
		list.add(timeTable.post("12h - 13h", Color.LIGHTGREY));
		list.add(timeTable.post("13h - 14h", Color.LIGHTGREY));
		list.add(timeTable.post("14h - 15h", Color.LIGHTGREY));
		list.add(timeTable.post("15h - 16h", Color.LIGHTGREY));
		list.add(timeTable.post("16h - 17h", Color.LIGHTGREY));
		list.add(timeTable.post("17h - 18h", Color.LIGHTGREY));
		list.add(timeTable.post("18h - 19h", Color.LIGHTGREY));
		list.add(timeTable.post("19h - 20h", Color.LIGHTGREY));

		for (Label l : list) {
			l.prefWidthProperty().bind(timeSlotsClasses.widthProperty().divide(6));
			l.prefHeightProperty().bind(timeSlotsClasses.heightProperty().divide(12));
		
		}
		return list;
	}

	private List<Label> getDayLabels() {
		List<Label> dayL = new ArrayList<Label>(6);
		Label l1 = timeTable.post("Time\\Day", Color.LIGHTGREY);
		l1.setWrapText(false);
		dayL.add(l1);
		dayL.add(timeTable.post("Mon", Color.LIGHTGREY));
		dayL.add(timeTable.post("Tue", Color.LIGHTGREY));
		dayL.add(timeTable.post("Wed", Color.RED));
		dayL.add(timeTable.post("Th", Color.LIGHTGREY));
		dayL.add(timeTable.post("Fri", Color.LIGHTGREY));

		for (Label l : dayL) {

			l.prefWidthProperty().bind(dayLabels.widthProperty().divide(6));
		}
		return dayL;
	}

	// a board on which you can place messages.
	class TimeTable extends Pane {

		TimeTable() {
			setId("TimeTable");
		}

		Label post(String c, Color color) {
			final Label label = new Label(c);
			label.setStyle("-fx-background-radius: 5; -fx-background-color: " + "rgba(" + (int) 256 * color.getRed()
					+ ", " + (int) 256 * color.getGreen() + ", " + (int) 256 * color.getBlue() + ", 0.5);"
					+ " -fx-text-fill: white; -fx-font: 13px 'Helvetica'; -fx-padding:10;");
			label.setWrapText(true);
			label.setAlignment(Pos.CENTER);
			label.setTextAlignment(TextAlignment.CENTER);
			final DropShadow dropShadow = new DropShadow();
			label.setEffect(dropShadow);
			this.getChildren().add(label);

			return label;
		}

		Label getTimeSlotLabel(TimeSlot ts) {
			
			Color color = getColorByType(ts.getGroup().getType());
			
			final Label label = new Label(ts.getGroup().getCourse().getName() + "\n" 
					+ ts.getRoom() + " | " + ts.getGroup().getType());
			label.setStyle("-fx-background-radius: 5; -fx-background-color: " + "rgba(" + (int) 256 * color.getRed()
					+ ", " + (int) 256 * color.getGreen() + ", " + (int) 256 * color.getBlue() + ", 0.5);"
					+ " -fx-text-fill: white; -fx-font: 13px 'Helvetica'; -fx-padding:10;");
			label.setWrapText(true);
			label.setAlignment(Pos.CENTER);
			label.setTextAlignment(TextAlignment.CENTER);
			final DropShadow dropShadow = new DropShadow();
			label.setEffect(dropShadow);

			label.prefWidthProperty().bind(timeTable.widthProperty().divide(5));
			label.layoutXProperty().bind(timeTable.widthProperty().divide(5).multiply(ts.getDay()));

			label.prefHeightProperty().bind(timeTable.heightProperty().divide(12).multiply(ts.getLength()));
			label.layoutYProperty().bind(timeTable.heightProperty().divide(12).multiply(ts.getStarttime()));

			return label;
		}

		private Color getColorByType(String type) {
			if(type.equals("AV")) {
				return Color.LIGHTBLUE;
			}
			if(type.equals("LV")) {
				return Color.DARKORANGE;
			}
			if(type.equals("P")) {
				return Color.LIGHTGREEN;
			}
			return Color.ANTIQUEWHITE;
		}
	}
}
