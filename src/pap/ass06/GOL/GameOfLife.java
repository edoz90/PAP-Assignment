package pap.ass06.GOL;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author edoardo
 */
public class GameOfLife extends Thread {

    private final int rows;
    private final int cols;
    private final int core;
    private final ControllerGOF c;
    private Matrix matrix;
    private ExecutorService exec;
    private int turn;

    public GameOfLife(int r, int c, int core, ControllerGOF con, Matrix m) {
        this.rows = r;
        this.cols = c;
        this.core = core;
        this.matrix = m;
        this.turn = 0;
        this.c = con;
        this.exec = null;
    }

    @Override
    public void run() {
        while (!ControllerGOF.stop) {
            this.exec = Executors.newScheduledThreadPool(this.core);
            IntStream.range(0, rows)
                    .parallel()
                    .forEach(i -> IntStream.range(0, cols)
                            .forEach(j -> exec.execute(new Cell(i, j, this.matrix, this.turn))));
            exec.shutdown();

            try {
                exec.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
            } catch (InterruptedException ex) {
            }

            this.turn = (this.turn == 0) ? 1 : 0;
            this.c.updateView(this.matrix.getDiff(), this.turn);
            this.matrix.resetDiff(this.turn);

            try {
                // need at least 50ms to let JavaFX update the view without causing
                // too high refresh-rate
                Thread.sleep(100);
            } catch (Exception ex) {
            }
            //printConsole();
        }
    }

    public void setCell(int x, int y, boolean val) {
        this.matrix.setState(x, y, val, (this.turn == 0) ? 1 : 0);
    }

    public boolean getCell(int x, int y) {
        return this.matrix.getState(x, y, (this.turn == 0) ? 1 : 0);
    }

    public int sum() {
        return this.matrix.sum(this.turn);
    }

    private void printConsole() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (this.matrix.getState(i, j, this.turn)) {
                    System.out.print("#");
                } else {
                    System.out.print("-");
                }
            }
            System.out.println("");
        }
        System.out.println("");
        System.out.println("");
    }
}
