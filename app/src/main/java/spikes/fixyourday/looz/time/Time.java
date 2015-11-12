package spikes.fixyourday.looz.time;

/**
 * Created by dvir on 11/12/15.
 */
public class Time {

    private Resolution res;
    private int time;

    public Time(Resolution res, int time) {
        this.res = res;
        this.time = time;
    }

    public void setResolution(Resolution res) {
        this.res = res;
    }

    public long getTime() {
        switch (res) {
            case HOURS:
                return time * 3600;
            case MINUTES:
                return time * 60;
            case SECONDS:
                return time;
            default:
                return 0;
        }
    }

}
