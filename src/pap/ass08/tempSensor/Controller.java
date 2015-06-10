package pap.ass08.tempSensor;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.Subscription;

/**
 *
 * @author edoardo
 */
public class Controller {

    private static Controller istance = null;
    private final boolean DEBUG = false;
    private double min;
    private double max;
    private final AvgTemp avgTemp;
    private Observable timer;
    private Subscription subTimer;

    private Controller(AvgTemp avg) {
        this.avgTemp = avg;
        this.min = Double.MAX_VALUE;
        this.max = Double.MIN_VALUE;
    }

    public static Controller getController(AvgTemp avg) {
        if (Controller.istance == null) {
            Controller.istance = new Controller(avg);
        }
        return Controller.istance;
    }

    public static Controller getController() throws Exception {
        if (Controller.istance != null) {
            return Controller.istance;
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