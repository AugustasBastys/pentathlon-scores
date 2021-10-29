package com.seb.pentathlonscores.utils;

public final class TenthOfSecNanoConverter {

   private static final int NANO_IN_TENTH_OF_SECOND = 100000000;

   public static int tenthOfSecondToNano(int tSecond) {
       return tSecond * NANO_IN_TENTH_OF_SECOND;
   }

    public static int NanoToTenthOfSecond(int nano) {
       if(nano == 0) {
           return 0;
       }
        return nano / NANO_IN_TENTH_OF_SECOND;
    }

}
