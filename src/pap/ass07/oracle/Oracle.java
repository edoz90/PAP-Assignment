package pap.ass07.oracle;

import akka.actor.UntypedActor;
import pap.ass07.oracle.Main.Lose;

import java.util.Random;

/**
 * @author edoardo
 */
public class Oracle extends UntypedActor {

    private final int seed = 11;
    private final long secret;
    private final Random rand;
    private final int players;
    // Quote from Matrix
    private final String GT = "Neo go right!";
    private final String LT = "Door on your left.";
    private final String NOHINT = "I'm trying to free your mind, Neo. But I can only show you the door.";
    private final String CHOICE = "The Oracle. She told me this would happen. She told me that I would have to make a choice.";
    private final String WIN = "You are the One, Neo.";
    private boolean stopFlag;
    private boolean hint;
    private int turn;

    public Oracle(long max, int players) {
        this.turn = 0;
        this.players = players;
        this.rand = new Random();
        this.stopFlag = false;
        this.hint = rand.nextBoolean();
        // SECRET NUMBER
        this.secret = Long.remainderUnsigned(new Random(seed).nextLong(), max);
    }

    @Override
    public void onReceive(Object message) throws Exception {
        if (!stopFlag) {
            if (message instanceof Long) {
                if (this.secret == (long) message) {
                    // The player has guessed the secret
                    getSender().tell(this.WIN, getSelf());
                    stopFlag = true;
                } else {
                    // Hint for the player
                    this.tellHint((Long) message);
                }
            } else if (message instanceof String && message.equals("Who is next?")) {
                // The referee ask "Who is next?"
                getSender().tell(this.turn, getSelf());
                this.turn = (this.turn++ == this.players - 1) ? 0 : this.turn++;
            } else {
                // Not a valid message
                getSender().tell("There is no spoon.", getSelf());
                unhandled(message);
            }
        } else {
            // The game is ended, you lost
            getSender().tell(new Lose(), getSelf());
        }
    }

    private void tellHint(Long m) {
        if (this.hint) { // the oracle wants to give a hint
            this.hint = rand.nextBoolean();
            if (!this.hint || rand.nextBoolean()) { // The oracle chooses which hint give
                if ((long) m < this.secret) {
                    getSender().tell(this.GT, getSelf());
                } else {
                    getSender().tell(this.LT, getSelf());
                }
            } else { // Tell the playes to choose a range
                getSender().tell(this.CHOICE, getSelf());
            }
        } else { // the oracle does not give a hint
            getSender().tell(this.NOHINT, getSelf());
        }
        this.hint = rand.nextBoolean();
    }
}
