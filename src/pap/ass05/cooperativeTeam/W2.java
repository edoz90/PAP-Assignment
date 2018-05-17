package pap.ass05.cooperativeTeam;

import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * @author edoardo
 */
public class W2 extends Worker {

    public W2(String name, List<Semaphore> sem, List<UnsafeCounter> count) {
        super(name, sem, count);
    }

    /* W1 e W2 hanno il compito di incrementare rispettivamente c1 e c2, concorrentemente e ripetutamente. */
    /* W1 e W2 possono procedere ad un nuovo incremento solo dopo che W5 ha stampato il valore di c3. */
    @Override
    public void run() {
        while (!stop) {
            try {
                sem.get(1).acquire();
                counters.get(1).inc(this.getName());
                sem.get(3).release();
            } catch (InterruptedException ex) {
            } finally {
                sem.get(1).release();
            }
        }
    }
}
