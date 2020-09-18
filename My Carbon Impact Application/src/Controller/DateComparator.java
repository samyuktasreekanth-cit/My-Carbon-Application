package Controller;

import Model.Activity;

import java.util.Comparator;


public class DateComparator implements Comparator<Activity> {
    /***
     *  Compares the date using the compareTo()
     * @param a1 the first date of the activity to be compared
     * @param a2 the second date of the activity to be compared
     * @return the compared value of a1 and a2
     */
    public int compare(Activity a1, Activity a2) {
        String d1 = a1.getDate();
        String d2 = a2.getDate();
        return d1.compareTo(d2);
    }
}
