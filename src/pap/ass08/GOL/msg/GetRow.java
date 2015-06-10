package pap.ass08.GOL.msg;

/**
 *
 * @author edoardo
 */
public class GetRow {

    public final String type;
    public final boolean[] row;
    
    public GetRow(String t) {
        this.type = t;
        this.row = null;
    }
    
    public GetRow(String t, boolean[] r) {
        this.type = t;
        this.row = r;
    }
    
}
