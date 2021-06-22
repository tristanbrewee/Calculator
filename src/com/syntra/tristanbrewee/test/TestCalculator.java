package com.syntra.tristanbrewee.test;

import com.syntra.tristanbrewee.model.Calculation;
import com.syntra.tristanbrewee.model.Calculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestCalculator {

    @Test
    public void add(){
        Calculation calculation = new Calculation(1.0, 2.0, "+");
        assertEquals(Calculator.getResult(calculation), 3.0);
    }

    @Test
    public void subtract(){
        Calculation calculation = new Calculation(2.0, 1.0, "-");
        assertEquals(Calculator.getResult(calculation), 1.0);
    }

    @Test
    public void multiply(){
        Calculation calculation = new Calculation(2.0, 3.0, "*");
        assertEquals(Calculator.getResult(calculation), 6.0);
    }

    @Test
    public void divide(){
        Calculation calculation = new Calculation(4.0, 2.0, "/");
        assertEquals(Calculator.getResult(calculation), 2.0);
    }

    @Test
    public void divideByZero(){
        Calculation calculation = new Calculation(5.0, 0.0, "/");
        assertEquals(Calculator.getResult(calculation), 0.0);
    }

    @Test
    public void power(){
        Calculation calculation = new Calculation(2.0, 3.0, "^");
        assertEquals(Calculator.getResult(calculation), 8.0);
    }
}
