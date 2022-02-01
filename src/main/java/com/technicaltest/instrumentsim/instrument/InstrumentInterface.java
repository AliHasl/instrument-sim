package com.technicaltest.instrumentsim.instrument;

public interface InstrumentInterface {

    /**
     * Returns a random integer between 0 and 999 from an active thread
     * then sleeps the thread for up to two seconds.
     *
     * @return Random integer between 0 and 999
     * @throws InterruptedException Thrown if thread interrupted
     */
    int getReading() throws InterruptedException;
}
