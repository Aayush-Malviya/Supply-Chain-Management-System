package com.example.supply_chain_management_system;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class SupplyChain extends Application { //refractoring means renaming
    public static final int  width = 800, height = 600, headerBar = 50;
    Pane bodyPane = new Pane();
    //Creating header bar
    private GridPane headerBar(){
        TextField searchText = new TextField();
        Button searchButton = new Button("Search here");

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(bodyPane.getMinWidth(), headerBar-10);
        gridPane.setVgap(5); //setting vertical gap between controls
        gridPane.setHgap(5); //setting horizontal gap between controls
        gridPane.setAlignment(Pos.CENTER); //to set controls to center


        gridPane.add(searchText, 0, 0);
        gridPane.add(searchButton, 1, 0);
        return gridPane;
    }
    private GridPane loginPage(){
        Label emailLabel =  new Label("Email");
        Label passwordLabel =  new Label("Password");
        Label messageLabel = new Label("Enter Account Details..");
        TextField emailTextField = new TextField();
        PasswordField passwordField = new PasswordField();

        Button loginButton = new Button("Login");
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String email = emailTextField.getText();
                String password = passwordField.getText();
                messageLabel.setText(email + " $$ " + password);
            }
        });

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(bodyPane.getMinWidth(), bodyPane.getMinHeight());
        gridPane.setVgap(5); //setting vertical gap between controls
        gridPane.setHgap(5); //setting horizontal gap between controls
        gridPane.setStyle("-fx-background-color: #C0C0C0"); //setting background colour

        gridPane.setAlignment(Pos.CENTER); //to set controls to center

        //adding email box on panel
        gridPane.add(emailLabel, 0, 0); //x, y
        gridPane.add(emailTextField,1, 0 ); //x, y

        //adding password field on panel
        gridPane.add(passwordLabel, 0, 1); //x, y
        gridPane.add(passwordField, 1, 1); //x, y
        //adding button position
        gridPane.add(loginButton,0, 2);
        gridPane.add(messageLabel, 1,2);
        return gridPane;
    }
    private Pane createContent(){
        Pane root = new Pane();
        root.setPrefSize(width, height+headerBar);
        //we need to add all child to the root to run

        bodyPane.setMinSize(width, height);
        bodyPane.setTranslateY(headerBar); //to make start login page after headerBar

        bodyPane.getChildren().addAll(loginPage());
        root.getChildren().addAll(headerBar(), bodyPane);
        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {
        // FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());
        stage.setTitle("Supply Chain Management System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}