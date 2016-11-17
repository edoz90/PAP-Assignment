package pap.ass04.textball;

import java.util.Random;

public class TextThread extends Thread {

    private static final P2d TOP_LEFT = new P2d(0, 0);
    private static final P2d BOTTOM_RIGHT = new P2d(80, 24);
    private P2d pos;
    private boolean stop;
    private int speed;
    private int rand;
    private int color;

    public TextThread(String name) {
        super(name);
        int x = Integer.remainderUnsigned(new Random().nextInt(), BOTTOM_RIGHT.x);
        int y = Integer.remainderUnsigned(new Random().nextInt(), BOTTOM_RIGHT.y);
        this.pos = new P2d(x, y);
        this.speed = 1;
        this.stop = false;
        this.color = Integer.remainderUnsigned(new Random().nextInt(), 9);
    }

    @Override
    public void run() {
        while (!this.stop) {
            try {
                this.updatePos();
                this.sleep(100);
            } catch (Exception e) {
            }
        }
    }

    private synchronized void updatePos() {
        this.rand = -this.speed + (int) (Math.random() * ((this.speed - (-this.speed)) + 1));
        this.pos.x += this.rand;
        this.pos.y += this.rand;
        applyConstraints();
    }

    private void applyConstraints() {
        if ((pos.x + this.rand) > BOTTOM_RIGHT.x)
            this.pos.x -= this.speed;
        else if ((pos.x + this.rand) <= TOP_LEFT.x)
            this.pos.x += this.speed;
        if ((pos.y + this.rand) > BOTTOM_RIGHT.y)
            this.pos.y -= this.speed;
        else if ((pos.y + this.rand) <= TOP_LEFT.y)
            this.pos.y += this.speed;
    }

    public synchronized P2d getPos() {
        return this.pos;
    }

    public void stopUpdate() {
        this.stop = true;
    }

    public void startUpdate() {
        this.stop = false;
    }

    public int getColor() {
        return this.color;
    }
}