package com.hw4;

import java.math.BigInteger;

public class RSA {

    public static String modExp(String a, String b, String m) {
        BigInteger x = new BigInteger(a);
        BigInteger y = new BigInteger("1");
        BigInteger M  = new BigInteger(m);
        int z = Integer.parseInt(b);
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

//    public static String decrypt(String encryptedMsg, String pubKeyN, String e) {
//        BigInteger y = new BigInteger(encryptedMsg);
//        BigInteger N = new BigInteger(pubKeyN);
//
//        y = y.pow(Integer.parseInt(e));
//        y = y.mod(N);
//
//    }

}
