package com.seb.pentathlonscores.utils;

public final class SecondsMilliConverter {

    private static final double MILLI_IN_SECOND = 1000.0;

    public static double milliToSeconds(int milli) {
       if(milli == 0) {
            return 0;
       }
       return milli / MILLI_IN_SECOND;
    }

    public static double secondsToMilli(int seconds) {
        return seconds * MILLI_IN_SECOND;
    }
}
