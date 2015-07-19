package views;

import controllers.StudentController;
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
import models.Department;
import models.FacultyPersonnel;
import models.Role;
import models.Semester;
import models.Student;

public class StudentView extends VBox{
	
	TableView<Student> table;
	TextField nameInput, lastnameInput, studentidInput;
	ComboBox<Semester> semesterInput;
	ComboBox<Department> departmentInput;
	StudentController sc = new StudentController();
	
	public StudentView(){
		TableColumn<Student, String> nameColumn = new TableColumn<>("Name");		
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		TableColumn<Student, String> lastnameColumn = new TableColumn<>("Last Name");		
		lastnameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));
		
		TableColumn<Student, String> studentidColumn = new TableColumn<>("Studentid");		
		studentidColumn.setCellValueFactory(new PropertyValueFactory<>("studentid"));
		
		TableColumn<Student, Semester> semesterColumn = new TableColumn<>("Semester");		
		semesterColumn.setCellValueFactory(new PropertyValueFactory<>("semester"));
		
		TableColumn<Student, Department> departmentColumn = new TableColumn<>("Department");		
		departmentColumn.setCellValueFactory(new PropertyValueFactory<>("department"));
		
		nameInput = new TextField();
		nameInput.setPromptText("Name");
		
		lastnameInput = new TextField();
		lastnameInput.setPromptText("Last name");
		
		studentidInput = new TextField();
		studentidInput.setPromptText("Studentid");
		
		departmentInput = new ComboBox<>();
		for(Department dep : sc.getAllDepartments()) {
			departmentInput.getItems().add(dep);
		}
		
		semesterInput = new ComboBox<>();
		for(Semester rol : sc.getAllSemesters()) {
			semesterInput.getItems().add(rol);
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
		hBox.getChildren().addAll(nameInput, lastnameInput, studentidInput, 
				semesterInput, departmentInput, addButton, deleteButton);
		
		table = new TableView<>(getStudents());
		table.getColumns().addAll(nameColumn, lastnameColumn, studentidColumn, semesterColumn, departmentColumn);
		
		this.getChildren().addAll(table, hBox);
	}
	
	public ObservableList<Student> getStudents(){
		//ObservableList<Course> courses = FXCollections.observableArrayList();
		// read entities from database
		ObservableList<Student> students = sc.getAllStudents();
		return students;
		
	}
	
	private void deleteButtonClicked() {
		ObservableList<Student> allStudents, studentSelected;
		allStudents = table.getItems();
		studentSelected = table.getSelectionModel().getSelectedItems();
		sc.deleteStudent(studentSelected.get(0).getId());
		studentSelected.forEach(allStudents::remove);
	}
	
	private void addButtonClicked() {
		Student student = sc.createStudent(nameInput.getText(), lastnameInput.getText(), 
				studentidInput.getText(), semesterInput.getValue(), departmentInput.getValue());
				
		table.getItems().add(student);
		
	}

}
