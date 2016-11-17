package pap.ass08.GOL.msg;

import akka.actor.ActorRef;

/**
 * @author edoardo
 */
public class ConfigWorker {

    public boolean[] row;
    public ActorRef upperActor;
    public ActorRef lowerActor;

    public ConfigWorker(boolean[] row, ActorRef up, ActorRef bo) {
        this.row = row;
        this.upperActor = up;
        this.lowerActor = bo;
    }
}
