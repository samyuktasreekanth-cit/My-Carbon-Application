package Model;

import java.io.Serializable;


public class Activity implements Serializable {

    private int week ;
    private String date;
    private String activity;
    private int points;

    /***
     * Constructor for Activity
     * @param week_spinner is for week
     * @param datebox is for date
     * @param activityInput is for activity name
     * @param points_spinner is for points (-10 to 10)
     */
    public Activity(int week_spinner, String datebox, String activityInput, int points_spinner) {
        this.week = week_spinner;
        this.date = datebox;
        this.activity = activityInput;
        this.points = points_spinner;
    }

    //getters

    public String getActivity() {
        return activity;
    }

    public int getPoints() {
        return points;
    }

    public int getWeek() {
        return week;
    }

    public String getDate() {
        return date;
    }

    //setters

    public void setDate(String date) {
        this.date = date;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setWeek(int week) {
        this.week = week;
    }


    @Override
    public String toString() {
        return "week= " + week +
                ", date= " + date +
                ", activity= '" + activity + '\'' +
                ", points= " + points + "\n";
    }

}
