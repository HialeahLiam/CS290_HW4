package com.hw4;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.PublicKey;
import java.util.*;

public class Matching {

    public final int NUMBER_OF_HOSPITALS;
    public final int NUMBER_OF_RESIDENTS;
    private static final String DELIMITER = ",";

    private String hospitalFile;
    private String residentFile;
    private String capacityFile;
    public String[][] residentPrefs;

    public Matching(String hospitalFile, String residentFile, String capacityFile) throws FileNotFoundException {
        this.hospitalFile = hospitalFile;
        this.residentFile = residentFile;
        this.capacityFile = capacityFile;


        Scanner residents = new Scanner(new File(residentFile));;
        residents.useDelimiter(DELIMITER);
        Scanner hospitals = new Scanner(new File(hospitalFile));;
        residents.useDelimiter(DELIMITER);

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

        residents = new Scanner(new File(residentFile));
        residents.useDelimiter(DELIMITER);

        residentPrefs = new String[NUMBER_OF_RESIDENTS][NUMBER_OF_HOSPITALS];
        for (int i = 0; i < NUMBER_OF_RESIDENTS; i++) {
            String[] r = residents.nextLine().split(",");
            for (int j = 0; j <  NUMBER_OF_HOSPITALS; j++) {
                residentPrefs[i][j] = r[j];
            }
        }
    }

    public String[][] BMA() throws FileNotFoundException {
        Scanner hospitals = new Scanner(new File(hospitalFile));
        Scanner capacities = new Scanner(new File(capacityFile));
        hospitals.useDelimiter(DELIMITER);
        capacities.useDelimiter(DELIMITER);

        String[][] finalResidencies = new String[NUMBER_OF_HOSPITALS][];
        ArrayList<Integer>[] applications = new ArrayList[NUMBER_OF_HOSPITALS];
        for (int i = 0; i < applications.length; i++) {
            applications[i] = new ArrayList<>();
        }
        Set<Integer> hospitalsAtCapacity = new HashSet<>();
        Set<Integer> residentsAlreadyAccepted = new HashSet<>();
        int[] remainingCapacities = new int[NUMBER_OF_HOSPITALS]; //ith cell corresponds to ith hospital

        //getting initial capacities
        for (int i=0; i<NUMBER_OF_HOSPITALS; i++) {
            String s = capacities.nextLine();
            remainingCapacities[i] = Integer.parseInt(s);
        }

        //Actual BMA
        for (int r = 0; r < NUMBER_OF_RESIDENTS; r++) {
            int preference = 0;
            while (remainingCapacities[Integer.parseInt(residentPrefs[r][preference])] == 0) {
                preference++;
            }
            int preferredHospital = Integer.parseInt(residentPrefs[r][preference]);
            applications[preferredHospital].add(r);
        }

        System.out.println("First choices that each hospital received:");
        for (int i = 0; i < NUMBER_OF_HOSPITALS; i++) {
            System.out.println("Hospital " + i + ":\t" + applications[i].size());
        }

        return finalResidencies;

    }

}
