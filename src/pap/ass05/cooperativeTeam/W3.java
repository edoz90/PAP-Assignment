package pap.ass05.cooperativeTeam;

import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * @author edoardo
 */
public class W3 extends Worker {

    public W3(String name, List<Semaphore> sem, List<UnsafeCounter> count) {
        super(name, sem, count);
    }

    /* W3 ha il compito di stampare il valore di c1 ogni volta che viene aggiornato da W1 e quindi di incrementare c3. */
    @Override
    public void run() {
        while (!stop) {
            try {
                sem.get(2).acquire();
                counters.get(0).print(this.getName());
                counters.get(2).inc(this.getName());
                sem.get(4).release();
            } catch (InterruptedException ex) {
            } finally {
                sem.get(2).release();
            }
        }
    }
}
