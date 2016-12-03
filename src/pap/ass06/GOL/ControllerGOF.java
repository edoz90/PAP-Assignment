package pap.ass06.GOL;

import java.util.ArrayList;

/**
 * @author edoardo
 */
public final class ControllerGOF {

    public static boolean stop;
    private static ControllerGOF instance = null;
    private final int rows;
    private final int cols;
    private final int core;
    private GameOfLife gof;

    private ControllerGOF(int r, int c, int numCore) {
        this.rows = r;
        this.cols = c;
        this.core = numCore;
    }

    public synchronized static ControllerGOF getInstance(int r, int c, int core) {
        if (instance == null) {
            instance = new ControllerGOF(r, c, core);
        }
        return instance;
    }

    public void stopGame() {
        try {
            stop = true;
            gof.join();
        } catch (Exception e) {
        }
    }

    public void startGame() {
        stop = false;
        gof.start();
    }

    public void updateView(ArrayList<Cell> diff, int turn) {
        Viewer.updateGrid(diff, turn);
    }

    public int getLive() {
        return gof.sum();
    }

    public void setMatrix(Matrix m) {
        gof = new GameOfLife(rows, cols, core, this, m);
    }
}
