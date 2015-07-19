package views;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

public class TimeTableView extends VBox {
	HBox dayLabels = new HBox();
	HBox timeSlotsClasses = new HBox();
	HBox filters = new HBox();
	
	VBox timeSlotLabels = new VBox();
	TimeTable timeTable = new TimeTable();
	

	public TimeTableView() {
		this.setStyle("-fx-background-color: lightgray;");
		this.setPrefSize(800, 600);
		this.setMinWidth(600);
		this.setMinHeight(400);
		
		// vertical partitoning
		dayLabels.prefHeightProperty().bind(this.heightProperty().divide(14));
		timeSlotsClasses.prefHeightProperty().bind(this.heightProperty().divide(14).multiply(12));
		filters.prefHeightProperty().bind(this.heightProperty().divide(14));
		// horizontal partitioning
		timeTable.prefWidthProperty().bind(timeSlotsClasses.widthProperty().divide(6).multiply(5));
		timeSlotLabels.prefWidthProperty().bind(timeSlotsClasses.widthProperty().divide(6));
		
		dayLabels.getChildren().addAll(getDayLabels());
		
		timeTable.getChildren().addAll(getTimeSlots());
		timeSlotLabels.getChildren().addAll(getTimeSlotLabels());
		
		// layout the scene.
		timeSlotsClasses.getChildren().addAll(timeSlotLabels, timeTable);
		HBox.setHgrow(timeTable, Priority.ALWAYS);
		
		
		this.getChildren().addAll(dayLabels, timeSlotsClasses, filters);
		/*
		stage.setScene(scene);
		stage.show(); */
	}
	
	private List<Label> getTimeSlots() {
		List<Label> list = new ArrayList<Label>();
		// controller gets timeslots
		int day = 3;
		float start = 4f;
		float duration = 5f;
		
		list.add(timeTable.getTimeSlotLabel("asdasd", Color.LIGHTGREY, day, start, duration));
		
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
		
		for(Label l : list) {
			l.prefWidthProperty().bind(dayLabels.widthProperty().divide(6));;
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
		
		int i = 0;
		for(Label l : dayL) {
			
			l.prefWidthProperty().bind(dayLabels.widthProperty().divide(6));
		}
		return dayL;
	}

		// a board on which you can place messages.
		class TimeTable extends Pane {
			
			TimeTable() { setId("TimeTable"); }

			Label post(String c, Color color) { 
				// choose a quote and style it.
				final Label label = new Label(c);
				label.setStyle("-fx-background-radius: 5; -fx-background-color: rgba("+(int)256*color.getRed()+", "+(int)256*color.getGreen()+", "+(int)256*color.getBlue()+", 0.5); -fx-text-fill: white; -fx-font: 18px 'Segoe Script'; -fx-padding:10;");
				label.setWrapText(true);
				label.setAlignment(Pos.CENTER);
				label.setTextAlignment(TextAlignment.CENTER);
				final DropShadow dropShadow = new DropShadow();
				label.setEffect(dropShadow);

				// Set label size and position - this is irrelevant
				//label.setMinSize(Label.USE_PREF_SIZE, Label.USE_PREF_SIZE);
				//label.setPrefSize(300, 75);
				
				// !! set position of the label !!
				//label.relocate(x, y);
				
				this.getChildren().add(label);

				return label;
			}
			
			Label getTimeSlotLabel(String c, Color color, int day, float start, float duration) {
				// choose a quote and style it.
				final Label label = new Label(c);
				label.setStyle("-fx-background-radius: 5; -fx-background-color: rgba(0, 100, 100, 0.5); -fx-text-fill: white; -fx-font: 14px 'Arial'; -fx-padding:10;");
				label.setWrapText(true);
				label.setAlignment(Pos.CENTER);
				label.setTextAlignment(TextAlignment.CENTER);
				final DropShadow dropShadow = new DropShadow();
				label.setEffect(dropShadow);
				
				label.prefWidthProperty().bind(timeTable.widthProperty().divide(5));;
				label.layoutXProperty().bind(timeTable.widthProperty().divide(5).multiply(day));
				
				label.prefHeightProperty().bind(timeTable.heightProperty().divide(12).multiply(duration));
				label.layoutYProperty().bind(timeTable.heightProperty().divide(12).multiply(start));

				// Set label size and position - this is irrelevant
				//label.setMinSize(Label.USE_PREF_SIZE, Label.USE_PREF_SIZE);
				//label.setPrefSize(300, 75);
				
				// !! set position of the label !!
				//label.relocate(x, y);
				
				//this.getChildren().add(label);

				return label;
			}
		}

		// records relative x and y coordinates.
		class Delta {
			double x, y;
		}
}
