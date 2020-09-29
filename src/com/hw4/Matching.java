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
        Set<Integer> hospitalsAtCapacity = new HashSet<>();
        Set<Integer> residentsAlreadyAccepted = new HashSet<>();
        int[] remainingCapacities = new int[NUMBER_OF_HOSPITALS]; //ith cell corresponds to ith hospital

        //getting initial capacities
//        for (int i=0; i<NUMBER_OF_HOSPITALS; i++) {
//            remainingCapacities[i] = capacities.nextInt();
//        }

//        //Actual BMA
//        for (int r = 0; r < NUMBER_OF_RESIDENTS; r += NUMBER_OF_HOSPITALS) {
//            int preference = 0;
//            while (remainingCapacities[Integer.parseInt(residentPrefs[r][preference])] == 0) {
//                preference++;
//            }
//            if (applications[preference] == null) applications[preference] = new ArrayList<>();
//            applications[preference].add(r);
//        }
//
//        for (ArrayList hospital : applications) {
//            System.out.println();
//            for (Object applicant : hospital) System.out.print(" " + applicant);
//
//        }

        return finalResidencies;

    }

    public int[] hospitalTopPicks() throws FileNotFoundException {

        Scanner hospitals = new Scanner(new File(hospitalFile));
        Scanner residents = new Scanner(new File(residentFile));
        hospitals.useDelimiter(DELIMITER);
        residents.useDelimiter(DELIMITER);

        int[] topPreferences = new int[10];

        while (residents.hasNext()) {
            topPreferences[Integer.parseInt(residents.next())]++;
            for (int i=0; i<9; i++) residents.next();
        }

        return topPreferences;
    }

}
