package spikes.looz;

import java.io.Serializable;
import java.util.Comparator;

import spikes.looz.time.Resolution;
import spikes.looz.time.Time;

/**
 * Created by dvir on 11/12/15.
 */
public class Task implements Comparable,Serializable {

    private int importance;
    private Time time;
    private int start_hour, start_min;
    private String name;

    public Task(int importance, Time time, String name) {
        this.importance = importance;
        this.time = time;
        this.name = name;
    }

    @Override
    public int compareTo(Object another) {
        if (this.importance < ((Task) another).importance) {
            return -1;
        } else if (this.importance > ((Task) another).importance) {
            return 1;
        } else {
            if (this.time.getTime() < ((Task) another).time.getTime()) {
                return -1;
            } else if (this.time.getTime() > ((Task) another).time.getTime()) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    public int getStart_hour() {
        return start_hour;
    }

    public void setStart_hour(int start_hour) {
        this.start_hour = start_hour;
    }

    public int getStart_min() {
        return start_min;
    }

    public void setStart_min(int start_min) {
        this.start_min = start_min;
    }

    public String getName() {
        return name;
    }

    public int getTime() {
        return time.getRes() == Resolution.HOURS ? time.getTime() / 60 : time.getTime();
    }

    public int getImportance() {
        return importance;
    }

    public static class TaskComparator implements Comparator {
        @Override
        public int compare(Object lhs, Object rhs) {
            return ((Task) lhs).compareTo(rhs);
        }
    }

    @Override
    public String toString() {
        return name + ": " + importance + " " + time + " " + start_hour;
    }
}
