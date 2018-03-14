package pap.ass08.tempSensor;

/**
 * @author edoardo
 */
public class Main {

    public static void main() {
        AvgTemp avgTemp = new AvgTemp(3);
        Controller.getController(avgTemp);
        GUI.main();
    }
}
