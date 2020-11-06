package mc.screenshare.utils;

public class TimerUtil {
    public long currentTimeMS = -1;
    public long goalTimeMS = -1;

    public TimerUtil() {}

    public TimerUtil(long goalMS) {
        this.currentTimeMS = System.currentTimeMillis();
        this.goalTimeMS = System.currentTimeMillis() + goalMS;
    }

    public void start(long goalMS) {
        this.currentTimeMS = System.currentTimeMillis();
        this.goalTimeMS = System.currentTimeMillis() + goalMS;
    }

    public void reset() {
        this.currentTimeMS = -1;
        this.goalTimeMS = -1;
    }

    public boolean isDone() {
        if(this.currentTimeMS != -1) {
            this.currentTimeMS = System.currentTimeMillis();
            return this.goalTimeMS != -1 && this.currentTimeMS >= this.goalTimeMS;
        }
        return false;
    }

}
