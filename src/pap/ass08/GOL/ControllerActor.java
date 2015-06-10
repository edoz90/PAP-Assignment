package pap.ass08.GOL;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import pap.ass08.GOL.msg.*;
import scala.concurrent.duration.Duration;

/**
 *
 * @author edoardo
 */
public class ControllerActor extends UntypedActor {

    private final Flag stop;
    private final int period;
    private final CellGrid grid;
    private final ActorRef view;
    private final ActorSystem sys;
    private int countWorker;
    private List<ActorRef> worker;
    private final ArrayList<Point> live;
    private long timeNano;
    private long time;

    public ControllerActor(int rate, List<ActorRef> w, CellGrid g, ActorRef v) {
        // convert to nanosecond
        this.period = (int)(rate * Math.pow(10, 6));
        this.worker = w;
        this.grid = g;
        this.view = v;
        this.stop = new Flag();
        this.sys = ActorSystem.create("system");
        this.live = new ArrayList<>();
    }

    @Override
    public void preStart() {
        // Creates and configures a worker for each row
        this.worker = new ArrayList<>();
        ActorRef up;
        ActorRef bo;
        int height = this.grid.getHeight();
        this.countWorker = height;

        for (int i = 0; i < height; i++) {
            this.worker.add(getContext().actorOf(Props.create(Worker.class, i), "Worker" + i));
        }

        // Setting up the initial values of each row cell
        // for the first row (0)
        up = this.worker.get(height - 1);
        bo = this.worker.get(1);
        this.worker.get(0).tell(new ConfigWorker(this.grid.getRow(0), up, bo), getSelf());
        // for each row from 1 to n-2
        for (int i = 1; i < (height - 1); i++) {
            up = this.worker.get(i - 1);
            bo = this.worker.get(i + 1);
            this.worker.get(i).tell(new ConfigWorker(grid.getRow(i), up, bo), getSelf());
        }
        // for the last row (n-1)
        up = this.worker.get(height - 2);
        bo = this.worker.get(0);
        this.worker.get(height - 1).tell(new ConfigWorker(this.grid.getRow(height - 1), up, bo), getSelf());
    }

    @Override
    public void onReceive(Object msg) throws Exception {
        if (msg instanceof WorkDone && "config".equals(((WorkDone) msg).type)) {
            // response from ConfigWorker message
            this.countWorker--;
            if (this.countWorker == 0) {
                this.countWorker = this.worker.size();
                this.view.tell(new ShowGUI(), getSelf());
            }
        } else if (msg instanceof StartGame) {
            this.stop.reset();
            getSelf().tell(new NextTurn(), getSelf());
        } else if (msg instanceof WorkDone && "turn".equals(((WorkDone) msg).type)) {
            // response from DoTurn message
            this.countWorker--;
            this.live.addAll(((ArrayList<Point>) ((WorkDone) msg).result));
            if (this.countWorker == 0) {
                this.countWorker = this.worker.size();
                this.updateView();
            }
        } else if (msg instanceof StopGame) {
            this.stop.set();
        } else if (msg instanceof NextTurn) {
            this.nextTurn();
        }
    }

    private void nextTurn() {
        // time start
        this.time = System.currentTimeMillis();
        // for more precision
        this.timeNano = System.nanoTime();
        this.live.clear();
        // lunch the computation
        this.worker.stream().forEach(w -> w.tell(new DoTurn(), getSelf()));
    }

    // WARNING: CHECK THE REFRESH PERIOD
    private void updateView() {
        // time stop
        long computeTimeNano = (System.nanoTime() - this.timeNano);
        // Update the model
        this.grid.setLive(this.live);
        this.view.tell(new UpdateGUI(this.live.size(), computeTimeNano), getSelf());
        if (!this.stop.isSet()) {
            if (computeTimeNano < period) {
                // wait until the correct frame rate
                this.sys.scheduler().scheduleOnce(Duration.create(period - computeTimeNano, TimeUnit.NANOSECONDS), () -> {
                    getSelf().tell(new NextTurn(), getSelf());
                }, this.sys.dispatcher());
            } else {
                getSelf().tell(new NextTurn(), getSelf());
            }
        }
    }
}
