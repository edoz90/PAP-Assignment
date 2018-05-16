package pap.ass08.tempSensor;

import rx.Observable;
import rx.Subscriber;

import java.util.ArrayList;
import java.util.List;

/**
 * @author edoardo
 */
public class AvgTemp {

    private final boolean DEBUG = false;
    private final double SPIKE = 0.1;
    private final int FREQ = 100;
    private final double MAXVARIATION = 10;
    private final List<Observable> lStream = new ArrayList<>();
    private final List<ReadTemp> lThread = new ArrayList<>();
    private final Observable<Double> zipStream;

    public AvgTemp(int obs) {
        Observable<Double> tempStream0 = Observable.create((Subscriber<? super Double> subscriber) -> {
            ReadTemp readTemp0 = new ReadTemp(subscriber, 0, 50, SPIKE, FREQ);
            readTemp0.start();
            this.lThread.add(readTemp0);
        });
        this.lStream.add(tempStream0);

        Observable<Double> tempStream1 = Observable.create((Subscriber<? super Double> subscriber) -> {
            ReadTemp readTemp1 = new ReadTemp(subscriber, 0, 50, SPIKE, FREQ);
            readTemp1.start();
            this.lThread.add(readTemp1);
        });
        this.lStream.add(tempStream1);

        Observable<Double> tempStream2 = Observable.create((Subscriber<? super Double> subscriber) -> {
            ReadTemp readTemp2 = new ReadTemp(subscriber, 0, 50, SPIKE, FREQ);
            readTemp2.start();
            this.lThread.add(readTemp2);
        });
        this.lStream.add(tempStream2);

        /* buffer(count, skip) creates a new buffer starting with the first emitted item from the source Observable
        * and every skip items thereafter, and fills each buffer with count items. It emits these buffers as Lists. */
        this.zipStream = Observable.zip(
                tempStream0.buffer(2, 1).filter(buf -> (buf.size() == 2 && (Math.abs(buf.get(0) - buf.get(1))) < MAXVARIATION)),
                tempStream1.buffer(2, 1).filter(buf -> (buf.size() == 2 && (Math.abs(buf.get(0) - buf.get(1))) < MAXVARIATION)),
                tempStream2.buffer(2, 1).filter(buf -> (buf.size() == 2 && (Math.abs(buf.get(0) - buf.get(1))) < MAXVARIATION)),
                (t0, t1, t2) -> ((t0.get(0) + t1.get(0) + t2.get(0)) / lStream.size()));
    }

    public void startRead(Controller c) {
        this.zipStream.subscribe(
                (Double v) -> {
                    if (DEBUG) {
                        System.out.println("Temp: " + v);
                    }
                    c.setValue(v);
                },
                (Throwable t) -> {
                    System.out.println("Ouch: " + t);
                },
                () -> {
                    if (DEBUG) {
                        System.out.println("DONE");
                    }
                }
        );
    }

    public void stopRead() {
        this.lThread.forEach(ReadTemp::stopThread);
    }
}
