package com.technicaltest.instrumentsim;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Timer;

public class Sampler extends Thread {

    private static Instrument thread;

    //private static int readingVal;
    private static boolean updatedResult;

    private static boolean threadActive;
    private static boolean pollReadings;

    private volatile static IntegerProperty readingVal;

    Sampler(Instrument _thread) {
        thread = _thread;
        threadActive = true;
        pollReadings = false;
        updatedResult = false;
        readingVal = new SimpleIntegerProperty(0);
    }

    public void startPolling() {
        pollReadings = true;
    }

    public void stopPolling() {
        pollReadings = false;
    }

    public IntegerProperty getReadingIntegerProperty() {
        return readingVal;
    }

    public int getReadingVal() {
        updatedResult = false;
        return readingVal.getValue();
    }

    public boolean getUpdatedResult() {
        return updatedResult;
    }

    public void stopThread() {
        threadActive = false;
    }

    @Override
    public void run() {
        System.out.println("Sampler Thread Start");
        while (threadActive) {
            try {
                sleep(0);
                while (pollReadings) {
                    System.out.println("Getting a reading");
                    readingVal.setValue(thread.getReading());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
