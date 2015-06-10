package pap.ass05.minDistance;

import java.util.ArrayList;
import static java.util.Comparator.comparingDouble;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

/**
 *
 * @author edoardo
 */
public class MinDistance {

    public static final int COOBOUND = 80;
    public static final int SEED = 11;
    public static final int CORE = Runtime.getRuntime().availableProcessors() - 1;
    public static final int NPOINTS = CORE * 950000;
    public static List<P3d> distanceList = new ArrayList();
    public static final int RANGE = NPOINTS / CORE;

    public static void main(String[] args) {
        P3d C = new P3d(0, 0, 0);
        Random rand = new Random(SEED);

        System.out.println("Creating " + NPOINTS + " points...");
        List<P3d> points = new ArrayList<>();
        IntStream.rangeClosed(0, NPOINTS).forEach(i -> {
            int x = rand.nextInt(COOBOUND);
            int y = rand.nextInt(COOBOUND);
            int z = rand.nextInt(COOBOUND);
            points.add(new P3d(x, y, z));
        });

        long startTime = System.currentTimeMillis();

        List<DistanceThread> tlist = new ArrayList<>();
        IntStream.rangeClosed(0, CORE - 1).forEach(i -> {
            DistanceThread t = new DistanceThread("Thread" + i, points, (i * RANGE), ((i + 1) * RANGE) - 1, C);
            t.start();
            tlist.add(t);
        });

        tlist.stream().forEach(t -> {
            try {
                t.join();
            } catch (Exception e) {
            }
        });

        P3d minDistance = distanceList.stream().min(comparingDouble(p -> p.distance(C))).get();

        long stopTime = System.currentTimeMillis();
        System.out.println("\u001B[34mThread approach {\u001B[0m");
        System.out.println("\tExecution time: \u001B[32m" + (stopTime - startTime) + "\u001B[0m");
        System.out.println("\tResult: \u001B[32m" + minDistance.toString() + "\u001B[0m");
        System.out.println("\tDistance from " + C.toString() + ": \u001B[32m" + minDistance + "\u001B[0m");
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
