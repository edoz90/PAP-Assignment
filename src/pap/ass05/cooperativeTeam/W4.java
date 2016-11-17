package pap.ass05.cooperativeTeam;

import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * @author edoardo
 */
public class W4 extends Worker {

    public W4(String name, List<Semaphore> sem, List<UnsafeCounter> count) {
        super(name, sem, count);
    }

    @Override
    public void run() {
        while (!stop) {
            try {
                sem.get(3).acquire();
                counters.get(1).print(this.getName());
                counters.get(2).inc(this.getName());
                sem.get(4).release();
            } catch (InterruptedException ex) {
            } finally {
                sem.get(3).release();
            }
        }
    }
}
