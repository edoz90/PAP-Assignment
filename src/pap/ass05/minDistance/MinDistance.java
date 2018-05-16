package pap.ass05.minDistance;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.IntStream;

import static java.util.Comparator.comparingDouble;

/**
 * @author edoardo
 */
public class MinDistance {

    public static final int COOBOUND = 5000;
    public static final int SEED = 11;
    public static final int CORE = Runtime.getRuntime().availableProcessors() - 1;
    public static int npoints = 0;
    public static int range = 0;

    private static List<P3d> distanceList = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("\u001B[1;33mTesting with low number of points\u001B[0m");
        range = 95;
        npoints = CORE * range;
        benchmark(npoints, range);
        System.out.println("\u001B[1;33mEnd Test\n\u001B[0m");

        System.out.println("\u001B[1;33mTesting with high number of points\u001B[0m");
        range = 950000;
        npoints = CORE * range;
        benchmark(npoints, range);
        System.out.println("\u001B[1;33mEnd Test\n\u001B[0m");
    }

    private static void benchmark(int npoints, int range) {
        P3d C = new P3d(0, 0, 0);
        Random rand = new Random(SEED);

        System.out.println("Creating " + npoints + " points...");
        List<P3d> points = new ArrayList<>();
        IntStream.rangeClosed(0, npoints).forEach(i -> {
            int x = rand.nextInt(COOBOUND);
            int y = rand.nextInt(COOBOUND);
            int z = rand.nextInt(COOBOUND);
            points.add(new P3d(x, y, z));
        });

        long startTime = System.currentTimeMillis();

        List<DistanceThread> tlist = new ArrayList<>();
        IntStream.rangeClosed(0, CORE - 1).forEach(i -> {
            DistanceThread t = new DistanceThread("Thread" + i, points, (i * range), ((i + 1) * range) - 1, C);
            t.start();
            tlist.add(t);
        });

        tlist.forEach(t -> {
            try {
                t.join();
            } catch (Exception ignored) {
            }
        });

        P3d minDistance = distanceList.stream().min(comparingDouble(p -> p.distance(C))).get();

        long stopTime = System.currentTimeMillis();
        System.out.println("\u001B[34mThread approach {\u001B[0m");
        System.out.println("\tExecution time: \u001B[32m" + (stopTime - startTime) + "\u001B[0m");
        System.out.println("\tResult: \u001B[32m" + minDistance + "\u001B[0m");
        System.out.println("\tDistance from " + C.toString() + ": \u001B[32m" + minDistance.distance(C) + "\u001B[0m");
        System.out.println("\u001B[34m}\u001B[0m");

        long startTime2 = System.currentTimeMillis();

        P3d minGet = points.stream().min(comparingDouble(p -> p.distance(C))).get();

        long stopTime2 = System.currentTimeMillis();
        System.out.println("\u001B[31mStream approach {\u001B[0m");
        System.out.println("\tExecution time: \u001B[32m" + (stopTime2 - startTime2) + "\u001B[0m");
        System.out.println("\tResult: \u001B[32m" + minGet.toString() + "\u001B[0m");
        System.out.println("\tDistance from " + C.toString() + ": \u001B[32m" + minGet.distance(C) + "\u001B[0m");
        System.out.println("\u001B[31m}\u001B[0m");
    }

    public synchronized static void addToList(P3d min) {
        MinDistance.distanceList.add(min);
    }
}
