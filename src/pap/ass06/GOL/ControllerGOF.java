package pap.ass06.GOL;

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

    public static ControllerGOF getInstance(int r, int c, int core) {
        if (instance == null) {
            synchronized (ControllerGOF.class) {
                instance = new ControllerGOF(r, c, core);
            }
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

    public void updateView(Matrix m, int turn) {
        Viewer.updateGrid(m, turn);
    }

    public int getLive() {
        return gof.sum();
    }

    public void setMatrix(Matrix m) {
        gof = new GameOfLife(rows, cols, core, this, m);
    }
}
