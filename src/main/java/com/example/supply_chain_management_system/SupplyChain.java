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
    public static final int  width = 800, height = 600, headerBar = 60;
    Pane bodyPane = new Pane();
    Login login = new Login();
    ProductDetails productDetails = new ProductDetails();

    Button globalLoginButton;
    Label customerEmailLabel = null;
    String customerEmail = null;
    //Creating header bar
    private GridPane headerBar(){
        TextField searchText = new TextField();
        Button searchButton = new Button("Search here");
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String productName = searchText.getText();
                productDetails.getProductsByName(productName);
                //clear body & put this new pane in the body
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(productDetails.getProductsByName(productName));
            }
        });

        //adding
        globalLoginButton = new Button("Log In");  //login button
        globalLoginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(loginPage());
                globalLoginButton.setVisible(false); //hiding button
            }
        });

        //---------------------------------------new code---------------------------------------------
        Button signUpButton = new Button("Sign Up"); //sign up button
        signUpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(signUpPage());
                signUpButton.setVisible(false);
            }
        });
        //----------------------------------------new code--------------------------------------------

        customerEmailLabel = new Label("Welcome User");
        //adding search bar & button
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(bodyPane.getMinWidth(), headerBar-10);
        gridPane.setVgap(5); //setting vertical gap between controls
        gridPane.setHgap(5); //setting horizontal gap between controls
        gridPane.setAlignment(Pos.CENTER); //to set controls to center

        gridPane.add(searchText, 0, 0);
        gridPane.add(searchButton, 1, 0);
        gridPane.add(globalLoginButton, 60, 0 );
        gridPane.add(customerEmailLabel, 61, 0);
        gridPane.add(signUpButton, 60,1); //new code
        return gridPane;
    }

    private GridPane signUpPage(){   //new code function
        Label emailLabel =  new Label("Email");
        Label passwordLabel =  new Label("Password");
        Label firstNameLabel = new Label("First Name");
        Label lastNameLabel = new Label("Last Name");
        Label addressLabel = new Label("Address");
        Label mobileLabel = new Label("Mobile");

        Label messageLabel = new Label("Enter Account Details..");
        TextField emailTextField = new TextField();
        PasswordField passwordField = new PasswordField();
        TextField firstNameTextField = new TextField();
        TextField lastNameTextField = new TextField();
        TextField addressTextField = new TextField();
        TextField mobileTextField = new TextField();

        Button signUpButton = new Button("Create Account");
        signUpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String email = emailTextField.getText();
                String password = passwordField.getText();
                String firstName = firstNameTextField.getText();
                String lastName = lastNameTextField.getText();
                String address = addressTextField.getText();
                String mobile = mobileTextField.getText();

                if (emailTextField.getText().isEmpty())
                    messageLabel.setText("Email cannot be empty");

                else if (passwordField.getText().isEmpty())
                    messageLabel.setText("Password Field cannot be empty");

                else if (firstNameTextField.getText().isEmpty())
                    messageLabel.setText("Name Field cannot be empty");

                else if (mobileTextField.getText().isEmpty())
                    messageLabel.setText("Mobile number cannot be empty");

                else {
                    //entering into db
                    DatabaseConnection databaseConnection = new DatabaseConnection();
                    String query = String.format("INSERT INTO customer(email, password, first_name, last_name, address, mobile) VALUES('%s', '%s', '%s', '%s', '%s', '%s')", email,password, firstName, lastName, address, mobile );
                    int rowCount = 0;
                    try{
                        rowCount = databaseConnection.executeUpdateQuery(query);
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                    if(rowCount != 0){
                        messageLabel.setText("Account Created SuccessFully");
                    }
                    else
                        messageLabel.setText("Email already exits");
                }
            };
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

        //adding firstName position
        gridPane.add(firstNameLabel,0, 2);
        gridPane.add(firstNameTextField, 1,2);

        //adding lastName position
        gridPane.add(lastNameLabel,0, 3);
        gridPane.add(lastNameTextField, 1,3);

        //adding address position
        gridPane.add(addressLabel,0, 4);
        gridPane.add(addressTextField, 1,4);

        //adding mobile position
        gridPane.add(mobileLabel,0, 5);
        gridPane.add(mobileTextField, 1,5);

        //adding address position
        gridPane.add(signUpButton,0, 6);
        gridPane.add(messageLabel, 1,6);
        return gridPane;
    }
    private GridPane footerBar(){

        Button addToCartButton = new Button("Add to Cart");
        Button buyNowButton = new Button("Buy Now");
        Label messageLabel = new Label("");
        buyNowButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Product selectedProduct = productDetails.getSelectedProduct();
                if(Order.placeOrder(customerEmail, selectedProduct)==true){
                    messageLabel.setText("Ordered");
                }
                else{
                    messageLabel.setText("Order Failed");
                }
            }
        });

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(bodyPane.getMinWidth(), headerBar-10);
        gridPane.setVgap(5);
        gridPane.setHgap(20);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setTranslateY(headerBar + height + 5);
        gridPane.add(addToCartButton, 0, 0);
        gridPane.add(buyNowButton, 1, 0);
        gridPane.add(messageLabel,2,0);
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
                if(login.customerLogin(email, password)==true){  //getting email & password verified from class login function
                    messageLabel.setText("Login Successful");
                    customerEmail = email;
                    globalLoginButton.setDisable(true);
                    customerEmailLabel.setText("Welcome : " + customerEmail);
                    bodyPane.getChildren().clear();
                    bodyPane.getChildren().add(productDetails.getAllProducts());
                }
                else
                    messageLabel.setText("Incorrect credentials");
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
        root.setPrefSize(width, height+2*headerBar);
        //we need to add all child to the root to run

        bodyPane.setMinSize(width, height);
        bodyPane.setTranslateY(headerBar); //to make start login page after headerBar

        bodyPane.getChildren().addAll(productDetails.getAllProducts());
        root.getChildren().addAll(headerBar(), bodyPane, footerBar());
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