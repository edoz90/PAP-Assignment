package pap.ass08.GOL;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import pap.ass08.GOL.msg.ConfigWorker;
import pap.ass08.GOL.msg.DoTurn;
import pap.ass08.GOL.msg.GetRow;
import pap.ass08.GOL.msg.WorkDone;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author edoardo
 */
public class Worker extends UntypedActor {

    private final int id;
    private boolean[] row;
    private boolean[] upperRow;
    private boolean[] lowerRow;
    private boolean[] newRow;
    private ActorRef upperActor;
    private ActorRef lowerActor;
    private ArrayList<Point> live;
    private int countLive;
    private ActorRef controller;

    public Worker(int i) {
        this.id = i;
        this.countLive = 0;
    }

    @Override
    public void onReceive(Object msg) throws Exception {
        if (msg instanceof ConfigWorker) {
            this.controller = getSender();
            this.configMe((ConfigWorker) msg);
        } else if (msg instanceof DoTurn) {
            // Get the upper and lower row
            this.upperActor.tell(new GetRow("upper"), getSelf());
            this.lowerActor.tell(new GetRow("lower"), getSelf());
        } else if (msg instanceof GetRow && ((GetRow) msg).row == null) {
            // If the msg.row is not set I've to respond with my row
            getSender().tell(new GetRow(((GetRow) msg).type, this.row), getSelf());
        } else if (msg instanceof GetRow && ((GetRow) msg).row != null) {
            // I received a row
            GetRow m = (GetRow) msg;
            if ("upper".equals(m.type))
                this.upperRow = m.row;
            if ("lower".equals(m.type))
                this.lowerRow = m.row;
            if (this.lowerRow != null && this.upperRow != null)
                this.checkNewRow();
        }
    }

    private void configMe(ConfigWorker cw) {
        this.row = cw.row;
        this.newRow = new boolean[row.length];
        this.upperActor = cw.upperActor;
        this.lowerActor = cw.lowerActor;

        this.live = new ArrayList<>();

        int length = this.row.length;
        for (int i = 0; i < length; i++) {
            if (this.row[i]) {
                this.countLive++;
            }
        }
        this.controller.tell(new WorkDone("config"), getSelf());
    }

    private void checkNewRow() {
        this.live.clear();
        for (int i = 0; i < row.length; i++) {
            this.computeNextCellState(i);
        }
        swapRow();
        this.lowerRow = null;
        this.upperRow = null;
        this.controller.tell(new WorkDone("turn", this.live), controller);
    }

    private void computeNextCellState(int col) {
        this.countLive = 0;
        int prec = col == 0 ? row.length - 1 : col - 1;
        int succ = (col + 1) % row.length;

        if (this.upperRow[prec]) {
            this.countLive++;
        }
        if (this.upperRow[col]) {
            this.countLive++;
        }
        if (this.upperRow[succ]) {
            this.countLive++;
        }
        if (row[prec]) {
            this.countLive++;
        }
        if (row[succ]) {
            this.countLive++;
        }
        if (this.lowerRow[prec]) {
            this.countLive++;
        }
        if (this.lowerRow[col]) {
            this.countLive++;
        }
        if (this.lowerRow[succ]) {
            this.countLive++;
        }

        if (row[col]) {
            this.newRow[col] = !(this.countLive <= 1 || this.countLive >= 4);
        } else {
            this.newRow[col] = this.countLive == 3;
        }

        if (this.newRow[col]) {
            this.live.add(new Point(col, id));
        }
    }

    private void swapRow() {
        boolean[] app = this.row;
        this.row = this.newRow;
        this.newRow = app;
    }
}
