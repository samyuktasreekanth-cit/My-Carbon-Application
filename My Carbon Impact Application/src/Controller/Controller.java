package Controller;

import Model.Activity;
import Model.ActivityList;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
    private ActivityList activity_list;
    private ArrayList list_of_activities;

    public Controller() {
        activity_list = new ActivityList();
        list_of_activities = new ArrayList();
    }

    public void add(Activity activityObject) {
        activity_list.add(activityObject);
    }

    public void remove(Activity activityObject) {
        activity_list.remove(activityObject);
    }

    public Activity get(int i) {
        return activity_list.get(i);
    }

    public int size() {
        return activity_list.size();
    }

    /***
     * Using serializable to save the activity list to a file
     * this is used by the save button in the activity management tab
     *  file name: carbon.txt.txt
     */
    public void savealllist() {
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("carbon.txt.txt"));
            {
                os.writeObject(activity_list);
            }
            os.close();
        } catch (Exception ex) {
            System.out.println("could not save");
            ex.printStackTrace();
        }
    }

    /***
     * Using Serializable to read from file
     * this is used by the load button in the activity management tab
     * there are pre-existing activities already there in the file if needed
     * @return the list of activities to be printed in the text area
     * file name: carbon.txt.txt
     */
    public String loadalllist() {
        String load = "";
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream("carbon.txt.txt"));
            activity_list = (ActivityList) is.readObject();
            for (int i = 0; i < activity_list.size(); i++) {
                Activity load_activity = activity_list.get(i);
                load += load_activity;
            }
            is.close();
        } catch (Exception ex) {
            System.out.println("could not load");
            ex.printStackTrace();
        }
        return load;
    }

    /***
     * Sorts the date in ascending order
     * @return the iterated activity details after sorting
     */
    public String sortByDate() {

        activity_list.sortByDate();
        String load_date = "";
        for (int i = 0; i < activity_list.size(); i++) {
            load_date += activity_list.get(i);
        }
        return load_date;
    }

    /***
     * Sorts the activity name in alphabetical order
     * @return the iterated activity details after sorting
     */
    public String sortByActivity() {

        activity_list.sortByActivity();
        String load_act = "";
        for (int i = 0; i < activity_list.size(); i++) {
            load_act += activity_list.get(i);
        }
        return load_act;
    }


}

