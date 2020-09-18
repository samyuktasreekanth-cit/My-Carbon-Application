package Model;

import org.junit.*;
import static org.junit.Assert.*;

public class ActivityTest {

    private ActivityList activity_list;
    private Activity activity_object;
    private Activity activity_name;


    @Before
    public void initTest() {
        activity_list = new ActivityList();
        activity_object = new Activity(3, "2020-12-20", "Eat a  pie", -8);
    }

    @Test
    public void getActivity(){
        activity_name = new Activity(3, "2020-03-21", "Driving to work", -8);
        assertEquals("Driving to work", activity_name.getActivity());
    }

    @Test
    public void OneItemAdd() {
        activity_list.add(activity_object);
        assertEquals(activity_list.size(), 1);
    }

    @Test
    public void OneItemRemove(){
        activity_list.add(activity_object);
        System.out.println("Elements before removing: " + activity_list.size());
        activity_list.remove(activity_object);
        System.out.println("Elements after removing: " + activity_list.size());
        assertEquals(activity_list.size(), 0);
    }
}