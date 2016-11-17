package pap.ass05.CTF;

/**
 * @author edoardo
 */
public class Flag {

    private boolean high = false;

    /* cambia lo stato in alzata */
    public synchronized void setHigh() {
        this.high = true;
        System.out.println("\u001B[32mCATCH THE FLAG NOW!!!!\u001B[0m");
    }

    /* cambia lo stato in abbassata */
    public synchronized void setLow() {
        this.high = false;
        if (!CTF.getGameStatus())
            System.out.println("TOO LATE!");
    }

    /* restituisce true se la bandiera è alzata, false altrimenti */
    public synchronized boolean capture() {
        return this.high;
    }
}
