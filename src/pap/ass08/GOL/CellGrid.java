package pap.ass08.GOL;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class CellGrid {

    private final int w, h;
    private final boolean cells[][];

    public CellGrid(int w, int h) {
        this.w = w;
        this.h = h;
        cells = new boolean[h][w];
    }

    public boolean[] getRow(int i) {
        return this.cells[i];
    }

    public void setLive(ArrayList<Point> l) {
        l.stream().forEach(p -> {
            this.cells[p.x][p.y] = true;
        });
    }

    public int getWidth() {
        return w;
    }

    public int getHeight() {
        return h;
    }

    public boolean isAlive(int x, int y) {
        return cells[y][x];
    }

    public void initRandom(int n) {
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                cells[j][i] = false;
            }
        }

        Random rand = new Random(System.currentTimeMillis());
        for (int i = 0; i < n; i++) {
            int x = rand.nextInt(w);
            int y = rand.nextInt(h);
            cells[y][x] = true;
        }
    }

    public void drawGlider(int x, int y) {
        cells[y][x] = false;
        cells[y][(x + 1) % w] = true;
        cells[y][(x + 2) % w] = false;
        cells[(y + 1) % h][x] = false;
        cells[(y + 1) % h][(x + 1) % w] = false;
        cells[(y + 1) % h][(x + 2) % w] = true;
        cells[(y + 2) % h][x] = true;
        cells[(y + 2) % h][(x + 1) % w] = true;
        cells[(y + 2) % h][(x + 2) % w] = true;
    }

    public void drawBlock(int x, int y) {
        drawConf(x, y, new boolean[][]{{false, false, false, false},
        {false, true, true, false},
        {false, true, true, false},
        {false, false, false, false}});
    }

    private void drawConf(int x, int y, boolean[][] conf) {
        for (int i = 0; i < conf.length; i++) {
            for (int j = 0; j < conf[i].length; j++) {
                cells[(y + i) % h][(x + j) % w] = conf[i][j];
            }
        }
    }
}
