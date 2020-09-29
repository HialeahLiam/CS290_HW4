package com.hw4;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.PublicKey;
import java.util.*;

public class Matching {

    public final int NUMBER_OF_HOSPITALS;
    public final int NUMBER_OF_RESIDENTS;
    private static final String DELIMITER = ",";

    private final String hospitalFile;
    private final String residentFile;
    private final String capacityFile;
    public final String[][] residentPrefs;
    public int[] remainingCapacities;

    public Matching(String hospitalFile, String residentFile, String capacityFile) throws FileNotFoundException {
        this.hospitalFile = hospitalFile;
        this.residentFile = residentFile;
        this.capacityFile = capacityFile;


        Scanner residents = getScanner(residentFile);
        Scanner hospitals = getScanner(hospitalFile);
        Scanner capacities = getScanner(capacityFile);

        int numberOfHospitals = 0;
        while (hospitals.hasNext()) {
            hospitals.nextLine();
            numberOfHospitals++;
        }
        NUMBER_OF_HOSPITALS = numberOfHospitals;

        int numberOfResidents = 0;
        while (residents.hasNext()) {
            residents.nextLine();
            numberOfResidents++;
        }
        NUMBER_OF_RESIDENTS = numberOfResidents;

        //converting resident preferences into 2d array
        residents = getScanner(residentFile);
        residentPrefs = new String[NUMBER_OF_RESIDENTS][NUMBER_OF_HOSPITALS];
        for (int i = 0; i < NUMBER_OF_RESIDENTS; i++) {
            String[] r = residents.nextLine().split(",");
            for (int j = 0; j <  NUMBER_OF_HOSPITALS; j++) {
                residentPrefs[i][j] = r[j];
            }
        }

        //getting initial capacities
        remainingCapacities = new int[NUMBER_OF_HOSPITALS]; //ith cell corresponds to ith hospital
        for (int i=0; i<NUMBER_OF_HOSPITALS; i++) {
            String s = capacities.nextLine();
            remainingCapacities[i] = Integer.parseInt(s);
        }
    }

    public String[][] BMA() throws FileNotFoundException {
        Scanner hospitals = getScanner(hospitalFile);
        Scanner capacities = getScanner(capacityFile);

//        Set<Integer> hospitalsAtCapacity = new HashSet<>();
//        Set<Integer> residentsAlreadyAccepted = new HashSet<>();

        //Actual BMA

        String[][] finalResidencies = new String[NUMBER_OF_HOSPITALS][];
        return finalResidencies;

    }

    public ArrayList[] application(int[] remainingCapacities) {

        ArrayList<Integer>[] applications = new ArrayList[NUMBER_OF_HOSPITALS];
        for (int i = 0; i < applications.length; i++) {
            applications[i] = new ArrayList<>();
        }

        for (int r = 0; r < NUMBER_OF_RESIDENTS; r++) {
            int preference = 0;
            while (remainingCapacities[Integer.parseInt(residentPrefs[r][preference])] == 0) {
                preference++;
            }
            int preferredHospital = Integer.parseInt(residentPrefs[r][preference]);
            applications[preferredHospital].add(r);
        }

        return applications;

    }

    public Scanner getScanner(String file) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(file));
        scanner.useDelimiter(DELIMITER);
        return scanner;
    }

}
