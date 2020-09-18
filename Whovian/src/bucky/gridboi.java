package bucky;

import javafx.stage.Stage;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import org.w3c.dom.Text;


public class gridboi extends Application{
    Stage window;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        window = stage;
        window.setTitle("NewNewNewYork");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);

        //Name Label
        Label nameLabel = new Label("Username");
        GridPane.setConstraints(nameLabel, 0,0);

        //Name Input
        TextField nameInput = new TextField("Bucky");
        GridPane.setConstraints(nameInput, 1, 0);

        //password Label
        Label passLabel = new Label("Password");
        GridPane.setConstraints(passLabel, 0,1);

        //password input
        TextField passInput = new TextField();
        passInput.setPromptText("password");
        GridPane.setConstraints(passInput, 1,1);

        Button loginbutton = new Button("Login");
        GridPane.setConstraints(loginbutton,1,2);

        //Extract and validate input
        Label extractLabel = new Label("Extract and validate input");
        GridPane.setConstraints(extractLabel,0,3);
        //Form
        TextField userinput = new TextField();
        GridPane.setConstraints(userinput,0,4);

        Button terminalButton = new Button("Terminal");
        GridPane.setConstraints(terminalButton,0,5);
        terminalButton.setOnAction(e -> isInt(userinput, userinput.getText()));

        grid.getChildren().addAll(nameLabel, nameInput, passLabel, passInput, loginbutton, extractLabel, userinput, terminalButton);
        Scene scene = new Scene(grid, 300,200);

        window.setScene(scene);
        window.show();
    }

    private boolean isInt(TextField input, String WhatUserTyped){
        try{
            int age = Integer.parseInt(input.getText());
            System.out.println("User age is: "+ age);
            return true;
        }
        catch (NumberFormatException e){
            System.out.println("ERROR");
            return false;
        }
    }
}
