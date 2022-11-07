/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.htlgkr.KohlbauerS190178.hue6;

import java.util.concurrent.ArrayBlockingQueue;

public class Storage { 
    private final ArrayBlockingQueue<Integer> queue;
    
    private int fetchedCounter;
    private int storedCounter;
    private int underflowCounter;
    private int overflowCounter;
    private boolean productionComplete;

    public Storage() {
        this.queue = new ArrayBlockingQueue<>(10);
        fetchedCounter=0;
        storedCounter=0;
        underflowCounter=0;
        overflowCounter=0;
        productionComplete=false;
    }
    
    public synchronized boolean put(Integer data) throws InterruptedException {

        if(queue.remainingCapacity()==0){
            overflowCounter++;
            return false;
        }

        queue.put(data);
        storedCounter++;
        return true;
    }
 
    public synchronized Integer get() {
        if(queue.peek()==null){
            underflowCounter++;
            return null;
        }
        fetchedCounter++;
        return queue.poll();
    }

    public boolean isProductionComplete() {
        return productionComplete;
    }

    public void setProductionComplete() {
        productionComplete=true;
    }

    public int getFetchedCounter() {
        return fetchedCounter;
    }

    public int getStoredCounter() {
        return storedCounter;
    }

    public int getUnderflowCounter() {
        return underflowCounter;
    }

    public int getOverflowCounter() {
        return overflowCounter;
    }
}
