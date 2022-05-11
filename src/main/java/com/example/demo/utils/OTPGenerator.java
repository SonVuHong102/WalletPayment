package com.example.demo.utils;

import java.util.Random;

public class OTPGenerator {
    public static String genOTP() {
        Random rand = new Random(); //instance of random class
        int upperbound = 1000;
        return rand.nextInt(upperbound) + "";
    }
}
