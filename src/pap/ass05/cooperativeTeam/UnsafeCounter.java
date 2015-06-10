package pap.ass05.cooperativeTeam;

/**
 *
 * @author edoardo
 */
public class UnsafeCounter {
    
    private int counter;
    private String name;
    
    public UnsafeCounter(String name) {
        this.name = name;
        this.counter = 0;
    }
    
    public UnsafeCounter(String name, int init) {
        this.counter = init;
    }
    
    public void inc(String who) {
        this.counter++;
        System.out.println(who + " increased " + this.name + ": " + this.counter);
    }

    public void print(String who) {
        System.out.println(who + " printed " + this.name + ": " + this.counter);
    }
}
