package com.hw4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        String[] decryptionStrings = RSA.getDecryptionStrings("resources/secretMessage.txt", "resources/publicKey.txt", "resources/privateKey.txt");
        String originalMessage = RSA.decrypt(decryptionStrings[0], decryptionStrings[2], decryptionStrings[1]);
        System.out.println("Original message:\t" + originalMessage);
    }
}
