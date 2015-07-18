package views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class BuildingView extends VBox{
	
	TableView<Product> table;
	TextField nameInput;
	
	
	
	public BuildingView() {
		TableColumn<Product, String> nameColumn = new TableColumn<>("Name");
		nameColumn.setMinWidth(200);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		nameInput = new TextField();
		nameInput.setPromptText("Name");
		nameInput.setMinWidth(100);
		
				
		Button addButton = new Button("Add");
		addButton.setOnAction(e -> addButtonClicked());
		Button deleteButton = new Button("Delete");
		deleteButton.setOnAction(e -> deleteButtonClicked());
		
		HBox hBox = new HBox();
		hBox.setPadding(new Insets(10, 10, 10, 10));
		hBox.setSpacing(10);
		hBox.getChildren().addAll(nameInput, addButton, deleteButton);

		
		table = new TableView<>(getProduct());
		table.getColumns().add(nameColumn);
		
		
		this.getChildren().addAll(table, hBox);
	}
	private void deleteButtonClicked() {
		ObservableList<Product> allProducts, productSelected;
		
		allProducts = table.getItems();
		productSelected = table.getSelectionModel().getSelectedItems();		
		productSelected.forEach(allProducts::remove);
	}


	private void addButtonClicked() {
		Product product = new Product();
		product.setName(nameInput.getText());
		
		table.getItems().add(product);
		nameInput.clear();
	}


	public ObservableList<Product> getProduct(){
		ObservableList<Product> products = FXCollections.observableArrayList();
		
		return products;
		
	}

}
