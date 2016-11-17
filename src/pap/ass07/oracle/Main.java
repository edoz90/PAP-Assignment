package pap.ass07.oracle;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author edoardo
 */
public class Main {

    private static final long MAX = 50000;
    private static int nplayers;
    private static List<ActorRef> alist;

    public static void main(String[] args) {
        // MAX = 50000 nplayers=1000 core=8 -> ~4000ms (~130 turns)
        nplayers = Integer.parseInt(args[0]);
        ActorSystem system = ActorSystem.create("PAP07");

        // Oracle
        ActorRef oracle = system.actorOf(Props.create(Oracle.class, MAX, nplayers), "Oracle");

        // Players
        alist = new ArrayList<>();
        IntStream.range(0, nplayers).forEach(i -> {
            alist.add(system.actorOf(Props.create(Guesser.class, oracle), "Guesser" + i));
        });

        // Terminator/Referee
        system.actorOf(Props.create(Terminator.class, alist, oracle), "Terminator");
    }

    // Type Lose
    public static class Lose {

        public static void printLoose(String name) {
            System.out.println("\u001B[31m" + name + ": SOB!\u001B[0m");
        }
    }
}
