package pap.ass08.tempSensor;

import rx.Subscriber;

/**
 * @author edoardo
 */
public class ReadTemp extends Thread {

    private final Subscriber<? super Double> subscriber;
    private final double min;
    private final double max;
    private final double spike;
    private final int freq;
    private final TempSensor ts;
    private boolean stop;

    public ReadTemp(Subscriber<? super Double> subscriber, double min, double max, double spike, int freq) {
        this.subscriber = subscriber;
        this.min = min;
        this.max = max;
        this.spike = spike;
        this.freq = freq;
        this.stop = false;
        ts = new TempSensor(this.min, this.max, this.spike);
    }

    @Override
    public void run() {
        while (!this.stop) {
            try {
                subscriber.onNext(ts.getCurrentValue());
                Thread.sleep(this.freq);
            } catch (Exception ex) {
            }
        }
        subscriber.onCompleted();
    }

    public void stopThread() {
        this.stop = true;
    }
}
