package com.example.codingconfessional.utils;

public final class DecryptUtils {

    static {
        System.loadLibrary("decrypt");
    }

    public static native String decrypt(String omg);
}
