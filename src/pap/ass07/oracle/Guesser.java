package pap.ass07.oracle;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import pap.ass07.oracle.Main.Lose;

import java.util.Random;

/**
 * @author edoardo
 */
public class Guesser extends UntypedActor {

    private final ActorRef oracle;
    private final Random rand;
    private Long guess;
    private Long min;
    private Long max;
    private boolean coin;

    public Guesser(ActorRef o) {
        this.oracle = o;
        this.min = 0L;
        this.max = Long.MAX_VALUE;
        this.guess = this.nextLong(min, max);
        this.rand = new Random();
        this.coin = rand.nextBoolean();
    }

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof Lose) {
            // I've lost
            Lose.printLoose(getSelf().path().name());
            getContext().stop(getSelf());
        } else {
            if (message instanceof String) {
                if (getSender() == this.oracle) // The response from the oracle
                    this.parseMessage(message.toString());
                else // Player's turn
                    oracle.tell(this.guess, getSelf());
            } else {
                unhandled(message);
            }
        }
    }

    // New long number from range
    private long nextLong(long min, long max) {
        return min + (long) (Math.random() * ((1L + max) - min));
    }

    private void parseMessage(String m) {
        switch (m) {
            case "You are the One, Neo.":
                // I won
                System.out.println("\u001B[32m" + getSelf().path().name() + ": WON!\u001B[0m");
                getContext().stop(getSelf());
                break;
            case "The is no spoon":
            case "I'm trying to free your mind, Neo. But I can only show you the door.":
                this.guess = this.nextLong(min, max);
                break;
            case "Neo go right!":
                min = this.guess;
                this.guess += this.nextLong(min, max);
                break;
            case "Door on your left.":
                max = this.guess;
                min = 0L;
                this.guess = this.nextLong(min, max);
                break;
            case "The Oracle. She told me this would happen. She told me that I would have to make a choice.":
                if (this.coin) { // go to left
                    max = this.guess;
                    this.guess = this.nextLong(min, max);
                } else { // go to right
                    min = this.guess;
                    this.guess = this.nextLong(min, max);
                }
                this.coin = rand.nextBoolean();
                break;
            case "Your Turn":
                // From the referee
                this.guess = this.nextLong(min, max);
                break;
        }
    }
}
