package com.hw4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        String[] decryptionStrings = RSA.getDecryptionStrings("resources/secretMessage.txt", "resources/publicKey.txt", "resources/privateKey.txt");
        String originalMessage = RSA.decrypt(decryptionStrings[0], decryptionStrings[2], decryptionStrings[1]);
        System.out.println("Original message:\t" + originalMessage);

        Matching residency = new Matching("resources/hospitals.csv", "resources/residents.csv", "resources/capacities.csv");
        Matching exampleMatching = new Matching("resources/smallHospitals.csv", "resources/smallResidents.csv", "resources/smallCapacities.csv");

//        ArrayList[] topPicks = exampleMatching.application(exampleMatching.remainingCapacities);
        ArrayList[] topPicks = residency.application(residency.initialCapacities);

        System.out.println("First choices for hospitals");
        for (int i = 0; i < topPicks.length; i++) {
            System.out.println("Hospital " + i + ":\t" + topPicks[i].size());
        }
        exampleMatching.BMA();
        exampleMatching.printResidencies();
        exampleMatching.preferenceMatches();
        exampleMatching.DMA();
        exampleMatching.printResidencies();
        exampleMatching.preferenceMatches();

        residency.BMA();
        residency.printResidencies();
        residency.preferenceMatches();
        residency.DMA();
        residency.printResidencies();
        residency.preferenceMatches();





    }
}
