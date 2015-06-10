package pap.ass08.GOL.msg;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author edoardo
 */
public class WorkDone {
    
    public String type;
    public ArrayList<Point> result;
    
    public WorkDone(String w) {
        this.type = w;
    }
    
    public WorkDone(String w, ArrayList<Point> r) {
        this.type = w;
        this.result = r;
    }
    
}
