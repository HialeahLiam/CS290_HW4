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

        Matching residency = new Matching("resources/hospitals.csv", "resources/residents.csv", "resources/capacities.csv");
        Matching exampleMatching = new Matching("resources/smallHospitals.csv", "resources/smallResidents.csv", "resources/smallCapacities.csv");

        //making array to track top choices
        int[] topPreferences = residency.hospitalTopPicks();

        for (int i=0; i<9; i++) {
            System.out.println("Number of top picks for Hospital " + i + ":\t" + topPreferences[i]);
        }


        exampleMatching.BMA();
        residency.BMA();

    }
}
