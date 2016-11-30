package pap.ass06.GOL;

import java.util.stream.IntStream;

/**
 * @author edoardo
 */
public class Cell implements Runnable {

    private final int row;
    private final int col;
    private final Matrix matrix;
    private final int turn;
    private boolean state; /* FALSE = dead, TRUE = live */
    private int countLive = 0;

    public Cell(int r, int c, Matrix m, int turn) {
        this.row = r;
        this.col = c;
        this.matrix = m;
        this.turn = turn;
    }

    @Override
    public void run() {
        this.state = this.matrix.getState(this.row, this.col, this.turn);
        IntStream.rangeClosed(row - 1, row + 1).forEach(i -> {
            IntStream.rangeClosed(col - 1, col + 1).forEach(k -> {
                if (k >= 0 && i >= 0 && k < this.matrix.getColNum() && i < this.matrix.getRowNum() && this.matrix.getState(i, k, turn)) {
                    this.countLive++;
                }
            });
        });
        // if is ALIVE
        if (this.state) {
            // the actual cell was added
            this.countLive--;
            if (this.countLive <= 1) {
                this.state = false;
            } else if (this.countLive >= 4) {
                this.state = false;
            }
        } else if (this.countLive == 3) { // if is DEAD
            this.state = true;
        }
        this.countLive = 0;
        this.matrix.setState(row, col, this.state, (turn == 0) ? 1 : 0);
        this.matrix.addDiffCell(this);
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    public boolean getState() {
        return this.state;
    }
}
