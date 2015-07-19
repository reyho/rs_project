package views;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Pos;
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
	
	final TimeTable timeTable = new TimeTable();
	

	public TimeTableView() {
		this.setStyle("-fx-background-color: lightgray;");
		this.setPrefSize(800, 600);
		this.setMinWidth(400);
		this.setMinHeight(400);
		
		List<Label> dayL = new ArrayList<Label>(6);
		dayL.add(timeTable.post("Time\\Day", Color.BLUE, 0, 0));
		dayL.add(timeTable.post("Mon", Color.BLUE, 100, 0));
		dayL.add(timeTable.post("Tue", Color.BLUE, 200, 0));
		dayL.add(timeTable.post("Wed", Color.BLUE, 300, 0));
		dayL.add(timeTable.post("Th", Color.BLUE, 400, 0));
		dayL.add(timeTable.post("Fri", Color.BLUE, 500, 0));
		
		dayLabels.getChildren().addAll(dayL);
		
		
		final Label newQuote = timeTable.post("123", Color.BLUE, 50, 50);
		// bind dimensions !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		newQuote.prefWidthProperty().bind(timeTable.widthProperty().divide(3));;
		newQuote.layoutXProperty().bind(timeTable.widthProperty().divide(3));
		
		
		
		// layout the scene.
		timeSlotsClasses.getChildren().addAll(timeTable);
		HBox.setHgrow(timeTable, Priority.ALWAYS);
		this.getChildren().addAll(dayLabels, timeSlotsClasses, filters);
		

		
		/*
		stage.setScene(scene);
		stage.show(); */
	}
	
	// a board on which you can place messages.
		class TimeTable extends Pane {
			

			TimeTable() {
				setId("TimeTable");
			}

			Label post(String c, Color color, int x, int y) {
				// choose a quote and style it.
				final Label label = new Label(c);
				label.setStyle("-fx-background-radius: 5; -fx-background-color: rgba(0, 100, 100, 0.5); -fx-text-fill: white; -fx-font: 18px 'Segoe Script'; -fx-padding:10;");
				label.setWrapText(true);
				label.setAlignment(Pos.CENTER);
				label.setTextAlignment(TextAlignment.CENTER);
				final DropShadow dropShadow = new DropShadow();
				label.setEffect(dropShadow);

				// Set label size and position - this is irrelevant
				//label.setMinSize(Label.USE_PREF_SIZE, Label.USE_PREF_SIZE);
				//label.setPrefSize(300, 75);
				
				// !! set position of the label !!
				label.relocate(x, y);
				
				this.getChildren().add(label);

				return label;
			}
		}

		// records relative x and y coordinates.
		class Delta {
			double x, y;
		}
}
