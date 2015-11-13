package spikes.looz;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by dvir on 11/12/15.
 */
public class Looz {

    private List<Task> tasks;
    private int start_hour, start_min, start_year, start_month, start_day;

    List<Integer> cantWork;

    public Looz(int start_year, int start_month, int start_day, int start_hour, int start_min, List<Integer> cantWork) {
        tasks = new ArrayList<>();
        this.start_year = start_year;
        this.start_month = start_month;
        this.start_day = start_day;
        this.start_hour = start_hour;
        this.start_min = start_min;
        this.cantWork = cantWork;
    }

    private void sort() {
        Collections.sort(tasks, new Task.TaskComparator());
    }

    public List<Task> getLooz() {
        sort();
        List<Task> betterTasks = new ArrayList<>();
        int last_startHour = start_hour;
        int last_startMin = start_min;
        for (Task t : tasks) {
            t.setStart_hour(last_startHour);
            t.setStart_min(last_startMin);
            betterTasks.add(t);
            last_startHour += t.getTime();
            last_startMin = start_min;
        }
        return betterTasks;
    }

    public List<Task> getLooz(int end_hour, int end_min) {
        sort();
        List<Task> betterTasks = new ArrayList<>();
        int time = end_hour - start_hour + 0 * end_min;
        int sum = 0;
        int last_endHour = start_hour;
        int last_startMin = start_min;
        for (Task t : tasks) {
            int end_cantWork = 0;
            int start_cantWork = 0;
            if (cantWork.size() > 0 && last_endHour == cantWork.get(0)) {
                start_cantWork = cantWork.remove(0);
                cantWork.remove(0);
                end_cantWork = cantWork.remove(0);
                cantWork.remove(0);
            }
            sum += end_cantWork - start_cantWork;
            last_endHour = end_cantWork == 0 ? last_endHour : end_cantWork;
            if (t.getTime() + sum <= time) {
                t.setStart_hour(last_endHour);
                last_endHour += t.getTime();
                t.setStart_min(last_startMin);
                betterTasks.add(t);
            }
        }
        return betterTasks;
    }

    public void addTask(Task t) {
        tasks.add(t);
    }

    public List<Task> getTasks() {
        return tasks;
    }

}
