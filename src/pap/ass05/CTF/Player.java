package pap.ass05.CTF;

/**
 *
 * @author edoardo
 */
public class Player extends Thread {

    private final int turn;
    private final Flag flag;
    private final Sync sync;

    public Player(String name, int turn, Flag f, Sync s) {
        super(name + turn);
        this.turn = turn;
        this.flag = f;
        this.sync = s;
    }

    @Override
    public void run() {
        // while the game is not finished
        while (!CTF.getGameStatus()) {
            this.sync.waitForTurn(this.turn);
            // when notified and the game is not finished
            if (!CTF.getGameStatus()) {
                // take the flag
                if (this.flag.capture()) {
                    CTF.endGame();
                    this.win();
                }
                // next round
                this.sync.next();
            } else {
                this.loose();
            }
        }
    }

    private void win() {
        System.out.println("\u001B[31m" + this.getName() + " WON!\u001B[0m");
    }

    private void loose() {
        System.out.println("\u001B[34mSOB!\u001B[0m");
    }

}
