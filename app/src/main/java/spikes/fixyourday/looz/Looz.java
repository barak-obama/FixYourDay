package spikes.fixyourday.looz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by dvir on 11/12/15.
 */
public class Looz {

    private List<Task> tasks;

    public Looz() {
        tasks = new ArrayList<>();
    }

    public void sort() {
        Collections.sort(tasks, new Task.TaskComparator());
    }

}
