package com.hw4;

import java.math.BigInteger;

public class RSA {

    public String modExp(int a, int b, int m) {
        BigInteger x = new BigInteger("a");
        BigInteger x = new BigInteger("a");
        int y = 1;
        int z = b;
        while (z > 0) {
            int r = z % 2;
            z /= 2;
            if (r == 1) y = x * y % m;
            x = x * x % m;
        }
        return y;
    }

}
