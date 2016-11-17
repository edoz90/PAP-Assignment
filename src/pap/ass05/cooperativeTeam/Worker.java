package pap.ass05.cooperativeTeam;

import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * @author edoardo
 */
public abstract class Worker extends Thread {

    public List<Semaphore> sem;
    public List<UnsafeCounter> counters;
    public boolean stop;

    public Worker(String name, List<Semaphore> sem, List<UnsafeCounter> count) {
        super(name);
        this.sem = sem;
        this.counters = count;
        stop = false;
    }

    public void stopRun() {
        this.stop = true;
    }

}
