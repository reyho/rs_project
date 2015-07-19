package views;

import controllers.DepartmentController;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Department;
import models.Student;

public class DepartmentView extends VBox{
	
	TableView<Department> table;
	TextField nameInput, departmentchiefidInput;
	DepartmentController dc = new DepartmentController();
	
	public DepartmentView(){
		
		TableColumn<Department, String> nameColumn = new TableColumn<>("Name");		
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		TableColumn<Department, String> departmentchiefidColumn = new TableColumn<>("Department chief id");		
		departmentchiefidColumn.setCellValueFactory(new PropertyValueFactory<>("departmentchiefid"));
		
		nameInput = new TextField();
		nameInput.setPromptText("Name");
		
		departmentchiefidInput = new TextField();
		departmentchiefidInput.setPromptText("Department chief id");
		
		Button addButton = new Button("Add");
		addButton.setOnAction(e -> addButtonClicked());
		addButton.setMinWidth(50);
		Button deleteButton = new Button("Delete");
		deleteButton.setOnAction(e -> deleteButtonClicked());
		deleteButton.setMinWidth(50);
		
		HBox hBox = new HBox();
		hBox.setPadding(new Insets(10, 10, 10, 10));
		hBox.setSpacing(10);
		hBox.getChildren().addAll(nameInput, departmentchiefidInput, addButton, deleteButton);
		
		table = new TableView<>(getDepartments());
		table.getColumns().addAll(nameColumn, departmentchiefidColumn);
		
		this.getChildren().addAll(table, hBox);
		
	}
	public ObservableList<Department> getDepartments(){
		//ObservableList<Course> courses = FXCollections.observableArrayList();
		// read entities from database
		ObservableList<Department> department = dc.getAllDepartments();
		return department;
		
	}
	
	private void addButtonClicked() {
		Department department = dc.createDepartment(nameInput.getText(), departmentchiefidInput.getText());
				
		table.getItems().add(department);
		
	}
	
	private void deleteButtonClicked() {
		ObservableList<Department> allDepartments, departmentSelected;
		allDepartments = table.getItems();
		departmentSelected = table.getSelectionModel().getSelectedItems();
		dc.deleteDepartment(departmentSelected.get(0).getId());
		departmentSelected.forEach(allDepartments::remove);
	}

}
