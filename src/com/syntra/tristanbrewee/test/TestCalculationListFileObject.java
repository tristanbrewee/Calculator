package com.syntra.tristanbrewee.test;

import com.syntra.tristanbrewee.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class TestCalculationListFileObject {

    private HashSet<Calculation> calculations;
    private CalculationListFileObject globalCalculationListFileObject;

    @BeforeEach
    public void setUp(){
        Calculation calculation1 = new Calculation(1.0, 1.0, "+");
        Calculation calculation2 = new Calculation(1.0, 1.0, "-");
        Calculation calculation3 = new Calculation(1.0, 1.0, "*");
        Calculation calculation4 = new Calculation(1.0, 1.0, "/");
        Calculation calculation5 = new Calculation(1.0, 1.0, "^");
        calculations = new HashSet<>();
        calculations.add(calculation1);
        calculations.add(calculation2);
        calculations.add(calculation3);
        calculations.add(calculation4);
        calculations.add(calculation5);
        globalCalculationListFileObject = new CalculationListFileObject("test.txt", calculations);
    }

    @AfterEach
    public void tearDown(){
        globalCalculationListFileObject = null;
        calculations = null;
    }

    @Test
    public void constructor(){
        CalculationListFileObject calculationListFileObject = new CalculationListFileObject("test.txt", calculations);
        assertTrue(calculationListFileObject.getCalculations().equals(calculations));
        assertTrue(calculationListFileObject.getFileName().equals("test.txt"));
    }

    @Test
    public void getCalculations(){
        assertTrue(globalCalculationListFileObject.getCalculations().equals(calculations));
    }

    @Test
    public void getFileName(){
        assertTrue(globalCalculationListFileObject.getFileName().equals("test.txt"));
    }

    @Test
    public void getReadFromFile(){
        assertFalse(globalCalculationListFileObject.isReadFromFile());
    }

    @Test
    public void setReadFromFile(){
        globalCalculationListFileObject.setReadFromFile(true);
        assertTrue(globalCalculationListFileObject.isReadFromFile());
    }

    @Test
    public void setFileName(){
        globalCalculationListFileObject.setFileName("new.txt");
        assertTrue(globalCalculationListFileObject.getFileName().equals("new.txt"));
    }

    @Test
    public void setCalculations(){
        Calculation calculation = new Calculation(5.0, 5.0, "^");
        HashSet<Calculation> newCalculations = new HashSet<>();
        newCalculations.add(calculation);
        globalCalculationListFileObject.setCalculations(newCalculations);
        assertTrue(globalCalculationListFileObject.getCalculations().equals(newCalculations));
    }
}
