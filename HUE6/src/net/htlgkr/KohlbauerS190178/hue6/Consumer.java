/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.htlgkr.KohlbauerS190178.hue6;

import java.util.ArrayList;
import java.util.List;


public class Consumer implements Runnable {
    private final String name;
    private final Storage storage;
    private final int sleepTime;

    public Consumer(String name, Storage storage, int sleepTime) {
        this.name = name;
        this.storage = storage;
        this.sleepTime = sleepTime;
        this.received = new ArrayList<>();
        running = true;
    }

    private final List<Integer> received;
    private boolean running;


    public List<Integer> getReceived() {
        return received;
    }

    @Override
    public void run() {


        while (!storage.isProductionComplete() && running) {


            Integer n = storage.get();

            if (n != null)
                received.add(n);
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Integer integer = storage.get();
        if (integer != null) {
            received.add(integer);
            integer = storage.get();
            while (integer != null) {
                received.add(integer);
                integer = storage.get();
            }
        }


    }
}

