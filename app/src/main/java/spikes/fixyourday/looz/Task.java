package spikes.fixyourday.looz;

import java.util.Comparator;

import spikes.fixyourday.looz.time.Time;

/**
 * Created by dvir on 11/12/15.
 */
public class Task implements Comparable {

    private int importance;
    private Time time;

    public Task(int importance, Time time) {
        this.importance = importance;
        this.time = time;
    }

    @Override
    public int compareTo(Object another) {
        if (this.importance < ((Task) another).importance) {
            return 1;
        } else if (this.importance > ((Task) another).importance) {
            return -1;
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

    public static class TaskComparator implements Comparator {
        @Override
        public int compare(Object lhs, Object rhs) {
            return ((Task) lhs).compareTo(rhs);
        }
    }

}
