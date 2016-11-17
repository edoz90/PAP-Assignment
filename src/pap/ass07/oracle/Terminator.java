package pap.ass07.oracle;

import akka.actor.ActorRef;
import akka.actor.Terminated;
import akka.actor.UntypedActor;
import pap.ass07.oracle.Main.Lose;

import java.util.List;

public class Terminator extends UntypedActor {

    private final List<ActorRef> ref;
    private final ActorRef oracle;
    private int macroTurn;
    private long startTime;
    private long endTime;

    public Terminator(List<ActorRef> ref, ActorRef oracle) {
        this.ref = ref;
        this.oracle = oracle;
        ref.parallelStream().forEach(i -> {
            getContext().watch(i);
        });
        this.macroTurn = 0; // 0.player1 0.player2... 3.player0 3.player1
        this.startTime = System.currentTimeMillis();
        this.nextTurn();
    }

    @Override
    public void onReceive(Object msg) {
        if (msg instanceof Integer) {
            System.out.println(this.macroTurn + "." + msg);
            // Tells the player to play the game
            this.ref.get((int) msg).tell("Your Turn", getSelf());
            if ((int) msg == (this.ref.size() - 1)) {
                this.macroTurn++;
            }
            this.nextTurn();
        } else if (msg instanceof Terminated) {
            // Someone won, end game
            getContext().stop(getSelf());
        } else {
            unhandled(msg);
        }
    }

    private void nextTurn() {
        //Asks the oracle the who is the next player
        this.oracle.tell("Who is next?", getSelf());
    }

    @Override
    public void postStop() {
        ref.forEach(i -> {
            i.tell(new Lose(), getSelf());
        });
        this.endTime = System.currentTimeMillis();
        System.out.println("\u001B[34mExecution time: " + (this.endTime - this.startTime + "ms\u001B[0m"));
        getContext().system().shutdown();
    }
}
