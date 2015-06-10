/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pap.ass05.minDistance;

/**
 *
 * 3-dimensional point
 * objects are completely state-less
 *
 */
public class P3d implements java.io.Serializable {

    public int x,y,z;

    public P3d(int x,int y, int z){
        this.x=x;
        this.y=y;
        this.z = z;
    }

    public double distance(P3d a){
        P3d dis = new P3d(x-a.x, y-a.x, z-a.z);
        return (double) Math.sqrt(dis.x * dis.x + dis.y * dis.y + dis.z * dis.z);        
    }

    public String toString(){
        return "P3d("+x+","+y+","+z+")";
    }

}