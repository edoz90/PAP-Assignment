package pap.ass08.GOL;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

import java.util.List;

/**
 * @author edoardo
 */
public class BootActor extends UntypedActor {

    private final int width;
    private final int height;
    private final int period;
    private final int viewWidth;
    private final int viewHeight;
    private final CellGrid grid;
    private ActorRef controller;
    private ActorRef view;
    private List<ActorRef> worker;

    public BootActor(int w, int h, int rate, int vW, int vH) {
        this.width = w;
        this.height = h;
        this.viewWidth = vW;
        this.viewHeight = vH;
        this.period = 1000 / rate;

        this.grid = new CellGrid(this.width, this.height);
        this.grid.initRandom(350 * ((this.height > this.width) ? this.height : this.width));
    }

    @Override
    public void preStart() {
        // view -> width, height, cellGrid (creates the View object)
        view = getContext().actorOf(Props.create(ViewActor.class, this.viewWidth, this.viewHeight, grid), "view");
        // controller -> period update view, actor worker, grid, view actor
        controller = getContext().actorOf(Props.create(ControllerActor.class, this.period, this.worker, this.grid, this.view), "controller");
    }

    @Override
    public void onReceive(Object o) throws Exception {
    }

}
