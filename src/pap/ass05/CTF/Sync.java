package pap.ass05.CTF;

/**
 *
 * @author edoardo
 */
public class Sync {

    private int turn;
    private final int players;

    public Sync(int players) {
        this.players = players - 1;
        this.turn = 0;
    }

    /* chiamata dal player i-esimo, sospende il player fin quando non è il suo turno */
    public synchronized void waitForTurn(int turn) {
        while (this.turn != turn && !CTF.getGameStatus()) {
            try {
                wait();
            } catch (InterruptedException ex) {
            }
        }
    }

    /* cede il turno al player successivo */
    public synchronized void next() {
        if (this.turn == this.players) {
            this.turn = 0;
        } else {
            this.turn++;
        }
        notifyAll();
    }
}
