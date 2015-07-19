package views;

import controllers.CourseController;
import controllers.FacultyPersonnelController;
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
import models.Role;
import models.Semester;

public class FacultyPersonnelView extends VBox{
	TableView<FacultyPersonnel> table;
	TextField nameInput, lastnameInput, usernameInput, passwordInput, titleInput;
	ComboBox<Role> roleInput;
	ComboBox<Department> departmentInput;
	FacultyPersonnelController fpc = new FacultyPersonnelController(); 
	
	
	public FacultyPersonnelView(){
		TableColumn<FacultyPersonnel, String> nameColumn = new TableColumn<>("Name");		
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		TableColumn<FacultyPersonnel, String> lastnameColumn = new TableColumn<>("Lastname");		
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));
		
		TableColumn<FacultyPersonnel, String> usernameColumn = new TableColumn<>("Username");		
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
		
		TableColumn<FacultyPersonnel, String> passwordColumn = new TableColumn<>("Password");		
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
		
		TableColumn<FacultyPersonnel, String> titleColumn = new TableColumn<>("Title");		
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
		
		TableColumn<FacultyPersonnel, Role> roleColumn = new TableColumn<>("Role");		
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
		
		TableColumn<FacultyPersonnel, Department> departmentColumn = new TableColumn<>("Department");		
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("department"));
		
		nameInput = new TextField();
		nameInput.setPromptText("Name");
		
		lastnameInput = new TextField();
		lastnameInput.setPromptText("Last name");
		
		usernameInput = new TextField();
		usernameInput.setPromptText("User name");
		
		passwordInput = new TextField();
		passwordInput.setPromptText("Password");
		
		titleInput = new TextField();
		titleInput.setPromptText("Title");
		
		for(Role rol : fpc.getAllRoles()) {
			roleInput.getItems().add(rol);
		}
		
		for(Department dep : fpc.getAllDepartments()) {
			departmentInput.getItems().add(dep);
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
		hBox.getChildren().addAll(nameInput, lastnameInput, usernameInput, passwordInput, 
				titleInput, roleInput, departmentInput,addButton, deleteButton);
		
		table = new TableView<>(getPersonnel());
		table.getColumns().addAll(nameColumn, lastnameColumn, usernameColumn, 
				passwordColumn, titleColumn, roleColumn, departmentColumn);
		
		this.getChildren().addAll(table, hBox);
	}
	
	private void deleteButtonClicked() {
		ObservableList<FacultyPersonnel> allCourses, courseSelected;
		allCourses = table.getItems();
		courseSelected = table.getSelectionModel().getSelectedItems();
		fpc.deleteFacultyMember(courseSelected.get(0).getId());
		courseSelected.forEach(allCourses::remove);
	}
	
	private void addButtonClicked() {
		FacultyPersonnel personnel = fpc.createFacultyMember(usernameInput.getText(), passwordInput.getText(), 
				nameInput.getText(), lastnameInput.getText(), titleInput.getText(), 
				roleInput.getValue(), departmentInput.getValue());
				
		table.getItems().add(personnel);
		nameInput.clear();
	}
	
	public ObservableList<FacultyPersonnel> getPersonnel(){
		//ObservableList<Course> courses = FXCollections.observableArrayList();
		// read entities from database
		ObservableList<FacultyPersonnel> courses = fpc.getAllPersonnel();
		return courses;
		
	}

}
