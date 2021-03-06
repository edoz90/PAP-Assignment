package pap.ass05.CTF;

import java.util.Random;

/**
 * @author edoardo
 */
public class Arbiter extends Thread {

    // SEED and MAX are empirically set to execute some turns
    private final int SEED = 100;
    private final int MAX = 1;
    private final Random rand;
    private final Flag flag;

    public Arbiter(String name, Flag f) {
        super(name);
        this.flag = f;
        rand = new Random(SEED);
    }

    @Override
    public void run() {
        while (!CTF.getGameStatus()) {
            try {
                Thread.sleep(rand.nextInt(MAX));
                this.flag.setHigh();
                Thread.sleep(rand.nextInt(MAX));
                this.flag.setLow();
            } catch (InterruptedException e) {
            }
        }
    }
}
