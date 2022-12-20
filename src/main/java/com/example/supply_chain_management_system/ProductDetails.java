package com.example.supply_chain_management_system;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class ProductDetails {
    public TableView<Product> productTable;

    public Pane getAllProducts(){
        TableColumn id = new TableColumn("Id"); //name displayed on table
        id.setCellValueFactory(new PropertyValueFactory<>("id")); //id as specified in product class
        TableColumn name = new TableColumn("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn price = new TableColumn("Price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        ObservableList<Product> data = FXCollections.observableArrayList();
        data.add(new Product(1, "Lenovo", 84449));
        data.add(new Product(1, "ASUS", 99999));

        productTable = new TableView<>();
        productTable.setItems(data);
        productTable.getColumns().addAll(id, name, price);

        Pane tabelPane = new Pane();
        tabelPane.getChildren().addAll(productTable);
        return tabelPane;
    }
}