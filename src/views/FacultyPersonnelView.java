package views;

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
import models.Department;
import models.FacultyPersonnel;
import models.Role;

public class FacultyPersonnelView extends VBox{
	TableView<FacultyPersonnel> table;
	TextField nameInput, lastnameInput, usernameInput, passwordInput, titleInput;
	ComboBox<Role> roleInput;
	ComboBox<Department> departmentInput;
	FacultyPersonnelController fpc = new FacultyPersonnelController(); 
	
	
	@SuppressWarnings("unchecked")
	public FacultyPersonnelView(){
		this.setPadding(new Insets(15));
		
		TableColumn<FacultyPersonnel, String> nameColumn = new TableColumn<>("Name");		
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		TableColumn<FacultyPersonnel, String> lastnameColumn = new TableColumn<>("Lastname");		
		lastnameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));
		
		TableColumn<FacultyPersonnel, String> usernameColumn = new TableColumn<>("Username");		
		usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
		
		TableColumn<FacultyPersonnel, String> passwordColumn = new TableColumn<>("Password");		
		passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
		
		TableColumn<FacultyPersonnel, String> titleColumn = new TableColumn<>("Title");		
		titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
		
		TableColumn<FacultyPersonnel, Role> roleColumn = new TableColumn<>("Role");		
		roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
		
		TableColumn<FacultyPersonnel, Department> departmentColumn = new TableColumn<>("Department");		
		departmentColumn.setCellValueFactory(new PropertyValueFactory<>("department"));
		
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
		roleInput = new ComboBox<>();
		for(Role rol : fpc.getAllRoles()) {
			roleInput.getItems().add(rol);
		}
		departmentInput = new ComboBox<>();
		for(Department dep : fpc.getAllDepartments()) {
			departmentInput.getItems().add(dep);
		}
		
		Button addButton = new Button("Add");
		addButton.setOnAction(e -> addButtonClicked());
		addButton.setMinWidth(50);
		Button deleteButton = new Button("Delete");
		deleteButton.setOnAction(e -> deleteButtonClicked());
		deleteButton.setMinWidth(50);
		
		HBox hBox = new HBox(10);
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
		ObservableList<FacultyPersonnel> personnel = fpc.getAllPersonnel();
		return personnel;
		
	}

}
