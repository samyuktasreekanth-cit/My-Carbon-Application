//basic window
//bucky 5 - creating alert boxes
//interacts with the AlertBox class
package bucky;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Stack;

public class BasicWindow extends Application{
    Stage window;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        window = stage;
        window.setTitle("Basic Window");

        Button button = new Button("Click me");

        //button for the alert box
        //button.setOnAction(e -> AlertBox.display("ALERT!!", "This is a cool alert box!"));

        //button for the confirmation box
        button.setOnAction(e -> {
           boolean result =  ConfirmBox.display("CONFIRM!!", "Whacha gonna do?");
           System.out.println(result);
        });

        StackPane layout = new StackPane();
        layout.getChildren().add(button);
        Scene scene = new Scene(layout, 300, 250);
        window.setScene(scene);
        window.show();

    }
}




































