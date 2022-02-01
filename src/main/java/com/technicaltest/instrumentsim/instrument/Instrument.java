package com.technicaltest.instrumentsim.instrument;

import java.util.concurrent.atomic.AtomicInteger;

public class Instrument extends Thread implements InstrumentInterface {

    private static boolean running;
    private static boolean initialReading;
    private static AtomicInteger randomNumber;

    public Instrument()
    {
        running = true;
        initialReading = true;
        randomNumber = new AtomicInteger();
    }

    @Override
    public void run() {
        System.out.println("Running counter thread");

        while (running) {
            randomNumber.set((int) (Math.random() * 1000));
        }
    }

    public int getReading() throws InterruptedException {

        if (initialReading) {
            initialReading = false;
            return randomNumber.get();
        }

        long waitTime = (int) (Math.random() * 4) * 1000;
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return randomNumber.get();
    }

    public static void stopThread() {
        System.out.println("Stopping Instrument thread.");
        running = false;
    }
}
