package application;



import javax.swing.JButton;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class ConfirmBox extends Application   {
    Stage window;
    Button button;
    private static  Label       pizzaLabel;
    private static  Button      orderButton, resetButton, quitButton;
    private static  TextArea    orderDetails;
    private static  CheckBox[]  topping;
    private static  int         NUM_TOPPINGS= 5;
    private Controller          controll;
    private static  CheckBox    topping2;
    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        controll = new Controller();
        window.setTitle("Piza Order");
        pizzaLabel = new Label("choose your topping: ");
        topping= new CheckBox[NUM_TOPPINGS];
        topping[0] = new CheckBox("Extra cheese");
        topping[1] = new CheckBox("Peperoni");
        topping[2] = new CheckBox("Anchovies");
        topping[3] = new CheckBox("Mushrooms");
        topping[4] = new CheckBox("Olives");
        topping2 = new CheckBox("Taco Chips");
        orderButton = new Button ("Place order");
        orderButton.setOnAction(e -> controll.processOrder(topping,orderDetails));
        resetButton = new Button("Reset order form");
        resetButton.setOnAction(e -> controll.resetForm(this));
        quitButton = new Button("Quit program");
        quitButton.setOnAction(e -> System.exit(0));// or window.close()

        orderDetails = new TextArea("awaiting your order.");


        HBox r1 = new HBox(10);
        r1.getChildren().addAll(pizzaLabel);
        r1.getChildren().addAll(topping);

        HBox r4 = new HBox(10);

        r4.getChildren().addAll(topping2);

        HBox r2 = new HBox(20);
        r2.getChildren().addAll(orderButton,resetButton,quitButton);

        HBox r3 = new HBox(20);
        r3.getChildren().addAll(orderDetails);

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(r1,r4,r2,r3);



        Scene scene = new Scene(layout, 300, 250);
        window.setScene(scene);
        window.show();}

     void  processOrder()
    {
       String order = "you ordered";
       boolean toppingsOrdered= false;
       for (int index=0; index < NUM_TOPPINGS;++index)
          if (topping[index].isSelected()) {
             toppingsOrdered = true;
             order += "\n"+ topping[index].getText();
          }
       if (!toppingsOrdered)
          order += "No Toppings";
       order += ".";
       orderDetails.setText(order);
    }

     void  resetForm()
    {
       for (int index=0; index < NUM_TOPPINGS;++index)
           topping[index].setSelected(false);
       orderDetails.setText("awaiting your order.");
    }
    public void reSetTopping()
    {
        for (int index=0; index < topping.length;++index)
            topping[index].setSelected(false);
    }
    public void reSetOrderDetails()
    {
        orderDetails.setText("awaiting your order.");
    }

}


package Application;

        import javafx.scene.control.CheckBox;
        import javafx.scene.control.TextArea;

public class Controller {
    void  processOrder(CheckBox [] topping, TextArea orderDetails)
    {
        String order = "you ordered";
        boolean toppingsOrdered= false;
        for (int index=0; index < topping.length;++index)
            if (topping[index].) {
                toppingsOrdered = true;
                order += "\n"+ topping[index].getText();
            }
        if (!toppingsOrdered)
            order += "No Toppings";
        order += ".";
        orderDetails.setText(order);
    }

    void  resetForm( Main x)
    {
        x.reSetTopping();
        x.reSetOrderDetails();

    }
}
