package pap.ass04.textball;

/**
 *
 * 2-dimensional point
 * objects are completely state-less
 *
 */
public class P2d implements java.io.Serializable {

    public int x,y;

    public P2d(int x,int y){
        this.x=x;
        this.y=y;
    }

    public P2d sum(V2d v){
        return new P2d(x+v.x,y+v.y);
    }

    public V2d sub(P2d v){
        return new V2d(x-v.x,y-v.y);
    }

    public String toString(){
        return "P2d("+x+","+y+")";
    }

}