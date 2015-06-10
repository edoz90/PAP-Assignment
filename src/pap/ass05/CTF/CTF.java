package pap.ass05.CTF;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 *
 * @author edoardo
 */
public class CTF {

    public static final int CORE = Runtime.getRuntime().availableProcessors() - 1;
    public static final int NPLAYERS = CORE;
    private static boolean endGame = false;

    public static void main(String[] args) {
        Flag flag = new Flag();
        Sync sync = new Sync(NPLAYERS);

        List<Player> tlist = new ArrayList<>();
        IntStream.rangeClosed(0, NPLAYERS).forEach(i -> {
            Player t = new Player("Player", i, flag, sync);
            tlist.add(t);
        });

        Arbiter arbiter = new Arbiter("Arbiter", flag);
        arbiter.start();

        tlist.parallelStream().forEach(t -> t.start());

        tlist.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException ex) {
            }
        });

        try {
            arbiter.join();
        } catch (InterruptedException ex) {
        }
    }
    
    public synchronized static void endGame() {
        CTF.endGame = true;
    }
    
    public static boolean getGameStatus() {
        return CTF.endGame;
    }
}
