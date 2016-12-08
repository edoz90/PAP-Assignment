package pap.ass06.GOL;

/**
 * @author edoardo
 */
public class Main {

    public static final int COLS = 70;
    public static final int ROWS = 30;
    public static final int CORE = Runtime.getRuntime().availableProcessors() + 1;

    public static void main(String[] args) {
        ControllerGOF c = ControllerGOF.getInstance(ROWS, COLS, CORE);
        args = new String[3];
        args[0] = ROWS + "";
        args[1] = COLS + "";
        args[2] = CORE + "";
        Viewer.main(args);
    }
}
