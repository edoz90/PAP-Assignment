package pap.ass08.GOL;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import pap.ass08.GOL.msg.ShowGUI;
import pap.ass08.GOL.msg.StartGame;
import pap.ass08.GOL.msg.StopGame;
import pap.ass08.GOL.msg.UpdateGUI;

/**
 * @author edoardo
 */
public class ViewActor extends UntypedActor implements InputListener {

    private final int width;
    private final int height;
    private final CellGrid grid;
    private final View v;
    private ActorRef controller;

    public ViewActor(int w, int h, CellGrid g) {
        this.width = w;
        this.height = h;
        this.grid = g;
        this.v = new View(width, height, grid);
        this.v.addListener(this);
    }

    @Override
    public void onReceive(Object msg) throws Exception {
        if (msg instanceof ShowGUI) {
            this.controller = getSender();
            this.v.setVisible(true);
        } else if (msg instanceof UpdateGUI) {
            UpdateGUI m = (UpdateGUI) msg;
            this.v.update(m.size, m.time);
        }
    }

    @Override
    public void started() {
        this.controller.tell(new StartGame(), getSelf());
    }

    @Override
    public void stopped() {
        this.controller.tell(new StopGame(), getSelf());
    }

}
