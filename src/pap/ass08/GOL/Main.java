package pap.ass08.GOL;

import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 *
 * @author edoardo
 */
public class Main {
    
    // max values: gridW = 2500, gridH = 2500, frameRate = 1000
    // max optimal values: gridW = 1500, gridH = 1500, frameRate = 1000;
    private static final int gridW = 1500, gridH = 1500, frameRate = 20;
    private static final int viewW = 800, viewH = 600;
    
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("GameOfLife");
        system.actorOf(Props.create(BootActor.class, gridW, gridH, frameRate, viewW, viewH));
    }
}
