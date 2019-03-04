package br.com.enjoeichallenge.models.util;

public class SQLiteLockThread {

    private boolean isLocked = false;

    public synchronized void lock(){

        while(isLocked){
            try {

                wait();

            } catch (InterruptedException e) {}
        }

        isLocked = true;

    }

    public synchronized void unlock(){
        isLocked = false;
        notify();
    }
}
