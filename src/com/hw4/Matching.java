package com.hw4;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.PublicKey;
import java.util.Scanner;

public class Matching {

    public static final int NUMBER_OF_HOSPITALS = 10;
    public static final int NUMBER_OF_RESIDENTS = 1000;

    private String hospitalFile;
    private String residentFile;
    private String capacityFile;

    public Matching(String hospitalFile, String residentFile, String capacityFile) {
        this.hospitalFile = hospitalFile;
        this.residentFile = residentFile;
        this.capacityFile = capacityFile;
    }

    public int[] hospitalTopPicks() throws FileNotFoundException {

        Scanner hospitals = new Scanner(new File(hospitalFile));
        Scanner residents = new Scanner(new File(residentFile));

        hospitals.useDelimiter(",|\n");
        residents.useDelimiter(",|\n");

        int[] topPreferences = new int[10];

        while (residents.hasNext()) {
            topPreferences[Integer.parseInt(residents.next())]++;
            for (int i=0; i<9; i++) residents.next();
        }

        return topPreferences;
    }

}
