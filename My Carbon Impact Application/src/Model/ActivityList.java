package Model;

import Controller.ActivityComparator;
import Controller.DateComparator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;


public class ActivityList implements Serializable {
    private ArrayList<Activity> activity_list;

    /***
     * Constructor
     */
    public ActivityList() {
        activity_list = new ArrayList<Activity>();
    }

    /***
     * Adds the activity to the activity list
     * @param activityObject is the activity to be added
     */

    public void add(Activity activityObject) {
        activity_list.add(activityObject);
    }

    /***
     * Removes the activity from the activity list
     * @param activityObject is the activity to be removed
     */
    public void remove(Activity activityObject) {
        activity_list.remove(activityObject);
    }

    /***
     *
     * @param i is the index
     * @return returns the index of the activity list
     */
    public Activity get(int i) {
        return activity_list.get(i);
    }

    /***
     *
     * @return the size of the arraylist
     */
    public int size() {
        return activity_list.size();
    }

    /***{
     * sort the date in ascending order
     * implements the DateComparator.java
     */
    public void sortByDate() {
        DateComparator dateComparator = new DateComparator();
        Collections.sort(activity_list, dateComparator);
    }

    /***{
     * sort the activity in alphabetical order
     * implements the ActivityComparator.java
     */
    public void sortByActivity() {
        ActivityComparator activityComparator = new ActivityComparator();
        Collections.sort(activity_list, activityComparator);
    }

}