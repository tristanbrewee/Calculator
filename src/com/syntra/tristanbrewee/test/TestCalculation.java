package com.syntra.tristanbrewee.test;

import com.syntra.tristanbrewee.model.Calculation;
import com.syntra.tristanbrewee.model.Operator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

public class TestCalculation {

    private static Random random;
    private static Calculation globalCalculation;

    @BeforeEach
    public void setUp(){
        random = new Random();
        globalCalculation = new Calculation(2.0, 5.0, "+");
    }

    @AfterEach
    public void tearDown(){
        random = null;
        globalCalculation = null;
    }

    //Constructors
    @Test
    public void constructorWithParam(){
        Calculation calculation = new Calculation(2.0, 5.0, "+");
        assertTrue(calculation instanceof Calculation);
        assertTrue(calculation.getNumber1() == 2.0);
        assertTrue(calculation.getNumber2() == 5.0);
        assertTrue(calculation.getOperator().equals(Operator.ADD));
    }

    @Test
    public void constructorRandom(){
        Calculation calculation = new Calculation(random);
        assertTrue(calculation instanceof Calculation);
        assertTrue(calculation.getOperator() instanceof Operator);
    }

    //Getters
    @Test
    public void getNumber1(){
        assertEquals(globalCalculation.getNumber1(), 2.0);
    }

    @Test
    public void getNumber2(){
        assertEquals(globalCalculation.getNumber2(), 5.0);
    }

    @Test
    public void getOperator(){
        assertEquals(globalCalculation.getOperator(), Operator.ADD);
    }

    @Test
    public void getResult(){
        assertEquals(globalCalculation.getResult(), 0.0);
    }

    //Setters
    @Test
    public void setResult(){
        globalCalculation.setResult(1.0);
        assertEquals(globalCalculation.getResult(), 1.0);
    }

    //Overrides
    @Test
    public void toStringTest(){
        String expected = "2,00 + 5,00 = 0,00";
        assertEquals(globalCalculation.toString(), expected);
    }

    @Test
    public void equalsEqual(){
        Calculation calculation = new Calculation(2.0, 5.0, "+");
        assertTrue(calculation.equals(globalCalculation));
    }

    @Test
    public void equalsNotEqual(){
        Calculation calculation = new Calculation(1.0, 2.0, "-");
        assertFalse(calculation.equals(globalCalculation));
    }

    @Test
    public void compareToEqual(){
        Calculation calculation = new Calculation(2.0, 5.0, "+");
        assertEquals(calculation.compareTo(globalCalculation), 0);
    }

    @Test
    public void compareToBefore(){
        Calculation calculation = new Calculation(1.0, 5.0, "+");
        assertEquals(calculation.compareTo(globalCalculation), -1);
    }

    @Test
    public void compareToAfter(){
        Calculation calculation = new Calculation(3.0, 5.0, "+");
        assertEquals(calculation.compareTo(globalCalculation), 1);
    }

    @Test
    public void hashCodeAdd(){
        assertEquals(globalCalculation.hashCode(), 2001500);
    }

    @Test
    public void hashCodeSubtract(){
        Calculation calculation = new Calculation(2.0, 5.0, "-");
        assertEquals(calculation.hashCode(), 2002500);
    }

    @Test
    public void hashCodeMultiply(){
        Calculation calculation = new Calculation(2.0, 5.0, "*");
        assertEquals(calculation.hashCode(), 2003500);
    }

    @Test
    public void hashCodeDivide(){
        Calculation calculation = new Calculation(2.0, 5.0, "/");
        assertEquals(calculation.hashCode(), 2004500);
    }

    @Test
    public void hashCodePower(){
        Calculation calculation = new Calculation(2.0, 5.0, "^");
        assertEquals(calculation.hashCode(), 2005500);
    }
}
