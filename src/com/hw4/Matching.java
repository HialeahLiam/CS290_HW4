package com.hw4;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Matching {

    public static final int NUMBER_OF_HOSPITALS = 10;
    public static final int NUMBER_OF_RESIDENTS = 1000;

    private String hospitalFile;
    private String residentFile;
    private String capacityFile;
    public String[][] residentPrefs;

    public Matching(String hospitalFile, String residentFile, String capacityFile) throws FileNotFoundException {
        this.hospitalFile = hospitalFile;
        this.residentFile = residentFile;
        this.capacityFile = capacityFile;

        Scanner residents = new Scanner(new File(residentFile));;
        residents.useDelimiter(",|\n");
        residentPrefs = new String[NUMBER_OF_RESIDENTS][NUMBER_OF_HOSPITALS];
        for (int i = 0; i < NUMBER_OF_RESIDENTS; i++) {
            for (int j = 0; j <  NUMBER_OF_HOSPITALS; j++) {
                residentPrefs[i][j] = residents.next();
            }
        }
    }

    public String[][] BMA() throws FileNotFoundException {
        Scanner hospitals = new Scanner(new File(hospitalFile));
        Scanner capacities = new Scanner(new File(capacityFile));
        hospitals.useDelimiter(",|\n");
        capacities.useDelimiter(",|\n");

        String[][] finalResidencies = new String[NUMBER_OF_HOSPITALS][];
        List<String[]> applications = new ArrayList<>(NUMBER_OF_HOSPITALS);
        int[] remainingCapacities = new int[NUMBER_OF_HOSPITALS]; //ith cell corresponds to ith hospital
        //getting initial capacities
        for (int i=0; i<NUMBER_OF_HOSPITALS; i++) remainingCapacities[i] = Integer.parseInt(capacities.next());


        for (int i = 0; i < 10; i++) {
            System.out.print(" " + residentPrefs[999][i]);
        }
//        List<String> applicants = new ArrayList<>();
//        for ()

        return finalResidencies;

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
