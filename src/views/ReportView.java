package views;


import controllers.ReportController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ReportView extends VBox{
	ReportController rc = new ReportController();
	Button generateReportButton = new Button("Generate report");
	
	public ReportView() {
		
		Label help = new Label("Here you can generate the report for your monthly activities. The report is generated as an HTML file in the app root directory.");	
		
		generateReportButton.setOnAction(e->{
			rc.generateReport();
		});
		
		this.getChildren().addAll(help, generateReportButton);
	}
}
