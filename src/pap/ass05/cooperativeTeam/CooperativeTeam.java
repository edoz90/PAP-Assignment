package pap.ass05.cooperativeTeam;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.stream.IntStream;

import static java.lang.Thread.sleep;

/**
 * @author edoardo
 */
public class CooperativeTeam {

    private final static int NUMWORK = 5;
    private final static int NUMCOUN = 3;
    private static List<UnsafeCounter> counters = new ArrayList<>(NUMCOUN);
    private static List<Semaphore> sem = new ArrayList<>();
    private static List<Worker> wlist = new ArrayList<>(NUMWORK);

    public static void main(String[] args) {
        IntStream.range(0, NUMCOUN).forEach(i -> {
            counters.add(new UnsafeCounter("Counter" + (i + 1)));
        });

        sem.add(new Semaphore(1));
        sem.add(new Semaphore(1));
        sem.add(new Semaphore(0));
        sem.add(new Semaphore(0));
        sem.add(new Semaphore(0));

        W1 w1 = new W1("Worker1", sem, counters);
        wlist.add(w1);
        W2 w2 = new W2("Worker2", sem, counters);
        wlist.add(w2);
        W3 w3 = new W3("Worker3", sem, counters);
        wlist.add(w3);
        W4 w4 = new W4("Worker4", sem, counters);
        wlist.add(w4);
        W5 w5 = new W5("Worker5", sem, counters);
        wlist.add(w5);

        wlist.forEach(t -> t.start());

        try {
            sleep(3);
        } catch (InterruptedException ex) {
        }

        wlist.forEach(i -> {
            i.stopRun();
        });


    }
}
