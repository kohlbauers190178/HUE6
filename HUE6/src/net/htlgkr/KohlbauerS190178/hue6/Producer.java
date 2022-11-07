/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.htlgkr.KohlbauerS190178.hue6;

import java.util.ArrayList;
import java.util.List;

public class Producer implements Runnable {
    private final String name;
    private final Storage storage;
    private final int sleepTime;

    private final List<Integer> sent;
    private final int numberOfItems;

    public Producer(String name, Storage storage, int sleepTime, int numberOfItems) {
        this.name = name;
        this.storage = storage;
        this.sleepTime = sleepTime;
        this.numberOfItems = numberOfItems;
        this.sent = new ArrayList<>();
    }

    public List<Integer> getSent() {
        return sent;
    }

    @Override
    public void run() {


           /*reidinger code for(int i = 0; i < numberOfItems; i++){
                try {
                    boolean right = storage.put(i);

                    while(!right){
                        Thread.sleep(sleepTime);
                        right = storage.put(i);
                    }

                    sent.add(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            storage.setProductionComplete();*/


        for (int i = 0; i < numberOfItems; i++) {
            try {
                while (!storage.put(i)) {
                    System.out.println("I DUA SCHLAFEN");
                    Thread.sleep(sleepTime);
                }

                sent.add(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("i bi fechde midn produziern");
        storage.setProductionComplete();
    }
}
