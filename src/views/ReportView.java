package views;


import controllers.ReportController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ReportView extends VBox{
	ReportController rc = new ReportController();
	Button generateReportButton = new Button("Generate report");
	
	public ReportView() {
		this.setPadding(new Insets(15));
		Label help = new Label("Here you can generate the report for your monthly activities. The report is generated as an HTML file in the app root directory.");	
		help.setStyle(" -fx-text-fill: black; -fx-font: 14px 'Helvetica'; -fx-padding:4;");
		
		generateReportButton.setOnAction(e->{
			rc.generateReport();
		});
		
		this.getChildren().addAll(help, generateReportButton);
	}
}
