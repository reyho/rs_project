package views;

import java.util.List;

import controllers.CourseController;
import javafx.collections.FXCollections;
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
import models.Course;
import models.Department;
import models.FacultyPersonnel;
import models.Semester;

public class CourseView extends VBox{
	
	TableView<Course> table;
	TableView<FacultyPersonnel> table2;
	TextField nameInput, requiredInput, nolecturesInput, noauditInput, nolabInput;
	ComboBox<Semester> semesterInput;
	ComboBox<Department> departmentInput;
	ComboBox<FacultyPersonnel> instructorsInput;
	Course course;
	CourseController cc = new CourseController();
	
	
	
	public CourseView() {
		TableColumn<Course, String> nameColumn = new TableColumn<>("Name");		
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		TableColumn<Course, Byte> requiredColumn = new TableColumn<>("Required");		
		requiredColumn.setCellValueFactory(new PropertyValueFactory<>("required"));
		
		TableColumn<Course, Byte> nolecturesColumn = new TableColumn<>("Nolectures");		
		nolecturesColumn.setCellValueFactory(new PropertyValueFactory<>("nolectures"));
		
		TableColumn<Course, Byte> noauditColumn = new TableColumn<>("Noaudit");		
		noauditColumn.setCellValueFactory(new PropertyValueFactory<>("noaudit"));
		
		TableColumn<Course, Byte> nolabColumn = new TableColumn<>("Nolab");		
		nolabColumn.setCellValueFactory(new PropertyValueFactory<>("nolab"));
		
		TableColumn<Course, Semester> semesterColumn = new TableColumn<>("Semester");		
		semesterColumn.setCellValueFactory(new PropertyValueFactory<>("semester"));
		
		TableColumn<Course, Department> departmentColumn = new TableColumn<>("Department");		
		departmentColumn.setCellValueFactory(new PropertyValueFactory<>("department"));
		
		TableColumn<FacultyPersonnel, String> instructorsColumn = new TableColumn<>("Name");		
		instructorsColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		nameInput = new TextField();
		nameInput.setPromptText("Name");
		
		
		requiredInput = new TextField();
		requiredInput.setPromptText("Required");
		
		
		nolecturesInput = new TextField();
		nolecturesInput.setPromptText("Nolectures");
		
		
		noauditInput = new TextField();
		noauditInput.setPromptText("Noaudit");
		
		
		nolabInput = new TextField();
		nolabInput.setPromptText("Nolab");
		
		semesterInput = new ComboBox<Semester>();
		for(Semester sem : cc.getAllSemesters()) {
			semesterInput.getItems().add(sem);
		}
		departmentInput = new ComboBox<Department>();
		for(Department dep : cc.getAllDepartments()) {
			departmentInput.getItems().add(dep);
		}
		instructorsInput = new ComboBox<FacultyPersonnel>();
		for(FacultyPersonnel ins : cc.getAllInstructors()) {
			instructorsInput.getItems().add(ins);
		}
				
		Button addButton = new Button("Add");
		addButton.setOnAction(e -> addButtonClicked());
		addButton.setMinWidth(50);
		Button deleteButton = new Button("Delete");
		deleteButton.setOnAction(e -> deleteButtonClicked());
		deleteButton.setMinWidth(50);
		
		Button addButton2 = new Button("Add");
		addButton2.setOnAction(e -> addButtonClicked2());
		addButton2.setMinWidth(50);
		Button deleteButton2 = new Button("Delete");
		deleteButton2.setOnAction(e -> deleteButtonClicked2());
		deleteButton2.setMinWidth(50);
		
		HBox hBox = new HBox();
		hBox.setPadding(new Insets(10, 10, 10, 10));
		hBox.setSpacing(10);
		hBox.getChildren().addAll(nameInput, requiredInput, nolecturesInput, 
				noauditInput, nolabInput, semesterInput, departmentInput, addButton, deleteButton);
		
		HBox hBox2 = new HBox();
		hBox2.setPadding(new Insets(10, 10, 10, 10));
		hBox2.setSpacing(10);
		hBox2.getChildren().addAll(instructorsInput, addButton2, deleteButton2);
		
		table2 = new TableView<>();
		table2.getColumns().add(instructorsColumn);
		
		table = new TableView<>(getCourse());
		table.getColumns().addAll(nameColumn, requiredColumn, nolecturesColumn, 
				noauditColumn, nolabColumn, semesterColumn, departmentColumn);
		table.setOnMouseClicked(e -> table2.setItems(getCourseInstructors()));
		
		this.getChildren().addAll(table, hBox, table2, hBox2);
	}
	private void deleteButtonClicked() {
		ObservableList<Course> allCourses, courseSelected;
		allCourses = table.getItems();
		courseSelected = table.getSelectionModel().getSelectedItems();
		cc.deleteCourse(courseSelected.get(0).getId());
		courseSelected.forEach(allCourses::remove);
	}
	
	private void deleteButtonClicked2() {
		ObservableList<Course> allCourses, courseSelected;
		allCourses = table.getItems();
		courseSelected = table.getSelectionModel().getSelectedItems();
		cc.deleteCourse(courseSelected.get(0).getId());
		courseSelected.forEach(allCourses::remove);
	}


	private void addButtonClicked() {
		Course course = cc.createCourse(nameInput.getText(),
				Byte.parseByte(requiredInput.getText()), Byte.parseByte(nolecturesInput.getText()),
				Byte.parseByte(noauditInput.getText()), Byte.parseByte(nolabInput.getText()), 
				semesterInput.getValue(), departmentInput.getValue());
		table.getItems().add(course);
		nameInput.clear();
	}
	
	private void addButtonClicked2() {
		Course course = cc.createCourse(nameInput.getText(),
				Byte.parseByte(requiredInput.getText()), Byte.parseByte(nolecturesInput.getText()),
				Byte.parseByte(noauditInput.getText()), Byte.parseByte(nolabInput.getText()), 
				semesterInput.getValue(), departmentInput.getValue());
		table.getItems().add(course);
		nameInput.clear();
	}


	public ObservableList<Course> getCourse(){
		//ObservableList<Course> courses = FXCollections.observableArrayList();
		// read entities from database
		ObservableList<Course> courses = cc.findCourseAll();
		return courses;
		
	}
	
	public ObservableList<FacultyPersonnel> getCourseInstructors(){
		//ObservableList<Course> courses = FXCollections.observableArrayList();
		// read entities from database
		ObservableList<Course> courseSelected;
		courseSelected = table.getSelectionModel().getSelectedItems();
		course = courseSelected.get(0);
			
		
		ObservableList<FacultyPersonnel> personnel = cc.findCourseInstructors(course);
		return personnel;
		
	}

}
