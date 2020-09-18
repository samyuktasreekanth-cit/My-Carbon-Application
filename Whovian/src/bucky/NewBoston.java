//bucky 1-3
//creating a basic window
//buttons and event handler
//inner anonymous class and lambda expression

package bucky;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

//anytime u want to handle user events u must implement ( implements EventHandler<ActionEvent> ) interface
public class NewBoston extends Application /*implements EventHandler<ActionEvent>*/{

    Button button;

    public static void main(String[] args){
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Title of the Window");
        button = new Button();
        button.setText("Click me");
        //button.setOnAction(this);

        //this class will handle the button event
        //ananymous inner class and lambda expression
        button.setOnAction(e -> System.out.println("hey now brown cow"));

        //A layout is basically how you want everything arranged on your screen
        StackPane layout = new StackPane();
        //this will just position the button in the middle
        layout.getChildren().add(button);

        //scene is the contents in the window
        //layout here is how do you want stuff arranged in your scene
        Scene scene = new Scene(layout, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

   /* @Override
    //whenever an event happens, such as a button click below method gets called
    public void handle(ActionEvent event) {
        if(event.getSource()== button){
            System.out.println("You have clicked the button");
        }
    }*/
}
