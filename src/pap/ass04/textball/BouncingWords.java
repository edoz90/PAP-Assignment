package pap.ass04.textball;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class BouncingWords {

    private static int n;

    public static void main(String[] args) {
        try {
            n = Integer.parseInt(args[0]);
        } catch (Exception e) {
            System.err.println("Bad Number");
            System.exit(1);
        }

        List<TextThread> tlist = new ArrayList<>();
        IntStream.rangeClosed(0, n - 1).forEach(i -> {
            int x = Integer.remainderUnsigned(new Random().nextInt(), 80);
            int y = Integer.remainderUnsigned(new Random().nextInt(), 24);
            TextThread t = new TextThread("Thread" + i);
            t.start();
            tlist.add(t);
        });

        BallPrinter bp = new BallPrinter(tlist);
        bp.start();

        tlist.forEach(t -> {
            try {
                t.join();
            } catch (Exception ex) {
            }
        });

        try {
            bp.join();
        } catch (Exception e) {
        }
    }

}
