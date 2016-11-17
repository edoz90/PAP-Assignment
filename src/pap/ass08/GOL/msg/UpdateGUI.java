package pap.ass08.GOL.msg;

/**
 * @author edoardo
 */
public class UpdateGUI {

    public long time;
    public int size;

    public UpdateGUI(int size, long timeRemains) {
        this.size = size;
        this.time = timeRemains;
    }

}
