package pap.ass04.textball;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class TestCruncher {

    static long max;
    int howMany;

    public static void main(String[] args) {
        long t0 = System.currentTimeMillis();
        int howMany = Runtime.getRuntime().availableProcessors();
        try {
            max = Long.parseLong(args[0]);
        } catch (Exception e) {
            System.err.println("ERROR: is not a number");
            System.exit(1);
        }

        Secret s = new Secret(max);
        long range = (long) Math.ceil(max / howMany);

        Thread.UncaughtExceptionHandler h = (Thread th, Throwable ex) -> {
            long t1 = System.currentTimeMillis();
            System.out.println("Secret is: " + ex.getMessage() + "\nTime elapsed: " + (t1 - t0));
            System.exit(0);
        };

        List<CruncherThread> tlist = new ArrayList<>();
        IntStream.rangeClosed(0, howMany - 1).forEach(i -> {
            CruncherThread t = new CruncherThread("Thread" + i, (long) (i * range), (long) (((i + 1) * range) - 1), s);
            t.setUncaughtExceptionHandler(h);
            t.start();
            tlist.add(t);
        });

        tlist.stream().forEach(t -> {
            try {
                t.join();
            } catch (Exception ex) {
            }
        });
    }
}
