package spikes.looz.time;

import java.io.Serializable;

/**
 * Created by dvir on 11/12/15.
 */
public class Time implements Serializable {

    private Resolution res;

    private int time;

    public Time(Resolution res, int time) {
        this.res = res;
        this.time = time;
    }

    public Resolution getRes() {
        return res;
    }

    public void setResolution(Resolution res) {
        this.res = res;
    }

    public int getTime() {
        switch (res) {
            case HOURS:
                return time * 60;
            case MINUTES:
                return time;
            default:
                return 0;
        }
    }

    @Override
    public String toString() {
        return time + " " + res;
    }
}
