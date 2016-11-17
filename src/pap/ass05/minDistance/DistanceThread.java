package pap.ass05.minDistance;

import java.util.List;

public class DistanceThread extends Thread {

    private List<P3d> list;
    private int start;
    private int end;
    private P3d compare;

    public DistanceThread(String name, List<P3d> list, int start, int end, P3d C) {
        super(name);
        this.list = list;
        this.start = start;
        this.end = end;
        this.compare = C;
    }

    @Override
    public void run() {
        double distanceMin = Double.MAX_VALUE;
        P3d pointMin = null;
        for (int i = this.start; i <= this.end; i++) {
            double check = this.list.get(i).distance(compare);
            if (check < distanceMin) {
                distanceMin = check;
                pointMin = this.list.get(i);
            }
        }
        MinDistance.addToList(pointMin);
    }
}
