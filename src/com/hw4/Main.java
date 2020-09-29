package com.hw4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        String[] decryptionStrings = RSA.getDecryptionStrings("resources/secretMessage.txt", "resources/publicKey.txt", "resources/privateKey.txt");
        String originalMessage = RSA.decrypt(decryptionStrings[0], decryptionStrings[2], decryptionStrings[1]);
        System.out.println("Original message:\t" + originalMessage);

        Scanner hospitals = new Scanner(new File("resources/hospitals.csv"));
        Scanner residents = new Scanner(new File("resources/residents.csv"));
        Scanner capacities = new Scanner(new File("resources/capacities.csv"));

        hospitals.useDelimiter(",|\n");
        residents.useDelimiter(",|\n");
//        System.out.println(capacities.next());
//        System.out.println(capacities.next());

        //making array to track top choices
        int[] topPreferences = new int[10];

        while (residents.hasNext()) {
            topPreferences[Integer.parseInt(residents.next())]++;
            for (int i=0; i<9; i++) residents.next();
        }

        for (int i=0; i<9; i++) {
            System.out.println("Number of top picks for Hospital " + i + ":\t" + topPreferences[i]);
        }



    }
}
