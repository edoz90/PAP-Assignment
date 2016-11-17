package pap.ass04.textball;

import java.util.List;

public class BallPrinter extends Thread {

    private boolean stop;
    private List<TextThread> list;

    public BallPrinter(List<TextThread> listT) {
        this.stop = false;
        this.list = listT;
    }

    public void run() {
        while (!stop) {
            TextLibFactory.getInstance().cls();
            this.list.parallelStream().forEach(t -> {
                int x = t.getPos().x;
                int y = t.getPos().y;
                TextLibFactory.getInstance().writeAt(x, y, "😀", t.getColor());
            });
            try {
                this.sleep(200);
            } catch (Exception e) {
            }
        }
    }

    public void stopPrint() {
        this.stop = true;
    }

    public void startPrint() {
        this.stop = false;
    }

}
