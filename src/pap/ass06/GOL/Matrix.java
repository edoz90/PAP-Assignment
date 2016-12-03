package pap.ass06.GOL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * @author edoardo
 */
public class Matrix {

    private final int rows;
    private final int cols;
    private final boolean[][] matrix0;
    private final boolean[][] matrix1;
    private int count0;
    private int count1;
    private ArrayList<Cell> diff = new ArrayList<>();

    public Matrix(int r, int c) {
        this.rows = r;
        this.cols = c;
        this.matrix0 = new boolean[rows][cols];
        this.matrix1 = new boolean[rows][cols];
        for (int i = 0; i < this.rows; i++) {
            Arrays.fill(this.matrix0[i], false);
            Arrays.fill(this.matrix1[i], false);
        }
    }

    public void randomInit(int seed) {
        Random rand = new Random(seed);
        IntStream.range(0, this.rows).forEach(i -> {
            IntStream.range(0, this.cols).forEach(k -> {
                this.matrix0[i][k] = rand.nextBoolean();
            });
        });
    }

    public void addDiffCell(Cell c) {
        this.diff.add(c);
    }

    public ArrayList<Cell> getDiff() {
        return this.diff;
    }

    public boolean getState(int x, int y, int turn) {
        if (turn == 0) {
            return this.matrix0[x][y];
        } else {
            return this.matrix1[x][y];
        }
    }

    public void setState(int x, int y, boolean val, int turn) {
        if (turn == 0) {
            this.matrix0[x][y] = val;
            this.count0 = (val) ? this.count0 + 1 : this.count0;
        } else {
            this.matrix1[x][y] = val;
            this.count1 = (val) ? this.count1 + 1 : this.count1;
        }
    }

    public int sum(int turn) {
        if (turn == 0) {
            return this.count0;
        } else {
            return this.count1;
        }
    }

    public int getRowNum() {
        return this.rows;
    }

    public int getColNum() {
        return this.cols;
    }

    public boolean[][] getMatrix(int turn) {
        if (turn == 0) {
            return this.matrix0;
        } else {
            return this.matrix1;
        }
    }
}
