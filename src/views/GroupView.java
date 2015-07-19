package views;

import controllers.GroupController;
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
import models.Course;
import models.Department;
import models.FacultyPersonnel;
import models.Group;
import models.Semester;
import models.Student;

public class GroupView extends VBox{
	
	TableView<Group> table;
	TableView<Student> table2;
	TextField typeInput, nameInput;
	ComboBox<FacultyPersonnel> personnelInput;
	ComboBox<Course> courseInput;
	ComboBox<Student> studentInput;
	Group group;
	ObservableList<Group> groupSelected2;
	GroupController gc = new GroupController();
	
	public GroupView(){
		
		TableColumn<Group, String> typeColumn = new TableColumn<>("Type");		
		typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
		
		TableColumn<Group, String> nameColumn = new TableColumn<>("Name");		
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		TableColumn<Group, FacultyPersonnel> facultyPersonnelColumn = new TableColumn<>("Faculty Personnel");		
		facultyPersonnelColumn.setCellValueFactory(new PropertyValueFactory<>("instructor"));
		
		TableColumn<Group, Course> courseColumn = new TableColumn<>("Course");		
		courseColumn.setCellValueFactory(new PropertyValueFactory<>("course"));
		
		TableColumn<Student, Course> studentColumn = new TableColumn<>("Student");		
		studentColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		typeInput = new TextField();
		typeInput.setPromptText("Type");
		
		nameInput = new TextField();
		nameInput.setPromptText("Name");
		
		personnelInput = new ComboBox<>();
		for(FacultyPersonnel per : gc.getAllInstructors()) {
			personnelInput.getItems().add(per);
		}
		
		courseInput = new ComboBox<>();
		for(Course cou : gc.getAllCourses()) {
			courseInput.getItems().add(cou);
		}
		
		studentInput = new ComboBox<>();
		for(Student stu : gc.getAllStudents()) {
			studentInput.getItems().add(stu);
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
		hBox.getChildren().addAll(typeInput, nameInput, personnelInput, 
				courseInput, addButton, deleteButton);
		
		HBox hBox2 = new HBox();
		hBox2.setPadding(new Insets(10, 10, 10, 10));
		hBox2.setSpacing(10);
		hBox2.getChildren().addAll(studentInput, addButton2, deleteButton2);
		
		table2 = new TableView<>();
		table2.getColumns().add(studentColumn);
		
		table = new TableView<>(getGroup());
		table.getColumns().addAll(typeColumn, nameColumn, facultyPersonnelColumn, courseColumn);
		table.setOnMouseClicked(e -> {table2.setItems(getGroupStudents());
				groupSelected2 = table.getSelectionModel().getSelectedItems();
				
				});
		
		this.getChildren().addAll(table, hBox, table2, hBox2);
		
	}
	
	public ObservableList<Group> getGroup(){
		//ObservableList<Course> courses = FXCollections.observableArrayList();
		// read entities from database
		ObservableList<Group> groups = gc.findGroupAll();
		return groups;
		
	}
	
	public ObservableList<Student> getGroupStudents(){
		//ObservableList<Course> courses = FXCollections.observableArrayList();
		// read entities from database
		ObservableList<Group> groupSelected;
		groupSelected = table.getSelectionModel().getSelectedItems();
		group = groupSelected.get(0);
			
		
		ObservableList<Student> student = gc.findGroupStudents(group);
		return student;
		
	}
	
	private void addButtonClicked() {
		Group group = gc.createGroup(typeInput.getText(), nameInput.getText(), personnelInput.getValue(),
				courseInput.getValue());
		table.getItems().add(group);
		nameInput.clear();
	}
	
	private void addButtonClicked2() {
		
		
		Student student = studentInput.getValue();
		table2.getItems().add(student);
		gc.setStudent(groupSelected2.get(0), table2.getItems());
		
	}
	
	private void deleteButtonClicked() {
		ObservableList<Group> allGroups, groupSelected;
		allGroups = table.getItems();
		groupSelected = table.getSelectionModel().getSelectedItems();
		gc.deleteGroup(groupSelected.get(0).getId());
		groupSelected.forEach(allGroups::remove);
	}
	
	private void deleteButtonClicked2() {
		ObservableList<Student> allStudents, studentSelected;
		allStudents = table2.getItems();
		studentSelected = table2.getSelectionModel().getSelectedItems();
		gc.deleteGroup(studentSelected.get(0).getId());
		studentSelected.forEach(allStudents::remove);
		gc.setStudent(groupSelected2.get(0), table2.getItems());
	}

}
