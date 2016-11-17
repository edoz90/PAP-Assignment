/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pap.ass05.cooperativeTeam;

import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * @author edoardo
 */
public class W5 extends Worker {

    public W5(String name, List<Semaphore> sem, List<UnsafeCounter> count) {
        super(name, sem, count);
    }

    @Override
    public void run() {
        while (!stop) {
            try {
                sem.get(4).acquire();
                sem.get(4).acquire();
                counters.get(2).print(this.getName());
                sem.get(0).release();
                sem.get(1).release();
            } catch (InterruptedException ex) {
            } finally {
                sem.get(4).release();
                sem.get(4).release();
            }
        }
    }
}
