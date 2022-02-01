package com.technicaltest.instrumentsim;

import com.technicaltest.instrumentsim.instrument.Instrument;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Sampler extends Thread {

    private static Instrument thread;

    private static boolean threadActive;
    private static boolean pollReadings;

    private static IntegerProperty readingVal;

    Sampler(Instrument _thread) {
        thread = _thread;
        threadActive = true;
        pollReadings = false;
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

    @Override
    public void run() {
        System.out.println("Sampler Thread Start");
        while (threadActive) {
            try {
                sleep(0);
                while (pollReadings) {
                    readingVal.setValue(thread.getReading());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void stopThread()
    {
        System.out.println("Stopping Sampler Thread");
        pollReadings = false;
        threadActive = false;
    }
}
