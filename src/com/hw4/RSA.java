package com.hw4;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Scanner;
import java.lang.Object;

public class RSA {

    public static String[] getDecryptionStrings(String messageFile, String publicFile, String privateFile) throws FileNotFoundException {
        String[] out = new String[3];

        Scanner scanner = new Scanner(new File(messageFile));
        out[0] = scanner.next();

        scanner = new Scanner(new File(privateFile));
        out[2] = scanner.next();

        scanner = new Scanner(new File(publicFile));
        scanner.useDelimiter(",");
        out[1] = scanner.next();

        return out;
    }

    public static String modExp(String a, String b, String m) {
        BigInteger x = new BigInteger(a);
        BigInteger y = new BigInteger("1");
        BigInteger M = new BigInteger(m);
        BigInteger z = new BigInteger(b);
        BigInteger zero = new BigInteger("0");
        BigInteger one = new BigInteger("1");
        BigInteger two = new BigInteger("2");

        while (z.compareTo(zero) == 1) {
            BigInteger r = z.mod(two);
            z = z.divide(two);
            if (r.compareTo(one) == 0) {
                y = y.multiply(x);
                y = y.mod(M);
            }
            x = x.multiply(x);
            x = x.mod(M);
        }
        return y.toString();
    }

    public static String decrypt(String encryptedMsg, String N, String privateKey) {
        return modExp(encryptedMsg, N, privateKey);
    }

}
