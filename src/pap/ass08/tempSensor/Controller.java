package pap.ass08.tempSensor;

import rx.Observable;
import rx.Subscription;

import java.util.concurrent.TimeUnit;

/**
 * @author edoardo
 */
public class Controller {

    private static Controller instance = null;
    private final boolean DEBUG = false;
    private final AvgTemp avgTemp;
    private double min;
    private double max;
    private Observable timer;
    private Subscription subTimer;

    private Controller(AvgTemp avg) {
        this.avgTemp = avg;
        this.min = Double.MAX_VALUE;
        this.max = Double.MIN_VALUE;
    }

    public static Controller getController(AvgTemp avg) {
        if (Controller.instance == null) {
            Controller.instance = new Controller(avg);
        }
        return Controller.instance;
    }

    public static Controller getController() throws Exception {
        if (Controller.instance != null) {
            return Controller.instance;
        } else {
            throw new Exception("NON PUOI!");
        }
    }

    public void setValue(double v) {
        if (v < this.min) {
            this.min = v;
            GUI.updateMin(this.min);
        } else if (v > this.max) {
            this.max = v;
            GUI.updateMax(this.max);
        }
        GUI.updateTemp(v);
    }

    public void startRead() {
        if (DEBUG) {
            System.out.println("START");
        }
        this.avgTemp.startRead(this);
    }

    public void stopRead() {
        if (DEBUG) {
            System.out.println("STOP");
        }
        this.avgTemp.stopRead();
    }

    public void checkQuota(double time) {
        this.timer = Observable.timer((long) time, TimeUnit.MILLISECONDS);
        this.subTimer = timer.subscribe(
                (Object v) -> {
                    if (DEBUG)
                        System.out.println("ALERT");
                    GUI.alert();
                },
                (Object t) -> {
                    System.out.println("Ouch: " + t.toString());
                },
                () -> {
                    if (DEBUG)
                        System.out.println("TIMER DONE");
                }
        );
    }

    public void reset() {
        if (DEBUG)
            System.out.println("RESET");
        this.subTimer.unsubscribe();
    }
}
