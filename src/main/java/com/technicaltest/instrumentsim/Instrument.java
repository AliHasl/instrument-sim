package com.technicaltest.instrumentsim;

import java.util.concurrent.atomic.AtomicInteger;

public class Instrument extends Thread {

    static boolean running = true;
    private static  AtomicInteger randomNumber;

    Instrument()
    {
        randomNumber = new AtomicInteger();
    }

    @Override
    public void run() {
        System.out.println("Running counter thread");

        while (running) {
            randomNumber.set((int)(Math.random() * 1000));
        }
    }

    private static boolean initialReading = true;

    public int getReading() throws InterruptedException {

        if (initialReading) {
            initialReading = false;
            return  randomNumber.get();
        }

        long waitTime = (int) (Math.random() * 5) * 1000;
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return randomNumber.get();
    }

    public void stopThread() {
        running = false;
    }
}
