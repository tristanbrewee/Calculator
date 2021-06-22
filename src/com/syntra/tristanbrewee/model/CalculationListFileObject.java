package com.syntra.tristanbrewee.model;

import java.io.Serializable;
import java.util.HashSet;

/**
 * A CalculationListFileObject object with as params:
 * fileName (String)
 * calculations (HashSet<Calculation)
 * readFromFile (Boolean)
 */
public class CalculationListFileObject implements Serializable {

    private String fileName;
    private HashSet<Calculation> calculations;
    private boolean readFromFile = false;

    //Constructors
    /**
     * @param fileName - The desired file name to read from/to
     * @param calculations - A HashSet of Calculations
     * Creates a CalculationListFileObject object with the given params.
     * readFromFile is false by default
     */
    public CalculationListFileObject(String fileName, HashSet<Calculation> calculations) {
        this.fileName = fileName;
        this.calculations = calculations;
    }

    //Getters
    /**
     * @return returns the file name (String)
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @return returns calculations (HashSet<Calculation>)
     */
    public HashSet<Calculation> getCalculations() {
        return calculations;
    }

    /**
     * @return returns readFromFile
     */
    public boolean isReadFromFile() {
        return readFromFile;
    }

    //Setters
    /**
     * @param fileName - A String
     * sets fileName with the given param
     */
    public void setFileName(String fileName){
        this.fileName = fileName;
    }

    /**
     * @param calculations - A HashSet<Calculation>
     * sets calculations with the given param
     */
    public void setCalculations(HashSet<Calculation> calculations) {
        this.calculations = calculations;
    }

    /**
     * @param readFromFile - A Boolean
     * sets readFromFile with the given param
     */
    public void setReadFromFile(boolean readFromFile) {
        this.readFromFile = readFromFile;
    }
}
