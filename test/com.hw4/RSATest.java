package com.hw4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

public class RSATest {

    @Test
    public void modExp() {
        BigInteger a = new BigInteger("87654321");
        BigInteger b = new BigInteger("98765432");
        BigInteger m = new BigInteger("9876543");

        Assertions.assertEquals(a.modPow(b,m).toString(), RSA.modExp(87654321, 98765432, 9876543));
    }

}





