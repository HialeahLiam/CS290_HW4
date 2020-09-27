package com.hw4;

import java.math.BigInteger;

public class RSA {

    public static String modExp(int a, int b, int m) {
        BigInteger x = new BigInteger(String.valueOf(a));
        BigInteger y = new BigInteger("1");
        BigInteger M  = new BigInteger(String.valueOf(m));
        int z = b;
        while (z > 0) {
            int r = z % 2;
            z /= 2;
            if (r == 1) {
                y = y.multiply(x);
                y = y.mod(M);
            }
            x = x.multiply(x);
            x = x.mod(M);
        }
        return y.toString();
    }

}
