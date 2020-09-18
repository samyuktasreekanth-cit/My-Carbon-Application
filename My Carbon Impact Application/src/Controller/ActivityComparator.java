package Controller;

import Model.Activity;

import java.util.Comparator;

public class ActivityComparator implements Comparator<Activity>{
    /***
     *  Compares the activity name using the compareTo()
     * @param a1 the first activity name to be compared
     * @param a2 the second activity to be compared
     * @return the compared value of a1 and a2
     */
    public int compare(Activity a1, Activity a2) {
        String activity1 = a1.getActivity();
        String activity2 = a2.getActivity();
        return activity1.compareTo(activity2);
    }
}
