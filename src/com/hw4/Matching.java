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
    public final int[][] residentPrefs;
    public final int[][] hospitalPrefs;
    private int[][] finalResidencies;

    public final int[] initialCapacities;
    public int[] remainingCapacities;
    private Set<Integer> hospitalsAtCapacity = new HashSet<>();
    private Set<Integer> residentsAlreadyAccepted = new HashSet<>();

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
        residentPrefs = new int[NUMBER_OF_RESIDENTS][NUMBER_OF_HOSPITALS];
        for (int i = 0; i < NUMBER_OF_RESIDENTS; i++) {
            String[] r = residents.nextLine().split(",");
            for (int j = 0; j <  NUMBER_OF_HOSPITALS; j++) {
                residentPrefs[i][j] = Integer.parseInt(r[j]);
            }
        }

        //converting hospital preferences into 2d array
        hospitals = getScanner(hospitalFile);
        hospitalPrefs = new int[NUMBER_OF_HOSPITALS][NUMBER_OF_RESIDENTS];
        for (int i = 0; i < NUMBER_OF_HOSPITALS; i++) {
            String[] r = hospitals.nextLine().split(",");
            for (int j = 0; j <  NUMBER_OF_RESIDENTS; j++) {
                hospitalPrefs[i][j] = Integer.parseInt(r[j]);
            }
        }

        //getting initial capacities
        initialCapacities = new int[NUMBER_OF_HOSPITALS]; //ith cell corresponds to ith hospital
        for (int i=0; i<NUMBER_OF_HOSPITALS; i++) {
            String s = capacities.nextLine();
            initialCapacities[i] = Integer.parseInt(s);
        }
    }

    public int[][] BMA() throws FileNotFoundException {
        Scanner hospitals = getScanner(hospitalFile);
        Scanner capacities = getScanner(capacityFile);


        //Actual BMA
        int[][] finalResidencies = new int[NUMBER_OF_HOSPITALS][];
        for (int i = 0; i < NUMBER_OF_HOSPITALS; i++) {
            finalResidencies[i] = new int[initialCapacities[i]];
        }

        remainingCapacities = new int[initialCapacities.length];
        System.arraycopy(initialCapacities, 0, remainingCapacities, 0, initialCapacities.length);

        while (residentsAlreadyAccepted.size() < NUMBER_OF_RESIDENTS) {
            ArrayList[] applications = application(remainingCapacities);
            for (int i = 0; i < NUMBER_OF_HOSPITALS; i++) {     //loops through hospitals
                for (int j = 0; j < NUMBER_OF_RESIDENTS; j++) {     //loops through a hospital's preferences
                    if (!residentsAlreadyAccepted.contains(hospitalPrefs[i][j])) {//skips accepted residents when searching through hospital's prefs
                        for (int k = 0; k < applications[i].size(); k++) {      //loops through residents that applied to hospital in this round
                            int applicant = (int) applications[i].get(k);
                            if (applicant == (hospitalPrefs[i][j])) {
                                finalResidencies[i][initialCapacities[i] - remainingCapacities[i]] = applicant; // residents matching higher hospital prefs are inserted first
                                remainingCapacities[i]--;
                                residentsAlreadyAccepted.add(applicant);
                                applications[i].remove(k);
                                break;
                            }
                        }
                    }
                    if (remainingCapacities[i] < 1 || applications[i].size() < 1) break;
                }
            }
        }

        this.finalResidencies = finalResidencies;
        return finalResidencies;

    }

    public void printResidencies(){
        for (int i = 0; i < NUMBER_OF_HOSPITALS ; i++) {
            System.out.print("Hospital " + i + ":\t");
            for (int j = 0; j < initialCapacities[i]; j++) {
                System.out.print("|" + finalResidencies[i][j]);
            }
            System.out.println() ;
        }
    }


    public ArrayList[] application(int[] remainingCapacities) {

        ArrayList<Integer>[] applications = new ArrayList[NUMBER_OF_HOSPITALS];
        for (int i = 0; i < applications.length; i++) {
            applications[i] = new ArrayList<>();
        }

        for (int r = 0; r < NUMBER_OF_RESIDENTS; r++) {
            if (!residentsAlreadyAccepted.contains(r)) { //skips residents who have already been matched
                int preference = 0;
                while (remainingCapacities[residentPrefs[r][preference]] == 0) {
                    preference++;
                }
                int preferredHospital = residentPrefs[r][preference];
                applications[preferredHospital].add(r);
            }
        }

        return applications;

    }

    public Scanner getScanner(String file) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(file));
        scanner.useDelimiter(DELIMITER);
        return scanner;
    }

}
