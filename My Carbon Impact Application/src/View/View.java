
/**
 * @author Samyukta Sreekanth
 */

package View;

import Model.Activity;
import Controller.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;

import java.io.*;
import java.util.*;

public class View extends Application {

    private Label DateLabel;
    private TextArea activity_display;
    private ArrayList<Activity> activity_list;
    private Button drop_down_activity;
    private TextField ActivityDropShow;
    private ChoiceDialog<String> act_diag;
    private String chosen_activity;
    private int chosen_points;
    private ArrayList<String> list_of_activities;
    private ComboBox combobox;
    private ChoiceDialog<String> rem_diag;

    private ObservableList<String> options;

    private Controller controll;
    private Activity activityObject;
    private String rem_act;


    @Override
    public void start(Stage stage) throws Exception {

        controll = new Controller();

        stage.setTitle("My Carbon Awareness Effect");

        GridPane grid = new GridPane();

        grid.setPadding(new Insets(10, 10, 10, 10));

        //week
        Label WeekLabel = new Label("Week _ _ _ _ _ _ _ _");
        grid.setConstraints(WeekLabel, 0, 0);

        TextField week_spinner = new TextField("3");
        grid.setConstraints(week_spinner, 1, 0);

        //Date
        DateLabel = new Label("Date _ _ _ _ _ _ _ _");
        grid.setConstraints(DateLabel, 0, 1);

        DatePicker datebox = new DatePicker();
        grid.setConstraints(datebox, 1, 1);

        //ActivityLabel
        Label ActivityLabel = new Label("Activity _ _ _ _ _ _ _ _");
        grid.setConstraints(ActivityLabel, 0, 2);


        //can type in new activity
        String activity_in_combobox[] =
                {"walking to work:10",
                        "Eating a 8oz steak:-10",
                        "Cycling to the shops:5",
                        "Driving to work:-5",
                        "Vegetarian for a day:7"};
        combobox = new ComboBox(FXCollections.observableArrayList(activity_in_combobox));


        //APP STUFF
        GridPane app_grid = new GridPane();
        Label app_label = new Label("Enter activity (activity:points)");
        app_grid.setConstraints(app_label, 0, 1);

        //text area for app
        TextField app_user_input = new TextField();
        app_grid.setConstraints(app_user_input, 0, 2);

        //Text area app
        TextArea app_text_area = new TextArea();
        app_grid.setConstraints(app_text_area, 0, 4);

        //add button for app
        //Buttons
        /***
         * This button is for the app tab where you can enter a new activity
         *  user input format is activity:point
         */
        Button add_app_button = new Button("Add");

        //array list of strings
        list_of_activities = new ArrayList<>();
        add_app_button.setOnAction(e -> {

            try (Scanner scanner = new Scanner(new File("app.txt"))) {
                //storing the contents of file to the arraylist
                while (scanner.hasNext()) {
                    list_of_activities.add(scanner.nextLine());
                }

                list_of_activities.add(app_user_input.getText());
                combobox.getItems().add(app_user_input.getText());

                //updating the text file with the new added activity and points
                try {
                    //Saving of object in a file
                    FileOutputStream file = new FileOutputStream("app.txt");
                    ObjectOutputStream out = new ObjectOutputStream(file);
                    // Method for serialization of object
                    out.writeObject(list_of_activities);
                    out.close();
                    file.close();
                    System.out.println("saved to file");
                } catch (IOException ex) {
                    System.out.println("cannot save to file");
                }

                //print the updated txt file to the text area in the app
                try {
                    ObjectInputStream is = new ObjectInputStream(new FileInputStream("app.txt"));
                    list_of_activities = (ArrayList<String>) is.readObject();
                    String loading_from_txt = "";
                    for (String l : list_of_activities) {
                        loading_from_txt += l + "\n";
                    }
                    app_text_area.setText(loading_from_txt);
                    is.close();
                } catch (Exception ex) {
                    System.out.println("could not load");
                    ex.printStackTrace();
                }
                //diplaying arraylist to the app text area
                System.out.println(list_of_activities);
            } catch (FileNotFoundException a) {
                a.printStackTrace();
            }
        });

        //END OF APP ADD

        grid.setConstraints(combobox, 1, 2);


        //add button
        Button add_button = new Button("Add");
        add_button.setLayoutX(10);
        add_button.setLayoutY(120);

        activity_display = new TextArea();
        activity_display.setLayoutX(10);
        activity_display.setLayoutY(150);
        activity_display.setPrefSize(500, 190);

        activity_list = new ArrayList<Activity>();

        /***
         * adds the activity to the activity list
         * drop down can be changed according to the app tab by adding or removing new activites
         */
        add_button.setOnAction(e -> {
            String myString = (String) combobox.getValue();
            String activity_from_dropdown = myString.split(":")[0];
            String points_from_dropdown = myString.split(":")[1];

            activityObject = new Activity(Integer.parseInt(week_spinner.getText()), datebox.getValue().toString(), activity_from_dropdown, Integer.parseInt(points_from_dropdown));

            controll.add(activityObject);

            String display = "";
            display += activityObject.toString() + "\n";

            activity_display.appendText(display);

        });

        //remove button
        Button remove_button = new Button("Remove");
        remove_button.setLayoutX(60);
        remove_button.setLayoutY(120);

        ArrayList<String> choices = new ArrayList<>();
        /***
         * Uses a drop drown menu to remove an activity from the activity list
         */
        remove_button.setOnAction(e -> {
            choices.clear();
            int j = 0;
            for (controll.get(j); j < controll.size(); j++) {
                choices.add(controll.get(j).getActivity());
            }
            ChoiceDialog<String> rem_diag = new ChoiceDialog<String>("None", choices);
            rem_diag.setTitle("Remove");
            rem_diag.setHeaderText("Choose an activity to remove: ");
            rem_diag.showAndWait();


            String after_rem_display = "";
            int i = 0, k = 0;
            for (controll.get(i); i < controll.size(); i++) {
                Activity activityloop = controll.get(i);
                if (activityloop.getActivity().equals(rem_diag.getSelectedItem())) {
                    controll.remove(activityloop);
                    for (controll.get(k); k < controll.size(); k++) {
                        after_rem_display += controll.get(k) + "\n";
                    }
                } else {
                    continue;
                }
            }
            activity_display.setText(after_rem_display);

        });

        //list button
        Button list_button = new Button("List");
        list_button.setLayoutX(140);
        list_button.setLayoutY(120);

        /***
         *uses an lert box to display the activity name and points in a tabular format
         */
        list_button.setOnAction(e -> {

            int i = 0;
            String display = "";
            for (controll.get(i); i < controll.size(); i++) {
                display += controll.get(i).getActivity() + "\t" + controll.get(i).getPoints() + "\n";
            }

            Alert list_alert = new Alert(Alert.AlertType.INFORMATION);
            list_alert.setTitle("List");
            list_alert.setHeaderText(null);
            list_alert.setContentText("Activity\tPoints\n" + display);
            list_alert.showAndWait();
        });

        //summary button
        Button summary_button = new Button("Summary");
        summary_button.setLayoutX(190);
        summary_button.setLayoutY(120);

        /***
         * Uses an alert box to count the points
         */
        summary_button.setOnAction(e -> {
            int counter = 0;
            int i = 0;
            for (controll.get(i); i < controll.size(); i++) {
                counter += controll.get(i).getPoints();
            }

            Alert summary_box = new Alert(Alert.AlertType.INFORMATION);
            summary_box.setTitle("Summary");
            summary_box.setHeaderText(null);
            summary_box.setContentText("Points: " + counter);
            summary_box.show();
        });

        //save button
        Button save_button = new Button("Save");

        save_button.setOnAction(e -> {

            controll.savealllist();
            activity_display.setText("Saved to file");
        });

        //load button
        Button load_button = new Button("Load");

        load_button.setOnAction(e -> {
            activity_display.setText(controll.loadalllist());
        });

        //exit button
        Button exit_button = new Button("Exit");
        exit_button.setOnAction(e -> {
            Platform.exit();
        });

        //memory button
        ArrayList<String> memory_array = new ArrayList<String>();
        Button memory_button = new Button("Memory");
        memory_button.setLayoutX(280);
        memory_button.setLayoutY(120);
        memory_button.setOnAction(e -> {
            for (; ; ) {
                memory_array.add("Infinite");
            }
        });

        //tile pane for load, save and exit
        TilePane tp = new TilePane();
        TilePane tp_e = new TilePane();
        tp.getChildren().add(save_button);
        tp.getChildren().add(load_button);
        tp_e.getChildren().add(exit_button);
        tp.setHgap(10);
        tp.setLayoutX(10);
        tp.setLayoutY(340);
        tp_e.setLayoutX(450);
        tp_e.setLayoutY(340);

        //RESULT TAB content stuff
        Button order_by_date = new Button("ORDER BY DATE");
        Button order_by_activity = new Button("ORDER BY ACTIVITY");

        HBox result_order_buttons = new HBox(order_by_date, order_by_activity);

        TextArea results_text_area = new TextArea("Results to be displayed");
        results_text_area.setLayoutX(10);
        results_text_area.setLayoutY(60);

        //order by date button
        order_by_date.setOnAction(e -> {
            results_text_area.setText(controll.sortByDate());
        });

        //order by activity button
        order_by_activity.setOnAction(e -> {
            results_text_area.setText(controll.sortByActivity());
        });

        // INTRO STUFF
        Label intro_label = new Label("Welcome To Your\nMy Carbon Impact Application");
        StackPane sp = new StackPane();
        sp.getChildren().add(intro_label);

        //ACTIVITY MANAGEMENT STUFF
        grid.getChildren().addAll(WeekLabel, DateLabel, ActivityLabel, week_spinner, datebox, combobox);
        Group root = new Group(grid, add_button, remove_button, list_button, summary_button, activity_display, tp, tp_e, memory_button);

        //RESULTS STUFF
        Group result_group = new Group(result_order_buttons, results_text_area);

//        APP STUFF CONTINUED
//        remove button for app
        Button remove_app_button = new Button("Remove App");
        ArrayList<String> app_choices = new ArrayList<String>();
        remove_app_button.setOnAction(e -> {
            app_choices.clear();
            try {
                // Reading the object from a file
                FileInputStream file = new FileInputStream("app.txt");
                ObjectInputStream in = new ObjectInputStream(file);

                list_of_activities = (ArrayList<String>) in.readObject();
                String loading_from_txt = "";
                for (String l : list_of_activities) {
                    loading_from_txt += l + "\n";
                }

                rem_diag = new ChoiceDialog<String>("None", list_of_activities);
                rem_diag.setTitle("Remove");
                rem_diag.setHeaderText("Choose an activity to remove: ");
                rem_diag.showAndWait();

                String rem_act = rem_diag.getSelectedItem();
                combobox.getItems().remove(rem_act);

                list_of_activities.removeIf(s -> s.equals(rem_act));

                //update the removed drop down list
                String updated = "";
                for (String g : list_of_activities) {
                    updated += g + "\n";
                }

                app_text_area.setText(updated);

                in.close();

                try {
                    //Saving of object in a file
                    FileOutputStream file_out = new FileOutputStream("app.txt");
                    ObjectOutputStream out = new ObjectOutputStream(file_out);
                    // Method for serialization of object
                    out.writeObject(list_of_activities);
                    out.close();
                    file.close();
                    System.out.println("saved to file");
                } catch (IOException ex) {
                    System.out.println("cannot save to file");
                }

            } catch (IOException ex) {
                System.out.println("IOException is caught");
            } catch (ClassNotFoundException ex) {
                System.out.println("ClassNotFoundException is caught");
            }
        });

        HBox app_button_hbox = new HBox(add_app_button, remove_app_button);
        app_grid.setConstraints(app_button_hbox, 0, 3);

        app_grid.getChildren().addAll(app_label, app_user_input, app_button_hbox, app_text_area);

        Group app_group = new Group(app_grid);

        StackPane app_stack = new StackPane(app_group);

        //ref for syntax: http://tutorials.jenkov.com/javafx/tabpane.html
        TabPane tabpane = new TabPane();
        Tab intro_tab = new Tab("Intro", sp);
        Tab act_man_tab = new Tab("Activity Management", root);
        Tab results_tab = new Tab("Results", result_group);
        Tab app_tab = new Tab("App", app_stack);
        tabpane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        tabpane.getTabs().add(intro_tab);
        tabpane.getTabs().add(act_man_tab);
        tabpane.getTabs().add(results_tab);
        tabpane.getTabs().add(app_tab);
        tabpane.setPrefSize(700, 100);

        Scene scene = new Scene(tabpane, 750, 420);
        stage.setScene(scene);
        stage.show();
    }

    //putting in methods
    ArrayList loadList(ArrayList list_of_activities) {
        return list_of_activities;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

//app.txt content to be pasted for every run of program

//walking to work:10"
//Eating a 8oz steak:-10"
//Cycling to the shops:5"
//Driving to work:-5"
//Vegetarian for a day:7

//files to use for this project:
// app.txt (App)
// carbon.txt.txt(Activity management)

//executable jar file
//C:\Users\samyu\IdeaProjects\My Carbon Impact Application\out\artifacts\My_Carbon_Impact_Application_jar2