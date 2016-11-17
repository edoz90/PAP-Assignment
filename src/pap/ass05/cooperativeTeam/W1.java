package pap.ass05.cooperativeTeam;

import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * @author edoardo
 */
public class W1 extends Worker {

    public W1(String name, List<Semaphore> sem, List<UnsafeCounter> count) {
        super(name, sem, count);
    }

    @Override
    public void run() {
        while (!stop) {
            try {
                sem.get(0).acquire();
                counters.get(0).inc(this.getName());
                sem.get(2).release();
            } catch (InterruptedException ex) {
            } finally {
                sem.get(0).release();
            }
        }
    }
}
